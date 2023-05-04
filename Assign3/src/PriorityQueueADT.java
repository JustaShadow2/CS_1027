/**
 * @author CS1027
 *
 *         Defines the interface of a priority queue
 */

public interface PriorityQueueADT<T> {
	/**
	 * Adds one data item to the priority queue keeping the data items sorted in non
	 * decreasing order of priority
	 * @param data item to be added onto the priority queue
	 */
	public void add(T data, double priority);

	/**
	 * Removes and returns the data item from the priority queue with smallest
	 * priority
	 * @return T smallest priority data item removed from the priority queue
	 */
	public T removeMin() throws EmptyPriorityQueueException;

	/**
	 * Updates the priority of the given data item to the new value
	 * 
	 * @param data item whose priority is to be changed
	 * @param newPriority  value of the new priority for this data item
	 */
	public void updatePriority(T data, double newPriority) throws InvalidElementException;

	/**
	 * Returns true if this priority queue contains no data items.
	 * 
	 * @return boolean whether or not this priority queue is empty
	 */
	public boolean isEmpty();

	/**
	 * Returns the number of data items in this priority queue.
	 * 
	 * @return int number of data items in this priority queue
	 */
	public int size();

	/**
	 * Returns a string representation of this priority queue.
	 * 
	 * @return String representation of this priority queue
	 */
	public String toString();
}
