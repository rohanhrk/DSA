import java.util.ArrayList;
import java.util.Arrays;

public class l008Recursion {
    public static int twoSubset(int[] arr, String setA, String setB, int sumA, int sumB, int idx) {
        if (idx == arr.length) {
            if (sumA == sumB) {
                System.out.println(setA + "\t" + setB);
                return 1;
            }
            return 0;
        }

        int count = 0;
        count += twoSubset(arr, setA + arr[idx] + " ", setB, sumA + arr[idx], sumB, idx + 1);
        count += twoSubset(arr, setA, setB + arr[idx] + " ", sumA, sumB + arr[idx], idx + 1);

        return count;
    }

    public static void twoSubsetProbleam() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70 };
        System.out.println(twoSubset(arr, "", "", 0, 0, 0));
    }

    // kSubset problem
    public static void kSubsetEqualSum(int[] arr, int vidx, int[] subsetSum, ArrayList<ArrayList<Integer>> ans) {
        if (vidx == arr.length) {
            for (int ele : subsetSum) {
                if (ele != 0)
                    return;
            }
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < ans.size(); i++) {
            if (subsetSum[i] - arr[vidx] >= 0) {
                boolean isFirstTime = false;
                if (ans.get(i).size() == 0)
                    isFirstTime = true;

                ans.get(i).add(arr[vidx]);
                subsetSum[i] -= arr[vidx];

                kSubsetEqualSum(arr, vidx + 1, subsetSum, ans);

                ans.get(i).remove(ans.get(i).size() - 1);
                subsetSum[i] += arr[vidx];

                if (isFirstTime)
                    break;
            }

        }
    }

    public static void kSubsetEqualSum(int[] arr, int k) {
        int sum = 0;
        for (int elem : arr)
            sum += elem;
        if (sum % k != 0 || arr.length < k)
            return;

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(new ArrayList<>());
        }

        int tar = sum / k;
        int[] subsetSum = new int[k];
        Arrays.fill(subsetSum, tar);
        kSubsetEqualSum(arr, 0, subsetSum, ans);
    }

    public static void kSubsetPrblem() {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        kSubsetEqualSum(arr, 3);
    }

    // All Palindromic Partitions
    public static boolean isPalindrom(String str) {
        int si = 0, ei = str.length() - 1;
        while (si <= ei) {
            if (str.charAt(si++) != str.charAt(ei--))
                return false;
        }

        return true;
    }

    public static void AllPalindromicPartitions(String str, String asf) {
        // write you code here
        if (str.length() == 0) {
            System.out.println(asf + " ");
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            String smallStr = str.substring(0, i + 1);
            if (isPalindrom(smallStr)) {
                AllPalindromicPartitions(str.substring(i + 1), asf + "(" + smallStr + ") ");
            }
        }

    }

    // friends pairing
    static int counter = 1;

    public static int friendsPairing_helperFunc(int totalFriends, boolean[] vis, String asf) {
        if (totalFriends == 0) {
            System.out.println(counter++ + "." + asf + " ");
            return 1;
        }
        int firstUnusedFriends = 0;
        for (int i = 1; i < vis.length; i++) {
            if (!vis[i]) {
                firstUnusedFriends = i;
                break;
            }
        }

        int count = 0;
        vis[firstUnusedFriends] = true;
        count += friendsPairing_helperFunc(totalFriends - 1, vis, asf + "(" + firstUnusedFriends + ") "); // single

        for (int i = firstUnusedFriends + 1; i < vis.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                count += friendsPairing_helperFunc(totalFriends - 2, vis,
                        asf + "(" + firstUnusedFriends + "," + i + ") "); // pairup
                vis[i] = false;
            }
        }
        vis[firstUnusedFriends] = false;

        return count;
    }

    public static void friendsPairing() {
        int N = 5;
        boolean[] vis = new boolean[N + 1];
        System.out.println(friendsPairing_helperFunc(N, vis, ""));
    }

    // Largest Number Possible After At Most K Swaps
    public static void swap(StringBuilder sb, int i, int j) {
        char ch1 = sb.charAt(i);
        char ch2 = sb.charAt(j);

        sb.setCharAt(i, ch2);
        sb.setCharAt(j, ch1);

    }

    public static String maxStr = "0";

    public static void largestPossibleNumber(StringBuilder sb, int k) {
        if (k == 0) {
            return;
        }
        for (int i = 0; i < sb.length(); i++) {
            char maxCh = sb.charAt(i);
            int idx = 0;
            for (int j = i + 1; j < sb.length(); j++)
                if (maxCh < sb.charAt(j)) {
                    maxCh = sb.charAt(j);
                    idx = j;
                }

            swap(sb, i, idx);
            if (Integer.parseInt(maxStr) < Integer.parseInt(sb.toString())) {
                maxStr = sb.toString();
            }
            largestPossibleNumber(sb, k - 1);
            swap(sb, i, idx);
        }
    }

    public static void largestPossibleNumber() {
        String str = "1234567";
        int k = 4;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
        }
        largestPossibleNumber(sb, k);
        System.out.println(maxStr);

    }

    public static void main(String[] args) {
        // twoSubsetProbleam();
        // kSubsetPrblem();
        // friendsPairing();
        largestPossibleNumber();
    }
}