import java.util.Scanner;

public class l003_Insertion_Sort {
    public static Scanner scn = new Scanner(System.in);

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void moveToStart(int[] arr, int si, int ei) {
        for (int i = si; i > ei; i--) {
            if (arr[i] < arr[i - 1])
                swap(arr, i, i - 1);
            else
                break;

        }
    }

    public static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int si = i;
            int ei = 0;
            moveToStart(arr, si, ei);
        }
    }
    public static void intput(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static void output(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        intput(arr);
        insertionSort(arr);
        output(arr);
    }
}
