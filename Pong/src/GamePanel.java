import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener {
    private Pong game;
    private Ball ball;
    private Paddle player1, player2;
    private int score1, score2;

    public GamePanel(Pong game) {
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        this.game = game;
        //ball = new Ball(game);
        player1 = new Paddle(20, 87, 83);
        player2 = new Paddle(Pong.WIDTH - 40, 38, 40);
        //Timer timer = new Timer(5, this);
        //timer.start();
        addKeyListener(this);
        setFocusable(true);
    }
    
    public void keyPressed(KeyEvent e){
    	player1.pressed(e.getKeyCode());
    	player2.pressed(e.getKeyCode());
    }
    
    public void keyReleased(KeyEvent e){
    	player1.released(e.getKeyCode());
    	player2.released(e.getKeyCode());
    }
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	player1.paint(g);
    	player2.paint(g);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		//Unused
	}    
}
