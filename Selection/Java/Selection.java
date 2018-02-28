/* 
    Rebecca Drucker & Owen Gallagher
    SelectionSort.java
 */

public class Selection {
    static int N = 10;
    static int A[] = new int[N];
    
    static int minIndex = 0;   //next smallest
    static int temp = 0;       //for swaps
    
    public static void describe() {
        System.out.print("A: ");
        for (int i = 0; i < N; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    public static void selectionSort(){
        for (int i = 0; i < N - 1; i++){
            minIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (A[j] < A[minIndex]){
                    minIndex = j;
                }
            }

            temp = A[i];
            A[i] = A[minIndex];
            A[minIndex] = temp;
        }
    }

    public static void main(String[] args){
        System.out.println("JAVA selection sort of n=" + N + " elements.");
        
        // set up A
        for (int i = 0; i < N; i++){
            A[i] = (int)(Math.random() * 100);
        }

        // print unsorted A
        describe();

        selectionSort();

        // print sorted A
        describe();
    }
}
