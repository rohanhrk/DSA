import java.util.Scanner;

public class l008_PartitionOfAnArray {
    public static Scanner scn = new Scanner(System.in);

    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }

    public static void partition(int[] arr, int pivot) {
        int n = arr.length;
        int ptr = 0; // pointer
        int itr = 0; // iterator

        while (itr < n) {
            if (arr[itr] <= pivot)
                swap(arr, itr++, ptr++);
            else
                itr++;
        }
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int pivot = scn.nextInt();
        partition(arr, pivot);
        print(arr);
    }
}