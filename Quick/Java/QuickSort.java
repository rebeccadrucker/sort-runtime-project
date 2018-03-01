/* Rebecca Drucker & Owen Gallagher
   QuickSort.java
 */

public class QuickSort {
    static final int N = 1000;
    static int A[] = new int[N];
    
    public static void describe() {
        System.out.print("A: ");
        
        for (int i=0; i<N; i++) {
            System.out.print(A[i] + " ");
        }
        
        System.out.println("\n");
    }

    public static int partition(int p, int r){
        int i,j;
        int x = A[r];
        int temp = 0;
        
        i = p-1;
        
        for (j=p ; j<=r-1 ; j++) {
            if (A[j] <= x) {
                i++;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        
        temp = A[i+1];
        A[i+1]=A[r];
        A[r]=temp;
        
        return i+1;
    }
    
    public static void quickSort(int p, int r){
        int q;

        if (p<r) {
            q = partition(p,r);
            quickSort(p,q-1);
            quickSort(q+1,r);
        }
    }

    public static void main(String[] args){
        System.out.println("JAVA quick sort of n=" + N + " elements.");
        
        // set up A
        for (int i = 0; i < N; i++){
            A[i] = (int)(Math.random() * Integer.MAX_VALUE);
        }

        //print unsorted A
//        describe();
        
        long before = System.nanoTime();
        quickSort(0, N-1);
        long after = System.nanoTime();
        
        double timeMS = ((double)(after-before)) / 1000000; //1ms = 1,000,000ns
        System.out.println("runtime: " + timeMS + "ms");

        // print sorted A
//        describe();
    }
}
