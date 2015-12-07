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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class GamePanel extends JPanel implements KeyListener {
    private Pong game;
    static private Ball ball;
    static public Paddle player1, player2;
    public static Timer timer;
    static public int P1Score = 0;
    static public int P2Score = 0;
	Font ttfBase = null;
	Font ttfReal = null;

    
    /**
     * Makes GamePanel
     * @param game
     */
    public GamePanel(Pong game) {
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setDoubleBuffered(true);
        try {
    		InputStream myStream = new BufferedInputStream(new FileInputStream("Roboto-Thin.ttf"));
    		ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
    		ttfReal = ttfBase.deriveFont(Font.PLAIN, 350);
    	} catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Roboto not loaded.");
        }
        //System.setProperty("sun.java2d.d3d", "True");
        this.game = game;
        ball = new Ball();
        player1 = new Paddle(20, 83, 87);
        player2 = new Paddle(Pong.WIDTH - 40, 40, 38);
    	
        timer = new Timer(2, new ActionListener() {
        	public void actionPerformed(ActionEvent e){
            	update();
            	repaint();
            	Toolkit.getDefaultToolkit().sync();
            }
        });//Speed Altering on Timer
        
        //timer.start();
        
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
    	System.out.println("keypressed");
    	player1.pressed(e.getKeyCode());
    	player2.pressed(e.getKeyCode());
    }
    
    public void keyReleased(KeyEvent e){
    	player1.released(e.getKeyCode());
    	player2.released(e.getKeyCode());
    }
    
    static public void update(){
    	//System.out.println("GamePanel.update()");
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
    	Graphics2D g2d = (Graphics2D)g;
    	g2d.setRenderingHint(
    	        RenderingHints.KEY_TEXT_ANTIALIASING,
    	        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    	super.paintComponent(g2d);
    	g2d.setColor(Color.GRAY);
    	g2d.setFont(ttfReal);
    	g2d.drawString(P1Score + "", 75 , 350);
    	g2d.drawString(P2Score + "", 435, 350);
    	g2d.setColor(Color.WHITE);
    	g2d.drawLine(Pong.getPanel().getWidth()/2,20,Pong.getPanel().getWidth()/2,Pong.getPanel().getHeight()-20);
    	player1.paint(g2d);
    	player2.paint(g2d);
    	ball.paint(g2d);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		//Unused
	}

	 public static void setGPSize(int w,int h) {
		 Pong.getPanel().setPreferredSize(new Dimension(w, h));
    }  
}