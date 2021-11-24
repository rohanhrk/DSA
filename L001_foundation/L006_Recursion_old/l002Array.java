import java.util.Scanner;

public class l002Array {
    public static Scanner scn = new Scanner(System.in);

    // ====================================================================================================================================================================   
    // Question_1 : display arr
    // ========================
    public static void display(int[] arr, int idx) {
        if (idx == arr.length)
            return;
        System.out.println(arr[idx]);
        display(arr, idx + 1);
    }

    public static void input(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    // ====================================================================================================================================================================
    // Question_2 : display reverse
    // ============================
    public static void displayReverse(int[] arr, int idx) {
        if (idx == arr.length)
            return;
        display(arr, idx + 1);
        System.out.println(arr[idx]);
    }

    // ====================================================================================================================================================================
    // Question_3 : max of Array
    // =========================
    public static int maxOfArray(int[] arr, int idx) {
        if (idx == arr.length)
            return (int) -1e8;
        int smallArrayKaMax = maxOfArray(arr, idx + 1);
        return arr[idx] > smallArrayKaMax ? arr[idx] : smallArrayKaMax;
    }

    // ====================================================================================================================================================================
    // Question_4 : find element in array
    // ==================================
    public static int findElementInArray(int[] arr, int idx, int d) {
        if (idx == arr.length)
            return -1;
        
        if (arr[idx] == d) {
            return idx;
        }

        int smallIdx = findElementInArray(arr, idx + 1, d);
        return smallIdx;
    }

    // ====================================================================================================================================================================
    // Question_5 : first index
    // ========================
    public static int firstIndex(int[] arr, int idx, int d) {
        if (idx == arr.length)
            return -1;
        if (arr[idx] == d) {
            return idx;
        }

        int smallIdx = firstIndex(arr, idx + 1, d);
        return smallIdx;
    }
    
    // ====================================================================================================================================================================
    // Question_6 : last index
    // =======================
    public static int lastIndex(int[] arr, int idx, int d) {
        if (idx == arr.length)
            return -1;

        int ans = lastIndex(arr, idx + 1, d);
        if (ans != -1) {
            return ans;
        }
        return arr[idx] == d ? idx : -1;
    }
    
    // ====================================================================================================================================================================
    // Question_7 : all index
    // ======================
    public static int[] allIndices(int[] arr, int data, int idx, int fsf) {
        if (idx == arr.length)
            return new int[fsf];

        if (arr[idx] == data) {
            fsf++;
        }
        int[] ans = allIndices(arr, data, idx + 1, fsf);

        if (arr[idx] == data) {
            ans[fsf - 1] = idx;
        }

        return ans;

    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        input(arr);
        int[] aiArr = allIndices(arr, 19, 0, 0);
        System.out.println(aiArr);
    }
}