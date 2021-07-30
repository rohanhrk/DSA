import java.util.Scanner;
import java.util.Arrays;

public class question {
    public static Scanner scn = new Scanner(System.in);

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

    // ===================_LEETCODE_===================
    // *********_leetCode 70. Climbing Stairs_*********
    public static int climb_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = 1;

        if (dp[n] != 0)
            return dp[n];
        int steps = climb_memo(n - 1, dp) + climb_memo(n - 2, dp);
        return dp[n] = steps;
    }

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

    // *********_746. Min Cost Climbing Stairs_*********
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

    // *************_62. Unique Paths_*************
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

    // 64. Minimum Path Sum
    public int minPathSum_memo(int[][] grid, int sr, int sc, int dr, int dc, int[][] dir, int[][] dp) {
        if (sr == dr && sc == dc) {
            return dp[sr][sc] = grid[sr][sc];
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int sum = (int) 1e8;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                sum = Math.min(minPathSum_memo(grid, r, c, dr, dc, dir, dp), sum);
            }

        }

        return dp[sr][sc] = sum + grid[sr][sc];
    }

    public int minPathSum_dp(int[][] grid, int SR, int SC, int dr, int dc, int[][] dir, int[][] dp) {
        for (int sr = dr; sr >= 0; sr--) {
            for (int sc = dc; sc >= 0; sc--) {
                if (sr == dr && sc == dc) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }
                int sum = (int) 1e8;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                        sum = Math.min(dp[r][c], sum);
                    }

                }

                dp[sr][sc] = sum + grid[sr][sc];
            }
        }

        return dp[SR][SC];

    }

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        int[][] dir = { { 0, 1 }, { 1, 0 } };

        return minPathSum_dp(grid, 0, 0, n - 1, m - 1, dir, dp);
    }

    // ****************_Friends Pairing_****************
    public static int friendPairing_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = 1;

        if (dp[n] != 0)
            return dp[n];

        int single = friendPairing_memo(n - 1, dp);
        int pairUp = friendPairing_memo(n - 2, dp) * (n - 1);

        return dp[n] = single + pairUp;
    }

    public static int friendPairing_dp(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            int single = dp[n - 1];// friendPairing_memo(n - 1, dp);
            int pairUp = dp[n - 2] * (n - 1);// friendPairing_memo(n - 2, dp) * (n - 1);

            dp[n] = single + pairUp;
        }

        return dp[N];
    }

    public static int friendsPairing_opti(int n) {
        int a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            int sum = b + a * (i - 1);
            a = b;
            b = sum;
        }

        return b;
    }

    public static void friendPairing(int n) {
        int[] dp = new int[n + 1];
        // friendPairing_dp(n, dp);
        System.out.println(friendsPairing_opti(n));
        // print(dp);
    }

    // *************_GOLD MINE_*************
    public static int goldMine_memo(int[][] maze, int sr, int sc, int dr, int dc, int[][] dp, int[][] dir) {
        if (sc == dc)
            return dp[sr][sc] = maze[sr][sc];

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int max = -(int) 1e8;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                max = Math.max(max, goldMine_memo(maze, r, c, dr, dc, dp, dir));
            }
        }

        return dp[sr][sc] = max + maze[sr][sc];
    }

    public static int goldMine_dp(int[][] maze, int SR, int SC, int dr, int dc, int[][] dp, int[][] dir) {
        for (int sc = dc; sc >= 0; sc--) {
            for (int sr = dr; sr >= 0; sr--) {
                if (sc == dc) {
                    dp[sr][sc] = maze[sr][sc];
                    continue;
                }

                int max = -(int) 1e8;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                        max = Math.max(max, dp[r][c]);
                    }
                }

                dp[sr][sc] = max + maze[sr][sc];
            }
        }

        return dp[SR][SC];
    }

    public static int goldMine(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        int[][] dp = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };

        int maxGold = -(int) 1e8;
        for (int i = 0; i < n; i++) {
            maxGold = Math.max(goldMine_dp(maze, i, 0, n - 1, m - 1, dp, dir), maxGold);
        }

        print2D(dp);
        return maxGold;
    }

    public static void input(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = scn.nextInt();
            }
        }
    }

    // Min Cost In Maze Traversal
    public static int minCost_memo(int[][] maze, int sr, int sc, int dr, int dc, int[][] dir, int[][] dp) {
        if (sr == dr && sc == dc)
            return dp[sr][sc] = maze[sr][sc];

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int min = (int) 1e8;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                min = Math.min(min, minCost_memo(maze, r, c, dr, dc, dir, dp));
            }
        }

        return dp[sr][sc] = min + maze[sr][sc];
    }

    public static int minCost_dp(int[][] maze, int SR, int SC, int dr, int dc, int[][] dir, int[][] dp) {
        for (int sc = dc; sc >= 0; sc--) {
            for (int sr = dr; sr >= 0; sr--) {
                if (sr == dr && sc == dc) {
                    dp[sr][sc] = maze[sr][sc];
                    continue;
                }

                int min = (int) 1e8;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                        min = Math.min(min, dp[r][c]); // Math.min(min, minCost_memo(maze, r, c, dr, dc, dir, dp));
                    }
                }

                dp[sr][sc] = min + maze[sr][sc];
            }
        }

        return dp[SR][SC];

    }

    public static void minCost(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        int[][] dir = { { 0, 1 }, { 1, 0 } };
        int[][] dp = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        minCost_dp(maze, 0, 0, n - 1, m - 1, dir, dp);
        // return dp[0][0];
        print2D(dp);
    }

   

    public static void main(String[] args) {
        // friendPairing(5);
        // int n = scn.nextInt();
        // int m = scn.nextInt();
        // int[][] maze = new int[n][m];
        // input(maze);

        // minCost(maze);
    }
}
