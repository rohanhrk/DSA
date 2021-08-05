public class l002RecursionTrees {
    public static int permutationWithInfi(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int a : arr) {
            if (tar - a >= 0)
                count += permutationWithInfi(arr, tar - a, ans + a);
        }

        return count;
    }

    public static int permutationWithSingle(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            if (tar - val >= 0 && arr[i] > 0) {
                arr[i] = -arr[i];
                count += permutationWithSingle(arr, tar - val, ans + val);
                arr[i] = -arr[i];
            }

        }

        return count;
    }

    public static int combinationWithInfi(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationWithInfi(arr, tar - arr[i], i, ans + arr[i]);
        }

        return count;
    }

    public static int combinationWithSingle(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationWithSingle(arr, tar - arr[i], i + 1, ans + arr[i]);
        }

        return count;
    }

    // *********************************************_SubSequence_Mathod_*********************************************
    public static int combinationWithSingleCoins_subseq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationWithSingleCoins_subseq(arr, tar - arr[idx], idx + 1, ans + arr[idx]);
        count += combinationWithSingleCoins_subseq(arr, tar, idx + 1, ans);

        return count;

    }

    public static int combinationWithInfiCoins_subseq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationWithInfiCoins_subseq(arr, tar - arr[idx], idx, ans + arr[idx]);
        count += combinationWithInfiCoins_subseq(arr, tar, idx + 1, ans);

        return count;

    }

    public static int permutationWithSingle_subseq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (arr[idx] > 0 && tar - arr[idx] >= 0) {
            int val = arr[idx];
            arr[idx] = -arr[idx];
            count += permutationWithSingle_subseq(arr, tar - val, 0, ans + val);
            arr[idx] = -arr[idx];
        }
        count += permutationWithSingle_subseq(arr, tar, idx + 1, ans);
        return count;
    }

    public static int permutationWithInfi_subseq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0) {
            count += permutationWithInfi_subseq(arr, tar - arr[idx], 0, ans + arr[idx]);
        }
        count += permutationWithInfi_subseq(arr, tar, idx + 1, ans);
        return count;
    }

    // ========================================_1D_Queens_Set_========================================
    // HINT
    public static int queenCombination_hint(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {

            count += queenCombination_hint(arr, tar - arr[i], i + 1, ans + i + " ");
        }
        return count;
    }

    // tBoxes -> total boxes, tQueens - > total queens, qpsf -> queen placed so far,
    // bn-> box no
    public static int queenCombination(int tBoxes, int tQueens, int qpsf, int bn, String ans) {
        if (qpsf == tQueens) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tBoxes; i++) {

            count += queenCombination(tBoxes, tQueens, qpsf + 1, i + 1, ans + "b" + i + "->" + "q" + qpsf + "  ");
        }
        return count;
    }

    public static void queenSet() {
        // int[] arr = { 1, 1, 1, 1, 1, 1 };
        // System.out.println(queenCombination_hint(arr, 4, 0, ""));
        System.out.println(queenCombination(6, 4, 0, 0, ""));
    }

    public static void coinChange() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        // System.out.println(permutationWithInfi(arr, tar, ""));
        // System.out.println(coFmbinationWithInfi(arr, tar,0, ""));
        // System.out.println(combinationWithSingle(arr, tar, 0, ""));
        // System.out.println(permutationWithSingleCoin(arr, tar, ""));

        // System.out.println(permutationWithInfi_subSeq(arr, tar, 0, ""));
        // System.out.println(combinationWithInfi_subSeq(arr, tar, 0, ""));
        // System.out.println(combinationWithSingle_subSeq(arr, tar, 0, ""));
        // System.out.println(permutationWithSingleCoin_subSeq(arr, tar, 0, ""));
    }

    public static void main(String[] args) {
        queenSet();
    }
}