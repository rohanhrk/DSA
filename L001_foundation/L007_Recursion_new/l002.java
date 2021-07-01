import java.util.Scanner;
import java.util.ArrayList;

public class l002 {
    // *************************** Date - 30/06 ***************************
    public static Scanner scn = new Scanner(System.in);

    // print subsequesne -> upar jate hue storing
    public static void printSS(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }
        char ch = str.charAt(idx);

        printSS(str, idx + 1, ans + ch);
        printSS(str, idx + 1, ans);

    }

    // using string builder
    public static void printss_02(String str, int idx, StringBuilder ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }

        ans.append(str.charAt(idx));
        printss_02(str, idx + 1, ans);
        ans.deleteCharAt(ans.length() - 1);

        printss_02(str, idx + 1, ans);
    }

    // *************************** Date - 1/07 ***************************
    // ASCII Subsequences
    public static int countAscii_01(String str, int idx) {

        if (idx == str.length()) {
            return 1;
        }

        int count = 0;

        count += countAscii_01(str, idx + 1);
        count += countAscii_01(str, idx + 1);
        count += countAscii_01(str, idx + 1);

        return count;

    }

    // return type(ArrayList)
    public static ArrayList<String> countAscii_02(String str, int idx) {
        if (idx == -1) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        char ch = str.charAt(idx);
        int ascii = (int) (ch);

        ArrayList<String> recAns = countAscii_02(str, idx - 1);
        ArrayList<String> myAns = new ArrayList<>(recAns);

        for (String s : recAns)
            myAns.add(ch + s);
        for (String s : recAns)
            myAns.add(ascii + s);

        return myAns;
    }

    // way up->upar jate hue ans storing
    public static void countAscii_03(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }

        char ch = str.charAt(idx);
        int ascii = (int) (ch);

        countAscii_03(str, idx + 1, ans);
        countAscii_03(str, idx + 1, ans + ch);
        countAscii_03(str, idx + 1, ans + ascii);

    }

    // get key pad combination (kpc) -> way up
    static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int getKPC_wayup(String str,int idx, String ans) {
        if(idx == str.length()) {
            System.out.println(ans);
            return 1;
        }
        char ch = str.charAt(idx);
        String code = codes[ch - '0'];
        int count = 0;

        for(int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            count += getKPC_wayup(str, idx + 1, ans + c);
        }

        return count;
    }
    
    public static ArrayList<String> getKPC_return(String str, int idx) {
        if(idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        char ch = str.charAt(idx);
        String code = codes[ch - '0'];
        int count = 0;
        
        ArrayList<String> recAns = getKPC_return(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>();

        for(int i = 0 ; i < code.length(); i++) {
            char c = code.charAt(i);
            for(String s : recAns) {
                myAns.add(c + s);
            }
        }

        return myAns;
    }
    public static void main(String[] args) {
        System.out.println(getKPC_return("567",0));
    }
}