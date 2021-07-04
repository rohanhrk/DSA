import java.util.Scanner;

public class l002_Selection_Sort {

    public static Scanner scn = new Scanner(System.in);

    // SELECTION SORT -> smallest element ko 1st index m place kara do
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void moveToStart(int[] arr, int si, int ei) {
        for (int i = si + 1; i <= ei; i++) {
            if (arr[si] > arr[i])
                swap(arr, si, i);
        }
    }

    public static void selectionSort(int arr[]) {
        int n = arr.length;
        int ei = n - 1;
        for (int i = 0; i < n - 1; i++) {
            moveToStart(arr, i, ei);
        }
    } 

    // ========================================================
    public static void output(int[] arr) {
        for(int a : arr) System.out.print(a + " ");
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, -8, 1, 18, 9, 4, 12, -15 };
        selectionSort(arr);
        output(arr);
    }
}
