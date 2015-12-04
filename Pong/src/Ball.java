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
		checkTopBottom();
		checkPaddles();
		//checkLeftRight();
	}
	
	
	public void checkTopBottom(){
		if (y > 485) {  //Check Bottom
			System.out.println("hey");
			yi = -1;
		}
		if (y <= 0) { // CHeck Top
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
			System.out.println("INTERSECTION");
			xi = -1;
		}
		if (p1Bounds.intersects(ballBounds)) {
			System.out.println("INTERSECTION");
			xi = 1;
		}
		
	}
	
	public void checkLeftRight(){
		if(x < GamePanel.WIDTH){
			resetBall();			
		}
		if(x < 5){
			resetBall();
			
		}
	}
	
	private void resetBall() {
		x = (Pong.WIDTH / 2);
		y = (Pong.HEIGHT / 2);
	}

	
	
	
	 private double invertDouble(double i){
		 i *= -1;
		 return i;
	 }
	
//	public void checkLeftRight(){
//		if(hit left or right){
//			add point to player
//			reset ball to middle
//		} else {
//			continue on path
//		}
//		
//	}
//	
//	public boolean checkPaddle(){
//		if(we hit the paddle){
//			change path
//		} else {
//			continue on path
//		}
//	}
//	
//	public void fireBall(){
//		
//	}
	
	public void paint(Graphics g){		
		g.setColor(Color.RED);
		g.fillRect((int)this.x,  (int)this.y, width, width);
	}

}
