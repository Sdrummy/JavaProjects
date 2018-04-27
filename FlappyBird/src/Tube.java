import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class Tube {
	private static final int W=50;
	private double x,xspeed;
	private double top,bottom;
	private Game match;
	private static final int GAP=80;
	private BufferedImage imgBottom,imgTop;
	private Clip death;

	public Tube(Game match,double pos) {
		this.match=match;
		x=pos;

		xspeed=4;
		Random ran=new Random();
		
		top=ran.nextDouble()*(match.getHeight()/2);
		bottom=match.getHeight()-GAP-top;
		try {
			imgBottom=ImageIO.read(getClass().getResource("super-mario-brothers-147465_960_720.png"));
			imgTop=ImageIO.read(getClass().getResource("pilonRuotato.png"));
			AudioInputStream ins=AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("morte.wav")));
			death=AudioSystem.getClip();
			death.open(ins);
		} catch (Exception e) {

		}
	}
	
	public void update() {
		x-=xspeed;
		if(x<-W) { //riposiziono il pilone e ne cambio la dimensione
			x=match.getWidth()-W;
			Random ran=new Random();
			do {
			top=ran.nextDouble()*(match.getHeight()/2);
			
			}while(top<=10);
			bottom=match.getHeight()-GAP-top; 
			
		}
		
		if(checkCollision()) {
			match.getGamePanel().stopFunnyMusic();
			death.start(); 			
			death.setFramePosition(0);
			Object[] op= {"Riproviamo","Basta Così"};
			JOptionPane pane=new JOptionPane("ops purtroppo sei morto :(((( che facciamo ora?",JOptionPane.INFORMATION_MESSAGE);
			
			pane.setOptions(op);
			JDialog dialog=pane.createDialog(match.getComponent(0), "Partita Conclusa");
			dialog.setVisible(true);
			if(pane.getValue()==null)
				System.exit(0);
			if(pane.getValue().equals(op[1]))
				System.exit(0);
			if(pane.getValue().equals(op[0]))
				match.getGamePanel().reset();
				
		}
		

	}
	
	public void reset(boolean primo) {
		if(primo)
			this.x=match.getWidth();
		else
			this.x=1.5*match.getWidth();
		
	}
	
	public boolean checkCollision() {
		if(match.getGamePanel().getBird().getBounds().intersects(getBottomBounds()) || match.getGamePanel().getBird().getBounds().intersects(getTopBounds()))
			return true;
		else
			return false;
	}
	public Rectangle getBottomBounds() {
		return (new Rectangle((int)x,(int)(match.getHeight()-bottom),W,(int)bottom));
	}
	
	public Rectangle getTopBounds() {
		return (new Rectangle((int)x,0,W,(int)top));
	}
	
	public void paint(Graphics g) {
		g.drawImage(imgTop, (int)x, 0 , W, (int)top, null);
		g.drawImage(imgBottom, (int)x, match.getHeight()-(int)bottom, W, (int)bottom,null);
		
	}
}
