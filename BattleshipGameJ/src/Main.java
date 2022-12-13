import java.util.Scanner;
import java.util.Scanner;
import java.io. File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Test test = new Test();
		int score = 48;
		// Creating welcome text.
		        boolean game = true;
		        System.out.println("-- Welcome to Battleship! --");
		        System.out.println("----------------------------------------------------------------------------------");
		        do {
		            
		            System.out.println(
		                    "Select a mode: \n" +
		                    " 1) Rules\n" +
		                    " 2) Player vs Player\n" +
		                    " 3) Player vs Ai\n" +
		                    " 4) High scores\n" +
		                    " 5) Quit "
		                );
		                System.out.println("----------------------------------------------------------------------------------");
		                int selection = input.nextInt();
		                input.nextLine();
		                switch (selection) {
		                case 1:
		                 Rules();
		                  break;
		                case 2:
		                  test.testTwoPlayer();
		                  score++;
		                  break;
		                case 3:
		                 test.testSinglePlayer();
		                 score++;
		                  break;
		                case 4:
		                	highScore(score);
		                  break;
		                case 5:
		                    game = false;
		                    break;
		                default:
		                  System.out.println("Invalid selection, please choose from options 1-4.");
		                  break;
		        }
		        
		    }while(game);
		    }
	  public static void Rules() {
	        //Title Battleship Rules in large font and on bold
	                String titleInBold = "BattleShip Rules";
	                System.out.println("\033[0;1m" + titleInBold);
	                
	                System.out.println("*--------------------------------------*");

	                
	                //Type Game Objectives sub-heading in bold
	                String gameObjInBold = "Game Objective";
	                System.out.println("\033[0;1m" + gameObjInBold);
	                
	                
	                
	                //Type game objectives in regular font
	                System.out.println("\033[3mThe object of Battleship is to try and sink all of the other player's before they sink all of your ships.\033[0m");
	                System.out.println("\033[3mAll of the other player's ships are somewhere on his/her board.\033[0m");
	                System.out.println("\033[3mYou try and hit them by calling out the coordinates of one of the squares on the board.\033[0m");
	                System.out.println("\033[3mThe other player also tries to hit your ships by calling out coordinates.\033[0m");
	                System.out.println("\033[3mNeither you nor the other player can see the other's board so you must try to guess where they are\033[0m");
	                System.out.println("\033[3mEach board in the physical game has two grids: the lower(horizontal) section for the player's ships and\033[0m");
	                System.out.println("\033[3mthe upper part(vertical during play) for recording the player's guesses.\033[0m");
	                
	                //Type Starting a New Game sub-heading in bold
	                System.out.println("*--------------------------------------*");
	                String startNewInBold = "Starting a New Game";
	                System.out.println("\033[0;1m" + startNewInBold);
	                
	                //Type out how to start a new game
	                System.out.println("\033[3mEach player places the 5 ships somewhere on their board.\033[0m");
	                System.out.println("\033[3mThe ships can only be placed vertically or horizontally.\033[0m");
	                System.out.println("\033[3mDiagonal placement is not allowed. No part of a ship may hang off the edge of the board.\033[0m");
	                System.out.println("\033[3mShips may not overlap each other. No ships may be placed on another ship.\033[0m");
	                System.out.println("\033[3mOnce the guessing begins, the players may not move the ships.\033[0m");
	                System.out.println("\033[3mThe 5 ships are:  Carrier (occupies 5 spaces), Battleship (4), Cruiser (3), Submarine (3), and Destroyer (2).\033[0m");
	            
	                //Type Playing the Game sub-heading in bold
	                System.out.println("*--------------------------------------*");
	                String playGameInBold = "Playing the Game";
	                System.out.println("\033[0;1m" + playGameInBold);
	                
	                //Type out how to play the game
	                System.out.println("\033[3mPlayer's take turns guessing by calling out the coordinates. The opponent responds with \"hit\" or \"miss\" as appropriate.\033[0m");
	                System.out.println("\033[3mBoth players should mark their board with pegs: red for hit, white for miss.\033[0m");
	                System.out.println("\033[3mFor example, if you call out F6\033[0m");
	                System.out.println("\033[3mand your opponent does not have any ship located at F6, your opponent would respond with \"miss\".\033[0m");
	                System.out.println("\033[3mYou record the miss F6 by placing a white peg on the lower part of your board at F6. \033[0m");
	                System.out.println("\033[3mYour opponent records the miss by placing.\033[0m");
	                System.out.println("\033[3mWhen all of the squares that one your ships occupies have been hit,the ship will be sunk. You should announce \"hit and sunk\".\033[0m");
	                System.out.println("\033[3mAs soon as all of one player's ships have been sunk, the game ends.\033[0m");
	                System.out.println("*--------------------------------------*");
	                Scanner Obj = new Scanner(System.in);
	                System.out.println("Type any letter to return");
	                String input = Obj.nextLine();
	    }
	  
	  public static void highScore(int score) {
		  //
	        //
	        Scanner myObj = new Scanner(System.in);
	        
	        System.out.println("Enter username");
	        //
	        String userName = myObj.nextLine();
	        System.out.println("Welcome " + userName);
	        
	        try {
	        File Obj = new File("D:\\highScore\\highScore.txt");
	          if (Obj.createNewFile()) {
	            System.out.println("Highscore file created: " + Obj.getName());
	          } else {
	            System.out.println("Highscore file found.");
	          }
	      //    System.out.println("Print your score");
	  //        int score = myObj.nextInt();
	          Scanner myObj2 = new Scanner(Obj);
	          boolean found = false;
	          int highscore = 0;
	          do {
	              if(myObj2.hasNext()) {
	                  try {
	                      String Highscore = myObj2.nextLine();
	                      highscore = Integer.parseInt(Highscore);
	                      found = true;
	                  }catch(Exception e) {
	                      
	                  } 
	          }else {
	              highscore = 0;
	              found = true;
	          }
	          } while(!found);
	          if(score > highscore) {
	              FileWriter myWriter = new FileWriter("D:\\highScore\\highScore.txt");
	              //myWriter.flush();
	              myWriter.write(userName + "\n" + String.valueOf(score));
	              myWriter.close();
	              System.out.println("Score written");
	          }else {
	        	  System.out.println("Score Rejected");
	          }
	        } catch (IOException e) {
	          System.out.println("An error occurred.");
	          e.printStackTrace();
	    }
	  }
		  
	}

