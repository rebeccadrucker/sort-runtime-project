//
//  Merge.java
//  
//
//  Created by Owen Gallagher & Rebecca Drucker on 2/25/18.
//
//

import java.lang.Math;

public class Merge {
    static int n = 1000;
    static int[] A = new int[n];
    
    static int[] B = new int[n];

    public static void describe() {
        System.out.print("A: ");
        
        for (int i=0; i<n; i++) {
            System.out.print(A[i] + " ");
        }
        
        System.out.print("\n");
    }

    public static void merge(int start, int end) {
        int length = end-start;
        
        if (length > 1) {
            int middle = (int) Math.floor((start+end) / 2);
            
            int startL = start;
            int endL = middle;
            int l=startL;
            
            int startR = middle;
            int endR = end;
            int r=startR;
            
            for (int i=start; i<end; i++) {
                if (l < endL) {
                    if (r >= endR || A[l] <= A[r]) {
                        B[i-start] = A[l];
                        l++;
                    }
                    else if (r < endR) {
                        B[i-start] = A[r];
                        r++;
                    }
                }
                else if (r < endR) {
                    B[i-start] = A[r];
                    r++;
                }
            }
            
            for (int i=start; i<end; i++) {
                A[i] = B[i-start];
            }
            
            //System.out.print("merge ");
            
            /*for (int i=0; i<n; i++) {
                if (i < start) {
                    System.out.print(".");
                }
                else if (i == start || i == end-1) {
                    System.out.print("|");
                }
                else if (i > start && i < end-1) {
                    System.out.print("_");
                }
                else {
                    System.out.print(".");
                }
            }*/
            
            //System.out.print("\n");
        }
        else {
            //System.out.println("TRIVIAL MERGE: " + start + ".." + end);
        }
    }

    public static void mergeSort(int start,int end) {
        if ((end-start) > 1) {
            int middle = (int) Math.ceil((start+end)/2);
            
            if (end-start > 2) {
                mergeSort(start,middle);
                mergeSort(middle,end);
            }
            merge(start,end);
        }
    }

    public static void main(String[] args) {
        
        //System.out.println("JAVA merge sort of n=" + n + " elements.");
        
        for (int i=0; i<n; i++) {
            A[i] = (int) Math.round(Math.random() * Integer.MAX_VALUE);
        }
        
        //describe();
		long startTime = System.nanoTime();
        mergeSort(0,n);
		long endTime = System.nanoTime();
		double estTime = ((double)endTime - startTime)/1000000;
		System.out.println("Program took " + estTime + " milliseconds to run.");
        //describe();
    }
}
