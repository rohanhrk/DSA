import java.util.Scanner;
import java.util.ArrayList;

public class l001Basic {
    public static Scanner scn = new Scanner(System.in);
    
    // ===============================================================================================================================================
    public static void test1() {
        // string is a immutable means not possible to change on same address
        String str = "sdsdfsdfdgfdg"; // it stores in intern pool
        String str1 = str;  // o(1) -> pointing on same address of str

        str += 'g' ; //O(n) -> creating copy of str and then 'g' is added in last
        char ch = str.charAt(3); // get charn
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
        StringBuilder sb = new StringBuilder(); // define string builder
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
    
    // without X2
    public static String withoutX2(String str) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < str.length(); i++) {
            if(i < 2 && str.charAt(i) != 'x') {
                sb.append(str.charAt(i));
            }else if(i >= 2) {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    } 
    // ===============================================================================================================================================
    // Question_1 : compression

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

            if(i == str.length()) break;

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
                char ch = (char)('a' + i);
                sb.append(ch);
                sb.append(freq[i]);
            }
        }

        return sb.toString();
    }
    
    //  ===============================================================================================================================================
    // Question_2 : toggle
    public static String toggle(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(ch >= 'a' && ch <= 'z') {
                char uc = (char)('A' - 'a' + ch);
                sb.append(uc);
            }else if(ch>='A' && ch<='Z') {
                char lc = (char)('a' - 'A' + ch);
                sb.append(lc);
            }
        }

        return sb.toString();
    }
    
    // ===============================================================================================================================================
    // Question_3 : palindromic substring
    public static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while(i < j) {
            if(str.charAt(i++) != str.charAt(j--)) return false;
        }

        return true;
    }
    public static void palindromicSubstring(String str) {
        for(int i = 0; i < str.length(); i++) {
            for(int j =i ; j < str.length(); j++) {
                String ss =  str.substring(i,j+1);
                if(isPalindrome(ss)) 
                    System.out.println(ss);
            }
        }
    }

    // ===============================================================================================================================================
    // Question_4 : String With Difference Of Every Two Consecutive Characters
    public static String string_With_Diff_Of_Every_Two_Consecutive_Number(String str) {
        if(str.length() <= 1) return str;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length() - 1; i++) {
            char ch = str.charAt(i);
            char nextCh = str.charAt(i+1);
            int diff = (int)(nextCh - ch);

            sb.append(ch);
            sb.append(diff);

        }
        sb.append(str.charAt(str.length()-1));
        return sb.toString();
    }
    
    // ===============================================================================================================================================
    // Question_5 : permutation of string
    public static void appendCharInString(String str,char ch,ArrayList<String> ans){
        for(int i=0;i<=str.length();i++){
            String s = str.substring(0,i) + ch + str.substring(i);
            ans.add(s);
        }
    }
    public static ArrayList<String> permutation(String str){
        ArrayList<String> ans = new ArrayList<>();
        ans.add("");
        for(int i = 0; i <str.length();i++){
            char ch = str.charAt(i);
            
            ArrayList<String> smallAns = new ArrayList<>();
            for(String s : ans)
                appendCharInString(s,ch,smallAns);

            ans = smallAns;
        }

        return ans;
    }
    
    // **************************************************************** DATE - 27/06 ****************************************************************
    // leetcode 541 => recverse string II
    public void reverseRange(char[] arr, int i, int j) {
        while(i <  j) {
            char ch = arr[i];
            arr[i] = arr[j];
            arr[j] = ch;
            
            i++;
            j--;
        }
    }
    public String reverseStr(String s, int k) {
        if(k == 0 || k == 1 || s.length() <= 1) return s;
        char[] arr = s.toCharArray();
        int i = 0 , n = s.length();
        
        while(i < n) {
            if(i + k - 1 < n) {
                reverseRange(arr, i , i + k - 1);
                i += 2 * k;
            } else {
                reverseRange(arr , i , n - 1);
                break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(char ch : arr) {
            sb.append(ch);
        }
        
        return sb.toString();
    }

    // leetcode 387 => first unique character
    public static int firstUniqueCharacter(String str) {
        int[] freq = new int[26];
        
        for(int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a'] ++;
        }

        for(int i = 0; i < str.length(); i++) {
            int count = freq[str.charAt(i) - 'a'];
            if(count == 1) {
                return i;
            } 
        }

        return -1;
    }

    // prime factor for query which contains number
    public static boolean isPrime(int n) {
        for(int i = 2; i * i <= n ; i++) {
            if(n % i == 0) return false;
        }

        return true;
    }

    public static void primeNumbers(int n, ArrayList<Integer> list) {
        for(int i = 2; i * i <= n; i++) {
            if(isPrime(i)) list.add(i);
        }
    }
    
    public static void primeFactors(int num, ArrayList<Integer> list) {
        int idx = 0;
        while(num != 1 && idx < list.size()) {
            int count = 0;
            while(num % list.get(idx) == 0) {
                num /= list.get(idx);
                count++;
            }
            if(count > 0) {
                System.out.print(list.get(idx) + " ^ " + count + " " );
            }

            idx++;
        }

        if(num > 1) {
            System.out.print(num + " ^ " + 1);
        }

        System.out.println();
    }

    public static void primeFactorForQuery(int[] query) {
        ArrayList<Integer> list = new ArrayList<>();
        primeNumbers(10000, list);


        for(int elem : query) {
            primeFactors(elem, list);
        }
    }
    

    // remove primes 
    public static void removePrimes(ArrayList<Integer> al) {
        ArrayList<Integer> ans = new ArrayList<>();

        for(int ele : al){
            if(!isPrime(ele)) ans.add(ele);
        }

        al.clear();
        for(int ele : ans) al.add(ele);
    }
    
    // ************************************************************************************************************************************************************************************************
    
    public static void main(String[] args) {
        int n = scn.nextInt();
        ArrayList<Integer> al = new ArrayList<>();
        for(int i = 0; i < n ; i++) {
            al.add(scn.nextInt());
        }
        removePrimes(al);

        System.out.println(al);
    }
}