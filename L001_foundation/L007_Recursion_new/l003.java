import java.util.Scanner;
// import java.util.ArrayList;
public class l003 {
    public static Scanner scn = new Scanner(System.in);

    // ******************************************** DATE: 2/07/2021
    // ********************************************
    // flood fill with one jump only-> radius 1
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

    // flood fill with multiple jump only-> radius vary karega
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

    // 01
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

    // knights tour ******************************************
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

    }

  
}