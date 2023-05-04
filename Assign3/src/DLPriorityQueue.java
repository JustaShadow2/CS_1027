public class DLPriorityQueue<T> implements PriorityQueueADT<T> { 
    private DLinkedNode<T> front;
    private DLinkedNode<T> rear;
    private int count;

    public DLPriorityQueue() { //constructor
        front = null;
        rear = null;
        count = 0;
    }

    public void add (T dataItem, double priority) { // sort in non decreasing order of priority (finds the right place to insert the new node) - new method time
        DLinkedNode<T> newNode = new DLinkedNode<T>(dataItem, priority);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            DLinkedNode<T> current = rear; // start at rear and work backwards to front to find the correct position to insert the new node
            while (current != null && current.getPriority() > priority) { // while current is not null and the priority of the current node is greater than the priority of the new node (new node has higher priority) 
                current = current.getPrev(); // move current to the previous node in the queue (move backwards) 
            }
            if (current == null) { // insert at front
                newNode.setNext(front); // set the next of the new node to the current front node (the node that is currently at the front of the queue)
                front.setPrev(newNode); // set the previous of the current front node to the new node (the new node is now the previous node of the current front node) 
                front = newNode;
            } else if (current == rear) { // insert at rear
                newNode.setPrev(rear); // set the previous of the new node to the current rear node (the node that is currently at the rear of the queue)
                rear.setNext(newNode); // set the next of the current rear node to the new node (the new node is now the next node of the current rear node)
                rear = newNode;
            } else { // insert in middle
                newNode.setNext(current.getNext()); // set the next of the new node to the next of the current node (the node that is currently in the queue) 
                newNode.setPrev(current); // set the previous of the new node to the current node (the node that is currently in the queue)
                current.getNext().setPrev(newNode); // set the previous of the next of the current node to the new node (the new node is now the previous node of the next of the current node)
                current.setNext(newNode); // set the next of the current node to the new node (the new node is now the next node of the current node)
            }

            // using bubble sort to sort the queue after adding a new node - space complexity is O(n^2) time complexity is O(n^2) which is longer than the above method so I commented it out (above one has linear space complexity and constant time complexity)
            // // DLinkedNode<T> current2 = front; 
            // // while (current2 != null) {
            // //     DLinkedNode<T> current3 = current2.getNext();
            // //     while (current3 != null) {
            // //         if (current2.getPriority() > current3.getPriority()) {
            // //             T tempData = current2.getDataItem();
            // //             double tempPriority = current2.getPriority();
            // //             current2.setDataItem(current3.getDataItem());
            // //             current2.setPriority(current3.getPriority());
            // //             current3.setDataItem(tempData);
            // //             current3.setPriority(tempPriority);
            // //         }
            // //         current3 = current3.getNext();
            // //     }
            // //     current2 = current2.getNext();
            // // }
        }
        ++count;
    }

    public void updatePriority (T dataItem, double newPriority) throws InvalidElementException { // update the priority of the data item with the given data item throw exception if data item is not in priority queue
        DLinkedNode<T> current = front; // start at front and work forwards to rear to find the correct node to update the priority of
        while (current != null && !current.getDataItem().equals(dataItem)) { // while current is not null and the data item of the current node is not equal to the data item of the new node
            current = current.getNext();
        }
        // if queue is empty or if the data is not in the queue throw invalid element exception
        //check if the queue is empty
        if (isEmpty()) { // if the queue is empty
            throw new InvalidElementException("Priority queue is empty"); 
        }
        if (current == null) { // if the data item is not in the queue
            throw new InvalidElementException("Not in priority queue");
        } else {
            current.setPriority(newPriority); // update the priority of the current node to the new priority
        }
        //reorder the queue after updating the priority, lowest priority at front
        DLinkedNode<T> node1 = front; // start at front and work forwards to rear to sort the queue - use node1 and node2 to traverse the queue with sorting algorithm (bubble sort)
        while (node1 != null) { // while node1 is not null
            DLinkedNode<T> node2 = node1.getNext(); // set node2 to the next of current2
            while (node2 != null) { // while node2 is not null
                if (node1.getPriority() > node2.getPriority()) { // if the priority of node1 is greater than the priority of node2
                    T tempData = node1.getDataItem(); // swap the data items and priorities of node1 and node2
                    double tempPriority = node1.getPriority(); // ^
                    node1.setDataItem(node2.getDataItem());  // ^ 
                    node1.setPriority(node2.getPriority()); // ^
                    node2.setDataItem(tempData); // ^
                    node2.setPriority(tempPriority); // ^
                }
                node2 = node2.getNext(); // move node2 to the next node in the queue
            }
            node1 = node1.getNext(); // move node1 to the next node in the queue
        }
    }

    public T removeMin() throws EmptyPriorityQueueException { // remove and return the data item with the smallest priority (front). throw exception if priority queue is empty
        if (isEmpty()) { // if the queue is empty
            throw new EmptyPriorityQueueException("Priority queue is empty");  
        } else {
            T dataItem = front.getDataItem(); // get the data item of the front node
            if (front == rear) { // if the front node is the only node in the queue
                front = null;
                rear = null;
            } else { // if there are more than one node in the queue
                front = front.getNext();
                front.setPrev(null);
            }
            --count; 
            return dataItem;
        }
    }

    public boolean isEmpty() { // return true if the priority queue is empty
        return count == 0;
    }

    public int size() { // return the number of data items in the priority queue
        return count;
    }

    public String toString() {
        // return a string representation of the priority queue
        String str = "";
        DLinkedNode<T> current = front;
        while (current != null) {
            str += current.getDataItem();
            current = current.getNext();
        }
        return str;
    }

    public DLinkedNode<T> getRear() { // return the rear node of the queue
        return rear;
    }
}