import java.util.Scanner;

public class l001_Bubble_Sort {
    public static Scanner scn = new Scanner(System.in);
    // BUBBLE SORT ->  largest element ko last index me add kara do
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    public static void moveToLast(int[] arr, int si, int ei) {
        for (int i = si + 1; i <= ei; i++) {
            if (arr[i - 1] > arr[i])
                swap(arr, i - 1, i);
        }
    }

    public static void bubbleSort(int arr[], int n) {
        int ei = n - 1;
        for (int i = 0; i < n; i++) {
            moveToLast(arr, 0, ei - i);
        }

    }
    
    // ========================================================
    public static void output(int[] arr) {
        for(int a : arr) System.out.print(a + " ");
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, -8, 1, 18, 9, 4, 12, -15 };
        bubbleSort(arr);
        output(arr);
    }
}