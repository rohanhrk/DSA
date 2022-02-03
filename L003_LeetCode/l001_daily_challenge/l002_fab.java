public class l002_fab {
    // ================================================================================================================================
    /*
        DAY_1 =>
        Question_1 : 121. Best Time to Buy and Sell Stock 
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
        DAY_2 =>
        Question_2 : 438. Find All Anagrams in a String
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
        DAY_3 =>
        Question_3 : 454. 4Sum II
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
                
            CD = target - AB
            
            step 2 => Now search (target - AB) in map. If found => increament count by 1\
            
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
}