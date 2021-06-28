import java.util.Scanner;

public class l001Basic {
    public static void printDecreasing(int n) {
        if(n == 0) return;

        System.out.println("Hi" + n);
        printDecreasing(n-1);
        System.out.println("Bye" + n);
    }

    public static void fun(int n) {
        if( n == 0) {
            System.out.println("i have to stop" + n);
            return;
        }
        for(int i = 0; i < 3; i++) {
            System.out.print(n + "@" + i+ " ");
        }
        System.out.println();

        if(n%2 == 0) System.out.println("heading toward child function" + n);
        fun(n-1);
        if(n % 2 != 0) System.out.println("Back to child function" + n);
    } 

    // print increasing
    public static void printIncreasing(int n) {
        if(n == 0) return;
        printIncreasing(n-1);
        System.out.println(n);
    }

    // print decreasing increasing
    public static void printDecresingIncreasing(int n) {
        if(n == 0) return ;
        System.out.println(n);
        printDecresingIncreasing(n-1);
        System.out.println(n);
    }

    // print factorial
    public static int printFactorial(int n) {
        if(n == 0) return 1;
        int ans = n * printFactorial(n-1);
        return ans;
    }

    // power linear
    public static int powerLinear(int x, int n) {
        if(n == 0) return 1;
        int ans = x * powerLinear(x,n-1);
        return ans;
    }

    public static int powerLinear1(int x, int n) {
        return n == 0 ? 1 : powerLinear1(x,n-1) * x;
    }

    // power logarithmic
    public static int powerLogarithmic(int x, int n) {
        if(n == 0) return 1;
        
        int ans = powerLogarithmic(x , n/2);
        ans *= ans;

        if(n % 2 == 0) return ans;
        else return ans * x; 
    }

    // zig zag
    public static void printZigZag(int n) {
        if(n == 0) return;

        System.out.print(n + " ");    //pre region
        printZigZag(n-1);
        
        System.out.print(n + " ");    // In region
        
        printZigZag(n-1);             //post region
        System.out.print(n + " ");
    }

    
    public static void main(String[] args) {
         
    }
}