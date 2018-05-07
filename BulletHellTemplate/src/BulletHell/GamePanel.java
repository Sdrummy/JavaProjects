package BulletHell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable,KeyListener{
	private static final long serialVersionUID = 1L;
	
	private double fps=60; //default
	private PlayerShipEntity player;
	private Thread animator;
	private boolean running=false;
	//private boolean gameOver=false;
	private BufferedImage background;
	private long lastTime;//ultima volta che ho sparato
	private JFrame frame;
	private ArrayList<EnemyEntity> enemy;
	private Fire friendlyFire,enemyFire;
	/*
	 * TODO:inserire un array di nemici e dare anche ad essi la possibilità di sparare
	 */
	public GamePanel(JFrame frame) {
		try {
			background=ImageIO.read(getClass().getResource("Background-4.png"));
		} catch (IOException e) {
			this.setBackground(Color.BLACK);
		}
		this.frame=frame;
		friendlyFire=new Fire(frame);
		player=new PlayerShipEntity("shipResized.png",frame.getWidth()/2-40,frame.getHeight()-100,frame);
		enemyFire=new Fire(frame);
		enemy=new ArrayList<EnemyEntity>();
		enemy.add(new EnemyEntity("ship.png",frame.getWidth()/2-40,30,frame));//questi 3 sono cose a caso 
		enemy.add(new EnemyEntity("ship.png",frame.getWidth()-100,100,frame));
		enemy.add(new EnemyEntity("ship.png",frame.getWidth()+200,350,frame));
		
		lastTime=0;
	}
	/*
	 * uso per aspettare che il panel sia inserito nel frame
	 * 
	 */
	public void addNotify() {
		super.addNotify();
		startGame();
	}
	public void startGame() {
		if(animator==null || !running) {
			animator=new Thread(this);
			animator.start();
		}
		
	}
	
	public void stopGame() {
		running=false;
	}
	
	public void run() {
		long beforeTime,timeDiff,sleepTime;
		beforeTime=System.nanoTime()/1000;
		running=true;
		while(running) {
			update();
			repaint();
			timeDiff=System.nanoTime()/1000-beforeTime;
			sleepTime=(long) ((1000/fps)-timeDiff);
			if(sleepTime<=0)
				sleepTime=5; //di default almeno un po' dormo
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				
			}
			beforeTime=System.nanoTime()/1000;
		}
		System.exit(0);
	}

	/*
	 * TODO:sistemare il modo di sparare proiettili: la starting position dipende dalla size dello sprite usato
	 * 		
	 * 		 
	 */
	public void fire() {
		
		if(System.nanoTime()-lastTime>35000000) { //sparo 1 colpo ogni 0.035 secondi
		
			friendlyFire.addBullet("BF.png",player.getX()+23,player.getY()-12,0,-2.5);
		}
		lastTime=System.nanoTime();
	}
	public void update() {
		if(!player.playerDead()) { //il gioco aggiorna fintanto che il player è vivo
			player.update();
			for(int i=0;i<enemy.size();i++) {
			if(!enemy.get(i).isDead()) {
				enemy.get(i).update();
				friendlyFire.entityCollision(enemy.get(i));
				enemyFire.entityCollision(player);
				if(this.enemy.get(i).canFire()) 
					enemyFire.addBullet("enemybullet.png", enemy.get(i).getX()+24, enemy.get(i).getY()+70, 0, 2);
				
			}
			}
			friendlyFire.update();
			enemyFire.update();	
			
		}
		else {
			System.out.println("Sei Morto! :)");
			this.running=false;
		}
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		super.paintComponent(g2d);
		g2d.drawImage(background, 0, 0, null);
		player.paint(g2d);
		for(int i=0;i<enemy.size();i++) {
		if(!enemy.get(i).isDead())
			enemy.get(i).paint(g2d);
		}
		friendlyFire.paint(g2d);
		enemyFire.paint(g2d);
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		player.pressed(e.getKeyCode());
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
			fire();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		player.released(e.getKeyCode());
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		return;
		
	}
}
