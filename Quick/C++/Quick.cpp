//
//  Quick.cpp
//  
//
//  Created by Owen Gallagher on 2/21/18.
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

int partition(int start, int end) { //return index of single sorted element
    int i = start-1;
    int temp = 0;
    
    for (int j=start; j<end; j++) {
        if (A[j] < A[end]) {
            i++;
            
            temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }
    
    temp = A[i+1]; //move sorted element to "middle"
    A[i+1] = A[end];
    A[end] = temp;
    
    for (int j=0; j<n; j++) {
        if (j < start || j > end) {
            if (A[j] <= 9) {
                cout << ". ";
            }
            else {
                cout << ".. ";
            }
        }
        else {
            cout << A[j] << " ";
        }
    }
    cout << endl;
    
    return i+1;
}

void quickSort(int start,int end) { //get sorted element, sort left, sort right
    int middle = partition(start,end);
    
    if (middle > start) {
        quickSort(start,middle-1);
    }
    if (middle < end) {
        quickSort(middle+1,end);
    }
    
}

int main() {
    A = new int[n];
    
    cout << "Quick sort of n=" << n << " elements." << endl;
    
    srand(time(nullptr));
    
    for (int i=0; i<n; i++) {
        A[i] = round(rand() % 100);
    }
    
    describe();
    quickSort(0,n-1);
    describe();
    
    delete [] A;
    
    return 0;
}
