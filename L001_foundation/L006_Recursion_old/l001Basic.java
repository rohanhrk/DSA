// import java.util.Scanner;

public class l001Basic {

    // ====================================================================================================================================================================
    // Question_1 : Print Decreasing
    // =============================
    public static void printDecreasing(int n) {
        if (n == 0)
            return;

        System.out.println("Hi" + n);
        printDecreasing(n - 1);
        System.out.println("Bye" + n);
    }

    public static void fun(int n) {
        if (n == 0) {
            System.out.println("i have to stop" + n);
            return;
        }
        for (int i = 0; i < 3; i++) {
            System.out.print(n + "@" + i + " ");
        }
        System.out.println();

        if (n % 2 == 0)
            System.out.println("heading toward child function" + n);
        fun(n - 1);
        if (n % 2 != 0)
            System.out.println("Back to child function" + n);
    }

    // ====================================================================================================================================================================
    // Question_2 : print increasing
    // =============================
    public static void printIncreasing(int n) {
        if (n == 0)
            return;
        printIncreasing(n - 1);
        System.out.println(n);
    }

    // ====================================================================================================================================================================
    // Question_3 : print decreasing increasing
    // ========================================
    public static void printDecresingIncreasing(int n) {
        if (n == 0)
            return;
        System.out.println(n);
        printDecresingIncreasing(n - 1);
        System.out.println(n);
    }

    // ====================================================================================================================================================================
    // Question_4 : print factorial
    // ============================
    public static int printFactorial(int n) {
        if (n == 0)
            return 1;
        int ans = n * printFactorial(n - 1);
        return ans;
    }

    // ====================================================================================================================================================================
    // Question_5 : power linear
    // =========================
    public static int powerLinear(int x, int n) {
        if (n == 0)
            return 1;
        int ans = x * powerLinear(x, n - 1);
        return ans;
    }

    public static int powerLinear1(int x, int n) {
        return n == 0 ? 1 : powerLinear1(x, n - 1) * x;
    }

    // ====================================================================================================================================================================
    // Question_6 : power logarithmic
    // ==============================
    public static int powerLogarithmic(int x, int n) {
        if (n == 0)
            return 1;

        int ans = powerLogarithmic(x, n / 2);
        ans *= ans;

        if (n % 2 == 0)
            return ans;
        else
            return ans * x;
    }

    // ====================================================================================================================================================================
    // Question_7 : zig zag
    // ====================
    public static void printZigZag(int n) {
        if (n == 0)
            return;

        System.out.print(n + " "); // pre region
        
        printZigZag(n - 1);
        System.out.print(n + " "); // In region
        printZigZag(n - 1); 
      
        System.out.print(n + " "); // post region
    }

    public static void main(String[] args) {

    }
}