import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Pong extends JFrame {

    public final static int WIDTH = 700, HEIGHT = 500;
    private static GamePanel panel;

    public Pong() {
    	panel = new GamePanel(this);
        GamePanel.setGPSize(700,500);
        add(panel); 
        setTitle("Pong");
        System.setProperty("sun.java2d.d3d", "True"); 
        setBackground(Color.WHITE);
        setVisible(true);
        setResizable(false);        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //panel = new GamePanel(this);
        pack();

    }

    public static GamePanel getPanel() {
        return panel;
    }
   
    public static void main(String[] args) {
        new Pong();
    }
}
