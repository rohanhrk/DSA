import java.util.Scanner;
// import java.util.ArrayList;

// **********************_DATE:3/07/2021_**********************
public class l004 {
    public static Scanner scn = new Scanner(System.in);

    // target sum
    public static int findTargetSumWays(int[] arr, int idx, int s) {
        // Write your code here
        if (idx == arr.length) {
            return (s == 0) ? 1 : 0;
        }

        int count = 0;
        count += findTargetSumWays(arr, idx + 1, s - arr[idx]);
        count += findTargetSumWays(arr, idx + 1, s - (-arr[idx]));

        return count;
    }

    // s = {10,20,30,40,50,60,70}
    // setA (union) setB = S
    // setA (intersection) SetB = phi
    // sum of setA = sum of setB
    public static int equiSet(int[] arr, int idx, int sum1, int sum2, String setA, String setB) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(setA + " = " + setB);
                return 1;
            }

            return 0;
        }

        int count = 0;
        count += equiSet(arr, idx + 1, sum1 + arr[idx], sum2, setA + arr[idx] + " ", setB);
        count += equiSet(arr, idx + 1, sum1, sum2 + arr[idx], setA, setB + arr[idx] + " ");

        return count;

    }

    public static void main(String[] args) {
        int[] arr = { 20, 30, 10, 60, 50, 70, 90, 110 };
        System.out.println(equiSet(arr, 0, 0, 0, "", ""));
        // System.out.println(equiSet(arr, 1, arr[0], 0, arr[0] + " ", ""));
    }
}
