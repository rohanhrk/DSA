import java.util.Scanner;

public class l001Basic {
    public static Scanner scn = new Scanner(System.in);
    public static int binaryToDecimal(int n) {
        int res = 0;
        int pow = 1;

        while(n != 0) {
            int rem = n % 2;
            n /= 2;

            res += rem * pow;
            pow *= 10;
        }

        return res;
    }
     

    public static void main(String[] args) {
        int n = scn.nextInt();
        // int b = scn.nextInt();
    
        System.out.println(binaryToDecimal(n));
    }
}