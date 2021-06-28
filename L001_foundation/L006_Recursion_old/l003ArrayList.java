import java.util.Scanner;

public class l003ArrayList {
    public static Scanner scn = new Scanner(System.in);

    // subsequence -> method 1 (with substring)
    public static ArrayList<String> gss(String str) {
        if(str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        
        char ch = str.charAt(0);
        String ros = str.substring(1);
        
        ArrayList<String> smallRes = gss(ros);
        ArrayList<String> myRes = new ArrayList<>();
        
        for(String s : smallRes) {
            myRes.add(s);
        }
        
        for(String s : smallRes) {
            myRes.add(ch + s);
        }
        
        
        return myRes;
    }
    
    // get subSequence -> method 2 (without substring)
    public static ArrayList<String> gss2(String str, int idx) {
        if(idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        
        char ch = str.charAt(idx);
        
        ArrayList<String> smallRes = gss2(str, idx + 1);
        ArrayList<String> myRes = new ArrayList<>();
        
        for(String s : smallRes) {
            myRes.add(s);
        }
        
        for(String s : smallRes) {
            myRes.add(ch + s);
        }
        
        return myRes;
    }
    
    // get key pad -> method 1 (with substring)
    public static ArrayList<String> getKPC(String str) {

        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        String[] codes = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};

        char ch = str.charAt(0);
        String ros = str.substring(1);

        ArrayList<String> smallRes = getKPC(ros);
        ArrayList<String> myRes = new ArrayList<>();

        int key = (int)(ch - '0');
        String code = codes[key];

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            for (String s : smallRes) {
            myRes.add(c + s);
            }
        }

        return myRes;

    }
     
    // get key pad -> method 2 (without substring)
    public static ArrayList<String> getKPC2(String str, int idx) {

        if (idx == str.length()) {
        ArrayList<String> base = new ArrayList<>();
        base.add("");
        return base;
        }

        String[] codes = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};

        char ch = str.charAt(idx);

        ArrayList<String> smallRes = getKPC2(str, idx + 1);
        ArrayList<String> myRes = new ArrayList<>();

        int key = (int)(ch - '0');
        String code = codes[key];

        for (int i = 0; i < code.length(); i++) {
        char c = code.charAt(i);

        for (String s : smallRes) {
            myRes.add(c + s);
        }
        }

        return myRes;

    }
    public static void main(String[] args) {

    }
}