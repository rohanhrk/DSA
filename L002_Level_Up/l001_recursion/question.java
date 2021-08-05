import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;

public class question {
    // Special Matrix
    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1
    public int floodFill_findWays(int sr, int sc, int dr, int dc, int[][] dir, boolean[][] block, int[][] dp) {
        if (sr == dr && sc == dc)
            return dp[sr][sc] = 1;

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        // block[sr][sc] = true;
        int count = 0;
        int mod = (int) 1e9 + 7;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc && !block[r][c]) {
                count = (count % mod + floodFill_findWays(r, c, dr, dc, dir, block, dp) % mod) % mod;
            }
        }

        // block[sr][sc] = false;
        return dp[sr][sc] = count;
    }

    public int FindWays(int n, int m, int[][] blocked_cells) {
        int[][] dir = { { 0, 1 }, { 1, 0 } };
        boolean[][] block = new boolean[n + 1][m + 1];
        for (int[] a : blocked_cells) {
            int i = a[0], j = a[1];
            block[i][j] = true;
        }
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        if (block[1][1] || block[n][m])
            return 0;
        int ans = floodFill_findWays(1, 1, n, m, dir, block, dp);
        return ans;
    }
   
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1*.cl
    public static int floodFill(int sr, int sc, int er, int ec, int[][] dir, String[] Sdir, int[][] vis, String psf,
            ArrayList<String> res) {

        if (sr == er && sc == ec) {
            res.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && vis[r][c] == 1) {
                count += floodFill(r, c, er, ec, dir, Sdir, vis, psf + Sdir[d], res);
            }
        }

        vis[sr][sc] = 1;
        return count;
    }

    public static ArrayList<String> findPath(int[][] mat, int n) {
        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        String[] Sdir = { "L", "R", "U", "D" };

        // System.out.println(floodFill(0, 0, n - 1, m - 1, dir, Sdir, vis, ""));
        ArrayList<String> res = new ArrayList<>();
        if (mat[0][0] == 0 || mat[n - 1][n - 1] == 0)
            return res;
        int ans = floodFill(0, 0, n - 1, n - 1, dir, Sdir, mat, "", res);

        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {

    }
}
