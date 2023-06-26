import java.util.Arrays;
import java.util.ArrayList;

public class l003_LIS {

    // =============================================================================================================================
    // Longest_Increasing_Subsequence
    // Question_25 : 300. Longest Increasing Subsequence -> left to right
    // https://leetcode.com/problems/longest-increasing-subsequence/
    public static int[] LIS_leftToRight(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int maxLIS = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            maxLIS = Math.max(maxLIS, dp[i]);
        }

        return dp;
    }

    // 2nd approach => TC => O(N*logN) 
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> lis_len = new ArrayList<>();
        lis_len.add(nums[0]);

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > lis_len.get(lis_len.size() - 1)) {
                lis_len.add(nums[i]);
            } else {
                // find just graeter element of nums[i] in arraylist
                int lo = 0, hi = lis_len.size() - 1;
                int idx = lo;
                while(lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    if(lis_len.get(mid) == nums[i]) {
                        idx = mid;
                        break;
                    } else if(lis_len.get(mid) < nums[i]) {
                        lo = mid + 1;
                        idx = lo;
                    } else {
                        hi = mid - 1;
                    }
                }
                lis_len.set(idx, nums[i]);
            }
        }

        return lis_len.size();
    }
    // =============================================================================================================================
    // Que_26 : maximum-sum-increasing-subsequence
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    public int maxSumIS(int arr[], int n) {  
        int[] dp = new int[n];
        int maxSum = 0;
        for(int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for(int j = i - 1; j >=  0; j--) {
                if(arr[i] > arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
            }
            
            maxSum = Math.max(maxSum , dp[i]);
        }
        
        return maxSum;
    }  

    // =============================================================================================================================
    // Question_27 : building-bridges
    // https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
    // {x-coordinate, y_coordinate}
    public static int buildingBridge(int[][] bridges) {
        Arrays.sort(bridges, (a,b) -> {
            return a[0] - b[0];
        });
        
        int n = bridges.length;
        int[] dp = new int[n];
        int max_len = 0;

        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(bridges[i][1] > bridges[i][j]) 
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max_len = Math.max(max_len, dp[i]);
        }

        return max_len;
    }

    // =============================================================================================================================
    // Ques_28 : Minimum number of deletion to be performed to make array sorted
    // arrays contains  -1e7 <= ele <= 1e7
    public int min_deletion(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int maxLIS = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] >= nums[j]) // edge case (equal sign bhi ayega)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            maxLIS = Math.max(maxLIS, dp[i]);
        }

        return n - maxLIS;
    }

    // Longest increasing Subsequences(LIS) -> right to left 
    // Longest decreasing subsequence(LDS) -> left to right
    public static int[] LIS_rightToLeft(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int max_len = 0;

        for(int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for(int j = i + 1; j < n ; j++) {
                if(arr[i] > arr[j]) 
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            max_len = Math.max(max_len, dp[i]);
        }

        return dp;
    }

    // =============================================================================================================================
    // Question_29 : Longest_Bitonic_Subsequesnce
    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    public static int longestBitonicSub(int[] arr) {
        int[] LIS = LIS_leftToRight(arr);
        int[] LDS = LIS_rightToLeft(arr);

        int n = arr.length, max = 0;
        for(int i = 0; i < n ; i++) {
            if(LIS[i] > 1 && LDS[i] > 1)
                max = Math.max(max, LIS[i] + LDS[i] - 1);
        }

        return max;
    }

    // =============================================================================================================================
    // Question_30 : 1671. Minimum Number of Removals to Make Mountain Array
    // https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
    public int minimumMountainRemovals(int[] arr) {
        int[] LIS = LIS_leftToRight(arr);
        int[] LDS = LIS_rightToLeft(arr);

        int n = arr.length, max = 0;
        for(int i = 0; i < n ; i++) {
            if(LIS[i] > 1 && LDS[i] > 1)
                max = Math.max(max, LIS[i] + LDS[i] - 1);
        }

        return n - max;
    }

    // =============================================================================================================================
    //  Que_31 : Maxium_Sum_Bitonic_SubSeq
    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1#
    // max sum increasing subsequence -> left to right LIS 
    public static int[] maxSumIS__LIS_LeftToRight(int arr[], int n) {  
        int[] dp = new int[n];
        int maxSum = 0;
        for(int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for(int j = i - 1; j >=  0; j--) {
                if(arr[i] > arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
            }
            
            maxSum = Math.max(maxSum , dp[i]);
        }
        
        return dp;
    }  
    
    // max sum Decresing subsequence -> right to left LIS
    public static int[] maxSumDS__LIS_RightToLeft(int[] arr, int n) {
        int[] dp = new int[n];
        int max_len = 0;

        for(int i = n - 1; i >= 0; i--) {
            dp[i] = arr[i];
            for(int j = i + 1; j < n ; j++) {
                if(arr[i] > arr[j]) 
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
            }

            max_len = Math.max(max_len, dp[i]);
        }

        return dp;
    }
    public static int maxSumBS(int arr[], int n){
        int[] maxSum_LIS = maxSumIS__LIS_LeftToRight(arr, n);
        int[] maxSum_LDS = maxSumDS__LIS_RightToLeft(arr, n);
        
        int maxSum_LBS = -(int)1e9;
        for(int i = 0; i < n; i++) {
            maxSum_LBS = Math.max(maxSum_LBS, maxSum_LIS[i] + maxSum_LDS[i] - arr[i]);
        }
        
        return maxSum_LBS;
    }

    // =============================================================================================================================
    // Que_32 : Longest Bitonic Subsequence II 
    public static int[] LDS_leftTOright(int[] arr, int n) {
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[i] < arr[j])
                    dp[i] = Math.max(arr[i], arr[j] + 1);
            }     
        }

        return dp;
    } 

    public static int[] LDS_rightTOleft(int[] arr, int n) {
        int[] dp = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for(int j = i + 1; j < n; j++) {
                if(arr[i] < arr[j])
                    dp[i] = Math.max(arr[i], arr[j] + 1);
            }     
        }

        return dp;
    } 

    public static int longestBitonicSub_02(int[] arr) {
        int n = arr.length;
        int[] LDS = LDS_leftTOright(arr, n);
        int[] LIS = LDS_rightTOleft(arr, n);
        int max_Len = 0;
        for(int i = 0; i < n; i++) {
            max_Len = Math.max(max_Len, LDS[i] + LIS[i] - 1);
        }

        return max_Len;
    }

    // =============================================================================================================================
    // Que_33 : 354. Russian Doll Envelopes
    // https://leetcode.com/problems/russian-doll-envelopes/
      public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if(a[1] == b[1]) return b[0] - a[0];
            return a[1] - b[1];
        });

        int[] heights = new int[envelopes.length];
        for(int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][0];
        }

        return lengthOfLIS(heights)
    }


    // =============================================================================================================================
    // LIS -> Recursive solution
    public static int LIS_recursive(int[] arr, int ei, int[] dp) {
        if(dp[ei] != 0) 
            return dp[ei];

        int max_len = 1;
        for(int i = ei - 1; i >= 0; i--) {
            if(arr[ei] > arr[i]) 
                max_len = Math.max(max_len, LIS_recursive(arr, i, dp));
        }

        return dp[ei] = max_len;
    }
    public static int LIS_recursive(int[] arr) {
        int max = 0, n = arr.length;
        int[] dp = new int[n];

        for(int i = 0; i < n; i++) {
            max = Math.max(max, LIS_recursive(arr, i, dp));
        }

        return max;
    }

    // LIS -> return subsequence
    public static int[] LIS(int[] nums, int[] dp) {
        int n = nums.length; 
        int[] max_len = new int[2]; // {len, idx}
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            if(max_len[0] < dp[i]) {
                max_len[0] = dp[i];
                max_len[1] = i;
            }
        }

        return max_len;
    }

    public static void LIS(int[] arr, int[] dp, ArrayList<Integer> ans, int ei) {
        for(int i = ei - 1; i >= 0; i--) {
            if(arr[i] < arr[ei] && dp[i] == dp[ei] - 1) {
                ans.add(arr[i]);
            } 
            ei = i;
        }
    }

    public static ArrayList<Integer> LIS_String(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int[] max_len = LIS(arr, dp);
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(arr[max_len[1]]);
        LIS(arr, dp, ans, max_len[1]);

        return ans;
    }  
    
    // =============================================================================================================================
    // Que_34 : 673. Number of Longest Increasing Subsequence
    // https://leetcode.com/problems/number-of-longest-increasing-subsequence/
    public int findNumberOfLIS(int[] arr) {
        int n = arr.length;
        int[] LIS_Len = new int[n]; // arr[i] pe khatam hone wala LIS ka length store karenge
        int[] LIS_Count = new int[n];  // arr[i] pe khatam hone wala "LIS_Len" ka kitne No. of LIS possible he woh store karenge
        
        int max_LIS_Len = 0; // poore array me se banne wala max LIS ka length store karenge
        int max_LIS_Count = 0;  // max_LIS_Len ka kitna LIS ban sakta he wo store karenge
        
        for(int i = 0; i < n; i++) {
            LIS_Len[i] = 1;
            LIS_Count[i] = 1;
            
            for(int j = i - 1; j >= 0; j--) {
                if(arr[i] > arr[j]) {
                    if(LIS_Len[j] + 1 > LIS_Len[i]) {
                        LIS_Len[i] = LIS_Len[j] + 1;
                        LIS_Count[i] = LIS_Count[j];
                    } else if(LIS_Len[j] + 1 == LIS_Len[i]) {
                        LIS_Count[i] += LIS_Count[j];
                    }
                }
            }
            
            if(LIS_Len[i] > max_LIS_Len) {
                max_LIS_Len = LIS_Len[i];
                max_LIS_Count = LIS_Count[i];
            } else if(LIS_Len[i] == max_LIS_Len) {
                max_LIS_Count += LIS_Count[i];
            }
        }
        
        return max_LIS_Count;
    }

    // =============================================================================================================================
    // Question_35 : 646. Maximum Length of Pair Chain 
    // https://leetcode.com/problems/maximum-length-of-pair-chain/
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> {
            return a[0] - b[0];
        });
        
        int n = pairs.length;
        int[] dp = new int[n];
        
        int maxLen = 0;
        for(int i = 0; i < n; i++) {
            int max = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(pairs[i][0] > pairs[j][1]) {
                    max = Math.max(max, dp[j]);
                }
            }
            
            dp[i] = max + 1;
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }

    // =============================================================================================================================
    // Question_36 : Box Stacking
    // https://practice.geeksforgeeks.org/problems/box-stacking/1
    private static class Box implements Comparable<Box>{
        int l;
        int b;
        int h;
        int area;
        
        Box(int l, int b, int h) {
            this.l = l;
            this.b = b;
            this.h = h;
            this.area = this.l * this.b;
        }
        
        public int compareTo(Box o) {
            return o.area - this.area;
        }
    }
    public static int maxHeight(int[] height, int[] width, int[] length, int n) {
        // Code here
        Box[] box = new Box[3 * n];
        int j = 0;
        for(int i = 0; i < n; i++) {
            int l = length[i], b = width[i], h = height[i];
            
            // lbh
            if(l > b) {
                box[j++] = new Box(l, b, h);
            } else {
                box[j++] = new Box(b, l, h);
            }
            
            // hbl
            if(h > b) {
                box[j++] = new Box(h, b, l);
            } else {
                box[j++] = new Box(b, h, l);
            }
            
            // lhb
            if(l > h) {
                box[j++] = new Box(l, h, b);
            } else {
                box[j++] = new Box(h, l, b);
            }
        }
        
        Arrays.sort(box);
        int[] dp = new int[box.length];
        int maxHt = 0;
        for(int i = 0; i < box.length; i++) {
            int max = 0;
            for(int k = i - 1; k >= 0; k--) {
                if(box[k].l > box[i].l && box[k].b > box[i].b) {
                    max = Math.max(max, dp[k]);
                }
            }
            
            dp[i] = max + box[i].h;
            maxHt = Math.max(maxHt, dp[i]); 
        }
        
        return maxHt;
    }

    // =============================================================================================================================
    // Question_37 : 1691. Maximum Height by Stacking Cuboids
    // https://leetcode.com/problems/maximum-height-by-stacking-cuboids/
    private class Box implements Comparable<Box>{
        int l;
        int b;
        int h;
        int area;
        
        Box(int l, int b, int h) {
            this.l = l;
            this.b = b;
            this.h = h;
            this.area = this.l * this.b;
        }
        
        public int compareTo(Box o) {
            if(this.area == o.area) {
                return o.h - this.h;
            }
            
            return o.area - this.area;
        }
    }
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        Box[] box = new Box[n];
        int j = 0;
        for(int[] c : cuboids) {
            Arrays.sort(c);
            box[j++] = new Box(c[0], c[1], c[2]);
        }
        Arrays.sort(box);
        
        int maxHt = 0;
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            Box box1 = box[i]; // {l, b, h}
            int max = 0;
            for(int k = i - 1; k >= 0; k--) {
                Box box2 = box[k];
                if(box2.l >= box1.l && box2.b >= box1.b && box2.h >= box1.h) {
                    max = Math.max(max, dp[k]);
                }
            }
            dp[i] = max + box1.h;
            maxHt = Math.max(maxHt, dp[i]);
        }
        return maxHt;
    }

}