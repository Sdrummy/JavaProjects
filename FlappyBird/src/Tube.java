import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Tube {
	private static final int W=30;
	private double x,xspeed;
	private double top=0,bottom=0;
	private Game match;
	public Tube(Game match) {
		this.match=match;
		x=match.getWidth()-W;
		xspeed=3;
		Random ran=new Random();
		
		top=ran.nextDouble()*match.getHeight()/2;
		bottom=ran.nextDouble()*match.getHeight()/2;
		
	}
	
	public void update() {
		x-=xspeed;
	}
	public double getPos() {
		return x;
	}
	public boolean outScreen() {
		return x<-W;
	}
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x,0 , W, (int)top);
		g.fillRect((int)x, match.getHeight()-(int)bottom+1, W, (int)bottom);
	}
}
