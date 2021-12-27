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
    // ==============_maximum_==============
    // =====================================
    public static int maximumElem(int[] arr) {
        int maxElem = (int) -1e8;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxElem) {
                maxElem = arr[i];
            }
        }

        return maxElem;
    }

    // ==============_minimum_==============
    // =====================================
    public static int miniumElem(int[] arr) {
        int minElem = (int) 1e8;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minElem) {
                minElem = arr[i];
            }
        }

        return minElem;
    }

    // ==============_Find_Element_==============
    // ==========================================
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

    // ==============_first_index_==============
    // =========================================
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

    // ==============_last_index_==============
    // ========================================
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

    // ==============_span_of_array_==============
    // ===========================================
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

    // ==============_reverse_array_==============
    // ===========================================
    public static void reverseArray(int[] arr) {
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    // ==============_digit_frequency_==============
    // =============================================
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

    // ==============sum_of_two_arrays_==============
    // ==============================================
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

    // difference of two arrays
    public static void diffOfTwoArrays(int[] arr1, int[] arr2) {
        int p = arr1.length;
        int q = arr2.length;
        int r = Math.max(p, q);

        int[] ans = new int[r];

        int i = p - 1, j = q - 1, k = r - 1, borrow = 0;

        while (k >= 0) {
            int num = borrow;

            if (i >= 0)
                num += arr1[i--];
            if (j >= 0)
                num -= arr2[j--];

            if (num < 0) {
                num += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }

            ans[k--] = num;
        }

        boolean flag = false;
        for (int l = 0; l < ans.length; l++) {
            if (!flag && ans[l] == 0)
                continue;

            System.out.println(ans[l]);
            flag = true;
        }
    }

    // rotate an array by k
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

    // inverse of an array
    public static int[] inverseOfAnArrays(int[] arr) {
        int[] ans = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int j = arr[i];
            ans[j] = i;
        }

        return ans;
    }

    // subArrays
    public static void subArrays(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                for (int k = i; k <= j; k++) {
                    System.out.print(arr[k] + " ");
                }
                System.out.println();
            }
        }
    }

    // Binary Search
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