public class l007SudokuSolver {
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
}