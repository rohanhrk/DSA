import java.util.Scanner;

public class l0010_countSort {
    public static Scanner scn = new Scanner(System.in);
    
    // if array contains only positive Number
    public static void countSortPositive(int[] arr, int minElem, int maxElem) {
        int size = maxElem - minElem + 1;
        int[] freqArr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            freqArr[arr[i]]++;
        }

        int itr = 0;
        for (int ptr = 0; ptr < freqArr.length; ptr++) {
            while (freqArr[ptr]-- > 0) {
                arr[itr++] = ptr;
            }
        }
    }

    // if array contains both Positive and Negative Number
    public static void countSort(int[] arr, int minElem, int maxElem) {
        int size = maxElem - minElem + 1;
        int[] freqArr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            freqArr[arr[i] - minElem]++;
        }

        int itr = 0;
        for (int ptr = 0; ptr < freqArr.length; ptr++) {
            while (freqArr[ptr]-- > 0) {
                arr[itr++] = ptr + minElem;
            }
        }
    }
    public static void print(int[] arr) {
        for(int elem : arr) {
            System.out.print(elem + " ");
        }
    }
    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        int max = (int)-1e9;
        int min = (int)1e9;
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        countSort(arr, min, max);
        print(arr);
    }
}
