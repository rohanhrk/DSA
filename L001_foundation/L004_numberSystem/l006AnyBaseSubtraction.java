import java.util.Scanner;

public class l006AnyBaseSubtraction {
    public static Scanner scn = new Scanner (System.in);
    public static int anyBaseSubtraction(int n1, int n2 , int b) {
        int borrow = 0, diff = 0, pow = 1, res = 0;

        while(n1!=0 || n2 !=0 || borrow != 0) {
            diff = borrow;

            int rem1 = n1 % 10;
            n1 /= 10;
            diff += rem1;

            int rem2 = n2 % 10;
            n2 /= 10;
            diff -= rem2;

            if(diff < 0) {
                diff += b;
                borrow = -1;
            } else  {
                borrow = 0;
            }

            res += diff*pow;
            pow *= 10;
        }

        return res;
    }
    public static void main(String[] args) {
        int n1 = scn.nextInt();
        int n2 = scn.nextInt();
        int b = scn.nextInt();

        int res = anyBaseSubtraction(n1, n2, b);
        System.out.println(res); 
    } 
}