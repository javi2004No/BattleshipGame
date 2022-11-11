
public class PlayerTile {
	//The variables of all the tiles
	public String Position;
	//0 no ship
	//1 2-space ship(destroyer)
	//2 3-space ship(submarine)
	//3 3-space ship(cruiser)
	//4 4-space ship(battleship)
	//5 5-space ship(aircraft-carrier)
	public int Ship;
	public boolean BeenHit;
	public double chance;
	//Sets everything to its defualt and takes the position from the function.
	public PlayerTile(String position) {
		Position = position;
		Ship = 0;
		BeenHit = false;
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
	//Gets the name of the ship of the current tile.
	public String getShip() {
		String shipName;
		switch(Ship) {
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
}
