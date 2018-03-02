//
//  Selection.cpp
//  
//
//  Owen Gallagher & Rebecca Drucker
//  2/14/18
//
//

#include <iostream>
#include <math.h>
#include <ctime>
#include <iomanip>
#include <fstream>

using namespace std;

int n = 1000000;
int* A;

int minIndex = 0;   //next smallest
int temp = 0;       //for swaps

void describe() {
    cout << "A: ";
    
    for (int i=0; i<n; i++) {
        cout << A[i] << " ";
    }
    
    cout << endl;
}

void selectionSort() {
    for (int i=0; i<n-1; i++) {
        //find next lowest
        int minIndex = i;
        for (int j=i+1; j<n; j++) {
            if (A[j] < A[minIndex]) {
                minIndex = j;
            }
        }
        
        //swap ith lowest with ith element
        temp = A[i];
        A[i] = A[minIndex];
        A[minIndex] = temp;
    }
}

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
        while (i<n && (reader >> x)) {
            A[i] = x;
            i++;
        }
        
        reader.close();
    }
}

int main() {
    A = new int[n];
    
    cout << "C++ selection sort of n=" << n << " elements." << endl;
    
    //fill A
    fill();
    
    //unsorted
//    describe();
    
    double before = clock();
    selectionSort();
    double after = clock();
    
    double timeMS = (after-before) / (((double)CLOCKS_PER_SEC)/1000);
    
    cout.precision(6);
    cout << "runtime: " << timeMS << "ms" << endl;
    
    //sorted
//    describe();
    
    return 0;
}
