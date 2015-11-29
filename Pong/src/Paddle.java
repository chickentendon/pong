import java.awt.Color;
import java.awt.Graphics;


public class Paddle {
	
	private int x;
	private int y;
	private int width = 15;
	private int height = 80;
	private int up;
	private int down;


	
	public Paddle(int x, int up, int down){
		this.x = x;
		this.up = up;
		this.down = down;
		y = (Pong.HEIGHT / 2) - 40;
		System.out.println(y);
	}
	
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
