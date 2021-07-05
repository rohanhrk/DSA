import java.util.Scanner;


public class l005_Merge_Sort {
    public static Scanner scn = new Scanner(System.in);

    public static int[] mergeTwoArray(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0)
            return A.length == 0 ? B : A;
        int n = A.length;
        int m = B.length;
        int[] ans = new int[n + m];

        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            if (A[i] < B[j])
                ans[k++] = A[i++];
            else
                ans[k++] = B[j++];

        }

        while (i < n)
            ans[k++] = A[i++];
        while (j < m)
            ans[k++] = B[j++];

        return ans;
    }

    public static int[] mergeSort_(int[] arr, int si, int ei) {
        if(si == ei) {
            int[] base = new int[1];
            base[0] = arr[si];
            return base;
        }
        int mid = (si + ei) / 2;

        int[] leftMergeSortArr = mergeSort_(arr, si, mid);
        int[] rightMergeSortArr = mergeSort_(arr, mid + 1, ei);

        return mergeTwoArray(leftMergeSortArr, rightMergeSortArr);
    }

    public static int[] mergeSort(int[] arr) {
        return mergeSort_(arr, 0 , arr.length-1);
       
    }

    public static void input(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static void output(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        input(arr);
        int[] mergeSort = mergeSort(arr);
        output(mergeSort);
    }
}
