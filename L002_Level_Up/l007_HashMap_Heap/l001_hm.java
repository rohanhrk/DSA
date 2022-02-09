public class l001_hm {
    // ===================================================================================================================================
    // Question_1 : Find Itinerary From Tickets
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/find-itinerary-from-tickets-official/ojquestion
     private static void printPath(String key, HashMap<String, String> connectivity_map) {
        if(connectivity_map.containsKey(key) == false) {
            System.out.print(key + ".");
            return;
        }
        
        System.out.print(key + " -> ");
        printPath(connectivity_map.get(key), connectivity_map);
    }
    public static void findItinerary(HashMap<String, String> tickets) {
        HashMap<String, Boolean> st_pt_map = new HashMap<>(); // to find the starting point
        
        for(String from : tickets.keySet()) {
            String to = tickets.get(from);
            if(!st_pt_map.containsKey(from)) {
                st_pt_map.put(from, true);
            }
            
            st_pt_map.put(to, false);

        }
        
        for(String key : st_pt_map.keySet()) {
            if(st_pt_map.get(key) == true) {
                printPath(key, tickets);
                break;
            }
        }
    }

    // ===================================================================================================================================
    // Question_2 : Count distinct elements in every window
    // https://practice.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1/
    ArrayList<Integer> countDistinct(int arr[], int n, int k)
    {
        // code here 
        ArrayList<Integer> res = new ArrayList<>(); // result
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < k - 1; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        
        for(int i = k - 1; i < n; i++) {
            // 1. add current element
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            
            // 2. add size of map in result
            res.add(map.size());
            
            /*
                3. reduce frequency of first element of window, 
                if frequency is zero then remove it from map
            */ 
            int elem = arr[i - k + 1]; // 1st element of curr window
            map.put(elem, map.get(elem) - 1);
            if(map.get(elem) == 0)
                map.remove(elem);
        }
        
        return res;
    }

    // ===================================================================================================================================
    // Question_3 : 1497. Check If Array Pairs Are Divisible by k
    // https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/
     public boolean canArrange(int[] arr, int k) {
        // step 1 => make frequency map of reminder of all element
        HashMap<Integer, Integer> map = new HashMap<>(); // reminder map => rem vs freq
        for(int elem : arr) {
            int rem = elem % k;
            if(rem < 0)
                rem += k;
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }
        
        // step 2 => travel on map and make pair => if pair not exist => return false
        boolean isPair = true;
        for(int rem : map.keySet()) {
            if(rem == 0 || k == 2 * rem) {
                if(map.get(rem) % 2 != 0) { // when freq is odd, one number is unpaired
                    isPair = false;
                    break;
                }
            }  else {
                // num whose remainder = rem, can pair up with num whose remainder = k - rem
                int freq1 = map.get(rem);
                int freq2 = map.getOrDefault((k - rem), 0);
                
                // both the frequencies must be equal so that they can pair up with each other
                if(freq1 != freq2) { 
                    isPair = false;
                    break;
                }
            }
        }
        
        return isPair;
    }

    /* mathod 2 => */ 
    public boolean canArrange_02(int[] arr, int k) {
        // step 1 => make frequency map of reminder of all element
        int[] map = new int[k]; // reminder map => rem vs freq
        for(int elem : arr) {
            int rem = elem % k;
            if(rem < 0)
                rem += k;
            map[rem]++;
        }
        
        // step 2 => travel on map and make pair => if pair not exist => return false
        boolean isPair = true;
        for(int rem = 0; rem < k; rem++) {
            if(rem == 0 || k == 2 * rem) {
                if(map[rem] % 2 != 0) { // when freq is odd, one number is unpaired
                    isPair = false;
                    break;
                }
            }  else {
                // num whose remainder = rem, can pair up with num whose remainder = k - rem
                int freq1 = map[rem];
                int freq2 = map[k - rem];
                
                // both the frequencies must be equal so that they can pair up with each other
                if(freq1 != freq2) { 
                    isPair = false;
                    break;
                }
            }
        }
        
        return isPair;
    }

    // ===================================================================================================================================
    // Question_4 : Largest subarray with 0 sum 
    // https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1/#
    int maxLen(int arr[], int n)
    {
        HashMap<Integer, Integer> prefix_map = new HashMap<>(); // store (prefix_sum, index)
        prefix_map.put(0, -1); // helping to make answer which subarray is starting from 0 to i
        
        int prefix = 0;
        int length = 0;
        
        for(int i = 0; i < n; i++) {
            prefix += arr[i];
            if(prefix_map.containsKey(prefix) == false) {
                prefix_map.put(prefix, i);
            } else {
                length = Math.max(length, i - prefix_map.get(prefix));
            }
        }
        
        return length;
    }

    // ===================================================================================================================================
    // Question_5 : Zero Sum Subarrays
    // https://practice.geeksforgeeks.org/problems/zero-sum-subarrays1825/1/
    //Function to count subarrays with sum equal to 0.
    public static long findSubarray(long[] arr ,int n) 
    {
        /*
		    step => iterate on arr and store frequenies of elem in hashmap and solve number of subarray having sum is zero
		*/ 
		HashMap<Integer,Integer> prefix_sum_fmap = new HashMap<>(); // store (prefix sum vs frequenies)
		prefix_sum_fmap.put(0, 1);
		int prefix = 0;
		int count = 0;
		for(long num : arr) {
		    prefix += num; // prefix sum
		    if(prefix_sum_fmap.containsKey(prefix) == false) {
		        // if prefix not exist in hashmap => put prefix with frequency 1
		        prefix_sum_fmap.put(prefix, 1);
		    } else {
		        /*
    		        if current prefix is already present in hashmap => add old frequency of prefix in count
    		        and increament frequency of current prefix by 1
		        */ 
		        count += prefix_sum_fmap.getOrDefault(prefix, 0);
		        prefix_sum_fmap.put(prefix, prefix_sum_fmap.getOrDefault(prefix, 0) + 1);
		    }
		}

		return count;
    }

    // ===================================================================================================================================
    // Question_6 : 525. Contiguous Array
    // https://leetcode.com/problems/contiguous-array/
    //Function to find longest subarrays with sum equal to 0.
    private int maxLen(int arr[], int n) {
        HashMap<Integer, Integer> prefix_map = new HashMap<>(); // store (prefix_sum, index)
        prefix_map.put(0, -1); // helping to make answer which subarray is starting from 0 to i
        
        int prefix = 0;
        int length = 0;
        
        for(int i = 0; i < n; i++) {
            prefix += arr[i];
            if(prefix_map.containsKey(prefix) == false) {
                prefix_map.put(prefix, i);
            } else {
                length = Math.max(length, i - prefix_map.get(prefix));
            }
        }
        
        return length;
    }
    public int findMaxLength(int[] arr) {
        // step 1 => convert all 0 to -1
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0)
                arr[i] = -1; 
        }
        
        // step 2 => solve for longest subarray having sum = 0
        int max_length =  maxLen(arr, n);
        return max_length;
    }

    // ===================================================================================================================================
    // Question_7 : Subarrays with equal 1s and 0s
    // https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1/
    //Function to count subarrays with sum equal to 0.
    public static int findSubarray(int[] arr ,int n) 
    {
        /*
		    step => iterate on arr and store frequenies of elem in hashmap and solve number of subarray having sum is zero
		*/ 
		HashMap<Integer,Integer> prefix_sum_fmap = new HashMap<>(); // store (prefix sum vs frequenies)
		prefix_sum_fmap.put(0, 1);
		int prefix = 0;
		int count = 0;
		for(int num : arr) {
		    prefix += num; // prefix sum
		    if(prefix_sum_fmap.containsKey(prefix) == false) {
		        // if prefix not exist in hashmap => put prefix with frequency 1
		        prefix_sum_fmap.put(prefix, 1);
		    } else {
		        /*
    		        if current prefix is already present in hashmap => add old frequency of prefix in count
    		        and increament frequency of current prefix by 1
		        */ 
		        count += prefix_sum_fmap.getOrDefault(prefix, 0);
		        prefix_sum_fmap.put(prefix, prefix_sum_fmap.getOrDefault(prefix, 0) + 1);
		    }
		}

		return count;
    }

    //Function to count subarrays with 1s and 0s.
    static int countSubarrWithEqualZeroAndOne(int arr[], int n)
    {
        // step 1 => convert all 0 to -1
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0)
                arr[i] = -1; 
        }
        
        // step 2 => solve for count of subarray having sum = 0
        int count =  findSubarray(arr, n);
        return count;
    }
}