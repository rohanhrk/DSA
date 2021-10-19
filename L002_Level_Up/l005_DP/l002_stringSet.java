import java.util.Arrays;
public class l002_stringSet {
    // 516. Longest Palindromic Subsequence
    public int lpss_memo(String s, int si, int ei, int[][] dp) {
        if (si >= ei)
            return dp[si][ei] = si == ei ? 1 : 0;

        if (dp[si][ei] != -1)
            return dp[si][ei];

        char ch1 = s.charAt(si), ch2 = s.charAt(ei);

        int len = 0;
        if (ch1 == ch2) {
            len = 2 + lpss_memo(s, si + 1, ei - 1, dp);
        } else {
            len = Math.max(lpss_memo(s, si + 1, ei, dp), lpss_memo(s, si, ei - 1, dp));
        }

        return dp[si][ei] = len;
    }

    public int lpss_tabu(String s, int SI, int EI, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (si >= ei) {
                    dp[si][ei] = (si == ei) ? 1 : 0;
                    continue;
                }

                char ch1 = s.charAt(si), ch2 = s.charAt(ei);
                if (ch1 == ch2) {
                    dp[si][ei] = dp[si + 1][ei - 1] + 2;// pss_memo(s, si + 1, ei - 1, dp);
                } else {
                    dp[si][ei] = Math.max(dp[si + 1][ei], dp[si][ei - 1]);
                }
            }
        }

        return dp[SI][EI];

    }

    // back_Engineering
    public String lpss_backEng(String s, int si, int ei, int[][] dp) {
        if (si >= ei)
            return (si == ei) ? s.charAt(si) + "" : "";
        char ch1 = s.charAt(si), ch2 = s.charAt(ei);
        String ans = "";
        if (ch1 == ch2) {
            ans = ch1 + lpss_backEng(s, si + 1, ei - 1, dp) + ch2;
        } else {
            ans = (dp[si + 1][ei] > dp[si][ei - 1]) ? lpss_backEng(s, si + 1, ei, dp) : lpss_backEng(s, si, ei - 1, dp);
        }
        return ans;
    }

    public String lpss(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        lpss_tabu(s, 0, n - 1, dp); // dp -> tabulation

        return lpss_backEng(s, 0, n - 1, dp);
    }

    public int longestPalindromeSubseq(String s) {
        String str = lpss(s);
        System.out.println(str);
        return -1;
    }

    // 1143. Longest Common Subsequence
    public int lcss_memo(String s1,int si1, String s2, int si2, int[][] dp) {
        if(si1 == s1.length() || si2 == s2.length()) 
            return dp[si1][si2] = 0;
        
        if(dp[si1][si2] != -1) 
            return dp[si1][si2];
        
        int len = 0;
        char ch1 = s1.charAt(si1), ch2 = s2.charAt(si2);
        if(ch1 == ch2) {
            len = 1 + lcss_memo(s1, si1 + 1, s2, si2 + 1, dp);
        } else {
            len = Math.max(lcss_memo(s1, si1 + 1, s2, si2, dp), lcss_memo(s1, si1, s2, si2 + 1, dp));
        }
        
        return dp[si1][si2] = len;
    }
    
    public int lcss_tabu(String s1,int SI1, String s2, int SI2, int[][] dp) { 
        for(int si1 = s1.length(); si1 >= SI1; si1--) {
            for(int si2 = s2.length(); si2 >= SI2; si2--) {
                if(si1 == s1.length() || si2 == s2.length()) {
                    dp[si1][si2] = 0;
                    continue;
                }

                int len = 0;
                char ch1 = s1.charAt(si1), ch2 = s2.charAt(si2);
                if(ch1 == ch2) {
                    len = 1 + dp[si1 + 1][si2 + 1]; 
                } else {
                    len = Math.max(dp[si1 + 1][si2], dp[si1][si2 + 1]);
                }

                dp[si1][si2] = len;
            }
        }
        return dp[0][0];
    }
    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        // for(int[] d : dp)
        //     Arrays.fill(d, -1);
        
        return lcss_tabu(s1, 0, s2, 0, dp);
    }

    // 5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        
        int length = 0, startIdx = 0;
        for(int gap = 0; gap < n; gap++) {
            for(int si = 0, ei = gap; ei < n ; si++, ei++) {
                if(gap == 0) 
                    dp[si][ei] = true;
                else if(gap == 1 && s.charAt(si) == s.charAt(ei)) 
                    dp[si][ei] = true;
                else dp[si][ei] = (s.charAt(si) == s.charAt(ei) && dp[si + 1][ei - 1]);
                
                if(dp[si][ei] && ei - si + 1 > length) {
                    length = ei - si + 1;
                    startIdx = si;
                }
            }
        }
        
        return s.substring(startIdx, length + startIdx);
    }

    // Longest Common Substring
    public String lcSubstring(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        int len = 0;
        int ei = -1;
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                char ch1 = s1.charAt(i - 1), ch2 = s2.charAt(j - 1);
                if(ch1 == ch2) 
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else 
                    dp[i][j] = 0;

                if(dp[i][j] > len) {
                    len = dp[i][j];
                    ei = j - 1;
                }

            }
        }

        int si = ei - len + 1;
        String lcs_string = s2.substring(si, ei + 1);
        return lcs_string;
    }

    // 1458. Max Dot Product of Two Subsequences
     public int maximum(int... arr) {
        int max = arr[0];
        for(int ele : arr) 
            max = Math.max(max, ele);
        
        return max;
    }
    public int maxDotProduct_memo(int[] a1, int[] a2, int i, int j, int[][] dp) {
        if(i == a1.length || j == a2.length) 
            return dp[i][j] = -(int)1e8;
        
        if(dp[i][j] != -(int)1e9) 
            return dp[i][j];
        
        int acceptTwoNum = a1[i] * a2[j] + maxDotProduct_memo(a1, a2, i + 1, j  + 1, dp); // accept both number of two array and call for rest of the element
        int acceptAnyOfTwoNum = Math.max(maxDotProduct_memo(a1, a2, i + 1, j, dp), maxDotProduct_memo(a1, a2, i, j + 1, dp)); // accept number from either 1st array or 2nd array
        
        return dp[i][j] = maximum(acceptTwoNum, acceptAnyOfTwoNum, a1[i] * a2[j]);
    }
    
    public int maxDotProduct_tabu(int[] a1, int[] a2, int I, int J, int[][] dp) {
        for(int i = a1.length; i >= I; i-- ) {
            for(int j = a2.length; j >= J; j--) {
                if(i == a1.length || j == a2.length) {
                    dp[i][j] = -(int)1e8;
                    continue;
                }

                int acceptTwoNum = a1[i] * a2[j] + dp[i + 1][j + 1]; // accept both number of two array and call for rest of the element
                int acceptAnyOfTwoNum = Math.max(dp[i + 1][j], dp[i][j + 1]); // accept number from either 1st array or 2nd array

                dp[i][j] = maximum(acceptTwoNum, acceptAnyOfTwoNum, a1[i] * a2[j]);
            }
        }
        
        return dp[I][J];
        
    } 
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        
        return maxDotProduct_tabu(nums1, nums2, 0, 0, dp);
    }

    // 72. Edit Distance
    public int minDistance_memo(String word1, String word2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0) {
            return dp[n][m] = (n == 0) ? m :n;
        }
        
        if(dp[n][m] != -1) 
            return dp[n][m];
        
        if(word1.charAt(n - 1) == word2.charAt(m - 1)) 
            return dp[n][m] = minDistance_memo(word1, word2, n - 1, m - 1, dp);
        

        int insert = minDistance_memo(word1, word2, n, m - 1, dp);
        int delete = minDistance_memo(word1, word2, n - 1, m, dp);
        int replace = minDistance_memo(word1, word2, n - 1, m - 1, dp);

        return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
        
    }
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d : dp) 
            Arrays.fill(d, -1);
        
        return minDistance_memo(word1, word2, n, m, dp);
    }
}