import java.util.*;

public class practice {
    private static int queen_memo(int[] boxes, int tnb, int bn, int tnq, int qsf, String ans) {
        if (qsf == tnq) {
        System.out.println(ans);
        return 1;
        }


        int cways = 0;
        for (int i = bn; i < boxes.length; i++) {
        cways += queen_memo(boxes, tnb, i + 1, tnq, qsf + 1, ans + 'q' + qsf + 'b' + bn  + ' ');
        }

        return cways;
    }
    public static void main(String[] args) {
        int tnb = 5;
        int tnq = 3;
        int[] boxes = new int[tnb];

        int ans = queen_memo(boxes, tnb, 0, tnq, 0, "");
        System.out.println(ans);
    }
}