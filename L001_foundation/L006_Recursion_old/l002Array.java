import java.util.Scanner;

public class l002Array {
    public static Scanner scn = new Scanner(System.in);
    // **************************************************************** DATE - 28/06 ****************************************************************
    // display arr
    public static void display(int[] arr, int idx) {
        if(idx == arr.length) return;
        System.out.println(arr[idx]);
        display(arr, idx + 1);
    }
    public static void input(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    // display reverse 
    public static void displayReverse(int[] arr, int idx) {
        if(idx == arr.length) return;
        display(arr, idx + 1);
        System.out.println(arr[idx]);
    }

    // max of Array
    public static int maxOfArray(int[] arr, int idx) {
        if(idx == arr.length) return (int)-1e8;
        int smallArrayKaMax = maxOfArray(arr, idx + 1); 

        return Math.max(arr[idx], smallArrayKaMax);
    }

    // find element in array
    public static int findElementInArray(int[] arr, int idx, int d) {
        if(idx == arr.length) return -1;
        if(arr[idx] == d) {
            return idx;
        } 
        int smallIdx = findElementInArray(arr, idx + 1, d);
    
        return smallIdx;
    }

    // first index
    public static int firstIndex(int[] arr, int idx, int d) {
        if(idx == arr.length) return -1;
        if(arr[idx] == d) {
            return idx;
        } 
        int smallIdx = firstIndex(arr, idx + 1, d);
    
        return smallIdx;
    }

    public static int lastIndex(int[] arr, int idx, int d) {
        if(idx == arr.length) return -1;

        int ans = lastIndex(arr,idx+1,d);
        if(ans != -1) {
            return ans;
        } 
        return arr[idx] == d ? idx : -1;
    }

    public static int[] allIndices(int[] arr, int data, int idx, int fsf) {
        if(idx == arr.length) return new int[fsf];
        
        if(arr[idx] == data) {
            fsf++;
        }
        int[] ans = allIndices(arr, data, idx+1, fsf);
        
        if(arr[idx] == data) {
            ans[fsf-1] = idx;
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