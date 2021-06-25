import java.util.Scanner;

public class l002DTAB {
    public static Scanner scn = new Scanner(System.in);

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

    public static void main(String[] args) {
        int n = scn.nextInt();
        int b = scn.nextInt();
        System.out.println(decimalToAnyBase(n,b));
    }
}