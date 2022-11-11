import java.util.Random;
import java.util.ArrayList;

//This shall be the class where all the AI stuff is stored
public class Ai {
	//Initializing the variables I need.
	//The aiBoard is the board of the ai itself.
	public ArrayList<PlayerTile> aiBoard;
	//The aiAttack is the board representing the AIShots.
	public ArrayList<PlayerTile> aiAttack;
	//Lastly the level variable represents the current level this ai is on.
	public int Level;
	//This is the main function that is called when you want the Ai to do something.
	//It will return a string which will be the position of th attack. Eg: F1 or G7.
	public String action() {
		String action = "";
		switch(Level) {
			case 1:
				EasyAi();
				break;
		}
		return action;
	}
	
	
	//This function here repersents the algorithim for the easy AI.
	//The easy AI will have two modes one mode will it will radomly shoot around the map making sure it does not shoot a placed it allready shot
	//and mode two where it hit something and now it will hit the surrundings areas of the hit intill
	//it sinks the ship.
	//It will again return a string with the position of the shot.
	private String EasyAi() {
		String action = "";
		//if(record.length == 1 && record[0].BeenHit) {
			
		//}
		
		
		return action;
	}
	
	//This is the constructor of the AI it will initialize all the variables.
	public Ai(int level) {
		aiBoard = new ArrayList<PlayerTile>();
		aiAttack = new ArrayList<PlayerTile>();
		String paramanter = "";
		//This is a nested loop in order to get the correct positions for the board.
		for(int i = 0; i < 10; i++) {
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
			String holder = paramanter;
			for(int i2 = 0; i2 < 10; i2++) {
				paramanter += (i2+1);
				//Adds the tiles for the AI boards
				aiBoard.add(new PlayerTile(paramanter));
				aiAttack.add(new PlayerTile(paramanter));
				paramanter= holder; 
			}
		}
		Level = level;
		//Calls the function to create the AI's ships.
		createShips();
	}
	
	//This function places the AI's ship in a random tile and direction.
	private void createShips() {
		//Intializing variables
		ArrayList<PlayerTile> holder = new ArrayList<PlayerTile>();
		Random random = new Random();
		int number;
		int currentShip = 5;
		boolean found;
		
		//It will continue until the break command is called.
		
		while(true) {
			found = false;
			//Picks random number from 0-100.
			number = random.nextInt(100);
			//First check
			if(aiBoard.get(number).Ship == 0) {
				switch(currentShip) {
				case 5:
					holder = ship(5,number);
					break;
				case 4:
					holder = ship(4,number);
					break;
				case 3:
				case 2:
					holder = ship(3,number);
					break;
				case 1:
					holder = ship(2,number);
					break;
				}
				if(holder.size() > 0) {
					found = true;
				}
			}
			if(found) {
				int newNum = random.nextInt(holder.size());
				int  nnum = aiBoard.indexOf(holder.get(newNum));
				if((number / 10) != (nnum / 10)) {
					if(nnum < number) {
						for(int i = nnum; i <= number; i+=10) {
							aiBoard.get(i).Ship = currentShip;
						}
					}else if(nnum > number) {
						for(int i = nnum; i >= number; i-=10) {
							aiBoard.get(i).Ship = currentShip;
						}
					}
				}else {
					if(nnum < number) {
						for(int i = nnum; i <= number; i++) {
							aiBoard.get(i).Ship = currentShip;
						}
					}else if(nnum > number) {
						for(int i = nnum; i >= number; i--) {
							aiBoard.get(i).Ship = currentShip;
						}
					}
				}
				currentShip--;
			}
			if(currentShip <= 0) {
				break;
			}
		}
	}
	
	//0 right.
	//1 left.
	//2 up.
	//3 down.
	private ArrayList<PlayerTile> ship(int numberofTiles, int starter) {
		int letter = starter/10;
		ArrayList<PlayerTile> holder = new ArrayList<PlayerTile>();
		System.out.println(letter + " " + starter + " " + aiBoard.get(starter).Position);
		for(int i = 0; i < 4; i++) {
			if(checkCells(numberofTiles,i,starter,aiBoard)) {
				System.out.println(i + " Fits");
				switch(i) {
				case 0:
					holder.add(aiBoard.get(starter + (numberofTiles -1 )));
					System.out.println(holder.get(holder.size() - 1).Position);
					break;
				case 1:
					holder.add(aiBoard.get(starter - (numberofTiles -1 )));
					System.out.println(holder.get(holder.size() - 1).Position);
					break;
				case 2:
					holder.add(aiBoard.get(starter - ((numberofTiles -1) * 10)));
					System.out.println(holder.get(holder.size() - 1).Position);
					break;
				case 3:
					holder.add(aiBoard.get(starter + ((numberofTiles -1) * 10)));
					System.out.println(holder.get(holder.size() - 1).Position);
					break;
				}
			}else {
				System.out.println(i + " Does not fit");
			}
		}
		
		return holder;
	}
	//0 right.
	//1 left.
	//2 up.
	//3 down.
	//CheckCells from a starter cell to a number of cells in a certain direction in a straight line.
	//Returns true if it checks out and returns false if it cannot fit.
	private boolean checkCells(int numberOfCells, int direction, int startCell, ArrayList<PlayerTile> array) {
		//initializing vriables
		int number = startCell;
		int previous = number;
		boolean done = true;	
		int step = 0;
		//Based on the direction is given sets the number of steps needed to skip 
		switch(direction) {
			case 0:
				step = 1;
				break;
			case 1:
				step = -1;
				break;
			case 2:
				step = -10;
				break;
			case 3:
				step = 10;
				break;
		}
		//it than has three checks to make sure the cell is valid.
		for(int i = 0; i < numberOfCells; i++) {
			//The first check is to make sure the cell exists since there are only cells from
			// 0 - 99.
			//The next check sees that if we are left or right have we reached the end of the
			//board.
			if(number >= 100 || number < 0) {
				done = false;
				return done;
			}else if(direction <= 1 && (number/10) != (previous/10)) {
				done = false;
				return done;
			}
			//The final check is to make sure that the cell we have is empty.
			if(array.get(number).Ship == 0) {
				
				previous = number;
				number+=step;
			}else {
				done = false;
				return done;
			}
		}	
		return done;
	}
}
