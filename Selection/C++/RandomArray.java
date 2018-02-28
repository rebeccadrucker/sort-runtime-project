/*
 
 Owen Gallagher & Rebecca Drucker
 28 February 2018
 
 RandomArray generates a text file with an array of random numbers in the range [0..Integer.MAX_VALUE]
 
 */

import java.lang.Math;
import java.io.PrintWriter;

public class RandomArray {
    static int n = 10000000;
    static int value = 0;
    static PrintWriter printer;
    
    
    public static void main(String[] args) {
        System.out.println("Creating array of length " + n + "...");
        
        try {
            printer = new PrintWriter("random_numbers.txt","UTF-8");
            
            for (int i=0; i<n; i++) {
                value = Math.round((int) (Math.random() * Integer.MAX_VALUE));
                
                printer.println(value);
            }
            
            printer.close();
            
            System.out.println("DONE!\n");
        }
        catch (Exception e) {
            System.out.println("FAILED :(");
        }
    }
}