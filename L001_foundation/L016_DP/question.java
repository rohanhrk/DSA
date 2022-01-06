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
