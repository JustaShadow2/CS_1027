
public class TestPriorityQueue {

	public static void main(String[] args) {
		boolean debugging = true;
		DLPriorityQueue<String> prioQueue = new DLPriorityQueue<String>();
		boolean testPassed;
		String s;

		System.out.println("Tests for class PriorityQueue");
		System.out.println("=============================");

		// Test 1: size, isEmpty, removeMin
		// --------------------------------
		testPassed = true;
		try {
			if (!prioQueue.isEmpty())
				testPassed = false;
			if (prioQueue.size() != 0)
				testPassed = false;
			s = prioQueue.removeMin();
			System.out.println("Test 1 failed");
		} catch (EmptyPriorityQueueException e) {
			if (testPassed)
				System.out.println("Test 1 passed");
			else
				System.out.println("Test 1 failed");
		} catch (Exception e) {
			System.out.println("Test 1 failed");
		}

		// Test 2: add, removeMin, updatePriority.
		// -----------------------------------
		testPassed = true;
		prioQueue.add("data1", 1.0);
		try {
			prioQueue.updatePriority("data1", 2.0);
			s = prioQueue.removeMin();
			if (!s.equals("data1"))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
			if (debugging)
				System.out.println(e.getMessage());
		}

		try {
			prioQueue.updatePriority("data1", 1.0);
			testPassed = false;
		} catch (InvalidElementException e) {
			;
		} catch (Exception e) {
			testPassed = false;
			if (debugging)
				System.out.println(e.getMessage());
		}

		if (testPassed)
			System.out.println("Test 2 passed");
		else
			System.out.println("Test 2 failed");

		// Test 3: add, removeMin, size
		// ---------------------------
		prioQueue = new DLPriorityQueue<String>();
		testPassed = true;
		try {
			for (int i = 0; i < 1000; ++i)
				prioQueue.add("data" + i, (double) ((i + 1) % 10) + (double) i / 1000.0);

			for (int i = 0; i < 20; ++i) {
				s = prioQueue.removeMin();
				if (!s.equals("data" + (9 + i * 10)))
					testPassed = false;
			}

			if (prioQueue.size() != 980)
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
			if (debugging)
				System.out.println(e.getMessage());
		}

		if (testPassed)
			System.out.println("Test 3 passed");
		else
			System.out.println("Test 3 failed");

		// Test 4: add, updatePriority
		// ---------------------------
		prioQueue = new DLPriorityQueue<String>();
		testPassed = true;
		try {
			for (int i = 1000; i > 0; --i)
				prioQueue.add("data" + i, (double) ((i + 1) % 10) + (double) i / 1000.0);

			for (int i = 1001; i < 2000; ++i)
				prioQueue.add("data" + i, (double) ((i + 1) % 10) + (double) i / 1000.0);

			for (int i = 0; i < 20; ++i) {
				prioQueue.updatePriority("data" + (9 + i * 10), 1.0 + i);
			}

			s = prioQueue.removeMin();
			if (!s.equals("data209"))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
			if (debugging)
				System.out.println(e.getMessage());
		}

		try {
			prioQueue.updatePriority("data0", 0.0);
			testPassed = false;
		} catch (InvalidElementException e) {
			;
		}

		if (testPassed)
			System.out.println("Test 4 passed");
		else
			System.out.println("Test 4 failed");

		// Test 5: toString
		// ----------------
		testPassed = true;
		prioQueue = new DLPriorityQueue<String>();
		String[] testData = { "d1", "d2", "d3", "d4", "d5" };
		double[] prioData = { 1.0, 5.0, 2.0, 0.3, 4.0 };
		try {
			for (int i = 0; i < testData.length; ++i)
				prioQueue.add(testData[i], prioData[i]);
			s = prioQueue.toString();
			if (!s.equals("d4d1d3d5d2"))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
			if (debugging)
				System.out.println(e.getMessage());
		}

		if (testPassed)
			System.out.println("Test 5 passed");
		else
			System.out.println("Test 5 failed");

		// Test 6: LinkedList
		// ----------------
		testPassed = true;
		prioQueue = new DLPriorityQueue<String>();
		double[] answer6 = { 0.3, 1.0, 2.0, 4.0, 5.0 };
		try {
			for (int i = 0; i < testData.length; ++i)
				prioQueue.add(testData[i], prioData[i]);
			DLinkedNode<String> rear = prioQueue.getRear();
			DLinkedNode<String> current = rear;
			int i = 4;
			while (current != null) {
				if (current.getPriority() != answer6[i])
					testPassed = false;
				if (i == 4 && current.getNext() != null)
					testPassed = false;
				else if (i == 0 && current.getPrev() != null)
					testPassed = false;
				if (current.getNext() != null && current.getNext().getPriority() != answer6[i + 1])
					testPassed = false;
				if (current.getPrev() != null && current.getPrev().getPriority() != answer6[i - 1])
					testPassed = false;
				--i;
				current = current.getPrev();
			}
		} catch (Exception e) {
			testPassed = false;
			if (debugging)
				System.out.println(e.getMessage());
		}

		if (testPassed)
			System.out.println("Test 6 passed");
		else
			System.out.println("Test 6 failed");
			
		// Test 7: LinkedList, updatePriority
		// ----------------
		testPassed = true;
		prioQueue = new DLPriorityQueue<String>();
		double[] answer7 = { 0.1, 0.3, 4.0, 6.0, 10.0 };
		try {
			for (int i = 0; i < testData.length; ++i)
				prioQueue.add(testData[i], prioData[i]);
			prioQueue.updatePriority("d2",0.1);
			prioQueue.updatePriority("d1",10.0);
			prioQueue.updatePriority("d3",6.0);
			DLinkedNode<String> rear = prioQueue.getRear();
			DLinkedNode<String> current = rear;
			int i = 4;
			while (current != null) {
				if (current.getPriority() != answer7[i])
					testPassed = false;
				if (i == 4 && current.getNext() != null)
					testPassed = false;
				else if (i == 0 && current.getPrev() != null)
					testPassed = false;
				if (current.getNext() != null && current.getNext().getPriority() != answer7[i + 1])
					testPassed = false;
				if (current.getPrev() != null && current.getPrev().getPriority() != answer7[i - 1])
					testPassed = false;
				--i;
				current = current.getPrev();
			}
		} catch (Exception e) {
			testPassed = false;
			if (debugging)
				System.out.println(e.getMessage());
		}

		if (testPassed)
			System.out.println("Test 7 passed");
		else
			System.out.println("Test 7 failed");			

	}

}