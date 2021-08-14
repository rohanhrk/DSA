import java.util.Scanner;

public class l001 {
    public static Scanner scn = new Scanner(System.in);
    
    // **************************************************************** Date-12/06 ****************************************************************
    public static void printHello() {                                                                                
        System.out.println("hello there!");
        System.out.println("This is Rohan Hazarika");

    }

    public static void workWithDataType() {
        int a = 10;
        int b = 20;
        System.out.println("value of A: " + a);
        System.out.println("value of B: " + b);
    }

    public static void printZ() {
        System.out.println("*****");
        System.out.println("   *");
        System.out.println("  *");
        System.out.println(" *");
        System.out.println("*****");
    }

    public static void gradingSystem(int marks) {
        if (marks > 90) {
            System.out.println("excellent");
        } else if (marks > 80 && marks <= 90) {
            System.out.println("good");
        } else if (marks > 70 && marks <= 80) {
            System.out.println("fair");
        } else if (marks > 60 && marks <= 70) {
            System.out.println("meets expectstions");
        } else {
            System.out.println("below par");
        }
    }

    public static void oddEven(int n) {
        if (n % 2 == 0) {
            System.out.println("even");
        } else {
            System.out.println("odd");
        }
    }

    public static boolean isPrime_(int n) {
        boolean prime = true;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }

    public static void isPrime() {
        int n;
        n = scn.nextInt();

        boolean ans = isPrime_(n);
        if (ans) {
            System.out.println("prime");
        } else {
            System.out.println("not prime");
        }
    }

    public static void printAllPrime(int a, int b) {
        for (int i = a; i <= b; i++) {
            boolean ans = isPrime_(i);
            if (ans) {
                System.out.println(i);
            }
        }
    }
   
    // **************************************************************** date - 13/06 ****************************************************************
    public static void printFibbonacciNumbersTillN(int n) {                                                                      //
        int a = 0;
        int b = 1;
        int c = 0;

        for (int i = 0; i < n; i++) {
            c = a + b;
            System.out.println(a);

            a = b;
            b = c;
        }
    }

    public static int countDigit_(int n) {
        int count = 0;
        int temp = n;
        while (temp != 0) {
            temp = temp / 10;
            count++;
        }

        return count;
    }

    public static void countDigit(int n) {
        ;

        int count = countDigit_(n);
        System.out.println(count);
    }

    public static void DigtOfANum(int n) {
        int count = countDigit_(n);
        int power = count - 1;
        int divisor = (int) Math.pow(10, power);
        while (divisor != 0) {
            int digit = n / divisor;
            System.out.println(digit);

            n = n % divisor;
            divisor = divisor / 10;
        }
    }

    public static void reverseNumber(int n) {
        int temp = n;

        while (temp != 0) {
            int quotient = temp / 10;
            int reminder = temp % 10;

            System.out.println(reminder);

            temp = quotient;
        }
    }

    // **************************************************************** date- 18/06 ****************************************************************
    public static int GCD(int a, int b) {                                                                                     
        int divisor = a;
        int dividend = b;
        int reminder;
        while (dividend % divisor != 0) {
            reminder = dividend % divisor;

            dividend = divisor;
            divisor = reminder;
        }

        return divisor;
    }

    public static int LCM(int a, int b, int gcd) {
        return (a / gcd) * b;
    }
     
    public static boolean PythagoreanTriplet(int a, int b, int c) {
        if((a*a + b*b == c*c) || (b*b + c*c == a*a) || (c*c + a*a == b*b) ) {
            return true;
        }
    
        return false;
    }
    public static void BenjaminBulbs(int n) {
        for (int i = 1; i * i <= n; i++) {
            System.out.println(i * i);
        }
    }

    public static void main(String[] args) {
        // printHello();
        // workWithDataType();
        // printZ();
        int a = scn.nextInt();
        int b = scn.nextInt();
        System.out.println(GCD(a, b));
        // gradingSystem(n);
        // oddEven(n);
    }
}