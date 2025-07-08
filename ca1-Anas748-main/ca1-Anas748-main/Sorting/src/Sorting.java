
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *@author name: Muhammad Anas
 * Student number: 2021387
 * GitHub link:  https://github.com/CCT-Dublin/ca1-Anas748
 */
public class Sorting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Double> data = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\work\\coucurrent system\\data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String part : values) {
                    double value = Double.parseDouble(part.trim());
                    data.add(value);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        // Create a SortTask to perform merge sort
        SortTask sortTask = new SortTask(data, 0, data.size() - 1);
        Thread mergeSortThread = new Thread(sortTask);
        mergeSortThread.start();

        try {
            // Wait for the merge sort thread to complete
            mergeSortThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        System.out.println("Sorted Data (largest to smallest):");
        printSortedData(data, 20, 10);// calling printing method 
    }
    // method to print the output in rows and columns
    public static void printSortedData(List<Double> data, int numRows, int numColumns) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                int index = i * numColumns + j;
                if (index < data.size()) {
                    System.out.print(data.get(index).intValue() + "\t");
                } else {
                    //size is less than the expected number of elements, print empty cell
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

}
