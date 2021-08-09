import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;

public class question {
    // =======================================_DATE:2/08_=======================================

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

    // 1219. Path with Maximum Gold
    public int getMaximumGold(int[][] mat, int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir) {
        if (mat[sr][sc] == 0)
            return 0;
        int max = 0;
        vis[sr][sc] = true;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c] && mat[r][c] != 0) {
                max = Math.max(max, getMaximumGold(mat, r, c, er, ec, vis, dir));
            }
        }
        vis[sr][sc] = false;

        return max + mat[sr][sc];
    }

    public int getMaximumGold(int[][] grid) {
        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        int max = -(int) 1e9;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, getMaximumGold(grid, i, j, n - 1, m - 1, vis, dir));
            }
        }

        return max;
    }

    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
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

    // =======================================_DATE:5/08_=======================================
    // 39. Combination Sum
    public int combinationSum(int[] arr, int tar, int idx, List<Integer> smallAns, List<List<Integer>> res) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                smallAns.add(arr[i]);
                count += combinationSum(arr, tar - arr[i], i, smallAns, res);
                smallAns.remove(smallAns.size() - 1);
            }
        }

        return count;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();

        combinationSum(candidates, target, 0, smallAns, res);

        return res;
    }

    // 40. Combination Sum II
    // mathode 1
    public int combinationSum2_m1(int[] arr, int tar, int idx, List<Integer> smallAns, List<List<Integer>> res) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }

        int count = 0;
        boolean[] blockWidth = new boolean[51];
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0 && !blockWidth[arr[i]]) {
                blockWidth[arr[i]] = true;
                smallAns.add(arr[i]);
                count += combinationSum2_m1(arr, tar - arr[i], i + 1, smallAns, res);
                smallAns.remove(smallAns.size() - 1);
            }
        }

        return count;
    }

    // mathod 2
    public int combinationSum2_m2(int[] arr, int tar, int idx, List<Integer> smallAns, List<List<Integer>> res) {
        if (tar == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }

        int count = 0;
        int prev = -1;

        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0 && prev != arr[i]) {
                smallAns.add(arr[i]);
                count += combinationSum2_m2(arr, tar - arr[i], i + 1, smallAns, res);
                smallAns.remove(smallAns.size() - 1);
            }
            prev = arr[i];
        }

        return count;
    }

    // subsequence mathod
    public int combinationSum2_subseq(int[] arr, int tar, int idx, List<Integer> smallAns, List<List<Integer>> res) {
        if (idx >= arr.length || tar == 0) {
            if (tar == 0) {
                List<Integer> base = new ArrayList<>(smallAns);
                res.add(base);
                return 1;
            }
            return 0;
        }

        int count = 0;

        if (tar - arr[idx] >= 0) {
            smallAns.add(arr[idx]);
            count += combinationSum2_subseq(arr, tar - arr[idx], idx + 1, smallAns, res);
            smallAns.remove(smallAns.size() - 1);
        }

        while (idx + 1 < arr.length && arr[idx + 1] == arr[idx])
            idx++;
        idx++;
        count += combinationSum2_subseq(arr, tar, idx, smallAns, res);
        return count;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2_m1(candidates, target, 0, smallAns, res);

        return res;
    }

    // 77. Combinations
    public int combine(int n, int k, int idx, List<Integer> smallAns, List<List<Integer>> res) {
        if (k == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }

        int count = 0;
        // if(dp[n][k] != 0) return dp
        for (int i = idx; i <= n; i++) {
            smallAns.add(i);
            count += combine(n, k - 1, i + 1, smallAns, res);
            smallAns.remove(smallAns.size() - 1);
        }

        return count;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        int count = combine(n, k, 1, smallAns, res);

        return res;
    }

    // 216. Combination Sum III
    public int combinationSum3(int tar, int k, int idx, List<Integer> smallAns, List<List<Integer>> res) {
        if (k == 0 || tar == 0) {
            if (k == 0 && tar == 0) {
                List<Integer> base = new ArrayList<>(smallAns);
                res.add(base);
                return 1;
            }
            return 0;
        }

        int count = 0;
        // if(dp[n][k] != 0) return dp
        for (int i = idx; i <= 9; i++) {
            if (tar - i >= 0) {
                smallAns.add(i);
                count += combinationSum3(tar - i, k - 1, i + 1, smallAns, res);
                smallAns.remove(smallAns.size() - 1);
            }
        }

        return count;
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        int count = combine(n, k, 1, smallAns, res);

        return res;
    }

    // 46. Permutations
    public int permute(int[] arr, int idx, List<Integer> smallAns, List<List<Integer>> res, int x, boolean[] vis) {
        if (x == arr.length) {
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                smallAns.add(arr[i]);
                count += permute(arr, i, smallAns, res, x + 1, vis);
                smallAns.remove(smallAns.size() - 1);
                vis[i] = false;
            }
        }

        return count;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        int count = permute(nums, 0, smallAns, res, 0, new boolean[nums.length + 1]);
        return res;

    }

    //
    public int permuteUnique(int[] arr, int idx, List<Integer> smallAns, List<List<Integer>> res, int x,
            boolean[] vis) {
        if (x == arr.length) {
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return 1;
        }

        int count = 0;
        // int prev = -1;
        boolean[] blockWidth = new boolean[22];
        for (int i = 0; i < arr.length; i++) {
            if (!vis[i] && !blockWidth[arr[i] + (10)]) {
                vis[i] = true;
                blockWidth[arr[i] + 10] = true;

                smallAns.add(arr[i]);
                count += permuteUnique(arr, i, smallAns, res, x + 1, vis);
                smallAns.remove(smallAns.size() - 1);

                vis[i] = false;
            }
            // prev = arr[idx];
        }

        return count;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        int count = permuteUnique(nums, 0, smallAns, res, 0, new boolean[nums.length + 1]);
        return res;

    }

    // 51. N-Queens
    boolean[] rows;
    boolean[] cols;
    boolean[] diag;
    boolean[] anti_diag;
    List<List<String>> ans = new ArrayList<>();

    public int nqueen_Combination03(boolean[][] boxes, int tnq, int idx) {
        int n = boxes.length, m = boxes[0].length;
        if (tnq == 0) {
            ArrayList<String> smallAns = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sb.append(boxes[i][j] ? "Q" : ".");
                }
                smallAns.add(sb.toString());
            }

            ans.add(smallAns);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!rows[r] && !cols[c] && !diag[r + c] && !anti_diag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = boxes[r][c] = true;
                count += nqueen_Combination03(boxes, tnq - 1, i + 1);
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = boxes[r][c] = false;
            }
        }

        return count;
    }

    public List<List<String>> solveNQueens(int n) {
        int m = n;
        boolean[][] boxes = new boolean[n][m];
        rows = new boolean[n];
        cols = new boolean[m];
        diag = new boolean[n + m - 1];
        anti_diag = new boolean[n + m - 1];

        nqueen_Combination03(boxes, n, 0);
        return ans;
    }

    // 52. N-Queens II
    // combination
    public int nqueen_combination03(int n, int m, int tnq, int idx) {
        if (tnq == 0) {
            return 1;
        }

        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!rows[r] && !cols[c] && !diag[r + c] && !anti_diag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = true;
                count += nqueen_combination03(n, m, tnq - 1, i + 1);
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    public int nQueens_optimize(int n) {
        int q = n, m = n;
        rows = new boolean[n];
        cols = new boolean[m];
        diag = new boolean[n + m - 1];
        anti_diag = new boolean[n + m - 1];

        return nqueen_combination03(n, m, q, 0);
        // System.out.println(nqueen_permutationn03(n, m, q, 0, ""));

        // System.out.println(nqueen_combination04(n, m, q, 0, ""));
        // System.out.println(nqueen_permutationn04(n, m, q, 0, ""));

    }

    public int totalNQueens(int n) {
        return nQueens_optimize(n);
    }

    public static void main(String[] args) {

    }
}
