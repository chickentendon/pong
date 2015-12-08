import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	
	private int x;
	private int y;
	private int width = 15;
	private int height = 110;
	private int up;
	private int down;
	private int space = 32;
	private int n = 78;
	private int P1moveY = 0;
	private int P2moveY = 0;
	public static int swapper = 1;
	public static int initPaddlePos = (Pong.HEIGHT / 2) - 40;

	//Constructor for paddle
	public Paddle(int x, int up, int down) {
		this.x = x;
		this.up = up;
		this.down = down;
		y = initPaddlePos;
	}
	
	//Update function for paddles
	public void update() {
		if(this.equals(GamePanel.getP1())) {
			y = y + P1moveY;
		} 
		else if(this.equals(GamePanel.getP2())) {
			y = y + P2moveY;
		} 
		else { //Hanging else to catch anomalies
		}
		checkPaddleBounds();
	}
	
	//Handles KeyEvents
	public void pressed(int code) {
		
		if (code == n) { //Handles [n] key which starts a new game
			GamePanel.p1Score = 0;
			GamePanel.p2Score = 0;
			Ball.resetBall();
			Pong.getPanel().repaint();
		}
		
		if (code == space && swapper == 1) { //Handles [space bar] game pausing
			System.out.println(swapper + " = 1");
			swapper = 2;
			GamePanel.timer.stop();
		} else if (code == space && swapper == 2) {
			System.out.println(swapper + " should be 2");
			swapper = 1;
			GamePanel.timer.start();
		}
		
		if (code == up && this.equals(GamePanel.getP1())) { //Player 1 paddle moving down
			if (this.y + this.height >= Pong.getPanel().getHeight() - 20) //Stop paddle if near bottom edge
			{															
				P1moveY = 0;
			} else {
				P1moveY = 1; 
			}
				
		} else if (code == down && this.equals(GamePanel.getP1())) { //Player 1 paddle moving up
			if  (this.y <= 20) {	// Stop if paddle is near top edge
				P1moveY = 0;
			}
			else {
				P1moveY = -1;
			}
		} 
	
		else if (code == up && this.equals(GamePanel.getP2())) { //Player 2 moving down
			if (this.y + this.height >= Pong.getPanel().getHeight() - 20) //Stop paddle if near bottom edge
			{
				P2moveY = 0;
			} else {
				P2moveY = 1; 
			}
		
		} else if (code == down && this.equals(GamePanel.getP2())) { //Player 2 moving up
			if  (this.y <= 20) {   //Stop paddle if near bottom edge
				P2moveY = 0;
			}
			else {
				P2moveY = -1;
			}
		}
	}
	
	//Handles key release, stopping movement of paddle
	public void released(int code) {
		if ((code == up || code == down)  && this.equals(GamePanel.getP1())) {
			P1moveY = 0; 
		} else if ((code ==up || code == down) && this.equals(GamePanel.getP2())) {
			P2moveY = 0;
		} 		
	}
	
	//Function to check if paddles are near top or bottom
	public void checkPaddleBounds() {
		if (GamePanel.getP1().y + GamePanel.getP1().height >= Pong.getPanel().getHeight() - 20)
		{
			P1moveY = 0;
		}
		else if  (GamePanel.getP1().y <= 20) {
			P1moveY = 0;
		} 
		
		else if (GamePanel.getP2().y + GamePanel.getP2().height >= Pong.getPanel().getHeight() - 20)
		{
			P2moveY = 0;
		}
		else if  (GamePanel.getP2().y <= 20) {
			P2moveY = 0;
		}
		
	}
	
	//Resets paddles to initial position on new game or point scored
	public static void resetPaddles() {
		GamePanel.getP1().y = initPaddlePos;
		GamePanel.getP2().y = initPaddlePos; 
		
	}
	
	//Accessor for height of paddle
	public int getHeight() {
		return height;
	}
	
	//Accessor for width of paddle
	public int getWidth() {
		return width;
	}
	
	//Accessor for X coordinate of paddle
	public int getX() {
		return x;
	}
	
	//Accessor for Y coordinate of paddle
	public int getY() {
	 return y;
	}
	
	//Paint paddles
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		
	}
}
