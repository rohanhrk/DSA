import java.util.Scanner;

public class l001 {
    public static Scanner scn = new Scanner(System.in);
    
    // ===================================================================================================================================================
    // How to Print
    public static void printHello() {                                                                                
        System.out.println("hello there!");
        System.out.println("This is Rohan Hazarika");

    }

    // ===================================================================================================================================================
    // data type
    public static void workWithDataType() {
        int val = 10;
        String name = "Rohan Hazarika";
        Char ch = 'a';

        System.out.println("Integer value is " + val);
        System.out.println("String value is " + name);
        System.out.println("Character value is " + ch);
    }

    // ===================================================================================================================================================
    // Question_1 : print z
    public static void printZ() {
        System.out.println("*****");
        System.out.println("   *");
        System.out.println("  *");
        System.out.println(" *");
        System.out.println("*****");
    }

    // ===================================================================================================================================================
    // Question_2 : grading syztem
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

    // ===================================================================================================================================================
    // Question_3 : check value is odd or even
    public static void oddEven(int n) {
        if (n % 2 == 0) {
            System.out.println("even");
        } else {
            System.out.println("odd");
        }
    }

    // ===================================================================================================================================================
    // Question_4 : whether a number is prime or not
    public static boolean isPrime_(int n){
        boolean prime = true; // mark given value as a prime
        for(int i = 2; i * i <= n; i++){
            /*
                check whether given number is completely divisible by 'i' or not
                if true => mark prime as false (means n is not prime) and break the loop
            */ 
            if(n % i == 0){
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

    // ===================================================================================================================================================
    // Question_5 : print all primes between given range
    public static void printAllPrime(int a, int b) {
        for (int i = a; i <= b; i++) {
            boolean ans = isPrime_(i);
            if (ans) {
                System.out.println(i);
            }
        }
    }
   
    // ===================================================================================================================================================
    // Question_6 : fibbonacci series
    // 8 => 0 1 1 2 3 5 8 13
    public static void printFibbonacciNumbersTillN(int n) {                                                                      
        int a = 0; // first number
        int b = 1; // second number
        int c = 0; // store summation of a and b

        for (int i = 0; i < n; i++) {
            c = a + b; // find sum
            System.out.println(a); // print a

            // update a and b for next answer
            a = b;
            b = c;
        }
    }

    // ===================================================================================================================================================
    // Question_7 : count digit
    public static int countDigit_(int n) {
        int count = 0; // store count of digit
        int temp = n;
        while (temp != 0) {
            temp = temp / 10;
            count++;
        }

        return count;
    }
    public static void countDigit(int n) {
        int count = countDigit_(n);
        System.out.println(count);
    }

    // ===================================================================================================================================================
    // Question_8 : digit of numbers
    // 1234 => 1 2 3 4
    public static void DigtOfANum(int n) {
        int count = countDigit_(n); // find count of digit
        int power = count - 1;
        int divisor = (int) Math.pow(10, power);
        while (divisor != 0) {
            int digit = n / divisor;
            System.out.println(digit);

            // update n and divisor
            n = n % divisor;
            divisor = divisor / 10;
        }
    }

    // ===================================================================================================================================================
    // Question_9 : reverse a number
    // 1234 -> 4321
    public static void reverseNumber(int n) {
        int temp = n;

        while (temp != 0) {
            int quotient = temp / 10;
            int reminder = temp % 10;

            System.out.println(reminder);

            temp = quotient;
        }
    }

    // ===================================================================================================================================================
    // Question_10 : Find GCD
    // GCD => greatest common divisor
    public static int GCD(int a, int b) {                                                                                     
        int divisor = a;
        int dividend = b;
        int reminder = 0;
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
     
    // ===================================================================================================================================================
    // Question_11 : pythagorean triplet
    public static boolean PythagoreanTriplet(int a, int b, int c) {
        if((a*a + b*b == c*c) || (b*b + c*c == a*a) || (c*c + a*a == b*b) ) {
            return true;
        }
    
        return false;
    }

    // ===================================================================================================================================================
    // Question_12 : Benjamin bulbs
    public static void BenjaminBulbs(int n) {
        for (int i = 1; i * i <= n; i++) {
            System.out.println(i * i);
        }
    }

    public static void main(String[] args) {
        // printHello();
        // workWithDataType();
        // printZ();
        // int a = scn.nextInt();
        // int b = scn.nextInt();
        // System.out.println(GCD(a, b));
        // gradingSystem(n);
        // oddEven(n);
        main();
    }

    public static void main() {
        int a = 300, b = 200, c = 0;
        if(!a >= 200) {
            b = 300;
        }
        c = 400;

        System.out.println(b + " " + c);
    }
}