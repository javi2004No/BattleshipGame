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
	//private ArrayList<PlayerTile> record;
	//The lowest boat that the enimie could have.
	public int lowestBoat;
	//Holds the pos for a function;
	private String PosHolder;
	//Hold the direction the Ai is currentlt going in.
	//private ArrayList<Integer> directions;
	//This is the main function that is called when you want the Ai to do something.
	//It will return a string which will be the position of the attack. Eg: F1 or G7.
	private boolean switchedDirections;
	private ArrayList<ArrayList<PlayerTile>> hello;
	private ArrayList<Boolean> morethanonespace;
	private boolean attackedOnce;
	private int indexofAttack;
	private ArrayList<ArrayList<PlayerTile>> memory;
	private ArrayList<ArrayList<Integer>> directionmem;
	private String name;
	private String actttion;
	public String action() {
		String action = "";
		switch(Level) {
			case 1:
				action = EasyAi();
				break;
		}
		return action;
	}
	
	public void cleanup(String action, boolean hit, boolean sunk, int shipSunk) {
		Random rand = new Random();
		actttion = action;
		if(hit) {
				if(memory.size() < 1) {
					memory.add(new ArrayList<PlayerTile>());
					directionmem.add(new ArrayList<Integer> ());
					indexofAttack = 0;
					memory.get(indexofAttack).add(aiAttack.get(findPos(action,aiAttack)));
					memory.get(indexofAttack).get(memory.get(indexofAttack).size() - 1).Ship = shipSunk;
					morethanonespace.add(false);
				}else if(memory.get(indexofAttack).get(0).Ship != shipSunk){
//					System.out.println(name);
//					System.out.println("ShipHit");
//					System.out.println(shipSunk);
//					System.out.println("Mem");
//					System.out.println(memory.get(indexofAttack).get(0).Ship);
					boolean found = false;
					int index = -1;
					for (int i = 0; i < memory.size(); i++) {
						if(memory.get(i).get(0).Ship == shipSunk) {
							found = true;
							index = i;
							break;
						}
					}
					if(!found) {
						memory.add(new ArrayList<PlayerTile>());
						memory.get(memory.size() -1).add(aiAttack.get(findPos(action,aiAttack)));
						memory.get(memory.size()- 1).get(memory.get(memory.size() - 1).size() - 1).Ship = shipSunk;
						directionmem.add(new ArrayList<Integer> ());
						morethanonespace.add(false);
					}else {
						memory.get(index).add(aiAttack.get(findPos(action,aiAttack)));
						memory.get(index).get(memory.get(index).size() - 1).Ship = shipSunk;
						if(memory.get(index).size() == 2) {
							int letternum1 = memory.get(index).get(0).transformPosition(memory.get(index).get(0).Position.substring(0,1));
							int numnum1 = Integer.parseInt(memory.get(index).get(0).Position.substring(1));
							int letternum2 = memory.get(index).get(0).transformPosition(memory.get(index).get(1).Position.substring(0,1));
							int numnum2 = Integer.parseInt(memory.get(index).get(1).Position.substring(1));
							if(letternum1 != letternum2) {
								if(letternum1 > letternum2) {
									directionmem.get(index).add(2);
								}else {
									directionmem.get(index).add(3);
								}
							}else if(numnum1 != numnum2) {
								if(numnum1 > numnum2) {
									directionmem.get(index).add(0);
								}else {
									directionmem.get(index).add(1);
								}
							}
							morethanonespace.set(index, true) ;
						}
					}
					switchdirections();
				}else {
					memory.get(indexofAttack).add(aiAttack.get(findPos(action,aiAttack)));
					memory.get(indexofAttack).get(memory.get(indexofAttack).size() - 1).Ship = shipSunk;
				}
			
			if(sunk) {
				if(shipSunk == memory.get(indexofAttack).get(0).Ship) {
					for(int i = 0; i < memory.get(indexofAttack).size(); i++) {
						aiAttack.remove(findPos(memory.get(indexofAttack).get(i).Position,aiAttack));
					}
					hello.add(memory.get(indexofAttack));
					memory.remove(indexofAttack);
					directionmem.remove(indexofAttack);
					morethanonespace.remove(indexofAttack);
					if(memory.size() < 1) {
						indexofAttack = -1;
					}else {
						indexofAttack = rand.nextInt(memory.size());
					}
					attackedOnce = false;
				}else {
					int index = -1;
					for (int i = 0; i < memory.size(); i++) {
						if(memory.get(i).get(0).Ship == shipSunk) {
							index = i;
							break;
						}
					}
					for(int i = 0; i < memory.get(index).size(); i++) {
						aiAttack.remove(findPos(memory.get(index).get(i).Position,aiAttack));
					}
					hello.add(memory.get(index));
					memory.remove(index);
					directionmem.remove(index);
				}
			}
		}else {
			if(memory.size() > 0 && indexofAttack >= 0) {
				switchdirections();
			}else if(memory.size() > 0){
				System.out.println("This should not be possible");
			}else {
				aiAttack.remove(findPos(action,aiAttack));
			}
		}
		
		
//		System.out.println(hit);
//		PlayerTile holder = new PlayerTile("");
//		if(hit) {
//			record.add(aiAttack.get(findPos(action,aiAttack)));
//			record.get(record.size() - 1).BeenHit = true;
//			System.out.println("I am here");
//			if(sunk) {
//				if(holder.GetSize(shipSunk) == record.size()) {
//					for(int i = 0; i < record.size(); i++) {
//						aiAttack.remove(findPos(record.get(i).Position,aiAttack));
//					}
//					record.clear();
//					directions.clear();
//					atteckedonce = false;
//				}else {
//					switch(directions.get(directions.size()-1)) {
//						case 0:
//							if(!directions.contains(1)) {
//								directions.add(1);
//							}else if(!directions.contains(2)) {
//								directions.add(2);
//							}else if(!directions.contains(3)) {
//								directions.add(3);
//							}else {
//								System.out.println("The impossible is possible.");
//							}
//							break;
//						case 1:
//							if(!directions.contains(0)) {
//								directions.add(0);
//							}else if(!directions.contains(3)) {
//								directions.add(3);
//							}else if(!directions.contains(2)) {
//								directions.add(2);
//							}else {
//								System.out.println("The impossible is possible.");
//							}
//							break;
//						case 2:
//							if(!directions.contains(3)) {
//								directions.add(3);
//							}else if(!directions.contains(0)) {
//								directions.add(0);
//							}else if(!directions.contains(1)) {
//								directions.add(1);
//							}else {
//								System.out.println("The impossible is possible.");
//							}
//							break;
//						case 3:
//							if(!directions.contains(2)) {
//								directions.add(2);
//							}else if(!directions.contains(1)) {
//								directions.add(1);
//							}else if(!directions.contains(0)) {
//								directions.add(0);
//							}else {
//								System.out.println("The impossible is possible.");
//							}
//							break;
//					}
//					switchedDirections = true;
//					for(int i = record.size() - holder.GetSize(shipSunk); i < record.size(); i++) {
//						aiAttack.remove(findPos(record.get(i).Position,aiAttack));
//					}
//					for(int i = 0; i < holder.GetSize(shipSunk); i++) {
//						record.remove(record.size()-1);
//					}
//				}
//			}
//		}else {
//			System.out.println("No here");
//			if(record.size() > 0) {
//				System.out.println("Right here");
//				aiAttack.remove(findPos(action,aiAttack));
//				switch(directions.get(directions.size()-1)) {
//				case 0:
//					if(!directions.contains(1)) {
//						directions.add(1);
//					}else if(!directions.contains(2)) {
//						directions.add(2);
//					}else if(!directions.contains(3)){
//						directions.add(3);
//					}else {
//						System.out.println("The impossible is possible.");
//					}
//					break;
//				case 1:
//					if(!directions.contains(0)) {
//						directions.add(0);
//					}else if(!directions.contains(3)) {
//						directions.add(3);
//					}else if(!directions.contains(2)){
//						directions.add(2);
//					}else {
//						System.out.println("The impossible is possible.");
//					}
//					break;
//				case 2:
//					if(!directions.contains(3)) {
//						directions.add(3);
//					}else if(!directions.contains(0)) {
//						directions.add(0);
//					}else if(!directions.contains(1)){
//						directions.add(1);
//					}else {
//						System.out.println("The impossible is possible.");
//					}
//					break;
//				case 3:
//					if(!directions.contains(2)) {
//						directions.add(2);
//					}else if(!directions.contains(1)) {
//						directions.add(1);
//					}else if(!directions.contains(0)){
//						directions.add(0);
//					}else {
//						System.out.println("The impossible is possible.");
//					}
//					break;
//			}
//			switchedDirections = true;
//			}else {
//				System.out.println("why am i here");
//				aiAttack.remove(findPos(action,aiAttack));
//			}
//		}
	}
	
	private void switchdirections() {
			switch(directionmem.get(indexofAttack).get(directionmem.get(indexofAttack).size() - 1)) {
			case 0:
				if(!directionmem.get(indexofAttack).contains(1)) {
					directionmem.get(indexofAttack).add(1);
				}else if(!directionmem.get(indexofAttack).contains(2)) {
					directionmem.get(indexofAttack).add(2);
				}else if(!directionmem.get(indexofAttack).contains(3)) {
					directionmem.get(indexofAttack).add(3);
				}else {
					System.out.println("The impossible is possible.");
				}
				break;
			case 1:
				if(!directionmem.get(indexofAttack).contains(0)) {
					directionmem.get(indexofAttack).add(0);
				}else if(!directionmem.get(indexofAttack).contains(3)) {
					directionmem.get(indexofAttack).add(3);
				}else if(!directionmem.get(indexofAttack).contains(2)) {
					directionmem.get(indexofAttack).add(2);
				}else {
					System.out.println("The impossible is possible.");
				}
				break;
			case 2:
				if(!directionmem.get(indexofAttack).contains(3)) {
					directionmem.get(indexofAttack).add(3);
				}else if(!directionmem.get(indexofAttack).contains(0)) {
					directionmem.get(indexofAttack).add(0);
				}else if(!directionmem.get(indexofAttack).contains(1)) {
					directionmem.get(indexofAttack).add(1);
				}else {
					System.out.println("The impossible is possible.");
				}
				break;
			case 3:
				if(!directionmem.get(indexofAttack).contains(2)) {
					directionmem.get(indexofAttack).add(2);
				}else if(!directionmem.get(indexofAttack).contains(1)) {
					directionmem.get(indexofAttack).add(1);
				}else if(!directionmem.get(indexofAttack).contains(0)) {
					directionmem.get(indexofAttack).add(0);
				}else {
					System.out.println("The impossible is possible.");
				}
				break;
		}
		switchedDirections = true;
	}
	
	//This function here repersents the algorithim for the easy AI.
	//The easy AI will have two modes one mode will it will radomly shoot around the map making sure it does not shoot a placed it allready shot
	//and mode two where it hit something and now it will hit the surrundings areas of the hit intill
	//it sinks the ship.
	//It will again return a string with the position of the shot.
	private String EasyAi() {
		Random random = new Random();
		String action = "";
		ArrayList<Integer> holderint = new ArrayList<Integer>();
		ArrayList<PlayerTile> holder = new ArrayList<PlayerTile>();
		PlayerTile holder2 = new PlayerTile("");
		int SIZE = 0;
		if(indexofAttack >= 0) {
			SIZE = memory.get(indexofAttack).size();
		}
	if(SIZE == 1 && !attackedOnce) {
			for(int i = 0; i < 4; i++) {
				if(checkCells(2,i,findPos(memory.get(indexofAttack).get(0).Position,aiAttack),aiAttack,false)) {
					//System.out.println(PosHolder);
					holder.add(aiAttack.get(findPos(PosHolder,aiAttack)));
					directionmem.get(indexofAttack).add(i);
				}else {
					holderint.add(i);
				}
			}
			int num = random.nextInt(holder.size());
			action = holder.get(num).Position;
			num = directionmem.get(indexofAttack).get(num);
			directionmem.get(indexofAttack).clear();
			directionmem.get(indexofAttack).addAll(holderint);
			directionmem.get(indexofAttack).add(num);
			attackedOnce = true;
		}else if(SIZE > 0) {
			
			int num = Integer.parseInt(memory.get(indexofAttack).get(memory.get(indexofAttack).size() - 1).Position.substring(1));
			int letnum = holder2.transformPosition(memory.get(indexofAttack).get(memory.get(indexofAttack).size()-1).Position.substring(0,1));
			int numst = Integer.parseInt(memory.get(indexofAttack).get(0).Position.substring(1));
			int letnumst = holder2.transformPosition(memory.get(indexofAttack).get(0).Position.substring(0,1));
			boolean found = false;
			do {
				int numh;
				int letnumh;
				if(switchedDirections || morethanonespace.get(indexofAttack)) {
					numh = numst;
					letnumh = letnumst;
					 morethanonespace.set(indexofAttack, false);
				}else {
					numh = num;
					letnumh = letnum;
				}
				switch(directionmem.get(indexofAttack).get(directionmem.get(indexofAttack).size() -1 )) {
					case 0:
						numh++;
						break;
					case 1:
						numh--;
						break;
					case 2:
						letnumh--;
						break;
					case 3:
						letnumh++;
						break;
				}
				if(findPos(holder2.transformIntToLetter(letnumh) + numh, memory.get(indexofAttack)) == -1) {
					if(findPos(holder2.transformIntToLetter(letnumh) + numh, aiAttack) != -1) {
						num = numh;
						letnum = letnumh;
						found = true;
					}else {
							switchdirections();	
					}
				}else{
					if(switchedDirections) {
						numst = numh;
						letnumst = letnumh;
					}else {
						num = numh;
						letnum = letnumh;	
					}
				}
				//System.out.println("Stuck?");
			} while(!found);
			switchedDirections = false;
			action = aiAttack.get(findPos(holder2.transformIntToLetter(letnum) + num, aiAttack)).Position;			
//			int num = record.size() - 1;
//			if(record.get(num).BeenHit) {
//				String letter1 = record.get(num).Position.substring(0,1);
//				int num1 = Integer.parseInt(record.get(num).Position.substring(1));
//				int k;
//				switch(directions.get(directions.size()-1)) {
//					case 0:
//						num1++;
//						break;
//					case 1:
//						num1--;
//						break;
//					case 2:
//						k = holder2.transformPosition(letter1);
//						k--;
//						letter1 = holder2.transformIntToLetter(k);
//						break;
//					case 3:
//						k = holder2.transformPosition(letter1);
//						k++;
//						letter1 = holder2.transformIntToLetter(k);
//						break;
//				}
//				//System.out.println(record);
//				action = aiAttack.get(findPos((letter1 + num1),aiAttack)).Position;
//			}else{
//				for(int i = 0; i < 4; i++) {
//					if(findint(i,directions) != -1) {
//						holderint.add(i);
//					}
//				}
//				directions.add(holderint.get(random.nextInt(holderint.size())));
//				String letter1 = record.get(0).Position.substring(0,1);
//				int num1 = Integer.parseInt(record.get(0).Position.substring(1));
//				int k;
//				switch(directions.get(directions.size()-1)) {
//					case 0:
//						num1++;
//						break;
//					case 1:
//						num1--;
//						break;
//					case 2:
//						k = holder2.transformPosition(letter1);
//						k--;
//						letter1 = holder2.transformIntToLetter(k);
//						break;
//					case 3:
//						k = holder2.transformPosition(letter1);
//						k++;
//						letter1 = holder2.transformIntToLetter(k);
//						break;
//				}
//				//System.out.println(record);
//				action = aiAttack.get(findPos((letter1 + num1),aiAttack)).Position;
//			}
			
			
		}else{
			int num = random.nextInt(aiAttack.size());
			action = aiAttack.get(num).Position;
		}
		return action;
	}
	
	//This is the constructor of the AI it will initialize all the variables.
	public Ai(int level, String nam, boolean holde) {
		if(!holde) {
			aiBoard = new ArrayList<PlayerTile>();
			aiAttack = new ArrayList<PlayerTile>();
			PlayerTile holder = new PlayerTile("");
			holder.createBoard(aiBoard);
			holder.createBoard(aiAttack);
			Level = level;
			//Calls the function to create the AI's ships.
			createShips();
			boatsLeft = 5;
			//record = new ArrayList<PlayerTile>();
			lowestBoat = 2;
			PosHolder = "";
			//directions = new ArrayList<Integer>();
			switchedDirections = false;
			attackedOnce= false;
			memory = new ArrayList<ArrayList<PlayerTile>>();
			directionmem = new ArrayList<ArrayList<Integer>>();
			indexofAttack = -1;
			name = nam;
			actttion = "";
			hello = new ArrayList<ArrayList<PlayerTile>>();
			morethanonespace = new ArrayList<Boolean> ();
		}
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
	//	int letter = starter/10;
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
		if(number == -1) {
			System.out.println("How?");
			System.out.println(name);
			System.out.println(actttion);
			System.out.println("Why?");
			}
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
	
//	private int findint(int inte, ArrayList<Integer> array) {
//		for(int i = 0; i < array.size(); i++) {
//			if(array.get(i).equals(inte)) {
//				return i;
//			}
//		}
//		return -1;
//	}
	public int findPos(String pos, ArrayList<PlayerTile> board) {
		for(int i = 0; i < board.size(); i++) {
			if(board.get(i).Position.equals(pos)) {
				return i;
			}
		}
		return -1;
	}
}
