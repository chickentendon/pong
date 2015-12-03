import java.awt.Color;
import java.awt.Graphics;



public class Ball {
	private double x;
	private double y;
	private int width = 15;
	
	public Ball(){
		this.x = x;
		this.y = y;
		
		x = (Pong.WIDTH / 2);
		y = (Pong.HEIGHT / 2);
	}
	
	public void update(){
		double xi = 0.2; 
		double yi = 0.2;
		x = x + xi;
		y = y + yi;		
	}
	
//	public void checkTopBottom(){
//		
//	}
//	
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
	
//	public void fireBall(){
//		
//	}
	
	public void paint(Graphics g){		
		g.setColor(Color.RED);
		g.fillRect((int)this.x,  (int)this.y, width, width);
	}

}
