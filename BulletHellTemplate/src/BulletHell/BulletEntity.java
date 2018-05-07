package BulletHell;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
/*
 * TODO:implementare una classe "FIRE" che permetta di sparare, la classe sar� semplicemente composta da una lista di bullets
 * e una serie di attributi per descrivere idealmente forma/sprite/velocit�/propriet� particolari di questi bullets
 * il costruttore deve prevedere una stringa che serve a costruire le gameEntity (tutte uguali) quindi ogni navicella avr� la sua classe fire
 * e in casi particolari anche pi� di una 
 * La classe deve prevedere tutti i metodi per sparare e visualizzare proiettili e anche eliminarli in caso di hit con unit� (alleata o nemica)
 * Idealmente tale classe potr� essere estesa ulteriormente per creare una classe "FIRE ENEMY" usata solo dai nemici che implementi la possibilit� di sparare
 * ad intervalli di tempo variabili
 */
public class BulletEntity extends GameEntity{

	public BulletEntity(String bufferedImageUrl, double x, double y,JFrame frame) {
		super(bufferedImageUrl, x, y,frame);
		
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see BulletHell.GameEntity#update()
	 * faccio override di questo metodo perch� tanto i bullets che escono dallo schermo vengono rimossi
	 */
	public void update() {
		x+=xspeed;
		y+=yspeed;
	}

	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(sprite, (int)x, (int)y, sprite.getWidth(), sprite.getHeight(), null);
		
	}
}
