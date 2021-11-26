import java.util.Scanner;
import java.util.ArrayList;

// import java.util.ArrayList;
public class l003_FloodFill {
    public static Scanner scn = new Scanner(System.in);

    // ====================================================================================================================================================================
    // Question_1 : flood fill with one jump only-> radius 1
    // =====================================================

    // ====================================
    // Mathod_1 : return type --> ArrayList
    // ====================================
    public static ArrayList<String> flood_fill(int sr, int sc, int dr, int dc, int[][] dir, String[] dir_str,
            boolean[][] vis) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();
        vis[sr][sc] = true;
        int n = vis.length, m = vis[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                ArrayList<String> small_ans = flood_fill(r, c, dr, dc, dir, dir_str, vis);
                for (String s : small_ans) {
                    ans.add(dir_str[d] + s);
                }
            }
        }
        vis[sr][sc] = false;

        return ans;
    }

    // =================
    // Mathod_2 : way_up
    // =================
    public static int floodFill(int sr, int sc, int er, int ec, boolean[][] visited, int[][] dir, String[] dirS,
            String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        visited[sr][sc] = true;
        int n = visited.length;
        int m = visited[0].length;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !visited[r][c]) {
                count += floodFill(r, c, er, ec, visited, dir, dirS, ans + dirS[d]);
            }
        }
        visited[sr][sc] = false;
        return count;
    }

    // ====================================================================================================================================================================
    // Question_2 : flood fill with multiple jump only-> radius vary karega
    // ====================================================================
    public static int floodFill_multiJump(int sr, int sc, int er, int ec, boolean[][] visited, int[][] dir,
            String[] dirS, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        visited[sr][sc] = true;
        int n = visited.length;
        int m = visited[0].length;

        for (int rad = 1; rad < Math.max(m, n); rad++) {

            for (int d = 0; d < dir.length; d++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m && !visited[r][c]) {
                    count += floodFill_multiJump(r, c, er, ec, visited, dir, dirS, ans + dirS[d] + rad);
                }
            }
        }
        visited[sr][sc] = false;
        return count;
    }

    // ====================================================================================================================================================================
    // Question_3 : Flood Fill 01
    // ==========================
    public static int floodFill_01(int sr, int sc, int er, int ec, int[][] visited, int[][] dir, String[] dirS,
            String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        visited[sr][sc] = 1;
        int n = visited.length;
        int m = visited[0].length;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && visited[r][c] == 0) {
                count += floodFill_01(r, c, er, ec, visited, dir, dirS, ans + dirS[d]);
            }
        }
        visited[sr][sc] = 0;
        return count;
    }

    // ====================================================================================================================================================================
    // Question_4 : knights tour
    // ========================= 
    private static void print_1d(int[] arr) {
        for(int ele : arr) 
            System.out.print(ele + " ");

    }
    private static void display(int[][] arr) {
        for(int[] a : arr) {
            print_1d(a);
            System.out.println();
        }
        System.out.println();
    }
    
    public static void knight_tour(int sr, int sc, int dr, int dc, int move, int total_cell, int[][] dir, int[][] board) {
        if(move == total_cell) {
            board[sr][sc] = move;
            display(board);
            board[sr][sc] = 0;
        }
    
        board[sr][sc] = move;
        int n = board.length, m = board[0].length;
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
    
            if(r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 0) {
                knight_tour(r, c, dr, dc, move + 1, total_cell, dir, board);
            }
        }
        board[sr][sc] = 0;
    }

    // ============================================
    public static void displayBoard(int[][] chess) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    // tnc -> total no of cell
    public static int knight_tour(int sr, int sc, int move, int tnc, int[][] visited, int[][] dir) {
        if (move == tnc) {
            visited[sr][sc] = move;
            displayBoard(visited);
            visited[sr][sc] = 0;
            return 1;
        }
        int count = 0;

        visited[sr][sc] = move;
        int n = visited.length;
        int m = visited[0].length;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && visited[r][c] == 0) {
                count += knight_tour(r, c, move + 1, tnc, visited, dir);
            }
        }
        visited[sr][sc] = 0;
        return count;
    }

    public static void main(String[] args) {
        // int[][] dir = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2
        // }, { -1, -2 }, { -2, -1 } };
        // int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
        // String[] dir_str = { "T", "E", "R", "S", "D", "W", "L", "N" };

        // String[] dirS = { "L", "D", "R", "U" };

        // int n = 3, m = 3;
        // boolean[][] vis = new boolean[n][m];
        // ArrayList<String> ans = flood_fill(0, 0, n - 1, m - 1, dir, dir_str, vis);
        // for (String s : ans) {
        //     System.out.println(s);
        // }
        // System.out.println(floodFill_multiJump(0, 0, n - 1, m - 1, vis, dir, dirS,
        // ""));
 
        int n = 5, m = 5;
        int[][] board = new int[n][m];
        int[][] dir = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1} , {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        knight_tour(0, 0, n - 1, m - 1, 1, n * m, dir, board);;
    }

}