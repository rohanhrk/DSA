import java.util.Arrays;

public class quesn {
    // 1035. Uncrossed Lines
    // mucl -> max Uncrossed Lines
    public static int mucl(int[] a1, int[] a2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }
        if (dp[n][m] != -1)
            return dp[n][m];
        if (a1[n - 1] == a2[m - 1])
            return dp[n][m] = mucl(a1, a2, n - 1, m - 1, dp) + 1;
        else
            return dp[n][m] = Math.max(mucl(a1, a2, n - 1, m, dp), mucl(a1, a2, n, m - 1, dp));
    }

    public static int mucl_dp(int[] a1, int[] a2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (a1[n - 1] == a2[m - 1])
                    dp[n][m] = dp[n - 1][m - 1] + 1; // mucl(a1, a2, n - 1, m - 1, dp) + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];

    }

    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = mucl(nums1, nums2, n, m, dp);
        return ans;
    }

    
}
