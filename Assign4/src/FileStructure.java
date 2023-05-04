import java.util.Iterator;
import java.util.ArrayList;

public class FileStructure {
    private NLNode<FileObject> root;

    public FileStructure (String fileObjectName) throws FileObjectException {
        
        //if fileObjectName is the name of a file, this FileStructure will be a single node storing the FileObject representing the file
        //otherwise, this FileStrucure object will behave a list with nodes for all the file objects contained inside the folder named fileObjectName
        FileObject fileObject = new FileObject(fileObjectName); 
        //if fileObject is a folder, use an auxiliary recursive method explore the folder and create the nodes corresponding to the files objects contained in it
        if (fileObject.isDirectory()||fileObject.isFile()) {
            root = new NLNode<>(fileObject, null); //create the root node
            explore(root); //explore the folder using the auxiliary method
        }
    }
    public void explore(NLNode<FileObject> r) { //auxiliary recursive method
        FileObject f = r.getData(); //get the FileObject stored in r
        Iterator<FileObject> it = f.directoryFiles(); //get an iterator storing the FileObjects contained in f
        while (it.hasNext()) { //while there are more FileObjects in the iterator
            FileObject f1 = it.next(); //get the next FileObject
            NLNode<FileObject> n1 = new NLNode<FileObject>(f1, r); //create a node storing f1 and set its parent to r
            r.addChild(n1); //add n1 to r as a child
            if (f1.isDirectory()) { //if f1 is a folder, call explore on n1
                explore(n1); 
            }
        }
    }

    public NLNode<FileObject> getRoot() { //returns the root node of this FileStructure
        return root;
    }

    public Iterator<String> filesOfType (String type) {
        //returns a String iterator containing the names of all the files of type type contained in the folder represented by nodes in this FileStructure
        //each name should include absolute path to the file
        //use java ArrayList as the container
        ArrayList<String> list = new ArrayList<String>(); //create an ArrayList to store the names of the files
        rec(root, type, list); //call the auxiliary recursive method
        return list.iterator();  //return an iterator for the ArrayList
}

    public void rec(NLNode<FileObject> r, String type, ArrayList<String> list) { //auxiliary recursive method
        FileObject f = r.getData(); //get the FileObject stored in r
        if (f.getLongName().endsWith(type)) { //if the name of the file ends with type, add it to the list
            list.add(f.getLongName()); //add the name of the file to the list
        }
        if (f.isDirectory()) { //if f is a folder, call rec on all its children
            Iterator<NLNode<FileObject>> it = r.getChildren(); //get an iterator storing the children of r 
            while (it.hasNext()) { //while there are more children in the iterator
                rec(it.next(), type, list); //recursively call rec on the next child
            }
        }
    }

    public String findFile(String name) {
        //looks for a file named name in the folder represented by nodes in this FileStructure. if found, then returns the absolute path as a String
        //if not found return empty string
        //only return the first file found

        ArrayList<String> list = new ArrayList<String>(); //create an ArrayList to store the names of the files
        search(root, name, list); //call the auxiliary recursive method
        if (list.size() == 0) { //if the list is empty, return an empty string
            return ""; 
        }
        return list.get(0); //return the first file found only
    }

    public void search(NLNode<FileObject> r, String name, ArrayList<String> list) { //auxiliary recursive method
        FileObject f = r.getData(); //get the FileObject stored in r
        if (f.getName().equals(name)) { //if the name of the file is equal to name, add it to the list
            list.add(f.getLongName()); //add the name of the file to the list
        }
        if (f.isDirectory()) { //if f is a folder, call search on all its children
            Iterator<NLNode<FileObject>> it = r.getChildren(); //get an iterator storing the children of r
            while (it.hasNext()) { //while there are more children in the iterator
                search(it.next(), name, list); //recursively call search on the next child
            }
        }
    }

}
