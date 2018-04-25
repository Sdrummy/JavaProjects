package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Puck {
	private static final int H=25,W=25;
	private int x,xspeed=3,y,yspeed=3;
	private Game match;
	
	public Puck(Game match) {
		this.match=match;
		this.x=match.getWidth() / 2;
		this.y=match.getHeight() / 2;
	}
	
	public void update() {
		x+=xspeed;
		y+=yspeed;
		if(x<0) {
			xspeed=-xspeed;
			x=match.getWidth() /2;
			match.getF().getPlayer1().pointScored();
		}
		
		if(x>match.getWidth()-W-7) {
			xspeed=-xspeed;
			x=match.getWidth() /2;
			match.getF().getPlayer2().pointScored();
		}
		
		if(y<0 || y>match.getHeight()-H-29) 
			yspeed=-yspeed;
		checkCollision();
	}
	
	
	public void checkCollision() {
		if (match.getF().getPlayer1().getBounds().intersects(getBounds()) || match.getF().getPlayer2().getBounds().intersects(getBounds()))
			xspeed=-xspeed;
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,W,H);
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, W, H);
		
	}
}
