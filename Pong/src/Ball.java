import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



public class Ball {
	private double x;
	private double y;
	private int width = 15;
	double xi = 1; 
	double yi = 1;
	
	public Ball(){
		this.x = x;
		this.y = y;
		x = (Pong.WIDTH / 2);
		y = (Pong.HEIGHT / 2);
	}
	
	public void update() {	
		x += xi;
		y += yi;		
		
		checkSurfaces();	
	}
	
	private void checkSurfaces(){
		//System.out.println("Bottom of ball: " + (GamePanel.getBall().y + 15));
		checkTopBottom();
		checkPaddles();
		checkForScore();
	}
	
	public void checkTopBottom(){
		if (y >= Pong.getPanel().getHeight() - width) {  //Check Bottom
			yi = -1;
		}
		if (y <= 0) { // Check Top
			yi = 1;
		}
	}
	
	public void checkPaddles(){
		Rectangle p1Bounds = new Rectangle(GamePanel.getP1().getX(),GamePanel.getP1().getY(), 
				GamePanel.getP1().getWidth(), GamePanel.getP1().getHeight());	

		Rectangle p2Bounds = new Rectangle(GamePanel.getP2().getX(),GamePanel.getP2().getY(), 
				GamePanel.getP2().getWidth(), GamePanel.getP2().getHeight());
		
		Rectangle ballBounds = new Rectangle((int)GamePanel.getBall().x, (int) GamePanel.getBall().y, GamePanel.getBall().width, GamePanel.getBall().width);
		
		if (p2Bounds.intersects(ballBounds)) {
			xi = -1;
		}
		if (p1Bounds.intersects(ballBounds)) {
			xi = 1;
		}
		
	}
	
	public void checkForScore(){
		if(GamePanel.getBall().x > 700){
			GamePanel.increaseScore(1);
			System.out.println("Player 1 : " + GamePanel.P1Score + "\nPlayer 2 : " + GamePanel.P2Score +"\n");
			resetBall();
		}
		else if(GamePanel.getBall().x < 5){
			GamePanel.increaseScore(2);
			System.out.println("Player 1 : " + GamePanel.P1Score + "\nPlayer 2 : " + GamePanel.P2Score + "\n");
			resetBall();
		}
		else {
			//System.out.println("No Score");
		}
	}
	
	private void resetBall() {
		x = ((Pong.WIDTH / 2) - 7);
		y = ((Pong.HEIGHT / 2) - 7);
		
		GamePanel.timer.stop();
	}
	
	public void paint(Graphics g){		
		g.setColor(Color.RED);
		g.fillRect((int)this.x,  (int)this.y, width, width);
	}


}
