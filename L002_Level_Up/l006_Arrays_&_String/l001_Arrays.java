import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Arrays;

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
    //     int temp = arr[i];
    //     arr[i] = arr[j];
    //     arr[j] = temp;
    // }

    // space -> O(n) where n is length
    public int maximumSwap_01(int num) {
        // convert number to string and create integer array         
        String str = "" + num;
        int n = str.length();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = str.charAt(i) - '0';
        
        // step 1 : prepare right max index array
        int[] right_max_idx = new int[n];
        right_max_idx[n - 1] = n - 1;
        for(int i = n - 2; i >= 0; i--) {
            int digit = arr[i];
            int last_idx = right_max_idx[i + 1];
           
            if(digit > arr[last_idx])
                right_max_idx[i] = i;
            else 
                right_max_idx[i] = last_idx;
        }
        
        // step 2 : travel and figure out position for swapping
        for(int i = 0; i < n; i++) {
            int digit = arr[i];
            int max_idx = right_max_idx[i];
            
            if(digit < arr[max_idx]) {
                // swap                 
                swap(arr, i, max_idx);
                break;
            }
        }
        
        // step 3 : make final result
        int fact = 1, ans = 0;
        for(int i = n - 1; i >= 0; i--) {
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
        for(int i = 0; i < chArr.length; i++) {
            int digit = chArr[i] - '0';
            last_idx[digit] = i;
        }
        
        // step 2 : figure out position of swapping
        boolean flag = false;
        for(int i = 0; i < chArr.length; i++) {
            int digit = chArr[i] - '0';
            for(int j = 9 ; j > digit; j--) {
                if(i < last_idx[j]) {
                    // swap
                    swap(chArr, i, last_idx[j]);
                    flag = true;
                    break;
                }
            }
            
            if(flag)
                break;
        }
        
        // step 3 : prepare number for ans
        int ans = 0, fact = 1, n = chArr.length;
        for(int i = n - 1; i >= 0 ; i--) {
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

        while(left < right) {
            if(left != 0 && arr[left] == arr[left - 1]) {
                left++;
                continue;
            }

            int sum = arr[left] + arr[right];
            if(sum == target) {
                List<Integer> small_ans = new ArrayList<>();
                small_ans.add(arr[left]);
                small_ans.add(arr[right]);
                ans.add(small_ans);

                left++;
                right--;
            } else if(sum > target) {
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

        while(left < right) {
            if(left != sp && arr[left] == arr[left - 1]) {
                left++;
                continue;
            }

            int sum = arr[left] + arr[right];
            if(sum == target) {
                List<Integer> small_ans = new ArrayList<>();
                small_ans.add(arr[left]);
                small_ans.add(arr[right]);
                ans.add(small_ans);

                left++;
                right--;
            } else if(sum > target) {
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

        for(int i = 0; i <= n - 3; i++) {
            if(i != 0 && nums[i] == nums[i - 1])
                continue;

            int val1 = nums[i];
            List<List<Integer>> twoSumPair = two_sum(nums, target - val1, i + 1);
            for(List<Integer> list : twoSumPair) {
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

        for(int i = sp; i <= n - 3; i++) {
            if(i != sp && nums[i] == nums[i - 1])
                continue;

            int val1 = nums[i];
            List<List<Integer>> twoSumPair = two_sum(nums, target - val1, i + 1);
            for(List<Integer> list : twoSumPair) {
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
        
        for(int i = 0; i <= n - 4; i++) {
            if(i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int val1 = nums[i];
            List<List<Integer>> three_sum_triplet = three_sum(nums, target - val1, i + 1);
            for(List<Integer> list : three_sum_triplet) {
                list.add(val1);
                ans.add(list);
            }
        }
        
        return ans;
    }
    
}
