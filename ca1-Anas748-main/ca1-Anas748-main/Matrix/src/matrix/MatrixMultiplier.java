/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matrix;

/**
 *
 * @author user
 * Each thread created will execute an instance of this class to perform a portion of the matrix multiplication task.
 * The result of the multiplication is stored in the shared result matrix.
 */
public class MatrixMultiplier extends Thread {
    private int[][] matrix1;
    private int[][] matrix2;
    private int[][] result;
    private int row;

    public MatrixMultiplier(int[][] matrix1, int[][] matrix2, int[][] result, int row) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
        this.row = row;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {    // Iterate over columns of result matrix
            for (int k = 0; k < 10; k++) {// Iterate over columns of matrix2 / rows of matrix1
                result[row][j] += matrix1[row][k] * matrix2[k][j]; // Perform dot product for the corresponding row of matrix1 and column of matrix2
            } 
        }
    }
}
