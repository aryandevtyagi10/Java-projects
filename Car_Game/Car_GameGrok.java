import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class CarRacingGame extends JPanel implements ActionListener, KeyListener {
    private enum GameState { MENU, PLAYING, GAME_OVER, HIGH_SCORES }
    private GameState state = GameState.MENU;
    
    private Timer timer = new Timer(20, this);
    private int carX = 275, carY = 500;
    private int speed = 5, roadY = 0, score = 0, lives = 3;
    private boolean paused = false;
    private ArrayList<Rectangle> obstacles = new ArrayList<>();
    private Random rand = new Random();
    private ArrayList<Integer> highScores = new ArrayList<>();
    
    // Images (replace with your own sprite paths)
    private Image carImage, obstacleImage, backgroundImage;
    
    // UI Buttons
    private Rectangle startButton = new Rectangle(220, 300, 160, 50);
    private Rectangle highScoreButton = new Rectangle(220, 360, 160, 50);
    private Rectangle exitButton = new Rectangle(220, 420, 160, 50);
    
    public CarRacingGame() {
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
        
        // Load images (ensure these files exist in your project directory)
        try {
            carImage = new ImageIcon("car.png").getImage();
            obstacleImage = new ImageIcon("obstacle_car.png").getImage();
            backgroundImage = new ImageIcon("road_bg.png").getImage();
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
        }
        
        // Load high scores
        loadHighScores();
        
        spawnObstacle();
        timer.start();
    }
    
    private void spawnObstacle() {
        int[] lanes = {220, 275, 330};
        int lane = lanes[rand.nextInt(3)];
        obstacles.add(new Rectangle(lane, -100, 30, 50));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        switch (state) {
            case MENU:
                drawMenu(g2d);
                break;
            case PLAYING:
                drawGame(g2d);
                break;
            case GAME_OVER:
                drawGameOver(g2d);
                break;
            case HIGH_SCORES:
                drawHighScores(g2d);
                break;
        }
    }
    
    private void drawMenu(Graphics2D g) {
        g.drawImage(backgroundImage, 0, 0, 600, 600, null);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 600, 600);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Car Racing", 200, 200);
        
        // Draw buttons
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.GREEN);
        g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
        g.setColor(Color.WHITE);
        g.drawString("Start", startButton.x + 60, startButton.y + 35);
        
        g.setColor(Color.BLUE);
        g.fillRect(highScoreButton.x, highScoreButton.y, highScoreButton.width, highScoreButton.height);
        g.setColor(Color.WHITE);
        g.drawString("High Scores", highScoreButton.x + 30, highScoreButton.y + 35);
        
        g.setColor(Color.RED);
        g.fillRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
        g.setColor(Color.WHITE);
        g.drawString("Exit", exitButton.x + 60, exitButton.y + 35);
    }
    
    private void drawGame(Graphics2D g) {
        // Background
        g.drawImage(backgroundImage, 0, roadY, 600, 600, null);
        g.drawImage(backgroundImage, 0, roadY - 600, 600, 600, null);
        
        // Lane lines
        g.setColor(Color.WHITE);
        for (int i = 0; i < 600; i += 100) {
            g.fillRect(295, (i + roadY) % 600, 10, 40);
        }
        
        // Car
        g.drawImage(carImage, carX, carY, 30, 50, null);
        
        // Obstacles
        for (Rectangle obs : obstacles) {
            g.drawImage(obstacleImage, obs.x, obs.y, obs.width, obs.height, null);
        }
        
        // HUD
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);
        g.drawString("Lives: " + lives, 500, 25);
        
        if (paused) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, 600, 600);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("PAUSED", 230, 300);
        }
    }
    
    private void drawGameOver(Graphics2D g) {
        drawGame(g);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("GAME OVER", 180, 280);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, 250, 320);
        g.drawString("Press R to Restart or M for Menu", 160, 360);
    }
    
    private void drawHighScores(Graphics2D g) {
        g.drawImage(backgroundImage, 0, 0, 600, 600, null);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 600, 600);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("High Scores", 200, 150);
        
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        for (int i = 0; i < Math.min(highScores.size(), 5); i++) {
            g.drawString((i + 1) + ". " + highScores.get(i), 250, 200 + i * 30);
        }
        
        g.drawString("Press M to return to Menu", 200, 400);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (state == GameState.PLAYING && !paused) {
            // Update road
            roadY += speed;
            if (roadY >= 600) roadY = 0;
            
            // Update obstacles
            for (int i = 0; i < obstacles.size(); i++) {
                Rectangle obs = obstacles.get(i);
                obs.y += speed;
                if (obs.y > 600) {
                    obstacles.remove(i);
                    score += 10;
                    spawnObstacle();
                    // Increase difficulty
                    if (score % 100 == 0 && speed < 10) speed++;
                }
                
                if (obs.intersects(new Rectangle(carX, carY, 30, 50))) {
                    lives--;
                    obstacles.remove(i);
                    spawnObstacle();
                    // TODO: Play collision sound
                    if (lives <= 0) {
                        state = GameState.GAME_OVER;
                        updateHighScores();
                        timer.stop();
                    }
                }
            }
        }
        repaint();
    }
    
    private void handleMouseClick(MouseEvent e) {
        if (state == GameState.MENU) {
            Point p = e.getPoint();
            if (startButton.contains(p)) {
                state = GameState.PLAYING;
            } else if (highScoreButton.contains(p)) {
                state = GameState.HIGH_SCORES;
            } else if (exitButton.contains(p)) {
                System.exit(0);
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (state == GameState.PLAYING && !paused) {
            if (code == KeyEvent.VK_LEFT && carX > 210) carX -= 55;
            if (code == KeyEvent.VK_RIGHT && carX < 335) carX += 55;
            if (code == KeyEvent.VK_P) paused = !paused;
        } else if (state == GameState.GAME_OVER) {
            if (code == KeyEvent.VK_R) restartGame();
            if (code == KeyEvent.VK_M) state = GameState.MENU;
        } else if (state == GameState.HIGH_SCORES && code == KeyEvent.VK_M) {
            state = GameState.MENU;
        }
    }
    
    private void restartGame() {
        carX = 275;
        lives = 3;
        obstacles.clear();
        score = 0;
        speed = 5;
        roadY = 0;
        state = GameState.PLAYING;
        paused = false;
        spawnObstacle();
        timer.start();
    }
    
    private void loadHighScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(Integer.parseInt(line));
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, initialize with zeros
            for (int i = 0; i < 5; i++) highScores.add(0);
        } catch (IOException e) {
            System.err.println("Error reading high scores: " + e.getMessage());
        }
    }
    
    private void updateHighScores() {
        highScores.add(score);
        highScores.sort((a, b) -> b - a); // Sort descending
        while (highScores.size() > 5) highScores.remove(highScores.size() - 1);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscores.txt"))) {
            for (int s : highScores) {
                writer.write(String.valueOf(s));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving high scores: " + e.getMessage());
        }
    }
    
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Advanced Car Racing");
        CarRacingGame game = new CarRacingGame();
        frame.add(game);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
  }
