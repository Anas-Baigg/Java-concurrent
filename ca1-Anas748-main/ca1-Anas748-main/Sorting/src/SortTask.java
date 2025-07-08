
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user
 */
public class SortTask implements Runnable {

    private List<Double> arr;
    private int left;
    private int right;

    public SortTask(List<Double> arr, int left, int right) { //The constructor is responsible for setting up the SortTask 
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    /**
     * run()method is part of runnable which will execute when thread start it
     * will check if its already not sorted
     *
     * to see what thread did what sorting replace in run() Thread leftThread =
     * new Thread(new SortTask(arr, left, mid), "LeftThread-" + left); Thread
     * rightThread = new Thread(new SortTask(arr, mid + 1, right),
     * "RightThread-" + right);
     */
    @Override
    public void run() {
        if (left < right) {// check if elemnts to store in subarry
            int mid = (left + right) / 2;//calculating the middle of the array

            Thread leftThread = new Thread(new SortTask(arr, left, mid));// will store left halve of currnt subarry 
            Thread rightThread = new Thread(new SortTask(arr, mid + 1, right));//will store right halve of currnt subarry 

            // Start both sorting threads
            leftThread.start();
            rightThread.start();
            try {
                // Wait for both sorting threads
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
            merge(arr, left, mid, right);
            // System.out.println("Thread " + Thread.currentThread().getName() + " sorting sub-list: " + left + " to " + right);
        }

    }

    /**
     * @param arr The list of Doubles containing the sub-lists to be merged.
     * @param left The starting index of the first sub-list.
     * @param mid The middle index separating the two sub-lists.
     * @param right The ending index of the second sub-list.
     */
    public static void merge(List<Double> arr, int left, int mid, int right) {
        // Find the sizes of the two sub lists.
        int n1 = mid - left + 1;
        int n2 = right - mid;

        //temporary arrays to hold the elements of the sub lists.
        List<Double> leftArr = new ArrayList<>(n1);
        List<Double> rightArr = new ArrayList<>(n2);

        // Copy elements from the original list to the temporary arrays.
        for (int i = 0; i < n1; i++) {
            leftArr.add(arr.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightArr.add(arr.get(mid + 1 + j));
        }

        // Merge the temporary arrays back into the original list.
        int i = 0, j = 0, k = left; // Track indices for leftArr, rightArr, and original list
        while (i < n1 && j < n2) {
            // Compare elements from both sub-lists.
            if (leftArr.get(i) >= rightArr.get(j)) {
                // If the element from the left sub-list is greater or equal, insert it into the original list.
                arr.set(k, leftArr.get(i));
                i++;
            } else {
                // Otherwise, insert the element from the right sub-list.
                arr.set(k, rightArr.get(j));
                j++;
            }
            k++; // Move to the next position in the original list.
        }

        // Copy any remaining elements from the left sub list.
        while (i < n1) {
            arr.set(k, leftArr.get(i));
            i++;
            k++;
        }

        // Copy any remaining elements from the right sub list.
        while (j < n2) {
            arr.set(k, rightArr.get(j));
            j++;
            k++;
        }
    }
}
