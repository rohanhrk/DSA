import java.util.*;

public class l001Basic {
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
    // Question_1 : Fibbonacci series

    /* memoization */
    public static int fibo_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;

        if (dp[n] != 0)
            return dp[n];
        int ans = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
        return dp[n] = ans;
    }

    /* tabulation */
    public static int fibo_DP(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            int ans = dp[n - 1] + dp[n - 2]; // fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
            dp[n] = ans;
        }

        return dp[N];
    }

    /* further optization */
    public static int fibo_opt(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    public static void fibo() {
        int n = 6;
        int[] dp = new int[n + 1];
        fibo_DP(n, dp);
        System.out.println(fibo_opt(n));
        print(dp);
    }

    // ==========================================================================================================================================================
    // Question_2 : 70. Climbing Stairs
    // https://leetcode.com/problems/climbing-stairs/

    /* memoization */
    public static int climb_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = 1;

        if (dp[n] != 0)
            return dp[n];
        int steps = climb_memo(n - 1, dp) + climb_memo(n - 2, dp);
        return dp[n] = steps;
    }

    /* tabulation */
    public static int climb_DP(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            int steps = climb_memo(n - 1, dp) + climb_memo(n - 2, dp);
            dp[n] = steps;
        }
        return dp[N];
    }

    /* optimization */
    public static int climb_opti(int n) {
        int a = 1, b = 1;
        for (int i = 0; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return climb_memo(n, dp);
        // return climb_DP(n, dp);
        // return climb_opti(n);
    }

    // ==========================================================================================================================================================
    // Question_3 : 746. Min Cost Climbing Stairs
    // https://leetcode.com/problems/min-cost-climbing-stairs/submissions/
    public int minCostClimbingStairs_memo(int[] cost, int n, int[] dp) {
        if (n <= 1)
            return dp[n] = cost[n];

        if (dp[n] != 0)
            return dp[n];

        int minCostFromOneStep = minCostClimbingStairs_memo(cost, n - 1, dp);
        int minCostFromTwoStep = minCostClimbingStairs_memo(cost, n - 2, dp);
        int minCost = Math.min(minCostFromOneStep, minCostFromTwoStep) + (n == cost.length ? 0 : cost[n]);

        return dp[n] = minCost;
    }

    public int minCostClimbingStairs_DP(int[] cost, int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            int minCost = Math.min(dp[n - 1], dp[n - 2]) + (n == cost.length ? 0 : cost[n]);

            dp[n] = minCost;
        }
        return dp[N];
    }

    public int minCostClimbingStairs_Opti(int[] cost, int N) {

        int a = cost[0], b = cost[1];
        for (int i = 2; i <= N; i++) {
            int minVal = Math.min(a, b) + (i != cost.length ? cost[i] : 0);
            a = b;
            b = minVal;
        }
        return b;
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        // return minCostClimbingStairs_memo(cost, n, dp);
        // return minCostClimbingStairs_DP(cost, n, dp);
        return minCostClimbingStairs_Opti(cost, n);

    }

    // ==========================================================================================================================================================
    // Question_4 : Dice Throw
    /* Memoization */
    public static int diceThrow_memo(int n, int[] dp) {
        if (n == 0)
            return dp[n] = 1;

        if (dp[n] != 0)
            return dp[n];
        int count = 0;
        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            count += diceThrow_memo(n - dice, dp);
        }

        return dp[n] = count;

    }

    /* Tabulation */
    public static int diceThrow_dp(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
                count += dp[n - dice]; // diceThrow_memo(n - i, dp);
            }

            dp[n] = count;
        }
        return dp[N];
    }

    public static int diceThrow_opti(int n) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.addLast(1);
        ll.addLast(1);

        for (int i = 2; i <= n; i++) {
            int data = ll.getLast() * 2;
            if (i <= 6)
                ll.addLast(data);
            else {
                int newData = data - ll.removeFirst();
                ll.addLast(newData);
            }
        }

        return ll.getLast();
    }

    public static void diceThrow() {
        int n = 10;
        // int[] dp = new int[n + 1];
        // diceThrow_memo(n, dp);
        // diceThrow_dp(n, dp);
        System.out.println(diceThrow_opti(n));
        // print(dp);
    }

    // ==========================================================================================================================================================
    // Question_5 : Maze path
    /* move => horizontal, diagonal, vertical */
    public static int mazePath_HDV_memo(int sr, int sc, int dr, int dc, int[][] dir, int[][] dp) {
        if (sr == dr && sc == dc) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                count += mazePath_HDV_memo(r, c, dr, dc, dir, dp);
            }
        }

        return dp[sr][sc] = count;
    }

    public static int mazePath_HDV_dp(int SR, int SC, int dr, int dc, int[][] dir, int[][] dp) {
        for (int sr = dr; sr >= 0; sr--) {
            for (int sc = dc; sc >= 0; sc--) {
                if (sr == dr && sc == dc) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                        count += dp[r][c]; // mazePath_memo(r, c, dr, dc, dir, dp);
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];

    }

    public static void mazePath_HDV() {
        int n = 5, m = 5;
        int[][] dp = new int[n][m];
        int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0 } };
        System.out.println(mazePath_HDV_memo(0, 0, n - 1, m - 1, dir, dp));
        print2D(dp);
    }

    // *********_maze path with multiple move_*********
    public static int mazePath_MultipleMove_memo(int sr, int sc, int dr, int dc, int[][] dir, int[][] dp) {
        if (sr == dr && sc == dc) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(dr, dc); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                    count += mazePath_MultipleMove_memo(r, c, dr, dc, dir, dp);
                } else {
                    break;
                }
            }

        }
        return dp[sr][sc] = count;

    }

    // ==========================================================================================================================================================
    // Question_6 : maze path with maximum jumps
    public static int mazePath_MultipleMove_dp(int SR, int SC, int dr, int dc, int[][] dir, int[][] dp) {
        for (int sr = dr; sr >= 0; sr--) {
            for (int sc = dc; sc >= 0; sc--) {
                if (sr == dr && sc == dc) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;

                for (int d = 0; d < dir.length; d++) {
                    for (int rad = 1; rad <= Math.max(dr, dc); rad++) {
                        int r = sr + rad * dir[d][0];
                        int c = sc + rad * dir[d][1];

                        if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                            count += dp[r][c];// mazePath_MultipleMove_memo(r, c, dr, dc, dir, dp);
                        } else {
                            break;
                        }
                    }

                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];

    }

    public static void mazePath_MultipleMove() {
        int n = 5, m = 5;
        int[][] dp = new int[n][m];
        int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0 } };
        System.out.println(mazePath_MultipleMove_dp(0, 0, n - 1, m - 1, dir, dp));
        print2D(dp);
    }

    // ==========================================================================================================================================================
    // 62. Unique Paths
    // https://leetcode.com/problems/unique-paths/
    public static int uniquePaths_memo(int sr, int sc, int dr, int dc, int[][] dir, int[][] dp) {
        if (sr == dr && sc == dc) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                count += uniquePaths_memo(r, c, dr, dc, dir, dp);
            }
        }

        return dp[sr][sc] = count;
    }

    public static int uniquePaths_dp(int SR, int SC, int dr, int dc, int[][] dir, int[][] dp) {
        for (int sr = dr; sr >= 0; sr--) {
            for (int sc = dc; sc >= 0; sc--) {
                if (sr == dr && sc == dc) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                        count += dp[r][c]; // mazePath_memo(r, c, dr, dc, dir, dp);
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];

    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        int[][] dir = { { 0, 1 }, { 1, 0 } };
        // return uniquePaths_memo(0, 0, m - 1, n - 1, dir, dp);
        return uniquePaths_dp(0, 0, m - 1, n - 1, dir, dp);

    }

    // ==========================================================================================================================================================
    // *************_63. Unique Paths II_*************
    public static int uniquePathsII_memo(int sr, int sc, int dr, int dc, int[][] dir, int[][] dp,
            int[][] obstacleGrid) {
        if (sr == dr && sc == dc && obstacleGrid[sr][sc] != 1) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc && obstacleGrid[r][c] != 1) {
                count += uniquePathsII_memo(r, c, dr, dc, dir, dp, obstacleGrid);
            }
        }

        return dp[sr][sc] = count;
    }

    public static int uniquePathsII_dp(int SR, int SC, int dr, int dc, int[][] dir, int[][] dp, int[][] obstacleGrid) {
        for (int sr = dr; sr >= 0; sr--) {
            for (int sc = dc; sc >= 0; sc--) {
                if (sr == dr && sc == dc && obstacleGrid[sr][sc] != 1) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r <= dr && c <= dc && obstacleGrid[r][c] != 1) {
                        count += dp[r][c];
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];

    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1)
            return 0;
        int[][] dp = new int[n][m];
        int[][] dir = { { 0, 1 }, { 1, 0 } };

        return uniquePathsII_dp(0, 0, n - 1, m - 1, dir, dp, obstacleGrid);
    }

    public static void main(String[] args) {
        // fibo();
        // return climbStairs(6);
        // diceThrow();
        mazePath_MultipleMove();
    }
}