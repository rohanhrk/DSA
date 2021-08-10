import java.util.Arrays;
import java.util.HashSet;

public class l006Recursion {
    static String s1 = "send";
    static String s2 = "more";
    static String s3 = "money";

    static boolean[] usedNumber = new boolean[10];
    static int[] mapping = new int[128];

    public static int convertStringToNumber(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res = res * 10 + mapping[str.charAt(i)];
        }

        return res;
    }

    public static int crypto_helper(String str, int idx) {
        if (idx == str.length()) {
            if (mapping[s1.charAt(0)] == 0 || mapping[s2.charAt(0)] == 0 || mapping[s3.charAt(0)] == 0)
                return 0;
            int x = convertStringToNumber(s1);
            int y = convertStringToNumber(s2);
            int z = convertStringToNumber(s3);

            if (x + y == z) {
                StringBuilder sb = new StringBuilder();
                for (int i = 'a'; i <= 'z'; i++) {
                    if (mapping[i] >= 0) {
                        sb.append((char) (i) + "-" + mapping[i] + " ");
                    }
                }
                System.out.println(sb.toString());
                return 1;
            }

            return 0;
        }

        int count = 0;
        char ch = str.charAt(idx);
        for (int i = 0; i <= 9; i++) {
            if (!usedNumber[i]) {
                usedNumber[i] = true;

                mapping[ch] = i;
                count += crypto_helper(str, idx + 1);
                mapping[ch] = -1;

                usedNumber[i] = false;
            }
        }

        return count;
    }

    public static void crypto() {
        String str = s1 + s2 + s3;
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        str = "";
        for (int i = 0; i < freq.length; i++)
            if (freq[i] > 0)
                str += (char) (i + 'a');

        Arrays.fill(mapping, -1);
        System.out.println(crypto_helper(str, 0));
    }

    // word break
    public static int wordBreak(String str, int idx, String ans, int len, HashSet<String> dict) {
        if (idx >= str.length()) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i <= str.length(); i++) {
            String word = str.substring(idx, i);
            if (word.length() > len)
                break;
            if (dict.contains(word)) {
                count += wordBreak(str, i, ans + word + " ", len, dict);
            }
        }
        return count;
    }

    public static void wordBreak(String str, String ans, HashSet<String> dict) {
        int len = 0;
        for (String s : dict)
            len = Math.max(len, s.length());
        wordBreak(str, 0, ans, len, dict);
    }

    // 37. Sudoku Solver
    public static boolean isSafeToPlaceNumber(char[][] board, int row, int col, int number) {
        int n = board.length, m = board[0].length;
        // row -> row const , but col varry
        for (int j = 0; j < m; j++)
            if ((board[row][j] - '0') == number)
                return false;

        // col -> col const but row varry
        for (int i = 0; i < n; i++)
            if ((board[i][col] - '0') == number)
                return false;

        // 3 X 3 mat
        row = (row / 3) * 3;
        col = (col / 3) * 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if ((board[i + row][j + col] - '0') == number)
                    return false;

        return true;
    }

    public static boolean sudokuSolver(char[][] board, int idx) {
        if (idx == 81) {
            return true;
        }
        int r = idx / 9;
        int c = idx % 9;

        if (board[r][c] != '.') {
            if (sudokuSolver(board, idx + 1))
                return true;
        } else {
            for (int num = 1; num <= 9; num++) {
                if (isSafeToPlaceNumber(board, r, c, num)) {
                    board[r][c] = (char) (num + '0');
                    if (sudokuSolver(board, idx + 1))
                        return true;
                    board[r][c] = '.';
                }

            }
        }

        return false;
    }

    public static void main(String[] args) {
        crypto();
    }
}