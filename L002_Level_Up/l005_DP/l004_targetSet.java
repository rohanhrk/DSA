import java.util.Arrays;

public class l004_targetSet {
    public static void display_1d(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");
    }

    public static void display_2d(int[][] arr) {
        for (int[] ar : arr) {
            display_1d(ar);
            System.out.println();
        }
    }

    // =============================================================================================================================
    // Que_35 : Permutation with infinite coins
    public static int permutation_memo(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != -1)
            return dp[tar];

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                count += permutation_memo(arr, tar - ele, dp);
            }
        }

        return dp[tar] = count;
    }

    public static int permutation_tabu(int[] arr, int TAR, int[] dp) {
        for (int tar = 0; tar <= TAR; tar++) {
            if (tar == 0) {
                dp[tar] = 1;
                continue;
            }

            int count = 0;
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    count += dp[tar - ele];
                }
            }

            dp[tar] = count;
        }
        return dp[TAR];
    }

    // =============================================================================================================================
    // Que_36 : Combination with infinite coins
    public static int combination_memo(int[] arr, int tar, int n, int[][] dp) {
        if (tar == 0)
            return dp[n][tar] = 1;

        if (dp[n][tar] != -1)
            return dp[n][tar];

        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (tar - arr[i] >= 0) {
                count += combination_memo(arr, tar - arr[i], i + 1, dp);
            }
        }

        return dp[n][tar] = count;
    }

    public static int combination_tabu(int[] arr, int TAR, int N, int[] dp) {
        dp[0] = 1;

        for (int ele : arr) {
            for (int tar = 0; tar <= TAR; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[TAR];
    }

    public static void coinChage() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        // // permutation
        // int[] dp = new int[tar + 1];
        // // Arrays.fill(dp, -1);
        // // System.out.println(permutation_memo(arr, tar, dp));
        // System.out.println(permutation_tabu(arr, tar, dp));
        // display_1d(dp);

        // combination
        int n = arr.length;
        // int[][] dp = new int[n + 1][tar + 1];
        // for(int[] d : dp)
        // Arrays.fill(d, -1);
        int[] dp = new int[tar + 1];
        System.out.println(combination_tabu(arr, tar, n, dp));
        display_1d(dp);
    }

    // =============================================================================================================================
    // Que_37 : 322. Coin Change
    public int coinChange_memo(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 0;
        }

        if (dp[tar] != (int) 1e9)
            return dp[tar];

        int minCount = (int) 1e8;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                minCount = Math.min(minCount, coinChange_memo(arr, tar - ele, dp) + 1);
            }
        }

        return dp[tar] = minCount >= (int) 1e8 ? (int) 1e8 : minCount;
    }

    public int coinChange_tabu(int[] arr, int TAR, int[] dp) {
        for (int tar = 0; tar <= TAR; tar++) {
            if (tar == 0) {
                dp[tar] = 0;
                continue;
            }

            int minCount = (int) 1e8;
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    minCount = Math.min(minCount, dp[tar - ele] + 1);
                }
            }

            dp[tar] = minCount >= (int) 1e8 ? (int) 1e8 : minCount;
        }

        return dp[TAR];

    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int ans = coinChange_tabu(coins, amount, dp);

        return ans == (int) 1e8 ? -1 : ans;
    }

    // =============================================================================================================================
    // Que_38 : 518. Coin Change 2
    public int change_memo(int[] arr, int tar, int n, int[][] dp) {
        if (tar == 0)
            return dp[n][tar] = 1;

        if (dp[n][tar] != -1)
            return dp[n][tar];

        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (tar - arr[i] >= 0) {
                count += change_memo(arr, tar - arr[i], i + 1, dp);
            }
        }

        return dp[n][tar] = count;
    }

    public static int change_tabu(int[] arr, int TAR, int N, int[] dp) {
        dp[0] = 1;

        for (int ele : arr) {
            for (int tar = 0; tar <= TAR; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[TAR];
    }

    public int change(int tar, int[] arr) {
        int n = arr.length;
        int[] dp = new int[tar + 1];
        return change_tabu(arr, tar, n, dp);
    }

    public static void main(String[] args) {
        coinChage();
    }

    // =============================================================================================================================

    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/

    // =============================================================================================================================
    // Que_39 : 377. Combination Sum IV
    public int combinationSum4(int[] nums, int target, int[] dp) {
        if (target == 0)
            return dp[target] = 1;

        if (dp[target] != -1)
            return dp[target];

        int count = 0;
        for (int ele : nums) {
            if (target - ele >= 0) {
                count += combinationSum4(nums, target - ele, dp);
            }
        }

        return dp[target] = count;
    }

    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);

        int count = combinationSum4(nums, target, dp);
        return count == -1 ? 0 : count;
    }

    // =============================================================================================================================
    // Que_40 : subset sum problem
    public static int subsetSum_memo(int[] arr, int tar, int n, int[][] dp) {
        if (tar == 0 || n == 0)
            return dp[n][tar] = (tar == 0) ? 1 : 0;

        if (dp[n][tar] == -1)
            return dp[n][tar];

        int count = 0;
        if (tar - arr[n - 1] >= 0)
            count += subsetSum_memo(arr, tar - arr[n - 1], n - 1, dp);

        count += subsetSum_memo(arr, tar, n - 1, dp);

        return dp[n][tar] = count;
    }

    public static int subsetSum_tebo(int[] arr, int TAR, int N, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= TAR; tar++) {
                if (tar == 0 || n == 0) {
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }

                int count = 0;
                if (tar - arr[n - 1] >= 0)
                    count += dp[n - 1][tar - arr[n - 1]];

                count += dp[n - 1][tar];

                dp[n][tar] = count;
            }
        }

        return dp[N][TAR];
    }

    // =============================================================================================================================
    // Que_41 : 0-1_knapsack_problems_
    public static int knapSack_memo(int W, int wt[], int val[], int n, int[][] dp) {
        if (W == 0 || n == 0)
            return dp[n][W] = 0;

        if (dp[n][W] != -1)
            return dp[n][W];

        int include = 0;
        if (W - wt[n - 1] >= 0)
            include = knapSack_memo(W - wt[n - 1], wt, val, n - 1, dp) + val[n - 1];
        int exclude = knapSack_memo(W, wt, val, n - 1, dp);

        return dp[n][W] = Math.max(include, exclude);

    }

    // =============================================================================================================================
    // Que_42 : UNBOUNDED_KNAPSACK_problems
    static int unboundedKnapSack_memo(int W, int wt[], int val[], int n, int[][] dp) {
        if (W == 0 || n == 0)
            return dp[n][W] = 0;

        if (dp[n][W] != -1)
            return dp[n][W];

        int include = 0;
        if (W - wt[n - 1] >= 0)
            include = unboundedKnapSack_memo(W - wt[n - 1], wt, val, n, dp) + val[n - 1];
        int exclude = unboundedKnapSack_memo(W, wt, val, n - 1, dp);

        return dp[n][W] = Math.max(include, exclude);

    }

    // using 2d_==============================
    static int unboundedKnapSack_tabu_2d(int w, int wt[], int val[], int N, int[][] dp) {
        for (int W = 0; W <= w; W++) {
            for (int n = 0; n <= N; n++) {
                if (W == 0 || n == 0) {
                    dp[n][W] = 0;
                    continue;
                }

                int include = 0;
                if (W - wt[n - 1] >= 0)
                    include = dp[W - wt[n - 1]][n] + val[n - 1];
                int exclude = dp[W][n - 1];

                return dp[n][W] = Math.max(include, exclude);
            }
        }

        return dp[w][N];

    }

    // using 1d_========================
    public static int  unboundedKnapSack_tabu_1d(int W, int wt[], int val[], int N, int[] dp) {
        for(int ele : wt) {
            for(int w = 0; w <= W; w++) {
                if(w - ele >= 0)
                    dp[w] = Math.max(dp[w], dp[w - ele]);
            }
        }

        return dp[W];
    }

    static int knapSack(int N, int W, int val[], int wt[]) {
        // code here
        int[][] dp = new int[N + 1][W + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return unboundedKnapSack_memo(W, wt, val, N, dp);
    }

    // =============================================================================================================================
    // Que_43 : 416. Partition Equal Subset Sum
    // -1 -> undefined, 0 -> false , 1 -> true     
    public int canPartition(int[] nums, int tar, int n, int[][] dp) {
        if(n == 0 || tar == 0)
            return dp[n][tar] = (tar == 0) ? 1 : 0;
        
        if(dp[n][tar] != -1) 
            return dp[n][tar];
        
        boolean res = false;
        if(tar - nums[n - 1] >= 0)
            res = res || canPartition(nums, tar - nums[n - 1], n - 1, dp) == 1;
        res = res || canPartition(nums, tar, n - 1, dp) == 1;
        
        return dp[n][tar] = (res ? 1 : 0);
    }
    public boolean canPartition(int[] nums) {
        int sum = 0, n = nums.length;
        for(int ele : nums)
            sum += ele;
        
        if(n == 0 || sum % 2 != 0)
            return false;
        
        int tar = sum / 2;
        int[][] dp = new int[n + 1][tar + 1];
        for(int[] d : dp)
            Arrays.fill(d, -1);
        return canPartition(nums, tar, n, dp) == 1;
        
    }
}