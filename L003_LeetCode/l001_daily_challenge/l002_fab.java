import java.util.*;

public class l002_fab {
    // ================================================================================================================================
    /*
     * DAY_1 : 121. Best Time to Buy and Sell Stock
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     */
    public int maxProfit(int[] prices) {
        int min_rate = prices[0], max_profit = 0;
        for (int price : prices) {
            /* step 1 => find minimum buying rate */
            min_rate = Math.min(min_rate, price);

            /* step 2 => find out maximum profit if I am selling stock today */
            max_profit = Math.max(max_profit, price - min_rate);
        }

        // step 3 : return overall maximum profit
        return max_profit;
    }

    // ================================================================================================================================
    /*
     * DAY_2 : 438. Find All Anagrams in a String
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/
     */
    private boolean isMatch(int[] map1, int[] map2) {
        for (int i = 0; i < 26; i++) {
            int val1 = map1[i];
            int val2 = map2[i];
            if (val1 != val2)
                return false;
        }
        return true;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length())
            return res;

        int[] p_map = new int[26]; // pattern map
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            p_map[ch - 'a']++;
        }

        /*
         * initially acquire substring of first window of string s of length =
         * p.length()
         */
        int[] s_map = new int[26]; // source map
        for (int i = 0; i < p.length(); i++) {
            char ch = s.charAt(i);
            s_map[ch - 'a']++;
        }

        /* Now store result inside the loop if satisfy the condition */

        for (int i = p.length(); i < s.length(); i++) {
            /*
             * step 1 => match source map with pattern map
             * if matching => store character of index i - p.length() of string s
             */
            if (isMatch(s_map, p_map) == true) {
                res.add(i - p.length());
            }

            /* step 2 => acquire character of i'th index of string s in source map */
            char acq_ch = s.charAt(i); // acquire character
            s_map[acq_ch - 'a']++;

            /*
             * step 3 => release character of (i - p.length)'th index of string s from
             * source map
             */
            char rel_ch = s.charAt(i - p.length()); // releasing character
            s_map[rel_ch - 'a']--;
        }

        // match last window with pattern map
        if (isMatch(s_map, p_map) == true) {
            res.add(s.length() - p.length());
        }

        return res;
    }

    // ================================================================================================================================
    /*
     * DAY_3 : 454. 4Sum II
     * https://leetcode.com/problems/4sum-ii/
     */
    // Time => O(N^2) , space => O(N^2)
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();

        /*
         * step 1 => initially store all combinational sum of
         * nums1 and nums2 in a map
         * 
         * Time => O(N^2)
         */

        for (int e1 : nums1) {
            for (int e2 : nums2) {
                int sum = e1 + e2;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        /*
         * AB + CD = target, where
         * AB => combinational sum of nums1 and nums2
         * CD => combinational sum of nums3 and nums4
         * 
         * AB = target - CD
         * 
         * step 2 => Now search (target - CD) in map. If found => increament count by 1
         * 
         * Time => O(N^2)
         */
        int target = 0, count = 0;
        for (int e3 : nums3) {
            for (int e4 : nums4) {
                int sum = e3 + e4;
                count += map.getOrDefault(target - sum, 0);
            }
        }

        return count;
    }

    // ================================================================================================================================
    /*
     * Day_4 : 525. Contiguous Array
     * https://leetcode.com/problems/contiguous-array/
     */
    public int findMaxLength(int[] nums) {
        int[] arr = new int[2 * nums.length + 1];
        Arrays.fill(arr, -2);
        arr[nums.length] = -1;

        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if (arr[count + nums.length] >= -1) {
                maxlen = Math.max(maxlen, i - arr[count + nums.length]);
            } else {
                arr[count + nums.length] = i;
            }

        }
        return maxlen;
    }

    // ================================================================================================================================
    /*
     * Day_5 : 23. Merge k Sorted Lists
     * https://leetcode.com/problems/merge-k-sorted-lists/
     */
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    private ListNode mergeTwoList(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null)
            return h1 != null ? h1 : h2;
        ListNode dummy = new ListNode(-1);
        ListNode c1 = h1, c2 = h2, prev = dummy;
        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }

            prev = prev.next;
        }

        prev.next = (c1 != null) ? c1 : c2;

        return dummy.next;
    }

    private ListNode mergeKLists_(ListNode[] lists, int si, int ei) {
        if (si >= ei)
            return (si == ei) ? lists[si] : null;

        int mid = (si + ei) / 2;
        ListNode leftList = mergeKLists_(lists, si, mid);
        ListNode rightList = mergeKLists_(lists, mid + 1, ei);

        return mergeTwoList(leftList, rightList);

    }

    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists_(lists, 0, lists.length - 1);
    }

    // ================================================================================================================================
    /*
     * Day_6 : 80. Remove Duplicates from Sorted Array II
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
     */
    private class Pair {
        int num = 0;
        int count = 0;

        Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public int removeDuplicates(int[] nums) {
        LinkedList<Pair> map = new LinkedList<>(); // num vs count

        int val = (int) 1e9;
        for (int elem : nums) {
            if (elem != val) {
                map.addLast(new Pair(elem, 0));
                val = elem;
            }
            map.getLast().count++;
        }

        int idx = 0;
        while (map.size() != 0) {
            Pair rp = map.removeFirst(); // num vs count
            int num = rp.num, count = rp.count;
            if (count >= 2) {
                for (int i = 2; i > 0; i--) {
                    nums[idx++] = num;
                }
            } else if (count == 1) {
                nums[idx++] = num;
            }
        }

        return idx;
    }

    // ================================================================================================================================
    /*
     * Day_7 : 389. Find the Difference
     * https://leetcode.com/problems/find-the-difference/
     */
    public char findTheDifference(String s, String t) {
        int sum = 0;
        for (char c : s.toCharArray())
            sum += (int) (c - 'a');

        for (char c : t.toCharArray())
            sum -= (int) (c - 'a');

        sum = Math.abs(sum);
        return (char) (sum + 'a');
    }

    // ================================================================================================================================
    /*
     * Day_8 : 258. Add Digits
     * https://leetcode.com/problems/add-digits/
     */
    public int addDigits(int num) {
        return num == 0 ? 0 : (num % 9 == 0) ? 9 : num % 9;
    }

    // ================================================================================================================================
    /*
     * Day_9 : 532. K-diff Pairs in an Array
     * https://leetcode.com/problems/k-diff-pairs-in-an-array/
     */
    public int findPairs(int[] nums, int k) {
        /*
         * |num1 - num2| = k <=> num1 = k + num2
         */

        // step 1 : initially, make frequency map
        HashMap<Integer, Integer> fmap = new HashMap<>(); // frequency map => store (num, freq)
        for (int num : nums) {
            fmap.put(num, fmap.getOrDefault(num, 0) + 1);
        }

        /*
         * step 2 : travel on hashmap, for every num, search (k + num) in HashMap
         * if found, increament count by 1
         */
        int count = 0;
        for (int num : fmap.keySet()) {
            count += (k == 0 && fmap.get(num) > 1) ? 1 : k > 0 && fmap.containsKey(k + num) ? 1 : 0;
        }

        return count;
    }

    // ================================================================================================================================
    // Day_10 : 560. Subarray Sum Equals K
    // https://leetcode.com/problems/subarray-sum-equals-k/
    public int subarraySum(int[] nums, int k) {
        /*
         * travel on nums, make prefix vs occurance of prefix
         * that maintain in hashmap and for every prefix check (prefix - k)
         * is contain in map or not, if found update count by map.get(prefix - k)
         * also increament occurance of prefix count by 1
         */

        HashMap<Integer, Integer> prefix_map = new HashMap<>(); // prefixsum vs occurance of prefix
        prefix_map.put(0, 1); // to handle subarray starting from 0th to ith index which sum is k

        int prefix = 0; // prefix sum
        int count = 0;
        for (int num : nums) {
            prefix += num;
            if (prefix_map.containsKey(prefix - k) == true)
                count += prefix_map.get(prefix - k);

            prefix_map.put(prefix, prefix_map.getOrDefault(prefix, 0) + 1);
        }

        return count;
    }

    // ================================================================================================================================
    // Day_11 : 567. Permutation in String
    // https://leetcode.com/problems/permutation-in-string/
    public boolean checkInclusion(String p, String s) {
        if (p.length() > s.length())
            return false;

        HashMap<Character, Integer> pmap = new HashMap<>(); // pattern map => store char of string p vs it's frequency
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            pmap.put(ch, pmap.getOrDefault(ch, 0) + 1);
        }

        // initially acquire substring of first window of string s of length =
        // p.length()
        HashMap<Character, Integer> smap = new HashMap<>(); // source map => store char of string p vs it's frequency
        for (int i = 0; i < p.length(); i++) {
            char ch = s.charAt(i);
            smap.put(ch, smap.getOrDefault(ch, 0) + 1);
        }

        // make a loop starting from i = p.length() and store result inside the loop if
        // satisfy the condition;
        int rel = -1; // release
        for (int i = p.length(); i < s.length(); i++) {
            // 1. match => if satisfies, store starting index of window of s in ans
            if (smap.equals(pmap) == true) {
                return true;
            }

            // 2. acquire
            char ch = s.charAt(i);
            smap.put(ch, smap.getOrDefault(ch, 0) + 1);

            // 3. release
            rel++;
            char relCh = s.charAt(rel);
            smap.put(relCh, smap.get(relCh) - 1);

            if (smap.get(relCh) == 0) {
                smap.remove(relCh);
            }
        }

        // also check for last window => anagram or not
        if (smap.equals(pmap) == true) {
            return true;
        }

        return false;
    }

    // ================================================================================================================================
    // Day_12 : 127. Word Ladder
    // https://leetcode.com/problems/word-ladder/

    // ================================================================================================================================
    // Day_13 : 78. Subsets
    // https://leetcode.com/problems/subsets/
    private void subsets_(int[] nums, int idx, List<Integer> smallAns, List<List<Integer>> ans) {
        if (idx == nums.length) {
            List<Integer> base = new ArrayList<>(smallAns);
            ans.add(base);
            return;
        }

        // yes call
        int ele = nums[idx];
        smallAns.add(ele);
        subsets_(nums, idx + 1, smallAns, ans);
        smallAns.remove(smallAns.size() - 1);

        // no call
        subsets_(nums, idx + 1, smallAns, ans);

    }

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        subsets_(nums, 0, smallAns, ans);
        return ans;
    }

    // ================================================================================================================================
    // Day_14 : 104. Maximum Depth of Binary Tree
    // https://leetcode.com/problems/maximum-depth-of-binary-tree/
    private class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int left_depth = maxDepth(root.left);
        int right_depth = maxDepth(root.right);

        return Math.max(left_depth, right_depth) + 1;
    }

    // ================================================================================================================================
    // Day_15 : 136. Single Number
    // https://leetcode.com/problems/single-number/

    // T -> O(N) S -> O(N)
    public int singleNumber01(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int ele : nums) {
            if(set.contains(ele) == true) {
                set.remove(ele);
            } else {
                set.add(ele);
            }
        }
        
        int ans = 0;
        for(int key : set)
            ans = key;
        
        return ans;
    }

    // T -> O(N) S -> O(1)
    public int singleNumber02(int[] nums) {
        int ans = 0;
        for(int ele : nums) 
            ans ^= ele;
        
        return ans;
    }

    // ================================================================================================================================
    // Day_16 : 24. Swap Nodes in Pairs
    // https://leetcode.com/problems/swap-nodes-in-pairs/
    private ListNode glb_head = null, glb_tail = null;
    private void addFirstNode(ListNode node) {
        if(glb_head == null) {
            glb_head = glb_tail = node;
        } else {
            node.next = glb_head;
            glb_head = node;
        }
    }
    private ListNode swapPairs_(ListNode head, int k) {
        ListNode org_head = null, org_tail = null;
        ListNode curr = head;
        
        while(curr != null) {
            int itr = k;
            while(curr != null && itr-- > 0) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
            }
            
            if(org_head == null) {
                org_head = glb_head;
                org_tail = glb_tail;
            } else {
                org_tail.next = glb_head;
                org_tail = glb_tail;    
            }
            
            glb_head = glb_tail = null;
        }
        
        return org_head;
    }
    public ListNode swapPairs(ListNode head) {
        return swapPairs_(head, 2);
    }

    // ================================================================================================================================
    // Day_17 : 39. Combination Sum
    // https://leetcode.com/problems/combination-sum/
     private void combinationSum_rec(int[] candidates, int idx, int target, List<Integer> smallAns, List<List<Integer>> ans) {
        if(target == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            ans.add(base);
            return;
        }
        
        for(int i = idx; i < candidates.length; i++) {
            if(target - candidates[i] >= 0) {
                smallAns.add(candidates[i]);
                combinationSum_rec(candidates, i, target - candidates[i], smallAns, ans);
                smallAns.remove(smallAns.size() - 1);
            }
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        
        combinationSum_rec(candidates, 0, target, smallAns, ans);
        return ans;
    }

    // ================================================================================================================================
    // Day_18 : 402. Remove K Digits
    // https://leetcode.com/problems/remove-k-digits/
    public String removeKdigits(String num, int k) {
        int n = num.length();
        LinkedList<Character> st = new LinkedList<>();
        for(int idx = 0; idx < n; idx++) {
            char ch = num.charAt(idx);
            while(k > 0 && st.size() > 0 && st.getLast() > ch) {
                st.removeLast();
                k--;
            }
            st.addLast(ch);
        }
        
        // manage remaing k's
        while(k > 0 && st.size() > 0) {
            st.removeLast();
            k--;
        }
        
        // manage leading 0's
        while(st.size() > 0 && st.getFirst() == '0')
            st.removeFirst();
        
        StringBuilder ans = new StringBuilder();
        for(char ch : st) {
            ans.append(ch);
        }
        
        return ans.length() > 0 ? ans.toString() : "0"; 
    }
}