import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.Timer;



import java.util.*;

public class GamePanel extends JPanel implements KeyListener {
    private Pong game;
    static private Ball ball;
    static public Paddle player1, player2;
    private int score1, score2;
    public static Timer timer;
    static public int P1Score = 0;
    static public int P2Score = 0;
    
    
    /**
     * Makes GamePanel
     * @param game
     */
    public GamePanel(Pong game) {
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setDoubleBuffered(true);
        System.setProperty("sun.java2d.d3d", "True");
        this.game = game;
        ball = new Ball();
        player1 = new Paddle(20, 83, 87);
        player2 = new Paddle(Pong.WIDTH - 40, 40, 38);
    	
        timer = new Timer(5, new ActionListener() {
        	public void actionPerformed(ActionEvent e){
            	update();
            	repaint();
            	Toolkit.getDefaultToolkit().sync();
            }
        });//Speed Altering on Timer
        
        timer.start();
        
        
        addKeyListener(this);
        setFocusable(true);
    }
    
    static public void increaseScore(int i) {
    	if (i == 1) {
    		P1Score++;
    	}
    	else {
    		P2Score++;
    	}
    }
    
    public void keyPressed(KeyEvent e){
    	player1.pressed(e.getKeyCode());
    	player2.pressed(e.getKeyCode());
    }
    
    public void keyReleased(KeyEvent e){
    	player1.released(e.getKeyCode());
    	player2.released(e.getKeyCode());
    }
    
    static public void update(){
    	player1.update();
    	player2.update();
    	ball.update();
    }
    
    static public Ball getBall()
    {
    	return ball;
    }
    
    static public Paddle getP1() {
    	return player1;
    }
    
    static public Paddle getP2() {
    	return player2;
    }
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	player1.paint(g);
    	player2.paint(g);
    	ball.paint(g);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		//Unused
	}    
}