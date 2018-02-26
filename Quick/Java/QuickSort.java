/* Rebecca Drucker & Owen Gallagher
   QuickSort.java
 */

public class QuickSort {
    static final int N = 5;
    static double A[] = new double[N];

    public static void quickSort(int p, int r){
        int q;

        if (p<r) {
            q = partition(p,r);
            quickSort(p,q-1);
            quickSort(q+1,r);
        }
    }

    public static int partition(int p, int r){
        int i,j;

        double x = A[r];

        i = p-1;

        for (j=p ; j<=r-1 ; j++) {
            if (A[j] <= x) {
                i++;
                double temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }

        double temp = A[i+1];
        A[i+1]=A[r];
        A[r]=temp;

        return i+1;
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

        quickSort(0, N-1);

        // print sorted A
        for (int i = 0; i < A.length; i++){
            System.out.println(A[i]);
        }
    }
}
