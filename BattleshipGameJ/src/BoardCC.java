
public class BoardCC {
	
	@Override
	public String toString() {
		String brdStr = "";
		//This will create a space
		brdStr += " ";
		for (int z = 0; z < 10; z++) {
			brdStr += " " + z;
		}
		brdStr += "\n";
		//The Return method does
		//Make sure to test, and make it line up
		//Create an array statement
		
		//This is a loop array(?) to create the board grid
		//Always make sure to type the right variable		
		for (int c = 0; c < 10; c++) {
			//This will create a String loop
			//And create a numerical grid as shown
			brdStr += c + "";
			//Create multiple Arrays to make the block patterns
			for (int i = 0; i < 10; i++) {
				brdStr += " .";
			}
			brdStr += "\n";
		}
		
		return brdStr;
	}

}
