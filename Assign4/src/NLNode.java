import java.util.Iterator;
import java.util.Comparator;

public class NLNode<T>{
    private NLNode<T> parent; // parent node
    private ListNodes<NLNode<T>> children; // children nodes
    private T data; // data stored in the node

    public NLNode() { // constructor
        this.data = null;
        this.parent = null;
        this.children = new ListNodes<NLNode<T>>();
    }

    public NLNode(T d, NLNode<T> p) { // constructor
        this.data = d;
        this.parent = p;
        this.children = new ListNodes<NLNode<T>>();
    }

    public void setParent(NLNode<T> p) {
        this.parent = p; // set parent
    }
    
    public NLNode<T> getParent() {
        return this.parent; // get parent
    }

    public void addChild(NLNode<T> newChild) {
        this.children.add(newChild); // add child
        newChild.setParent(this); // set parent of new child
    }

    public Iterator<NLNode<T>> getChildren() {
        //convert children to an iterator using the getList method
        return this.children.getList();
    }

    public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter) {
        //convert children to an iterator using the sortedList method
        return this.children.sortedList(sorter);
    }

    public void setData(T d) {
        this.data = d; // set data
    }

    public T getData() {
        return this.data; // get data
    }
}
