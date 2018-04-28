import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class Bird {
	private static final int SIZE=25;
	private Game match;
	private double y,yspeed;
	private double x;
	private double grav,lift;
	private BufferedImage img;
	private Clip hoop,death;
	private int score;
	public Bird(Game match) {
		this.match=match;
		y=match.getHeight()/2;
		x=SIZE;
		grav=0.15;
		lift=8;
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
			FloatControl gainControl=(FloatControl)hoop.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			FloatControl gainControl2=(FloatControl)death.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl2.setValue(-15.0f);
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
	  
		if(y>match.getHeight()-2*SIZE) { // ho toccato terra quindi sono morto
			match.getGamePanel().stopFunnyMusic();
			death.start(); 	
			death.setFramePosition(0);
			Object[] op= {"Riproviamo","Basta Così"};
			JOptionPane pane=new JOptionPane("Che facciamo?Ah complimentoni hai superato ben "+match.getGamePanel().getBird().getScore()+" piloni :)",JOptionPane.INFORMATION_MESSAGE);
			pane.setOptions(op);
			JDialog dialog=pane.createDialog(match.getComponent(0), "Ops Sei Morto :(");
			dialog.setVisible(true);
			if(pane.getValue()==null)
				System.exit(0);
			if(pane.getValue().equals(op[1]))
				System.exit(0);
			if(pane.getValue().equals(op[0]))
				match.getGamePanel().reset();
			
	
	
				
		}
		if(y<0) {
			y=0;
			yspeed=0;
		}
			
		if(match.getGamePanel().getTube1().getX()==this.x || match.getGamePanel().getTube2().getX()==this.x)
			score++;
		
	}
	
	public void reset() {
		this.x=SIZE;
		y=match.getHeight()/2;
		yspeed=0;
		score=0;
	
	}
	public int getScore() {
		return score;
	}
	
	public Rectangle getBounds() {
		return (new Rectangle((int)x,(int)y,SIZE,SIZE));
	}
	public void paint(Graphics g) {
		g.drawImage(img, (int)x, (int)y, SIZE, SIZE, null);
	}
	
}
