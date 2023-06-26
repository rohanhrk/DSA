public class practice {
    public static boolean isSafeToPlaceQueen(int sr, int sc, boolean[][] vis, int n, int m) {
        int[][] dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        for(int[] d : dir) {
            for(int rad = 1; rad < n; rad++) {
                int r = sr + d[0] * rad;
                int c = sc + d[1] * rad;
                if(r >= 0 && r < n && c >= 0 && c < m && vis[r][c]) {
                    return false;
                }
            }	
        }
        
        return true;
    }
    public static int queensComb2d(int tnb, int bn, int tnq, int qn, String qpsf, int n, int m, boolean[][] vis) {
        if(qn == tnq) {
            System.out.println(qpsf);
            return 1;
        }

        int count = 0;
        for(int i = bn; i < n * m; i++) {
            int r = i / m;
            int c = i % m;

            if(isSafeToPlaceQueen(r, c, vis, n, m)) {
                vis[r][c] = true;
                count += queensComb2d(tnb, i + 1, tnq, qn + 1, qpsf + "b" + r + "" + c + "q" + qn + " ", n, m, vis);
                vis[r][c] = false;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int n = 4, m = 4, q = 4, tnb = n * m;
        boolean[][] vis = new boolean[n][m];
        int ans = queensComb2d(tnb, 0, q, 0, "", n, m, vis);
        System.out.println(ans);
    }

}