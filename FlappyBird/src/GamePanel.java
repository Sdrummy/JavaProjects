import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	private Game match;
	private Bird flappy;
	private Tube tube1,tube2;
	private BufferedImage background;
	
/*
 * implementare un modo per bloccare il gioco quando il giocatore colpisce un pilone con una specie di menù che riporta il risultato ottenuto
 * e la possibilità di scegliere se ritentare oppure uscire 
 * 
 */
	public GamePanel(Game match) {
		this.match=match;
		flappy=new Bird(match);
		Timer timer=new Timer(10,this);
		tube1=new Tube(match,match.getWidth());
		tube2=new Tube(match,match.getWidth()+match.getWidth()/2);
		try {
			this.background=ImageIO.read(getClass().getResource("landscape.png"));
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		flappy.update();
		tube1.update();
		tube2.update();

		repaint();
	}
	public Bird getBird() {
		return this.flappy;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,-800, -900, null);
		flappy.paint(g);
		tube1.paint(g);
		tube2.paint(g);


	}

}
