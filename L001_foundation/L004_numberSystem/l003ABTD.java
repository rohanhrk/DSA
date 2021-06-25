import java.util.Scanner;

public class l003ABTD {
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

    public static void main(String[] args) {
        int n = scn.nextInt();
        int b = scn.nextInt();
        System.out.println(anyBaseToDecimal(n,b));
    }
}