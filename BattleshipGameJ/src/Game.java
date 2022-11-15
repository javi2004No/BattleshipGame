import java.util.ArrayList;

public class Game {
	
	ArrayList<ArrayList<PlayerTile>> player1;
	ArrayList<ArrayList<PlayerTile>> player2;
	int player1boats;
	int player2boats;
	public int play(String action, ArrayList<PlayerTile> defender, int player) {
		int result = 0;
		Ai hold = new Ai(0);
		int num = hold.findPos(action, defender);
		if(defender.get(num).Ship > 0) {
			if(player == 1) {
				int num2 = findPos(defender.get(num).Ship,player1);
				if(num2 == -1) {
					player1.add(new ArrayList<PlayerTile>());
					player1.get(player1.size() - 1).add(defender.get(num));
					result = 1;
				}else {
					player1.get(num2).add(defender.get(num));
					if(player1.get(num2).size() >= shipSize(defender.get(num).Ship)) {
						result = 2;
						player1boats--;
					}
				}
			}else {
				int num2 = findPos(defender.get(num).Ship,player2);
				if(num2 == -1) {
					player2.add(new ArrayList<PlayerTile>());
					player2.get(player2.size() - 1).add(defender.get(num));
					result = 1;
				}else {
					player2.get(num2).add(defender.get(num));
					if(player2.get(num2).size() >= shipSize(defender.get(num).Ship)) {
						result = 2;
						player2boats--;
					}
				}
			}
		}
		return result;
	}
	
	Game(){
		player1 = new ArrayList<ArrayList<PlayerTile>>();
		player2 = new ArrayList<ArrayList<PlayerTile>>();
		player1boats = 5;
		player2boats = 5;
	}
	
	private int findPos(int ship, ArrayList<ArrayList<PlayerTile>> array){
		int pos = -1;
		for(int i = 0; i < array.size(); i++) {
			if(array.get(i).get(0).Ship == ship) {
				return i;
			}
		}
		return pos;
	}
	
	private int shipSize(int ship) {
		switch(ship) {
			case 5:
				return 5;
			case 4:
				return 4;
			case 3:
			case 2:
				return 3;
			case 1:
				return 2;
			default:
				return -1;
				
		}
	}
}
