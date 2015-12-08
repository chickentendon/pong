import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class GamePanel extends JPanel implements KeyListener {
    
	static private Ball ball;
    static public Paddle player1, player2;
    public static Timer timer;
    static public int p1Score = 0;
    static public int p2Score = 0;
	Font ttfBase = null;
	Font ttfFinal = null;
	static boolean p1Win = false, p2Win = false;
	static String winText;

    // Creates the GamePanel, on which all game components are drawn
    public GamePanel(Pong game) {
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        
        //Imports custom font for scorekeeping
        try {
    		InputStream myStream = new BufferedInputStream(new FileInputStream("Roboto-Thin.ttf"));
    		ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
    		ttfFinal = ttfBase.deriveFont(Font.PLAIN, 350);
    	} catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }
        
        //Initialize ball, paddles
        ball = new Ball();
        player1 = new Paddle(20, 83, 87);
        player2 = new Paddle(Pong.WIDTH - 40, 40, 38);
    	
        //Sets timer which updates and repaints the panel 
        timer = new Timer(2, new ActionListener() {
        	public void actionPerformed(ActionEvent e){
            	update(); //updates ball, paddles, and scores
            	repaint();
            	Toolkit.getDefaultToolkit().sync(); //Allows for smooth animation
            	GamePanel.checkWin();
            	if (p1Win || p2Win) {
            		JOptionPane.showMessageDialog(null, winText);
            		resetGame();
            	}
            }
        });
        addKeyListener(this);
        setFocusable(true);
    }
    
    // Increments score of designated player
    static public void increaseScore(int i) {
    	if (i == 1) {
    		p1Score++;
    	}
    	else {
    		p2Score++;
    	}
    }
    
    //Handles the movement of the paddles 
    public void keyPressed(KeyEvent e) {
    	System.out.println(e.getKeyCode());
    	player1.pressed(e.getKeyCode());
    	player2.pressed(e.getKeyCode());
    }
    
    //Stops movement of paddles
    public void keyReleased(KeyEvent e) {
    	player1.released(e.getKeyCode());
    	player2.released(e.getKeyCode());
    }
    
    //Updates all game components
    static public void update() {
    	player1.update();
    	player2.update();
    	ball.update();
    }
    
    //Getter for ball
    static public Ball getBall() {
    	return ball;
    }
    
    //Getter for player 1's paddle
    static public Paddle getP1() {
    	return player1;
    }
    
    //Getter for player 2's paddle
    static public Paddle getP2() {
    	return player2;
    }
    
    public static void checkWin() {
    	if (p1Score == 5) {
    		p1Win = true;
    		winText = "P1 wins! Press [space] to start a new game";
    	}
    	else if (p2Score == 5) {
    		p2Win = true;
    		winText = "P2 wins! Press [space] to start a new game";
    	}
    }
    
    public void resetGame() {
    	p1Win = false;
    	p2Win = false;
    	
    	p1Score = 0;
    	p2Score = 0;
    	
    	Pong.getPanel().repaint();
    }
    //Paint function for all
    @Override
    public void paintComponent(Graphics g) {
    	
    	Graphics2D g2d = (Graphics2D)g;
    	g2d.setRenderingHint(
    	        RenderingHints.KEY_TEXT_ANTIALIASING,
    	        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    	super.paintComponent(g2d);
    	
    	
    	g2d.setColor(Color.GRAY); //Color of text
    	g2d.setFont(ttfFinal);	  //Custom font
    	
    	//Sets size and position of scores
    	g2d.drawString(p1Score + "", 75 , 350); 
    	g2d.drawString(p2Score + "", 435, 350);
    	
    	g2d.setColor(Color.WHITE); //Color of mid-line
    	g2d.drawLine(Pong.getPanel().getWidth()/2,20,Pong.getPanel().getWidth()/2,Pong.getPanel().getHeight()-20);
    	
    	//Paints all components
    	player1.paint(g2d);
    	player2.paint(g2d);
    	ball.paint(g2d);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		//Unused
	}
	
	//Sets the GamePanel size
	 public static void setGPSize(int w,int h) {
		 Pong.getPanel().setPreferredSize(new Dimension(w, h));
    }  
}
