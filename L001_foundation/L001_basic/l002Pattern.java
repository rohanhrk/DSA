import java.util.Scanner;

public class l002Pattern {
    public static Scanner scn = new Scanner(System.in);
    
    //  **************************************************************** Date - 18/06 ****************************************************************
    public static void printSquare(int row) {
        int nst = row; // no. of star

        for (int r = 1; r <= row; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }

            System.out.println();
        }
    }

    public static void printTriangle(int row) {
        int nst = 1; // no. of star

        for (int r = 1; r <= row; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }
            nst++;
            System.out.println();
        }
    }

    public static void pattern2(int row) {
        int nsp = 0;
        int nst = row;

        for (int r = 1; r <= row; r++) {
            // star
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }

            // space
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            nsp++;
            nst--;
            System.out.println();
        }
    }

    public static void pattern3(int row) {
        int nst = 1;
        int nsp = row - 1;

        for (int r = 1; r <= row; r++) {

            // space
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            // star
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }

            nst++;
            ;
            nsp--;
            System.out.println();
        }
    }

    public static void pattern5(int row) {
        int nst = 1;
        int nsp = row / 2;

        for (int r = 1; r <= row; r++) {

            // space
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            // star
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }

            if (r <= row / 2) {
                nsp--;
                nst += 2;
            } else {
                nsp++;
                nst -= 2;
            }
            System.out.println();
        }
    }

    public static void printMirrorInvertedTriangle(int row) {
        int nsp = 0;
        int nst = row;

        for (int r = 1; r <= row; r++) {
            // space
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            // star
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }
            nsp++;
            nst--;
            System.out.println();
        }
    }
   
    // **************************************************************** Date - 20/06 ****************************************************************
    public static void pattern6(int row) {
        int nsp = 1;
        int nst = (row + 1) / 2;

        for (int r = 1; r <= row; r++) {

            // star
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }

            // space
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            // star
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }

            if (r <= row / 2) {
                nst--;
                nsp += 2;
            } else {
                nst++;
                nsp -= 2;
            }
            System.out.println();
        }
    }

    public static void pattern15_mathod1(int row) {
        int nsp = row / 2;
        int nst = 1;

        int rCount = 1;
        int cCount;
        for (int r = 1; r <= row; r++) {
            // space
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            // star
            cCount = rCount;
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(cCount + "\t");

                if (r > 1 && r < row) {
                    if (cst <= nst / 2) {
                        cCount++;
                    } else {
                        cCount--;
                    }
                }

            }

            // change
            if (r <= row / 2) {
                nst += 2;
                nsp--;
                rCount++;
            } else {
                nst -= 2;
                nsp++;
                rCount--;
            }
            System.out.println();
        }
    }

    public static void printPattern15_mathod2(int row) {
        int nsp = row / 2;
        int nst = 1;

        for (int r = 1; r <= row; r++) {
            // space
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            // star
            int count = r;
            if (r > (row / 2) + 1) {
                count = row - r + 1;
            }
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(count + "\t");

                if (cst <= nst / 2) {
                    count++;
                } else {
                    count--;
                }

            }

            // change
            if (r <= row / 2) {
                nst += 2;
                nsp--;
            } else {
                nst -= 2;
                nsp++;
            }
            System.out.println();
        }
    }

    public static void printPattern7(int row) {
        int nsp = 0;

        for (int r = 1; r <= row; r++) {
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }
            System.out.print("*\t");
            nsp++;
            System.out.println();
        }
    }

    public static void printPattern7_mathod2(int row) {
        for (int r = 1; r <= row; r++) {
            for (int c = 1; c <= row; c++) {
                if (r == c) {
                    System.out.print("*\t");
                } else {
                    System.out.print("\t");
                }
            }

            System.out.println();
        }
    }

    public static void printPAttern8(int row) {
        int nsp = row - 1;

        for (int r = 1; r <= row; r++) {
            // space
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            // star
            System.out.print("*\t");

            nsp--;
            System.out.println();
        }
    }

    public static void pattern8_mathod2(int row) {
        for (int r = 1; r <= row; r++) {
            for (int c = 1; c <= row; c++) {
                if (r + c == row + 1) {
                    System.out.print("*\t");
                } else {
                    System.out.print("\t");
                }
            }

            System.out.println();
        }
    }

    public static void pattern9(int row) {
        for (int r = 1; r <= row; r++) {
            for (int c = 1; c <= row; c++) {
                if (r == c || r + c == row + 1) {
                    System.out.print("*\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public static void pattern10(int row) {
        int outerNSP = row / 2; // outer No. of space
        int innerNSP = -1; // inner No. Of Space

        for (int r = 1; r <= row; r++) {
            // space
            for (int csp = 1; csp <= outerNSP; csp++) {
                System.out.print("\t");
            }

            // star
            System.out.print("*\t");

            // space
            for (int csp = 1; csp <= innerNSP; csp++) {
                System.out.print("\t");
            }

            // star
            if (innerNSP != -1) {
                System.out.print("*\t");
            }

            // change
            if (r <= row / 2) {
                outerNSP--;
                innerNSP += 2;
            } else {
                outerNSP++;
                innerNSP -= 2;
            }

            // enter
            System.out.println();
        }
    }

    public static void pattern11(int row) {
        int nst = 1;
        int count = 1;
        for (int r = 1; r <= row; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(count + "\t");
                count++;
            }

            nst++;

            System.out.println();
        }
    }

    public static void pattern12(int row) {
        int nst = 1;
        int a = 0;
        int b = 1;
        int c;
        for (int r = 1; r <= row; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(a + "\t");
                c = a + b;
                a = b;
                b = c;
            }

            nst++;

            System.out.println();
        }
    }

    public static void pattern13(int row) {

        for (int n = 0; n < row; n++) {
            int val = 1;
            for (int r = 0; r <= n; r++) {
                System.out.print(val + "\t");
                val = ((n - r) * val) / (r + 1);
            }

            System.out.println();
        }
    }

    public static void pattern14(int num) {

        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " * " + i + " = " + num * i);
        }
    }

    public static void pattern16(int row) {
        int nst = 1;
        int nsp = 2 * row - 3;

        for (int r = 1; r <= row; r++) {

            // int count = 1;
            // star
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(cst + "\t");
            }

            // space
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("\t");
            }

            if (nsp == -1) {
                nst--;
            }
            // star
            for (int cst = nst; cst >= 1; cst--) {
                System.out.print(cst + "\t");
            }

            nst++;
            nsp -= 2;
            System.out.println();
        }

    }
    // **************************************************************** HOMEWORK ****************************************************************
    public static void pattern17(int row) {
        int nst = 1;
        int nsp = row / 2;

        for (int r = 1; r <= row; r++) {
            // space
            for (int csp = 1; csp <= nsp; csp++) {
                if (r <= row / 2 || r > (row + 2) / 2) {
                    System.out.print("\t");
                } else {
                    System.out.print("*\t");
                }
            }

            // star
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }

            // change
            if (r <= row / 2) {
                nst++;
                nsp = row / 2;
            } else {
                nst--;
                nsp = row / 2;
            }

            System.out.println();
        }
    }

    public static void pattern18(int row) {
        int nsp1 = 0;
        int nsp2 = row - 2;

        for (int r = 1; r <= row; r++) {
            // space
            for (int csp = 1; csp <= nsp1; csp++) {
                System.out.print("\t");
            }

            // star
            System.out.print("*\t");

            // space
            for (int csp = 1; csp <= nsp2; csp++) {
                if (r > 1 && r <= row / 2) {
                    System.out.print("\t");
                } else {
                    System.out.print("*\t");
                }

            }

            // star
            if (nsp2 != -1) {
                System.out.print("*\t");
            }

            // change
            if (r <= row / 2) {
                nsp1++;
                nsp2 -= 2;
            } else {
                nsp1--;
                nsp2 += 2;
            }

            System.out.println();
        }
    }

    public static void pattern19(int row) {
        for (int r = 1; r <= row; r++) {
            for (int c = 1; c <= row; c++) {
                if (r == 1) {
                    if (c <= (row + 1) / 2 || c == row) {
                        System.out.print("*\t");
                    } else {
                        System.out.print("\t");
                    }
                } else if (r == (row + 1) / 2) {
                    System.out.print("*\t");
                } else if (r == row) {
                    if (c == 1 || c >= (row + 1) / 2) {
                        System.out.print("*\t");
                    } else {
                        System.out.print("\t");
                    }
                } else if (c == 1) {
                    if (r == 1 || r >= (row + 1) / 2) {
                        System.out.print("*\t");
                    } else {
                        System.out.print("\t");
                    }
                } else if (c == (row + 1) / 2) {
                    System.out.print("*\t");
                } else if (c == row) {
                    if (r <= (row + 1) / 2 || r == row) {
                        System.out.print("*\t");
                    } else {
                        System.out.print("\t");
                    }
                } else {
                    System.out.print("\t");
                }
            }

            System.out.println();
        }
    }

    public static void pattern20(int row) {
        for (int r = 1; r <= row; r++) {
            for (int c = 1; c <= row; c++) {
                if (r == c || r + c == row + 1) {
                    if( r > 1 && r <= row / 2) {
                        System.out.print("\t");
                    } else {
                         System.out.print("*\t");
                    }
                }
                else {
                    if(c == 1 || c == row) {
                        System.out.print("*\t");
                    } else {
                        System.out.print("\t");
                    }
                    
                }
            }

            System.out.println();
        }
    }
    // ********************************************************************************************************************************
    public static void patterExtra1_1(int row) {
        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= row; j++) {
                if(i == j || j - i == 2 || i - j == 2) {
                    System.out.print("*\t");
                }else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
      
        patterExtra1_1(5);
    }
}