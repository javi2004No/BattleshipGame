

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Initialize the array
		Test test = new Test();
		//test.testBoard();
		//test.testvariables();
		//Quick way to check just how many turns it takes on avarage
		int counter = 0;
		int errorcounter = 0;
		int total = 0;
		for(int i = 0; i < 1000000; i++) {
			try {
				counter += test.testAi();
				total++;
				System.out.println("Game " + i + " Done");
			}catch(Exception e) {
				errorcounter++;
				System.out.println("Error on game " + i);
			}
		}
		int avrg = counter / total;
		System.out.println("The avarage number of turns it took for one gamme out of 200 turns is: " + avrg + " turns");
		System.out.println("The amount of errors in 1000000 games is " + errorcounter);
		//ClickRegistering j = new ClickRegistering();
	}

}
