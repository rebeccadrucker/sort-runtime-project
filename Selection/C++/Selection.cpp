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

using namespace std;

int n = 10;
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

int main() {
    A = new int[n];
    
    cout << "C++ selection sort of n=" << n << " elements." << endl;
    
    //fill A
    srand(time(nullptr));
    
    for (int i=0; i<n; i++) {
        A[i] = round(rand() % 100);
    }
    
    //unsorted
    describe();
    
    selectionSort();
    
    //sorted
    describe();
    
    return 0;
}