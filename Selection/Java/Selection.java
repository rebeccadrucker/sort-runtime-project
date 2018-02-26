/* Rebecca Drucker & Owen Gallagher
   SelectionSort.java
 */

public class SelectionSort {
    static int N = 5;
    static int A[] = new int[N];

    public static void selectionSort(){
        for (int i = 0; i < A.length - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[minIndex]){
                    minIndex = j;
                }
            }

            int temp = A[i];
            A[i] = A[minIndex];
            A[minIndex] = temp;
        }
    }

    public static void main(String[] args){
        // set up A
        for (int i = 0; i < A.length; i++){
            A[i] = (int)(Math.random() * 100);
        }

        // print unsorted A
        for (int i = 0; i < A.length; i++){
            System.out.println(A[i]);
        }

        System.out.println();

        selectionSort();

        // print sorted A
        for (int i = 0; i < A.length; i++){
            System.out.println(A[i]);
        }
    }
}
