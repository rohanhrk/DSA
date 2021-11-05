import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class l001_Arrays {
    // 925. Long Pressed Name
    public boolean isLongPressedName(String name, String typed) {
        int n = name.length(), m = typed.length();
        if(n > m) return false;
        
        int i = 0; // pointer for name
        int j = 0; // pointer for typed
        
        while(i < n && j < m) {
            if(name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else {
                if(i > 0 && name.charAt(i - 1) == typed.charAt(j)) {
                    j++;
                } else {
                    return false;
                }
            }
        }
        
        while(j < m) {
            if(name.charAt(i - 1) != typed.charAt(j)) 
                return false;
            j++;
        }
        
        
        return i == n;
    }

    // 11. Container With Most Water
    public int maxArea(int[] height) {
        int n = height.length;
        int i = 0 , j = n - 1; // with i, j pair, forms a container
        
        int most_water = 0; // maximum volume
        while(i < j) {
            // l -> length, b -> breadth, h -> height             
            int l = (j - i), b = 1, h = Math.min(height[i], height[j]);
            int current_volume = l * b * h;
            most_water = Math.max(most_water, current_volume);
            
            if(height[i] < height[j]) 
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
        
        for(int k = n - 1; k >= 0; k--) {
            if(nums[i] * nums[i] >= nums[j] * nums[j] ) {
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
        
        for(int i = 1; i < n; i++) {
            if(nums[i] == val) {
                count++; 
            } else {
                if(count > 0) {
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
        for(int ele : arr) 
            if(ele == val)
                count++;
        
        return count > n / 3;
    }
    public List<Integer> majorityElement_02(int[] nums) {
        int n = nums.length;
        
        int val1 = nums[0];
        int count1 = 1;
        int val2 = nums[0]; // take any number
        int count2 = 0;
        
        for(int i = 1; i < n; i++) {
            if(nums[i] == val1) {
                count1++;
            } else if(nums[i] == val2) {
                count2++;
            } else {
                if(count1 == 0) {
                    val1 = nums[i];
                    count1 = 1;
                } else if(count2 == 0) {
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
        if(isMajority(nums, val1)) 
            res.add(val1);
        
        if(val2 != val1 && isMajority(nums, val2))
            res.add(val2);
        
        return res;
    }

    // Majority Element General (pepcoding)_=======================================
    public static ArrayList<Integer> majorityElement_03(int[] arr, int k) {
        // write yout code here
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        
        int n = arr.length;
        for(int key : arr) {
            if(!map.containsKey(key)) 
                map.put(key, 1);
                
            else {
                int count = map.get(key);
                map.put(key, count + 1);
            }
        }
        
        for(int key : map.keySet()) 
            if(map.get(key) > n / k)
                res.add(key);
                
        
        Collections.sort(res);
        return res;
    }

    // =================================_556. Next Greater Element III_============================================
    private int findDipIndex(int[] arr) {
        int idx = -1, n = arr.length;
        for(int i = n - 1; i > 0; i--) {
            if(arr[i - 1] < arr[i]) {
                idx = i - 1;
                break;
            }
        }
        
        return idx;
    }
    
    private int findCeilIndex(int[] arr, int dip_idx) {
        int idx = arr.length - 1;
        while(arr[idx] <= arr[dip_idx]) {
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
        while(si < ei) {
            swap(arr, si, ei);
            
            si++;
            ei--;
        }
    }
    
    public int nextGreaterElement(int n) {
        String str = Integer.toString(n); // convert interger to string
        int size = str.length();
        int[] arr = new int[size];
        for(int i = 0; i < size; i++)
            arr[i] = str.charAt(i) - '0';
        
        // step 1 ----> find dip index (traverse from LSD to MSD)
        int dip_idx = findDipIndex(arr);
        if(dip_idx == -1)
            return -1;
        
        // step 2 ----> find ceil index just greater with dip index element and swap both of them
        int ceil_idx = findCeilIndex(arr, dip_idx);
        swap(arr, dip_idx, ceil_idx);
        
        // step 3 ----> reverse from dip_index + 1 to end
        reverse(arr, dip_idx + 1, size - 1);
        
        // converting array to integer
        long ans = 0;
        for(int i = 0; i < arr.length; i++) 
            ans = ans * 10 + arr[i];
        
        if(ans <= Integer.MAX_VALUE)
            return (int)ans;
        
        else
            return -1;
    }

    // 905. Sort Array By Parity_==============================================
    
    // private void swap(int[] arr, int si, int ei) {
    //     int temp = arr[si];
    //     arr[si] = arr[ei];
    //     arr[ei] = temp;
    // }
    
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        while(i < n) {
            if(nums[i] % 2 == 0) {
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
        
        for(int val : nums) {
            if(val > max1) {
                max3 = max2;
                max2 = max1;
                max1 = val;
            } else if(val > max2) {
                max3 = max2;
                max2 = val;
            } else if(val > max3) {
                max3 = val;
            }
            
            if(val < min1) {
                min2 = min1;
                min1 = val;
            } else if(val < min2) {
                min2 = val;
            }
        }
        
        return Math.max(max1 * max2 * max3 , min1 * min2 * max1);
    }

    // 769. Max Chunks To Make Sorted_======================
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        
        int chunk = 0, max = 0; 
        for(int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            
            if(max == i) 
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
        
        
        for(int i = 1; i < left_max.length; i++) 
            left_max[i] = Math.max(arr[i], left_max[i - 1]);
        for(int i = right_min.length - 2; i >= 0; i--)
            right_min[i] = Math.min(arr[i], right_min[i + 1]);
        
        int chunk_count = 1;
        for(int i = 0; i < n - 1; i++) {
            if(left_max[i] <= right_min[i + 1])
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
        
        for(int i = right_min.length - 2; i >= 0; i--)
            right_min[i] = Math.min(arr[i], right_min[i + 1]);
        
        // count chunk         
        int chunk_count = 1;
        int left_max = -(int)1e9;
        for(int i = 0; i < n - 1; i++) {
            left_max = Math.max(left_max, arr[i]);
            if(left_max <= right_min[i + 1])
                chunk_count++;
        }
        
        return chunk_count;
    }

    // 747. Largest Number At Least Twice of Others
    public int dominantIndex(int[] nums) {
        int n = nums.length;
        int max1 = -(int)1e9 , max2 = -(int)1e9, idx = -1;
        
        for(int i = 0; i < n; i++) {
            if(nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
                idx = i;
            } else if(nums[i] > max2) {
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
        for(int i = 0; i < n; i++) 
            arr[i] = s.charAt(i);
        
        
        int left = 0;
        int right = n - 1;
        
        while(left < right) {
            while(left < right && !isVowel(arr[left]))
                left++;
            
            while(left < right && !isVowel(arr[right]))
                right--;
            
            swap(arr, left, right);
            left++;
            right--;
        }
        
        return new String(arr);
    }

    // 
    
}



