//inherit Matrix.java
public class Vector extends Matrix {
    public Vector (int c) {
        //Use super() to call the Matrix constructor and send in 1 as the value of r
        super(1, c);
    }
    public Vector (int c, double[] linArr) {
        //Use super() to call the Matrix constructor and send in 1 as the value of r
        super(1, c, linArr);
    }
    public double getElement (int c) {
        //return the value at row 0 and column c
        return getElement(0, c);
    }
}