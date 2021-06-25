import java.util.Scanner;

public class l007ABMultiplication {
    public static Scanner scn = new Scanner(System.in);
    public static int multipliedWithDigit(int n1, int digit, int pow, int b) {
        int mult = 0, res = 0, carry = 0;
        while(n1 != 0 || carry != 0) {
            int rem1 =  n1 % 10;
            n1 /= 10;

            mult = rem1 * digit + carry;

            int r = mult % b;
            carry = mult / b;

            res += r*pow;
            pow *= 10;
        }
        return res;
    }
    public static int getSum (int n1, int n2, int b) {
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
    public static int anyBaseMultiplication(int b, int n1, int n2) {
        int pow = 1;
        int sum = 0;
        while(n2 != 0) {
            int rem2 = n2 % 10;
            n2 /= 10;
            int mult = multipliedWithDigit(n1,rem2, pow, b);
            pow *= 10;
            sum = getSum(mult, sum, b);
        }
        return sum;
    }
    public static void main(String[] args) {
        int b = scn.nextInt(); 
        int n1 = scn.nextInt();
        int n2 = scn.nextInt();
        

        int res = anyBaseMultiplication(b,n1,n2);
        System.out.println(res);
       
    }
}