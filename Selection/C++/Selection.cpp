//
//  Selection.cpp
//  
//
//  Created by Owen Gallagher on 2/14/18.
//
//

#include <iostream>
#include <math.h>

using namespace std;
int n = 10;
int* A;

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
        int lowIndex = i;
        for (int j=i+1; j<n; j++) {
            if (A[j] < A[lowIndex]) {
                lowIndex = j;
            }
        }
        
        //swap ith lowest with ith element
        int temp = A[i];
        A[i] = A[lowIndex];
        A[lowIndex] = temp;
    }
}

int main() {
    A = new int[n];
    
    cout << "Selection sort of n=" << n << " elements." << endl;
    
    srand(time(nullptr));
    
    for (int i=0; i<n; i++) {
        A[i] = round(rand() % 100);
    }
    
    describe();
    selectionSort();
    describe();
    
    return 0;
}