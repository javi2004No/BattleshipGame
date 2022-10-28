
public class PlayerTile {
	public String Position;
	//0 no ship
	//1 2-space ship(destroyer)
	//2 3-space ship(submarine)
	//3 3-space ship(cruiser)
	//4 4-space ship(battleship)
	//5 5-space ship(aircraft-carrier)
	public int Ship;
	public boolean BeenHit;
	
	public PlayerTile(String position) {
		Position = position;
		Ship = 0;
		BeenHit = false;
	}
	
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
