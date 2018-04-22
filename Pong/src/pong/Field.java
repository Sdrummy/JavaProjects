package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Field extends JPanel implements ActionListener, KeyListener{
	private Player p1,p2;
	private Puck p;
	private Game match;
	
	public Field(Game match) {
		setBackground(Color.BLACK);
		this.match=match;
		p=new Puck(match);
		p1=new Player(match,5,38,40);
		p2=new Player(match,670,87,83);
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
