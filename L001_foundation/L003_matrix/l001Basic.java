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
     

    // Question========================================================================================  
    public static void waveTraversal(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;

        for(int j = 0 ; j < col; j++) {
            if(j % 2 == 0) {
                for(int i = 0; i < row; i++) {
                    System.out.println(arr[i][j]);
                }
            }else {
                for(int i = row - 1 ; i >= 0 ; i--) {
                    System.out.println(arr[i][j]);
                }
            }
        }
    }

    public static void waveTraversal2(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;

        for(int i = 0 ; i < row; i++) {
            if(i % 2 == 0) {
                for(int j = 0; j < col; j++) {
                    System.out.println(arr[i][j]);
                }
            }else {
                for(int j = col - 1 ; j >= 0 ; j--) {
                    System.out.println(arr[i][j]);
                }
            }
        }
    }
    
    public static void exiPointsOfMatrix(int[][] arr) {
        int dir = 0; //west
        int i = 0;
        int j = 0;

        while(i < arr.length && i >= 0 && j < arr[0].length && j >= 0) {
            dir = (dir + arr[i][j]) % 4; //direction
            if(dir == 0) {
                j++;
            }else if(dir == 1) {
                i++;
            }else if(dir == 2) {
                j--;
            } else if(dir == 3) {
                i--;
            }
            
        }
        
        // check for valid index
        if(i < 0) {
            i++;
        } else if(i >= arr.length ) {
            i--;
        } else if( j < 0) {
            j++;
        } else {
            j--;
        }

        System.out.println(i);
        System.out.println(j);;
    }

    // diagonal traversal
    public static void printAllDiagonals(int[][] arr) {
        for(int gap = 0; gap < arr[0].length; gap++ ) {
            for(int i = 0, j = gap ; i < arr.length && j < arr[0].length; i++, j++) {
                System.out.println(arr[i][j]);
            }
        }
    }

    // saddle point
    
    public static void saddlePoint(int[][] arr) {
        for(int r = 0 ; r < arr.length; r++) {
            int minElem = (int)1e8;
            int c =0 ;
            
            // find minimum elem in a row
            for(int j = 0; j < arr[0].length; j++) {
                if(minElem > arr[r][j]) {
                    minElem = arr[r][j];
                    c = j;
                }
            }
               
            boolean isSaddle = true;
            for(int i = 0; i < arr.length; i++) {
                if(arr[i][c] > minElem) {
                    isSaddle = false;
                    break;
                }
            }
            
            if(isSaddle == true) {
                System.out.println(minElem);
                return;
            }
        }

        System.out.println("Invalid input"); 
        
    }
    
    // search In a sorted 2d Array 
    public static void searchInASorted2dArray(int[][] arr, int d) {
        int i = 0;
        int j = arr[0].length-1;

        while(true) {
            if(arr[i][j] < d) {
                i++;
                if(i == arr.length) {
                    System.out.println("Not Found");
                    break;
                }
            } else if(arr[i][j] > d) {
                j--; 
                if(j < 0 ) {
                    System.out.println("Not Found");
                    break;
                }
            } else if(arr[i][j] == d) {
               System.out.println(i);
               System.out.println(j);
               return;
           }
        }
    }

    // rotate 90 degree ===================================
    public static void transpose(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j <= i; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }

    public static void reverseCol(int[][] arr) {
        int c1 = 0;
        int c2 = arr[0].length-1;

        while(c1 < c2) {
            for(int r = 0; r < arr.length; r++) {
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
        for(int i = 0; i < arr.length; i++ ) {
            for(int j = 0; j < arr[0].length ; j++) {
                System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }
    }

    // spiral display
    public static void spiralDisplay(int[][] arr) {
        int minR = 0;
        int minC = 0;
        int maxR = arr.length - 1;
        int maxC = arr[0].length - 1;
        int count = 0;
        int totalElem = arr.length * arr[0].length;

        while(count <= totalElem ) {
            // left wall
            for(int r = minR, c = minC; r <= maxR; r++) {
                count++;
                if(count <= totalElem)
                    System.out.println(arr[r][c]);
            }
            minC++;

            // bottom wall
            for(int r = maxR, c = minC ; c <= maxC; c++) {
                count++;
                if(count <= totalElem)
                    System.out.println(arr[r][c]);
            }
            maxR--;

            // right wall
            for(int r = maxR, c = maxC; r >= minR; r--) {
                count++;
                if(count <= totalElem)
                    System.out.println(arr[r][c]);
            }
            maxC--;

            // top wall
            for(int r = minR, c = maxC ; c >= minC; c--) {
                count++;
                if(count <= totalElem)
                    System.out.println(arr[r][c]);
            }
            minR++;
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
        spiralDisplay(arr);
    }
}