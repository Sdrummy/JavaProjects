package BulletHell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class EnemyEntity extends GameEntity implements ActionListener{
	private Timer timer;
	private boolean isDead;
	private int hitsTaken;

	public EnemyEntity(String bufferedImageUrl, double x, double y,JFrame frame) {
		super(bufferedImageUrl, x, y,frame);
	
		hitsTaken=0;
		timer=new Timer(700,this);
		isDead=false;
		timer.start();
	}
	
	@Override
	public void update() {
		super.update();
		if(this.y>0.5*frame.getHeight())
			y-=yspeed;
		
	}
/*
 * TODO:implementare un diverso modo per decidere se sparare o meno
 */
	public boolean canFire() {
		boolean canfire;
		Random ran=new Random();
		switch(ran.nextInt(70)) {
		case 1:
			canfire=true;
			break;
		default:
			canfire=false;
		}
		return canfire;
	}
	public void move() {
		Random ran=new Random();
		
		switch(ran.nextInt(8)) {
		case 0:
			this.xspeed=0;
			this.yspeed=2;
			break;
		case 1:
			this.xspeed=0;
			this.yspeed=-2;
			break;
		case 2:
			this.xspeed=2;
			this.yspeed=0;
			break;
		case 3:
			this.xspeed=2;
			this.yspeed=2;
			break;
		case 4:
			this.xspeed=2;
			this.yspeed=-2;
			break;
		case 5:
			this.xspeed=-2;
			this.yspeed=2;
			break;
		case 6:
			this.xspeed=1;
			this.yspeed=-2;
			break;
		
		case 7:
			this.xspeed=-2;
			this.yspeed=1;
			break;
	
	
		}
		
		
	}
	
	
	
	public boolean isDead() {
		return this.isDead;
	}
	public void hit() {
		this.hitsTaken++;
		if(this.hitsTaken>=5)
			this.isDead=true;
		
	}
	public void actionPerformed(ActionEvent e) {
		move();
		
	}
	public void paint(Graphics g) {
		
			Graphics2D g2d=(Graphics2D)g;
			g2d.drawImage(this.sprite, (int)this.x, (int)this.y, this.sprite.getWidth(), this.sprite.getHeight(), null);
		
	}
		
}
