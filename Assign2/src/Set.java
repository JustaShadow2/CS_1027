public class Set<T> {
    private LinearNode<T> setStart;
    
    public Set() { //constructor initializes setStart to null
        setStart = null;
    }
    
    public void add(T element) { //adds element to the end of the list
        LinearNode<T> newNode = new LinearNode<T>(element); //create a new node with the element
        if (setStart == null) { //if the list is empty
            setStart = newNode; //set the start of the list to the new node
        } else {
            LinearNode<T> current = setStart; //create a new node to traverse the list
            while (current.getNext() != null) { //while the next node is not null
                current = current.getNext(); //set the current node to the next node
            }
            current.setNext(newNode); //set the next node to the new node
        }
    }
    
    public int getLength() {//returns the number of elements in the list
        int count = 0;
        LinearNode<T> current = setStart; //create a new node to traverse the list
        while (current != null) { //while the current node is not null
            count++;
            current = current.getNext(); //set the current node to the next node
        }
        return count;
    }
    
    public T getElement(int i) { //returns the element at position i in the list
        LinearNode<T> current = setStart; //create a new node to traverse the list
        for (int j = 0; j < i; j++) { //traverse the list to the position i
            current = current.getNext(); //set the current node to the next node
        }
        return current.getElement(); //return the element at position i
    }
    
    public boolean contains(T element) { //returns true if the list contains element
        LinearNode<T> current = setStart; //create a new node to traverse the list
        while (current != null) { //while the current node is not null
            if (current.getElement().equals(element)) { //if the current node contains the element
                return true; 
            }
            current = current.getNext(); //set the current node to the next node
        }
        return false;
    }
    
    public String toString() { //returns a string representation of the list
        String result = "";
        LinearNode<T> current = setStart; //create a new node to traverse the list
        while (current != null) { //while the current node is not null
            result += current.getElement() + " "; //add the element to the string
            current = current.getNext(); //set the current node to the next node
        }
        return result;
    }
}