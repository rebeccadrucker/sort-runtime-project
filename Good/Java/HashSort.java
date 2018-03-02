/*

 Author: Owen Gallagher & Rebecca Drucker
 Date: 26 February 2018
 
 Description: Attempt to make a sort that performs better than the
    selection, insertion, merge, quick, and heap sorting algorithms.
 
 57 4 24 43 12 52 91 18 15 66 1 73 47 52 73 9 89 66 89 94 64 71 81 95 73
 
 swapped 57 with 73
 
 swapped 73 with 89
 
 swapped 89 with 81
 
 swapped 81 with 64
 
 swapped 64 with 89

 shifted 89 to 89
 
 swapped 4 with itself
 
 swapped 24 with 91
 
 shifted 91 to 73
 
 swapped 43 with 47
 
 swapped 47 with 52
 
 swapped 52 with 9
 
 moved 52 to 12
 
 swapped 9 with 12
 
 shifted 12 to 24
 
 swapped 52 with 52
 
 shifted 52 to 52
 
 swapped 18 with itself
 
 moved 18 to 3
 
 swapped 18 with itself
 
 shifted 15 to 12
 
 shifted 66 to 73
 
 shifted 1 to 4
 
 shifted 73 to 73
 
 shifted 66 to 66
 
 swapped 94 with 73
 
 shifted 73 to 81
 
 shifted 71 to 66
 
 shifted 95 to 91
 
 fault @22
 runtime: 3.815562ms
 
 1 4 9 12 15 18 18 43 47 52 52 52 64 66 66 71 73 73 73 81 89 89 94 91 95

*/

import java.lang.Math;

public class HashSort {
    static int n;                //size of array
    static int[] a;              //array to be sorted
    
    static int i=0;              //indeces
    static int j=0;
    
    static int temp;             //for swaps
    
    static int min;              //describe the data set for the hash function
    static int range;
    
    static boolean[] hashed;     //remember which elements have already been hashed
    static int dest;             //hash function output
    static int nMinus1;
    
    static int newDest;
    static boolean confirmed;
    
    public static void fill() {
        int value;
        
        for (i=0; i<n; i++) {
            value = (int) Math.round(Math.random() * 100);
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
        i = 0;
        nMinus1 = n-1;
        while (i < n) {
            if (!hashed[i]) { //ensure that element is not finished
                //HASH FUNCTION
                temp = a[i];
                dest = (int)(((double) (temp - min)) / range * nMinus1);
                
                if (dest != i && hashed[dest]) { //check if blocked
                    //SHIFT
                    System.out.print("shifted " + a[i] + " to ");
                    if (i < dest) {
                        j=i+1;
                        while (j<n-1 && (!hashed[j] || a[j] < temp)) {
                            //SHIFT
                            a[j-1] = a[j];
                            hashed[j-1] = hashed[j];
                            
                            j++;
                        }
                        
                        //PLACE
                        if (temp <= a[j]) {
                            a[j-1] = temp;
                            hashed[j-1] = true;
                            
                            System.out.println(a[j]);
                        }
                        else {
                            a[j-1] = a[j];
                            hashed[j-1] = hashed[j];
                            
                            a[j] = temp;
                            hashed[j] = true;
                            
                            System.out.println(a[j-1]);
                        }
                    }
                    else {
                        j=i-1;
                        while (j>0 && (!hashed[j] || a[j] > temp)) {
                            //SHIFT
                            a[j+1] = a[j];
                            hashed[j+1] = hashed[j];
                            
                            j--;
                        }
                        
                        //PLACE
                        if (temp >= a[j]) {
                            a[j+1] = temp;
                            hashed[j+1] = true;
                            
                            System.out.println(a[j]);
                        }
                        else {
                            a[j+1] = a[j];
                            hashed[j+1] = hashed[j];
                            
                            a[j] = temp;
                            hashed[j] = true;
                            
                            System.out.println(a[j+1]);
                        }
                    }
                }
                else {
                    //SWAP
                    a[i] = a[dest];
                    a[dest] = temp;
                    if (dest == i) {
                        System.out.println("swapped " + a[dest] + " with itself");
                    }
                    else {
                        System.out.println("swapped " + a[dest] + " with " + a[i]);
                    }
                    
                    //CHECK LEFT
                    j=dest;
                    newDest = -1;
                    confirmed = false;
                    while (!confirmed) {
                        while (j>0 && !hashed[j]) { //move j to first hashed element > a[dest], or last hashed element <= dest
                            j--;
                        }
                        
                        if (j != 0 && a[j] > a[dest]) {
                            newDest = j;
                            j--;
                        }
                        else {
                            confirmed = true;
                        }
                        System.out.println("j=" + j);
                    }
                    
                    if (newDest != -1) {
                        //SHIFT LEFT
                        temp = a[dest];
                        for (j=dest-1; j>newDest; j--) {
                            a[j+1] = a[j];
                            hashed[j+1] = hashed[j];
                        }
                        a[newDest] = temp;
                        hashed[newDest] = true;
                        
                        System.out.println("moved " + a[newDest] + " to " + newDest);
                    }
                    else {
                        //CHECK RIGHT
                        j=dest;
                        confirmed = false;
                        while (!confirmed) {
                            while (j<n-1 && !hashed[j]) {
                                j++;
                            }
                            
                            if (j != n-1 && a[j] < a[dest]) {
                                newDest = j;
                                j++;
                            }
                            else {
                                confirmed = true;
                            }
                            System.out.println("j=" + j);
                        }
                        
                        if (newDest != -1) {
                            //SHIFT RIGHT
                            temp = a[dest];
                            for (j=dest+1; j<newDest; j++) {
                                a[j-1] = a[j];
                                hashed[j-1] = hashed[j];
                            }
                            a[newDest] = temp;
                            hashed[newDest] = true;
                            
                            System.out.println("moved " + a[newDest] + " to " + newDest);
                        }
                        else {
                            //ORIGINAL SWAP CORRECT
                            hashed[dest] = true;
                        }
                    }
                }
            }
            else {
                i++;
            }
        }
        
        //SHIFT FIX
        
        for (i=0; i < n-1; i++) {
            if (a[i] > a[i+1]) {
                System.out.println("fault @" + i);
            }
            
            
            /*
            j=i;
            temp = a[i+1];
             
            //SHIFT
            while (j > 0 && a[j] > temp) {
                a[j+1] = a[j];
                
                j--;
            }
            
            
            //PLACE
            a[j+1] = temp;
             */
        }
        
    }
    
    public static void main(String[] args) {
        n = 25;
        a = new int[n];
        hashed = new boolean[n];
        
        System.out.println("HashSort: sorts an array a[] of " + n + " elements, \n\tby first hashing each element to its estimated final position,\n\t and then passing through a[] again to fix the array using insertion sort.\n");
        
        fill();
        
        describe();
        
        min = a[0]; //initialize stats
        range = min;
        
        long before = System.nanoTime();
        hashSort();
        long after = System.nanoTime();
	
        double timeMS = ((double)(after-before)) / 1000000;
        System.out.println("runtime: " + timeMS + "ms");

        describe();
    }
}
