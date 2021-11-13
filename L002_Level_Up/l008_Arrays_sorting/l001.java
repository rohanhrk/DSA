import java.util.Arrays;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Collections;

public class l001 {

    // https://practice.geeksforgeeks.org/problems/marks-of-pcm2529/1
    public class marks implements Comparable<marks> {
        int phy;
        int chem;
        int math;

        marks(int phy, int chem, int math) {
            this.phy = phy;
            this.chem = chem;
            this.math = math;
        }

        public int compareTo(marks other) {
            if (this.phy != other.phy)
                return this.phy - other.phy; // increasing order
            else if (this.chem != other.chem)
                return other.chem - this.chem; // decreasing order
            else
                return this.math - other.math; // increasing order
        }
    }

    public void customSort(int phy[], int chem[], int math[], int N) {
        // your code here
        int n = phy.length;
        marks[] arr = new marks[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new marks(phy[i], chem[i], math[i]);
        }

        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            phy[i] = arr[i].phy;
            chem[i] = arr[i].chem;
            math[i] = arr[i].math;
        }

    }

    // https://practice.geeksforgeeks.org/problems/union-of-two-sorted-arrays/1
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        // add your code here
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (arr1[i] == arr2[j]) {
                if (res.size() == 0 || res.get(res.size() - 1) != arr1[i])
                    res.add(arr1[i]);
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                if (res.size() == 0 || res.get(res.size() - 1) != arr1[i])
                    res.add(arr1[i]);
                i++;
            } else {
                if (res.size() == 0 || res.get(res.size() - 1) != arr2[j])
                    res.add(arr2[j]);
                j++;
            }
        }

        while (i < n) {
            if (res.size() == 0 || res.get(res.size() - 1) != arr1[i])
                res.add(arr1[i]);
            i++;
        }

        while (j < m) {
            if (res.size() == 0 || res.get(res.size() - 1) != arr2[j])
                res.add(arr2[j]);
            j++;
        }

        return res;
    }

    // 74. Search a 2D Matrix
    public static int findRowIdx(int[][] matrix, int target) {
        int idx = -1;
        int lo = 0, hi = matrix.length - 1;

        while (lo <= hi) {
            int mid_row = (lo + hi) / 2; // mid index of row
            if (matrix[mid_row][0] <= target && target <= matrix[mid_row][matrix[0].length - 1]) {
                idx = mid_row;
                break;
            } else if (matrix[mid_row][0] > target) {
                hi = mid_row - 1;
            } else {
                lo = mid_row + 1;
            }
        }

        return idx;
    }

    public static int findInIthRow(int[][] matrix, int target, int row) {
        int idx = -1;
        int lo = 0, hi = matrix[0].length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (matrix[row][mid] == target) {
                idx = mid;
                break;
            } else if (matrix[row][mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return idx;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int rowIdx = findRowIdx(matrix, target);
        if (rowIdx == -1)
            return false;

        int colIdx = findInIthRow(matrix, target, rowIdx);
        return colIdx != -1;
    }

    // 240. Search a 2D Matrix II
    public boolean searchMatrix_2(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int r = 0, c = m - 1;

        while (r < n && c >= 0) {
            if (matrix[r][c] == target)
                return true;
            else if (matrix[r][c] < target)
                r++;
            else
                c--;
        }

        return false;
    }

    // 724. Find Pivot Index
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int total_sum = 0;
        for (int val : nums)
            total_sum += val;

        int left_sum = 0;
        for (int i = 0; i < n; i++) {
            total_sum -= nums[i];

            if (left_sum == total_sum)
                return i;

            left_sum += nums[i];
        }

        return -1;
    }

    // 658. Find K Closest Elements
    public static class pair implements Comparable<pair> {
        int val;
        int dis;

        pair(int val, int dis) {
            this.val = val;
            this.dis = dis;
        }

        public int compareTo(pair other) {
            if (this.dis != other.dis)
                return this.dis - other.dis;
            else
                return this.val - other.val;
        }
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<pair> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (i < k) {
                pq.add(new pair(arr[i], Math.abs(arr[i] - x)));
            } else {
                if (pq.peek().dis > Math.abs(arr[i] - x)) {
                    pq.remove();
                    pq.add(new pair(arr[i], Math.abs(arr[i] - x)));
                }
            }
        }

        while (pq.size() > 0) {
            res.add(pq.remove().val);
        }
        Collections.sort(res);
        return res;
    }

    // Find Transition Point
    // https://practice.geeksforgeeks.org/problems/find-transition-point/1
    public static int findTransition(int[] arr) {
        int tp = -1, n = arr.length;
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] == 0) {
                lo = mid + 1;
            } else if (arr[mid] == 1) {
                tp = mid;
                hi = mid - 1;
            }
        }

        return tp;
    }

    // 278. First Bad Version
    public static boolean isBadVersion(int version) {
        return true;
    }

    public static int firstBadVersion(int n) {
        // write your code here
        int lo = 1;
        int hi = n;
        int fbv = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (isBadVersion(mid)) {
                fbv = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return fbv;
    }

    // 374. Guess Number Higher or Lower
    // https://leetcode.com/problems/guess-number-higher-or-lower/submissions/
    static int pn = 0; // picked number

    public static int guess(int val) {
        if (val == pn) {
            return 0;
        } else if (val < pn) {
            return 1;
        } else {
            return -1;
        }
    }

    public int guessNumber(int n) {
        int lo = 1;
        int hi = n;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == -1) {
                hi = mid - 1;
            } else if (guess(mid) == 1) {
                lo = mid + 1;
            }
        }
        return -1;
    }

    // Find Pair With Given Difference
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/searching-and-sorting/find_pair_with_given_difference/ojquestion
    public static void findPair(int[] arr, int target) {
        // write your code here
        int n = arr.length;
        Arrays.sort(arr);
        int lo = 0;
        int hi = 1;

        while (hi < n && lo < hi) {
            int diff = arr[hi] - arr[lo];
            if (diff == target) {
                System.out.print(arr[lo] + " " + arr[hi]);
                return;
            } else if (diff > target) {
                lo++;
            } else {
                hi++;
            }
        }

        System.out.println("-1");
    }

    // https://practice.geeksforgeeks.org/problems/roof-top-1587115621/1
    public static int findMaxSteps(int[] arr) {
        // write your code here
        int n = arr.length;
        int max_step = 0;
        int count = 0;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                count++;
            } else {
                max_step = Math.max(count, max_step);
                count = 0;
            }
        }
        max_step = Math.max(count, max_step);
        return max_step;
    }

    // Maximize Sum Of Arr[i]*i Of An Array
    // https://practice.geeksforgeeks.org/problems/maximize-arrii-of-an-array0026/1#
    public static int maximise(int[] arr) {
        // write your code here
        Arrays.sort(arr);
        int n = arr.length;
        int sum = 0, mod = (int) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            sum = (sum % mod + (arr[i] % mod) * i) % mod;
        }

        return sum;
    }

}