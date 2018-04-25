package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Puck {
	private static final int H=25,W=25;
	private int x,xspeed=4,y,yspeed=4;
	private Game match;
	private Clip hitSound;
	private boolean soundOn;
	public Puck(Game match) {
		this.match=match;
		this.x=match.getWidth() / 2;
		this.y=match.getHeight() / 2;
		soundOn=true;
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(
					new BufferedInputStream(
							new FileInputStream("clunk.wav")
							));
			hitSound = AudioSystem.getClip();
			hitSound.open(inputStream);
		} catch (Exception e) {
			soundOn=false;
			
		} 
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
		if (match.getF().getPlayer1().getBounds().intersects(getBounds()) || match.getF().getPlayer2().getBounds().intersects(getBounds())) {
			xspeed=-xspeed;
			if(soundOn) {
				hitSound.start();
				hitSound.setFramePosition(0);
			}
		}
			
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,W,H);
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, W, H);
		
	}
}
