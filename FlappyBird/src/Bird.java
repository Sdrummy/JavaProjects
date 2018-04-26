import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Bird {
	private static final int SIZE=20;
	private Game match;
	private double y,yspeed;
	private double x;
	private double grav,lift;
	private BufferedImage img;
	private Clip hoop,death;
	public Bird(Game match) {
		this.match=match;
		y=match.getHeight()/2;
		x=SIZE;
		yspeed=0;
		grav=0.1;
		lift=7;
		try {
			img=ImageIO.read(getClass().getResource("cippy.png"));
		} catch (Exception e) {
		}
		
		try {
			AudioInputStream instream=AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("funnysound.wav")));
			AudioInputStream ins=AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("morte.wav")));
			hoop=AudioSystem.getClip();
			hoop.open(instream);
			death=AudioSystem.getClip();
			death.open(ins);
		} catch (Exception e) {
		} 
		
	}
	
	public void pressed(int keyCode) {
		if(keyCode==' ') {
			yspeed-=lift;
			y+=yspeed;
			hoop.start();
			hoop.setFramePosition(0);
		}
	}
	public void released(int keyCode) {
		if(keyCode==' ') 
			yspeed=0;
		
	}
	public void update()  {
		yspeed+=grav;
		yspeed*=0.98;
		y+=yspeed;
		if(y>match.getHeight()-2.25*SIZE) { // ho toccato terra quindi sono morto
			death.start(); 			//prima bisogna settare il menù di partita terminata
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				
			}
			System.exit(0);
			
			
			//JOptionPane.showMessageDialog(match.getComponent(0), "ops è morto l'uccellino");
			/*y=match.getHeight()-2.5*SIZE;
			yspeed=0;*/
		}
		if(y<0) {
			y=0;
			yspeed=0;
		}
			
	}
	
	public double getXPos() {
		return this.x;
	}
	
	public Rectangle getBounds() {
		return (new Rectangle((int)x,(int)y,SIZE,SIZE));
	}
	public void paint(Graphics g) {
		g.drawImage(img, (int)x, (int)y, SIZE, SIZE, null);
	}
	
}
