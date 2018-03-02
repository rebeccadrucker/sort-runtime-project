/*
 Title: InsertionSort.cpp
 
 Author: Owen Gallagher
 Date: 2/28/2018
 
 Description: implementation of insertion sort (first i elements in A are sorted)
*/

#include <iostream>
#include <math.h>
#include <ctime>
#include <iomanip>
#include <fstream>

using namespace std;

int N = 1000000; //number of elements in array A
int* A = new int[N];

int spot = 0; //space to insert in A
int temp = 0; //memory for swaps

void fill() {
    ifstream reader;
    reader.open("./random_numbers.txt");
    
    if (!reader) {
        cout << "ERROR: couldn't fill array" << endl;
        exit(1);
    }
    else {
        int i=0;
        int x;
        while (i<N && (reader >> x)) {
            A[i] = x;
            i++;
        }
        
        reader.close();
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
    //print();

    //sort A
	double before = clock();
	sort();
	double after = clock();

	double timeMS = (after-before) / (((double)CLOCKS_PER_SEC)/1000);

	cout.precision(6);
	cout << "runtime: " << timeMS << "ms" << endl;
    
    //print A AFTER
    //print();
    
    return 0;
}

