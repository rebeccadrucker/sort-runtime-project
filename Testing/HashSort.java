/*
 Author: Owen Gallagher & Rebecca Drucker
 Date: 26 February 2018
 
 Description: Attempt to make a sort that performs better than the
    selection, insertion, merge, quick, and heap sorting algorithms.
*/

import java.lang.Math;

public class HashSort {
    public static int n;                //size of array
    public static int[] a;              //array to be sorted
    
    public static int i=0;              //indeces
    public static int j=0;
    
    public static int temp;             //for swaps
    
    public static int min;              //describe the data set for the hash function
    public static int range;
    
    public static boolean[] hashed;     //remember which elements have already been hashed
    public static int dest;             //hash function output
    
    public static void fill() {
        int value;
        
        for (i=0; i<n; i++) {
            value = (int) Math.round(Math.random() * Integer.MAX_VALUE);
            a[i] = value;
            
            hashed[i] = false;
        }
    }
    
    public static void describe() {
        for (i=0; i<n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\n");
    }
    
    public static void hashSort() {
        //GET STATS
        for (i=0; i<n; i++) { //get stats
            if (a[i] < min) {
                min = a[i];
            }
            if (a[i] > range) {
                range = a[i];
            }
        }
        
        range -= min;
        
        //HASH SORT
        i=0;
        while (i < n) {
            if (!hashed[i]) { //ensure that element is not finished
                //HASH FUNCTION
                dest = (int)(((double) (a[i] - min)) / range * (n-1));
                
                if (dest == i) {
                    hashed[i] = true;
                    i++;
                }
                else if (hashed[dest]) { //check if blocked
                    //SHIFT
                    temp = a[i];
                    
                    if (i < dest) {
                        j=i+1;
                        while (j<dest && (!hashed[j] || a[j] < temp)) {
                            //SWAP
                            a[j-1] = a[j];
                            hashed[j-1] = hashed[j];
                            
                            j++;
                        }
                        
                        //PLACE
                        if (temp <= a[j]) {
                            a[j-1] = temp;
                            hashed[j-1] = true;
                        }
                        else {
                            a[j-1] = a[j];
                            hashed[j-1] = true;
                            
                            a[j] = temp;
                        }
                    }
                    else {
                        j=i-1;
                        while (j>dest && (!hashed[j] || a[j] > temp)) {
                            //SWAP
                            a[j+1] = a[j];
                            hashed[j+1] = hashed[j];
                            
                            j--;
                        }
                        
                        //PLACE
                        if (temp >= a[j]) {
                            a[j+1] = temp;
                            hashed[j+1] = true;
                        }
                        else {
                            a[j+1] = a[j];
                            hashed[j+1] = true;
                            
                            a[j] = temp;
                        }
                    }
                }
                else {
                    //SWAP
                    temp = a[i];
                    a[i] = a[dest];
                    a[dest] = temp;
                    
                    hashed[dest] = true;
                }
            }
            else {
                i++;
            }
        }
        
        //SHIFT FIX
        for (i=0; i<n-1; i++) {
            j=i;
            temp = a[i+1];
            
            //SHIFT
            while (j > 0 && a[j] > temp) {
                a[j+1] = a[j];
                
                j--;
            }
            
            //PLACE
            a[j+1] = temp;
        }
    }
    
    public static void main(String[] args) {
        n = 1000000;
        a = new int[n];
        hashed = new boolean[n];
        
        //System.out.println("HashSort: sorts an array a[] of " + n + " elements, \n\tby first hashing each element to its estimated final position,\n\t and then passing through a[] again to fix the array using insertion sort.\n");
        
        fill();
        
        //describe();
        
        min = a[0]; //initialize stats
        range = min;
        
		long startTime = System.nanoTime();
        hashSort();
		long endTime = System.nanoTime();
		double estTime = ((double)endTime - startTime)/1000000;
		System.out.println("Program took " + estTime + " milliseconds to run.");

        //describe();
    }
}
