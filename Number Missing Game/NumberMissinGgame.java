import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class NumberMissingGame extends JFrame {
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219);
    private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
    private static final Color ERROR_COLOR = new Color(231, 76, 60);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font STATS_FONT = new Font("Arial", Font.PLAIN, 14);
    
    private JPanel gamePanel;
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JButton newGameButton;
    private JButton hintButton;
    private JLabel feedbackLabel;
    private JLabel statsLabel;
    private JLabel timerLabel;
    private JProgressBar difficultyBar;
    private JComboBox<String> difficultyComboBox;
    
    private List<Integer> currentSequence;
    private int missingNumber;
    private int currentLevel;
    private int score;
    private int streak;
    private int hintsUsed;
    private Timer gameTimer;
    private int timeRemaining;
    private boolean gameActive;
    private String currentDifficulty;
    
    // Game statistics
    private int totalGamesPlayed;
    private int totalCorrectAnswers;
    private int bestStreak;
    
    public NumberMissingGame() {
        initializeGame();
        setupUI();
        startNewGame();
    }
    
    private void initializeGame() {
        currentLevel = 1;
        score = 0;
        streak = 0;
        hintsUsed = 0;
        totalGamesPlayed = 0;
        totalCorrectAnswers = 0;
        bestStreak = 0;
        currentDifficulty = "Easy";
        gameActive = false;
    }
    
    private void setupUI() {
        setTitle("Enhanced Number Missing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Create main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(BACKGROUND_COLOR);
        
        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Game Panel
        gamePanel = createGamePanel();
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        
        // Control Panel
        JPanel controlPanel = createControlPanel();
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Set window properties
        setSize(600, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Add subtle animations
        setupAnimations();
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        // Title
        titleLabel = new JLabel("🎯 Number Missing Game", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        
        // Stats panel
        JPanel statsPanel = new JPanel(new GridLayout(2, 3, 10, 5));
        statsPanel.setBackground(CARD_COLOR);
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // Create stats labels
        JLabel levelLabel = new JLabel("Level: " + currentLevel, SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        JLabel streakLabel = new JLabel("Streak: " + streak, SwingConstants.CENTER);
        timerLabel = new JLabel("Time: --", SwingConstants.CENTER);
        statsLabel = new JLabel("Accuracy: --%", SwingConstants.CENTER);
        JLabel hintsLabel = new JLabel("Hints: " + hintsUsed, SwingConstants.CENTER);
        
        Font statsFont = STATS_FONT;
        levelLabel.setFont(statsFont);
        scoreLabel.setFont(statsFont);
        streakLabel.setFont(statsFont);
        timerLabel.setFont(statsFont);
        statsLabel.setFont(statsFont);
        hintsLabel.setFont(statsFont);
        
        statsPanel.add(levelLabel);
        statsPanel.add(scoreLabel);
        statsPanel.add(streakLabel);
        statsPanel.add(timerLabel);
        statsPanel.add(statsLabel);
        statsPanel.add(hintsLabel);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(statsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createGamePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(CARD_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(30, 30, 30, 30)
        ));
        
        // Difficulty selection
        JPanel difficultyPanel = new JPanel(new FlowLayout());
        difficultyPanel.setBackground(CARD_COLOR);
        JLabel diffLabel = new JLabel("Difficulty: ");
        diffLabel.setFont(STATS_FONT);
        difficultyComboBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard", "Expert"});
        difficultyComboBox.setFont(STATS_FONT);
        difficultyComboBox.addActionListener(e -> {
            currentDifficulty = (String) difficultyComboBox.getSelectedItem();
            if (!gameActive) startNewGame();
        });
        
        difficultyPanel.add(diffLabel);
        difficultyPanel.add(difficultyComboBox);
        
        // Progress bar for difficulty
        difficultyBar = new JProgressBar(0, 100);
        difficultyBar.setStringPainted(true);
        difficultyBar.setString("Ready to Start!");
        difficultyBar.setForeground(PRIMARY_COLOR);
        
        // Instruction label
        instructionLabel = new JLabel("Find the missing number in the sequence:", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        // Sequence display area
        JTextArea sequenceArea = new JTextArea(3, 40);
        sequenceArea.setEditable(false);
        sequenceArea.setFont(new Font("Monospace", Font.BOLD, 18));
        sequenceArea.setBackground(new Color(248, 249, 250));
        sequenceArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        sequenceArea.setLineWrap(true);
        sequenceArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(sequenceArea);
        scrollPane.setPreferredSize(new Dimension(500, 80));
        
        // Answer input
        JPanel answerPanel = new JPanel(new FlowLayout());
        answerPanel.setBackground(CARD_COLOR);
        JLabel answerLabel = new JLabel("Your Answer: ");
        answerLabel.setFont(BUTTON_FONT);
        answerField = new JTextField(10);
        answerField.setFont(new Font("Arial", Font.PLAIN, 16));
        answerField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            new EmptyBorder(8, 8, 8, 8)
        ));
        
        answerPanel.add(answerLabel);
        answerPanel.add(answerField);
        
        // Feedback label
        feedbackLabel = new JLabel(" ", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 16));
        feedbackLabel.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        // Add components to panel
        panel.add(difficultyPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(difficultyBar);
        panel.add(Box.createVerticalStrut(20));
        panel.add(instructionLabel);
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(20));
        panel.add(answerPanel);
        panel.add(feedbackLabel);
        
        return panel;
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panel.setBackground(BACKGROUND_COLOR);
        
        // Submit button
        submitButton = createStyledButton("Submit Answer", PRIMARY_COLOR);
        submitButton.addActionListener(e -> checkAnswer());
        
        // Hint button
        hintButton = createStyledButton("💡 Hint", new Color(243, 156, 18));
        hintButton.addActionListener(e -> showHint());
        
        // New game button
        newGameButton = createStyledButton("🎮 New Game", SUCCESS_COLOR);
        newGameButton.addActionListener(e -> startNewGame());
        
        // Exit button
        JButton exitButton = createStyledButton("❌ Exit", ERROR_COLOR);
        exitButton.addActionListener(e -> System.exit(0));
        
        panel.add(submitButton);
        panel.add(hintButton);
        panel.add(newGameButton);
        panel.add(exitButton);
        
        // Enter key support
        answerField.addActionListener(e -> checkAnswer());
        
        return panel;
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private void setupAnimations() {
        // Add subtle fade-in effect for feedback
        Timer fadeTimer = new Timer();
        fadeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (feedbackLabel.getText().trim().isEmpty() || feedbackLabel.getText().equals(" ")) {
                        feedbackLabel.setForeground(BACKGROUND_COLOR);
                    }
                });
            }
        }, 100, 100);
    }
    
    private void startNewGame() {
        gameActive = true;
        totalGamesPlayed++;
        generateSequence();
        updateDisplay();
        startTimer();
        answerField.setText("");
        answerField.requestFocus();
        feedbackLabel.setText(" ");
        
        // Update difficulty progress
        int progress = Math.min(currentLevel * 10, 100);
        difficultyBar.setValue(progress);
        difficultyBar.setString("Level " + currentLevel + " - " + currentDifficulty);
    }
    
    private void generateSequence() {
        currentSequence = new ArrayList<>();
        Random random = new Random();
        
        int sequenceLength;
        int range;
        int step;
        
        // Adjust difficulty based on selection and level
        switch (currentDifficulty) {
            case "Easy":
                sequenceLength = 5 + currentLevel / 3;
                range = 20;
                step = 1;
                timeRemaining = 60;
                break;
            case "Medium":
                sequenceLength = 7 + currentLevel / 2;
                range = 50;
                step = random.nextInt(3) + 1; // 1-3
                timeRemaining = 45;
                break;
            case "Hard":
                sequenceLength = 8 + currentLevel / 2;
                range = 100;
                step = random.nextInt(5) + 2; // 2-6
                timeRemaining = 30;
                break;
            case "Expert":
                sequenceLength = 10 + currentLevel / 2;
                range = 200;
                step = random.nextInt(8) + 3; // 3-10
                timeRemaining = 20;
                break;
            default:
                sequenceLength = 6;
                range = 30;
                step = 1;
                timeRemaining = 60;
        }
        
        // Generate sequence type
        int sequenceType = random.nextInt(4);
        int start = random.nextInt(range);
        
        switch (sequenceType) {
            case 0: // Arithmetic sequence
                generateArithmeticSequence(start, step, sequenceLength);
                break;
            case 1: // Geometric sequence (for higher difficulties)
                if (!currentDifficulty.equals("Easy")) {
                    generateGeometricSequence(start + 1, 2, sequenceLength);
                } else {
                    generateArithmeticSequence(start, step, sequenceLength);
                }
                break;
            case 2: // Fibonacci-like
                if (currentDifficulty.equals("Hard") || currentDifficulty.equals("Expert")) {
                    generateFibonacciLike(start, sequenceLength);
                } else {
                    generateArithmeticSequence(start, step, sequenceLength);
                }
                break;
            default: // Square/cube sequences
                if (currentDifficulty.equals("Expert")) {
                    generatePowerSequence(sequenceLength);
                } else {
                    generateArithmeticSequence(start, step, sequenceLength);
                }
        }
        
        // Remove one number randomly (not first or last)
        int removeIndex = random.nextInt(currentSequence.size() - 2) + 1;
        missingNumber = currentSequence.get(removeIndex);
        currentSequence.set(removeIndex, null); // Use null to represent missing number
    }
    
    private void generateArithmeticSequence(int start, int step, int length) {
        for (int i = 0; i < length; i++) {
            currentSequence.add(start + i * step);
        }
    }
    
    private void generateGeometricSequence(int start, int ratio, int length) {
        int current = start;
        for (int i = 0; i < length; i++) {
            currentSequence.add(current);
            current *= ratio;
            if (current > 1000) break; // Prevent too large numbers
        }
    }
    
    private void generateFibonacciLike(int start, int length) {
        if (length < 2) return;
        currentSequence.add(start);
        currentSequence.add(start + 1);
        
        for (int i = 2; i < length; i++) {
            int next = currentSequence.get(i-1) + currentSequence.get(i-2);
            if (next > 1000) break;
            currentSequence.add(next);
        }
    }
    
    private void generatePowerSequence(int length) {
        Random random = new Random();
        int power = random.nextInt(2) + 2; // 2 or 3 (square or cube)
        
        for (int i = 1; i <= length; i++) {
            currentSequence.add((int) Math.pow(i, power));
        }
    }
    
    private void updateDisplay() {
        // Update sequence display
        StringBuilder sequenceText = new StringBuilder("Sequence: ");
        for (int i = 0; i < currentSequence.size(); i++) {
            if (currentSequence.get(i) == null) {
                sequenceText.append("? ");
            } else {
                sequenceText.append(currentSequence.get(i)).append(" ");
            }
            
            if (i < currentSequence.size() - 1) {
                sequenceText.append("→ ");
            }
        }
        
        // Find the sequence display area and update it
        Component[] components = gamePanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                JTextArea textArea = (JTextArea) scrollPane.getViewport().getView();
                textArea.setText(sequenceText.toString());
                break;
            }
        }
        
        // Update stats
        updateStats();
    }
    
    private void updateStats() {
        // Update header stats
        Component[] headerComponents = ((JPanel) ((JPanel) getContentPane().getComponent(0)).getComponent(0)).getComponents();
        if (headerComponents.length > 1 && headerComponents[1] instanceof JPanel) {
            JPanel statsPanel = (JPanel) headerComponents[1];
            Component[] statLabels = statsPanel.getComponents();
            
            if (statLabels.length >= 6) {
                ((JLabel) statLabels[0]).setText("Level: " + currentLevel);
                ((JLabel) statLabels[1]).setText("Score: " + score);
                ((JLabel) statLabels[2]).setText("Streak: " + streak);
                ((JLabel) statLabels[4]).setText("Accuracy: " + (totalGamesPlayed > 0 ? 
                    (totalCorrectAnswers * 100 / totalGamesPlayed) + "%" : "--%"));
                ((JLabel) statLabels[5]).setText("Hints: " + hintsUsed);
            }
        }
    }
    
    private void startTimer() {
        if (gameTimer != null) {
            gameTimer.cancel();
        }
        
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (timeRemaining > 0 && gameActive) {
                        timeRemaining--;
                        timerLabel.setText("Time: " + timeRemaining + "s");
                        
                        // Change color based on remaining time
                        if (timeRemaining <= 10) {
                            timerLabel.setForeground(ERROR_COLOR);
                        } else if (timeRemaining <= 20) {
                            timerLabel.setForeground(new Color(243, 156, 18));
                        } else {
                            timerLabel.setForeground(Color.BLACK);
                        }
                    } else if (gameActive) {
                        // Time's up
                        gameActive = false;
                        showFeedback("⏰ Time's up! The answer was: " + missingNumber, ERROR_COLOR);
                        streak = 0;
                        gameTimer.cancel();
                    }
                });
            }
        }, 1000, 1000);
    }
    
    private void checkAnswer() {
        if (!gameActive) return;
        
        try {
            int userAnswer = Integer.parseInt(answerField.getText().trim());
            gameActive = false;
            gameTimer.cancel();
            
            if (userAnswer == missingNumber) {
                // Correct answer
                totalCorrectAnswers++;
                streak++;
                if (streak > bestStreak) bestStreak = streak;
                
                int points = calculatePoints();
                score += points;
                
                showFeedback("🎉 Correct! +" + points + " points", SUCCESS_COLOR);
                
                // Level up every 3 correct answers
                if (totalCorrectAnswers % 3 == 0) {
                    currentLevel++;
                    showFeedback("🆙 Level Up! Now on Level " + currentLevel, PRIMARY_COLOR);
                }
                
                // Auto-start next game after delay
         