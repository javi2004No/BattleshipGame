import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Board_Code extends JPanel {
	JButton b = new JButton("Push");
	public void paint(Graphics g) {
		//This creates the real Size of the board
				//The FillRect created the block thats full
				//Note: These two creates 2 divided boards of the Game
				//IMPORTANT
				  //First #, that moves the board to the right
				  //Second #,
				  //Third #, 
				  //Fourth #, 
				
				g.fillRect(150, 50, 500, 300);
				
				g.fillRect(150, 400, 500, 300);
				
				
				//This adds and spaces each block space
				//This is spaces blocks vertically
				//Note: 
				int k = 0;
				for(int i = 50; i < 350; i+=30) {
					if(((i/2)%2) == 1) {
						k = 200;
					}else {
						k = 150;
					}
					//This sets blocks Horizantoly
					for(; k < 650; k += 100) {
						
						//This part changes the size of each white block on the baord
						g.clearRect(k,  i, 50, 30);
					}
				}
				//This does the same as the previous
				//Note: Each one creates more blocks and space to expand
				//The I corralates for the White blocks
				for(int i = 400; i < 700; i+=30) {
					if(((i/2)%2) == 1) {
						k = 200;
					}else {
						k = 150;
					}
					//This sets blocks Horizantoly
					for(; k < 650; k += 100) {
						
						//This part changes the size of each white block on the baord
						g.clearRect(k,  i, 50, 30);
					}
				}		
	}
	
	
	
	public void drawBoard() {
		//Note:The scanner statement can be used for any interpretation
				JFrame frame = new JFrame();
				//This set created the size of the board itself
				frame.setSize(800,800);
				//NOTE: when adding the name of the Class, make sure its correct
				frame.getContentPane().add(new Board_Code());
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
