import java.awt.Color;
import java.awt.Graphics;

public class Bird {
	private static final int SIZE=20;
	private Game match;
	private double y,yspeed;
	private double x;
	private double grav,lift;
	public Bird(Game match) {
		this.match=match;
		y=match.getHeight()/2;
		x=SIZE;
		yspeed=0;
		grav=0.1;
		lift=7;
	}
	
	
	public void pressed(int keyCode) {
		if(keyCode==' ') {
			yspeed-=lift;
			y+=yspeed;
		}
	}
	public void released(int keyCode) {
		if(keyCode==' ') 
			yspeed=0;
		
	}
	public void update() {
		yspeed+=grav;
		yspeed*=0.98;
		y+=yspeed;
		if(y>match.getHeight()-3*SIZE) {
			y=match.getHeight()-3*SIZE;
			yspeed=0;
		}
		if(y<0) {
			y=0;
			yspeed=0;
		}
		
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)x,(int) y, SIZE, SIZE);
		
	}
	
}
