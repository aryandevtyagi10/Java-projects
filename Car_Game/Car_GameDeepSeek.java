import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class EnhancedCarRacingGame extends JPanel implements ActionListener, KeyListener {
    private enum GameState { MENU, PLAYING, GAME_OVER, HIGH_SCORES, CAR_SELECTION, SETTINGS }
    private GameState state = GameState.MENU;
    
    private Timer timer = new Timer(20, this);
    private int carX = 275, carY = 500;
    private int speed = 5, roadY = 0, score = 0, lives = 3, level = 1;
    private boolean paused = false;
    private List<Rectangle> obstacles = new ArrayList<>();
    private List<Rectangle> powerUps = new ArrayList<>();
    private Random rand = new Random();
    private List<Integer> highScores = new ArrayList<>();
    
    // Game settings
    private boolean soundEnabled = true;
    private boolean nightMode = false;
    private boolean rainEffect = false;
    private int selectedCar = 0; // 0: Red, 1: Blue, 2: Green
    
    // Particles for effects
    private List<Particle> particles = new ArrayList<>();
    
    // UI Buttons
    private List<RoundButton> menuButtons = new ArrayList<>();
    private List<RoundButton> gameOverButtons = new ArrayList<>();
    private List<RoundButton> carButtons = new ArrayList<>();
    private RoundButton soundButton, nightModeButton, rainButton;
    
    // Colors
    private Color primaryColor = new Color(46, 204, 113);
    private Color secondaryColor = new Color(52, 152, 219);
    private Color accentColor = new Color(231, 76, 60);
    private Color backgroundColor = new Color(44, 62, 80);
    
    // Fonts
    private Font titleFont = new Font("Arial Rounded MT Bold", Font.BOLD, 50);
    private Font buttonFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 24);
    private Font hudFont = new Font("Arial Rounded MT Bold", Font.BOLD, 20);
    
    // Game metrics
    private long startTime;
    private int coins = 0;
    private int distance = 0;
    private int powerUpType = -1; // 0: Shield, 1: Speed, 2: Coin Magnet
    private long powerUpStartTime;
    private final int POWER_UP_DURATION = 5000; // 5 seconds
    
    public EnhancedCarRacingGame() {
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMove(e);
            }
        });
        
        // Initialize buttons
        createButtons();
        
        // Load high scores
        loadHighScores();
        
        spawnObstacle();
        timer.start();
        startTime = System.currentTimeMillis();
    }
    
    private void createButtons() {
        // Menu buttons
        menuButtons.add(new RoundButton(220, 300, 160, 50, "Start Game", primaryColor));
        menuButtons.add(new RoundButton(220, 370, 160, 50, "Select Car", secondaryColor));
        menuButtons.add(new RoundButton(220, 440, 160, 50, "High Scores", secondaryColor));
        menuButtons.add(new RoundButton(220, 510, 160, 50, "Settings", secondaryColor));
        
        // Game over buttons
        gameOverButtons.add(new RoundButton(150, 380, 140, 45, "Restart", primaryColor));
        gameOverButtons.add(new RoundButton(310, 380, 140, 45, "Menu", secondaryColor));
        
        // Car selection buttons
        carButtons.add(new RoundButton(150, 350, 80, 45, "Red", new Color(231, 76, 60)));
        carButtons.add(new RoundButton(250, 350, 80, 45, "Blue", new Color(52, 152, 219)));
        carButtons.add(new RoundButton(350, 350, 80, 45, "Green", new Color(46, 204, 113)));
        carButtons.add(new RoundButton(220, 420, 160, 45, "Back", secondaryColor));
        
        // Settings buttons
        soundButton = new RoundButton(220, 300, 160, 45, "Sound: ON", soundEnabled ? primaryColor : accentColor);
        nightModeButton = new RoundButton(220, 370, 160, 45, "Night Mode: OFF", secondaryColor);
        rainButton = new RoundButton(220, 440, 160, 45, "Rain: OFF", secondaryColor);
        menuButtons.add(new RoundButton(220, 510, 160, 45, "Back", secondaryColor));
    }
    
    private void spawnObstacle() {
        int[] lanes = {220, 275, 330};
        int lane = lanes[rand.nextInt(3)];
        obstacles.add(new Rectangle(lane, -100, 30, 50));
        
        // Randomly spawn power-ups (10% chance)
        if (rand.nextInt(10) == 0) {
            int powerLane = lanes[rand.nextInt(3)];
            powerUps.add(new Rectangle(powerLane, -150, 30, 30));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        switch (state) {
            case MENU: drawMenu(g2d); break;
            case PLAYING: drawGame(g2d); break;
            case GAME_OVER: drawGameOver(g2d); break;
            case HIGH_SCORES: drawHighScores(g2d); break;
            case CAR_SELECTION: drawCarSelection(g2d); break;
            case SETTINGS: drawSettings(g2d); break;
        }
    }
    
    private void drawMenu(Graphics2D g) {
        // Background with gradient
        GradientPaint gradient = new GradientPaint(0, 0, backgroundColor, 0, getHeight(), 
                                                  new Color(33, 47, 60));
        g.setPaint(gradient);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Title with shadow effect
        g.setFont(titleFont);
        String title = "CAR RACER";
        g.setColor(new Color(0, 0, 0, 150));
        g.drawString(title, 203, 203);
        g.setColor(Color.WHITE);
        g.drawString(title, 200, 200);
        
        // Draw buttons
        for (RoundButton button : menuButtons) {
            button.draw(g);
        }
        
        // Decorative elements
        drawCarAtPosition(g, 150, 100, 0.7);
        drawCarAtPosition(g, 400, 120, 0.7);
        
        // Draw animated particles
        drawParticles(g);
    }
    
    private void drawGame(Graphics2D g) {
        // Background with night mode effect
        if (nightMode) {
            g.setColor(new Color(20, 30, 48));
            g.fillRect(0, 0, getWidth(), getHeight());
        } else {
            GradientPaint gradient = new GradientPaint(0, 0, new Color(100, 130, 150), 
                                                      0, getHeight(), new Color(70, 100, 130));
            g.setPaint(gradient);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        
        // Road with perspective effect
        drawRoad(g);
        
        // Draw power-ups
        for (Rectangle power : powerUps) {
            drawPowerUp(g, power);
        }
        
        // Draw obstacles
        for (Rectangle obs : obstacles) {
            drawObstacle(g, obs);
        }
        
        // Draw player car
        drawPlayerCar(g);
        
        // Draw rain effect if enabled
        if (rainEffect) {
            drawRain(g);
        }
        
        // Draw particles
        drawParticles(g);
        
        // Draw HUD
        drawHUD(g);
        
        // Draw active power-up indicator
        if (powerUpType != -1) {
            drawPowerUpIndicator(g);
        }
        
        // Draw pause overlay
        if (paused) {
            drawPauseOverlay(g);
        }
    }
    
    private void drawRoad(Graphics2D g) {
        // Road surface
        g.setColor(new Color(50, 50, 50));
        g.fillRect(180, 0, 240, getHeight());
        
        // Road markings with perspective effect
        g.setColor(Color.YELLOW);
        for (int i = 0; i < 600; i += 100) {
            int yPos = (i + roadY) % 600;
            int width = 10 - (yPos / 60);
            if (width < 2) width = 2;
            g.fillRect(295, yPos, width, 40);
        }
        
        // Road borders
        g.setColor(new Color(120, 120, 120));
        g.fillRect(180, 0, 10, getHeight());
        g.fillRect(410, 0, 10, getHeight());
        
        // Road shoulders
        g.setColor(new Color(100, 80, 40));
        g.fillRect(170, 0, 10, getHeight());
        g.fillRect(420, 0, 10, getHeight());
    }
    
    private void drawPlayerCar(Graphics2D g) {
        Color carColor;
        switch (selectedCar) {
            case 0: carColor = new Color(231, 76, 60); break; // Red
            case 1: carColor = new Color(52, 152, 219); break; // Blue
            default: carColor = new Color(46, 204, 113); // Green
        }
        
        // Car body
        g.setColor(carColor);
        g.fillRoundRect(carX, carY, 40, 70, 15, 15);
        
        // Car top
        g.fillRoundRect(carX + 5, carY + 5, 30, 40, 10, 10);
        
        // Windows
        g.setColor(new Color(200, 230, 255, 180));
        g.fillRoundRect(carX + 7, carY + 10, 26, 20, 5, 5);
        
        // Wheels
        g.setColor(Color.BLACK);
        g.fillOval(carX - 3, carY + 10, 16, 16);
        g.fillOval(carX - 3, carY + 50, 16, 16);
        g.fillOval(carX + 27, carY + 10, 16, 16);
        g.fillOval(carX + 27, carY + 50, 16, 16);
        
        // Headlights
        g.setColor(Color.YELLOW);
        g.fillOval(carX + 5, carY + 60, 8, 8);
        g.fillOval(carX + 27, carY + 60, 8, 8);
        
        // If shield power-up is active, draw shield effect
        if (powerUpType == 0) {
            g.setColor(new Color(30, 170, 255, 100));
            g.fillOval(carX - 10, carY - 10, 60, 90);
        }
    }
    
    private void drawObstacle(Graphics2D g, Rectangle obs) {
        // Obstacle car
        g.setColor(new Color(192, 57, 43));
        g.fillRoundRect(obs.x, obs.y, obs.width, obs.height, 10, 10);
        
        // Car top
        g.fillRoundRect(obs.x + 5, obs.y + 5, 20, 20, 5, 5);
        
        // Windows
        g.setColor(new Color(200, 230, 255, 180));
        g.fillRoundRect(obs.x + 7, obs.y + 8, 16, 10, 3, 3);
        
        // Wheels
        g.setColor(Color.BLACK);
        g.fillOval(obs.x - 3, obs.y + 10, 10, 10);
        g.fillOval(obs.x - 3, obs.y + 35, 10, 10);
        g.fillOval(obs.x + 23, obs.y + 10, 10, 10);
        g.fillOval(obs.x + 23, obs.y + 35, 10, 10);
    }
    
    private void drawPowerUp(Graphics2D g, Rectangle power) {
        // Power-up types: 0: Shield, 1: Speed, 2: Coin Magnet
        int type = rand.nextInt(3);
        
        // Draw power-up
        switch (type) {
            case 0: // Shield
                g.setColor(new Color(30, 170, 255));
                g.fillOval(power.x, power.y, power.width, power.height);
                g.setColor(Color.WHITE);
                g.drawOval(power.x + 5, power.y + 5, power.width - 10, power.height - 10);
                g.drawOval(power.x + 10, power.y + 10, power.width - 20, power.height - 20);
                break;
                
            case 1: // Speed
                g.setColor(new Color(46, 204, 113));
                g.fillOval(power.x, power.y, power.width, power.height);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("+", power.x + 10, power.y + 23);
                break;
                
            case 2: // Coin Magnet
                g.setColor(new Color(241, 196, 15));
                g.fillOval(power.x, power.y, power.width, power.height);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("C", power.x + 10, power.y + 23);
                break;
        }
    }
    
    private void drawHUD(Graphics2D g) {
        // Semi-transparent background for HUD
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRoundRect(10, 10, 180, 100, 15, 15);
        
        g.setColor(Color.WHITE);
        g.setFont(hudFont);
        
        // Score
        g.drawString("Score: " + score, 20, 35);
        
        // Lives
        g.drawString("Lives: " + lives, 20, 60);
        
        // Coins
        g.drawString("Coins: " + coins, 20, 85);
        
        // Level
        g.drawString("Level: " + level, 20, 110);
        
        // Distance
        g.drawString("Distance: " + distance + "m", 400, 35);
        
        // Time
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        g.drawString("Time: " + elapsedTime + "s", 400, 60);
        
        // Speed
        g.drawString("Speed: " + (speed + level - 1) + " km/h", 400, 85);
        
        // Draw lives as hearts
        for (int i = 0; i < lives; i++) {
            g.setColor(new Color(231, 76, 60));
            g.fillOval(120 + i * 25, 43, 15, 15);
        }
    }
    
    private void drawPowerUpIndicator(Graphics2D g) {
        int x = 300;
        int y = 20;
        int width = 30;
        int height = 30;
        
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRoundRect(x - 5, y - 5, width + 10, height + 10, 10, 10);
        
        switch (powerUpType) {
            case 0: // Shield
                g.setColor(new Color(30, 170, 255));
                g.fillOval(x, y, width, height);
                g.setColor(Color.WHITE);
                g.drawOval(x + 5, y + 5, width - 10, height - 10);
                g.drawOval(x + 10, y + 10, width - 20, height - 20);
                break;
                
            case 1: // Speed
                g.setColor(new Color(46, 204, 113));
                g.fillOval(x, y, width, height);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("+", x + 10, y + 23);
                break;
                
            case 2: // Coin Magnet
                g.setColor(new Color(241, 196, 15));
                g.fillOval(x, y, width, height);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("C", x + 10, y + 23);
                break;
        }
        
        // Draw timer bar
        long elapsed = System.currentTimeMillis() - powerUpStartTime;
        float percent = 1.0f - (float) elapsed / POWER_UP_DURATION;
        if (percent < 0) percent = 0;
        
        g.setColor(new Color(200, 200, 200));
        g.fillRoundRect(x - 5, y + height + 5, width + 10, 5, 3, 3);
        g.setColor(secondaryColor);
        g.fillRoundRect(x - 5, y + height + 5, (int) ((width + 10) * percent), 5, 3, 3);
    }
    
    private void drawPauseOverlay(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(titleFont);
        g.drawString("PAUSED", 200, 300);
        
        g.setFont(buttonFont);
        g.drawString("Press P to Resume", 200, 350);
    }
    
    private void drawGameOver(Graphics2D g) {
        drawGame(g);
        
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(titleFont);
        g.drawString("GAME OVER", 170, 280);
        
        g.setFont(hudFont);
        g.drawString("Score: " + score, 250, 320);
        g.drawString("Distance: " + distance + "m", 250, 350);
        
        // Draw buttons
        for (RoundButton button : gameOverButtons) {
            button.draw(g);
        }
    }
    
    private void drawHighScores(Graphics2D g) {
        // Background
        GradientPaint gradient = new GradientPaint(0, 0, backgroundColor, 0, getHeight(), 
                                                  new Color(33, 47, 60));
        g.setPaint(gradient);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(titleFont);
        g.drawString("High Scores", 180, 150);
        
        g.setFont(hudFont);
        for (int i = 0; i < Math.min(highScores.size(), 5); i++) {
            g.drawString((i + 1) + ". " + highScores.get(i), 250, 200 + i * 40);
        }
        
        g.drawString("Press M to return to Menu", 200, 450);
        
        // Draw decorative cars
        drawCarAtPosition(g, 150, 350, 0.6);
        drawCarAtPosition(g, 400, 350, 0.6);
    }
    
    private void drawCarSelection(Graphics2D g) {
        // Background
        GradientPaint gradient = new GradientPaint(0, 0, backgroundColor, 0, getHeight(), 
                                                  new Color(33, 47, 60));
        g.setPaint(gradient);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(titleFont);
        g.drawString("Select Your Car", 150, 150);
        
        // Draw car options
        drawCarAtPosition(g, 150, 200, 1.2);
        drawCarAtPosition(g, 250, 200, 1.2);
        drawCarAtPosition(g, 350, 200, 1.2);
        
        // Draw buttons
        for (RoundButton button : carButtons) {
            button.draw(g);
        }
    }
    
    private void drawSettings(Graphics2D g) {
        // Background
        GradientPaint gradient = new GradientPaint(0, 0, backgroundColor, 0, getHeight(), 
                                                  new Color(33, 47, 60));
        g.setPaint(gradient);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(titleFont);
        g.drawString("Settings", 200, 150);
        
        // Draw settings buttons
        soundButton.draw(g);
        nightModeButton.draw(g);
        rainButton.draw(g);
        
        // Draw back button
        RoundButton backButton = new RoundButton(220, 510, 160, 45, "Back", secondaryColor);
        backButton.draw(g);
    }
    
    private void drawCarAtPosition(Graphics2D g, int x, int y, double scale) {
        int width = (int) (40 * scale);
        int height = (int) (70 * scale);
        
        // Car body
        g.setColor(menuButtons.get(0).getColor());
        g.fillRoundRect(x, y, width, height, 15, 15);
        
        // Car top
        g.fillRoundRect(x + (int)(5 * scale), y + (int)(5 * scale), 
                        (int)(30 * scale), (int)(40 * scale), 10, 10);
        
        // Windows
        g.setColor(new Color(200, 230, 255, 180));
        g.fillRoundRect(x + (int)(7 * scale), y + (int)(10 * scale), 
                        (int)(26 * scale), (int)(20 * scale), 5, 5);
    }
    
    private void drawRain(Graphics2D g) {
        g.setColor(new Color(200, 230, 255, 100));
        for (int i = 0; i < 100; i++) {
            int x = rand.nextInt(600);
            int y = (rand.nextInt(600) + roadY) % 600;
            g.drawLine(x, y, x + 2, y + 10);
        }
    }
    
    private void drawParticles(Graphics2D g) {
        for (Iterator<Particle> it = particles.iterator(); it.hasNext();) {
            Particle p = it.next();
            if (p.update()) {
                p.draw(g);
            } else {
                it.remove();
            }
        }
    }
    
    private void createParticles(int x, int y, Color color) {
        for (int i = 0; i < 15; i++) {
            particles.add(new Particle(x, y, color));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (state == GameState.PLAYING && !paused) {
            // Update road
            roadY += speed;
            if (roadY >= 600) roadY = 0;
            
            // Update distance
            distance += speed / 2;
            
            // Update obstacles
            for (int i = 0; i < obstacles.size(); i++) {
                Rectangle obs = obstacles.get(i);
                obs.y += speed;
                
                if (obs.y > 600) {
                    