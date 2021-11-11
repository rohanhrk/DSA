import java.util.Arrays;
import java.util.HashMap;

public class l002_stringSet {
    // =============================================================================================================================
    // Question_9 : 516. Longest Palindromic Subsequence_
    // https://leetcode.com/problems/longest-palindromic-subsequence/
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

    // =============================================================================================================================
    // Question_10 : 1143. Longest Common Subsequence
    // https://leetcode.com/problems/longest-common-subsequence/
    public int lcss_memo(String s1, int si1, String s2, int si2, int[][] dp) {
        if (si1 == s1.length() || si2 == s2.length())
            return dp[si1][si2] = 0;

        if (dp[si1][si2] != -1)
            return dp[si1][si2];

        int len = 0;
        char ch1 = s1.charAt(si1), ch2 = s2.charAt(si2);
        if (ch1 == ch2) {
            len = 1 + lcss_memo(s1, si1 + 1, s2, si2 + 1, dp); // both ch1 and ch2 excluded
        } else {
            // any of the character is excluded
            len = Math.max(lcss_memo(s1, si1 + 1, s2, si2, dp), lcss_memo(s1, si1, s2, si2 + 1, dp));
        }

        return dp[si1][si2] = len;
    }

    public int lcss_tabu(String s1, int SI1, String s2, int SI2, int[][] dp) {
        for (int si1 = s1.length(); si1 >= SI1; si1--) {
            for (int si2 = s2.length(); si2 >= SI2; si2--) {
                if (si1 == s1.length() || si2 == s2.length()) {
                    dp[si1][si2] = 0;
                    continue;
                }

                int len = 0;
                char ch1 = s1.charAt(si1), ch2 = s2.charAt(si2);
                if (ch1 == ch2) {
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
        // Arrays.fill(d, -1);

        return lcss_tabu(s1, 0, s2, 0, dp);
    }

    // =============================================================================================================================
    // Question_11 : 5. Longest Palindromic Substring
    // https://leetcode.com/problems/longest-palindromic-substring/
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int length = 0, startIdx = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (gap == 0)
                    dp[si][ei] = true;
                else if (gap == 1 && s.charAt(si) == s.charAt(ei))
                    dp[si][ei] = true;
                else
                    dp[si][ei] = (s.charAt(si) == s.charAt(ei) && dp[si + 1][ei - 1]);

                if (dp[si][ei] && ei - si + 1 > length) {
                    length = ei - si + 1;
                    startIdx = si;
                }
            }
        }

        return s.substring(startIdx, length + startIdx);
    }

    // =============================================================================================================================
    // Question_12 : Longest Common Substring_
    // https://practice.geeksforgeeks.org/problems/longest-common-substring1452/1#
    public String lcSubstring(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        int len = 0;
        int ei = -1;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                char ch1 = s1.charAt(i - 1), ch2 = s2.charAt(j - 1);
                if (ch1 == ch2)
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = 0;

                if (dp[i][j] > len) {
                    len = dp[i][j];
                    ei = j - 1;
                }

            }
        }

        int si = ei - len + 1;
        String lcs_string = s2.substring(si, ei + 1);
        return lcs_string;
    }

    // =============================================================================================================================
    // Question_13 : 1458. Max Dot Product of Two Subsequences
    // https://leetcode.com/problems/max-dot-product-of-two-subsequences/
    private int maximum(int... arr) {
        int max = arr[0];
        for (int ele : arr)
            max = Math.max(max, ele);

        return max;
    }

    public int maxDotProduct_memo(int[] a1, int[] a2, int i, int j, int[][] dp) {
        if (i == a1.length || j == a2.length)
            return dp[i][j] = -(int) 1e8;

        if (dp[i][j] != -(int) 1e9)
            return dp[i][j];

        int acceptTwoNum = a1[i] * a2[j] + maxDotProduct_memo(a1, a2, i + 1, j + 1, dp); // accept both number of two
                                                                                         // array and call for rest of
                                                                                         // the element
        int acceptAnyOfTwoNum = Math.max(maxDotProduct_memo(a1, a2, i + 1, j, dp),
                maxDotProduct_memo(a1, a2, i, j + 1, dp)); // accept number from either 1st array or 2nd array

        return dp[i][j] = maximum(acceptTwoNum, acceptAnyOfTwoNum, a1[i] * a2[j]);
    }

    public int maxDotProduct_tabu(int[] a1, int[] a2, int I, int J, int[][] dp) {
        for (int i = a1.length; i >= I; i--) {
            for (int j = a2.length; j >= J; j--) {
                if (i == a1.length || j == a2.length) {
                    dp[i][j] = -(int) 1e8;
                    continue;
                }

                int acceptTwoNum = a1[i] * a2[j] + dp[i + 1][j + 1]; // accept both number of two array and call for
                                                                     // rest of the element
                int acceptAnyOfTwoNum = Math.max(dp[i + 1][j], dp[i][j + 1]); // accept number from either 1st array or
                                                                              // 2nd array

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

    // =============================================================================================================================
    // Question_14 : 72. Edit Distance
    // https://leetcode.com/problems/edit-distance/
    public int minDistance_memo(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0) ? m : n; // Insert : delete
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = minDistance_memo(word1, word2, n - 1, m - 1, dp);

        int insert = minDistance_memo(word1, word2, n, m - 1, dp);
        int delete = minDistance_memo(word1, word2, n - 1, m, dp);
        int replace = minDistance_memo(word1, word2, n - 1, m - 1, dp);

        return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;

    }

    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return minDistance_memo(word1, word2, n, m, dp);
    }

    // =============================================================================================================================
    // Question_15 : 583. Delete Operation for Two Strings
    // https://leetcode.com/problems/delete-operation-for-two-strings/
    public int minDistance_memo_(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0) ? m : n;
        }

        char ch1 = word1.charAt(n - 1), ch2 = word2.charAt(m - 1);
        if (ch1 == ch2)
            return dp[n][m] = minDistance_memo(word1, word2, n - 1, m - 1, dp);
        else
            return dp[n][m] = 1 + Math.min(minDistance_memo(word1, word2, n - 1, m, dp),
                    minDistance_memo(word1, word2, n, m - 1, dp));
    }

    public int minDistance_tabu(String word1, String word2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = (n == 0) ? m : n;
                    continue;
                }

                char ch1 = word1.charAt(n - 1), ch2 = word2.charAt(m - 1);
                if (ch1 == ch2)
                    dp[n][m] = dp[n - 1][m - 1]; // minDistance_memo(word1, word2, n - 1, m - 1, dp);
                else
                    dp[n][m] = 1 + Math.min(dp[n - 1][m], dp[n][m - 1]); // Math.min(minDistance_memo(word1, word2, n -
                                                                         // 1, m, dp), minDistance_memo(word1, word2, n,
                                                                         // m - 1, dp));
            }
        }

        return dp[N][M];

    }

    public int minDistance_(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        return minDistance_tabu(word1, word2, n, m, dp);
    }

    // =============================================================================================================================
    // Question_16 : minimum-number-of-deletions-and-insertions
    // https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1
    public int minOperations(String str1, String str2, int n, int m, int[][] dp) {
        // Your code goes here
        if (n == 0 || m == 0)
            return dp[n][m] = (n == 0) ? m : n;

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = str1.charAt(n - 1), ch2 = str2.charAt(m - 1);
        if (ch1 == ch2)
            return dp[n][m] = minOperations(str1, str2, n - 1, m - 1, dp);

        else
            return dp[n][m] = 1
                    + Math.min(minOperations(str1, str2, n - 1, m, dp), minOperations(str1, str2, n, m - 1, dp));

    }

    public int minOperations(String str1, String str2) {
        // Your code goes here
        int n = str1.length(), m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return minOperations(str1, str2, n, m, dp);
    }

    // =============================================================================================================================
    // Question_17 : 115. Distinct Subsequences
    // https://leetcode.com/problems/distinct-subsequences/
    public int numDistinct_memo(String s, String t, int n, int m, int[][] dp) {
        // faith --> string 't' ko as a subsequence, string 's' me kitne tariko se dhund
        // sakta hu
        if (n == 0 || m == 0 || n < m) {
            return dp[n][m] = (m == 0) ? 1 : 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1), ch2 = t.charAt(m - 1);
        int a = numDistinct_memo(s, t, n - 1, m - 1, dp); // both character are excluded
        int b = numDistinct_memo(s, t, n - 1, m, dp); // excluded 1st character from string s

        if (ch1 == ch2)
            return dp[n][m] = a + b;

        else
            return dp[n][m] = b;
    }

    public int numDistinct_dp(String s, String t, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0 || n < m) {
                    dp[n][m] = (m == 0) ? 1 : 0;
                    continue;
                }

                char ch1 = s.charAt(n - 1), ch2 = t.charAt(m - 1);
                if (ch1 == ch2)
                    dp[n][m] = dp[n - 1][m - 1] + dp[n - 1][m]; // numDistinct(s, t, n - 1, m - 1, dp) + numDistinct(s,
                                                                // t, n - 1, m, dp);

                else
                    dp[n][m] = dp[n - 1][m];
            }
        }

        return dp[N][M];
    }

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return numDistinct_memo(s, t, n, m, dp);
    }

    // =============================================================================================================================
    // Question_18 : 940. Distinct Subsequences II
    // https://leetcode.com/problems/distinct-subsequences-ii/
    public int distinctSubseqII(String s) {
        int n = s.length();
        long[] dp = new long[n + 1];
        long mod = (long) 1e9 + 7;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                dp[i] = 1;
                continue;
            }

            dp[i] = (2 * dp[i - 1] % mod) % mod;
            char ch = s.charAt(i - 1);
            if (map.containsKey(ch)) {
                int lo = map.get(ch); // index of last occurance
                dp[i] = ((dp[i] % mod) - (dp[lo - 1] % mod)) % mod;
            }

            map.put(ch, i);
        }

        if (dp[n] < 0) {
            dp[n] += mod;
        }
        return (int) ((dp[n] % mod - 1) % mod);
    }

    // =============================================================================================================================
    // Question_19 : count-subsequences-of-type-a^i-b^j-c^k
    // https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1
    public int countSubseq_ai_bj_ck(String s) {
        int emptyCount = 1, n = s.length();
        long aCount = 0, bCount = 0, cCount = 0, mod = (int) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                aCount = aCount + (aCount + emptyCount) % mod;
            } else if (ch == 'b') {
                bCount = bCount + (bCount + aCount) % mod;
            } else if (ch == 'c') {
                cCount = cCount + (cCount + bCount) % mod;
            }
        }

        return (int) (cCount % mod);
    }

    // =============================================================================================================================
    // Que_20 --->
    // follow up quesn
    // i) slove quens for k
    // ii) b^p a^i c^j d^k

    // generic
    public static int countSubseq_k(String s, int k) {
        int[] alphabet = new int[k];
        int n = s.length(), emptyCount = 1, mod = (int) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            int idx = ch - 'a';
            if (ch == 'a') {
                alphabet[idx] = alphabet[idx] + (alphabet[idx] + emptyCount) % mod;
            } else {
                alphabet[idx] = alphabet[idx] + (alphabet[idx] + alphabet[idx - 1]) % mod;
            }

        }

        return alphabet[k - 1];
    }

    public static int countSubseq_bp_ai_cj_dk(String s) {
        int n = s.length();
        int emptyCount = 1, bCount = 0, aCount = 0, cCount = 0, dCount = 0;
        int mod = (int) 1e9 + 7;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'b') {
                bCount = bCount + (bCount + emptyCount) % mod;
            } else if (ch == 'a') {
                aCount = aCount + (aCount + bCount) % mod;
            } else if (ch == 'c') {
                cCount = cCount + (cCount + aCount) % mod;
            } else if (ch == 'd') {
                dCount = dCount + (dCount + cCount) % mod;
            }
        }

        return dCount;
    }

    // =============================================================================================================================
    // Question_21 : count-palindromic-subsequences
    // https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
    long countPS_memo(String str, int i, int j, long[][] dp) {
        // Your code here
        if (i >= j) {
            return dp[i][j] = (i == j) ? 1 : 0;
        }

        if (dp[i][j] != -1)
            return dp[i][j];

        long common = countPS_memo(str, i + 1, j - 1, dp);
        long exclude_first = countPS_memo(str, i + 1, j, dp);
        long exclude_last = countPS_memo(str, i, j - 1, dp);

        char first_ch = str.charAt(i); // first character
        char last_ch = str.charAt(j); // last character
        long mod = (long) 1e9 + 7;

        // If first and last characters are same, then we
        // consider it as palindrome subsequence and check
        // for the rest subsequence (i+1, j), (i, j-1)
        if (first_ch == last_ch) {
            dp[i][j] = (exclude_first + exclude_last + 1) % mod;
        } else {
            // check for rest sub-sequence and remove common
            // palindromic subsequences as they are counted
            // twice when we do countPS(i+1, j) + countPS(i,j-1)
            dp[i][j] = (exclude_first + exclude_last - common + mod) % mod;
        }

        return dp[i][j];
    }

    long countPS(String str) {
        // Your code here
        int n = str.length();
        long[][] dp = new long[n][n];
        for (long[] d : dp)
            Arrays.fill(d, -1);
        return countPS_memo(str, 0, n - 1, dp);
    }

    // =============================================================================================================================
    // Question_22 : 44. Wildcard Matching
    // https://leetcode.com/problems/wildcard-matching/
    public String remove_multiStar(String str) {
        if (str.length() == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while (i < str.length()) {
            while (i < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
                i++;

            if (i < str.length())
                sb.append(str.charAt(i));
            i++;

        }

        return sb.toString();
    }

    public int isMatch_memo(String s, String p, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1;
            else if (m == 1 && p.charAt(m - 1) == '*') {
                return dp[n][m] = 1;
            } else {
                return dp[n][m] = 0;
            }
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1), ch2 = p.charAt(m - 1);
        if (ch1 == ch2 || ch2 == '?') {
            return dp[n][m] = isMatch_memo(s, p, n - 1, m - 1, dp);
        } else if (ch2 == '*') {
            boolean res = false;
            res = res || isMatch_memo(s, p, n - 1, m, dp) == 1; // *(star) mapped with current character
            res = res || isMatch_memo(s, p, n, m - 1, dp) == 1; // *(star) mapped with empty string

            return dp[n][m] = res ? 1 : 0;
        } else {
            return dp[n][m] = 0; // false
        }
    }

    public boolean isMatch(String s, String p) {
        p = remove_multiStar(p);
        int n = s.length(), m = p.length();

        int[][] dp = new int[n + 1][m + 1]; // 0 -> false , 1 -> true;
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return isMatch_memo(s, p, n, m, dp) == 1 ? true : false;
    }

    // =============================================================================================================================
    // Question_23 : 132. Palindrome Partitioning II
    // https://leetcode.com/problems/palindrome-partitioning-ii/
    public void lpsString_dp(String s, boolean[][] isPlindromeDp) {
        for (int gap = 0; gap < s.length(); gap++) {
            for (int i = 0, j = gap; j < s.length(); i++, j++) {
                if (gap == 0)
                    isPlindromeDp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    isPlindromeDp[i][j] = true;
                else
                    isPlindromeDp[i][j] = s.charAt(i) == s.charAt(j) && isPlindromeDp[i + 1][j - 1];

            }
        }
    }

    public int minCut_memo(String s, int si, boolean[][] isPlindromeDp, int[] dp) {
        if (isPlindromeDp[si][s.length() - 1])
            return dp[si] = 0;

        if (dp[si] != -1)
            return dp[si];

        int min_cut = (int) 1e9;
        for (int cut = si; cut < s.length(); cut++) {
            if (isPlindromeDp[si][cut])
                min_cut = Math.min(min_cut, minCut_memo(s, cut + 1, isPlindromeDp, dp) + 1);

        }

        return dp[si] = min_cut;
    }

    public int minCut(String s) {
        int n = s.length();

        boolean[][] isPlindromeDp = new boolean[n][n];
        lpsString_dp(s, isPlindromeDp);

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        return minCut_memo(s, 0, isPlindromeDp, dp);
    }

    // =============================================================================================================================
    // Question_24 : 10. Regular Expression Matching
    // https://leetcode.com/problems/regular-expression-matching/
    public String removeMultiStars(String str) {
        if (str.length() == 0)
            return "";

        int n = str.length();

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while (i < n) {
            while (i < n && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
                i++;

            if (i < n)
                sb.append(str.charAt(i));

            i++;
        }

        return sb.toString();
    }

    public static boolean isCharStarSeq(String str, int len) {
        int i = 0;
        while (i < len) {
            while (i < len && ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || str.charAt(i) == '.')
                    && str.charAt(i + 1) == '*')
                i += 2;

            return i >= len ? true : false;
        }

        return true;
    }

    public int isMatch_memo_(String s, String p, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0) {
                return dp[n][m] = 1;
            } else if (n == 0 && m >= 2) {
                if (isCharStarSeq(p, m))
                    return dp[n][m] = 1;
                else
                    return dp[n][m] = 0;
            } else {
                return dp[n][m] = 0;
            }
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1), ch2 = p.charAt(m - 1);
        if (ch1 == ch2 || ch2 == '.')
            return dp[n][m] = isMatch_memo_(s, p, n - 1, m - 1, dp);
        else if (ch2 == '*') {
            boolean res = false;

            if (m > 1 && (s.charAt(n - 1) == p.charAt(m - 2) || p.charAt(m - 2) == '.'))
                res = res || isMatch_memo_(s, p, n - 1, m, dp) == 1;
            res = res || isMatch_memo_(s, p, n, m - 2, dp) == 1;

            return dp[n][m] = res ? 1 : 0;
        } else {
            return dp[n][m] = 0;
        }
    }

    public boolean isMatch_(String s, String p) {
        p = removeMultiStars(p);
        int n = s.length(), m = p.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return isMatch_memo(s, p, n, m, dp) == 1;
    }

}