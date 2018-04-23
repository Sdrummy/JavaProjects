import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends JFrame {
	private static final int WIDTH=1000,HEIGHT=700;
	GamePanel gamePanel;
	
	public Game() {
		super("Flappy Bird");
		this.setSize(WIDTH,HEIGHT);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gamePanel=new GamePanel(this);
		add(gamePanel);
		setResizable(false);
		setVisible(true);
		
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	

}
