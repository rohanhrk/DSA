import java.util.Scanner;

public class l004_Merge_Two_Sorted_Array {
    public static Scanner scn = new Scanner(System.in);

    public static int[] mergeTwoArray(int[] A, int[] B) {
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
    
    public static void input(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static void output(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[] A = new int[n];
        input(A);
        int[] B = new int[m];
        input(B);

        int[] mergeSortedArr = mergeTwoArray(A, B);
        output(mergeSortedArr);
    }
}
