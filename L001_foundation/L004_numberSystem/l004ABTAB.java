import java.util.Scanner;

public class l004ABTAB {
    public static Scanner scn = new Scanner(System.in);

    // any base to decimal
    public static int anyBaseToDecimal(int n, int b) {
        int res = 0;
        int pow = 1;

        while(n != 0) {
            int rem = n % 10;
            n /= 10;

            res += rem * pow;
            pow *= b;
        }

        return res;

    }

    //  decimal to any base
    public static int decimalToAnyBase(int n, int b) {
        int res = 0;
        int pow = 1;

        while(n != 0) {
            int rem = n % b;
            n /= b;

            res += rem * pow;
            pow *= 10;
        }

        return res;

    }

    // any base to any base
    public static int anyBaseToAnyBase(int n, int b1, int b2) {
        int baseToDec = anyBaseToDecimal(n,b1);
        int decToBase = decimalToAnyBase(baseToDec, b2);
        return decToBase;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int b1 = scn.nextInt();
        int b2 = scn.nextInt();
        System.out.println(anyBaseToAnyBase(n,b1, b2));
    }
}