import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class l001_hm {
    // ===================================================================================================================================
    // Question_1 : Find Itinerary From Tickets
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/find-itinerary-from-tickets-official/ojquestion
    private static void printPath(String key, HashMap<String, String> connectivity_map) {
        if (connectivity_map.containsKey(key) == false) {
            System.out.print(key + ".");
            return;
        }

        System.out.print(key + " -> ");
        printPath(connectivity_map.get(key), connectivity_map);
    }

    public static void findItinerary(HashMap<String, String> tickets) {
        HashMap<String, Boolean> st_pt_map = new HashMap<>(); // to find the starting point

        for (String from : tickets.keySet()) {
            String to = tickets.get(from);
            if (!st_pt_map.containsKey(from)) {
                st_pt_map.put(from, true);
            }

            st_pt_map.put(to, false);

        }

        for (String key : st_pt_map.keySet()) {
            if (st_pt_map.get(key) == true) {
                printPath(key, tickets);
                break;
            }
        }
    }

    // ===================================================================================================================================
    // Question_2 : Count distinct elements in every window
    // https://practice.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1/
    ArrayList<Integer> countDistinct(int arr[], int n, int k) {
        ArrayList<Integer> res = new ArrayList<>(); // result
        HashMap<Integer, Integer> map = new HashMap<>();

        // add element from 0 to k - 1 in a hashmap
        for (int i = 0; i < k - 1; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        for (int i = k - 1; i < n; i++) {
            // 1. add current element
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

            // 2. add size of map in result
            res.add(map.size());

            /*
             * 3. reduce frequency of first element of window,
             * if frequency is zero then remove it from map
             */
            int elem = arr[i - k + 1]; // 1st element of curr window
            map.put(elem, map.get(elem) - 1);
            if (map.get(elem) == 0)
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
        for (int elem : arr) {
            int rem = elem % k;
            if (rem < 0)
                rem += k;
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        // step 2 => travel on map and make pair => if pair not exist => return false
        boolean isPair = true;
        for (int rem : map.keySet()) {
            if (rem == 0 || gg) {
                if (map.get(rem) % 2 != 0) { // when freq is odd, one number is unpaired
                    isPair = false;
                    break;
                }
            } else {
                // num whose remainder = rem, can pair up with num whose remainder = k - rem
                int freq1 = map.get(rem);
                int freq2 = map.getOrDefault((k - rem), 0);

                // both the frequencies must be equal so that they can pair up with each other
                if (freq1 != freq2) {
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
        for (int elem : arr) {
            int rem = elem % k;
            if (rem < 0)
                rem += k;
            map[rem]++;
        }

        // step 2 => travel on map and make pair => if pair not exist => return false
        boolean isPair = true;
        for (int rem = 0; rem < k; rem++) {
            if (rem == 0 || k == 2 * rem) {
                if (map[rem] % 2 != 0) { // when freq is odd, one number is unpaired
                    isPair = false;
                    break;
                }
            } else {
                // num whose remainder = rem, can pair up with num whose remainder = k - rem
                int freq1 = map[rem];
                int freq2 = map[k - rem];

                // both the frequencies must be equal so that they can pair up with each other
                if (freq1 != freq2) {
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
    int maxLen(int arr[], int n) {
        HashMap<Integer, Integer> prefix_map = new HashMap<>(); // store (prefix_sum, index -> first occurance)
        prefix_map.put(0, -1); // helping to make answer which subarray is starting from 0 to i

        int prefix = 0;
        int length = 0;

        for (int i = 0; i < n; i++) {
            prefix += arr[i];
            if (prefix_map.containsKey(prefix) == false) {
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
    // Function to count subarrays with sum equal to 0.
    public static long findSubarray(long[] arr, int n) {
        /*
         * step => iterate on arr and store frequenies of prefix sum in hashmap and solve
         * number of subarray having sum is zero
         */
        HashMap<Integer, Integer> prefix_sum_fmap = new HashMap<>(); // store (prefix sum vs frequenies)
        prefix_sum_fmap.put(0, 1);
        int prefix = 0;
        int count = 0;
        for (long num : arr) {
            prefix += num; // prefix sum
            if (prefix_sum_fmap.containsKey(prefix) == false) {
                // if prefix not exist in hashmap => put prefix with frequency 1
                prefix_sum_fmap.put(prefix, 1);
            } else {
                /*
                 * if current prefix is already present in hashmap => add old frequency of
                 * prefix in count
                 * and increament frequency of current prefix by 1
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
    // Function to find longest subarrays with sum equal to 0.
    private int get_maxLen(int arr[], int n) {
        HashMap<Integer, Integer> prefix_map = new HashMap<>(); // store (prefix_sum, index)
        prefix_map.put(0, -1); // helping to make answer which subarray is starting from 0 to i

        int prefix = 0;
        int length = 0;

        for (int i = 0; i < n; i++) {
            prefix += arr[i];
            if (prefix_map.containsKey(prefix) == false) {
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
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0)
                arr[i] = -1;
        }

        // step 2 => solve for longest subarray having sum = 0
        int max_length = get_maxLen(arr, n);
        return max_length;
    }

    // ===================================================================================================================================
    // Question_7 : Subarrays with equal 1s and 0s
    // https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1/
    // Function to count subarrays with sum equal to 0.
    public static int findSubarray(int[] arr, int n) {
        /*
         * step => iterate on arr and store frequenies of elem in hashmap and solve
         * number of subarray having sum is zero
         */
        HashMap<Integer, Integer> prefix_sum_fmap = new HashMap<>(); // store (prefix sum vs frequenies)
        prefix_sum_fmap.put(0, 1);
        int prefix = 0;
        int count = 0;
        for (int num : arr) {
            prefix += num; // prefix sum
            if (prefix_sum_fmap.containsKey(prefix) == false) {
                // if prefix not exist in hashmap => put prefix with frequency 1
                prefix_sum_fmap.put(prefix, 1);
            } else {
                /*
                 * if current prefix is already present in hashmap => add old frequency of
                 * prefix in count
                 * and increament frequency of current prefix by 1
                 */
                count += prefix_sum_fmap.getOrDefault(prefix, 0);
                prefix_sum_fmap.put(prefix, prefix_sum_fmap.getOrDefault(prefix, 0) + 1);
            }
        }

        return count;
    }

    // Function to count subarrays with 1s and 0s.
    static int countSubarrWithEqualZeroAndOne(int arr[], int n) {
        // step 1 => convert all 0 to -1
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0)
                arr[i] = -1;
        }

        // step 2 => solve for count of subarray having sum = 0
        int count = findSubarray(arr, n);
        return count;
    }

    // ===================================================================================================================================
    // Question_8 : Longest Sub-Array with Sum K
    // https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1/
    // Function for finding maximum and value pair
    public static int lenOfLongSubarr(int nums[], int n, int k) {
        /*
         * num1 + num2 = k <=> num 2 = k - num1
         * 
         * step 1 => travel on nums and store prefix sum with first occurance index in
         * hashamp and
         * also for every prefix, search (k - prefix) in hashmap => if found, our ans
         * would be
         * max(length, i - map.get(prefix));
         */

        HashMap<Integer, Integer> prefix_map = new HashMap<>(); // store {prefix vs first occurance of prefix}
        prefix_map.put(0, -1);

        int prefix = 0; // prefix sum
        int length = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];

            // if prefix is not exist in map, simply put it
            if (prefix_map.containsKey(prefix) == false) {
                prefix_map.put(prefix, i);
            }

            // if (prefix - k) is exist in map, update length
            if (prefix_map.containsKey(prefix - k) == true) {
                length = Math.max(length, i - prefix_map.get(prefix - k));
            }
        }

        return length;

    }

    // ===================================================================================================================================
    // Question_9 : 560. Subarray Sum Equals K
    // https://leetcode.com/problems/subarray-sum-equals-k/
    public int subarraySum(int[] nums, int k) {
        /*
         * travel on nums, make prefix vs occurance of prefix
         * that maintain in hashmap and for every prefix check (prefix - k)
         * is contain in map or not, if found update count by map.get(prefix - k)
         * also increament occurance of prefix count by 1
         */

        HashMap<Integer, Integer> prefix_map = new HashMap<>(); // prefixsum vs occurance of prefix
        prefix_map.put(0, -1); // to handle subarray starting from 0th to ith index which sum is k

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

    // ===================================================================================================================================
    // Question_10 : Longest subarray with sum divisible by K
    // https://practice.geeksforgeeks.org/problems/longest-subarray-with-sum-divisible-by-k1259/1/
    int longSubarrWthSumDivByK(int nums[], int n, int k) {
        // step : traverse on nums, for every element find prefix sum and find reminder
        // for that prefix sum
        // if reminder is not present in hashmap => simply put reminder with current
        // index
        // if found => update length

        HashMap<Integer, Integer> rem_map = new HashMap<>(); // store => {reminder vs first occurance index for that
                                                             // reminder}
        rem_map.put(0, -1); // for handle subarray starting from 0 to i

        int prefix = 0, length = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];

            int rem = prefix % k;
            if (rem < 0)
                rem += k; // if rem is negative, make rem to positive

            if (rem_map.containsKey(rem) == false) {
                rem_map.put(rem, i);
            } else {
                length = Math.max(length, i - rem_map.get(rem));
            }
        }

        return length;

    }

    // ===================================================================================================================================
    // Question_11 : 974. Subarray Sums Divisible by K
    // https://leetcode.com/problems/subarray-sums-divisible-by-k/
    public int subarraysDivByK(int[] nums, int k) {
        // step : traverse on nums, for every element find prefix sum and find reminder
        // for that prefix sum
        // if reminder is not present in hashmap => simply put reminder with count 1
        // if found => update count for that reminder in hashmap

        HashMap<Integer, Integer> rem_map = new HashMap<>(); // store => {reminder vs number of occurance}
        rem_map.put(0, 1); // for handle subarray starting from 0 to i

        int prefix = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];

            int rem = prefix % k;
            if (rem < 0)
                rem += k; // if rem is negative, make rem to positive

            if (rem_map.containsKey(rem) == true)
                count += rem_map.get(rem);

            rem_map.put(rem, rem_map.getOrDefault(rem, 0) + 1);
        }

        return count;
    }

    // ===================================================================================================================================
    // Question_12 : Longest Subarray With Equal Number Of 0s 1s And 2s
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/longest-subarray-with-equal-number-of-0s-1s-and-2s-official/ojquestion
    public static int longestSsubarrayEqNum012(int[] arr) {
        // "a#b" => a : count1 - count0, b : count2 - count1
        HashMap<String, Integer> map = new HashMap<>(); // store => ("a#b" vs first occurance index)
        int count0 = 0, count1 = 0, count2 = 0;
        int length = 0;

        map.put("0#0", -1);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                count0++;
            } else if (arr[i] == 1) {
                count1++;
            } else {
                count2++;
            }

            int a = count1 - count0, b = count2 - count1;
            String key = a + "#" + b;
            if (map.containsKey(key) == false) {
                map.put(key, i);
            } else {
                // upadte length
                length = Math.max(length, i - map.get(key));
            }
        }

        return length;
    }

    // ===================================================================================================================================
    // Question_13 : Count Of Subarrays With Equal Number Of 0s 1s And 2s
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/count-of-subarrays-with-equal-number-of-0s-1s-and-2s-official/ojquestion
    public static int countSubarray012(int[] arr) {
        // "a#b" => a : count1 - count0, b : count2 - count1
        HashMap<String, Integer> map = new HashMap<>(); // store => ("a#b" vs number of occurance)
        int count0 = 0, count1 = 0, count2 = 0;
        int count = 0;

        map.put("0#0", 1);
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            } else {
                count2++;
            }

            int a = count1 - count0, b = count2 - count1;
            String key = a + "#" + b;
            if (map.containsKey(key) == true) {
                count += map.get(key);
            }

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return count;
    }

    // ===================================================================================================================================
    // Question_14 : Task Completion
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/task-completion-official/ojquestion
    public static void completeTask(int n, int m, int[] tasks) {
        ArrayList<Integer> s1 = new ArrayList<>(); // s1 => student 1
        ArrayList<Integer> s2 = new ArrayList<>(); // s2 => student 2
        HashSet<Integer> set = new HashSet<>();

        // step 1 : hashSet -> store already completed task
        for (int task : tasks) {
            set.add(task);
        }

        // step 2 : Make a loop from task 1 to N(total number of task)
        // if task is found in hashset => continue
        // otherwise => alternativly add task to student s1 and s2
        boolean flag = true;
        for (int task = 1; task <= n; task++) {
            if (set.contains(task) == true)
                continue;

            if (flag == true) {
                s1.add(task);
                flag = false;
            } else {
                s2.add(task);
                flag = true;
            }
        }

        for (int task : s1) {
            System.out.print(task + " ");
        }

        System.out.println();

        for (int task : s2) {
            System.out.print(task + " ");
        }
    }

    // ===================================================================================================================================
    // Question_15 : 76. Minimum Window Substring
    // https://leetcode.com/problems/minimum-window-substring/
    public String minWindow(String s, String t) {
        int n = s.length(), m = t.length();

        // 1. make frquency map of character of string t
        HashMap<Character, Integer> fmap = new HashMap<>(); // store => {character vs freq}
        for (int i = 0; i < m; i++) {
            char ch = t.charAt(i);
            fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
        }

        // 2. use acquire and release character techniques
        HashMap<Character, Integer> map = new HashMap<>();

        int acq = -1, rel = -1; // acq => acquire index , rel => release index
        String ans = "";

        int count = 0, requirement = t.length();
        while (true) {
            boolean acFlag = false; // acquire flag
            boolean relFlag = false; // release flag

            // acuire character from string "s" till valid
            while (acq < n - 1 && count < requirement) {
                acq++;

                // 1. add character in map
                char ch = s.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                // 2. conditional increament in count
                if (map.get(ch) <= fmap.getOrDefault(ch, 0)) {
                    count++;
                }

                // 3. make change acquire flag to true
                acFlag = true;
            }

            // start release character till invalid
            while (count == requirement) {
                // 1. hold answer, if substring is smaller
                String temp_ans = s.substring(rel + 1, acq + 1);
                if (ans.length() == 0 || temp_ans.length() < ans.length())
                    ans = temp_ans;

                // 2. hold releasing character and decreament its frequency from map
                rel++;
                char ch = s.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                // 3. if frquency becomes zero, remove it's key from map
                if (map.get(ch) == 0)
                    map.remove(ch);

                // 4. with invalid release decreament count
                if (map.getOrDefault(ch, 0) < fmap.getOrDefault(ch, 0))
                    count--;

                // 5. make change release flag to true
                relFlag = true;
            }

            // conditional break from loop
            if (!acFlag && !relFlag)
                break;
        }

        return ans;
    }

    // ===================================================================================================================================
    // Question_16 : Smallest distinct window
    // https://practice.geeksforgeeks.org/problems/smallest-distant-window3132/1/
    public String findSmallestSubStringContainUniqueCharacter(String str) {
        // Your code goes here
        int len = str.length();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            set.add(ch);
        }

        HashMap<Character, Integer> map = new HashMap<>();
        int acq = -1, rel = -1;
        String ans = "";
        while (true) {
            boolean acFlag = false, relFlag = false;

            // acquire
            while (acq < len - 1 && map.size() < set.size()) {
                acq++;

                // 1. add character in map with old_freq + 1
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                acFlag = true;
            }

            // release
            while (map.size() == set.size()) {
                // 1. hold ans and if subtring is smaller than old ans
                // int temp_len = acq - rel;
                // ans_len = Math.max(ans_len, temp_len);
                String temp_ans = str.substring(rel + 1, acq + 1);
                if (ans.length() == 0 || temp_ans.length() < ans.length())
                    ans = temp_ans;

                // 2. hold releasing character and decreament its frequency from map
                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                // 3. if frquency becomes zero, remove it's key from map
                if (map.get(ch) == 0)
                    map.remove(ch);

                relFlag = true;
            }

            // conditional break from loop
            if (!acFlag && !relFlag)
                break;
        }

        return ans;
    }

    // ===================================================================================================================================
    // Question_17 : 3. Longest Substring Without Repeating Characters
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap<>(); // character vs frequency
        int acq = -1, rel = -1; // acq -> acquire, rel -> release
        int ans_len = 0;

        while (true) {
            boolean acFlag = false, relFlag = false;

            // acquire
            while (acq < len - 1) {
                acFlag = true;

                // 1. add charater with map with old_freq + 1
                acq++;
                char ch = s.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                // 2. if current charater's frequency is become 2
                // => stop acquire more character and start release character
                // otherwise hold ans if substring is larger length from previous length
                if (map.get(ch) == 2) {
                    // stop aquire loop
                    break;
                } else {
                    int temp_len = acq - rel;
                    ans_len = Math.max(ans_len, temp_len);
                }

            }

            // release
            while (rel < acq) {
                relFlag = true;

                // 1. hold releasing character and decreasing frequency of it
                rel++;
                char ch = s.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                // 2. releasing character's freq. becomes 1 which means this char. is
                // responsible for repitition
                // break from loop
                if (map.get(ch) == 1) {
                    // stop release
                    break;
                }
            }

            if (!acFlag && !relFlag) {
                break;
            }
        }

        return ans_len;
    }

    // ===================================================================================================================================
    // Question_18 : Count Of Substrings Having All Unique Characters
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/count-of-substrings-having-all-unique-characters-official/ojquestion
    public static int countSubstringWithUniqueChar(String s) {
        int n = s.length();

        HashMap<Character, Integer> map = new HashMap<>(); // store => {charcter vs frequency}
        int acq = -1, rel = -1; // acq => acquire, rel => release
        int count = 0;

        // do acquire and release and solve for Count Unique Characters of All
        // Substrings of a Given String
        while (true) {
            boolean acFlag = false;
            boolean relFlag = false;

            // acquire
            while (acq < n - 1) {
                // 1. while entering to acquire loop, make acFlag to true
                acFlag = true;

                // 2. hold acquiring character and increament frequency
                acq++;
                char ch = s.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                // 3. conditional break when frequency of acquiring character becomes 2
                if (map.get(ch) == 2) {
                    break;
                } else {
                    // 4. add (acq - rel) in count
                    count += acq - rel;
                }

            }

            // release
            while (rel < acq) {
                // 1. while entering to release loop, make relFlag to true
                relFlag = true;

                // 2. hold releasing character and decreament it's frequency
                rel++;
                char ch = s.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                // 3. conditional break if releasing character's freuquency reduce to 1 and
                // before break the loop, add (acq - rel) in count
                if (map.get(ch) == 1) {
                    count += acq - rel;
                    break;
                }
            }

            if (!acFlag && !relFlag) {
                break;
            }
        }

        return count;
    }

    // ===================================================================================================================================
    // Question_19 : Longest K unique characters substring
    // https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1/
    public int longestkSubstr(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>(); // store {character vs frequency}
        int acq = -1, rel = -1; // acq => acquire, rel => release
        int long_len = -1;

        while (true) {
            boolean acFlag = false, relFlag = false;

            // acquire
            while (acq < s.length() - 1) {
                acFlag = true;

                // 1. hold acquiring character and increament it's frequency
                acq++;
                char ch = s.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                // 2. conditiona
                if (map.size() < k) {
                    continue;
                } else if (map.size() == k) {
                    long_len = Math.max(long_len, acq - rel);
                } else {
                    break;
                }
            }

            // release
            while (rel < acq) {
                relFlag = true;

                // 1. hold releasing character and decreament it's frequency
                rel++;
                char ch = s.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                if (map.get(ch) == 0) {
                    map.remove(ch);
                }

                if (map.size() > k) {
                    continue;
                } else if (map.size() == k) {
                    break;
                }
            }

            if (acFlag == false && relFlag == false)
                break;
        }

        return long_len;
    }

    // ===================================================================================================================================
    // Question_20 : Count Of Substrings With Exactly K Unique Characters
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/count-of-substrings-with-exactly-k-unique-characters-official/ojquestion
    private static int hangleWhenK1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int acq = -1, rel = -1;
        int count = 0;

        while (true) {
            boolean flag1 = false, flag2 = false;

            // 1. acquire
            while (acq < s.length() - 1) {
                flag1 = true;

                acq++;
                char ch = s.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.size() == 2) {
                    map.remove(ch);
                    acq--;
                    break;
                }
            }

            // 2. release
            while (rel < acq) {
                flag2 = true;

                count += acq - rel;

                rel++;
                char ch = s.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                if (map.get(ch) == 0) {
                    map.remove(ch);
                }
            }

            if (flag1 == false && flag2 == false)
                break;

        }

        return count;
    }

    public static int countSubstrKuniqueChar(String s, int k) {
        if (k == 1) {
            return hangleWhenK1(s);
        }

        int len = s.length();
        HashMap<Character, Integer> bigMap = new HashMap<>(); // storing (character vs it's frequency) upto size k
        HashMap<Character, Integer> smallMap = new HashMap<>(); // storing (character vs it's frequency) upto size k - 1

        int acq_big = -1, acq_small = -1; // acq_big => acquiring character for big hashmap
                                          // acq_small => acquiring character for small hashmap
        int rel = -1;

        int count = 0;
        while (true) {
            boolean flag1 = false, flag2 = false, flag3 = false;

            // 1. acquiring character for big hashmap
            while (acq_big < len - 1) {
                flag1 = true;

                acq_big++;
                char ch = s.charAt(acq_big);
                bigMap.put(ch, bigMap.getOrDefault(ch, 0) + 1);

                if (bigMap.size() == k + 1) {
                    bigMap.remove(ch);
                    acq_big--;
                    break;
                }
            }

            // 2. acquiring character for small hashmap
            while (acq_small < len - 1) {
                flag2 = true;

                acq_small++;
                char ch = s.charAt(acq_small);
                smallMap.put(ch, smallMap.getOrDefault(ch, 0) + 1);

                if (smallMap.size() == k) {
                    smallMap.remove(ch);
                    acq_small--;
                    break;
                }
            }

            // 3. releasing character from both hashmap
            while (rel < acq_small) {
                flag3 = true;

                // make count
                count += (acq_big - acq_small);

                rel++;
                char ch = s.charAt(rel);
                bigMap.put(ch, bigMap.get(ch) - 1);
                if (bigMap.get(ch) == 0)
                    bigMap.remove(ch);

                smallMap.put(ch, smallMap.get(ch) - 1);
                if (smallMap.get(ch) == 0)
                    smallMap.remove(ch);

                if (bigMap.size() < k || smallMap.size() < k - 1)
                    break;
            }

            if (!flag1 && !flag2 && !flag3)
                break;
        }

        return count;
    }

    // ===================================================================================================================================
    // Question_21 : Maximum Consecutive Ones - 1
    public static int maxConsecutiveOnes1(int[] nums) {
        int zeroCount = 0;
        int rel = -1;

        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zeroCount++;

            while (zeroCount > 1) {
                rel++;
                if (nums[rel] == 0)
                    zeroCount--;
            }

            len = Math.max(len, i - rel);
        }

        return len;
    }

    // ===================================================================================================================================
    // Question_22 : 1004. Max Consecutive Ones III
    // https://leetcode.com/problems/max-consecutive-ones-iii/
    public int longestOnes(int[] nums, int k) {
        int zeroCount = 0;
        int rel = -1;

        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zeroCount++;

            while (zeroCount > k) {
                rel++;
                if (nums[rel] == 0)
                    zeroCount--;
            }

            len = Math.max(len, i - rel);
        }

        return len;
    }

    // ===================================================================================================================================
    // Question_23 : Largest Subarray With Contiguous Elements
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/largest-subarray-with-contiguous-elements-official/ojquestion
    public static int largestSubarrayWithContiguous(int[] arr) {
        int len = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>(); // store element for ensure dublicacy not add in ans
            int min = arr[i], max = arr[i];

            for (int j = i; j < n; j++) {
                int ele = arr[j];
                if (set.contains(ele) == true) // if current element is already present in hashset, simply break the
                                               // loop
                    break;

                // add curr elem in set
                set.add(ele);

                // update min and max value
                min = Math.min(min, ele);
                max = Math.max(max, ele);

                // find (max - min)
                int diff = max - min;

                // if diff value is greater than length of the array, means we cann't get the
                // ans from current loop, simply break the loop
                if (diff > n)
                    break;

                // check getting diff value with j - j
                if (diff == j - i) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }

        return len;
    }

    // ===================================================================================================================================
    // Question_24 : Longest Substring With At Most K Unique Characters
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/longest-substring-with-at-most-k-unique-characters-official/ojquestion
    public static int longestSubstrKUnique(String str, int k) {
        HashMap<Character, Integer> map = new HashMap<>(); // store {character vs it's frequency
        int acq = -1, rel = -1, len = 0, n = str.length();

        while (true) {
            boolean flag1 = false, flag2 = false;

            // 1. acquire
            while (acq < n - 1) {
                flag1 = true;

                acq++;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.size() > k) {
                    break;
                } else {
                    len = Math.max(len, acq - rel);
                }
            }

            // 2. release
            while (rel < acq) {
                flag2 = true;

                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                if (map.get(ch) == 0) {
                    map.remove(ch);
                }

                if (map.size() == k) {
                    break;
                }
            }

            if (!flag1 && !flag2)
                break;
        }

        return len;

    }

    // ===================================================================================================================================
    // Question_25 : Count Of Substrings Having At Most K Unique Characters
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/count-of-substrings-having-at-most-k-unique-characters-official/ojquestion
    public static int countSubstrKUnique(String str, int k) {
        HashMap<Character, Integer> map = new HashMap<>(); // store {char vs integer}
        int acq = -1, rel = -1; // acq => acquire, rel => release
        int count = 0, len = str.length();

        while (true) {
            boolean flag1 = false, flag2 = false;

            // 1. acquire
            while (acq < len - 1) {
                flag1 = true;

                acq++;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.size() > k) {
                    break;
                } else {
                    count += acq - rel;
                }
            }

            if (acq == len - 1 && map.size() <= k)
                break;

            // 2. release
            while (rel < acq) {
                flag2 = true;

                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                if (map.get(ch) == 0) {
                    map.remove(ch);
                }

                if (map.size() == k) {
                    count += acq - rel;
                    break;
                }
            }

            if (!flag1 && !flag2) {
                break;
            }
        }

        return count;
    }

    // ===================================================================================================================================
    // Question_27 : 242. Valid Anagram
    // https://leetcode.com/problems/valid-anagram/
    public boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            if (map.containsKey(ch) == false) {
                return false;
            }

            map.put(ch, map.get(ch) - 1);
            if (map.get(ch) == 0) {
                map.remove(ch);
            }
        }

        return map.size() == 0 ? true : false;
    }

    // ===================================================================================================================================
    // Question_28 : 438. Find All Anagrams in a String
    // https://leetcode.com/problems/find-all-anagrams-in-a-string/
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>(); // storing start indices of p's anagrams in s
        if (p.length() > s.length())
            return list;

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
                list.add(rel + 1);
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
            list.add(rel + 1);
        }

        return list;
    }

    // ===================================================================================================================================
    // Question_29 : Check if two strings are k-anagrams or not
    // https://practice.geeksforgeeks.org/problems/check-if-two-strings-are-k-anagrams-or-not/1/
    boolean areKAnagrams(String s1, String s2, int k) {
        if (s1.length() != s2.length())
            return false;

        // 1. make frequency map for s1
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // 2. reduce mapping from s2
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            if (map.containsKey(ch) == true && map.get(ch) > 0) {
                map.put(ch, map.get(ch) - 1);
            }
        }

        // 3. add +ve count
        int count = 0;
        for (char key : map.keySet()) {
            count += map.get(key);
        }

        return count <= k;
    }

    // ===================================================================================================================================
    // Question_30 : 49. Group Anagrams
    // https://leetcode.com/problems/group-anagrams/
    private HashMap<Character, Integer> getFreqMap(String str) {
        HashMap<Character, Integer> fmap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
        }

        return fmap;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        // store {hashmap of character vs integer} vs arraylist of string
        HashMap<HashMap<Character, Integer>, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            HashMap<Character, Integer> fmap = getFreqMap(str);

            if (map.containsKey(fmap) == false) {
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(fmap, list);
            } else {
                map.get(fmap).add(str);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (ArrayList<String> list : map.values()) {
            ans.add(list);
        }

        return ans;
    }

    // ===================================================================================================================================
    // Question_31 : Group Shifted Strings
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/group-shifted-strings/ojquestion
    private static String getStringCode(String str) {
        String code = "";
        for (int i = 1; i < str.length(); i++) {
            char prev_ch = str.charAt(i - 1);
            char curr_ch = str.charAt(i);

            int val = (int) (curr_ch - prev_ch);

            if (val >= 0) {
                code += val;
            } else {
                code += (val + 26);
            }
            code += "#"; // # => separator
        }
        code += "."; // to hadle single character, we add "."
        return code;
    }

    public static List<List<String>> groupStrings(String[] strings) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (String str : strings) {
            String code = getStringCode(str);

            if (map.containsKey(code) == true) {
                map.get(code).add(str);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(code, list);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (ArrayList<String> list : map.values()) {
            ans.add(list);
        }

        return ans;

    }

    // ===================================================================================================================================
    // Question_32 : 205. Isomorphic Strings
    // https://leetcode.com/problems/isomorphic-strings/
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Character> map = new HashMap<>(); // store mapping of char1 with char2
        HashSet<Character> set = new HashSet<>(); // store already mapped character

        // must be satisfy one to one mapping
        // if ch1 mapping with ch2 then ch2 also mapping with ch1
        // otherwise return false
        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            if (map.containsKey(ch1) == true) {
                // if ch1 is present then it must be mapped with ch2
                if (map.get(ch1) != ch2) {
                    return false;
                }
            } else {
                // if ch1 is not mapped but ch2 is mapped => return false
                if (set.contains(ch2) == true) {
                    return false;
                }

                map.put(ch1, ch2);
                set.add(ch2);
            }
        }

        return true;
    }

    // ===================================================================================================================================
    // Question_33 : 290. Word Pattern
    // https://leetcode.com/problems/word-pattern/
    public boolean wordPattern(String pattern, String strs) {
        String[] str_arr = strs.split(" ");
        if (pattern.length() != str_arr.length)
            return false;

        HashMap<Character, String> map = new HashMap<>(); // store character -> ch of pattern vs string -> str of
                                                          // str_arr
        HashSet<String> set = new HashSet<>(); // store String of str_arr which means String is already mapped

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String str = str_arr[i];

            if (map.containsKey(ch) == true) {
                // // if ch is present then it must be mapped with String str
                // otherwise return false
                if (map.get(ch).equals(str) == false) {
                    return false;
                }
            } else {
                // if ch is not mapped but String str is mapped => return false
                if (set.contains(str) == true) {
                    return false;
                }

                map.put(ch, str);
                set.add(str);
            }
        }

        return true;
    }

    // ===================================================================================================================================
    // Question_34 : 1502. Can Make Arithmetic Progression From Sequence
    // https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
    public boolean canMakeArithmeticProgression(int[] arr) {
        HashSet<Integer> set = new HashSet<>(); // store number
        int n = arr.length, min = (int) 1e9, max = -(int) 1e9;
        for (int num : arr) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            set.add(num);
        }

        int d = (max - min) / (n - 1);
        int sum = min;
        while (sum < max) {
            sum += d;
            if (set.contains(sum) == false) {
                return false;
            }
        }

        return true;
    }

    // ===================================================================================================================================
    // Question_35 : 781. Rabbits in Forest
    // https://leetcode.com/problems/rabbits-in-forest/
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> fmap = new HashMap<>(); // storing number vs its frequency
        for (int num : answers) {
            fmap.put(num, fmap.getOrDefault(num, 0) + 1);
        }

        int count = 0; // making count of total rabbit in a forest
        for (int key : fmap.keySet()) {
            int freq = fmap.get(key);

            count += (key + 1) * (int) Math.ceil(freq * 1.0 / (key + 1));
        }

        return count;
    }

    // ===================================================================================================================================
    // Question_36 : 166. Fraction to Recurring Decimal
    // https://leetcode.com/problems/fraction-to-recurring-decimal/
    public String fractionToDecimal(int num, int den) {
        if (num == 0) {
            return "0";
        }

        long n = num; // divident
        long d = den; // devisor
        boolean n1 = n < 0; // check divident is negative or not
        boolean n2 = d < 0; // check devisor is negative or not

        // make devident and devisor to positive number
        n = Math.abs(n); 
        d = Math.abs(d);

        StringBuilder ans = new StringBuilder();
        long q = n / d; // quotient
        long r = n % d; // remainder
        ans.append(q);

        if (r == 0) {
            // handle for negative
            if ((n1 == true && n2 == false) || (n1 == false && n2 == true)) {
                ans.insert(0, "-");
            }
            return ans.toString();
        }

        ans.append("."); // decimal
        HashMap<Long, Integer> map = new HashMap<>(); // store num vs index
        while (r != 0) {
            map.put(r, ans.length()); // mapping remainder vs it's index in ans

            r *= 10; // make remainder 10 times
            q = r / d; // again find quotient
            r = r % d; // again find remainder
            ans.append(q); // add quotient

            if (map.containsKey(r) == true) {
                int si = map.get(r); // starting index
                ans.insert(si, "(");
                ans.append(")");
                break;
            }
        }

        // handle for negative
        if ((n1 == true && n2 == false) || (n1 == false && n2 == true)) {
            ans.insert(0, "-");
        }

        return ans.toString();
    }

    // ===================================================================================================================================
    // Question_37 : Equivalent Sub-Array
    // https://practice.geeksforgeeks.org/problems/equivalent-sub-arrays3731/1#
    // Method to calculate distinct sub-array 
    static int countDistinctSubarray(int arr[], int n) 
    { 
        HashSet<Integer> set = new HashSet<>(); // store distinct element
        for(int ele : arr) {
            set.add(ele);
        }
        
        int k = set.size();
        
        HashMap<Integer, Integer> fmap = new HashMap<>(); // store {num vs its occurrance}
        int acq = -1, rel = -1; // acq -> acquire, rel -> release
        int count = 0; // making ans
        
        while(true) {
            boolean flag1 = false, flag2 = false;
            
            // 1. acquire
            while(acq < n - 1) {
                flag1 = true;
                
                acq++;
                int num = arr[acq];
                fmap.put(num, fmap.getOrDefault(num, 0) + 1);
                if(fmap.size() == k) {
                    //  make ans
                    count += n - acq;
                    break;
                }
            }
            
            // 2. release
            while(rel < acq) {
                flag2 = true;
                
                rel++;
                int num = arr[rel];
                fmap.put(num, fmap.get(num) - 1);
                if(fmap.get(num) == 0) {
                    fmap.remove(num);
                }
                
                if(fmap.size() == k) {
                    // make ans
                    count += n - acq;
                } else {
                    break;
                }
            }
            
            if(flag1 == false && flag2 == false) {
                break;
            }
        }
        
        return count;
    }

    // ===================================================================================================================================
    // Question_38 : Pairs With Equal Sum
    // https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/heap-and-hashmap-l2/pairs-with-equal-sum-official/ojquestion
    public static boolean paitWithEqualSum(int[] arr) {
        // write your code here
        HashSet<Integer> set = new HashSet<>(); // store pair sum
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                int ele1 = arr[i], ele2 = arr[j];
                int pairsum = ele1 + ele2;
                
                if(set.contains(pairsum) == false) {
                    set.add(pairsum);
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    // 16 jan, 2022 not done


}