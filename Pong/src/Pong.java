import java.awt.Color;

import javax.swing.JFrame;

public class Pong extends JFrame {

    public final static int WIDTH = 700, HEIGHT = 500;
    private static GamePanel panel;

    public Pong() {
        setSize(WIDTH, HEIGHT);
        setTitle("Pong");
        setBackground(Color.WHITE);
        setVisible(true);
        setResizable(false);        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new GamePanel(this);
        add(panel);
    }

    public GamePanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        new Pong();
    }
}
