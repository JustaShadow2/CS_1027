public class Matrix {
    private int numCols;
    private int numRows;
    private double[][] data;

    public Matrix(int r, int c) {
        //Initialize the instance variables numRows and numCols and initialize data to have r rows and c columns
        numRows = r;
        numCols = c;
        data = new double[r][c];
    }

    public Matrix(int r, int c, double[] linArr) {
        //populate the 2-dimensional array data with the elements in linArr
        numRows = r;
        numCols = c;
        data = new double[r][c];
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                data[i][j] = linArr[count]; //populating the 2d array
                count++;
            }
        }
    }

    //return the number of rows in the matrix
    public int getNumRows() {
        return numRows;
    }

    //return the number of columns in the matrix
    public int getNumCols() {
        return numCols;
    }

    //return the 2d array data
    public double[][] getData() {
        return data;
    }

    //return the value at row r and column c of data
    public double getElement(int r, int c) {
        return data[r][c];
    }

    //store value at row r and column c of data
    public void setElement(int r, int c, double value) {
        data[r][c] = value;
    }

    //Transpose the matrix and update the instance variables to store the transposed matrix as the new instance of 'this' matrix
    public void transpose() {
        double[][] temp = new double[numCols][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) { //switching the rows and columns
                temp[j][i] = data[i][j]; //^^^
            }
        }
        data = temp; 
        int temp2 = numRows; //switching the number of rows and columns
        numRows = numCols;  
        numCols = temp2;  
    }

    //Create a new Matrix object with the same dimensions as 'this' matrix. Multiply each value in 'this' matrix by the given scalar and insert the resulting values into the new Matrix object and return it
    public Matrix multiply (double scalar) {
        Matrix newMatrix = new Matrix(numRows, numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) { //multiplying each value in the matrix by the scalar
                newMatrix.setElement(i, j, data[i][j] * scalar); //^^^
            }
        }
        return newMatrix;
    }

    public Matrix multiply (Matrix other) {
        //if the number of columns of this matrix is not equal to the number of rows of the other matrix return null
        //otherwise create a new Matrix object with the number of rows from 'this' matrix and the number of columns from the other matrix
        //multiply the two matrices together and store the result in the new Matrix object and return it
        if (numCols != other.getNumRows()) { //if the number of columns of this matrix is not equal to the number of rows of the other matrix return null
            return null;
        }
        Matrix newMatrix = new Matrix(numRows, other.getNumCols()); //create a new Matrix object with the number of rows from 'this' matrix and the number of columns from the other matrix
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < other.getNumCols(); j++) {
                double sum = 0;
                for (int k = 0; k < numCols; k++) {
                    sum += data[i][k] * other.getData()[k][j]; //multiplying the two matrices together and storing the result in the new Matrix object
                }
                newMatrix.setElement(i, j, sum); 
            }
        }
        return newMatrix;
    }

    public String toString() {
        //if the matrix data is empty return 'empty matrix'
        //otherwise return a string representation of the matrix
        if (data.length == 0) {
            return "Empty matrix"; //if the matrix data is empty return 'empty matrix'
        }
        String result = "";
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result += String.format(("%8.3f"),data[i][j]); //return a string representation of the matrix with 3 decimal places
            }
            result += "\n"; //newline for each row
        }
        return result;
    }
}
