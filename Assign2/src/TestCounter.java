
public class TestCounter {
	
	private static String[][] tests = new String [][] {
		new String[] {"C","6","D","4","S","5","C","J","C","3"},
		new String[] {"D","2","S","4","C","6","H","8","D","Q"},
		new String[] {"H","Q","D","K","D","J","S","Q","C","9"},
		new String[] {"C","K","H","A","H","8","H","10","H","3"},
		new String[] {"H","K","H","A","H","8","H","10","H","3"}
	};
	private static int[] results = new int[] {
		9, 0, 8, 4, 5
	};

	public static void main(String[] args) {

		for (int i = 0; i < tests.length; i++) {
			runTest(i);
		}
		
	}
	
	private static void runTest (int i) {
		String[] strArray = tests[i];
		Card starter = new Card(strArray[0], strArray[1]);
		Card[] cardArray = new Card[strArray.length/2];
		int c = 0;
		for (int j = 2; j < strArray.length; j+=2) {
			cardArray[c++] = new Card(strArray[j], strArray[j+1]);
		}
		cardArray[c] = starter;
		
		Counter counter = new Counter(cardArray, starter);
		int act = counter.countPoints();
		int exp = results[i];

		if (act == exp) {
			System.out.println("Test " + (i+1) + " Passed");
		} else {
			System.out.println("Test " + (i+1) + " Failed\tYour score is: " + act + " but it should be: " + exp);
		}
	}

}
