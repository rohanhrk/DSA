import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;



public class l005_cutSet {
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
    // Que_44 : matrix_chain_multiplication
    // memoization
    public static int mcm_memo(int[] arr, int si, int ei, int[][] dp) {
        if (si + 1 == ei)
            return dp[si][ei] = 0;

        if (dp[si][ei] != -1)
            return dp[si][ei];

        int minRes = (int) 1e9;
        for (int cut = si + 1; cut < ei; cut++) {
            int leftRes = mcm_memo(arr, si, cut, dp);
            int rightRes = mcm_memo(arr, cut, ei, dp);

            int myRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
            if (myRes < minRes)
                minRes = myRes;
        }

        return dp[si][ei] = minRes;
    }

    // tabulation
    public static int mcm_tabu(int[] arr, int SI, int EI, int[][] dp) {
        for (int gap = 1; gap < arr.length; gap++) {
            for (int si = 0, ei = gap; ei < arr.length; si++, ei++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    continue;
                }

                int minRes = (int) 1e9;
                for (int cut = si + 1; cut < ei; cut++) {
                    int leftRes = dp[si][cut];
                    int rightRes = dp[cut][ei];

                    int myRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
                    if (myRes < minRes)
                        minRes = myRes;
                }

                dp[si][ei] = minRes;
            }
        }

        return dp[SI][EI];
    }

    public static String mcm_tabu_findString(int[] arr, int SI, int EI, int[][] dp) {
        int n = arr.length;
        String[][] string_dp = new String[n][n];
        for (String[] d : string_dp)
            Arrays.fill(d, "");

        for (int gap = 1; gap < arr.length; gap++) {
            for (int si = 0, ei = gap; ei < arr.length; si++, ei++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    string_dp[si][ei] = (char) (si + 'A') + "";
                    continue;
                }

                int minRes = (int) 1e9;
                String minString = "";
                for (int cut = si + 1; cut < ei; cut++) {
                    int leftRes = dp[si][cut];
                    int rightRes = dp[cut][ei];

                    int myRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
                    if (myRes < minRes) {
                        minRes = myRes;
                        minString = '(' + string_dp[si][cut] + string_dp[cut][ei] + ')';
                    }

                }

                dp[si][ei] = minRes;
                string_dp[si][ei] = minString;
            }
        }

        return string_dp[SI][EI];
    }

    public static void mcm() {
        int[] arr = { 4, 2, 3, 2, 3, 5, 4 };
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        // System.out.println(mcm_memo(arr, 0, n - 1, dp));
        // display_2d(dp);

        // System.out.println(mcm_tabu(arr, 0, n - 1, dp));
        // display_2d(dp);

        System.out.println(mcm_tabu_findString(arr, 0, n - 1, dp));
        display_2d(dp);

    }

    // =============================================================================================================================
    // Que_45 : https://www.geeksforgeeks.org/minimum-maximum-values-expression/
    // Minimum and Maximum values of an expression with * and +
    public static class min_max_pair {
        int min_value = (int) 1e9;
        int max_value = -(int) 1e9;

        min_max_pair(int min_value, int max_value) {
            this.min_value = min_value;
            this.max_value = max_value;
        }

        min_max_pair() {

        }
    }

    public static min_max_pair evaluation(min_max_pair left_pair, min_max_pair right_pair, char operator) {
        if (operator == '+')
            return new min_max_pair(left_pair.min_value + right_pair.min_value,
                    left_pair.max_value + right_pair.max_value);
        else
            return new min_max_pair(left_pair.min_value * right_pair.min_value,
                    left_pair.max_value * right_pair.max_value);
    }

    public static min_max_pair minMaxEvaluation(String str, int si, int ei, min_max_pair[][] dp) {
        if (si == ei) {
            int val = str.charAt(si) - '0';
            return new min_max_pair(val, val);
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        min_max_pair my_pair = new min_max_pair();
        for (int cut = si + 1; cut < ei; cut += 2) {
            min_max_pair left_pair = minMaxEvaluation(str, si, cut - 1, dp);
            min_max_pair right_pair = minMaxEvaluation(str, cut + 1, ei, dp);

            // evaluate of both left and right result
            min_max_pair eval = evaluation(left_pair, right_pair, str.charAt(cut));

            // update min_value and max_value
            my_pair.min_value = Math.min(my_pair.min_value, eval.min_value);
            my_pair.max_value = Math.max(my_pair.max_value, eval.max_value);
        }

        return dp[si][ei] = my_pair;
    }

    public static void minMaxEvaluation() {
        String str = "1+2*3+4*5";
        int n = str.length();
        min_max_pair[][] dp = new min_max_pair[n][n];

        min_max_pair val = minMaxEvaluation(str, 0, n - 1, dp);

        System.out.println("min_value : " + val.min_value);
        System.out.println("max_value : " + val.max_value);
    }

    // =============================================================================================================================
    // Que_44 : 312. Burst Balloons
    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if (dp[si][ei] != -1)
            return dp[si][ei];

        int left_val = (si == 0) ? 1 : nums[si - 1];
        int right_val = (ei == nums.length - 1) ? 1 : nums[ei + 1];

        int max_cost = 0;
        for (int cut = si; cut <= ei; cut++) {
            int left_cost = (cut == si) ? 0 : maxCoins(nums, si, cut - 1, dp);
            int right_cost = (cut == nums.length - 1) ? 0 : maxCoins(nums, cut + 1, ei, dp);

            int my_cost = left_cost + left_val * nums[cut] * right_val + right_cost;
            max_cost = Math.max(max_cost, my_cost);
        }

        return dp[si][ei] = max_cost;
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return maxCoins(nums, 0, n - 1, dp);
    }

    // =============================================================================================================================
    // Que_45 : https://practice.geeksforgeeks.org/problems/boolean-parenthesization5610/1
    public static class boolean_pair {
        int true_count = 0;
        int false_count = 0;

        boolean_pair(int true_count, int false_count) {
            this.true_count = true_count;
            this.false_count = false_count;
        }
    }

    public static void evaluation(boolean_pair lp, boolean_pair rp, char operator, boolean_pair my_pair) {
        int total_TF_count = (lp.true_count + lp.false_count) * (rp.true_count + rp.false_count);
        int mod = 1003;
        if (operator == '|') {
            int false_count = lp.false_count * rp.false_count;
            my_pair.true_count = (my_pair.true_count % mod + total_TF_count % mod - false_count % mod + mod) % mod;
            my_pair.false_count = (my_pair.false_count % mod + false_count % mod) % mod;
        } else if (operator == '&') {
            int true_count = lp.true_count * rp.true_count;
            my_pair.true_count = (my_pair.true_count % mod + true_count % mod) % mod;
            my_pair.false_count = (my_pair.false_count % mod + total_TF_count % mod - true_count % mod) % mod;
        } else {
            int true_count = lp.true_count * rp.false_count + lp.false_count * rp.true_count;
            my_pair.true_count = (my_pair.true_count % mod + true_count % mod) % mod;
            my_pair.false_count = (my_pair.false_count % mod + total_TF_count % mod - true_count % mod + mod) % mod;
        }
    }

    public static boolean_pair countWays_memo(String str, int si, int ei, boolean_pair[][] dp) {
        // code here
        if (si == ei) {
            int true_count = (str.charAt(si) == 'T') ? 1 : 0;
            int false_count = (str.charAt(si) == 'F') ? 1 : 0;

            return dp[si][ei] = new boolean_pair(true_count, false_count);
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        boolean_pair my_pair = new boolean_pair(0, 0);
        for (int cut = si + 1; cut < ei; cut += 2) {
            boolean_pair left_pair = countWays_memo(str, si, cut - 1, dp);
            boolean_pair right_pair = countWays_memo(str, cut + 1, ei, dp);
            char operator = str.charAt(cut);

            evaluation(left_pair, right_pair, operator, my_pair);
        }

        return dp[si][ei] = my_pair;
    }

    public static int countWays(int N, String S) {
        // code here
        boolean_pair[][] dp = new boolean_pair[N][N];

        return countWays_memo(S, 0, N - 1, dp).true_count;
    }

    // =============================================================================================================================
    // Que_46 : https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/
    public static int optimalSearchTree_memo(int keys[], int freq[], int si, int ei, int[][] dp) {
        // code here
        if (dp[si][ei] != -1)
            return dp[si][ei];

        int minFreq = (int) 1e9;
        int sum = 0;
        for (int cut = si; cut <= ei; cut++) {
            int leftFreq = (cut == si) ? 0 : optimalSearchTree_memo(keys, freq, si, cut - 1, dp);
            int rightFreq = (cut == ei) ? 0 : optimalSearchTree_memo(keys, freq, cut + 1, ei, dp);

            sum += freq[cut];

            int myAns = leftFreq + rightFreq;
            minFreq = Math.min(minFreq, myAns);
        }

        return dp[si][ei] = minFreq + sum;
    }

    public static int optimalSearchTree(int keys[], int freq[], int n) {
        // code here
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return optimalSearchTree_memo(keys, freq, 0, n - 1, dp);
    }

    // =============================================================================================================================
    // Que_47 : 95. Unique Binary Search Trees II
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void generate_all_BST(List<TreeNode> left_bst_rootNode, int num, List<TreeNode> right_bst_rootNode,
            List<TreeNode> ans) {
        if (left_bst_rootNode.size() != 0 && right_bst_rootNode.size() != 0) {
            for (int i = 0; i < left_bst_rootNode.size(); i++) {
                for (int j = 0; j < right_bst_rootNode.size(); j++) {
                    TreeNode root = new TreeNode(num);
                    root.left = left_bst_rootNode.get(i);
                    root.right = right_bst_rootNode.get(j);
                    ans.add(root);
                }
            }
        } else if (left_bst_rootNode.size() != 0) {
            for (int i = 0; i < left_bst_rootNode.size(); i++) {
                TreeNode root = new TreeNode(num);
                root.left = left_bst_rootNode.get(i);
                ans.add(root);
            }
        } else if (right_bst_rootNode.size() != 0) {
            for (int j = 0; j < right_bst_rootNode.size(); j++) {
                TreeNode root = new TreeNode(num);
                root.right = right_bst_rootNode.get(j);
                ans.add(root);
            }
        } else {
            TreeNode root = new TreeNode(num);
            ans.add(root);
        }
    }

    public List<TreeNode> generateTrees_memo(int si, int ei) {
        List<TreeNode> ans = new ArrayList<>();
        for (int cut = si; cut <= ei; cut++) {
            List<TreeNode> left_bst_rootNode = generateTrees_memo(si, cut - 1);
            List<TreeNode> right_bst_rootNode = generateTrees_memo(cut + 1, ei);

            generate_all_BST(left_bst_rootNode, cut, right_bst_rootNode, ans);
        }

        return ans;
    }

    public List<TreeNode> generateTrees(int n) {
        return generateTrees_memo(1, n);
    }

    // =============================================================================================================================
    // Que_48 : 1039. Minimum Score Triangulation of Polygon
    public int minScoreTriangulation_memo(int[] values, int si, int ei, int[][] dp) {
        if(ei - si <= 1)
            return dp[si][ei] = 0;
        
        if(dp[si][ei] != -1)
            return dp[si][ei];
        
        int minAns = (int)1e9;
        for(int cut = si + 1; cut < ei; cut++) {
            int left_res = minScoreTriangulation_memo(values, si, cut, dp);
            int right_res = minScoreTriangulation_memo(values, cut, ei, dp);
            
            int myAns = left_res + (values[si] * values[cut] * values[ei]) + right_res;
            minAns = Math.min(minAns, myAns);
        }    
        
        return dp[si][ei] = minAns;
    }
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for(int[] d : dp)
            Arrays.fill(d, -1);
        
        return minScoreTriangulation_memo(values, 0, n - 1, dp);
    }

    public static void main(String[] args) {
        // mcm();
        minMaxEvaluation();
    }
}