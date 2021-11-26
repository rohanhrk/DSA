import java.util.Scanner;

public class heapSort {
    // ===========================================================================================================================================================
    private static void swap(int[] arr, int si, int li) {
        int temp = arr[si];
        arr[si] = arr[li];
        arr[li] = temp;
    }

    private static int compareTo(int[] arr, int a, int b, boolean isMax) {
        if (isMax)
            return arr[a] - arr[b];
        else
            return arr[b] - arr[a];
    }

    // O(log(n))
    private static void heapify(int[] arr, int pi, int li, boolean isMax) {
        int max_idx = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if (lci <= li && compareTo(arr, lci, max_idx, isMax) > 0)
            max_idx = lci;
        if (rci <= li && compareTo(arr, rci, max_idx, isMax) > 0)
            max_idx = rci;

        if (pi != max_idx) {
            swap(arr, pi, max_idx);
            heapify(arr, max_idx, li, isMax);
        }
    }

    private static void heap_sort(int[] arr) {
        int n = arr.length;
        boolean isMax = false;

        for (int i = n - 1; i >= 0; i--) // O(n)
            heapify(arr, i, n - 1, isMax);
        
        int li = n - 1;
        while (li >= 0) {
            swap(arr, 0, li--);
            heapify(arr, 0, li, isMax);
        }
    }
    // ===========================================================================================================================================================
    
    private static void display(int[] arr) {
        for (int val : arr)
            System.out.print(val + " ");
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        heap_sort(arr);
        display(arr);
    }
}