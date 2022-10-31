import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Board_Code {
	public void paint(Graphics g) {
		//This creates the real Size of the board
		//The FillRect created the block thats full
		//Note: this set is for the Grey Blocks
		g.fillRect(100, 100, 500, 500);
		//This adds and spaces each block space
		//This is spaces blocks vertically
		for(int i = 100; i <= 500; i+=100) {
			//This sets blocks Horizantoly
			for(int j = 100; j <= 500; j += 100) {
				g.clearRect(i,  j, 50, 50);
			}
		}
		//This does the same as the previous
		//Note: Each one creates more blocks and space to expand
		//The I corralates for the White blocks
		for(int z = 150; z <= 550; z += 100) {
			for(int i = 150; i <= 550; i += 100) {
				g.clearRect(z, i, 50, 50);
			}
				
		}
		
		//This Clear-rect will start chaging color patterns of the now filled board
		//Note: Keep in mind of the RGB color pattern [Numerical]
		//g.clearRect(100, 100, 50, 50);
		
	}
	
	public void drawBoard() {
		//Note:The scanner statement can be used for any interpretation
		JFrame frame = new JFrame();
		//This set created the size of the board itself
		frame.setSize(800,800);
		//NOTE: when adding the name of the Class, make sure its correct
		//frame.getContentPane().add(new Battleship());
		//This creates the spots each block goes
		frame.setLocationRelativeTo(null);
		//This is IMPORTANT
		//This will create the background of the board
		frame.setBackground(Color.BLUE);
		//
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		frame.setVisible(true);
		
		
		
	}

}
