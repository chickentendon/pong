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
	Font ttfBaseThin = null;
	Font ttfBaseItalic = null;
	Font robotoThin = null;
	Font robotoItalic = null;
	static boolean p1Win = false, p2Win = false;
	static String winText;

    // Creates the GamePanel, on which all game components are drawn
    public GamePanel(Pong game) {
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        
        //Imports custom font for scorekeeping
        try {
    		InputStream myStream = new BufferedInputStream(new FileInputStream("Roboto-Thin.ttf"));
    		ttfBaseThin = Font.createFont(Font.TRUETYPE_FONT, myStream);
    		robotoThin = ttfBaseThin.deriveFont(Font.PLAIN, 350);
    		
    		InputStream myStream1 = new BufferedInputStream(new FileInputStream("Roboto-Italic.ttf"));
    		ttfBaseItalic = Font.createFont(Font.TRUETYPE_FONT, myStream1);
    		robotoItalic = ttfBaseItalic.deriveFont(Font.PLAIN, 16);
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
    	        RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //Anti-aliasing for string drawing
    	
    	super.paintComponent(g2d);
    	
    	g2d.setColor(Color.darkGray); //Color of text
    	g2d.setFont(robotoThin);	  //Custom font
    	
    	//Sets size and position of scores
    	g2d.drawString(p1Score + "", 75 , 375); 
    	g2d.drawString(p2Score + "", 435, 375);
    	
    	//Draws new game and pause instructions 
    	g2d.setFont(ttfBaseItalic.deriveFont(Font.PLAIN, 13));
    	g2d.setColor(Color.gray); //Color of text
    	g2d.drawString("First to   5 wins", 306, 470);
    	g2d.setFont(robotoItalic); //Change font
    	g2d.drawString("[N] starts new game     [SPACE] to serve", 200, 490);
    	
    	g2d.setColor(Color.WHITE); //Color and painting of mid-line
    	
    	//Draw midline
    	g2d.drawLine(Pong.getPanel().getWidth()/2,20,Pong.getPanel().getWidth()/2,Pong.getPanel().getHeight()-20);
    	
    	//Paints paddles
    	player1.paint(g2d);
    	player2.paint(g2d);
    	
    	//Sets font and draws up/down controls on paddles
    	g2d.setFont(ttfBaseItalic.deriveFont(Font.PLAIN, 14));
    	g2d.setColor(Color.GRAY);
    	g2d.drawString("W", (getP1().getX()) + 1, getP1().getY() + 15);
    	g2d.drawString("S", (getP1().getX() + 3), getP1().getY() + getP1().getHeight() - 5 );
    	g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
    	g2d.drawString("\u21E1", (getP2().getX()), getP2().getY() + 20);
    	g2d.drawString("\u21E3", (getP2().getX()), getP2().getY() + getP2().getHeight() - 5);
    	
    	//paints ball
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
