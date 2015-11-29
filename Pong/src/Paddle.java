import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


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
	
	public void update(){
		
		
		//repaint();
	}
	
	public void pressed(int code){
		if(code == up){
			y += 1; 
		} else if (code == down){
			y -= 1;
		}
	}
	
	public void released(KeyEvent e){
		
		
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
