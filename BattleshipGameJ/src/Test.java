import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	//this is simply a test that all the variables have been created in an array and
	//their positions are correct.
	//WARNING this is only supposed to be used as a test to make sure the variables are working
	//DO NOT use this in the actual game.
	//Will most likely delete it later.
	public void testvariables() {
		//Initializing the array and a String paramanter which I than feed Into the construtor
		PlayerTile[] tiles = new PlayerTile[100];
		String paramanter = "";
		for(int i = 0; i < 10; i++) {
			//Turning the i into the letter that I need for its position.
			switch(i) {
				case 0:
					paramanter = "A";
					break;
				case 1:
					paramanter = "B";
					break;
				case 2:
					paramanter = "C";
					break;
				case 3:
					paramanter = "D";
					break;
				case 4:
					paramanter = "E";
					break;
				case 5:
					paramanter = "F";
					break;
				case 6:
					paramanter = "G";
					break;
				case 7:
					paramanter = "H";
					break;
				case 8:
					paramanter = "I";
					break;
				case 9:
					paramanter = "J";
					break;
			}
			//Keeping the orginal state of the paramenter so I dont have to re input it every time.
			String holder = paramanter;
			//Simple algorithim add and multiply the 2 i's so that we dont have to do it manualy.
			//Now since this only for testing purposses I didnt use the much esier ArryList class but it should be used for the actual game.
			for(int i2 = 0; i2 < 10; i2++) {
				paramanter += (i2+1);
				tiles[(i*10) + i2] = new PlayerTile(paramanter);
				paramanter= holder; 
			}
		}
		//This just goes through every tile and prints out its position.
		for(int i = 0; i < 100; i++) {
			System.out.println(tiles[i].Position);
		}
	}
	
	//This function will simply create the ai and than tell us its ships.
	public int testAi() {
		//The AI class is being called here
		//The number in the parentheses repersents its level I want to make diffrent AI with diffrent level of diffuculty to them.
		Ai jeff = new Ai(1,"Jeff",false);
		Ai bob = new Ai(1,"Bob",false);
		Scanner obj = new Scanner(System.in);
		//Very simple just looks for and prints out where the AI ships are.
		//System.out.println("Jeffs Ships");
//		for(int i = 5; i > 0; i--) {
//			System.out.println("Ship " + i);
//			for(int d = 0; d < jeff.aiBoard.size(); d++) {
//				if(jeff.aiBoard.get(d).Ship == i) {
//					System.out.println(jeff.aiBoard.get(d).Position);
//				}
//			}
//		}
//		System.out.println("Bobs Ships");
//		for(int i = 5; i > 0; i--) {
//			System.out.println("Ship " + i);
//			for(int d = 0; d < bob.aiBoard.size(); d++) {
//				if(bob.aiBoard.get(d).Ship == i) {
//					System.out.println(bob.aiBoard.get(d).Position);
//				}
//			}
//		}
//		System.out.println("Next 100 are attacks");
//		String Attack;
//		while(jeff.aiAttack.size() > 0) {
//			Attack = jeff.action();
//			System.out.println(Attack);
//			jeff.cleanup(Attack, false,true);
//		}
//		System.out.println("Done");
		//System.out.println("Welcome to the Ai Battleship");
		//System.out.println("Click enter to continue, n to quit");
		String answer;
		String Attack;
		Game game = new Game();
		int result;
		int turn = 0;
		int totalturns = 0;
		while(true) {
//			answer = obj.next();
//			if(answer.equalsIgnoreCase("n")) {
//				break;
//			}
			if(turn == 0) {
//				System.out.println("Jeff");
				Attack = jeff.action();
//				System.out.println("Jeff attacks: " + Attack);
				result = game.play(Attack, bob.aiBoard,2);
				switch(result) {
					case 0:
//						System.out.println("Jeff Misses");
						jeff.cleanup(Attack, false, false, bob.aiBoard.get(bob.findPos(Attack, bob.aiBoard)).Ship);
						break;
					case 1:
//						System.out.println("Jeff Hits");
						jeff.cleanup(Attack, true, false, bob.aiBoard.get(bob.findPos(Attack, bob.aiBoard)).Ship);
						break;
					case 2:
					case 3:
//						System.out.println("Jeff Sinks Bobs " + bob.aiBoard.get(bob.findPos(Attack, bob.aiBoard)).getShip());
						jeff.cleanup(Attack, true, true, bob.aiBoard.get(bob.findPos(Attack, bob.aiBoard)).Ship);
						break;
				}
				if(result == 3) {
//					System.out.println("Jeff wins the Game!");
					break;
				}
//				jeff.cleanup(Attack, false, false);
				turn++;
			}else {
//				System.out.println("Bob");
				Attack = bob.action();
//				System.out.println("Bob attacks: " + Attack);
				result = game.play(Attack, jeff.aiBoard,1);
				switch(result) {
					case 0:
//						System.out.println("Bob Misses");
						bob.cleanup(Attack, false, false, jeff.aiBoard.get(jeff.findPos(Attack, jeff.aiBoard)).Ship);
						break;
					case 1:
//						System.out.println("Bob Hits");
						bob.cleanup(Attack, true, false, jeff.aiBoard.get(jeff.findPos(Attack, jeff.aiBoard)).Ship);
						break;
					case 2:
					case 3:
//						System.out.println("Bob Sinks Jeffs " + jeff.aiBoard.get(jeff.findPos(Attack, jeff.aiBoard)).getShip());
						bob.cleanup(Attack, true, true, jeff.aiBoard.get(jeff.findPos(Attack, jeff.aiBoard)).Ship);
						break;
				}
				if(result == 3) {
//					System.out.println("Bob wins the Game!");
					break;
				}
//				bob.cleanup(Attack, false, false);
				turn--;
			}
			totalturns++;
		}
		
		//System.out.println("It took " + totalturns + " turns!");
//		System.out.println("Game End");
		return totalturns;
	}
	//This code calls the drawboard function which draws the board Rhodas is in charge of that.
	public void testBoard() {
		Board_Code test = new Board_Code();
		test.drawBoard();
	}
	
	public void testTwoPlayer() {
		Scanner input = new Scanner(System.in);
		Ai holder = new Ai(1,"",true);
		Player player1 = new Player();
		Player player2 = new Player();
		Game game = new Game();
		System.out.println("Player 1 place your ships.");
		for(int i = 5; i > 0; i--) {
			System.out.println("YourBoard");
			drawTable(player1.playerBoard,false);
			System.out.println("Place " + player1.playerBoard.get(0).getShip(i) + " (Size " + player1.playerBoard.get(0).GetSize(i) + ")");
			boolean found = false;
			while(!found) {
				System.out.println("Start point: ");
				String startPoint = input.nextLine();
				System.out.println("End point: ");
				String endPoint = input.nextLine();
				if(player1.checkships(startPoint, endPoint, player1.playerBoard.get(0).GetSize(i),i)) {
					found = true;
					for(int z = 0; z < 50; z++) {
						System.out.println();
					}
					System.out.println("Ship Added");
				}
			}
		}
		for(int z = 0; z < 50; z++) {
			System.out.println();
		}
		System.out.println("Player 2 place your ships.");
		for(int i = 5; i > 0; i--) {
			System.out.println("YourBoard");
			drawTable(player2.playerBoard,false);
			System.out.println("Place " + player2.playerBoard.get(0).getShip(i) + " (Size " + player2.playerBoard.get(0).GetSize(i) + ")");
			boolean found = false;
			while(!found) {
				System.out.println("Start point: ");
				String startPoint = input.nextLine();
				System.out.println("End point: ");
				String endPoint = input.nextLine();
				if(player2.checkships(startPoint, endPoint, player2.playerBoard.get(0).GetSize(i),i)) {
					found = true;
					for(int z = 0; z < 50; z++) {
						System.out.println();
					}
					System.out.println("Ship Added");
				}
			}
		}
		for(int z = 0; z < 50; z++) {
			System.out.println();
		}
		int result;
		boolean Game = true;
		int turn = 0;
		String previous = "";
		int previousmove = -1;
		while(Game) {
			if(turn%2 == 0) {
				if(turn != 0) {
					System.out.println("Player 2 Attacked " + previous);
					switch(previousmove) {
					case 0:
						System.out.println("Player 2 Missed");
						break;
					case 1:
						System.out.println("Player 2 Hit");
						break;
					case 2:
					case 3:
						System.out.println("Player 2 Sunk Your " + player1.playerBoard.get(holder.findPos(previous, player1.playerBoard)).getShip());
						break;
				}
				}
				System.out.println("Player 1 turn");
				System.out.println("AttackBoard");
				drawTable(player1.playerAttack,true);
				System.out.println("PlayerBoard");
				drawTable(player1.playerBoard,false);
				System.out.println("What is your Attack?");
				boolean valid = false;
				while(!valid) {
					System.out.println("Attack:");
					String Attack = input.nextLine();
					if(player1.checkAttack(Attack)) {
						System.out.println("Attacked " + Attack);
						result = game.play(Attack, player2.playerBoard,2);
						switch(result) {
							case 0:
								System.out.println("You Miss");
								player1.cleanup(Attack, false);
								break;
							case 1:
								System.out.println("You Hit");
								player1.cleanup(Attack, true);
								break;
							case 2:
							case 3:
								System.out.println("You Sink Player 2's " + player2.playerBoard.get(holder.findPos(Attack, player2.playerBoard)).getShip());
								player1.cleanup(Attack, true);
								break;
						}
						if(result == 3) {
							System.out.println("Congratulations you won!");
							Game = false;
						}else {
							previous = Attack;
							previousmove = result;
						}
						System.out.println("Press enter to continue");
						input.nextLine();
						valid = true;
					}else {
						System.out.println("Invalid attack");
					}
				}
			}else {
				if(turn != 0) {
					System.out.println("Player 1 Attacked " + previous);
					switch(previousmove) {
					case 0:
						System.out.println("Player 1 Missed");
						break;
					case 1:
						System.out.println("Player 1 Hit");
						break;
					case 2:
					case 3:
						System.out.println("Player 1 Sunk Your " + player2.playerBoard.get(holder.findPos(previous, player2.playerBoard)).getShip());
						break;
				}
				}
				System.out.println("Player 2 turn");
				System.out.println("Your AttackBoard");
				drawTable(player2.playerAttack,true);
				System.out.println("Your Board");
				drawTable(player2.playerBoard,false);
				System.out.println("What is your Attack?");
				boolean valid = false;
				while(!valid) {
					System.out.println("Attack:");
					String Attack = input.nextLine();
					if(player2.checkAttack(Attack)) {
						System.out.println("Attacked " + Attack);
						result = game.play(Attack, player1.playerBoard,1);
						switch(result) {
							case 0:
								System.out.println("You Miss");
								player2.cleanup(Attack, false);
								break;
							case 1:
								System.out.println("You Hit");
								player2.cleanup(Attack, true);
								break;
							case 2:
							case 3:
								System.out.println("You Sink Player 1's " + player1.playerBoard.get(holder.findPos(Attack, player1.playerBoard)).getShip());
								player2.cleanup(Attack, true);
								break;
						}
						if(result == 3) {
							System.out.println("Congratulations you won!");
							Game = false;
						}else {
							previous = Attack;
							previousmove = result;
						}
						System.out.println("Press enter to continue");
						input.nextLine();
						valid = true;
					}else {
						System.out.println("Invalid attack");
					}
				}
			}
			turn++;
			for(int z = 0; z < 50; z++) {
				System.out.println();
			}
		}
		//System.out.
	}
	
	public void testSinglePlayer() {
		Scanner input = new Scanner(System.in);
		Ai holder = new Ai(1,"",true);
		Ai jeff = new Ai(1,"Jeff",false);
		Player player1 = new Player();
		Game game = new Game();
		System.out.println("Player 1 place your ships.");
		for(int i = 5; i > 0; i--) {
			System.out.println("Your Board");
			drawTable(player1.playerBoard,false);
			System.out.println("Place " + player1.playerBoard.get(0).getShip(i) + " (Size " + player1.playerBoard.get(0).GetSize(i) + ")");
			boolean found = false;
			while(!found) {
				System.out.println("Start point: ");
				String startPoint = input.nextLine();
				System.out.println("End point: ");
				String endPoint = input.nextLine();
				if(player1.checkships(startPoint, endPoint, player1.playerBoard.get(0).GetSize(i),i)) {
					found = true;
					for(int z = 0; z < 50; z++) {
						System.out.println();
					}
					System.out.println("Ship Added");
				}
			}
		}
		for(int z = 0; z < 50; z++) {
			System.out.println();
		}
		int result;
		boolean Game = true;
		int turn = 0;
		String previous = "";
		int previousmove = -1;
		while(Game) {
			if(turn%2 == 0) {
				if(turn != 0) {
					System.out.println("AI Attacked " + previous);
					switch(previousmove) {
					case 0:
						System.out.println("AI Missed");
						break;
					case 1:
						System.out.println("AI Hit");
						break;
					case 2:
					case 3:
						System.out.println("AI Sunk Your " + player1.playerBoard.get(holder.findPos(previous, player1.playerBoard)).getShip());
						break;
				}
				}
				System.out.println("Player 1 turn");
				System.out.println("Your AttackBoard");
				drawTable(player1.playerAttack,true);
				System.out.println("Your Board");
				drawTable(player1.playerBoard,false);
				System.out.println("What is your Attack?");
				boolean valid = false;
				while(!valid) {
					System.out.println("Attack:");
					String Attack = input.nextLine();
					if(player1.checkAttack(Attack)) {
						System.out.println("Attacked " + Attack);
						result = game.play(Attack, jeff.aiBoard,2);
						switch(result) {
							case 0:
								System.out.println("You Miss");
								player1.cleanup(Attack, false);
								break;
							case 1:
								System.out.println("You Hit");
								player1.cleanup(Attack, true);
								break;
							case 2:
							case 3:
								System.out.println("You Sink Player 2's " + jeff.aiBoard.get(holder.findPos(Attack, jeff.aiBoard)).getShip());
								player1.cleanup(Attack, true);
								break;
						}
						if(result == 3) {
							System.out.println("Congratulations you won!");
							Game = false;
						}else {
							previous = Attack;
							previousmove = result;
						}
						System.out.println("Press enter to continue");
						input.nextLine();
						valid = true;
					}else {
						System.out.println("Invalid attack");
					}
				}
			}else {
				String Attack = jeff.action();
				result = game.play(Attack, player1.playerBoard, 1);
				switch(result) {
					case 0:
						jeff.cleanup(Attack, false, false,player1.playerBoard.get(holder.findPos(Attack, player1.playerBoard)).Ship );
						break;
					case 1:
						jeff.cleanup(Attack, true, false,player1.playerBoard.get(holder.findPos(Attack, player1.playerBoard)).Ship );
						break;
					case 2:
					case 3:
						jeff.cleanup(Attack, true, true,player1.playerBoard.get(holder.findPos(Attack, player1.playerBoard)).Ship );
						break;
				}
				if(result == 3) {
					System.out.println("Unfourtainaly you lost");
					Game = false;
				}else {
					previous = Attack;
					previousmove = result;
				}
			}
			turn++;
			for(int z = 0; z < 50; z++) {
				System.out.println();
			}
		}
	}
	
	public void testArt() {
//		System.out.println("\033[1m _");
//		System.out.println("\033[1m|*|");
//		System.out.println("\033[1m|*|");
//		System.out.println("\033[1m|*|");
//		System.out.println("\033[1m|\033[4m*\033[0m\033[1m|");
//		System.out.println("\033[0m  \033[4m1\033[0m \033[4m2\033[0m \033[4m3\033[0m \033[4m4\033[0m \033[4m5\033[0m \033[4m6\033[0m \033[4m7\033[0m \033[4m8\033[0m \033[4m9\033[0m \033[4m10\033[0m\033[0m");
//		System.out.println("A|");
//		System.out.println("B|");
//		System.out.println("C|");
//		System.out.println("D|");
//		System.out.println("E|");
//		System.out.println("F|");
//		System.out.println("G|");
//		System.out.println("H|");
//		System.out.println("I|");
//		System.out.println("J|____________________");
		Ai holder = new Ai(1,"",false);
		drawTable(holder.aiBoard, false);
	}
	
	private void drawTable(ArrayList<PlayerTile> board, boolean attack) {
		Ai hold = new Ai(0,"",true);
//		boolean previousexisted = false;
//		System.out.println("\033[0m  \033[4m1\033[0m \033[4m2\033[0m \033[4m3\033[0m \033[4m4\033[0m \033[4m5\033[0m \033[4m6\033[0m \033[4m7\033[0m \033[4m8\033[0m \033[4m9\033[0m \033[4m10\033[0m\033[0m");
//		for(int i = 0; i < 10; i++) {
//			String letter = board.get(0).transformIntToLetter(i);
//			int holdpos = hold.findPos(letter+"1", board);
////			if(holdpos != -1) {
////				if(board.get(holdpos).Ship != 0) {
////					System.out.print(letter +"\033[4m\033[1m|\033[0m");
////					previousexisted = true;
////				}else {
////					System.out.print(letter +"|");
////				}
////			}else {
//				System.out.print(letter +"|");
////			}
//			for(int z = 1; z < 11; z++) {
//				String pos = letter+z;
//				int num = hold.findPos(pos, board);
//				if(num > -1) {
//					if(board.get(num).Ship > 0) {
//						if(board.get(num).horizantal) {
////							if(board.get(num).where == 1) {
////								if(previousexisted) {
////									System.out.print("\033[4m\033[1m* ");
////								}else {
////									System.out.print("\033[1m\033[4m|* ");
////								}
////							}else if(board.get(num).where == 2) {
////								System.out.print("\033[4m\033[1m*|\033[0m ");
////							}else {
////								System.out.print("* ");
////							}
//						}else {
//							
//							if(board.get(num).where == 1) {
//								if(previousexisted) {
//									System.out.print("\033[1m*|\033[0m");
//								}else {
//									System.out.print("\033[1m|*|\033[0m");
//								}
//							}else if(board.get(num).where == 2) {
//								System.out.print("\033[4m\033[1m|*|\033[0m");
//							}else {
//								System.out.print("\033[1m|*|\033[0m");
//							}
//						}
//						//System.out.print("* ");
//						previousexisted = true;
//					}else {
//						String posh = letter+(z+1);
//						int numh = hold.findPos(posh, board);
//						if(numh != -1) {
//							if(board.get(numh).Ship != 0) {
//								System.out.print(" ");
//							}else {
//								System.out.print("  ");
//							}
//						}else {
//							System.out.print("  ");
//						}
//						previousexisted = false;
//					}
//				}else {
//					String posh = letter+(z+1);
//					int numh = hold.findPos(posh, board);
//					if(numh != -1) {
//						if(board.get(numh).Ship != 0) {
//							System.out.print(" ");
//						}else {
//							System.out.print("  ");
//						}
//					}else {
//						System.out.print("  ");
//					}
//					previousexisted = false;
//				}
//			}
//			System.out.println();
//		}
//		System.out.println("Stop");
		if(!attack) {
			System.out.println("\033[0m  \033[4m1\033[0m \033[4m2\033[0m \033[4m3\033[0m \033[4m4\033[0m \033[4m5\033[0m \033[4m6\033[0m \033[4m7\033[0m \033[4m8\033[0m \033[4m9\033[0m \033[4m10\033[0m\033[0m");
			for(int i = 0; i < 10; i++) {
				String letter = board.get(0).transformIntToLetter(i);
				System.out.print(letter+"|");
				for(int z = 1; z < 11; z++) {
					String pos = letter+z;
					int num = hold.findPos(pos, board);
					if(num > -1) {
						if(board.get(num).Ship > 0) {
							System.out.print("* ");
						}else {
							System.out.print("  ");
						}
					}else {
						System.out.print("  ");
					}
				}
				System.out.println();
			}
		}else {
			System.out.println("\033[0m  \033[4m1\033[0m \033[4m2\033[0m \033[4m3\033[0m \033[4m4\033[0m \033[4m5\033[0m \033[4m6\033[0m \033[4m7\033[0m \033[4m8\033[0m \033[4m9\033[0m \033[4m10\033[0m\033[0m");
			for(int i = 0; i < 10; i++) {
				String letter = board.get(0).transformIntToLetter(i);
				System.out.print(letter+"|");
				for(int z = 1; z < 11; z++) {
					String pos = letter+z;
					int num = hold.findPos(pos, board);
					if(num > -1) {
						if(board.get(num).BeenHit == 1) {
							System.out.print("* ");
						}else if(board.get(num).BeenHit == 2) {
							System.out.print("X ");
						}else {
							System.out.print("  ");
						}
					}else {
						System.out.print("  ");
					}
				}
				System.out.println();
			}
		}

	}

	public void testClick() {
		
	}
}
