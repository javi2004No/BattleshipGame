import java.util.ArrayList;

//This class will store all the values for the player.
public class Player {
	//This is the defending board.
	//This is the board that you pass to the play function when this player is defending.
	public ArrayList<PlayerTile> playerBoard;
	//This is the attacking board
	public ArrayList<PlayerTile> playerAttack;
	
//Constructor creates the board for the players.
	Player(){
		playerBoard = new ArrayList<PlayerTile>();
		playerAttack = new ArrayList<PlayerTile>();
		PlayerTile holder = new PlayerTile("");
		holder.createBoard(playerBoard);
		holder.createBoard(playerAttack);
	}
		
}
