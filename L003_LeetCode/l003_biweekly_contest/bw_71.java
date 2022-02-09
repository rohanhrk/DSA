public class bw_71 {
    // ==========================================================================================================================================================
    // Question_1 : 2160. Minimum Sum of Four Digit Number After Splitting Digits
    // https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/
    public int minimumSum(int num) {
        int[] num_arr = new int[4];
        int idx = 0;
        while(num != 0) {
            num_arr[idx++] = num % 10;
            num = num / 10;
        }
        
        Arrays.sort(num_arr); // sort in ascending order
        
        int new1 = num_arr[0] * 10 + num_arr[2]; // first and third digit
        int new2 = num_arr[1] * 10 + num_arr[3]; // second and fourth digit
        
        return new1 + new2;
    }

    // ==========================================================================================================================================================
    // Question_2 : 
}