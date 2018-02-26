/*
 Author: Owen Gallagher
 Date: 2/5/2018
 
 Description: implementation of insertion sort (first i elements in A are sorted)
*/

import java.util.Scanner;
import java.lang.Math;

public class InsertionSort {
    public static int N = 5; //number of elements in array A
    public static int A[] = new int[N];
    public static int timer = 0;
    
    static Scanner scanner = new Scanner(System.in); //for user input
    
    public static void main(String[] args) {
        System.out.println("This JAVA program performs insertion sort on an array of N=" + N + " ints.");
        
        //fill A
        fill();
       
	//print A BEFORE
	print();
 
        //sort A
        sort();
        
        //print A AFTER
        print();
    }
    
    public static void fill() {
        for (int i=0; i<N; i++) {
            A[i] = (int)Math.round(Math.random() * 100);
            System.out.println("A[" + i + "]: " + A[i]);
        }
    }
    
    public static void sort() {
        int spot = 0; //space to insert in A
        int temp = 0; //memory for swapping elements
        
        for (int next=1; next<N; next++) {
            spot = next-1;
            
            while (spot >= 0 && A[spot+1] < A[spot]) {                
                temp = A[spot]; //perform swap
                A[spot] = A[spot+1];
                A[spot+1] = temp;
                
                spot--;
                timer++;
            }
        }
    }
    
    public static void print() {
        System.out.print("A: ");
        for (int i=0; i<N; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println("\nTimer: " + timer + "\n");
        
    }
}
