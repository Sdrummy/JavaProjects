package BulletHell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;


public class PlayerShipEntity extends GameEntity{
	private boolean isDead;
	private int hitsTaken;
	public PlayerShipEntity(String bufferedImageUrl, double x, double y,JFrame frame) {
		super(bufferedImageUrl, x, y,frame);
		isDead=false;
		hitsTaken=0;
	}
	public void pressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_W:
			this.xspeed=0;
			this.yspeed=-2;
			break;
		case KeyEvent.VK_S:
			this.xspeed=0;
			this.yspeed=2;
			break;
		case KeyEvent.VK_D:
			this.xspeed=2;
			this.yspeed=0;
			break;
		case KeyEvent.VK_A:
			this.xspeed=-2;
			this.yspeed=0;
			break;
		}
		
	}
	
	public void released(int keyCode) {
		if(keyCode==KeyEvent.VK_W ||keyCode==KeyEvent.VK_A ||keyCode==KeyEvent.VK_D ||keyCode==KeyEvent.VK_S) {
			this.xspeed=0;
			this.yspeed=0;
		}
	}
	public void hit() {
		hitsTaken++;
		if(hitsTaken>=3) {
			isDead=true;
		}
	}
	public boolean playerDead() {
		return this.isDead;
	}
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(this.sprite, (int)x, (int)y, sprite.getWidth(),sprite.getHeight(), null);
		
	}
}
