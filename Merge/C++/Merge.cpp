//
//  Merge.cpp
//  
//
//  Created by Owen Gallagher & Rebecca Drucker on 2/14/18.
//
//

#include <iostream>
#include <math.h>

using namespace std;
int n = 10;
int* A;

int* B;

void describe() {
    cout << "A: ";
    
    for (int i=0; i<n; i++) {
        cout << A[i] << " ";
    }
    
    cout << endl;
}

void merge(int start, int end) { //needs local vars because of recursion
    int length = end-start;
    
    if (length > 1) {
        int middle = floor((start+end) / 2);
        
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
    }
}

void mergeSort(int start,int end) {
    if ((end-start) > 1) {
        int middle = ceil((start+end)/2);
        
        if (end-start > 2) {
            mergeSort(start,middle);
            mergeSort(middle,end);
        }
        merge(start,end);
    }
}

int main() {
    A = new int[n];
    B = new int[n];
    
    cout << "C++ merge sort of n=" << n << " elements." << endl;
    
    srand(time(nullptr));
    
    for (int i=0; i<n; i++) {
        A[i] = round(rand() % 100);
    }
    
    describe();
    mergeSort(0,n);
    describe();
    
    delete [] A;
    delete [] B;
    
    return 0;
}
