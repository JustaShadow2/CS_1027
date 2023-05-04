public class DLinkedNode<T> {
    private T dataItem; //instance variables
    private double priority;
    private DLinkedNode<T> next;
    private DLinkedNode<T> prev;

    public DLinkedNode(T dataItem, double priority) { //constructor
        this.dataItem = dataItem;
        this.priority = priority;
        next = null;
        prev = null;
    }

    public DLinkedNode() { //default constructor
        this(null, 0.0);
    } 

    public T getDataItem() { //getters and setters
        return dataItem;
    }

    public void setDataItem(T dataItem) {
        this.dataItem = dataItem;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public DLinkedNode<T> getNext() {
        return next;
    }

    public void setNext(DLinkedNode<T> next) {
        this.next = next;
    }

    public DLinkedNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DLinkedNode<T> prev) {
        this.prev = prev;
    }
    
}
