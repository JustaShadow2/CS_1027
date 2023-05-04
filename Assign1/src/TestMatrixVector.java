
public class TestMatrixVector {

	public static void main(String[] args) {

		Matrix m1 = new Matrix(2, 4, new double[] {4.1, 8.0, 0.5, 2.9, 5.2, 3.0, 9.4, 7.3});
		double[][] m1Array = {{4.1, 8.0, 0.5, 2.9}, {5.2, 3.0, 9.4, 7.3}};
		Matrix m2 = new Matrix(5, 3, new double[] {1.5, 0.9, 6.5, 2.5, 0.8, 7.5, 3.5, 0.7, 8.5, 4.5, 0.6, 9.5, 5.5, 0.5, 10.5});
		double[][] m2Array = {{1.5, 0.9, 6.5}, {2.5, 0.8, 7.5}, {3.5, 0.7, 8.5}, {4.5, 0.6, 9.5}, {5.5, 0.5, 10.5}};

		
		/* ---------- Test 1 [constructor and array getter] ---------- */
		
		try {
			
			double[][] stud_m1Array = m1.getData();
			double[][] stud_m2Array = m2.getData();
			
			if (equalArrays(m1Array, stud_m1Array) && equalArrays(m2Array, stud_m2Array)) {
				System.out.println("Test 1 Passed");
			} else {
				System.out.println("Test 1 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 1 Failed");
		}
		
		/* ---------- Test 2 [row and col num getters] ---------- */
		
		try {
			if (m1.getNumRows() == 2 && m1.getNumCols() == 4 && m2.getNumRows() == 5 && m2.getNumCols() == 3) {
				System.out.println("Test 2 Passed");
			} else {
				System.out.println("Test 2 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 2 Failed");
		}
		
		
		/* ---------- Test 3 [toString] ---------- */
		
		String st1 = "   4.100   8.000   0.500   2.900\n   5.200   3.000   9.400   7.300";

		try {
			
			Matrix m0 = new Matrix(0, 0, new double[] {});
			
			if (m1.toString().trim().equals(st1.trim()) && m0.toString().equals("Empty matrix")) {
				System.out.println("Test 3 Passed");
			} else {
				System.out.println("Test 3 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 3 Failed");
		}
		
		
		/* ---------- Test 4 [getElement and setElement] ---------- */
	
		try {
			
			double tmp1 = m1.getElement(1, 2);
			m1.setElement(1, 2, 4.9);
			double tmp2 = m2.getElement(4, 2);
			m2.setElement(4, 2, 10.0);
			
			if (equals(tmp1, 9.4) && equals(m1.getElement(1, 2), 4.9) && equals(tmp2, 10.5) && equals(m2.getElement(4, 2), 10.0)) {
				System.out.println("Test 4 Passed");
			} else {
				System.out.println("Test 4 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 4 Failed");
		}
		
		/* ---------- Test 5 [transpose] ---------- */
	
		try {
			
			m1.transpose();
			m2.transpose();
			
			if (m1.getNumRows() == 4 && m1.getNumCols() == 2 && m1.getData()[2][0] == 0.5 && m2.getNumRows() == 3 && m2.getNumCols() == 5 && m2.getData()[2][0] == 6.5) {
				System.out.println("Test 5 Passed");
			} else {
				System.out.println("Test 5 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 5 Failed");
		}
		
		/* ---------- Test 6 [multiply by scalar] ---------- */
		
		try {
			
			Matrix s1 = m1.multiply(3.0);
			Matrix s2 = m2.multiply(1.25);
			
			if (equals(s1.getData()[0][0], 12.30) && equals(s1.getData()[1][1], 9.0) && equals(s2.getData()[0][0], 1.875) && equals(s2.getData()[2][2], 10.625)) {
				System.out.println("Test 6 Passed");
			} else {
				System.out.println("Test 6 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 6 Failed");
		}

		/* ---------- Test 7 [multiply by matrix] ---------- */
		
		Matrix m3 = new Matrix(2, 2, new double[] {4.0, 3.0, 0.5, 5.0});
		Matrix m4 = new Matrix(5, 2, new double[] {2.25, 1.0, 3.7, 9.2, 4.75, 0.25, 2.3, 8.5, 3.95, 1.6});

		double[][] p1Array = {{17.5, 27.0}, {4.5, 26.5}};
		double[][] p2Array = {{9.5, 11.75}, {19.4, 57.1}, {19.125, 15.5}, {13.45, 49.4}, {16.6, 19.85}};
		
		try {
			Matrix p1 = m3.multiply(m3);
			Matrix p2 = m4.multiply(m3);
			Matrix p3 = m3.multiply(m4);
			
			if (equalArrays(p1.getData(), p1Array) && equalArrays(p2.getData(), p2Array) && p3 == null) {
				System.out.println("Test 7 Passed");
			} else {
				System.out.println("Test 7 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 7 Failed");
		}
		
		/* ---------- Test 8 [vector] ---------- */
		
		Vector v1 = new Vector(4, new double[] {2.5, 0.5, 7.0, 3.5});
		double[][] v1Array = {{2.5, 0.5, 7.0, 3.5}};
				
		try {

			if (equalArrays(v1.getData(), v1Array)) {
				System.out.println("Test 8 Passed");
			} else {
				System.out.println("Test 8 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 8 Failed");
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
					//System.out.println("not equal  " + A[i][j] + " != " + B[i][j]);
					return false;
				}
			}
		}
		return true;
	}
	
}
