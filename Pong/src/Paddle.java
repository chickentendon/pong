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
	private int space = 32;
	private int P1moveY = 0;
	private int P2moveY = 0;
	public static int swapper = 1;

	public Paddle(int x, int up, int down){
		this.x = x;
		this.up = up;
		this.down = down;
		y = (Pong.HEIGHT / 2) - 40;
		System.out.println(this.x + " " + this. y);
	}
	
	public void update(){
		
		if(this.equals(GamePanel.getP1())){
			y = y + P1moveY;
		} 
		else if(this.equals(GamePanel.getP2())){
			y = y + P2moveY;
		} 
		else {
		}checkPaddleBounds();
	}
	
	public void pressed(int code){
		
		if(code == space && swapper == 1){
			swapper = 2;
			GamePanel.timer.stop();
		} else if (code == space && swapper == 2){
			swapper = 1;
			GamePanel.timer.start();
		}
		
		if (code == up && this.equals(GamePanel.getP1())){
			System.out.println("P1 down");
			if (this.y + this.height >= Pong.getPanel().getHeight() - 20)
			{
				P1moveY = 0;
			} else {
				P1moveY = 1; 
			}
				
		} else if (code == down && this.equals(GamePanel.getP1())){
			if  (this.y <= 20) {
				P1moveY = 0;
			}
			else {
				P1moveY = -1;
			}
		} 
	
		else if(code == up && this.equals(GamePanel.getP2())){
			System.out.println("P1 down");
			if (this.y + this.height >= Pong.getPanel().getHeight() - 20)
			{
				P2moveY = 0;
			} else {
				P2moveY = 1; 
			}
		
		} else if (code == down && this.equals(GamePanel.getP2())){
			
			if  (this.y <= 20) {
				P2moveY = 0;
			}
			else {
				P2moveY = -1;
			}
		}
	}
	
	public void released(int code){
		if((code == up || code == down)  && this.equals(GamePanel.getP1())){
			P1moveY = 0; 
		} else if ((code ==up || code == down) && this.equals(GamePanel.getP2())){
			P2moveY = 0;
		} 		
	}
	
	public void checkPaddleBounds()
	{
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
