import java.util.Arrays;

public class l002_stringSet {
    public static void print(int[] arr) {
        for (int a : arr)
            System.out.print(a + " ");

        System.out.println();
    }

    public static void print2D(int[][] arr) {
        for (int[] a : arr)
            print(a);
        System.out.println();
    }

    // ==========================================================================================================================================================
    // Question_1 : 516. Longest Palindromic Subsequence
    // https://leetcode.com/problems/longest-palindromic-subsequence/
    // LPSS - Longest Palindromic Subsequence
    public static int LPSS(String str, int i, int j, int[][] dp) {
        if (i >= j) {
            return dp[i][j] = (i == j ? 1 : 0);
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        if (str.charAt(i) == str.charAt(j))
            return dp[i][j] = LPSS(str, i + 1, j - 1, dp) + 2;
        else
            return dp[i][j] = Math.max(LPSS(str, i + 1, j, dp), LPSS(str, i, j - 1, dp));
    }

    public static int LPSS_dp(String str, int I, int J, int[][] dp) {
        int n = str.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {

                if (i >= j) {
                    dp[i][j] = (i == j ? 1 : 0);
                    continue;
                }

                if (str.charAt(i) == str.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;// LPSS(str, i + 1, j - 1, dp) + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);// Math.max(LPSS(str, i + 1, j, dp), LPSS(str,
                                                                    // i, j - 1, dp));

            }
        }
        return dp[I][J];
    }

    public static String LPSS_String_dp(String str, int I, int J, String[][] dp) {
        int n = str.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {

                if (i >= j) {
                    dp[i][j] = (i == j ? str.charAt(i) + "" : "");
                    continue;
                }

                if (str.charAt(i) == str.charAt(j))
                    dp[i][j] = str.charAt(i) + dp[i + 1][j - 1] + str.charAt(i);// LPSS(str, i + 1, j - 1, dp) + 2;
                else
                    dp[i][j] = dp[i + 1][j].length() > dp[i][j - 1].length() ? dp[i + 1][j] : dp[i][j - 1];// Math.max(LPSS(str,
                                                                                                           // i + 1, j,
                                                                                                           // dp),
                                                                                                           // LPSS(str,
                // i, j - 1, dp));

            }
        }
        return dp[I][J];
    }

    public static void longestPAlindromicSubsequence() {
        String str = "geeksfgeek";
        int n = str.length();
        String[][] dp = new String[n][n];
        for (String[] d : dp)
            Arrays.fill(d, "");
        // System.out.println(LPSS(str, 0, n - 1, dp));
        System.out.println(LPSS_String_dp(str, 0, n - 1, dp));

        // print2D(dp);
    }

    // ==========================================================================================================================================================
    // LPS : Longest Palindromic Substring
    public static void LPS(String str, boolean[][] dp) {
        int n = str.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j);
                else
                    dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1];
            }
        }
    }

    // 5. Longest Palindromic Substring
    // 647. Palindromic Substrings
    public static class pair {
        int count;
        int length;
        String s;

        pair(int count, int length, String s) {
            this.count = count;
            this.length = length;
            this.s = s;
        }

    }

    public static pair LPS(String s, int[][] dp) {
        int n = s.length();
        int maxLength = 0;
        int count = 0;
        int si = 0; // starting index
        int ei = 0; // ending index
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++, i++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (gap == 1)
                    dp[i][j] = (s.charAt(i) == s.charAt(j) ? 2 : 0);
                else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] > 0)
                    dp[i][j] = dp[i + 1][j - 1] + 2;

                if (dp[i][j] > maxLength) {
                    maxLength = dp[i][j];
                    si = i;
                    ei = j;
                }

                if (dp[i][j] != 0)
                    count++;
            }

        }

        pair lps = new pair(count, maxLength, s.substring(si, ei + 1));
        return lps;
    }

    public static void palindromicSubstring() {
        String s = "geegfgeek";
        int n = s.length();
        int[][] dp = new int[n][n];
        pair lps = LPS(s, dp);
        System.out.println(lps.count + " " + lps.length + " " + lps.s);
    }

    // 1143. Longest Common Subsequence
    public static int LCSS_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0)
            return dp[n][m] = 0;
        if (dp[n][m] != -1)
            return dp[n][m];
        if (s1.charAt(n - 1) == s2.charAt(m - 1))
            return dp[n][m] = LCSS_memo(s1, s2, n - 1, m - 1, dp) + 1;
        else
            return dp[n][m] = Math.max(LCSS_memo(s1, s2, n - 1, m, dp), LCSS_memo(s1, s2, n, m - 1, dp));
    }

    public static int LCSS_dp(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m < M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (s1.charAt(n - 1) == s2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];

    }

    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = LCSS_memo(s1, s2, n, m, dp);
        return ans;
    }

    // 72. Edit Distance
    public static int minDistance_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0 ? m : n);
        }
        if (dp[n][m] != -1)
            return dp[n][m];
        int insert = minDistance_memo(s1, s2, n, m - 1, dp);
        int delete = minDistance_memo(s1, s2, n - 1, m, dp);
        int replace = minDistance_memo(s1, s2, n - 1, m - 1, dp);

        if (s1.charAt(n - 1) == s2.charAt(m - 1))
            return dp[n][m] = minDistance_memo(s1, s2, n - 1, m - 1, dp);

        else
            return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
    }

    public static int minDistance_dp(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = (n == 0 ? m : n);
                    continue;
                }

                int insert = dp[n][m - 1]; // minDistance_memo(s1, s2, n, m - 1, dp);
                int delete = dp[n - 1][m]; // minDistance_memo(s1, s2, n - 1, m, dp);
                int replace = dp[n - 1][m - 1]; // minDistance_memo(s1, s2, n - 1, m - 1, dp);

                if (s1.charAt(n - 1) == s2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1]; // minDistance_memo(s1, s2, n - 1, m - 1, dp);

                else
                    return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
            }
        }
        return dp[N][M];

    }

    public int minDistance(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = minDistance_memo(s1, s2, n, m, dp);
        return ans;
    }

    // Cost of insert , replace and delete is {2,1,2};
    public int minCostToConvert_DP(String s1, String s2, int N, int M, int[] cost, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = (n == 0 ? m * cost[0] : n * cost[2]);
                    continue;
                }

                int insert = dp[n][m - 1];
                int replace = dp[n - 1][m - 1];
                int delete = dp[n - 1][m];

                if (s1.charAt(n - 1) == s2.charAt(m - 1))
                    dp[n][m] = replace;
                else
                    dp[n][m] = Math.min(Math.min(insert + cost[0], replace + cost[1]), delete + cost[2]);
            }
        }

        return dp[N][M];
    }

    public int minCostToConvert(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int[] cost = { 2, 1, 2 };
        int ans = minCostToConvert_DP(word1, word2, n, m, cost, dp);
        return ans;
    }

    // https://www.geeksforgeeks.org/edit-distance-and-lcs-longest-common-subsequence/?ref=rp
    // where we are allowed only two operations insert and delete, find edit
    // distance in this variation.
    public static int LCSS(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m < M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (s1.charAt(n - 1) == s2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];

    }

    public static int editDistanceVariation(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        int lcss = LCSS(s1, s2, n, m, dp);
        int minOperation = (n - lcss) + (m - lcss);
        return minOperation;
    }

    // https://practice.geeksforgeeks.org/problems/minimum-deletitions1648/1
    public static int LPSS_(String str, int I, int J, int[][] dp) {
        int n = str.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (i >= j) {
                    dp[i][j] = (i == j) ? 1 : 0;
                    continue;
                }

                if (str.charAt(i) == str.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;// LPSS(str, i + 1, j - 1, dp) + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[I][J];
    }

    public static void minDeleteToMakePlaindrome(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];

        int ans = n - LPSS_(str, 0, n - 1, dp);
        System.out.println(ans);
    }

    public static void main(String[] args) {
        palindromicSubstring();

    }
}
