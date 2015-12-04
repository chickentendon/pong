import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Paddle {
	
	private int x;
	private int y;
	private int width = 15;
	private int height = 110;
	private int up;
	private int down;
	private int P1moveY = 0;
	private int P2moveY = 0;




	
	public Paddle(int x, int up, int down){
		this.x = x;
		this.up = up;
		this.down = down;
		y = (Pong.HEIGHT / 2) - 40;
		System.out.println(y);
	}
	
	public void update(){
		if(this.equals(GamePanel.getP1()) /*&& this.y > 0 && (this.y + this.height) < Pong.HEIGHT*/){
			y = y + P1moveY;
		} 
		else if(this.equals(GamePanel.getP2()) /*&& y < Pong.HEIGHT - height && y > 0*/){
			y = y + P2moveY;
		} 
		else {
			//System.out.println("SHIIIT");
		}
		
	}
	
	public void pressed(int code){
		
			//System.out.println("if // y= " + this.y);
			if(code == up && this.equals(GamePanel.getP1())){
				if (this.y + this.height > Pong.HEIGHT - 20)
				{
					P1moveY = 0;
				} else {
					P1moveY = 1; 
				}
				
			} else if (code == down && this.equals(GamePanel.getP1())){
				if  (this.y < 20) {
					P1moveY = 0;
				}
				else {
					P1moveY = -1;
				}
			} 
		
			else if(code == up && this.equals(GamePanel.getP2())){
				//System.out.println("P2 up -> y + height = " + (this.y + this.height));
				if (this.y + this.height > 450)
				{
					P2moveY = 0;
				} else {
					P2moveY = 1; 
				}
			
			} else if (code == down && this.equals(GamePanel.getP2())){
				//System.out.println("P2 down -> y= " + this.y);
				if  (this.y < 20) {
					P2moveY = 0;
				}
				else {
					P2moveY = -1;
				}
				
			}
//		} else {
//			System.out.println("else " + this.y + " HEIGHT " + Pong.HEIGHT);
//			P1moveY = 0;
//			P2moveY = 0;
	
	}
	
	public void released(int code){
		if((code == up || code == down)  && this.equals(GamePanel.getP1())){
			P1moveY = 0; 
		} else if ((code ==up || code == down) && this.equals(GamePanel.getP2())){
			P2moveY = 0;
		} 		
	}
	
//	private boolean checkInBounds(){
//		if(){
//			
//			return true;
//		}
//		
//		return false;
//	}

	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
	 return y;
	}
	
	public int getUP(){
		 return up;
	}
	
	public int getDOWN(){
		 return down;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

}
