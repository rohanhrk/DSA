import java.util.Scanner;

public class l001Basic {
    public static Scanner scn = new Scanner(System.in);

    // ================================================================================================================================================================
    public static void printOutput() {
        int n = scn.nextInt(), m = scn.nextInt();
        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // ================================================================================================================================================================
    // Question_1 : wave traversal
    public static void waveTraversal(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;

        for (int j = 0; j < col; j++) {
            if (j % 2 == 0) {
                for (int i = 0; i < row; i++) {
                    System.out.println(arr[i][j]);
                }
            } else {
                for (int i = row - 1; i >= 0; i--) {
                    System.out.println(arr[i][j]);
                }
            }
        }
    }

    // ================================================================================================================================================================
    // Question_2 : wave traversal 2
    public static void waveTraversal2(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;

        for (int i = 0; i < row; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < col; j++) {
                    System.out.println(arr[i][j]);
                }
            } else {
                for (int j = col - 1; j >= 0; j--) {
                    System.out.println(arr[i][j]);
                }
            }
        }
    }

    // ================================================================================================================================================================
    // Question_3 : exit point of matrix
    // https://practice.geeksforgeeks.org/problems/exit-point-in-a-matrix0905/1

    // mathod 1 :
    public static void exiPointsOfMatrix_1(int[][] arr) {
        int dir = 0; // west
        int i = 0;
        int j = 0;

        while (i < arr.length && i >= 0 && j < arr[0].length && j >= 0) {
            dir = (dir + arr[i][j]) % 4; // direction
            if (dir == 0) {
                j++;
            } else if (dir == 1) {
                i++;
            } else if (dir == 2) {
                j--;
            } else if (dir == 3) {
                i--;
            }

        }

        // check for valid index
        if (i < 0) {
            i++;
        } else if (i >= arr.length) {
            i--;
        } else if (j < 0) {
            j++;
        } else {
            j--;
        }

        System.out.println(i);
        System.out.println(j);

    }

    // mathod 2 :
    public int[] exiPointsOfMatrix_2(int[][] matrix) {
        // code here
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int n = matrix.length, m = matrix[0].length;
        int sr = 0, sc = 0, d = 0;

        while (sr >= 0 && sr < n && sc >= 0 && sc < m) {
            if (matrix[sr][sc] == 1) {
                d = (d + 1) % 4;
                matrix[sr][sc] = 0;
            }

            sr += dir[d][0];
            sc += dir[d][1];
        }

        sr -= dir[d][0];
        sc -= dir[d][1];

        return new int[] { sr, sc };
    }

    // ================================================================================================================================================================
    // Question_4 : diagonal traversal
    public static void printAllDiagonals(int[][] arr) {
        for (int gap = 0; gap < arr[0].length; gap++) {
            for (int i = 0, j = gap; i < arr.length && j < arr[0].length; i++, j++) {
                System.out.println(arr[i][j]);
            }
        }
    }

    // ================================================================================================================================================================
    // Question_5 : saddle point
    public static void saddlePoint(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            int minElem = (int) 1e8;
            int c = 0;

            // find minimum elem in a row
            for (int j = 0; j < arr[0].length; j++) {
                if (minElem > arr[r][j]) {
                    minElem = arr[r][j];
                    c = j;
                }
            }

            boolean isSaddle = true;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i][c] > minElem) {
                    isSaddle = false;
                    break;
                }
            }

            if (isSaddle == true) {
                System.out.println(minElem);
                return;
            }
        }

        System.out.println("Invalid input");

    }

    // ================================================================================================================================================================
    // Question_6 : search In a sorted 2d Array
    public static void searchInASorted2dArray(int[][] arr, int d) {
        int i = 0;
        int j = arr[0].length - 1;

        while (true) {
            if (arr[i][j] < d) {
                i++;
                if (i == arr.length) {
                    System.out.println("Not Found");
                    break;
                }
            } else if (arr[i][j] > d) {
                j--;
                if (j < 0) {
                    System.out.println("Not Found");
                    break;
                }
            } else if (arr[i][j] == d) {
                System.out.println(i);
                System.out.println(j);
                return;
            }
        }
    }

    // ================================================================================================================================================================
    // Question_7 : rotate 90 degree 
    public static void transpose(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }

    public static void reverseCol(int[][] arr) {
        int c1 = 0;
        int c2 = arr[0].length - 1;

        while (c1 < c2) {
            for (int r = 0; r < arr.length; r++) {
                int elem1 = arr[r][c1];
                int elem2 = arr[r][c2];

                // swap each elem
                arr[r][c1] = elem2;
                arr[r][c2] = elem1;
            }

            // update
            c1++;
            c2--;
        }
    }

    public static void rotateBy90Degrees(int[][] arr) {
        // 1. transpose
        transpose(arr);

        // 2. reverse column
        reverseCol(arr);

        // print updated arr
        display(arr);

    }

    public static void display(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }
    }

    // ================================================================================================================================================================
    // Question_8 : spiral display
    public static void spiralDisplay(int[][] arr) {
        int minR = 0;
        int minC = 0;
        int maxR = arr.length - 1;
        int maxC = arr[0].length - 1;
        int count = 0;
        int totalElem = arr.length * arr[0].length;

        while (count <= totalElem) {
            // left wall
            for (int r = minR, c = minC; r <= maxR; r++) {
                count++;
                if (count <= totalElem)
                    System.out.println(arr[r][c]);
            }
            minC++;

            // bottom wall
            for (int r = maxR, c = minC; c <= maxC; c++) {
                count++;
                if (count <= totalElem)
                    System.out.println(arr[r][c]);
            }
            maxR--;

            // right wall
            for (int r = maxR, c = maxC; r >= minR; r--) {
                count++;
                if (count <= totalElem)
                    System.out.println(arr[r][c]);
            }
            maxC--;

            // top wall
            for (int r = minR, c = maxC; c >= minC; c--) {
                count++;
                if (count <= totalElem)
                    System.out.println(arr[r][c]);
            }
            minR++;
        }

    }

    // rotate shell
    public static int[] storedIn1dArrayFrom2d(int[][] arr, int s) {
        int rmin = s - 1;
        int cmin = s - 1;
        int rmax = arr.length - s;
        int cmax = arr[0].length - s;

        int size = 2 * (rmax + cmax) - 2 * (rmin + cmin);
        int[] oned = new int[size];
        int i = 0;

        // top wall
        for (int r = rmin, c = cmin; c <= cmax; c++) {
            oned[i] = arr[r][c];
            i++;

        }
        rmin++;

        // right wall
        for (int r = rmin, c = cmax; r <= rmax; r++) {
            oned[i] = arr[r][c];
            i++;

        }
        cmax--;

        // bottom wall
        for (int r = rmax, c = cmax; c >= cmin; c--) {
            oned[i] = arr[r][c];
            i++;

        }
        rmax--;

        // left wall
        for (int r = rmax, c = cmin; r >= rmin; r--) {
            oned[i] = arr[r][c];
            i++;

        }
        cmin++;

        return oned;
    }

    public static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }

    public static void rotateArrayBy_r(int[] arr, int r) {
        int n = arr.length;
        r = (r % n + n) % n;
        // reverse 0 to n-1
        reverse(arr, 0, n - 1);
        reverse(arr, 0, n - r - 1);
        reverse(arr, n - r, n - 1);
    }

    public static void fillElementIn_2dArray_From_1d(int[][] twod, int[] oned, int s) {
        int rmin = s - 1;
        int cmin = s - 1;
        int rmax = twod.length - s;
        int cmax = twod[0].length - s;

        int i = 0;

        // top wall
        for (int r = rmin, c = cmin; c <= cmax; c++) {
            twod[r][c] = oned[i];
            i++;

        }
        rmin++;

        // right wall
        for (int r = rmin, c = cmax; r <= rmax; r++) {
            twod[r][c] = oned[i];
            i++;

        }
        cmax--;

        // bottom wall
        for (int r = rmax, c = cmax; c >= cmin; c--) {
            twod[r][c] = oned[i];
            i++;

        }
        rmax--;

        // left wall
        for (int r = rmax, c = cmin; r >= rmin; r--) {
            twod[r][c] = oned[i];
            i++;

        }
        cmin++;
    }

    public static void rotateShell(int[][] arr, int s, int r) {

        // store element in 1d array
        int[] oned = storedIn1dArrayFrom2d(arr, s);

        // rotate array by r
        rotateArrayBy_r(oned, r);

        // stored element from oned array to original 2d array
        fillElementIn_2dArray_From_1d(arr, oned, s);

    }

    // ************************************************************************************************************************************************************************************************
    public static void main(String[] args) {
        // test1();
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        int r = scn.nextInt();
        rotateShell(arr, 2, r);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}