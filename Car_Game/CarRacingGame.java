import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class CarRacingGame extends JPanel implements ActionListener, KeyListener {
    Timer timer = new Timer(20, this);
    int carY = 500, carX = 275;
    int speed = 5;
    int roadY = 0;
    int score = 0;
    boolean gameOver = false;

    ArrayList<Rectangle> obstacles = new ArrayList<>();
    Random rand = new Random();

    public CarRacingGame() {
        setFocusable(true);
        addKeyListener(this);
        spawnObstacle();
        timer.start();
    }

    public void spawnObstacle() {
        int[] lanes = {220, 275, 330}; // lane positions
        int lane = lanes[rand.nextInt(3)];
        obstacles.add(new Rectangle(lane, -100, 30, 50));
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 600, 600);

        // Road
        g.setColor(Color.GRAY);
        g.fillRect(200, 0, 200, 600);

        // Lane lines
        g.setColor(Color.WHITE);
        for (int i = 0; i < 600; i += 100) {
            g.fillRect(295, (i + roadY) % 600, 10, 40);
        }

        // Car
        g.setColor(Color.RED);
        g.fillRect(carX, carY, 30, 50);

        // Obstacles
        g.setColor(Color.BLUE);
        for (Rectangle obs : obstacles) {
            g.fillRect(obs.x, obs.y, obs.width, obs.height);
        }

        // Score
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);

        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 180, 300);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press R to Restart", 200, 340);
            timer.stop();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Scroll road
            roadY += speed;
            if (roadY >= 100) roadY = 0;

            // Move obstacles
            for (int i = 0; i < obstacles.size(); i++) {
                Rectangle obs = obstacles.get(i);
                obs.y += speed;
                if (obs.y > 600) {
                    obstacles.remove(i);
                    score += 10;
                    spawnObstacle();
                }

                if (obs.intersects(new Rectangle(carX, carY, 30, 50))) {
                    gameOver = true;
                }
            }
        }

        repaint();
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (!gameOver) {
            if (code == KeyEvent.VK_LEFT && carX > 210) carX -= 55;
            if (code == KeyEvent.VK_RIGHT && carX < 335) carX += 55;
        }

        if (gameOver && code == KeyEvent.VK_R) {
            restartGame();
        }
    }

    public void restartGame() {
        carX = 275;
        obstacles.clear();
        score = 0;
        roadY = 0;
        gameOver = false;
        spawnObstacle();
        timer.start();
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame f = new JFrame("Advanced Car Racing");
        CarRacingGame game = new CarRacingGame();
        f.add(game);
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
