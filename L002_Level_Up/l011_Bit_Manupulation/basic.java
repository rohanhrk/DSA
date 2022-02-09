public class basic {
    // ====================================================================================================================================
    // Question_1 : Basics Of Bit Manipulation
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/bit-manipulation/basics-of-bit-official/ojquestion
    private static void basicBit(int n, int i, int j, int k, int m) {
        // 1. on ith bit
        int on_mask = (1 << i);
        int on = n | on_mask;
        System.out.println(on);
        
        // 2. off j'th bit
        int off_mask = ~(1 << j);
        int off = n & off_mask;
        System.out.println(off);
        
        // 3. toggle k'th bit
        int toggle_mask = (1 << k);
        int toggle = n ^ toggle_mask;
        System.out.println(toggle);
        
        // 4. check if m'th bit is on or off
        int on_or_off_mask = (1 << m);
        boolean on_or_off = (n & on_or_off_mask) == 0 ? false : true;
        System.out.println(on_or_off);
    }

    // ====================================================================================================================================
    // Question_2 : Find first set bit 
    // https://practice.geeksforgeeks.org/problems/find-first-set-bit-1587115620/1/#
    public static int getFirstSetBit(int n){
        if(n == 0)
            return 0;
        // Your code here
        int rsb = (n & -n);
        String str = "" + Integer.toBinaryString(rsb);
        return str.length();
    }

    // ====================================================================================================================================
    // Question_3 : Number of 1 Bits
    // https://practice.geeksforgeeks.org/problems/set-bits0143/1/
    public static int setBits(int N) {
        // code here
        int count = 0;
        while(N != 0) {
            int rsb = (N & -N);
            N = N - rsb;
            count++;
        }
        
        return count;
    }

    // ====================================================================================================================================
    // Question_4 : Josephus Special
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/bit-manipulation/josephus-special-official/ojquestion
    public static int solution(int n){
        //write your code here
        int i = 1;
        while(i * 2 <= n) {
            i = i * 2;
        }
        
        // n = 2^x + l
        int l = n - i;
        return 2 * l + 1;
    }

}