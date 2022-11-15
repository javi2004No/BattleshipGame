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
	public void testAi() {
		//The AI class is being called here
		//The number in the parentheses repersents its level I want to make diffrent AI with diffrent level of diffuculty to them.
		Ai jeff = new Ai(1);
		Ai bob = new Ai(1);
		Scanner obj = new Scanner(System.in);
		//Very simple just looks for and prints out where the AI ships are.
//		for(int i = 5; i > 0; i--) {
//			System.out.println("Ship " + i);
//			for(int d = 0; d < jeff.aiBoard.size(); d++) {
//				if(jeff.aiBoard.get(d).Ship == i) {
//					System.out.println(jeff.aiBoard.get(d).Position);
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
		System.out.println("Welcome to the Ai Battleship");
		System.out.println("Click enter to continue, n to quit");
		String answer;
		String Attack;
		Game game = new Game();
		int result;
		int turn = 0;
		while(true) {
			answer = obj.next();
			if(answer.equalsIgnoreCase("n")) {
				break;
			}
			if(turn == 0) {
				Attack = jeff.action();
				System.out.println("Jeff attacks: " + Attack);
				result = game.play(Attack, bob.aiBoard,0);
				if(result == 1) {
					System.out.println("Jeff Hits");
				}else if (result == 0) {
					System.out.println("Jeff Misses");
				}else {
					System.out.println("Jeff Sinks Bobs " + bob.aiBoard.get(bob.findPos(Attack, bob.aiBoard)).getShip());
				}
				jeff.cleanup(Attack, false, false);
				turn++;
			}else {
				Attack = bob.action();
				System.out.println("Bob attacks: " + Attack);
				result = game.play(Attack, jeff.aiBoard,1);
				if(result == 1) {
					System.out.println("Bob Hits");
				}else if(result == 0){
					System.out.println("Bob Misses");
				}else {
					System.out.println("Bob Sinks Jeffs " + jeff.aiBoard.get(jeff.findPos(Attack, jeff.aiBoard)).getShip());
				}
				bob.cleanup(Attack, false, false);
				turn--;
			}
		}
	}
	//This code calls the drawboard function which draws the board Rhodas is in charge of that.
	public void testBoard() {
		Board_Code test = new Board_Code();
		test.drawBoard();
	}
	
	public void testClick() {
		
	}
}
