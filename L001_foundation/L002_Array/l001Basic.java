import java.util.Scanner;
import java.util.Arrays;

public class l001Basic {
    public static Scanner scn = new Scanner(System.in);

    public static void test1() {
        int n = scn.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void test2() {
        int[] arr = new int[10];
        Arrays.fill(arr, 88);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    // ===================================================================================================================================================
    // Question_1 : maximum
    public static int maximumElem(int[] arr) {
        int maxElem = (int) -1e8;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxElem) {
                maxElem = arr[i];
            }
        }

        return maxElem;
    }

    // ===================================================================================================================================================
    // Question_2 : minimum
    public static int miniumElem(int[] arr) {
        int minElem = (int) 1e8;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minElem) {
                minElem = arr[i];
            }
        }

        return minElem;
    }

    // ===================================================================================================================================================
    // Question_3 : Find_Element
    public static int findElem(int[] arr, int data) {
        int idx = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    // ===================================================================================================================================================
    // Question_4 : first_index
    public static int firstIndex(int[] arr, int data) {
        int idx = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    // ===================================================================================================================================================
    // Question_5 : last_index
    public static int lastIndex(int[] arr, int data) {
        int idx = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == data) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    // Question_6 : span_of_array
    public static int spanOfArray(int[] arr) {
        int maxElem = maximumElem(arr);
        int minElem = miniumElem(arr);

        return maxElem - minElem;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ===================================================================================================================================================
    // Question_7 : reverse_array
    public static void reverseArray(int[] arr) {
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    // ===================================================================================================================================================
    // Question_8 : digit_frequency
    public static int digitFrequency(int num, int data) {
        int digitCount = 0;

        while (num != 0) {
            int reminder = num % 10;
            num = num / 10;

            if (reminder == data) {
                digitCount++;
            }
        }

        return digitCount;
    }

    // ===================================================================================================================================================
    // Question_9 : sum_of_two_arrays
    public static void sumOfTwoArrays(int[] arr1, int[] arr2) {
        int p = arr1.length;
        int q = arr2.length;
        int r = Math.max(p, q) + 1;

        int[] ans = new int[r];
        int i = p - 1, j = q - 1, k = r - 1, carry = 0;

        while (k >= 0) {
            int sum = carry;
            if (i >= 0)
                sum += arr1[i--];
            if (j >= 0)
                sum += arr2[j--];

            ans[k--] = sum % 10;
            carry = sum / 10;
        }

        for (int l = 0; l < ans.length; l++) {
            if (l == 0 && ans[l] == 0)
                continue;

            System.out.println(ans[l]);
        }
    }

    // ===================================================================================================================================================
    // Question_10 : difference_of_two_arrays
    public static void diffOfTwoArrays(int[] arr1, int[] arr2) {
        // assumption => len(arr1) > len(arr2)
        int p = arr1.length;
        int q = arr2.length;
        int r = Math.max(p, q);

        int[] ans = new int[r];

        int i = p - 1, j = q - 1, k = r - 1, borrow = 0;

        while (k >= 0) {
            int num2 = (j >= 0) ? arr2[j--] : 0;
            int num1 = borrow + arr1[i--];

            int difference = (num1 >= num2) ? num1 - num2 : num1 + 10 - num2;
            borrow = (num1 >= num2) ? 0 : -1;

            ans[k--] = difference;
        }

        // edge case
        int idx = 0;
        while(ans[idx] == 0) {
            idx++;
        }

        // print ans
        while(idx < ans.length)
            System.out.println(ans[idx++]);
    }

    // ===================================================================================================================================================
    // Question_11 : 189. Rotate Array
    // https://leetcode.com/problems/rotate-array/
    public static void swapFunc(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void reverseFunc(int[] arr, int i, int j) {
        while (i < j) {
            swapFunc(arr, i, j);
            i++;
            j--;
        }
    }

    public static void rotate(int[] arr, int k) {
        int n = arr.length;
        k = (k % n + n) % n;
        reverseFunc(arr, 0, n - 1);
        reverseFunc(arr, 0, k - 1);
        reverseFunc(arr, k, n - 1);
    }

    // ===================================================================================================================================================
    // Question_12 : inverse of an array
    public static int[] inverseOfAnArrays(int[] arr) {
        int[] ans = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int j = arr[i];
            ans[j] = i;
        }

        return ans;
    }

    // ===================================================================================================================================================
    // Question_13 : subArrays
    public static void subArrays(int[] arr) {
        for (int si = 0; si < arr.length; si++) {
            for (int ei = si; ei < arr.length; ei++) {
                for (int t = si; t <= ei; t++) {
                    System.out.print(arr[t] + " ");
                }
                System.out.println();
            }
        }
    }

    // ===================================================================================================================================================
    // Question_14 : Binary Search
    public static int binarySearch(int[] arr, int data) {
        int si = 0;
        int ei = arr.length - 1;

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                return mid;
            } else if (arr[mid] < data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int data = scn.nextInt();

        System.out.println(binarySearch(arr, data));
    }
}