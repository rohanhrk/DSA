import java.util.Scanner;

public class l007_Sort_012 {
    public static Scanner scn = new Scanner(System.in);

    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping index " + i + " and index " + j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort012(int[] arr) {
        int n = arr.length;
        int ptr1 = 0; // number 1 ke liye pointer
        int ptr2 = n - 1; // number 2 ke liye pointer
        int itr = 0; // iterator

        while (itr <= ptr2) {
            if (arr[itr] == 0)
                swap(arr, itr++, ptr1++);
            else if (arr[itr] == 1)
                itr++;
            else
                swap(arr, itr, ptr2--);
        }
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        sort012(arr);
        print(arr);
    }
}
