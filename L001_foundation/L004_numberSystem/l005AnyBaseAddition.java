import java.util.Scanner;

public class l005AnyBaseAddition {
    public static Scanner scn = new Scanner(System.in);
    public static int anyBaseAddition (int n1, int n2, int b) {
        int sum = 0, res = 0, carry = 0, pow = 1;

        while(n1 != 0 || n2 != 0 || carry != 0) {
            sum = carry;

            int rem1 = n1 % 10;
            n1 /= 10;
            sum += rem1;

            int rem2 = n2 % 10;
            n2 /= 10;
            sum += rem2;

            if(sum >= b) {
                carry = sum / b;  
                sum = sum % b;
            } else {
                carry = 0;
            }
            
            res += sum * pow;
            pow *= 10;
        }

        return res;
    }
    public static void main(String[] args) {
        int n1 = scn.nextInt();
        int n2 = scn.nextInt();
        int b = scn.nextInt();

        System.out.println(anyBaseAddition(n1, n2, b));
    }
}