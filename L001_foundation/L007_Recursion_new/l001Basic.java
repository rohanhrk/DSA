import java.util.Scanner;

public class l001Basic {
    public static Scanner scn = new Scanner(System.in);

    // ====================================================================================================================================================================
    // Question_1 : fibonacci
    // ======================
    public static int fibo(int n) {
        if (n <= 1)
            return n;
        return fibo(n - 1) + fibo(n - 2);
    }

    // ====================================================================================================================================================================
    // Question_2 : tribonacci
    // =======================
    public static int tribo(int n) {
        if (n <= 2)
            return n == 2 ? 1 : n;
        return tribo(n - 1) + tribo(n - 2) + tribo(n - 3);
    }

    // ====================================================================================================================================================================
    // Question_3 : palindrome
    // =======================
    public static boolean palindrom(int[] arr, int si, int li) {
        if (si >= li) {
            return true;
        }

        if (arr[si] != arr[li]) {
            return false;
        }

        return palindrom(arr, si + 1, li - 1);
    }

    //  ====================================================================================================================================================================
    // Question_4 : reverse array
    // ==========================
    public static void reverse(int[] arr, int si, int li) {
        if (si >= li)
            return;

        int e1 = arr[si];
        int e2 = arr[li];
        arr[si] = e2;
        arr[li] = e1;

        reverse(arr, si + 1, li - 1);
    }

    // ====================================================================================================================================================================
    // Question_5 : Inverse array -> extra space use(array ka)
    // =======================================================
    public static int[] inverse_mathod1(int[] arr, int idx) {
        if (idx == arr.length) {
            int[] ans = new int[arr.length];
            return ans;
        }

        int[] ans = inverse_mathod1(arr, idx + 1);
        ans[arr[idx]] = idx;

        return ans;
    }

    // ===================================
    // rajneesh sir ka solution
    // Inverse array -> No extra space use
    // ===================================
    public static void inverse_mathod2(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }

        int val = arr[idx];
        inverse_mathod2(arr, idx + 1);
        arr[val] = idx;

    }

    // ====================================================================================================================================================================
    // Question_6 : sum of digit in string
    // ===================================
    public static int sumDigitOfAString_mathod1(String str, int idx, int sum) {
        if (idx == str.length())
            return sum;
        int num = (int) (str.charAt(idx) - '0');
        int add = sumDigitOfAString_mathod1(str, idx + 1, sum + num);

        return add;
    }

    // ========================
    // rajneesh sir ka solution
    // ========================
    public static int sumDigitOfAString_mathod2(String str, int idx) {
        if (idx == str.length())
            return 0;
        int recAns = sumDigitOfAString_mathod2(str, idx + 1);

        return (str.charAt(idx) - '0') + recAns;
    }

    // ====================================================================================================================================================================
    // Question_7 : String One Two Are Reverse
    // =======================================
    public static boolean StringOneTwoAreReverse(String s1, String s2, int idx1, int idx2) {
        if (idx1 == s1.length() && idx2 == -1)
            return true;
        if (s1.charAt(idx1) != s2.charAt(idx2))
            return false;

        return StringOneTwoAreReverse(s1, s2, idx1 + 1, idx2 - 1);
    }

    // ====================================================================================================================================================================
    // Question_8 : palindrome of string
    // =================================
    public static boolean isPalindrom1(String str, int i, int j) {
        if (i >= j) {
            return true;
        }
        char c1 = str.charAt(i);
        char c2 = str.charAt(j);
        if (c1 >= 'A' && c1 <= 'Z')
            c1 = (char) ('a' - 'A' + c1);
        if (c2 >= 'A' && c2 <= 'Z')
            c2 = (char) ('a' - 'A' + c2);

        if (c1 != c2)
            return false;
        return isPalindrom1(str, i + 1, j - 1);

    }

    // ===============
    // sir ka solution
    // ===============
    public static boolean isPalindrom2(String str, int i, int j) {
        if (i >= j) {
            return true;
        }
        char c1 = str.charAt(i);
        char c2 = str.charAt(j);
        int diff1 = (c1 >= 'A' && c1 <= 'Z') ? c1 - 'A' : c1 - 'a';
        int diff2 = (c2 >= 'A' && c2 <= 'Z') ? c2 - 'A' : c2 - 'a';

        if (diff1 != diff2)
            return false;
        return isPalindrom2(str, i + 1, j - 1);

    }

    // ====================================================================================================================================================================
    // Question_9 : seperate dublicate by "*"
    // ======================================
    public static String separateDuplicates(String str) { // niche ate hute result store karenge
        if (str.length() == 1)
            return str;

        char ch = str.charAt(0);
        String ros = str.substring(1);
        String resAns = separateDuplicates(ros);
        if (ch == resAns.charAt(0)) {
            return ch + "*" + resAns;
        } else {
            return ch + resAns;
        }

    }

    public static void separateDuplicates_wayup(String ques, int idx, String ans) { // upar jate hute result store karenge
        if (idx == ques.length() - 1) {
            System.out.println(ans + ques.charAt(idx));
            return;
        }

        char ch = ques.charAt(idx);
        if (ch == ques.charAt(idx + 1))
            separateDuplicates_wayup(ques, idx + 1, ans + ch + "*");
        else
            separateDuplicates_wayup(ques, idx + 1, ans + ch);
    }

    // ====================================================================================================================================================================
    // Question_10 : remove adjacent dublicate from string
    // ===================================================
    public static String removeAdjacentDuplicates(String str) { // niche ate hute result store karenge
        if (str.length() == 1)
            return str;
        char ch = str.charAt(0);
        String ros = str.substring(1);
        String resAns = removeAdjacentDuplicates(ros);

        if (ch != resAns.charAt(0))
            return ch + resAns;
        else
            return resAns;
    }

    public static void removeAdjacentDuplicates_wayup(String str, int idx, String ans) { // upar jate hute result store karenge
        if (idx == str.length() - 1) {
            System.out.println(ans + str.charAt(idx));
            return;
        }
        char ch = str.charAt(idx);
        if (ch == str.charAt(idx + 1))
            removeAdjacentDuplicates_wayup(str, idx + 1, ans);
        else
            removeAdjacentDuplicates_wayup(str, idx + 1, ans + ch);

    }

    // ************************************************************************************************************************************************************************************************
    public static void main(String[] args) {

    }
}