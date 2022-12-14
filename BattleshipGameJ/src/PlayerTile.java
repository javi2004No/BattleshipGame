import java.util.ArrayList;

public class PlayerTile {
	//The variables of all the tiles
	//The position stored as a string for example "F1" or "G4".
	public String Position;
	//0 no ship
	//1 2-space ship(destroyer)
	//2 3-space ship(submarine)
	//3 3-space ship(cruiser)
	//4 4-space ship(battleship)
	//5 5-space ship(aircraft-carrier)
	public int Ship;
	//If the tile has been hit or not
	public int BeenHit;
	//This is for the AI you dont need to worry about this.
	public double chance;
	//Is it first, last, or in the middle.
	//0 middle
	//1 first
	//2 last
	public int where;
	//Is it horizantal
	public boolean horizantal;
	//Sets everything to its defualt and takes the position from the function.
	public PlayerTile(String position) {
		Position = position;
		Ship = 0;
		//0 not hit
		//1 miss
		//2 hit
		BeenHit = 0;
	}
	//Transform the currosponding letter to its index number
	public int transformPosition(String position) {
		int output = -1;
		switch(position) {
		case "A":
			output = 0;
			break;
		case "B":
			output = 1;
			break;
		case "C":
			output = 2;
			break;
		case "D":
			output = 3;
			break;
		case "E":
			output = 4;
			break;
		case "F":
			output = 5;
			break;
		case "G":
			output = 6;
			break;
		case "H":
			output = 7;
			break;
		case "I":
			output = 8;
			break;
		case "J":
			output = 9;
			break;
		}
		return output;
	}
	
	//Transforms a specific index number to its corresponding letter.
	public String transformIntToLetter(int input) {
		String output = "";
		switch(input) {
		case 0:
			output = "A";
			break;
		case 1:
			output = "B";
			break;
		case 2:
			output = "C";
			break;
		case 3:
			output = "D";
			break;
		case 4:
			output = "E";
			break;
		case 5:
			output = "F";
			break;
		case 6:
			output = "G";
			break;
		case 7:
			output = "H";
			break;
		case 8:
			output = "I";
			break;
		case 9:
			output = "J";
			break;
		}
		return output;
	}
	
	public String getShip(int ship) {
		String shipName;
		switch(ship) {
		  case 1:
			  shipName = "destroyer";
			  break;
		  case 2:
			  shipName = "submarine";
			  break;
		  case 3:
			  shipName = "cruiser";
			  break;
		  case 4:
			  shipName = "battleship";
			  break;
		  case 5:
			  shipName = "aircraft carrier";
			  break;
			  default:
				  shipName = "Ship does not exist";
				  break;
		}
		return shipName;
	}
	
	//Gets the name of the ship of the current tile.
	public String getShip() {
		return getShip(Ship);
	}
	
	//Gets the size of either the ship the current tile has or the ship that you want.
	public int GetSize(int ship) {
		int size = 0;
		switch(ship) {
			case 1:
				size = 2;
				break;
			case 2:
			case 3:
				size = 3;
				break;
			case 4:
			case 5:	
				size = ship;
				break;
		}
		return size;
	}
	
	public int GetSize() {
		return GetSize(Ship);
	}
	
	//A function that will take in an empty board and than add in all the 100 tiles.
	public void createBoard(ArrayList<PlayerTile> board){
		String paramanter = "";
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
				board.add(new PlayerTile(paramanter));
				paramanter= holder; 
			}
		}
	}
}
