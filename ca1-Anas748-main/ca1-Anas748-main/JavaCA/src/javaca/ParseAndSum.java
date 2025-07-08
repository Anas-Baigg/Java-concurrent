/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaca;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ParseAndSum extends Thread{
    private String line;
    private ArrayList<Double> data;
    private double sum;

    public ParseAndSum(String line, ArrayList<Double> data) {
        this.line = line;
        this.data = data;
        this.sum = 0.0;
    }

    @Override
    public void run() {
        processLine(line, data);
    }

    private void processLine(String line, ArrayList<Double> data) {
        String[] values = line.split(",");
        for (String value : values) { // Parse each value in the line and add it to the data list while calculating the sum
            synchronized (data) {// Synchronize access to the shared data list to avoid concurrent modification
            double num = Double.parseDouble(value.trim());
            data.add(num);
                sum += num; // Accumulate the sum
            }
        }
         // Print the sum calculated by the thread for the current line
        System.out.println("Thread " + Thread.currentThread().getId() + " calculated sum: " + sum + " for line: " + line);
    }

    public double getSum() {// Method to get the sum calculated by the thread
        return sum;
    }
}
