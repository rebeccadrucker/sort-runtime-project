//
//  Quick.cpp
//  
//
//  Created by Owen Gallagher on 2/21/18.
//
//

#include <iostream>
#include <math.h>
#include <ctime>
#include <iomanip>
#include <fstream>

using namespace std;
int n = 10000000;
int* A = new int[n];

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
    cout << "C++ quick sort of n=" << n << " elements." << endl;
    
    srand(time(NULL));
    
    fill();
    
//    describe();
    
    double before = clock();
    quickSort(0,n-1);
    double after = clock();
    double timeMS = (after - before) / (((double)CLOCKS_PER_SEC)/1000);
    
    cout.precision(6);
    cout  << "runtime: " << timeMS << "ms" << endl;
    
//    describe();
    
    delete [] A;
    
    return 0;
}
