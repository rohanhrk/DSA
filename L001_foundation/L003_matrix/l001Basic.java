import java.util.Scanner;

public class l001Basic {
    public static Scanner scn = new Scanner(System.in);
    public static void printOutput() {
        int n = scn.nextInt(), m = scn.nextInt();
        int[][] arr = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m ; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m ; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void waveTraversal(int[][] arr) {
        int col = 0;
        while( col < arr[0].length) {
            if(col % 2 == 0) {
                for(int row = 0; row < arr.length; row++) {
                    System.out.println(arr[row][col]);
                }
            }else {
                for(int row = arr.length -1 ; row >= 0 ; row--) {
                    System.out.println(arr[row][col]);
                }
            }

            col++;
        }
    }
    public static void main(String[] args) {
        // test1();
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        waveTraversal(arr);
    }
}