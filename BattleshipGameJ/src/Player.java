import java.util.ArrayList;
import java.util.Scanner;

//This class will store all the values for the player.
public class Player {
	//This is the defending board.
	//This is the board that you pass to the play function when this player is defending.
	public ArrayList<PlayerTile> playerBoard;
	//This is the attacking board
	public ArrayList<PlayerTile> playerAttack;
	Ai holder2;
	
//Constructor creates the board for the players.
	Player(){
		playerBoard = new ArrayList<PlayerTile>();
		playerAttack = new ArrayList<PlayerTile>();
		PlayerTile holder = new PlayerTile("");
		holder.createBoard(playerBoard);
		holder.createBoard(playerAttack);
		holder2 = new Ai(0,"",true);
	}
	
	void cleanup(String action, boolean hit) {
		if(hit) {
			playerAttack.get(holder2.findPos(action, playerAttack)).BeenHit = 1;
		}else {
			playerAttack.get(holder2.findPos(action, playerAttack)).BeenHit = 2;
		}
	}
	
	boolean checkAttack(String action) {
		if(holder2.findPos(action, playerAttack) == -1) {
			return false;
		}
		if(playerAttack.get(holder2.findPos(action, playerAttack)).BeenHit == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	boolean checkships(String startPoint, String endPoint, int size, int Ship) {
		
		if(holder2.findPos(startPoint, playerBoard) == -1 || holder2.findPos(endPoint, playerBoard) == -1) {
			System.out.println("Invalid input");
			return false;
		}
		int startint = Integer.parseInt(startPoint.substring(1));
		int startletter = playerAttack.get(0).transformPosition(startPoint.substring(0,1));
		int endint = Integer.parseInt(endPoint.substring(1));
		int endletter = playerAttack.get(0).transformPosition(endPoint.substring(0,1));
		int move = -1;
		if(endint != startint && endletter != startletter) {
			System.out.println("Invalid direction");
			return false;
		}else {
			if(endletter != startletter) {
				if(startletter > endletter) {
					move = 2;
				}else {
					move = 3;
				}
			}else {
				if(startint > endint) {
					move = 1;
				}else {
					move = 0;
				}
			}
		}
		String point = startPoint;
		ArrayList<PlayerTile> ship = new ArrayList<PlayerTile>();
		for(int i = 0; i < size; i++) {
			if(holder2.findPos(point, playerBoard) == -1) {
				System.out.println("Invalid Input");
				return false;
			}
			if(playerBoard.get(holder2.findPos(point, playerBoard)).Ship != 0) {
				System.out.println("Invalid Location");
				return false;
			}else {
				ship.add(playerBoard.get(holder2.findPos(point, playerBoard)));
			}
			if(i == (size-1)) {
				if(!point.equals(endPoint)) {
					System.out.println("Too big");
					return false;
				}
			}else {
				if(point.equals(endPoint)) {
					System.out.println("Too small");
					return false;
				}else {
					int holdint = Integer.parseInt(point.substring(1));
					int holdletter = playerAttack.get(0).transformPosition(point.substring(0,1));
					switch(move) {
						case 0:
							holdint++;
							break;
						case 1:
							holdint--;
							break;
						case 2:
							holdletter--;
							break;
						case 3:
							holdletter++;
							break;
						default:
							System.out.println("Direction letter");
							return false;
					}
					point = playerBoard.get(0).transformIntToLetter(holdletter) + holdint;
				}
			}
		}
		System.out.println("Ship position valid do you wish to confirm?");
		System.out.println("Type Y for yes or anything else for no.");
		Scanner scannerObj = new Scanner(System.in);
		String input = scannerObj.nextLine();
		if(input.equalsIgnoreCase("Y")) {
			for(int i = 0; i < ship.size(); i++) {
				playerBoard.get(holder2.findPos(ship.get(i).Position,playerBoard)).Ship = Ship;
			}
			return true;
		}else {
			return false;
		}
	}
}
