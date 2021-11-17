import java.util.Arrays;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

public class l001 {

    // ==============================================================================================
    // Question_1 : Marks Of PCM (GFG)
    // Link: https://practice.geeksforgeeks.org/problems/marks-of-pcm2529/1
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

    // ==============================================================================================
    // Question_2 : Union Of Two Sorted Array (GFG)
    // Link :
    // https://practice.geeksforgeeks.org/problems/union-of-two-sorted-arrays/1
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0, j = 0;

        // logic
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

        // out of the loop--->>> add unique elements of 'i' or 'j'
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

    // ==============================================================================================
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

    // Optimization
    public List<Integer> findClosestElements_optimize(int[] arr, int k, int x) {
        int n = arr.length;
        int lo = 0, hi = n - 1, min_dist_idx = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // everytime maintain closest value idx
            min_dist_idx = Math.abs(arr[mid] - x) < Math.abs(arr[min_dist_idx] - x) ? mid : min_dist_idx;
            if (arr[mid] == x) {
                break;
            } else if (arr[mid] > x) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        int left = min_dist_idx - 1;
        int right = min_dist_idx;
        LinkedList<Integer> res = new LinkedList<>();

        while (left >= 0 && right < n && k > 0) {
            if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                res.addFirst(arr[left]);
                left--;
            } else {
                res.addLast(arr[right]);
                right++;
            }
            k--;
        }

        while (left >= 0 && k > 0) {
            res.addFirst(arr[left]);
            left--;
            k--;
        }
        while (right < n && k > 0) {
            res.addLast(arr[right]);
            right++;
            k--;
        }

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

    // https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1
    int max_sum(int arr[], int n) {
        // Your code here

        int sum = 0;
        int sIm1 = 0; // s(i - 1)

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            sIm1 += arr[i] * i;
        }

        int max = sIm1;
        for (int i = 1; i < n; i++) {
            int sI = sIm1 + sum - n * arr[n - i];
            max = Math.max(sI, max);
            sIm1 = sI;
        }

        return max;
    }

    // 33. Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
        int n = nums.length;
        int lo = 0, hi = n - 1;
        int idx = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target) {
                idx = mid;
                break;
            } else if (nums[mid] < nums[hi]) {
                // right side is sorted
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                // left side is sorted
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }

        return idx;
    }

    // 153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int n = nums.length;
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[hi])
                hi = mid; // potential ans
            else
                lo = mid + 1; // discurded left side
        }

        return nums[hi];
    }

    // https://practice.geeksforgeeks.org/problems/rotation4723/1
    int findKRotation(int nums[], int n) {
        // code here
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[hi])
                hi = mid; // potential ans
            else
                lo = mid + 1; // discurded left side
        }

        return hi;
    }

    // Count Inversions
    // https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
    public static long count = 0;

    public static long[] mergeTwoArr(long[] left, long[] right) {
        int l1 = left.length, l2 = right.length;
        long[] res = new long[l1 + l2];
        int i = 0, j = 0, k = 0;
        while (i < l1 && j < l2) {
            if (left[i] <= right[j]) {
                res[k++] = left[i++];
            } else {
                count += (l1 - i);
                res[k++] = right[j++];
            }
        }

        while (i < l1) {
            res[k++] = left[i++];
        }

        while (j < l2) {
            res[k++] = right[j++];
        }

        return res;
    }

    public static long[] mergeSort(long[] arr, int lo, int hi) {
        if (lo == hi) {
            long[] base = { arr[lo] };
            return base;
        }
        int mid = lo + (hi - lo) / 2;
        long[] left_arr = mergeSort(arr, lo, mid);
        long[] right_arr = mergeSort(arr, mid + 1, hi);

        return mergeTwoArr(left_arr, right_arr);
    }

    public static long inversionCount(long arr[]) {
        count = 0;
        mergeSort(arr, 0, arr.length - 1);
        return count;
        // Your Code Here
    }

    // 4. Median of Two Sorted Arrays
    // brute force -> O(n + m)
    public static int[] mergeTwoArr(int[] left, int[] right) {
        int l1 = left.length, l2 = right.length;
        int[] res = new int[l1 + l2];
        int i = 0, j = 0, k = 0;
        while (i < l1 && j < l2) {
            if (left[i] <= right[j]) {
                res[k++] = left[i++];
            } else {
                res[k++] = right[j++];
            }
        }

        while (i < l1) {
            res[k++] = left[i++];
        }

        while (j < l2) {
            res[k++] = right[j++];
        }

        return res;
    }

    public static double find(int[] a1, int[] a2) {
        // write your code here
        int[] merged = mergeTwoArr(a1, a2);
        int n = merged.length;
        int lo = 0, hi = n - 1;
        int mid = lo + (hi - lo) / 2;
        double median = 0;

        if (n % 2 != 0) {
            median = merged[mid];
        } else {
            median = (merged[mid] + merged[mid + 1]) / 2.0;
        }
        return median;
    }

    // Optimised solution
    // O(log(min(n, m)))
    public double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) {
            int[] temp = a;
            a = b;
            b = temp;
        }
        int l1 = a.length, l2 = b.length;
        int total_elem = l1 + l2;

        int lo = 0, hi = l1;
        while (lo <= hi) {
            int aleft = lo + (hi - lo) / 2;
            int bleft = (total_elem + 1) / 2 - aleft;

            int al = (aleft == l1) ? (int) 1e9 : a[aleft];
            int alm1 = (aleft == 0) ? -(int) 1e9 : a[aleft - 1];
            int bl = (bleft == l2) ? (int) 1e9 : b[bleft];
            int blm1 = (bleft == 0) ? -(int) 1e9 : b[bleft - 1];

            // valid spliting
            if (alm1 <= bl && blm1 <= al) {
                double median = 0.0;
                int lmax = Math.max(alm1, blm1);
                int rmin = Math.min(al, bl);

                if (total_elem % 2 == 0) {
                    median = (lmax + rmin) / 2.0;
                } else {
                    median = lmax;
                }

                return median;
            } else if (alm1 > bl) {
                // there are more element to picked in left part from 'b' arr
                hi = aleft - 1;
            } else if (blm1 > al) {
                // there are more element to picked in left part from 'a' arr
                lo = aleft + 1;
            }
        }

        return 0;
    }

    // 875. Koko Eating Bananas
    private boolean isPossible(int[] piles, int speed, int h) {
        int time = 0;
        for (int val : piles)
            time += Math.ceil(val * 1.0 / speed);

        return time <= h;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int max = 0;
        for (int val : piles)
            max = Math.max(max, val);

        int lo = 1, hi = max;
        int min_k = (int) 1e9;
        while (lo <= hi) {
            int speed = lo + (hi - lo) / 2;
            if (isPossible(piles, speed, h) == true) {
                min_k = speed;
                hi = speed - 1;
            } else {
                lo = speed + 1;
            }

        }

        return min_k;
    }

    // 1283. Find the Smallest Divisor Given a Threshold
    private boolean isPossible_(int[] nums, int threshold, int divisor) {
        int sum = 0;
        for (int val : nums)
            sum += Math.ceil(val * 1.0 / divisor);

        return sum <= threshold;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int n = nums.length;
        int max = 0;
        for (int val : nums)
            max = Math.max(max, val);

        int lo = 1, hi = max;
        int min_divisor = (int) 1e9;
        while (lo <= hi) {
            int divisor = lo + (hi - lo) / 2;
            if (isPossible_(nums, threshold, divisor) == true) {
                min_divisor = Math.min(min_divisor, divisor);
                hi = divisor - 1;
            } else {
                lo = divisor + 1;
            }
        }

        return min_divisor;
    }

    // Allocate minimum number of pages
    // https://practice.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
    private static boolean isAllocationPossible(int[] page, int mid, int M) {
        int st_count = 1, sum = 0;
        for (int val : page) {
            sum += val;
            if (sum > mid) {
                st_count += 1;
                sum = val;
            }
        }

        return st_count <= M;
    }

    public static int findPages(int[] page, int n, int M) {
        // Your code here
        int max = -(int) 1e9, sum = 0;
        for (int val : page) {
            max = Math.max(max, val);
            sum += val;
        }

        int lo = max, hi = sum;
        int min = (int) 1e9;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isAllocationPossible(page, mid, M) == true) {
                min = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return min;
    }

    // 1011. Capacity To Ship Packages Within D Days
    private boolean isShipmentPossible(int[] weights, int days, int curr_burden) {
        int day_count = 1, sum = 0;
        for (int val : weights) {
            sum += val;
            if (sum > curr_burden) {
                day_count += 1;
                sum = val;
            }
        }

        return day_count <= days;
    }

    public int shipWithinDays(int[] weights, int days) {
        int max = -(int)1e9, sum = 0;
        for(int val : weights) {
            max = Math.max(max, val);
            sum += val;
        }
        
        int lo = max, hi = sum;
        int min_burden = (int)1e9;
        while(lo <= hi) {
            int curr_burden = lo + (hi - lo) / 2;
            if(isShipmentPossible(weights, days, curr_burden) == true) {
                min_burden = curr_burden;
                hi = curr_burden - 1;
            } else {
                lo = curr_burden + 1;
            }
        }
        
        return min_burden;
    }

    // 410. Split Array Largest Sum
    private boolean isSplitPossible(int[] nums, int m, int curr_sum) {
        int count_subarr = 1, sum = 0;
        for(int val : nums) {
            sum += val;
            if(sum > curr_sum) {
                count_subarr += 1;
                sum = val;
            }
        }
        
        return count_subarr <= m;
    }
    public int splitArray(int[] nums, int m) {
        int max = -(int)1e9, sum = 0;
        for(int val : nums) {
            max = Math.max(max, val);
            sum += val;
        }
        
        int lo = max, hi = sum, min_sum = (int)1e9;
        while(lo <= hi) {
            int curr_sum = lo + (hi - lo) / 2;
            if(isSplitPossible(nums, m, curr_sum)) {
                min_sum = curr_sum;
                hi = curr_sum - 1;
            } else {
                lo = curr_sum + 1;
            }
        }
        
        return min_sum;
    }
}
