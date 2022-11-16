

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Initialize the array
		Test test = new Test();
		//test.testBoard();
		//test.testvariables();
		//Quick way to check just how many turns it takes on avarage
		int counter = 0;
		for(int i = 0; i < 1; i++) {
			counter += test.testAi();
		}
		int avrg = counter / 1;
		System.out.println("The avarage number of turns it took for one gamme out of 200 turns is: " + avrg + " turns");
		
		//ClickRegistering j = new ClickRegistering();
	}

}
