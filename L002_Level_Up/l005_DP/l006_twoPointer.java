import java.util.Arrays;

public class twoPointer {

	public static void print_oned(int[] arr) {
		for (int ele : arr)
			System.out.print(ele + " ");
	}

	public static void print_twod(int[][] arr) {
		for (int[] ar : arr) {
			print_oned(ar);
			System.out.println();
		}
	}

	// faith --> F(n) = F(n-1) + F(n-2)
	public static int fibo_memo(int n, int[] dp) {
		// base case
		if (n <= 1)
			return dp[n] = n;

		if (dp[n] != 0)
			return dp[n];

		int ans = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
		return dp[n] = ans;
	}

	public static int fibo_tabu(int N, int[] dp) {
		for (int n = 0; n <= N; n++) {
			if (n <= 1) {
				dp[n] = n;
				continue;
			}

			int ans = dp[n - 1] + dp[n - 2];
			dp[n] = ans;
		}

		return dp[N];
	}

	public static int fibo_opti(int n) {
		int a = 0, b = 1;
		for (int i = 0; i <= n; i++) {
			System.out.print(a + " ");
			int c = a + b;
			a = b;
			b = c;
		}

		return a;
	}

	// Maze path
	public static int maze_memo(int sr, int sc, int dr, int dc, int[][] dir, int[][] dp) {
		// base case
		if (sr == dr && sc == dc) {
			return dp[sr][sc] = 1;
		}

		if (dp[sr][sc] != 0)
			return dp[sr][sc];

		int count = 0;
		for (int[] d : dir) {
			int r = sr + d[0];
			int c = sc + d[1];

			if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
				count += maze_memo(r, c, dr, dc, dir, dp);
			}
		}

		return dp[sr][sc] = count;
	}

	public static int maze_tabu(int SR, int SC, int dr, int dc, int[][] dir, int[][] dp) {
		// base case

		for (int sr = dr; sr >= SR; sr--) {
			for (int sc = dc; sc >= SC; sc--) {
				if (sr == dr && sc == dc) {
					dp[sr][sc] = 1;
					continue;
				}

				int count = 0;
				for (int[] d : dir) {
					int r = sr + d[0];
					int c = sc + d[1];

					if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
						count += dp[r][c];
					}
				}

				dp[sr][sc] = count;
			}
		}

		return dp[SR][SC];
	}

	// gold mine problems
	static int goldMine_memo(int[][] mat, int sr, int sc, int[][] dir, int[][] dp) {
		if (sc == mat[0].length - 1) {
			return dp[sr][sc] = mat[sr][sc];
		}

		if (dp[sr][sc] != -1)
			return dp[sr][sc];

		int gold = 0;
		for (int[] d : dir) {
			int r = sr + d[0];
			int c = sc + d[1];
			if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length) {
				gold = Math.max(gold, goldMine_memo(mat, r, c, dir, dp) + mat[sr][sc]);
			}
		}

		return dp[sr][sc] = gold;
	}

	static int goldMine_tabu(int[][] mat, int SR, int SC, int[][] dir, int[][] dp) {
		for (int sc = mat[0].length - 1; sc >= SC; sc--) {
			for (int sr = mat.length - 1; sr >= SR; sr--) {
				if (sc == mat[0].length - 1) {
					dp[sr][sc] = mat[sr][sc];
					continue;
				}

				int gold = 0;
				for (int[] d : dir) {
					int r = sr + d[0];
					int c = sc + d[1];
					if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length) {
						gold = Math.max(gold, dp[r][c] + mat[sr][sc]);
					}
				}

				return dp[sr][sc] = gold;
			}
		}

		int max = 0;
		for (int sr = 0; sr < mat.length; sr++) {
			max = Math.max(max, dp[sr][0]);
		}

		return max;
	}

	static int maxGold(int n, int m, int M[][]) {
		// code here
		int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
		int[][] dp = new int[n][m];
		for (int[] d : dp)
			Arrays.fill(d, -1);

		// int max = 0;
		// for(int sr = 0; sr < n; sr++) {
		// max = Math.max(max, goldMine_memo(M, sr, 0, dir, dp));
		// }

		// return max;
		return goldMine_tabu(M, 0, 0, dir, dp);
	}

	// Dice Problems
	public static int dice_memo(int sp, int ep, int[] dp) {
		if (sp == ep) {
			return dp[sp] = 1;
		}

		if (dp[sp] != 0)
			return dp[sp];

		int count = 0;
		for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
			count += dice_memo(sp + dice, ep, dp);
		}

		return dp[sp] = count;
	}

	public static int dice_tabu(int SP, int ep, int[] dp) {
		for (int sp = ep; sp >= SP; sp--) {
			if (sp == ep) {
				dp[sp] = 1;
				continue;
			}

			int count = 0;
			for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
				count += dp[sp + dice];
			}

			dp[sp] = count;
		}
		return dp[SP];
	}

	public static void dice_set() {
		int sp = 0, ep = 10;
		int[] dp = new int[ep + 1];
		System.out.println(dice_tabu(sp, ep, dp));
		print_oned(dp);
	}

	//
	public int numDecodings_memo(String s, int idx, int[] dp) {
		if (idx == s.length()) {
			return dp[idx] = 1;
		}

		if (s.charAt(idx) == '0')
			return dp[idx] = 0;

		if (dp[idx] != -1)
			return dp[idx];

		// single digit
		int count = 0;
		count += numDecodings_memo(s, idx + 1, dp);

		// double digit
		if (idx < s.length() - 1) {
			int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
			if (num <= 26) {
				count += numDecodings_memo(s, idx + 2, dp);
			}
		}

		return dp[idx] = count;
	}

	// manipulation with length
	public int numDecodings_memo_02(String s, int len, int[] dp) {
		if (len == 0) {
			return dp[len] = 1;
		}

		if (dp[len - 1] != -1)
			return dp[len];

		int count = 0;
		if (s.charAt(len - 1) != '0')
			count += numDecodings_memo_02(s, len - 1, dp);

		if (len > 1) {
			int num = (s.charAt(len - 2) - '0') * 10 + (s.charAt(len - 1) - '0');
			if (num <= 26 && num >= 10) {
				count += numDecodings_memo_02(s, len - 2, dp);
			}
		}

		return dp[len] = count;
	}

	public int numDecodings_tabu(String s, int IDX, int[] dp) {
		for (int idx = s.length(); idx >= IDX; idx--) {
			if (idx == s.length()) {
				dp[idx] = 1;
				continue;
			}

			if (s.charAt(idx) == '0') {
				dp[idx] = 0;
				continue;

			}

			// single digit
			int count = 0;
			count += dp[idx + 1];

			// double digit
			if (idx < s.length() - 1) {
				int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
				if (num <= 26) {
					count += dp[idx + 2]; // numDecodings_memo(s, idx + 2, dp);
				}
			}

			dp[idx] = count;
		}
		return dp[IDX];
	}

	public int numDecodings(String s) {
		int len = s.length();
		int[] dp = new int[len + 1];
		Arrays.fill(dp, -1);
		// return numDecodings_tabu(s, 0, dp);
		return numDecodings_memo_02(s, len, dp);
	}

	// =====================================================================
	// 639. Decode Ways II
	public long numDecodings_memo(String s, int idx, long[] dp) {
		if (idx == s.length()) {
			return dp[idx] = 1;
		}

		if (dp[idx] != -1)
			return dp[idx];

		if (s.charAt(idx) == '0')
			return dp[idx] = 0;

		// single digit
		long count = 0;
		long mod = (long) 1e9 + 7;
		if (s.charAt(idx) == '*')
			count = (count + 9 * numDecodings_memo(s, idx + 1, dp)) % mod;
		else
			count += numDecodings_memo(s, idx + 1, dp);

		// double digit
		if (idx < s.length() - 1) {
			if (s.charAt(idx) == '*' && s.charAt(idx + 1) == '*') { // * *
				count = (count + 15 * numDecodings_memo(s, idx + 2, dp)) % mod;
			} else if (s.charAt(idx) == '*' && s.charAt(idx + 1) != '*') { // * c
				if (s.charAt(idx + 1) >= '0' && s.charAt(idx + 1) <= '6')
					count = (count + 2 * numDecodings_memo(s, idx + 2, dp)) % mod;
				else if (s.charAt(idx + 1) > '6')
					count = (count + 1 * numDecodings_memo(s, idx + 2, dp)) % mod;
			} else if (s.charAt(idx) != '*' && s.charAt(idx + 1) == '*') { // c *
				if (s.charAt(idx) == '1')
					count = (count + 9 * numDecodings_memo(s, idx + 2, dp)) % mod;
				else if (s.charAt(idx) == '2')
					count = (count + 6 * numDecodings_memo(s, idx + 2, dp)) % mod;
			} else { // c c
				if ((s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0') <= 26) {
					count = (count + numDecodings_memo(s, idx + 2, dp)) % mod;
				}
			}
		}

		return dp[idx] = count;
	}

	public int numDecodings_star(String s) {
		int N = s.length();
		long[] dp = new long[N + 1];
		Arrays.fill(dp, -1);
		return (int) numDecodings_memo(s, 0, dp);
	}

	// https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1
	public long countFriendsPairings_memo(int n, long[] dp) {
		// code here
		if (n <= 1)
			return dp[n] = 1;

		if (dp[n] != 0)
			return dp[n];

		long mod = (long) 1e9 + 7;
		long single = countFriendsPairings_memo(n - 1, dp);
		long pairup = (n - 1) * countFriendsPairings_memo(n - 2, dp);

		return dp[n] = (single + pairup) % mod;
	}

	public long countFriendsPairings_tabu(int N, long[] dp) {
		// code here
		for (int n = 0; n <= N; n++) {
			if (n <= 1) {
				dp[n] = 1;
				continue;
			}

			long mod = (long) 1e9 + 7;
			long single = dp[n - 1]; // countFriendsPairings_memo(n-1, dp);
			long pairup = (n - 1) * dp[n - 2];// countFriendsPairings_memo(n-2, dp) ;

			dp[n] = (single + pairup) % mod;
		}

		return dp[N];
	}

	public long countFriendsPairings_opti(int n) {
		long mod = (long) 1e9 + 7;
		long a = 1, b = 1;
		for (long i = 2; i <= n; i++) {
			long sum = (b + a * (i - 1)) % mod;
			a = b;
			b = sum;
		}

		return b;
	}

	// https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-n-in-k-groups-incrementally/
	public static int divideInKGroups_memo(int n, int k, int[][] dp) {
		if(k == 1 || n == k) {
			return dp[n][k] = 1;
		}

		if(dp[n][k] != -1) 
			return dp[n][k];

		int selfSet = divideInKGroups_memo(n-1, k-1, dp);
		int partOfAnotherSet = divideInKGroups_memo(n-1, k, dp) * k;

		return dp[n][k] = selfSet + partOfAnotherSet;
	}

	public static int divideInKGroups() {
		int n = 5, k = 3;
		int[][] dp = new int[n + 1][k + 1];
		return divideInKGroups_memo(n, k, dp);
	}
	public long countFriendsPairings(int n) {
		// code her
		return countFriendsPairings_opti(n);
	}

	public static void main(String[] args) {
		dice_set();
	}

}