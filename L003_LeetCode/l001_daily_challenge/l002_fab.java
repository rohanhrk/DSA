public class l002_fab {
    // ================================================================================================================================
    /*
        DAY_1 : 121. Best Time to Buy and Sell Stock 
        https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    */ 
     public int maxProfit(int[] prices) {
        int min_rate = prices[0], max_profit = 0;
        for(int price : prices) {
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
        DAY_2 : 438. Find All Anagrams in a String
        https://leetcode.com/problems/find-all-anagrams-in-a-string/
    */ 
    private boolean isMatch(int[] map1, int[] map2) {
        for(int i = 0; i < 26; i++) {
            int val1 = map1[i];
            int val2 = map2[i];
            if(val1 != val2) return false;
        }
        return true;
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length())
            return res;
        
        int[] p_map = new int[26]; // pattern map
        for(int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            p_map[ch - 'a']++;
        }
        
        /* initially acquire substring of first window of string s of length = p.length() */ 
        int[] s_map = new int[26]; // source map
        for(int i = 0; i < p.length(); i++) {
            char ch = s.charAt(i);
            s_map[ch - 'a']++;
        }
        
        /* Now store result inside the loop if satisfy the condition */
        
        for(int i = p.length(); i < s.length(); i++) {
            /* 
                step 1 => match source map with pattern map
                if matching => store character of index i - p.length() of string s
            */
            if(isMatch(s_map, p_map) == true) {
                res.add(i - p.length());
            }
            
            /* step 2 => acquire character of i'th index of string s in source map */
            char acq_ch = s.charAt(i); // acquire character
            s_map[acq_ch - 'a']++;
            
            /* step 3 => release character of (i - p.length)'th index of string s from source map */
            char rel_ch = s.charAt(i - p.length()); // releasing character
            s_map[rel_ch - 'a']--;      
        }
        
        // match last window with pattern map
        if(isMatch(s_map, p_map) == true) {
                res.add(s.length() - p.length());
        }
        
        return res;
    }

    // ================================================================================================================================
    /*
        DAY_3 : 454. 4Sum II
        https://leetcode.com/problems/4sum-ii/
    */ 
    // Time => O(N^2) , space => O(N^2)     
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        /*
            step 1 => initially store all combinational sum of 
            nums1 and nums2 in a map 
            
            Time => O(N^2)
        */ 
        
        for(int e1 : nums1) {
            for(int e2 : nums2) {
                int sum = e1 + e2;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        
        /*  
            AB + CD = target, where
                AB => combinational sum of nums1 and nums2
                CD => combinational sum of nums3 and nums4
                
            AB = target - CD
            
            step 2 => Now search (target - CD) in map. If found => increament count by 1
            
            Time => O(N^2)
        */ 
        int target = 0, count = 0;
        for(int e3 : nums3) {
            for(int e4 : nums4) {
                int sum = e3 + e4;
                count += map.getOrDefault(target - sum, 0);
            }
        }
        
        return count;
    }

    // ================================================================================================================================
    /*
        Day_4 : 525. Contiguous Array
        // https://leetcode.com/problems/contiguous-array/
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
        Day_5 : 23. Merge k Sorted Lists
        // https://leetcode.com/problems/merge-k-sorted-lists/
    */ 
    private ListNode mergeTwoList(ListNode h1, ListNode h2) {
        if(h1 == null || h2 == null) 
            return h1 != null ? h1 : h2;
        ListNode dummy = new ListNode(-1);
        ListNode c1 = h1, c2 = h2, prev = dummy;
        while(c1 != null && c2 != null) {
            if(c1.val < c2.val) {
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
        if(si >= ei)
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
        Day_6 : 80. Remove Duplicates from Sorted Array II
        https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
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
        for(int elem : nums) {
            if(elem != val) {
                map.addLast(new Pair(elem, 0));
                val = elem;
            }
            map.getLast().count++;   
        }
        
        int idx = 0;
        while(map.size() != 0) {
            Pair rp = map.removeFirst(); // num vs count
            int num = rp.num, count = rp.count;
            if(count >= 2) {
                for(int i = 2; i > 0; i--) {
                    nums[idx++] = num;
                }
            } else if(count == 1) {
                nums[idx++] = num;
            }
        }
        
        return idx;
    }


    // ================================================================================================================================
    /*
        Day_7 : 389. Find the Difference
        https://leetcode.com/problems/find-the-difference/
    */ 
    public char findTheDifference(String s, String t) {
        int sum = 0;
        for(char c : s.toCharArray())
            sum += (int)(c - 'a');

        for(char c : t.toCharArray())
            sum -= (int)(c - 'a');

        sum = Math.abs(sum);
        return (char)(sum + 'a');
    }

    // ================================================================================================================================
    /*
        Day_8 : 258. Add Digits
        https://leetcode.com/problems/add-digits/
    */ 
    public int addDigits(int num) {
        return num == 0 ? 0 : (num % 9 == 0) ? 9 : num % 9;
    }

    // ================================================================================================================================
    /*
        Day_9 : 532. K-diff Pairs in an Array
        https://leetcode.com/problems/k-diff-pairs-in-an-array/
    */ 
    public int findPairs(int[] nums, int k) {
        /*
            |num1 - num2| = k <=> num1 = k + num2
        */
        
        // step 1 : initially, make frequency map
        HashMap<Integer, Integer> fmap = new HashMap<>(); // frequency map => store (num, freq)
        for(int num : nums) {
            fmap.put(num, fmap.getOrDefault(num, 0) + 1);
        }
        
        /* 
            step 2 : travel on hashmap, for every num, search (k + num) in HashMap
            if found, increament count by 1
        */ 
        int count = 0;
        for(int num : fmap.keySet()) {
            count += (k == 0 && fmap.get(num) > 1) ? 1 : k > 0 && fmap.containsKey(k + num) ? 1 : 0;
        }
        
        return count;
    }
}