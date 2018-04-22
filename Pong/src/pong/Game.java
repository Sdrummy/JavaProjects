package pong;
import java.awt.Color;

import javax.swing.*;

public class Game extends JFrame{
	private static final int WINDOW_HEIGHT=450,WINDOW_WIDTH=700;
	private Field f;
	
	public Game() {
		super("Pong");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		f=new Field(this);
		add(f);
		this.setVisible(true);
	}
	


	public Field getF() {
		return f;
	}

	
	
	
}
