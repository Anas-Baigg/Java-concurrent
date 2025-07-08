/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaca;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author name: Muhammad Anas
 * Student number: 2021387
 * GitHub link:  https://github.com/CCT-Dublin/ca1-Anas748
 */
public class JavaCA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Double> data = new ArrayList<>();// ArrayList to store the data read from the CSV file
        double sum = 0.0;// Variable to hold the total sum of the data
        String csvFile = "C:\\Users\\user\\Desktop\\work\\coucurrent system\\data.csv"; 
        try ( BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            ArrayList<ParseAndSum> threads = new ArrayList<>();// ArrayList to store ParseAndSum threads
            while ((line = br.readLine()) != null) {
                ParseAndSum readerThread = new ParseAndSum(line, data);//ParseAndSum thread for each line and add it to the list of threads
                threads.add(readerThread);
                readerThread.start(); // Start each thread independently
            }
            for (ParseAndSum thread : threads) {
                thread.join(); // Wait for each thread to finish processing
                sum += thread.getSum();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
         
        int n = data.size();//total number of values read from the CSV file
        
        System.out.println("Data: " + data);// Print the data read from the CSV file
       
        System.out.println("Number of values: " + n); // Print the number of values read from the CSV file
       
        double mean = sum / n; // Calculate the mean of the data
        
        double standardDeviation = 0.0;// Calculate the standard deviation of the data
        for (Double num : data) {
            standardDeviation += Math.pow((num - mean), 2);
        }
        standardDeviation = Math.sqrt(standardDeviation / n);
        // Print the calculated mean and standard deviation
        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + standardDeviation);

    }

    }


