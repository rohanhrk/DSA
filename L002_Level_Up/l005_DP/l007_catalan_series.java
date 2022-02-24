public class l007_catalan_series {
    // ======================================================================================================================================
    // Question_1 : Catalan Number
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/dynamic-programming-l2/catalan-number-official/ojquestion
    private static int catalan(int n) {
        int[] dp = new int[n + 1];
        for(int i = 0; i < n + 1; i++) {
            if(i == 0 || i == 1) {
                dp[i] = 1;
                continue;
            }
            
            for(int itr1 = 0, itr2 = i - 1; itr1 < i; itr1++, itr2--) {
                dp[i] += dp[itr1] * dp[itr2];
            }
        }
        return dp[n];
    }

    // ======================================================================================================================================
    // Question_2 : 96. Unique Binary Search Trees
    // https://leetcode.com/problems/unique-binary-search-trees/
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        for(int i = 0; i < n + 1; i++) {
            if(i == 0 || i == 1) {
                dp[i] = 1;
                continue;
            }
            
            for(int itr1 = 0, itr2 = i - 1; itr1 < i; itr1++, itr2--) {
                dp[i] += dp[itr1] * dp[itr2];
            }
        }
        
        return dp[n];
    }
}