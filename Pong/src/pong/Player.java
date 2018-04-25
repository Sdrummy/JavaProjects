package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player{
	private static final int PH=60 ,PW=10 ;
	private Game match;
	private int y,yspeed,x,up,down;
	private int score=0;
	public void setScore(int score) {
		this.score = score;
	}

	public Player(Game match,int x,int up,int down) {
		this.match=match;
		this.y=match.getHeight() / 2;
		this.x=x;
		this.up=up;
		this.down=down;
		
	}
	
	public Integer getScore() {
		return score;
	}

	public void pointScored() {
		score++;
	}
	public void update() {
		if(y>0 && y<match.getHeight()-PH-29)
			y+=yspeed;
		else if(y==0)
			y+=3;
		else if(y>=match.getHeight()-PH-29)
			y-=3;
		
	}
	
	public void pressed(int keyCode) {
		if(keyCode == up)
			yspeed=-3;
		else if(keyCode==down)
			yspeed=3;
		
	}
	
	public void relesed(int keyCode) {
		if(keyCode==up || keyCode==down)
			yspeed=0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, PW, PH);
	}
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, PW, PH);
		g.drawString(match.getF().getPlayer1().getScore().toString(), 670,20);
		g.drawString(match.getF().getPlayer2().getScore().toString(), 15,20);
		
		
	}
}
