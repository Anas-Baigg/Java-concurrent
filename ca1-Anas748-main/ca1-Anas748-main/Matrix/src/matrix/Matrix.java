/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author name: Muhammad Anas Student
 *  b number: 2021387 GitHub link:
 * https://github.com/CCT-Dublin/ca1-Anas748
 */
public class Matrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int size = 10;
        int[][] matrix1 = new int[size][size];
        int[][] matrix2 = new int[size][size];
        int[][] result = new int[size][size];
        try ( BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\work\\coucurrent system\\data.csv"))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 2 * matrix1.length) {
                String[] values = line.split(",");
                for (int col = 0; col < matrix1.length; col++) {
                    if (row < matrix1.length) {
                        matrix1[row][col] = Integer.parseInt(values[col]);
                    } else {
                        matrix2[row - matrix1.length][col] = Integer.parseInt(values[col]);
                    }
                }
                row++;
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        Thread[] multiplicationThreads = new Thread[size];// array to hold threads for matrix multiplication
        for (int i = 0; i < size; i++) { //create and start threads for matrix multiplication
            multiplicationThreads[i] = new Thread(new MatrixMultiplier(matrix1, matrix2, result, i));    //new thread and initialize it with a MatrixMultiplier object
            multiplicationThreads[i].start();  // Start the thread
        }

        // Wait for all multiplication threads to finish
        try {
            for (Thread thread : multiplicationThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the resulting matrix
        System.out.println("Resultant Matrix:");
        printMatrix(result);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

}
