
public class Test {
	public void testvariables() {
		PlayerTile[] tiles = new PlayerTile[100];
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
				tiles[(i*10) + i2] = new PlayerTile(paramanter);
				paramanter= holder; 
			}
		}
		for(int i = 0; i < 100; i++) {
			System.out.println(tiles[i].Position);
		}
	}
	
	public void testBoard() {
		Board_Code test = new Board_Code();
		test.drawBoard();
	}
}
