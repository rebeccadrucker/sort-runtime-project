/*

 Author: Owen Gallagher & Rebecca Drucker
 Date: 26 February 2018
 
 Description: Attempt to make a sort that performs better than the
    selection, insertion, merge, quick, and heap sorting algorithms.

*/

import java.lang.Math;

public class HashSort {
    static int n;                //size of array
    static int[] a;              //array to be sorted
    
    static int i=0;              //iterators
    static int j=0;
    
    static int temp;             //for swaps
    
    static int min;              //describe the data set for the hash function
    static int range;
    
    static boolean[] hashed;     //remember which elements have already been hashed
    static int dest;             //hash function output
    static int nMinus1;
    
    static int newDest;          //taking care of collisions
    static boolean confirmed;
    
    public static void fill() {
        int value;
        
        for (int i=0; i<n; i++) {
            value = (int) Math.round(Math.random() * Integer.MAX_VALUE);
            a[i] = value;
            
            hashed[i] = false;
        }
    }
    
    public static void describe(boolean showAll) {
        for (int i=0; i<n; i++) {
            if (hashed[i] || showAll) {
                System.out.print(" " + a[i] + " ");
            }
            else {
                System.out.print("   ");
            }
        }
        System.out.println("\n");
    }
    
    public static void checkLeft(int start, int startValue) { //move j from start to first hashed element > startValue, stores result in newDest
        j=start;
        if (a[start] > startValue) {
            newDest = start;
        }
        else {
            newDest = -1;
        }
        confirmed = false;
        
        while (!confirmed) {
            while (j>0 && !hashed[j]) {
                j--;
            }
            
            if (a[j] > startValue) {
                if (hashed[j]) {
                    newDest = j;
                }
                
                if (j==0) {
                    confirmed = true;
                }
                else {
                    j--;
                }
            }
            else {
                confirmed = true;
            }
        }
    }
    
    public static void checkRight(int start, int startValue) { //move j from start to last hashed element < startValue, stores result in newDest
        j=start;
        if (a[start] < startValue) {
            newDest = start;
        }
        else {
            newDest = -1;
        }
        confirmed = false;
        
        while (!confirmed) {
            while (j<nMinus1 && !hashed[j]) {
                j++;
            }
            
            if (a[j] < startValue) {
                if (hashed[j]) {
                    newDest = j;
                }
                
                if (j == nMinus1) {
                    confirmed = true;
                }
                else {
                    j++;
                }
            }
            else {
                confirmed = true;
            }
        }
    }
    
    public static void shiftLeft(int start, int end) {
        j=start+1;
        while (j <= end) {
            a[j-1] = a[j];
            hashed[j-1] = hashed[j];
            
            j++;
        }
    }
    
    public static void shiftRight(int start, int end) {
        j=start-1;
        while (j >= end) {
            a[j+1] = a[j];
            hashed[j+1] = hashed[j];
            
            j--;
        }
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
        while (i < n) {
            if (!hashed[i]) {
                //HASH FUNCTION
                temp = a[i];
                dest = (int)(((double) (temp - min)) / range * nMinus1);
                
                if (dest != i && hashed[dest]) { //check if blocked
                    //collision
                    //System.out.print("shifted " + a[i] + "@" + i + " to ");
                    
                    if (a[i] < a[dest]) {
                        checkLeft(dest,a[i]);
                        
                        if (newDest == -1) {
                            //System.out.println("nowhere");
                        }
                        else {
                            //shift right from i & place <@newDest
                            temp = a[i];
                            if (i < newDest) {
                                shiftLeft(i,newDest-1);
                                
                                a[newDest-1] = temp;
                                hashed[newDest-1] = true;
                            }
                            else {
                                shiftRight(i,newDest);
                                
                                a[newDest] = temp;
                                hashed[newDest] = true;
                            }

                            //System.out.println("@" + newDest);
                        }
                    }
                    else if (a[i] > a[dest]) {
                        checkRight(dest,a[i]);
                        
                        if (newDest == -1) {
                            //System.out.println("nowhere");
                        }
                        else {
                            //shift left from i & place @newDest>
                            temp = a[i];
                            if (i > newDest) {
                                shiftRight(i,newDest+1);
                                
                                a[newDest+1] = temp;
                                hashed[newDest+1] = true;
                            }
                            else {
                                shiftLeft(i,newDest);
                                
                                a[newDest] = temp;
                                hashed[newDest] = true;
                            }
                            
                            //System.out.println("@" + newDest);
                        }
                    }
                    else {
                        if (i < dest) {
                            //shift left from i & place <@dest
                            temp = a[i];
                            shiftLeft(i,dest-1);
                            
                            a[dest-1] = temp;
                            hashed[dest-1] = true;
                            
                            //System.out.println(a[dest] + "@" + dest);
                        }
                        else {
                            //shift right from i & place @newDest>
                            temp = a[i];
                            shiftRight(i,dest+1);
                            
                            a[dest+1] = temp;
                            hashed[dest+1] = true;
                            
                            //System.out.println(a[dest] + "@" + dest);
                        }
                    }
                }
                else {
                    //swap
                    a[i] = a[dest];
                    a[dest] = temp;
                    
                    //if (dest == i) {
                        //System.out.println("swapped " + a[dest] + " with itself");
                    //}
                    //else {
                        //System.out.println("swapped " + a[dest] + " with " + a[i]);
                    //}
                    
                    checkLeft(dest, a[dest]);
                    
                    if (newDest != -1) {
                        //shift left from dest to newDest, place @newDest
                        temp = a[dest];
                        
                        for (j=dest-1; j>=newDest; j--) {
                            a[j+1] = a[j];
                            hashed[j+1] = hashed[j];
                        }
                        
                        a[newDest] = temp;
                        hashed[newDest] = true;
                        
                        //System.out.println("moved " + a[newDest] + " to " + newDest);
                    }
                    else {
                        checkRight(dest, a[dest]);
                        
                        if (newDest != -1) {
                            //shift right from dest to newDest, place @newDest
                            temp = a[dest];
                            for (j=dest+1; j<=newDest; j++) {
                                a[j-1] = a[j];
                                hashed[j-1] = hashed[j];
                            }
                            a[newDest] = temp;
                            hashed[newDest] = true;
                            
                            //System.out.println("moved " + a[newDest] + " to " + newDest);
                        }
                        else {
                            //original swap correct
                            hashed[dest] = true;
                        }
                    }
                }
            }
            else {
                i++;
            }
//            describe(false);
        }
        
    }
    
    public static void main(String[] args) {
        n = 5000000;
        nMinus1 = n-1;
        a = new int[n];
        hashed = new boolean[n];
        
        System.out.println("HashSort: sorts an array a[] of " + n + " elements, \n\tby first hashing each element to its estimated final position,\n\t and then passing through a[] again to fix the array using insertion sort.\n");
        
        fill();
        
        //describe(true);
        
        min = a[0]; //initialize stats
        range = min;
        
        long before = System.nanoTime();
        hashSort();
        long after = System.nanoTime();
	
        double timeMS = ((double)(after-before)) / 1000000;
        System.out.println("runtime: " + timeMS + "ms");

        //describe(false);
    }
}
