import java.util.Scanner;
import java.util.ArrayList;

public class l002 {
    // *************************** Date - 30/06 ***************************
    public static Scanner scn = new Scanner(System.in);

    // print subsequesne -> upar jate hue storing
    public static void printSS(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }
        char ch = str.charAt(idx);

        printSS(str, idx + 1, ans + ch);
        printSS(str, idx + 1, ans);

    }

    // using string builder
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

    // *************************** Date - 1/07 ***************************
    // ASCII Subsequences
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

    // return type(ArrayList) **********************************
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

    // way up->upar jate hue ans storing **********************************
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

    // get key pad combination (kpc) -> way up **********************************
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

    // maze path (horizontal ans vertical move) **********************************

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
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

    // way up
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

    // maze path with jump

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static ArrayList<String> getMazePathsWithJump_return(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();

        for (int jump = 1; sc + jump <= dc; jump++) {
            ArrayList<String> horizontal = getMazePathsWithJump_return(sr, sc + jump, dr, dc);

            for (String s : horizontal) {
                myAns.add("h" + jump + s);
            }
        }

        for (int jump = 1; sr + jump <= dr; jump++) {
            ArrayList<String> vertical = getMazePathsWithJump_return(sr + jump, sc, dr, dc);

            for (String s : vertical) {
                myAns.add("v" + jump + s);
            }
        }

        for (int jump = 1; sc + jump <= dc || sr + jump <= dr; jump++) {
            ArrayList<String> diagonal = getMazePathsWithJump_return(sr + jump, sc + jump, dr, dc);

            for (String s : diagonal) {
                myAns.add("d" + jump + s);
            }
        }
        return myAns;
    }
    
    // way up
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

    public static void main(String[] args) {
        System.out.println(getMazePathsWithJump_wayup(0, 0, 2, 2, ""));
    }
}