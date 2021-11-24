// =====================================_DATE:-7/08_=====================================
public class l005Nqueen_series {
    // =============================================================================================================================================================================
    // =======================================_N-QUEENS_=======================================
    // ========================================================================================
    public static boolean isSafeToPlaceQueen(boolean[][] boxes, int r, int c) {
        // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } }; // for combination
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } }; // for permutation

        int n = boxes.length, m = boxes[0].length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < n; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];
                if (x >= 0 && y >= 0 && x < n && y < m) {
                    if (boxes[x][y])
                        return false;
                } else
                    break;
            }
        }

        return true;
    }

    // ===================_COMBINATION_===================
    // ===================================================
    public static int nqueen_combination01(boolean[][] boxes, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = boxes.length, m = boxes[0].length, count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafeToPlaceQueen(boxes, r, c)) {
                boxes[r][c] = true;
                count += nqueen_combination01(boxes, tnq - 1, i + 1, ans + "(" + r + "," + c + ")" + " ");
                boxes[r][c] = false;
            }
        }

        return count;
    }

    // ===================_PERMUTATION_===================
    // ===================================================
    public static int nqueen_permutation01(boolean[][] boxes, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = boxes.length, m = boxes[0].length, count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!boxes[r][c] && isSafeToPlaceQueen(boxes, r, c)) {
                boxes[r][c] = true;
                count += nqueen_permutation01(boxes, tnq - 1, 0, ans + "(" + r + "," + c + ")" + " ");
                boxes[r][c] = false;
            }
        }

        return count;
    }

    // =============================================================================================================================================================================
    // ================================_subsequence_mathod_================================
    // ====================================================================================

    // ===================_COMBINATION_===================
    // ===================================================
    public static int nqueen_combination02(boolean[][] boxes, int tnq, int idx, String ans) {
        int n = boxes.length, m = boxes[0].length, count = 0;

        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int r = idx / m;
        int c = idx % m;
        if (isSafeToPlaceQueen(boxes, r, c)) {
            boxes[r][c] = true;
            count += nqueen_combination01(boxes, tnq - 1, idx + 1, ans + "(" + r + "," + c + ")" + " "); // haa ki call
            boxes[r][c] = false;
        }

        count += nqueen_combination01(boxes, tnq, idx + 1, ans); // na ki call
        return count;
    }

    // ===================_PERMUTATION_===================
    // =================================================== 
    public static int nqueen_permutation02(boolean[][] boxes, int tnq, int idx, String ans) {
        int n = boxes.length, m = boxes[0].length, count = 0;

        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int r = idx / m;
        int c = idx % m;
        if (!boxes[r][c] && isSafeToPlaceQueen(boxes, r, c)) {
            boxes[r][c] = true;
            count += nqueen_permutation01(boxes, tnq - 1, 0, ans + "(" + r + "," + c + ")" + " ");
            boxes[r][c] = false;
        }

        count += nqueen_permutation01(boxes, tnq, idx + 1, ans);
        return count;
    }

    public static void nQueens() {
        int n = 4, m = 4, q = 4;
        boolean[][] boxes = new boolean[n][m];
        // System.out.println(nqueen_combination01(boxes, q, 0, ""));
        // System.out.println(nqueen_permutation01(boxes, q, 0, ""));

        // System.out.println(nqueen_combination02(boxes, q, 0, ""));
        // System.out.println(nqueen_permutation02(boxes, q, 0, ""));

    }


    // =============================================================================================================================================================================
    // =====================================_OPTIMIZATION_1_=====================================
    // ==========================================================================================
    
    // static variables --->
    static int calls = 0;
    static boolean[] rows;
    static boolean[] cols;
    static boolean[] diag;
    static boolean[] anti_diag;

    // ===================_COMBINATION_===================
    // ===================================================
    public static int nqueen_combination03(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        calls++;
        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!rows[r] && !cols[c] && !diag[r + c] && !anti_diag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = true;
                count += nqueen_combination03(n, m, tnq - 1, i + 1, ans + "(" + r + "," + c + ")" + " ");
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    // ===================_PERMUTATION_===================
    // ===================================================
    public static int nqueen_permutationn03(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        calls++;
        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!rows[r] && !cols[c] && !diag[r + c] && !anti_diag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = true;
                count += nqueen_permutationn03(n, m, tnq - 1, 0, ans + "(" + r + "," + c + ")" + " ");
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = false;
            }
        }

        return count;
    }


    // =============================================================================================================================================================================
    // ================================_subsequence_mathod_================================
    // ====================================================================================

    // ===================_COMBINATION_===================
    // ===================================================
    public static int nqueen_combination04(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;

        int r = idx / m;
        int c = idx % m;
        if (!rows[r] && !cols[c] && !diag[r + c] && !anti_diag[r - c + m - 1]) {
            rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = true;
            count += nqueen_combination04(n, m, tnq - 1, idx + 1, ans + "(" + r + "," + c + ")" + " ");
            rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = false;
        }

        count += nqueen_combination04(n, m, tnq, idx + 1, ans);
        return count;
    }

    // ===================_PERMUTATION_===================
    // ===================================================
    public static int nqueen_permutationn04(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;

        int r = idx / m;
        int c = idx % m;
        if (!rows[r] && !cols[c] && !diag[r + c] && !anti_diag[r - c + m - 1]) {
            rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = true;
            count += nqueen_permutationn04(n, m, tnq - 1, 0, ans + "(" + r + "," + c + ")" + " ");
            rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = false;
        }

        count += nqueen_permutationn04(n, m, tnq, idx + 1, ans);
        return count;
    }

    // ====================================
    // ====================================
    public static void nQueens_optimize1() {
        int n = 4, m = 4, q = 4;
        rows = new boolean[n];
        cols = new boolean[m];
        diag = new boolean[n + m - 1];
        anti_diag = new boolean[n + m - 1];

        // System.out.println(nqueen_combination03(n, m, q, 0, ""));
        System.out.println(nqueen_permutationn03(n, m, q, 0, ""));
        System.out.println(calls);
        // System.out.println(nqueen_combination04(n, m, q, 0, ""));
        // System.out.println(nqueen_permutationn04(n, m, q, 0, ""));

    }



    // =============================================================================================================================================================================
    // =====================================_OPTIMIZATION_2_=====================================
    // ==========================================================================================
    
    // ===================_COMBINATION_===================
    // ===================================================
    public static int Nqueen04_combination_01(int floor, int tnq, int m, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        calls++;
        int count = 0;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (!cols[c] && !diag[r + c] && !anti_diag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = true;
                count += Nqueen04_combination_01(floor + 1, tnq - 1, m, ans + "(" + r + "," + c + ")" + " ");
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    // ===================_PERMUTATION_===================
    // ===================================================
    public static int Nqueen04_permutation_01(int floor, int tnq, int m, String ans) {
        if (tnq == 0 || floor >= m) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        calls++;
        int count = 0;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (!rows[r] && !cols[c] && !diag[r + c] && !anti_diag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = true;
                count += Nqueen04_permutation_01(0, tnq - 1, m, ans + "(" + r + "," + c + ")" + " ");
                rows[r] = cols[c] = diag[r + c] = anti_diag[r - c + m - 1] = false;
            }
        }
        count += Nqueen04_permutation_01(floor + 1, tnq, m, ans);
        return count;
    }

    // ====================================
    // ====================================
    public static void nQueens_optimize2() {
        int n = 4, m = 4, q = 4;
        rows = new boolean[n];
        cols = new boolean[m];
        diag = new boolean[n + m - 1];
        anti_diag = new boolean[n + m - 1];

        // System.out.println(Nqueen04_combination_01(0, q, m, ""));
        // System.out.println(calls);
        System.out.println(Nqueen04_permutation_01(0, q, m, ""));
        System.out.println(calls);

        // System.out.println(nqueen_combination04(n, m, q, 0, ""));
        // System.out.println(nqueen_permutationn04(n, m, q, 0, ""));

    }

    public static void main(String[] args) {
        nQueens_optimize1();
        nQueens_optimize2();
    }
}