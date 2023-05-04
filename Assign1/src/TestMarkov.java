
public class TestMarkov {

	public static void main(String[] args) {

		/* ---------- Test 1 [invalid row sums] ---------- */
		
		try {
			
			Vector sv1 = new Vector(4, new double[] {0.4, 0.1, 0.5, 0.2});
			Matrix tm1 = new Matrix(4, 4, new double[] {0.25, 0.2, 0.25, 0.3, 0.2, 0.3, 0.2, 0.3, 0.25, 0.25, 0.4, 0.1, 0.3, 0.3, 0.1, 0.3});
			MarkovChain markov1 = new MarkovChain(sv1, tm1);

			Vector sv2 = new Vector(4, new double[] {0.2, 0.1, 0.5, 0.2});
			Matrix tm2 = new Matrix(4, 4, new double[] {0.5, 0.4, 0.1, 0.0, 0.0, 0.2, 0.5, 0.3, 0.3, 0.0, 0.2, 0.6, 0.2, 0.4, 0.3, 0.1});
			MarkovChain markov2 = new MarkovChain(sv2, tm2);

			if (!markov1.isValid() && !markov2.isValid()) {
				System.out.println("Test 1 Passed");
			} else {
				System.out.println("Test 1 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 1 Failed");
		}
		
		/* ---------- Test 2 [invalid matrix sizes] ---------- */
		
		try {
			Vector sv = new Vector(3, new double[] {0.3, 0.5, 0.2});
			Matrix tm = new Matrix(3, 2, new double[] {0.5, 0.0, 0.5, 0.2, 0.7, 0.1});
			MarkovChain markov = new MarkovChain(sv, tm);

			if (!markov.isValid() && markov.computeProbabilityMatrix(1) == null) {
				System.out.println("Test 2 Passed");
			} else {
				System.out.println("Test 2 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 2 Failed");
		}
		
		
		/* ---------- Test 3 [computeProbabilityMatrix] ---------- */

		try {
			Vector sv = new Vector(3, new double[] {0.3, 0.5, 0.2});
			Matrix tm = new Matrix(3, 3, new double[] {0.5, 0.0, 0.5, 0.2, 0.7, 0.1, 0.1, 0.4, 0.5});
			MarkovChain markov = new MarkovChain(sv, tm);
			
			Matrix m1 = markov.computeProbabilityMatrix(1);
			Matrix m2 = markov.computeProbabilityMatrix(2);
			Matrix m3 = markov.computeProbabilityMatrix(3);
			
			double[][] r1 = {{0.270, 0.430, 0.300}};
			double[][] r2 = {{0.251, 0.421, 0.328}};
			double[][] r3 = {{0.243, 0.426, 0.332}};

			if (markov.isValid() && equalArrays(m1.getData(), r1) && equalArrays(m2.getData(), r2) && equalArrays(m3.getData(), r3)) {
				System.out.println("Test 3 Passed");
			} else {
				System.out.println("Test 3 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 3 Failed");
		}
		
		
		/* ---------- Test 4 [computeProbabilityMatrix] ---------- */
	
		try {
			
			Vector sv = new Vector(4, new double[] {0.0, 1.0, 0.0, 0.0});
			Matrix tm = new Matrix(4, 4, new double[] {0.2, 0.6, 0.1, 0.1, 0.0, 0.3, 0.7, 0.0, 0.9, 0.05, 0.0, 0.05, 0.4, 0.1, 0.3, 0.2});
			MarkovChain markov = new MarkovChain(sv, tm);
			
			Matrix m1 = markov.computeProbabilityMatrix(15);
			Matrix m2 = markov.computeProbabilityMatrix(16);
			Matrix m3 = markov.computeProbabilityMatrix(17);

			double[][] r = {{0.342, 0.321, 0.277, 0.060}};

			if (markov.isValid() && equalArrays(m1.getData(), r) && equalArrays(m2.getData(), r) && equalArrays(m3.getData(), r)) {
				System.out.println("Test 4 Passed");
			} else {
				System.out.println("Test 4 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 4 Failed");
		}
		
	}

	
	private static boolean equals (double a, double b) {
		// Compares two doubles to see if they equal, taking rounding errors into consideration.
		return Math.abs(a-b) < 0.001;
	}
	
	private static boolean equalArrays (double[][] A, double[][] B) {
		// Compares two arrays of doubles to see if they are identical.
		if (A.length != B.length || A[0].length != B[0].length) {
			// If the dimensions are different, immediately return false.
			return false;
		}
		// The dimensions are the same, so now loop through all the elements and check if any is different between the two arrays.
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (!equals(A[i][j],B[i][j])) {
					System.out.println("not equal  " + A[i][j] + " != " + B[i][j]);
					return false;
				}
			}
		}
		return true;
	}
	
}
