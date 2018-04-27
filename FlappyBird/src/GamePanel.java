import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	private Game match;
	private Bird flappy;
	private Tube tube1,tube2;
	private BufferedImage background;
	private Clip theme;

	public GamePanel(Game match) {
		this.match=match;
		flappy=new Bird(match);
		Timer timer=new Timer(15,this);
		tube1=new Tube(match,match.getWidth());
		tube2=new Tube(match,match.getWidth()+match.getWidth()/2);
		try {
			this.background=ImageIO.read(getClass().getResource("landscape.png"));
			AudioInputStream instream=AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("8bitmusic.wav")));
			theme=AudioSystem.getClip();
			theme.open(instream);
		} catch (Exception e) {
			
		}
		
		this.setBackground(Color.BLACK);
		this.addKeyListener(this);
		this.setFocusable(true);
		timer.start();
	}
	
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		flappy.pressed(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		flappy.released(e.getKeyCode());
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		;;
		
	}

	public void reset() {
		flappy.reset();
		tube1.reset(true);
		tube2.reset(false);
		theme.setFramePosition(0);
		repaint();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		flappy.update();
		tube1.update();
		tube2.update();
		theme.loop(Clip.LOOP_CONTINUOUSLY);
		repaint();
	}
	public Bird getBird() {
		return this.flappy;
	}
	public void stopFunnyMusic() {
		theme.stop();
	}

	public Tube getTube1() {
		return tube1;
	}
	public Tube getTube2() {
		return tube2;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,-800, -900, null);
		flappy.paint(g);
		tube1.paint(g);
		tube2.paint(g);
	

	}

}
