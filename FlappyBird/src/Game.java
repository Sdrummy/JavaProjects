import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH=600,HEIGHT=450;
	GamePanel gamePanel;
	
	public Game() {
		super("Cippy Bird");
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
