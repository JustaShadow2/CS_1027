public class MarkovChain {
    private Vector stateVector;
    private Matrix transitionMatrix;

    public MarkovChain(Vector sVector, Matrix tMatrix) {
        //initialize the instance variables with the given parameters
        stateVector = sVector;
        transitionMatrix = tMatrix;
    }

    public boolean isValid() {
        //set variables for the number of rows and columns in the transition matrix and state vector
        int tMatrixRows = transitionMatrix.getNumRows();
        int tMatrixCols = transitionMatrix.getNumCols();
        int sVectorCols = stateVector.getNumCols();
        //check if the instance variables are valid for a markov chain problem and return true if they are and false otherwise
        //A Markov chain is valid if the following conditions are met:
        //1. the sum of values of the state vector is 1.0
        //2. the sum of values of each row of the transition matrix is 1.0
        //3. the transition matrix is square and the number of rows is equal to the number of columns in the state vector

        //check if the transition matrix is square and the number of rows is equal to the number of columns in the state vector
        if (tMatrixRows != tMatrixCols || tMatrixRows != sVectorCols) {
            return false;
        }

        //check if the sum of value of each row of the transition matrix is 1.0
        for (int i = 0; i < tMatrixRows; i++) {
            double tsum = 0;
            for (int j = 0; j < tMatrixCols; j++) {
                tsum += transitionMatrix.getElement(i, j); //get the sum of each row
            }
            if (!(0.99 < tsum && tsum < 1.01)) { //check if the sum is 1.0
                return false;
            }
        }

        //check if the sum of values of the state vector is 1.0
        double stsum = 0;
        for (int a = 0; a < sVectorCols; a++) {
            stsum += stateVector.getElement(a); //get the sum of the state vector
        }
        if (!(0.99 < stsum && stsum < 1.01)) { //check if the sum is 1.0
            return false;
        }
        return true;
    }

    public Matrix computeProbabilityMatrix (int numSteps) {
        //call isValid() and return null if it returns false
        //Compute the probability matrix of this Markov chain after the given numSteps by multiplying the transition matrix by itself numSteps-1 times and multiplying the state vector by the resulting matrix. return the resulting vector
        //Note: You can use the Matrix class's multiply method to multiply two matrices and the Vector class's multiply method to multiply a matrix and a vector
        
        //check if the Markov chain is valid
        if (!isValid()) {
            return null;
        }

        //compute the probability matrix
        Matrix result = transitionMatrix;
        for (int i = 0; i < numSteps - 1; i++) {
            result = result.multiply(transitionMatrix); //multiply the transition matrix by itself numSteps-1 times
        }
        Matrix finalresult = stateVector.multiply(result); //multiply the state vector by the resulting matrix
        return finalresult;
    }
}


