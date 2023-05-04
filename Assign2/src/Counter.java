public class Counter {
    private PowerSet<Card> cardps;  // Power set of cards in hand
    private Card starter;          // Starter card

    public Counter(Card[] hand, Card starter) { // Constructor
        this.starter = starter;
        cardps = new PowerSet<>(hand);
    }

    public int countPoints() { // Count points
        int points = 0;
        points += countKnobs();
        // System.out.println("Nobs: " + countKnobs());
        points += countRuns();
        // System.out.println("Runs: " + countRuns());
        points += countFlush();
        // System.out.println("Flush: " + countFlush());
        points += countPairs();
        // System.out.println("Pairs: " + countPairs());
        points += countFifteens();
        // System.out.println("Fifteens: " + countFifteens());
        // System.out.println("Starter: " + starter.toString());
        // System.out.println(cardps.getSet(cardps.getLength() - 1).toString());
        return points;
    }

    private boolean isRun (Set<Card> set) {
		// In this method, we are going through the given set to check if it constitutes a run of 3 or more
		// consecutive cards. To do this, we are going to create an array of 13 cells to represent the
		// range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
		// each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
		// An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
		// contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
		// Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.
		
		int n = set.getLength();
		
		if (n <= 2) return false; // Run must be at least 3 in length.
		
		int[] rankArr = new int[13];
		for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.
		
		for (int i = 0; i < n; i++) {
			rankArr[set.getElement(i).getRunRank()-1] += 1;
		}

		// Now search in the array for a sequence of n consecutive 1's.
		int streak = 0;
		int maxStreak = 0;
		for (int i = 0; i < 13; i++) {
			if (rankArr[i] == 1) {
				streak++;
				if (streak > maxStreak) maxStreak = streak;
			} else {
				streak = 0;
			}
		}
		if (maxStreak == n) { // Check if this is the maximum streak.
			return true;
		} else {
			return false;
		}

	}
    
    private int countKnobs() { 
        int points = 0;
        String suit = starter.getSuit(); //get the suit of the starter card
        for (int i = 0; i < cardps.getLength(); i++) { //go through the power set of the hand
            Set<Card> temp = cardps.getSet(i); //get the set at the current index of the power set
            if (temp.getLength() == 1) { //if the set has 1 card
                if (temp.getElement(0).getLabel().equals("J") && temp.getElement(0).getSuit().equals(suit)) { //if the card is a Jack and has the same suit as the starter card
                    points += 1;
                }
            }
        }
        return points;
    }

    private int countRuns() { //does it twice because if there are two runs of the same length it needs to count the points twice
        int points = 0;
        int max = 0;
        for (int i = 0; i < cardps.getLength(); i++) { //go through the power set of the hand
            Set<Card> temp = cardps.getSet(i); //get the set at the current index of the power set
            if (isRun(temp)) { //if the set is a run
                if (temp.getLength() > max) { //if the length of the run is greater than the current max
                    max = temp.getLength(); //set the max to the length of the run
                }
            }
        }
        for (int i = 0; i < cardps.getLength(); i++) { //go through the power set of the hand
            Set<Card> temp = cardps.getSet(i); //get the set at the current index of the power set
            if (isRun(temp)) { //if the set is a run
                if (temp.getLength() == max) { //if the length of the run is equal to the max
                    points += temp.getLength(); //add the length of the run to the points
                } 
            }
        }
        return points;
    }

    private int countFlush() {
        int points = 0;
        int counter = 0; //used to determine the number of points to add
        Set<Card> Set2 = new Set<>();  // temp Set of cards in hand
        for (int i = 0; i < (cardps.getSet(cardps.getLength() - 1).getLength()); i++) {  // Add cards to set
            if (cardps.getSet(cardps.getLength() - 1).getElement(i) != starter) { //if the card is not the starter card
                Set2.add(cardps.getSet(cardps.getLength() -1).getElement(i)); //add the card to the set
            }
        }
        String suit = Set2.getElement(0).getSuit(); //get the suit of the first card in the set
        for (int i = 0; i < Set2.getLength(); i++) {  // Count the number of cards in the set with the same suit as the first card
            if (Set2.getElement(i).getSuit() == suit) {  //if the card has the same suit as the first card
                counter++; //increment the counter
            }
        }
        if (counter == 4) {
            points = points + 4; //add 4 points
        }
        if (suit == starter.getSuit() && counter == 4) { //if the suit of the starter card is the same as the suit of the first card in the set and the counter is 4
            points++; //add 1 point
        }
        return points;
    }

    private int countPairs() { 
        int points = 0; 
        for (int i = 0; i < cardps.getLength(); i++) { //go through the power set of the hand 
            Set<Card> temp = cardps.getSet(i); //get the set at the current index of the power set 
            if (temp.getLength() == 2) { //if the set has 2 cards 
                if (temp.getElement(0).getLabel().equals(temp.getElement(1).getLabel())) { //if the cards have the same label
                    points += 2; //add 2 points
                }
            }
        }
        return points;
    }

    private int countFifteens() { 
        int points = 0;
        for (int i = 0; i < cardps.getLength(); i++) { //go through the power set of the hand
            Set<Card> temp = cardps.getSet(i); //get the set at the current index of the power set
            int sum = 0; //used to determine if the sum of the cards in the set is 15
            for (int j = 0; j < temp.getLength(); j++) { //go through the set
                sum += temp.getElement(j).getFifteenRank(); //add the rank of the card to the sum
            }
            if (sum == 15) {  //if the sum is 15
                points += 2; //add 2 points
            }
        }
        return points;
    }
}