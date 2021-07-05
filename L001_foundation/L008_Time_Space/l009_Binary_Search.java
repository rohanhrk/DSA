import java.util.Scanner;
import java.util.Arrays;

public class l009_Binary_Search {
    public static Scanner scn = new Scanner(System.in);

    public static int binarySearch(int[] arr, int data, int si, int ei) {
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                return mid;
            } else if (arr[mid] > data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return -1;
    }

    public static int closestElem(int[] arr, int data, int si, int ei) {

        while (si <= ei) {
            int mid = (si + ei) / 2;

            if (arr[mid] == data) {
                return arr[mid];
            } else if (arr[mid] < data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return arr[si] - data < data - arr[ei] ? arr[si] : arr[ei];
    }

    // target sum pair
    public static void sort(int[] arr) {
        Arrays.sort(arr);
    }

    public static void targetSumPair(int[] arr, int data, int si, int ei) {
        sort(arr);

        while (si < ei) {
            int sum = arr[si] + arr[ei];

            if (sum < data)
                si++;
            else if (sum > data)
                ei--;
            else {
                System.out.println(arr[si] + ", " + arr[ei]);
                si++;
                ei--;
            }
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int d = scn.nextInt();
        targetSumPair(arr, d, 0, n - 1);
    }
}