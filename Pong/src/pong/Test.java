package pong;

import java.awt.EventQueue;
/*
 * Simple version of Pong, to control the right-hand paddle use the up,down arrow keys, for the left-hand paddle use W S keys
 * the game automatically ends when either one of the two players reaches 10 points
 */
public class Test {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Game();
			}
		});
		
		

	}

}
