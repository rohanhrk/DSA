import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class l001_basic {
    public static Scanner scn = new Scanner(System.in);
    
    // ======================================================================================================
    /*
        time complexity => 
        1. size() -> O(1)
        2. isEmpty() -> O(1)
        3. add(data) -> O(log(n))
        4. peek() -> O(log(n))
        5. remove() -> O(log(n))
    */ 


    // ======================================================================================================
    // Increasing Order
    public static void MinPQ() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // by default min PQ

        // O(nlogn)
        for (int i = 10; i >= 0; i--) {
            pq.add(i); // O(logn)
        }

        // O(nlogn)
        while (pq.size() != 0) {
            int ele = pq.remove(); // overall min ele remove hoga -> O(logn)
            System.out.print(ele + " ");
        }
    }

    // ======================================================================================================
    // Decreasing Order
    public static void maxPQ() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> { 
            /* 
                a -> this, b -> other
                return a - b; // this - other, default behavior -> means minimum -> increasing order
            */ 
            return b - a; // other - this, reverse of default -> decreasing order
        });

        for (int i = 10; i >= 0; i--) {
            pq.add(i);
        }

        while (pq.size() != 0) {
            int ele = pq.remove();
            System.out.print(ele + " ");
        }
    }

    // ======================================================================================================
    /*
        Question_1 : K largest element
        https://practice.geeksforgeeks.org/problems/k-largest-elements3736/1
    */ 
    public static ArrayList<Integer> kLargest(int arr[], int n, int k) {
        // code here
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // by default min PQ

        for (int val : arr) {
            pq.add(val);

            if (pq.size() > k)
                pq.remove();
        }

        ArrayList<Integer> res = new ArrayList<>();
        while (pq.size() != 0)
            res.add(pq.remove());

        Collections.sort(res, (a, b) -> {
            return b - a;
        });

        return res;
    }

    // ======================================================================================================
    // Question_2 : K Smallest element
    public static void kSmallest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int ele : arr) {
            pq.add(ele);

            if (pq.size() > k)
                pq.remove();
        }

        while (pq.size() != 0)
            System.out.println(pq.remove());
    }

    // ======================================================================================================
    /* 
        Question_3 : 347. Top K Frequent Elements
        https://leetcode.com/problems/top-k-frequent-elements/
    */  
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        // {val , freq}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        for (Integer ele : map.keySet()) {
            int val = ele;
            int freq = map.get(val);

            int[] arr = new int[] { val, freq };
            pq.add(arr);

            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[k];
        int idx = 0;
        while (pq.size() != 0) {
            int ele = pq.remove()[0];
            ans[idx++] = ele;
        }

        return ans;
    }

    // mathod 2
    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return map.get(a) - map.get(b);
        });

        for (Integer ele : cccet()) {
            pq.add(ele);

            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[k];
        int idx = 0;
        while (pq.size() != 0) {
            int ele = pq.remove();
            ans[idx++] = ele;
        }

        return ans;
    }

    // ======================================================================================================
    /*
        Question_4 : 692. Top K Frequent Words
        https://leetcode.com/problems/top-k-frequent-words/
    */ 
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a) == map.get(b))
                return b.compareTo(a);
            return map.get(a) - map.get(b);
        });

        for (String key : map.keySet()) {
            pq.add(key);

            if (pq.size() > k)
                pq.remove();
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++)
            ans.add("");

        int idx = k;
        while (pq.size() != 0) {
            ans.set(--idx, pq.remove());
        }
        return ans;
    }

    // ======================================================================================================
    /*
        Question_5 : 215. Kth Largest Element in an Array
        https://leetcode.com/problems/kth-largest-element-in-an-array/
    */ 
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int ele : nums) {
            pq.add(ele);

            if (pq.size() > k)
                pq.remove();
        }

        return pq.remove();
    }

    // ======================================================================================================
    /*
        Question_6 : 378. Kth Smallest Element in a Sorted Matrix
        https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
    */ 
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                pq.add(matrix[i][j]);
                if (pq.size() > k)
                    pq.remove();
            }
        }

        return pq.remove();
    }

    // ======================================================================================================
    /*
        Question_7 : Sort K-sorted Array
        https://practice.geeksforgeeks.org/problems/nearly-sorted-algorithm/0
    */ 
    public static void kSortedArray(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] ans = new int[arr.length];
        int idx = 0;
        for (int ele : arr) {
            pq.add(ele);
            if (pq.size() > k)
                ans[idx++] = pq.remove();
        }

        while (pq.size() != 0)
            ans[idx++] = pq.remove();

        for (int ele : ans)
            System.out.println(ele);
    }

    // ======================================================================================================
    /*
        Question_8 : 23. Merge k Sorted Arrays
        https://practice.geeksforgeeks.org/problems/merge-k-sorted-arrays/1#
        method 1 ---> Using Helper class
    */ 
    private static class Helper implements Comparable<Helper> {
        int val;
        int r;
        int c;

        Helper(int val, int r, int c) {
            this.val = val;
            this.r = r;
            this.c = c;
        }

        public int compareTo(Helper other) {
            return this.val - other.val;
        }
    }

    public static ArrayList<Integer> mergeKArrays(int[][] arr, int K) {
        PriorityQueue<Helper> pq = new PriorityQueue<>(); // default behaviour
        ArrayList<Integer> ans = new ArrayList<>();

        // O(log(k))
        for (int i = 0; i < arr.length; i++) {
            Helper nh = new Helper(arr[i][0], i, 0);
            pq.add(nh);
        }

        while (pq.size() != 0) {
            Helper rem = pq.remove();
            int val = rem.val, r = rem.r, c = rem.c;
            ans.add(val);

            if (c + 1 < arr[r].length) {
                rem.val = arr[r][c + 1];
                rem.c = c + 1;

                pq.add(rem);
            }
        }

        return ans;
    }

    // Method 2 => 
    // {val, col_idx, row_idx}
    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<Integer> rv = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 0; i < lists.size(); i++) {
            int[] ar = new int[] { lists.get(i).get(0), 0, i };
            pq.add(ar);
        }

        while (pq.size() != 0) {
            int[] ar = pq.remove();
            rv.add(ar[0]);

            int idx = ar[1];
            int listIdx = ar[2];
            int length = lists.get(listIdx).size();

            if (idx + 1 < length) {
                ar[1] = idx + 1;
                ar[0] = lists.get(listIdx).get(idx + 1);
                pq.add(ar);
            }
        }
        return rv;
    }

    // Method_3 : using devide ansd conquer mathod
    // Space -> nk , time -> O(nklog(k))
    private static ArrayList<Integer> merge_two_list(ArrayList<Integer> left_list, ArrayList<Integer> right_list) {
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left_list.size() && j < right_list.size()) {
            int val1 = left_list.get(i), val2 = right_list.get(j);
            if (val1 < val2) {
                ans.add(val1);
                i++;
            } else {
                ans.add(val2);
                j++;
            }
        }

        while (i < left_list.size()) {
            int val1 = left_list.get(i);
            ans.add(val1);
            i++;
        }

        while (j < right_list.size()) {
            int val2 = right_list.get(j);
            ans.add(val2);
            j++;
        }

        return ans;
    }

    public static ArrayList<Integer> mergeKSortedList2(ArrayList<ArrayList<Integer>> lists, int si, int ei) {
        if (si == ei) {
            return lists.get(si);
        }

        int mid = (si + ei) / 2;
        ArrayList<Integer> left_list = mergeKSortedList2(lists, si, mid);
        ArrayList<Integer> right_list = mergeKSortedList2(lists, mid + 1, ei);

        return merge_two_list(left_list, right_list);

    }
    
    // ======================================================================================================
    /*
        Question_9 : 242. Valid Anagram
        https://leetcode.com/problems/valid-anagram/
        using frequency array
    */  
   
    public boolean isAnagram1(String s, String t) {
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a']++;

        for (int i = 0; i < t.length(); i++)
            freq[t.charAt(i) - 'a']--;

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0)
                return false;
        }

        return true;
    }

    // using hashmap
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

        for (int i = 0; i < t.length(); i++)
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);

        for (char ch : map.keySet()) {
            if (map.get(ch) != 0)
                return false;
        }

        return true;

    }

    // ======================================================================================================
    /*
        Question_10 : 49. Group Anagrams
        https://leetcode.com/problems/group-anagrams/
    */ 
    public static String RLES(String str) {
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                sb.append((char) (i + 'a'));
                sb.append(freq[i]);
            }
        }

        return sb.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            String rles = RLES(s);
            map.putIfAbsent(rles, new ArrayList<>());
            map.get(rles).add(s);
        }

        List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet())
            ans.add(map.get(key));

        return ans;
    }

    // ======================================================================================================
    // Question_11 : 506. Relative Ranks
    // https://leetcode.com/problems/relative-ranks/
    public String[] findRelativeRanks(int[] scores) {
        int n = scores.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[0] - a[0];
        });
            
        String[] ans = new String[n];
        for(int i = 0; i < n; i++) {
            pq.add(new int[]{scores[i], i});
        }
        
        int rank = 1;
        while(pq.size() != 0) {
            int[] rem_arr = pq.remove();
            int score = rem_arr[0];
            int idx = rem_arr[1];
            
            if(rank == 1) {
                ans[idx] = "Gold Medal";
            } else if(rank == 2) {
                ans[idx] = "Silver Medal"; 
            } else if(rank == 3) {
                ans[idx] = "Bronze Medal";
            } else {
                ans[idx] = rank + "";
            }
            
            rank++;
        }
        
        return ans;
    }
    // ======================================================================================================
    public static void input(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = scn.nextInt();
    }

    public static void main(String[] args) {
        int[] arr = new int[12];
        input(arr);
        // kLargest(arr, 4);
        System.out.println();
        kSmallest(arr, 4);
    }

}