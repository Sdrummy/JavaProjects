package pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Field extends JPanel implements ActionListener, KeyListener{
	private static final long serialVersionUID = 1L;
	private Player p1,p2;
	private Puck p;
	private Game match;
	public Field(Game match) {
		setBackground(Color.BLACK);
		this.match=match;
		p=new Puck(match);
		p1=new Player(match,4,87,83);
		p2=new Player(match,680,38,40);
		JPanel scorePanel=new JPanel(new BorderLayout());
		JLabel scoreP1=p1.getScoreLabel();
		JLabel scoreP2=p2.getScoreLabel();
		scorePanel.add(scoreP1,BorderLayout.EAST);
		scorePanel.add(scoreP2,BorderLayout.WEST);
		
		this.add(scorePanel);
		
		Timer timer=new Timer(10,this);
		timer.start();
		addKeyListener(this);
	    setFocusable(true);
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		p1.paint(g);
		p2.paint(g);
		p.paint(g);
		
	}

	public void update() {
		p.update();
		p1.update();
		p2.update();
		if(p1.checkScore()) {
			JOptionPane.showMessageDialog(this.getParent().getComponent(0), "Vince il giocatore di Destra!" );
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
			
		}
		if(p2.checkScore()) {
			JOptionPane.showMessageDialog(this.getParent().getComponent(0), "Vince il giocatore di Sinistra!");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
			
		}
		
		
	}
	
	
	public Player getPlayer1() {
		return p1;
	}


	public Player getPlayer2() {
		return p2;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		p1.pressed(e.getKeyCode());
		p2.pressed(e.getKeyCode());
	}


	@Override
	public void keyReleased(KeyEvent e) {
		p1.relesed(e.getKeyCode());
		p2.relesed(e.getKeyCode());
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
	}
	
	
	

}
