import java.util.Scanner;

public class l006_Sort_01 {
    public static Scanner scn = new Scanner(System.in);

    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping index " + i + " and index " + j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort01(int[] arr) {
        int n = arr.length;
        int ptr = 0; // pointer
        int itr = 0; // iterator

        while (itr < n) {
            if (arr[itr] == 0)
                swap(arr, itr++, ptr++);
            else
                itr++;
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
        sort01(arr);
        print(arr);
    }
}