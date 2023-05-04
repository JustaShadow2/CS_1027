public class PowerSet<T> {
    private Set<T> [] set; //array of sets
    
    public PowerSet(T[] elements) {
        set = (Set<T>[]) new Set[(int) Math.pow(2, elements.length)]; //create an array of sets
        for (int i = 0; i < set.length; i++) { //initialize each set in the array
            set[i] = new Set<T>(); //initialize the set
        }
        for (int i = 0; i < elements.length; i++) { //add each element to the appropriate sets
            for (int j = 0; j < set.length; j++) { //add the element to the set if the bit at position i is 1
                if ((j & (1 << i)) != 0) { //if the bit at position i is 1
                    set[j].add(elements[i]); //add the element to the set
                }
            }
        }
    }
    
    public int getLength() { //returns the number of sets in the array
        return set.length;
    }
    
    public Set<T> getSet(int i) { //returns the set at position i in the array
        return set[i];
    }
}