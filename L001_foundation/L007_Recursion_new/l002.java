import java.util.Scanner;
import java.util.ArrayList;

public class l002 {
    // *************************** Date - 30/06 ***************************
    public static Scanner scn = new Scanner(System.in);

    // ====================================================================================================================================================================
    // Question_1 : print subsequesne -> upar jate hue storing
    // =======================================================
    public static void printSS(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }
        char ch = str.charAt(idx);

        printSS(str, idx + 1, ans + ch);
        printSS(str, idx + 1, ans);

    }

    // ====================================================================================================================================================================
    // using string builder
    // ====================
    public static void printss_02(String str, int idx, StringBuilder ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }

        ans.append(str.charAt(idx));
        printss_02(str, idx + 1, ans);
        ans.deleteCharAt(ans.length() - 1);

        printss_02(str, idx + 1, ans);
    }

    // ====================================================================================================================================================================
    // Question_2 : ASCII Subsequences
    // ===============================
    public static int countAscii_01(String str, int idx) {

        if (idx == str.length()) {
            return 1;
        }

        int count = 0;

        count += countAscii_01(str, idx + 1);
        count += countAscii_01(str, idx + 1);
        count += countAscii_01(str, idx + 1);

        return count;

    }

    // ======================
    // Method_2 : return type(ArrayList)
    // ======================
    public static ArrayList<String> countAscii_02(String str, int idx) {
        if (idx == -1) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        char ch = str.charAt(idx);
        int ascii = (int) (ch);

        ArrayList<String> recAns = countAscii_02(str, idx - 1);
        ArrayList<String> myAns = new ArrayList<>(recAns);

        for (String s : recAns)
            myAns.add(ch + s);
        for (String s : recAns)
            myAns.add(ascii + s);

        return myAns;
    }

    // =================================
    // Method_3 : way up->upar jate hue ans storing
    // =================================
    public static void countAscii_03(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }

        char ch = str.charAt(idx);
        int ascii = (int) (ch);

        countAscii_03(str, idx + 1, ans);
        countAscii_03(str, idx + 1, ans + ch);
        countAscii_03(str, idx + 1, ans + ascii);

    }

    // ====================================================================================================================================================================
    // Question_3 : get key pad combination (kpc) -> way up
    // ====================================================
    static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int getKPC_wayup(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return 1;
        }
        char ch = str.charAt(idx);
        String code = codes[ch - '0'];
        int count = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            count += getKPC_wayup(str, idx + 1, ans + c);
        }

        return count;
    }

    public static ArrayList<String> getKPC_return(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        char ch = str.charAt(idx);
        String code = codes[ch - '0'];

        ArrayList<String> recAns = getKPC_return(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>();

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            for (String s : recAns) {
                myAns.add(c + s);
            }
        }

        return myAns;
    }

    // ====================================================================================================================================================================
    // Quetion_4 : maze path (horizontal/diagonal/vertical move)
    // =========================================================
    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column

    // ========
    // mathod_1
    // ========
    public static ArrayList<String> maze_path(int[][] maze, int sr, int sc, int dr, int dc, int[][] dir,
            String[] dir_str) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> my_ans = new ArrayList<>();
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < maze.length && c < maze[0].length) {
                ArrayList<String> small_ans = maze_path(maze, r, c, dr, dc, dir, dir_str);
                for (String s : small_ans)
                    my_ans.add(dir_str[d] + s);
            }
        }

        return my_ans;
    }

    // ========
    // mathod_2
    // ========
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        if (sc + 1 <= dc) {
            ArrayList<String> horizontal = getMazePaths(sr, sc + 1, dr, dc);

            for (String s : horizontal) {
                myAns.add("h" + s);
            }
        }
        if (sc + 1 <= dc && sr + 1 <= dr) {
            ArrayList<String> diagonal = getMazePaths(sr + 1, sc + 1, dr, dc);

            for (String s : diagonal) {
                myAns.add("d" + s);
            }
        }
        if (sr + 1 <= dr) {
            ArrayList<String> vertical = getMazePaths(sr + 1, sc, dr, dc);

            for (String s : vertical) {
                myAns.add("v" + s);
            }
        }

        return myAns;

    }

    // ==================
    // mathod_3 -> way up
    // ==================
    public static int getMazePaths_wayup(int sr, int sc, int dr, int dc, String ans) {
        if (sr == dr && sc == dc) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        if (sc + 1 <= dc)
            count += getMazePaths_wayup(sr, sc + 1, dr, dc, ans + "h");

        if (sr + 1 <= dr)
            count += getMazePaths_wayup(sr + 1, sc, dr, dc, ans + "v");

        return count;

    }

    // ====================================================================================================================================================================
    // Question_5 : maze path with jump
    // ================================
    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column

    // ========
    // mathod_1
    // ========
    public static ArrayList<String> maze_path_with_jumps(int[][] maze, int sr, int sc, int dr, int dc, int[][] dir,
            String[] dir_str) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> my_ans = new ArrayList<>();
        int n = maze.length, m = maze[0].length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < Math.max(n, m); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    ArrayList<String> small_ans = maze_path_with_jumps(maze, r, c, dr, dc, dir, dir_str);
                    for (String s : small_ans)
                        my_ans.add(dir_str[d] + rad + s);
                }
            }
        }

        return my_ans;
    }

    // ========
    // Mathod_2
    // ========
    public static ArrayList<String> getMazePathsWithJump_return(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();

        // horizontal move
        for (int jump = 1; sc + jump <= dc; jump++) {
            ArrayList<String> horizontal = getMazePathsWithJump_return(sr, sc + jump, dr, dc);

            for (String s : horizontal) {
                myAns.add("h" + jump + s);
            }
        }

        // vertical move
        for (int jump = 1; sr + jump <= dr; jump++) {
            ArrayList<String> vertical = getMazePathsWithJump_return(sr + jump, sc, dr, dc);

            for (String s : vertical) {
                myAns.add("v" + jump + s);
            }
        }

        // diagonal move
        for (int jump = 1; sc + jump <= dc && sr + jump <= dr; jump++) {
            ArrayList<String> diagonal = getMazePathsWithJump_return(sr + jump, sc + jump, dr, dc);

            for (String s : diagonal) {
                myAns.add("d" + jump + s);
            }
        }
        return myAns;
    }

    // =================
    // Mathod_3 : way up
    // =================
    public static int getMazePathsWithJump_wayup(int sr, int sc, int dr, int dc, String ans) {
        if (sr == dr && sc == dc) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int jump = 1; sc + jump <= dc; jump++) {
            count += getMazePathsWithJump_wayup(sr, sc + jump, dr, dc, ans + "h" + jump);
        }

        for (int jump = 1; sr + jump <= dr; jump++) {
            count += getMazePathsWithJump_wayup(sr + jump, sc, dr, dc, ans + "v" + jump);
        }

        for (int jump = 1; sc + jump <= dc || sr + jump <= dr; jump++) {
            count += getMazePathsWithJump_wayup(sr + jump, sc + jump, dr, dc, ans + "d" + jump);
        }
        return count;
    }

    // ====================================================================================================================================================================
    // Question_6 : print stair path -> 1/2/3 steps allowed
    // ====================================================

    // ========
    // Mathod_1
    // ========
    public static ArrayList<String> path_stair(int sr, int sc, int dr, int dc, int[][] dir) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();
        int n = dr + 1, m = dc + 1;
        for (int d = 0; d < dir.length; d++) {
            for (int step = 1; step <= 3; step++) {
                int r = sr + step * dir[d][0];
                int c = sc + step * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    ArrayList<String> small_ans = path_stair(r, c, dr, dc, dir);
                    for (String s : small_ans) {
                        ans.add("" + step + s);
                    }

                }
            }

        }

        return ans;
    }

    // ========
    // Mathod_2
    // ========
    public static ArrayList<String> getStairPaths_return(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();

        if (n - 1 >= 0) {
            ArrayList<String> one = getStairPaths_return(n - 1);
            for (String s : one)
                myAns.add("1" + s);
        }

        if (n - 2 >= 0) {
            ArrayList<String> two = getStairPaths_return(n - 2);
            for (String s : two)
                myAns.add("2" + s);

        }

        if (n - 3 >= 0) {
            ArrayList<String> three = getStairPaths_return(n - 3);
            for (String s : three)
                myAns.add("3" + s);
        }

        return myAns;

    }

    // =================
    // Mathod_3 : way up
    // =================
    public static void printStairPaths_wayup(int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return;
        }

        if (n - 1 >= 0) {
            printStairPaths_wayup(n - 1, ans + "1");

        }

        if (n - 2 >= 0) {
            printStairPaths_wayup(n - 2, ans + '2');

        }

        if (n - 3 >= 0) {
            printStairPaths_wayup(n - 3, ans + '3');
        }

    }

    public static void main(String[] args) {
        // System.out.println(getMazePathsWithJump_wayup(0, 0, 2, 2, ""));
        int n = 3, m = 10;
        int[][] maze = new int[n][m];
        // int[][] dir = {{0,1}, {1, 1}, {1, 0}};
        String[] dir_str = { "H", "D", "V" };

        // ArrayList<String> ans = maze_path(maze, 0, 0, n - 1, m - 1, dir, dir_str);
        // for(String s : ans) {
        // System.out.println(s.toString());
        // }

        // ArrayList<String> ans = getMazePaths(0, 0, n - 1, m - 1);
        // for(String s : ans) {
        // System.out.println(s);
        // }

        // ArrayList<String> ans = maze_path_with_jumps(maze, 0, 0, n - 1, m - 1, dir,
        // dir_str);
        // for(String s : ans) {
        // System.out.println(s.toString());
        // }

        int[][] dir = { { 0, 1 } };
        ArrayList<String> ans = path_stair(0, 0, 0, m, dir);
        for (String s : ans) {
            System.out.println(s.toString());
        }

    }
}