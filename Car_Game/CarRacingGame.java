import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CarRacingGame extends JPanel implements ActionListener, KeyListener {
    Timer timer = new Timer(20, this);
    int carY = 400, carX = 250;
    int roadY = 0;
    int speed = 5;

    public CarRacingGame() {
        setFocusable(true);
        addKeyListener(this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        // Road
        g.setColor(Color.GRAY);
        g.fillRect(200, roadY, 200, 600);
        g.fillRect(200, roadY - 600, 200, 600);
        // Car
        g.setColor(Color.RED);
        g.fillRect(carX, carY, 30, 50);
    }

    public void actionPerformed(ActionEvent e) {
        roadY += speed;
        if (roadY >= 600) roadY = 0;
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT && carX > 200) carX -= 10;
        if (code == KeyEvent.VK_RIGHT && carX < 370) carX += 10;
        if (code == KeyEvent.VK_UP && carY > 0) carY -= 10;
        if (code == KeyEvent.VK_DOWN && carY < 550) carY += 10;
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame f = new JFrame("Car Racing");
        CarRacingGame game = new CarRacingGame();
        f.add(game);
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
