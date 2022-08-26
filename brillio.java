import java.util.*;

public class brillio {

    // Star(*) pattern se kar
    /*
        *
    */ 
     public static void fn() {
        int cnt = 0;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j <= i; j++) {
                char ch = (char)(cnt + 'A');
                System.out.print(ch + " ");
                cnt++;
            }
            System.out.println();
        }
    }

    private static int fns(String str) {
        int sum = 0;
        // string me travel kar
        // saath me check karte ja 

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9') {
                sb.append(ch);
            } else {
                sum += Integer.parseInt(sb.toString());
                sb = new StringBuilder();
            }
        }

        return sum;
        // dry run karne ke liye bol mam ko
        // time le le
        //quesn kya he


        // QUESTION LIKH DO
    }
    public static void main(String[] args) {

    }
}