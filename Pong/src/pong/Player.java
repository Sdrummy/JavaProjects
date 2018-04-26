package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class Player{
	private static final int PH=60 ,PW=10 ;
	private Game match;
	private int y,yspeed,x,up,down;
	private int score=0;
	private JLabel scr;
	public void setScore(int score) {
		this.score = score;
	}

	public Player(Game match,int x,int up,int down) {
		this.match=match;
		this.y=match.getHeight() / 2;
		this.x=x;
		this.up=up;
		this.down=down;
		scr=new JLabel("Score: 0");
		
	}
	
	public Integer getScore() {
		return score;
	}

	public void pointScored() {
		score++;
		scr.setText("Score: "+score);
		
	}
	public boolean checkScore() {
		if(score>=10)
			return true;
		else
			return false;
	}
	public void update() {
		if(y>=0 && y<=match.getHeight()-PH-29)
			y+=yspeed;
		if(y<0 || y>match.getHeight()-PH-29 )
			y-=yspeed;
	
		
	}
	
	public void pressed(int keyCode) {
		if(keyCode == up)
			yspeed=-4;
		else if(keyCode==down)
			yspeed=4;
		
	}
	
	public void relesed(int keyCode) {
		if(keyCode==up || keyCode==down)
			yspeed=0;
	}
	public JLabel getScoreLabel() {
		return scr;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, PW, PH);
	}
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, PW, PH);
	}
}
