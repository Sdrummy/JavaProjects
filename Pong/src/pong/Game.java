package pong;


import javax.swing.*;

public class Game extends JFrame{
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_HEIGHT=450,WINDOW_WIDTH=700;
	private Field f;
	
	public Game() {
		super("Pong");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		f=new Field(this);
		add(f);
		this.setVisible(true);
		this.setResizable(false);
	}
	


	public Field getF() {
		return f;
	}

	
	
	
}
