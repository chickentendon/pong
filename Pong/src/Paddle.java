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
			System.out.println("SHIIIT");
		}
		
	}
	
	public void pressed(int code){
		
			System.out.println("if" + this.y);
			if(code == up && this.equals(GamePanel.getP1())){
				P1moveY = 1; 
			} else if (code == down && this.equals(GamePanel.getP1())){
				P1moveY = -1;
			} 
		
			else if(code == up && this.equals(GamePanel.getP2())){
				P2moveY = 1; 
			} else if (code == down && this.equals(GamePanel.getP2())){
				P2moveY = -1;
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

	private int getHeight(){
		return height;
	}
	
	private int getWidth(){
		return width;
	}
	
	private int getX(){
		return x;
	}
	
	private int getY(){
	 return y;
	}
	
	private int getUP(){
		 return up;
	}
	
	private int getDOWN(){
		 return down;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

}
