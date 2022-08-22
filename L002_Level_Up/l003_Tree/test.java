import java.util.*;

public class test {

    private static ArrayList<Integer> maxSum(int[] arr, int[] queries) {
        int[] leftmax = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            leftmax[i] = (i == 0) ? arr[i] : Math.max(leftmax[i - 1], arr[i]);
        }

        int[] rightmax = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--) {
            rightmax[i] = (i == arr.length - 1) ? arr[i] : Math.max(rightmax[i + 1], arr[i]);
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        for(int q : queries) {
            int g = 0;
            for(int i = q - 1; i < arr.length; i++) {
                for(int j = i; j < arr.length; j++) {
                    int lm = (i - 1 < 0) ? 0 : leftmax[i - 1];
                    int rm = (j + 1 == arr.length) ? 0 : rightmax[j + 1];

                    g += Math.max(lm, rm);
                }
            }
            res.add(g);		
        }
        
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 5};
        int[] q = {2};

        ArrayList<Integer> res = maxSum(arr, q);
        for(int r : res) System.out.println(r);

    }

}