import java.util.Scanner;

public class l001Basic {
        public static Scanner scn = new Scanner(System.in);

    public static void test1() {
        // string is a immutable means not possible to change on same address
        String str = "sdsdfsdfdgfdg"; // it stores in intern pool
        String str1 = str;  // o(1) -> pointing on same address of str

        str += 'g' ; //O(n) -> creating copy of str and then 'g' is added in last
        char ch = str.charAt(3); // get char
    }

    public static void test2() {
        String str = "";
        for(int i = 0; i < (int)1e6; i++) {  // O(n'2) -> this loop performs O(n^2) operation
            str += i;   // O(n)
        }

        System.out.println(str);
    }
    
    public static void test3() {
        // StringBuilder -> it works same as string of c++ -> character ka array
        // it is mutable
        StringBuilder sb = new StringBuilder();
        sb.append('d');    //O(1)
        StringBuilder sb1 = sb; //O(1)
        StringBuilder sb2 = new StringBuilder(sb); // O(n) -> it creates new instace of sb which pointing on new address

        // char ch = sb.toString();//O(n) // convert char array to string which perform // O(n) operation
        // System.out.println(str);
    }

    public static void test4() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < (int)1e6; i++) { // O(n)
            str.append(i); // O(1)
        }

        System.out.println(str);
    }

    // Question ======================================================
    // compression

    public static String compression1_mathod1(String str) {
        int i = 0, j = 0, count = 0;
        StringBuilder sb = new StringBuilder();

        while(j < str.length()) {
            if(count == 0) {
                sb.append(str.charAt(i));
            }
            
            char c1 = str.charAt(i);
            char c2 = str.charAt(j);

            if(c1 == c2) {
                j++;
                count++;
            } else {
                sb.append(count);
                i = j;
                count = 0;
            }
        }

        sb.append(count);

        return sb.toString();
    }

    public static String compression1_mathod2(String str) {
        if(str.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        char prevChar = str.charAt(0);
        int i = 1;
        while(i <= str.length()) {
            int count = 1;
            while( i < str.length() && prevChar == str.charAt(i) ) {
                count++;
                prevChar = str.charAt(i);
                i++;
            }

            sb.append(prevChar);
            sb.append(count);

            if(i == str.length) break;

            prevChar = str.charAt(i);
            i++;
        }

        return sb.toString();
    }

    public static String compression2(String str) {

        int[] freq = new int[26];
        for(int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            freq[idx]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < freq.length; i++) {
            if(freq[i] > 0) {
                char ch = (char)('a' + i)
                sb.append(ch);
                sb.append(freq[i]);
            }
        }


    }
    public static void main(String[] args) {
        String str = scn.nextLine();
        String c1 = compression1_mathod1(str);
        System.out.println(c1);
    }
}