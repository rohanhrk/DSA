import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Arrays;
import java.util.Stack;
import java.util.PriorityQueue;

public class l001_Arrays {
    // 925. Long Pressed Name
    public boolean isLongPressedName(String name, String typed) {
        int n = name.length(), m = typed.length();
        if (n > m)
            return false;

        int i = 0; // pointer for name
        int j = 0; // pointer for typed

        while (i < n && j < m) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else {
                if (i > 0 && name.charAt(i - 1) == typed.charAt(j)) {
                    j++;
                } else {
                    return false;
                }
            }
        }

        while (j < m) {
            if (name.charAt(i - 1) != typed.charAt(j))
                return false;
            j++;
        }

        return i == n;
    }

    // 11. Container With Most Water
    public int maxArea(int[] height) {
        int n = height.length;
        int i = 0, j = n - 1; // with i, j pair, forms a container

        int most_water = 0; // maximum volume
        while (i < j) {
            // l -> length, b -> breadth, h -> height
            int l = (j - i), b = 1, h = Math.min(height[i], height[j]);
            int current_volume = l * b * h;
            most_water = Math.max(most_water, current_volume);

            if (height[i] < height[j])
                i++;
            else {
                j--;
            }
        }

        return most_water;
    }

    // 977. Squares of a Sorted Array
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res_arr = new int[n];

        int i = 0;
        int j = n - 1;

        for (int k = n - 1; k >= 0; k--) {
            if (nums[i] * nums[i] >= nums[j] * nums[j]) {
                res_arr[k] = nums[i] * nums[i];
                i++;
            } else {
                res_arr[k] = nums[j] * nums[j];
                j--;
            }
        }

        return res_arr;
    }

    // ========================================_MAJORITY_SERIES(MOORE'S_VOTING_ALGO)_========================================
    // 169. Majority Element
    public int majorityElement_01(int[] nums) {
        int n = nums.length;
        int val = nums[0];
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] == val) {
                count++;
            } else {
                if (count > 0) {
                    count--;
                } else {
                    // reset variable
                    val = nums[i];
                    count = 1;
                }
            }
        }

        return val;
    }

    // 229. Majority Element II
    private boolean isMajority(int[] arr, int val) {
        int n = arr.length;
        int count = 0;
        for (int ele : arr)
            if (ele == val)
                count++;

        return count > n / 3;
    }

    public List<Integer> majorityElement_02(int[] nums) {
        int n = nums.length;

        int val1 = nums[0];
        int count1 = 1;
        int val2 = nums[0]; // take any number
        int count2 = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] == val1) {
                count1++;
            } else if (nums[i] == val2) {
                count2++;
            } else {
                if (count1 == 0) {
                    val1 = nums[i];
                    count1 = 1;
                } else if (count2 == 0) {
                    val2 = nums[i];
                    count2 = 1;
                } else {
                    count1--;
                    count2--;
                }
            }
        }

        // check if val1 and val2 have more than n/3 frequency
        ArrayList<Integer> res = new ArrayList<>();
        if (isMajority(nums, val1))
            res.add(val1);

        if (val2 != val1 && isMajority(nums, val2))
            res.add(val2);

        return res;
    }

    // Majority Element General (pepcoding)_=======================================
    public static ArrayList<Integer> majorityElement_03(int[] arr, int k) {
        // write yout code here
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();

        int n = arr.length;
        for (int key : arr) {
            if (!map.containsKey(key))
                map.put(key, 1);

            else {
                int count = map.get(key);
                map.put(key, count + 1);
            }
        }

        for (int key : map.keySet())
            if (map.get(key) > n / k)
                res.add(key);

        Collections.sort(res);
        return res;
    }

    // =================================_556. Next Greater Element
    // III_============================================
    private int findDipIndex(int[] arr) {
        int idx = -1, n = arr.length;
        for (int i = n - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i]) {
                idx = i - 1;
                break;
            }
        }

        return idx;
    }

    private int findCeilIndex(int[] arr, int dip_idx) {
        int idx = arr.length - 1;
        while (arr[idx] <= arr[dip_idx]) {
            idx--;
        }

        return idx;
    }

    private void swap(int[] arr, int si, int ei) {
        int temp = arr[si];
        arr[si] = arr[ei];
        arr[ei] = temp;
    }

    private void reverse(int[] arr, int si, int ei) {
        while (si < ei) {
            swap(arr, si, ei);

            si++;
            ei--;
        }
    }

    public int nextGreaterElement(int n) {
        String str = Integer.toString(n); // convert interger to string
        int size = str.length();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = str.charAt(i) - '0';

        // step 1 ----> find dip index (traverse from LSD to MSD)
        int dip_idx = findDipIndex(arr);
        if (dip_idx == -1)
            return -1;

        // step 2 ----> find ceil index just greater with dip index element and swap
        // both of them
        int ceil_idx = findCeilIndex(arr, dip_idx);
        swap(arr, dip_idx, ceil_idx);

        // step 3 ----> reverse from dip_index + 1 to end
        reverse(arr, dip_idx + 1, size - 1);

        // converting array to integer
        long ans = 0;
        for (int i = 0; i < arr.length; i++)
            ans = ans * 10 + arr[i];

        if (ans <= Integer.MAX_VALUE)
            return (int) ans;

        else
            return -1;
    }

    // 905. Sort Array By Parity_==============================================

    // private void swap(int[] arr, int si, int ei) {
    // int temp = arr[si];
    // arr[si] = arr[ei];
    // arr[ei] = temp;
    // }

    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        while (i < n) {
            if (nums[i] % 2 == 0) {
                swap(nums, i, j);
                i++;
                j++;
            } else {
                i++;
            }
        }

        return nums;
    }

    // 628. Maximum Product of Three Numbers_==============================
    public int maximumProduct(int[] nums) {
        int max1 = -(int) 1e9, max2 = -(int) 1e9, max3 = -(int) 1e9;
        int min1 = (int) 1e9, min2 = (int) 1e9;

        for (int val : nums) {
            if (val > max1) {
                max3 = max2;
                max2 = max1;
                max1 = val;
            } else if (val > max2) {
                max3 = max2;
                max2 = val;
            } else if (val > max3) {
                max3 = val;
            }

            if (val < min1) {
                min2 = min1;
                min1 = val;
            } else if (val < min2) {
                min2 = val;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }

    // 769. Max Chunks To Make Sorted_======================
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;

        int chunk = 0, max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);

            if (max == i)
                chunk++;
        }

        return chunk;
    }

    // 768. Max Chunks To Make Sorted II_=============================

    // using two array
    public int maxChunksToSorted_01(int[] arr) {
        int n = arr.length;
        int[] left_max = new int[n];
        int[] right_min = new int[n];

        left_max[0] = arr[0];
        right_min[n - 1] = arr[n - 1];

        for (int i = 1; i < left_max.length; i++)
            left_max[i] = Math.max(arr[i], left_max[i - 1]);
        for (int i = right_min.length - 2; i >= 0; i--)
            right_min[i] = Math.min(arr[i], right_min[i + 1]);

        int chunk_count = 1;
        for (int i = 0; i < n - 1; i++) {
            if (left_max[i] <= right_min[i + 1])
                chunk_count++;
        }

        return chunk_count;
    }

    // using one array
    public int maxChunksToSorted_02(int[] arr) {
        int n = arr.length;
        // prepare right_min and maage left_max while running with loop
        int[] right_min = new int[n];
        right_min[n - 1] = arr[n - 1];

        for (int i = right_min.length - 2; i >= 0; i--)
            right_min[i] = Math.min(arr[i], right_min[i + 1]);

        // count chunk
        int chunk_count = 1;
        int left_max = -(int) 1e9;
        for (int i = 0; i < n - 1; i++) {
            left_max = Math.max(left_max, arr[i]);
            if (left_max <= right_min[i + 1])
                chunk_count++;
        }

        return chunk_count;
    }

    // 747. Largest Number At Least Twice of Others
    public int dominantIndex(int[] nums) {
        int n = nums.length;
        int max1 = -(int) 1e9, max2 = -(int) 1e9, idx = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
                idx = i;
            } else if (nums[i] > max2) {
                max2 = nums[i];
            }
        }

        return max1 >= 2 * max2 ? idx : -1;
    }

    // 345. Reverse Vowels of a String
    private boolean isVowel(char ch) {
        String s = "aeiou";
        return s.contains(ch + "");
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public String reverseVowels(String s) {
        int n = s.length();
        char[] arr = new char[n];
        for (int i = 0; i < n; i++)
            arr[i] = s.charAt(i);

        int left = 0;
        int right = n - 1;

        while (left < right) {
            while (left < right && !isVowel(arr[left]))
                left++;

            while (left < right && !isVowel(arr[right]))
                right--;

            swap(arr, left, right);
            left++;
            right--;
        }

        return new String(arr);
    }

    // 795. Number of Subarrays with Bounded Maximum
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n = nums.length;
        int prevCount = 0, count = 0;

        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                prevCount = i - j + 1;
                count += (i - j + 1);
            } else if (nums[i] < left) {
                count += prevCount;
            } else if (nums[i] > right) {
                j = i + 1;
                prevCount = 0;
            }
        }

        return count;
    }

    // Wiggle Sort 1
    // https://practice.geeksforgeeks.org/problems/wave-array-1587115621/1
    private static void swap_(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void convertToWave(int arr[], int n) {
        for (int i = 0; i < n - 1; i++) {
            if (i % 2 == 0) {
                // Even
                if (arr[i] < arr[i + 1])
                    swap_(arr, i, i + 1);

            } else {
                // odd
                if (arr[i] > arr[i + 1])
                    swap_(arr, i, i + 1);
            }
        }

    }

    // 324. Wiggle Sort II
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] dup = new int[n];
        for (int i = 0; i < n; i++)
            dup[i] = nums[i];

        Arrays.sort(dup);

        int i = 1, j = dup.length - 1;
        while (i < n) {
            nums[i] = dup[j];
            i += 2;
            j--;
        }

        i = 0;
        while (i < n) {
            nums[i] = dup[j];
            i += 2;
            j--;
        }
    }

    // https://www.lintcode.com/problem/range-addition/description
    public int[] getModifiedArray(int n, int[][] query) {
        // Write your code here
        int[] arr = new int[n];

        for (int i = 0; i < query.length; i++) {
            int si = query[i][0];
            int ei = query[i][1];
            int val = query[i][2];

            arr[si] += val;
            if (ei != n - 1)
                arr[ei + 1] -= val;
        }

        // make prefix sum araay
        for (int i = 1; i < n; i++) {
            arr[i] += arr[i - 1];
        }

        return arr;
    }

    // 238. Product of Array Except Self
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] right_prod = new int[n + 1];
        int[] res = new int[n];

        // prepare right product array
        right_prod[n] = 1;
        for (int i = n - 1; i >= 0; i--)
            right_prod[i] = nums[i] * right_prod[i + 1];

        // prepare left product with ruuning loop and solve
        int left_prod = 1;
        for (int i = 0; i < n; i++) {
            res[i] = left_prod * right_prod[i + 1];
            left_prod *= nums[i];
        }

        return res;
    }

    // 118. Pascal's Triangle
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int n = 0; n < numRows; n++) {
            ArrayList<Integer> small_res = new ArrayList<>();
            int val = 1;
            for (int r = 0; r <= n; r++) {
                small_res.add(val);

                val = ((n - r) * val) / (r + 1);
            }
            res.add(small_res);
        }

        return res;
    }

    // ==============================================================
    // 849. Maximize Distance to Closest Person
    // space-->O(n)
    public int maxDistToClosest_01(int[] seats) {
        int n = seats.length;

        // step 1 : make right person distance array
        int[] right_person_distance = new int[n];
        int idx = (int) 1e9;
        for (int i = n - 1; i >= 0; i--) {
            if (seats[i] == 1) {
                right_person_distance[i] = 0;
                idx = i;
            } else {
                right_person_distance[i] = Math.abs(idx - i);

            }
        }

        /*
         * step 2 : make left person distacne arr and find closest distance along with
         * find max dist. b/w closest person & Alex
         */

        int[] left_person_distance = new int[n];
        idx = (int) 1e9;
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                left_person_distance[i] = 0;
                idx = i;
            } else {
                left_person_distance[i] = Math.abs(idx - i);
                int closest_dist = Math.min(left_person_distance[i], right_person_distance[i]);
                max = Math.max(max, closest_dist);
            }
        }

        return max;
    }

    // space = O(1)
    public int maxDistToClosest(int[] seats) {
        // step 1 : iterate on array and make left person distance on given array except
        // for occupied seat
        int idx = (int) 1e9, n = seats.length;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                seats[i] = -1;
                idx = i;
            } else {
                seats[i] = Math.abs(idx - i);
            }
        }

        // step 2 : iterate from right to left and manage closest distance as well as
        // max dist b/w closest ans alex
        int max = 0;
        idx = (int) 1e9;
        for (int i = n - 1; i >= 0; i--) {
            if (seats[i] == -1) {
                idx = i;
            } else {
                seats[i] = Math.min(seats[i], Math.abs(idx - i));
                max = Math.max(max, seats[i]);
            }
        }

        return max;
    }

    // 41. First Missing Positive
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1 : travel and mark number which are out of range as 1 and also check
        // presence of one
        boolean isOnePresent = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1)
                isOnePresent = true;

            if (nums[i] <= 0 || n < nums[i])
                nums[i] = 1;
        }

        if (!isOnePresent)
            return 1;

        // step 2. travel and mark present index in array
        for (int i = 0; i < n; i++) {
            int idx = Math.abs(nums[i]) - 1;
            nums[idx] = -Math.abs(nums[idx]);
        }

        // step 3. travel and check unmarked index, if found return indx + 1, otherwise
        // n + 1
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0)
                return i + 1;
        }
        return n + 1;
    }

    // https://www.lintcode.com/problem/912/
    public int minTotalDistance(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        // step 1 : find x coordinate in a sorted order and travel row wise
        ArrayList<Integer> x_cord = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1)
                    x_cord.add(r);
            }
        }

        // step 2 : find y-coordinate in a sorted order and travel column wise
        ArrayList<Integer> y_cord = new ArrayList<>();
        for (int c = 0; c < m; c++) {
            for (int r = 0; r < n; r++) {
                if (grid[r][c] == 1)
                    y_cord.add(c);
            }
        }

        // step 3 : find median
        int x = x_cord.get(x_cord.size() / 2);
        int y = y_cord.get(y_cord.size() / 2);

        // step 4 : find distacne
        int dist = 0;
        for (int i = 0; i < x_cord.size(); i++) {
            // distance between x coordinate
            dist += Math.abs(x_cord.get(i) - x);

            // distance between y coordinate
            dist += Math.abs(y_cord.get(i) - y);

        }

        return dist;
    }

    // 670. Maximum Swap
    // private void swap(int[] arr, int i , int j) {
    // int temp = arr[i];
    // arr[i] = arr[j];
    // arr[j] = temp;
    // }

    // space -> O(n) where n is length
    public int maximumSwap_01(int num) {
        // convert number to string and create integer array
        String str = "" + num;
        int n = str.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = str.charAt(i) - '0';

        // step 1 : prepare right max index array
        int[] right_max_idx = new int[n];
        right_max_idx[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            int digit = arr[i];
            int last_idx = right_max_idx[i + 1];

            if (digit > arr[last_idx])
                right_max_idx[i] = i;
            else
                right_max_idx[i] = last_idx;
        }

        // step 2 : travel and figure out position for swapping
        for (int i = 0; i < n; i++) {
            int digit = arr[i];
            int max_idx = right_max_idx[i];

            if (digit < arr[max_idx]) {
                // swap
                swap(arr, i, max_idx);
                break;
            }
        }

        // step 3 : make final result
        int fact = 1, ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            ans = ans + arr[i] * fact;
            fact *= 10;
        }

        return ans;
    }

    // space -> O(10)
    public int maximumSwap_02(int num) {
        String str = "" + num;
        char[] chArr = str.toCharArray(); // convert string to char array

        // step 1 : prepare last index of digit
        int[] last_idx = new int[10];
        for (int i = 0; i < chArr.length; i++) {
            int digit = chArr[i] - '0';
            last_idx[digit] = i;
        }

        // step 2 : figure out position of swapping
        boolean flag = false;
        for (int i = 0; i < chArr.length; i++) {
            int digit = chArr[i] - '0';
            for (int j = 9; j > digit; j--) {
                if (i < last_idx[j]) {
                    // swap
                    swap(chArr, i, last_idx[j]);
                    flag = true;
                    break;
                }
            }

            if (flag)
                break;
        }

        // step 3 : prepare number for ans
        int ans = 0, fact = 1, n = chArr.length;
        for (int i = n - 1; i >= 0; i--) {
            int digit = chArr[i] - '0';
            ans = ans + fact * digit;
            fact *= 10;
        }

        return ans;

    }

    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/arrays-&-strings-l2/2-sum-target-sum-unique-pairs/ojquestion
    public static List<List<Integer>> twoSum(int[] arr, int target) {
        // write your code here
        Arrays.sort(arr);
        int n = arr.length;
        int left = 0, right = arr.length - 1;
        List<List<Integer>> ans = new ArrayList<>();

        while (left < right) {
            if (left != 0 && arr[left] == arr[left - 1]) {
                left++;
                continue;
            }

            int sum = arr[left] + arr[right];
            if (sum == target) {
                List<Integer> small_ans = new ArrayList<>();
                small_ans.add(arr[left]);
                small_ans.add(arr[right]);
                ans.add(small_ans);

                left++;
                right--;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        return ans;
    }

    // 15. 3Sum
    private List<List<Integer>> two_sum(int[] arr, int target, int sp) {
        int n = arr.length;
        int left = sp, right = arr.length - 1;
        List<List<Integer>> ans = new ArrayList<>();

        while (left < right) {
            if (left != sp && arr[left] == arr[left - 1]) {
                left++;
                continue;
            }

            int sum = arr[left] + arr[right];
            if (sum == target) {
                List<Integer> small_ans = new ArrayList<>();
                small_ans.add(arr[left]);
                small_ans.add(arr[right]);
                ans.add(small_ans);

                left++;
                right--;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        return ans;
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i <= n - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1])
                continue;

            int val1 = nums[i];
            List<List<Integer>> twoSumPair = two_sum(nums, target - val1, i + 1);
            for (List<Integer> list : twoSumPair) {
                list.add(val1);
                ans.add(list);
            }
        }

        return ans;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums, 0);
    }

    // 18. 4Sum
    public List<List<Integer>> three_sum(int[] nums, int target, int sp) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = sp; i <= n - 3; i++) {
            if (i != sp && nums[i] == nums[i - 1])
                continue;

            int val1 = nums[i];
            List<List<Integer>> twoSumPair = two_sum(nums, target - val1, i + 1);
            for (List<Integer> list : twoSumPair) {
                list.add(val1);
                ans.add(list);
            }
        }

        return ans;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i <= n - 4; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int val1 = nums[i];
            List<List<Integer>> three_sum_triplet = three_sum(nums, target - val1, i + 1);
            for (List<Integer> list : three_sum_triplet) {
                list.add(val1);
                ans.add(list);
            }
        }

        return ans;
    }

    // K Sum - Target Sum Unique Set
    public List<List<Integer>> kSum(int[] arr, int target, int k, int sp) {
        // write your code here
        if (k == 2) {
            List<List<Integer>> base = two_sum(arr, target, sp);
            return base;
        }
        int n = arr.length;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = sp; i <= n - k; i++) {
            if (i != sp && arr[i] == arr[i - 1])
                continue;

            int val1 = arr[i];
            List<List<Integer>> small_ans = kSum(arr, target - val1, k - 1, i + 1);
            for (List<Integer> list : small_ans) {
                list.add(val1);
                ans.add(list);
            }
        }

        return ans;
    }

    public List<List<Integer>> kSum(int[] arr, int target, int k) {
        // write your code here
        Arrays.sort(arr);
        return kSum(arr, target, k, 0);
    }

    // 537. Complex Number Multiplication
    public String complexNumberMultiply(String num1, String num2) {
        int a1 = Integer.parseInt(num1.substring(0, num1.indexOf("+")));
        int b1 = Integer.parseInt(num1.substring(num1.indexOf("+") + 1, num1.length() - 1));

        int a2 = Integer.parseInt(num2.substring(0, num2.indexOf("+")));
        int b2 = Integer.parseInt(num2.substring(num2.indexOf("+") + 1, num2.length() - 1));

        int real = a1 * a2 - b1 * b2;
        int imaginary = a1 * b2 + a2 * b1;

        String result = "" + real + "+" + imaginary + "i";
        return result;
    }

    // https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
    public static int findPlatform(int arr[], int dep[], int n) {
        // add your code here
        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 0, j = 0;
        int platform = 0, over_max = 0;

        while (i < n) {
            if (arr[i] <= dep[j]) {
                platform++;
                i++;
            } else {
                platform--;
                j++;
            }

            over_max = Math.max(over_max, platform);
        }

        return over_max;
    }

    // Sieve Of Eratosthenes
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/arrays-&-strings-l2/sieve-of-eratosthenes/ojquestion
    public static void printPrimeUsingSieve(int n) {
        // pre calculation
        boolean[] isPrime = new boolean[n + 1];

        // fill all as prime , i.e. marked true => this is optional
        Arrays.fill(isPrime, true);

        // begins from 2 to root(n)
        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i])
                continue; // because if it is marked as not primes, then its multiple are also marked

            for (int j = i + i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }

        // run a loop and print if it is a prime
        for (int i = 2; i <= n; i++) {
            if (isPrime[i])
                System.out.print(i + " ");
        }

    }

    // 204. Count Primes
    public int countPrimes(int n) {
        // pre calculation
        boolean[] isPrime = new boolean[n + 1];

        // fill all as prime , i.e. marked true => this is optional
        Arrays.fill(isPrime, true);

        // begins from 2 to root(n)
        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i])
                continue; // because if it is marked as not primes, then its multiple are also marked

            for (int j = i + i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }

        // run a loop and increament count if it is a prime
        int prime_count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i])
                prime_count++;
        }

        return prime_count;
    }

    // segmented Sieve Algo
    private static ArrayList<Integer> seive(int n) {
        // pre calculation
        boolean[] isPrime = new boolean[n + 1];

        // begins from 2 to root(n)
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i])
                continue; // because if it is marked as not primes, then its multiple are also marked

            for (int j = i + i; j <= n; j += i) {
                isPrime[j] = true;
            }
        }

        // run a loop and store if it is a prime
        ArrayList<Integer> prime = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i])
                prime.add(i);
        }

        return prime;
    }

    public static void segmentedSieveAlgo(int a, int b) {
        // write your code here
        int root_b = (int) Math.sqrt(b);
        ArrayList<Integer> primes = seive(root_b);

        int n = b - a;
        boolean[] is_prime = new boolean[n + 1];
        // is_prime[i] = true -> value associated with index is not prime
        // is_prime[i] = false -> value associated with index is prime

        for (int prime : primes) {
            int multiple = (int) Math.ceil(a * 1.0 / prime);

            if (multiple == 1)
                multiple++;

            int si = multiple * prime - a; // starting index
            for (int i = si; i < is_prime.length; i += prime) {
                is_prime[i] = true; // mark it as not prime
            }
        }

        // travel and print prime
        for (int i = 0; i < is_prime.length; i++) {
            if (is_prime[i] == false && i + a != 1) {
                System.out.println(i + a);
            }
        }
    }

    // Find Pair Given Difference
    // https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1#
    public boolean findPair(int arr[], int size, int target) {
        // code here.
        Arrays.sort(arr);
        int left = 0, right = 1;

        while (right < arr.length) {
            int diff = arr[right] - arr[left];

            if (diff == target) {
                return true;
            } else if (diff > target) {
                left++;
            } else {
                right++;
            }
        }

        return false;
    }

    // 881. Boats to Save People
    public int numRescueBoats(int[] weights, int capacity) {
        Arrays.sort(weights);
        int n = weights.length;
        int left = 0, right = n - 1;
        int boats = 0;

        while (left <= right) {
            int sum = weights[left] + weights[right];
            if (sum > capacity) {
                right--;
            } else {
                // sum <= capacity
                left++;
                right--;
            }

            boats++;
        }

        return boats;
    }

    // 763. Partition Labels
    public List<Integer> partitionLabels(String s) {
        int n = s.length();

        // 1. make a HashMap and maintain index of last occurance of character
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            map.put(ch, i);
        }

        // 2. making of result
        List<Integer> res = new ArrayList<>();

        int prev = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            max = Math.max(max, map.get(ch));

            if (max == i) {
                int length = max - prev + 1;
                res.add(length);

                prev = max + 1;
            }
        }

        return res;
    }

    // 754. Reach a Number
    public int reachNumber(int target) {
        // if target is negative
        target = Math.abs(target);

        int jump = 0;
        int sum = 0;
        while (sum < target) {
            jump++;
            sum += jump;
        }

        while ((sum - target) % 2 != 0) {
            jump++;
            sum += jump;
        }

        return jump;
    }

    // 867. Transpose Matrix
    public int[][] transpose_NxM(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int[][] transpose = new int[col][row];

        for (int r = 0; r < col; r++) {
            for (int c = 0; c < row; c++) {
                transpose[r][c] = matrix[c][r];
            }
        }

        return transpose;
    }

    // https://practice.geeksforgeeks.org/problems/transpose-of-matrix-1587115621/1
    // Function to find transpose of a matrix.
    private static void swap(int[][] arr, int i, int j) {
        int temp = arr[i][j];
        arr[i][j] = arr[j][i];
        arr[j][i] = temp;
    }

    public static void transpose_NxN(int matrix[][], int n) {
        // code here
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j);
            }
        }
    }

    // 48. Rotate Image
    private void transpose(int matrix[][]) {
        // code here
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j);
            }
        }
    }

    public void rotate(int[][] matrix) {
        // 1. take transpose
        transpose(matrix);

        // 2. reverse column
        int n = matrix.length;
        for (int r = 0; r < n; r++) {
            int left = 0, right = n - 1;

            while (left < right) {
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][right];
                matrix[r][right] = temp;

                left++;
                right--;
            }

        }
    }

    // 838. Push Dominoes
    private void solveConf(char[] arr, int i, int j) {
        if (arr[i] == 'L' && arr[j] == 'L') {
            for (int k = i + 1; k < j; k++) {
                arr[k] = 'L';
            }
        } else if (arr[i] == 'R' && arr[j] == 'R') {
            for (int k = i + 1; k < j; k++) {
                arr[k] = 'R';
            }
        } else if (arr[i] == 'L' && arr[j] == 'R') {
            // Nothing to do
        } else {
            int left = i + 1, right = j - 1;
            while (left < right) {
                arr[left] = 'R';
                arr[right] = 'L';

                left++;
                right--;
            }
        }
    }

    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] arr = new char[n + 2];
        arr[0] = 'L';
        arr[arr.length - 1] = 'R';

        for (int i = 1; i < arr.length - 1; i++)
            arr[i] = dominoes.charAt(i - 1);

        int i = 0, j = 1;

        while (j < arr.length) {
            while (arr[j] == '.')
                j++;
            // case management
            if (j - i > 1)
                solveConf(arr, i, j);
            i = j;
            j++;
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 1; k < arr.length - 1; k++)
            sb.append(arr[k]);

        return sb.toString();
    }

    // 829. Consecutive Numbers Sum
    public int consecutiveNumbersSum(int n) {
        int count = 0;
        for (int k = 1; k * (k - 1) < 2 * n; k++) {
            // x = (2N - K(K - 1)) / 2K
            int num = 2 * n - k * (k - 1); // numerator
            int den = 2 * k; // denomanator

            if (num % den == 0) {
                // x is an integer
                count++;
            }
        }

        return count;
    }

    // 415. Add Strings
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        int sum = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int ival = (i >= 0) ? num1.charAt(i--) - '0' : 0;
            int jval = (j >= 0) ? num2.charAt(j--) - '0' : 0;

            sum = carry + ival + jval;

            sb.append(sum >= 10 ? sum % 10 : sum);
            carry = sum / 10;
        }

        return sb.reverse().toString();
    }

    // 43. Multiply Strings
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        int l1 = num1.length(), l2 = num2.length();
        int[] res = new int[l1 + l2];
        int i = l2 - 1;
        int pf = 0; // power factor

        while (i >= 0) {
            int ival = num2.charAt(i--) - '0';
            int carry = 0, j = l1 - 1, k = res.length - 1 - pf;

            while (j >= 0 || carry != 0) {
                int jval = (j >= 0) ? num1.charAt(j--) - '0' : 0;
                int prod = ival * jval + carry + res[k];
                res[k--] = prod % 10;
                carry = prod / 10;
            }
            pf++;
        }

        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (int val : res) {
            if (val == 0 && flag == false) {
                // leading zero
                continue;
            } else {
                // work
                flag = true;
                sb.append(val);
            }
        }
        return sb.toString();
    }

    // 239. Sliding Window Maximum
    private int[] ngri(int[] arr) {
        // ngri -> next greater on right index
        int n = arr.length;
        int[] ngr = new int[n];
        Stack<Integer> st = new Stack<>(); // store index
        st.push(0);

        for (int i = 1; i < n; i++) {
            while (st.size() > 0 && arr[i] > arr[st.peek()]) {
                ngr[st.pop()] = i;
            }
            st.push(i);
        }

        while (st.size() > 0) {
            ngr[st.pop()] = n;
        }

        return ngr;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ngr = ngri(nums);

        int[] res = new int[n - k + 1];
        int j = 0;
        for (int i = 0; i < res.length; i++) {
            if (j < i)
                j = i;

            while (i + k > ngr[j]) {
                j = ngr[j];
            }
            res[i] = nums[j];
        }

        return res;
    }

    // 42. Trapping Rain Water
    // Time -> O(n) , space -> O(n)
    public int trap_01(int[] height) {
        int n = height.length;
        int[] left_max = new int[n];
        int[] right_max = new int[n];

        // prepare right max
        right_max[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right_max[i] = Math.max(right_max[i + 1], height[i]);
        }

        // prepare left max and along with find water
        int water = 0;
        left_max[0] = height[0];
        water += Math.min(left_max[0], right_max[0]) - height[0];

        for (int i = 1; i < n; i++) {
            left_max[i] = Math.max(left_max[i - 1], height[i]);
            water += Math.min(left_max[i], right_max[i]) - height[i];
        }

        return water;
    }

    // Space optimization
    // Time -> O(n) , space -> O(1)
    public int trap_02(int[] height) {
        int n = height.length;

        int water = 0, flow = 0, max = height[0], max_idx = 0;

        for (int i = 1; i < n; i++) {
            int ht = height[i];

            if (max <= ht) {
                water += flow;
                flow = 0;
                max = ht;
                max_idx = i;
            } else {
                flow += (max - ht);
            }
        }

        // solve overflow of flow value
        flow = 0;
        max = height[n - 1];
        for (int i = n - 2; i >= max_idx; i--) {
            int ht = height[i];
            if (max <= ht) {
                water += flow;
                flow = 0;
                max = ht;
            } else {
                flow += (max - ht);
            }
        }

        return water;
    }

    // meeting rooms 1
    // https://www.lintcode.com/problem/920/
    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public boolean canAttendMeetings(List<Interval> intervals) {
        // Write your code here
        int n = intervals.size();
        if (n == 0)
            return true;
        Collections.sort(intervals, (a, b) -> {
            return a.start - b.start;
        });

        int end = intervals.get(0).end;
        for (int i = 1; i < n; i++) {
            if (end > intervals.get(i).start) {
                return false;
            } else {
                end = intervals.get(1).end;
            }
        }

        return true;
    }

    // meeting room2
    // https://www.interviewbit.com/problems/meeting-rooms/
    public int solve(ArrayList<ArrayList<Integer>> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals.get(i).get(0);
            end[i] = intervals.get(i).get(1);
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int i = 0, j = 0; // start ansd end point
        int req_rooms = 0, overall_max = 0;
        while (i < n) {
            if (start[i] < end[j]) {
                req_rooms++;
                i++;
            } else {
                req_rooms--;
                j++;
            }

            overall_max = Math.max(overall_max, req_rooms);
        }

        return overall_max;
    }

    // 56. Merge Intervals
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        ArrayList<int[]> res = new ArrayList<>();

        int lsp = intervals[0][0];
        int lep = intervals[0][1];
        for (int i = 1; i < n; i++) {
            int sp = intervals[i][0];
            int ep = intervals[i][1];

            if (sp > lep) {
                int[] arr = { lsp, lep };
                res.add(arr);

                lsp = sp;
                lep = ep;
            } else if (lep < ep) {
                // partial marging
                lep = ep;
            } else {
                // new interval is already covered in between lsp and lep;
            }
        }

        int[] arr = { lsp, lep };
        res.add(arr);

        // int[][] ans = new int[res.size()][2];
        // for(int i = 0; i < res.size(); i++) {
        // ans[i][0] = res.get(i)[0];
        // ans[i][1] = res.get(i)[1];
        // }

        // return ans;

        return res.toArray(new int[res.size()][]);
    }

    // 986. Interval List Intersections
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int l1 = firstList.length, l2 = secondList.length;
        if (l1 == 0 || l2 == 0)
            return new int[0][0];

        ArrayList<int[]> res = new ArrayList<>();
        int i = 0; // poniting to first interval of first list
        int j = 0; // poniting to first interval of second list

        while (i < l1 && j < l2) {
            int sp = Math.max(firstList[i][0], secondList[j][0]);
            int ep = Math.min(firstList[i][1], secondList[j][1]);

            if (sp <= ep) {
                int[] arr = { sp, ep };
                res.add(arr);
            }

            if (firstList[i][1] < secondList[j][1])
                i++;
            else
                j++;

        }

        return res.toArray(new int[res.size()][]);
    }

    // 853. Car Fleet
    private class fleetHelper implements Comparable<fleetHelper> {
        int pos;
        int speed;
        double time;

        fleetHelper(int pos, int speed, double time) {
            this.pos = pos;
            this.speed = speed;
            this.time = time;
        }

        public int compareTo(fleetHelper other) {
            return this.pos - other.pos;
        }
    }

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        fleetHelper[] data = new fleetHelper[n];
        for (int i = 0; i < n; i++) {
            data[i] = new fleetHelper(position[i], speed[i], (target - position[i]) * 1.0 / speed[i]);
        }
        Arrays.sort(data);

        double maxTime = data[n - 1].time;
        int fleet = 1;

        for (int i = n - 2; i >= 0; i--) {
            if (data[i].time > maxTime) {
                fleet++;
                maxTime = data[i].time;
            }
        }

        return fleet;
    }

    // Digit multiplier
    // https://practice.geeksforgeeks.org/problems/digit-multiplier3000/1#
    public static String getSmallest(Long N) {
        // code here
        if (N == 1)
            return "1";
        String res = "";
        for (int digit = 9; digit > 1; digit--) {
            while (N % digit == 0) {
                N = N / digit;
                res = digit + res;
            }
        }

        return (N == 1) ? res : "-1";
    }

    // First negative integer in every window of size k
    // https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
    public long[] printFirstNegativeInteger(long arr[], int n, int k) {
        long[] res = new long[n - k + 1];
        int last_neg = n;
        for (int i = n - 1; i >= n - k; i--) {
            if (arr[i] < 0)
                last_neg = i;
        }

        for (int i = n - k; i >= 0; i--) {
            if (arr[i] < 0)
                last_neg = i;

            if (i + k > last_neg)
                res[i] = arr[last_neg];
        }

        return res;
    }

    // 53. Maximum Subarray
    public int maxSubArray(int[] arr) {
        int n = arr.length;
        int curr_sum = 0;
        int over_sum = -(int) 1e9;
        for (int i = 0; i < n; i++) {
            if (curr_sum < 0) {
                curr_sum = arr[i];
            } else {
                curr_sum += arr[i];
            }

            over_sum = Math.max(over_sum, curr_sum);
        }
        return over_sum;
    }

    // 1191. K-Concatenation Maximum Sum
    public long kadans1(int[] arr) {
        int n = arr.length;
        long curr_sum = 0;
        long over_sum = -(int) 1e9;
        for (int i = 0; i < n; i++) {
            if (curr_sum < 0) {
                curr_sum = arr[i];
            } else {
                curr_sum += arr[i];
            }

            over_sum = Math.max(over_sum, curr_sum);
        }
        return Math.max(over_sum, 0);
    }

    private long kadans12(int[] arr) {
        int n = arr.length;
        int[] n_arr = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            n_arr[i] = arr[i % n];
        }

        return kadans1(n_arr);

    }

    long mod = (int) 1e9 + 7;

    public int kConcatenationMaxSum(int[] arr, int k) {
        if (k == 1) {
            return (int) (kadans1(arr) % mod);
        }

        long t_sum = 0;
        for (int val : arr)
            t_sum += val;

        if (t_sum >= 0) {
            return (int) ((kadans12(arr) + (t_sum * (k - 2))) % mod);
        } else {
            return (int) (kadans12(arr) % mod);
        }
    }

    // 152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int curr_prod = 1;
        int max_prod = -(int) 1e9;

        // left product with maximization of result
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                curr_prod *= nums[i];
                max_prod = Math.max(max_prod, curr_prod);
            } else {
                curr_prod = 1;
                max_prod = Math.max(0, max_prod);
            }
        }

        // right product with maximization of result
        curr_prod = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] != 0) {
                curr_prod *= nums[i];
                max_prod = Math.max(max_prod, curr_prod);
            } else {
                curr_prod = 1;
                max_prod = Math.max(0, max_prod);
            }
        }

        return max_prod;
    }

    // Max sum in sub-arrays
    // https://practice.geeksforgeeks.org/problems/max-sum-in-sub-arrays0824/1
    public static long pairWithMaxSum(long arr[], long n) {
        // Your code goes here
        long max_sum = -(int) 1e9;
        for (int i = 0; i < n - 1; i++)
            max_sum = Math.max(max_sum, arr[i] + arr[i + 1]);

        return max_sum;
    }

    // 134. Gas Station
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int gas_sum = 0, cost_sum = 0;
        for (int i = 0; i < n; i++) {
            gas_sum += gas[i];
            cost_sum += cost[i];
        }

        if (gas_sum - cost_sum < 0)
            return -1;

        int idx = 0, prefix_sum = 0, min = (int) 1e9;

        for (int i = 0; i < n; i++) {
            prefix_sum += gas[i] - cost[i];

            if (prefix_sum <= min) {
                idx = i;
                min = prefix_sum;
            }
        }

        return (idx + 1) % n;
    }

    // 1007. Minimum Domino Rotations For Equal Row
    private int find_minimum(int... arr) {
        int min = arr[0];
        for (int val : arr)
            min = Math.min(min, val);

        return min;
    }

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int n = tops.length, m = bottoms.length;

        int num1 = tops[0], num2 = bottoms[0];

        int count1 = 0; // No of rotation required to make tops array as num1
        int count2 = 0; // No of rotation required to make tops array as num2
        int count3 = 0; // No of rotation required to make bottoms array as num1
        int count4 = 0; // No of rotation required to make bottoms array as num2

        for (int i = 0; i < n; i++) {
            if (count1 != (int) 1e9) {
                if (tops[i] == num1) {
                    // nothing to do
                } else if (bottoms[i] == num1) {
                    count1++;
                } else {
                    count1 = (int) 1e9;
                }
            }

            if (count2 != (int) 1e9) {
                if (tops[i] == num2) {
                    // nothing to do
                } else if (bottoms[i] == num2) {
                    count2++;
                } else {
                    count2 = (int) 1e9;
                }
            }

            if (count3 != (int) 1e9) {
                if (bottoms[i] == num1) {
                    // nothing to do
                } else if (tops[i] == num1) {
                    count3++;
                } else {
                    count3 = (int) 1e9;
                }
            }

            if (count4 != (int) 1e9) {
                if (bottoms[i] == num2) {
                    // nothing to do
                } else if (tops[i] == num2) {
                    count4++;
                } else {
                    count4 = (int) 1e9;
                }
            }
        }

        int res = find_minimum(count1, count2, count3, count4);
        return res != (int) 1e9 ? res : -1;
    }

    // 632. Smallest Range Covering Elements from K Lists
    private class range_helper implements Comparable<range_helper> {
        int val;
        int r;
        int c;

        range_helper(int val, int r, int c) {
            this.val = val;
            this.r = r;
            this.c = c;
        }

        public int compareTo(range_helper other) {
            return this.val - other.val;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<range_helper> pq = new PriorityQueue<>();
        int n = nums.size();

        int max = -(int) 1e9;
        int sp = 0, ep = 0, length = (int) 1e9;

        for (int r = 0; r < n; r++) {
            int val = nums.get(r).get(0);
            pq.add(new range_helper(val, r, 0));
            max = Math.max(max, val);
        }

        int cmin = 0, cmax = 0;
        while (true) {
            range_helper rem = pq.remove();

            cmin = rem.val;
            cmax = max;

            if (cmax - cmin < length) {
                length = cmax - cmin;
                sp = cmin;
                ep = cmax;
            }

            if (rem.c + 1 < nums.get(rem.r).size()) {
                int val = nums.get(rem.r).get(rem.c + 1);
                pq.add(new range_helper(val, rem.r, rem.c + 1));
                max = Math.max(max, val);
            } else {
                break;
            }
        }

        int[] res = { sp, ep };
        return res;
    }

    // 209. Minimum Size Subarray Sum
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int sum = 0;
        int left_ptr = 0;
        int min_len = (int)1e9;
        
        for(int i = 0; i < n; i++) {
            if(nums[i] >= target)
                return 1;
            
            sum += nums[i];
            
            while(sum >= target) {
                min_len = Math.min(min_len, i - left_ptr + 1);
                sum -= nums[left_ptr];
                left_ptr++;
            }
        }
        
        return min_len != (int)1e9 ? min_len : 0;
    }

    // 643. Maximum Average Subarray I
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for(int i = 0; i < k - 1; i++) {
            sum += nums[i];
        }
        
        int n = nums.length;
        double max_avg = -(int)1e9 * 1.0;
        for(int i = k - 1; i < n; i++) {
            sum += nums[i];
            double avg = sum * 1.0 / k;
            max_avg = Math.max(max_avg, avg);
            sum -= nums[i - k + 1];
        }
        
        return max_avg;
    }

    // 442. Find All Duplicates in an Array
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            int idx = Math.abs(nums[i]) - 1;
            int val = nums[idx];
            
            if(val < 0) {
                // duplicacy encounter
                res.add(idx + 1);
            } else {
                // mark it
                nums[idx] *= -1;
            }
        }
        
        return res;
    }
}
