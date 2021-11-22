import java.util.Scanner;
import java.util.Arrays;
// =======================================_DATE:2/08_=======================================
public class l001 {
    public static Scanner scn = new Scanner(System.in);

    // ==================================================================================================================
    // ==================================================
    // ===============_Flood_Fill_problem_===============
    // --> Print all path
    public static int floodFill(int sr, int sc, int dr, int dc, String[] dirs, int[][] dir, boolean[][] vis, String psf) {
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;
        int n = vis.length;
        int m = vis[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                count += floodFill(r, c, dr, dc, dirs, dir, vis, psf + dirs[d]);
            }
        }
        vis[sr][sc] = false;
        return count;
    }

    // ==================================================================================================================
    // ============================================
    // ===============_Longest_Path_===============
    public static class pair {
        String psf;
        int length;
        int count;

        // int wsf;
        pair(String psf, int length, int count) {
            this.psf = psf;
            this.length = length;
            this.count = count;
        }

    }

    public static pair floodFill_longestPAth(int sr, int sc, int dr, int dc, String[] dirs, int[][] dir, boolean[][] vis) {
        if (sr == dr && sc == dc) {
            // System.out.println(ans);
            pair p = new pair("", 0, 1);
            return p;
        }

        pair myAns = new pair("", -(int) 1e8, 0);
        vis[sr][sc] = true;
        int n = vis.length;
        int m = vis[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                pair recAns = floodFill_longestPAth(r, c, dr, dc, dirs, dir, vis);
                if (recAns.length + 1 > myAns.length) {
                    myAns.psf = dirs[d] + recAns.psf;
                    myAns.length = recAns.length + 1;
                }

                myAns.count += recAns.count;
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }

    // ==================================================================================================================
    // ============================================
    // ===============_Longest_Path_===============
    public static pair floodFill_shortesttPAth(int sr, int sc, int dr, int dc, String[] dirs, int[][] dir,
            boolean[][] vis) {
        if (sr == dr && sc == dc) {
            // System.out.println(ans);
            pair p = new pair("", 0, 1);
            return p;
        }
        // int count = 0;
        pair myAns = new pair("", (int) 1e8, 0);
        vis[sr][sc] = true;
        int n = vis.length;
        int m = vis[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                pair recAns = floodFill_shortesttPAth(r, c, dr, dc, dirs, dir, vis);
                if (recAns.length + 1 < myAns.length) {
                    myAns.psf = dirs[d] + recAns.psf;
                    myAns.length = recAns.length + 1;
                }

                myAns.count += recAns.count;
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }

    // ==================================================================================================================
    // ==========================================
    // ===============_heavy_Path_===============
    public static class pathpair {
        String psf;
        int wsf;
        int count;

        pathpair(String psf, int wsf, int count) {
            this.psf = psf;
            this.wsf = wsf;
            this.count = count;
        }

    }

    public static pathpair floodFill_heavyPath(int sr, int sc, int dr, int dc, String[] dirs, int[][] dir, boolean[][] vis, int[] wt) {
        if (sr == dr && sc == dc) {
            // System.out.println(ans);
            pathpair p = new pathpair("", 0, 1);
            return p;
        }
        // int count = 0;
        pathpair myAns = new pathpair("", -(int) 1e8, 0);
        vis[sr][sc] = true;
        int n = vis.length;
        int m = vis[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                pathpair recAns = floodFill_heavyPath(r, c, dr, dc, dirs, dir, vis, wt);
                if (recAns.wsf + wt[d] > myAns.wsf) {
                    myAns.psf = dirs[d] + recAns.psf;
                    myAns.wsf = recAns.wsf + wt[d];
                }

                myAns.count += recAns.count;
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }

    // ==================================================================================================================
    // =============================================
    // ===============_Lightest_Path_===============
    public static pathpair floodFill_lightestPath(int sr, int sc, int dr, int dc, String[] dirs, int[][] dir,
            boolean[][] vis, int[] wt) {
        if (sr == dr && sc == dc) {
            // System.out.println(ans);
            pathpair p = new pathpair("", 0, 1);
            return p;
        }
        // int count = 0;
        pathpair myAns = new pathpair("", (int) 1e8, 0);
        vis[sr][sc] = true;
        int n = vis.length;
        int m = vis[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                pathpair recAns = floodFill_lightestPath(r, c, dr, dc, dirs, dir, vis, wt);
                if (recAns.wsf + wt[d] < myAns.wsf) {
                    myAns.psf = dirs[d] + recAns.psf;
                    myAns.wsf = recAns.wsf + wt[d];
                }

                myAns.count += recAns.count;
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }

    // ==================================================================================================================
    
    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
        String[] dirs = { "T", "E", "L", "S", "D", "W", "R", "N" };
        int[] wt = { 2, 1, 3, 1, 2, 5, 4, 1 };
        boolean[][] vis = new boolean[n][m];
        // pair p = new pair();
        pathpair p = floodFill_heavyPath(0, 0, n - 1, m - 1, dirs, dir, vis, wt);
        System.out.println("path->" + p.psf);
        System.out.println("length->" + p.wsf);
        System.out.println("count->" + p.count);

    }
}