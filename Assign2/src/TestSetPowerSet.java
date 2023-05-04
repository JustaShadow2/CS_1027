
public class TestSetPowerSet {

	public static void main(String[] args) {
		
		/* ---------- Test 1 [Set getLength and contains] ---------- */
		
		try {
			
			Set<String> sSet = new Set<String>();
			sSet.add("spring");
			sSet.add("summer");
			sSet.add("autumn");
			sSet.add("winter");
			
			if (sSet.getLength() == 4 && sSet.contains("summer") && !sSet.contains("fall")) {
				System.out.println("Test 1 Passed");
			} else {
				System.out.println("Test 1 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 1 Failed");
		}
		
		/* ---------- Test 2 [Set getLength and toString] ---------- */
		
		try {
			
			Set<Integer> iSet = new Set<Integer>();
			iSet.add(14);
			iSet.add(35);
			iSet.add(9);
			iSet.add(12);
			iSet.add(28);
			iSet.add(49);
			iSet.add(17);
			
			String str = iSet.toString();

			if (iSet.getLength() == 7 && str.contains("14") && str.contains("12") && str.contains("17") && str.split(" ").length >= 6) {
				System.out.println("Test 2 Passed");
			} else {
				System.out.println("Test 2 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 2 Failed");
		}
		
		
		/* ---------- Test 3 [PowerSet constructor and getLength] ---------- */
		
		try {
			
			Character[] carr = new Character[] {'a', 'b', 'c'};
			PowerSet<Character> cps = new PowerSet<Character>(carr);
			int[] res = getMinMax(cps);

			if (cps.getLength() == 8 && res[0] == 0 && res[1] == 3) {
				System.out.println("Test 3 Passed");
			} else {
				System.out.println("Test 3 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 3 Failed");
		}
		
		/* ---------- Test 4 [PowerSet constructor and getLength] ---------- */
		
		try {
			
			Integer[] iarr = new Integer[] {5, 1, 9, 3, 7};
			PowerSet<Integer> ips = new PowerSet<Integer>(iarr);
			int[] res = getMinMax(ips);

			if (ips.getLength() == 32 && res[0] == 0 && res[1] == 5) {
				System.out.println("Test 4 Passed");
			} else {
				System.out.println("Test 4 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 4 Failed");
		}
		
		/* ---------- Test 5 [PowerSet getSet] ---------- */
		
		try {
			
			String[] sarr = new String[] {"Tim", "Bob", "Don"};
			PowerSet<String> sps = new PowerSet<String>(sarr);

			if (sps.getSet(0).getLength() == 0 && sps.getSet(5).getLength() == 2 && sps.getSet(7).getLength() == 3) {
				System.out.println("Test 5 Passed");
			} else {
				System.out.println("Test 5 Failed");
			}
		} catch (Exception e) {
			System.out.println("Test 5 Failed");
		}
		
	}
	
	private static int[] getMinMax (PowerSet ps) {
		int tmpMin = 99, tmpMax = -1;
		Set set;
		int n;
		for (int i = 0; i < ps.getLength(); i++) {
			set = ps.getSet(i);
			n = set.getLength();
			if (n < tmpMin) tmpMin = n;
			if (n > tmpMax) tmpMax = n;
		}
		return new int[] {tmpMin, tmpMax};
	}

}
