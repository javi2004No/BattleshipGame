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
//				
				//This Clear-rect will start chaging color patterns of the now filled board
				
				//Note: The upper part is for the Top board
//				g.clearRect(150, 50, 50, 50);
//				g.clearRect(250, 50, 50, 50);
//				g.clearRect(350, 50, 50, 50);
//				g.clearRect(450, 50, 50, 50);
//				g.clearRect(550, 50, 50, 50);
//				
//				//This if for the upper Second Row
//				g.clearRect(200, 100, 50, 50);
//				g.clearRect(300, 100, 50, 50);
//				g.clearRect(400, 100, 50, 50);
//				g.clearRect(500, 100, 50, 50);
//				g.clearRect(600, 100, 50, 50);
//				
//				//This is for the upper Fourth Row
//				g.clearRect(600, 200, 50, 50);
//				g.clearRect(500, 200, 50, 50);
//				g.clearRect(400, 200, 50, 50);
//				g.clearRect(300, 200, 50, 50);
//				g.clearRect(200, 200, 50, 50);
//				
//				//This is for the upper Fourth Row
//				g.clearRect(200, 300, 50, 50);
//				g.clearRect(300, 300, 50, 50);
//				g.clearRect(400, 300, 50, 50);
//				g.clearRect(500, 300, 50, 50);
//				g.clearRect(600, 300, 50, 50);
//				
//				//Note: This is for the bottom part of board
//				//First row
//				g.clearRect(200, 400, 50, 50);
//				g.clearRect(300, 400, 50, 50);
//				g.clearRect(400, 400, 50, 50);
//				g.clearRect(500, 400, 50, 50);
//				g.clearRect(600, 400, 50, 50);
//				
//				//Third Row
//				g.clearRect(200, 500, 50, 50);
//				g.clearRect(300, 500, 50, 50);
//				g.clearRect(400, 500, 50, 50);
//				g.clearRect(500, 500, 50, 50);
//				g.clearRect(600, 500, 50, 50);
//				
//				//Fifth row
//				g.clearRect(200, 600, 50, 50);
//				g.clearRect(300, 600, 50, 50);
//				g.clearRect(400, 600, 50, 50);
//				g.clearRect(500, 600, 50, 50);
//				g.clearRect(600, 600, 50, 50);
//				
//				//Bottom Row
//				g.clearRect(150, 650, 50, 50);
//				g.clearRect(250, 650, 50, 50);
//				g.clearRect(350, 650, 50, 50);
//				g.clearRect(450, 650, 50, 50);
//				g.clearRect(550, 650, 50, 50);
//				
//				//Extra Layer in the bottom
//				g.clearRect(200, 700, 50, 50);
//				g.clearRect(300, 700, 50, 50);
//				g.clearRect(400, 700, 50, 50);
//				g.clearRect(500, 700, 50, 50);
//				g.clearRect(600, 700, 50, 50);
		
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
