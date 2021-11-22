
// ==========================================_DATE:-4/08_==========================================
public class l004QueenSeries {
    // =============================================================================================================================================================================
    // ==========================_1D_Queens_Set_==========================
    // ===================================================================
    // HINT
    public static int queenCombination_hint(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            count += queenCombination_hint(arr, tar - arr[i], i + 1, ans + i + " ");
        }
        return count;
    }

    // =============================================================================================================================================================================
    // ==========================_COMBINATION_==========================
    // =================================================================

    // tBoxes -> total boxes, tQueens - > total queens, qpsf -> queen placed so far,
    // bn-> box no
    public static int queenCombination(int tBoxes, int tQueens, int qpsf, int bn, String ans) {
        if (qpsf == tQueens) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tBoxes; i++) {
            count += queenCombination(tBoxes, tQueens, qpsf + 1, i + 1, ans + "b" + i + "->" + "q" + qpsf + "  ");
        }
        return count;
    }

    // =============================================================================================================================================================================
    // ==========================_PERMUTATION_==========================
    // =================================================================
    public static int queenPermutation(int tBoxes, int tQueens, int qpsf, int bn, String ans, boolean[] visited) {
        if (qpsf == tQueens) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tBoxes; i++) {
            if (!visited[i]) {
                visited[i] = true;
                count += queenPermutation(tBoxes, tQueens, qpsf + 1, 0, ans + "b" + i + "->" + "q" + qpsf + "  ",
                        visited);
                visited[i] = false;
            }
        }

        return count;
    }

    // =============================================================================================================================================================================
    // ==========================_SubSequence_Mathod_==========================
    // ========================================================================
    public static int queenCombination_subseq(int tBoxes, int tQueens, int qpsf, int bn, String ans) {
        if (qpsf == tQueens || bn == tBoxes) {
            if (qpsf == tQueens) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (bn + 1 <= tBoxes) {
            count += queenCombination_subseq(tBoxes, tQueens, qpsf + 1, bn + 1, ans + "b" + bn + "q" + qpsf + " ");
        }

        count += queenCombination_subseq(tBoxes, tQueens, qpsf, bn + 1, ans);

        return count;
    }

    public static int queenpermutation_subseq(int tBoxes, int tQueens, int qpsf, int bn, String ans,
            boolean[] visited) {
        if (qpsf == tQueens || bn == tBoxes) {
            if (qpsf == tQueens) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (bn + 1 <= tBoxes) {
            if (!visited[bn]) {
                visited[bn] = true;
                count += queenCombination_subseq(tBoxes, tQueens, qpsf + 1, 0, ans + "b" + bn + "q" + qpsf + " ");
                visited[bn] = false;
            }
        }

        count += queenCombination_subseq(tBoxes, tQueens, qpsf, bn + 1, ans);

        return count;
    }


    // =============================================================================================================================================================================
    // ========================================_2D_Queens_Set_========================================
    // ===============================================================================================
    
    // ==========================_COMBINATION_==========================
    // =================================================================
    // tBoxes -> total boxes, tQueens - > total queens, bn-> box no
    public static int queenCombination2d(boolean[][] tBoxes, int tQueens, int bn, String ans) {
        if (tQueens == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = tBoxes.length, m = tBoxes[0].length, count = 0;
        for (int i = bn; i < n * m; i++) {
            int r = i / m, c = i % m;
            count += queenCombination2d(tBoxes, tQueens - 1, i + 1, ans + "(" + r + "," + c + ")" + "  ");
        }
        return count;
    }

    // ==========================_PERMUTATION_==========================
    // =================================================================
    public static int queenPermutation2d(boolean[][] tBoxes, int tQueens, int bn, String ans) {
        if (tQueens == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = tBoxes.length, m = tBoxes[0].length, count = 0;
        for (int i = bn; i < n * m; i++) {
            int r = i / m, c = i % m;
            if (!tBoxes[r][c]) {
                tBoxes[r][c] = true;
                count += queenPermutation2d(tBoxes, tQueens - 1, 0, ans + "(" + r + "," + c + ")" + "  ");
                tBoxes[r][c] = false;
            }
        }

        return count;
    }

    
    // =============================================================================================================================================================================
    // ==========================_SubSequence_Mathod_==========================
    // ========================================================================
    public static int queenCombination2d_subseq(int[][] tBoxes, int tQueens, int qpsf, int bn, String ans) {
        int n = tBoxes.length, m = tBoxes[0].length, count = 0;
        if (qpsf == tQueens || bn == n * m) {
            if (qpsf == tQueens) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int r = bn / m, c = bn % m;
        count += queenCombination2d_subseq(tBoxes, tQueens, qpsf + 1, bn + 1, ans + "(" + r + "," + c + ")" + "  ");

        count += queenCombination2d_subseq(tBoxes, tQueens, qpsf, bn + 1, ans);

        return count;
    }

    public static int queenpermutation2d_subseq(int[][] tBoxes, int tQueens, int qpsf, int bn, String ans,
            boolean[][] visited) {
        int n = tBoxes.length, m = tBoxes[0].length, count = 0;
        if (qpsf == tQueens || bn == n * m) {
            if (qpsf == tQueens) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int r = bn / m, c = bn % m;
        if (!visited[r][c]) {
            visited[r][c] = true;
            count += queenpermutation2d_subseq(tBoxes, tQueens, qpsf + 1, 0, ans + "(" + r + "," + c + ")" + "  ",
                    visited);
            visited[r][c] = false;
        }

        count += queenpermutation2d_subseq(tBoxes, tQueens, qpsf, bn + 1, ans, visited);

        return count;
    }

    public static void queenSet() {
        // int[] arr = { 1, 1, 1, 1, 1, 1 };
        // System.out.println(queenCombination_hint(arr, 4, 0, ""));
        // System.out.println(queenCombination(6, 4, 0, 0, ""));
        // System.out.println(queenPermutation(6, 4, 0, 0, "", new boolean[6]));
        // System.out.println(queenCombination_subseq(6, 4, 0, 0, ""));
        // System.out.println(queenPermutation(6, 4, 0, 0, "", new boolean[6]));

        int[][] arr = new int[4][4];
        // for (int[] a : arr)
        // Arrays.fill(a, 1);
        // System.out.println(queenCombination2D_hint(arr, 4, 0, ""));
        // System.out.println(queenCombination2d(arr, 4, 0, 0, ""));
        // System.out.println(queenPermutation2d(arr, 4, 0, 0, "", new boolean[4][4]));

        // System.out.println(queenCombination2d_subseq(arr, 4, 0, 0, ""));
        System.out.println(queenpermutation2d_subseq(arr, 4, 0, 0, "", new boolean[4][4]));

    }

    public static void main(String[] args) {
        queenSet();
    }
}