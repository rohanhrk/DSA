import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// **********************************_DATE:-15/07/21_**********************************
public class l001_basic {
    public static Scanner scn = new Scanner(System.in);

    // ***********************_Increasing_Order_***********************
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

    // ***********************_Decreasing_Order_***********************
    public static void maxPQ() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> { // a -> this, b -> other
            // return a - b; // this - other, default behavior -> means minimum ->
            // increasing order
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

    // ***********************_K_largest_element_***********************
    public static void kLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int ele : arr) {
            pq.add(ele);

            if (pq.size() > k)
                pq.remove();
        }

        while (pq.size() != 0)
            System.out.println(pq.remove());
    }

    // ***********************_K_Smallest_element_***********************
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

    // ***********************_Top_K_frequent_element_***********************
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

        for (Integer ele : map.keySet()) {
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

    // ***********************_leetcode 692. Top K Frequent Words_***********************
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

    // ***********************_find_k_largest_***********************
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int ele : nums) {
            pq.add(ele);

            if (pq.size() > k)
                pq.remove();
        }

        return pq.remove();
    }
    
    // ***********************leetcode 378. Kth Smallest Element in a Sorted Matrix***********************
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
    
    // leetcode 451. Sort Characters By Frequency_=============================
    public String frequencySort(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch , map.getOrDefault(ch, 0) + 1);
        } 
            
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b) -> {
            if(map.get(a) == map.get(b)) {
                return a.compareTo(b);
            }
            return map.get(b) - map.get(a);
        });
        
        for(char ch : map.keySet()) {
            pq.add(ch);
        } 
        
        StringBuilder sb = new StringBuilder();
        while(pq.size() != 0) {
            char rkey = pq.remove();
            int val = map.get(rkey);
            while(val-- > 0) 
                sb.append(rkey);
        } 
            
        
        return sb.toString();
    }
    // =============================================_INPUT_=============================================
    public static void input(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = scn.nextInt();
    }

    public static void main(String[] args) {
        int[] arr = new int[12];
        input(arr);
        kLargest(arr, 4);
        System.out.println();
        kSmallest(arr, 4);
    }
}