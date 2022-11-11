import java.util.ArrayList;

//This class will store all the values for the player.
public class Player {
	public int boatsLeft;
	public ArrayList<PlayerTile> playerBoard;
	public ArrayList<PlayerTile> playerAttack;
	
//Constructor creates the board for the players.
	Player(){
		boatsLeft = 5;
		playerBoard = new ArrayList<PlayerTile>();
		playerAttack = new ArrayList<PlayerTile>();
		PlayerTile holder = new PlayerTile("");
		holder.createBoard(playerBoard);
		holder.createBoard(playerAttack);
	}
		
}
