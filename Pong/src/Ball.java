import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
	
	private static double x;
	private static double y;
	private int width = 15;
	double xi = 1; 
	double yi = 1;
	
	//Constructor for the ball, sets initial position -> (x,y) centered
	public Ball() {
		x = ((Pong.WIDTH - 13) / 2);
		y = ((Pong.HEIGHT) / 2);
	}
	
	//Moves the ball and checks all the bounds
	public void update() {	
		x += xi;
		y += yi;		
		checkSurfaces();
	}
	
	//Checks collisions and scores
	private void checkSurfaces() {
		checkTopBottom();
		checkPaddles();
		checkForScore();
	}	
	
	//Checks the top and bottom to see if the ball is in bounds
	public void checkTopBottom() {
		if (y >= Pong.getPanel().getHeight() - width) {  //Check Bottom
			yi = -1;
		}
		if (y <= 0) { // Check Top
			yi = 1;
		}
	}
	
	public void checkPaddles() {
		//Storing two Rectangles to check the paddle bounds
		Rectangle p1Bounds = new Rectangle(GamePanel.getP1().getX(),GamePanel.getP1().getY(), 
											GamePanel.getP1().getWidth(), GamePanel.getP1().getHeight());	

		Rectangle p2Bounds = new Rectangle(GamePanel.getP2().getX(),GamePanel.getP2().getY(), 
											GamePanel.getP2().getWidth(), GamePanel.getP2().getHeight());
		
		//Stores the bounds to the ball to compare it to the paddles
		Rectangle ballBounds = new Rectangle((int)GamePanel.getBall().x, (int)GamePanel.getBall().y, 
													GamePanel.getBall().width, GamePanel.getBall().width);
		
		if (p2Bounds.intersects(ballBounds)) { //Checking Paddle 2, if it is in contact swap directions
			xi = -1;
		}
		//Checking Paddle 1, if it is in contact swap directions
		if (p1Bounds.intersects(ballBounds)) {
			xi = 1;
		}
	}

	//Checking to see if there was a score, and if so reset the ball and increase the proper score	
	public void checkForScore() {
		if(GamePanel.getBall().x > 700) {
			GamePanel.increaseScore(1);
			resetBall();
		}
		else if(GamePanel.getBall().x < 5) {
			GamePanel.increaseScore(2);
			resetBall();
		}
	}
	
	//Private helper to reset the ball back to the center
	public static void resetBall() {
		x = ((Pong.getPanel().getWidth() / 2) - 7);
		y = ((Pong.getPanel().getHeight() / 2));
		Paddle.resetPaddles();
		Paddle.swapper = 1;
		GamePanel.timer.stop();
	}
	
	//Paints the ball as it moves
	public void paint(Graphics g) {		
		g.setColor(Color.RED);
		g.fillRect((int)this.x,  (int)this.y, width, width);
	}
}
