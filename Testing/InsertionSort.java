/*
 Author: Owen Gallagher
 Date: 2/5/2018
 
 Description: implementation of insertion sort (first i elements in A are sorted)
*/

import java.util.Scanner;
import java.lang.Math;

public class InsertionSort {
    public static int N = 1000000; //number of elements in array A
    public static int A[] = new int[N];
    
    public static int spot = 0; //space to insert in A
    public static int temp = 0; //memory for swaps
    
    public static void fill() {
        for (int i=0; i<N; i++) {
            A[i] = (int)Math.round(Math.random() * Integer.MAX_VALUE);
        }
    }
    
    public static void sort() {
        for (int next=1; next<N; next++) {
            spot = next-1;
            
            while (spot >= 0 && A[spot+1] < A[spot]) {
                temp = A[spot]; //perform swap
                A[spot] = A[spot+1];
                A[spot+1] = temp;
                
                spot--;
            }
        }
    }
    
    public static void print() {
        System.out.print("A: ");
        for (int i=0; i<N; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println("\n");
    }
    
    public static void main(String[] args) {
        System.out.println("This JAVA program performs insertion sort on an array of N=" + N + " ints.");
        
        //fill A
        fill();
        
        //print A BEFORE
        //print();
 
        //sort A
	long startTime = System.nanoTime();
        sort();
        long endTime = System.nanoTime();

	double estTime = ((double)endTime - startTime)/1000000;
	System.out.println("Program took " + estTime + " milliseconds to run.");

        //print A AFTER
        //print();
    }
}
