import java.util.Random;
import java.util.ArrayList;

//This shall be the class where all the AI stuff is stored
public class Ai {
	public int boatsLeft;
	//Initializing the variables I need.
	//The aiBoard is the board of the ai itself.
	public ArrayList<PlayerTile> aiBoard;
	//The aiAttack is the board representing the AIShots.
	public ArrayList<PlayerTile> aiAttack;
	//Lastly the level variable represents the current level this ai is on.
	public int Level;
	//Gives me a a short record of past position that I have hit. 
	public ArrayList<PlayerTile> record;
	//The lowest boat that the enimie could have.
	public int lowestBoat;
	//Holds the pos for a function;
	private String PosHolder;
	//This is the main function that is called when you want the Ai to do something.
	//It will return a string which will be the position of th attack. Eg: F1 or G7.
	public String action() {
		String action = "";
		switch(Level) {
			case 1:
				action = EasyAi();
				break;
		}
		return action;
	}
	
	public void cleanup(String action, boolean hit, boolean sunk) {
		if(hit) {
			record.add(aiAttack.get(findPos(action,aiAttack)));
		}else {
			if(record.size() > 0) {
				if(sunk) {
					for(int i = 0; i < record.size(); i++) {
						aiAttack.remove(findPos(record.get(i).Position,aiAttack));
					}
					record.clear();
				}else {
					
				}
			}else {
				aiAttack.remove(findPos(action,aiAttack));
			}
		}
	}
	
	//This function here repersents the algorithim for the easy AI.
	//The easy AI will have two modes one mode will it will radomly shoot around the map making sure it does not shoot a placed it allready shot
	//and mode two where it hit something and now it will hit the surrundings areas of the hit intill
	//it sinks the ship.
	//It will again return a string with the position of the shot.
	private String EasyAi() {
		Random random = new Random();
		String action = "";
		ArrayList<PlayerTile> holder = new ArrayList<PlayerTile>();
		if(record.size() == 1) {
			for(int i = 0; i < 4; i++) {
				if(checkCells(lowestBoat,i,findPos(record.get(0).Position,aiAttack),aiAttack,false)) {
					//System.out.println(PosHolder);
					holder.add(aiAttack.get(findPos(PosHolder,aiAttack)));
				}
			}
		}else{
			int num = random.nextInt(aiAttack.size());
			action = aiAttack.get(num).Position;
		}
		return action;
	}
	
	//This is the constructor of the AI it will initialize all the variables.
	public Ai(int level) {
		aiBoard = new ArrayList<PlayerTile>();
		aiAttack = new ArrayList<PlayerTile>();
		PlayerTile holder = new PlayerTile("");
		holder.createBoard(aiBoard);
		holder.createBoard(aiAttack);
		Level = level;
		//Calls the function to create the AI's ships.
		createShips();
		boatsLeft = 5;
		record = new ArrayList<PlayerTile>();
		lowestBoat = 2;
		PosHolder = "";
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
				//Calls the function based on what ship it has.
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
			//When it finds sutible locations for the ship it will than pick a random location
			//and set that location as where the ship is.
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
	//Return all possible permutations of directions the boats can fit in.
	private ArrayList<PlayerTile> ship(int numberofTiles, int starter) {
		//gets the index number of the position
		int letter = starter/10;
		ArrayList<PlayerTile> holder = new ArrayList<PlayerTile>();
		//System.out.println(letter + " " + starter + " " + aiBoard.get(starter).Position);
		//Adds any direction that fits to the holder list.
		for(int i = 0; i < 4; i++) {
			if(checkCells(numberofTiles,i,starter,aiBoard,true)) {
				//System.out.println(i + " Fits");
				switch(i) {
				case 0:
					holder.add(aiBoard.get(starter + (numberofTiles -1 )));
					//System.out.println(holder.get(holder.size() - 1).Position);
					break;
				case 1:
					holder.add(aiBoard.get(starter - (numberofTiles -1 )));
					//System.out.println(holder.get(holder.size() - 1).Position);
					break;
				case 2:
					holder.add(aiBoard.get(starter - ((numberofTiles -1) * 10)));
					//System.out.println(holder.get(holder.size() - 1).Position);
					break;
				case 3:
					holder.add(aiBoard.get(starter + ((numberofTiles -1) * 10)));
					//System.out.println(holder.get(holder.size() - 1).Position);
					break;
				}
			}else {
				//System.out.println(i + " Does not fit");
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
	private boolean checkCells(int numberOfCells, int direction, int startCell, ArrayList<PlayerTile> array, boolean ships) {
		//initializing vriables
		int number = startCell;
		int previous = number;
		boolean done = true;
		int step = 0;
		String pos = array.get(number).Position;
		if(ships) {
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
			//it repeats these checks for however long the check needs to be.
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
		}else {
			PlayerTile holder = new PlayerTile("");
			for(int i = 0; i < numberOfCells; i++) {
				if(number == -1) {
					return false;
				}
				String letter = pos.substring(0, 1);
				int num = Integer.parseInt(pos.substring(1));
				int k;
				switch(direction) {
				case 0:
					num++;
					break;
				case 1:
					num--;
					break;
				case 2:
					k = holder.transformPosition(letter);
					k--;
					letter = holder.transformIntToLetter(k);
					break;
				case 3:
					k = holder.transformPosition(letter);
					k++;
					letter = holder.transformIntToLetter(k);
					break;
				}
				if(i == (numberOfCells - 1)) {
					PosHolder = pos;
				}else {
					pos = letter + num;
					number = findPos(pos, array);
				}
			}
		}
		return done;
	}
	
	public int findPos(String pos, ArrayList<PlayerTile> board) {
		for(int i = 0; i < board.size(); i++) {
			if(board.get(i).Position.equals(pos)) {
				return i;
			}
		}
		return -1;
	}
}
