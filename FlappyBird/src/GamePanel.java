import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	private Game match;
	private Bird flappy;
	private ArrayList<Tube> tubes;
	
	//private Tube tube;
	public GamePanel(Game match) {
		this.match=match;
		flappy=new Bird(match);
		Timer timer=new Timer(10,this);
		//tube=new Tube(match);
		tubes=new ArrayList<Tube>();
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
		if(arg0.getWhen()%30==0)
			tubes.add(new Tube(match));
		for(int i=0;i<tubes.size();i++) {
			tubes.get(i).update();
			if(tubes.get(i).outScreen())
				tubes.remove(i);
			System.out.println(tubes.size());
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		flappy.paint(g);
		for(int i=0;i<tubes.size();i++)
			tubes.get(i).paint(g);
	}

}
