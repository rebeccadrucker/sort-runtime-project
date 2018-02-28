/*
 Title: InsertionSort.cpp
 
 Author: Owen Gallagher
 Date: 2/28/2018
 
 Description: implementation of insertion sort (first i elements in A are sorted)
*/

#include <iostream>
#include <math.h>

using namespace std;

int N = 10; //number of elements in array A
int* A = new int[N];

int spot = 0; //space to insert in A
int temp = 0; //memory for swaps

void fill() {
    srand(time(nullptr));
    
    for (int i=0; i<N; i++) {
        A[i] = round(rand() % 100);
    }
}

void sort() {
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

void print() {
    cout << "A: ";
    for (int i=0; i<N; i++) {
        cout << A[i] << " ";
    }
    cout << endl;
}

int main() {
    cout << "This C++ program performs insertion sort on an array of N=" << N << " ints." << endl;
    
    //fill A
    fill();
   
    //print A BEFORE
    print();

    //sort A
    sort();
    
    //print A AFTER
    print();
    
    return 0;
}

