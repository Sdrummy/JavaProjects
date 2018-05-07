package BulletHell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
/*
 * la classe fire è una utilities che serve a contenere un array di bullets tutti con la stessa immagine
 */
public class Fire { 
	protected List<BulletEntity> bullets;
	protected JFrame frame;
	
	public Fire(JFrame frame) {
		this.frame=frame;
		bullets=new ArrayList<BulletEntity>();
	}
	
	public void addBullet(String spriteUrl,double x,double y,double xspeed,double yspeed) {
		BulletEntity b=new BulletEntity(spriteUrl,x,y,frame);
		
		b.setXspeed(xspeed);
		b.setYspeed(yspeed);
		bullets.add(b);
	}
	public void update() {
		for(int i=0;i<bullets.size();i++) {
			bullets.get(i).update();
			 if(bullets.get(i).y<0 || bullets.get(i).y>frame.getHeight())
				bullets.remove(i);
		}
		
		
	
	}
	
	public void entityCollision(GameEntity entity) {
		for(int i=0;i<bullets.size();i++) {
			if(bullets.get(i).collidesWith(entity)) {
				bullets.remove(i);
				if(entity instanceof EnemyEntity) 
				
					((EnemyEntity) entity).hit();
				if(entity instanceof PlayerShipEntity)
					((PlayerShipEntity) entity).hit();
					
			}
		}
	}
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		for(BulletEntity b:bullets)
			b.paint(g2d);
		
	}
	
}
