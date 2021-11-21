import java.util.HashMap;
import java.util.ArrayList;

// **************************************_DATE:-14/07/21_**************************************
public class l001_basic {
    // BASIC
    public static void basic_01() {
        HashMap<String, Integer> map = new HashMap<>();
        // put -> contains (key,value) pair
        map.put("Nepal", 233);
        map.put("UK", 45);
        map.put("Germany", 35);
        map.put("USA", 20);
        map.put("Russia", 18);
        map.put("India", 10);

        System.out.println(map);

        map.put("USA", 19); // key "USA" already present in HashMap, so old value update to new value
        map.put("china", 15); // key "china" not present in HashMap, so key 'china' inserted in hashmap

        System.out.println(map);

        // get -> returns the value to which specified key is mapped or
        // returns 'null' if this map contains no mapping for the key
        System.out.println(map.get("India")); // 10
        System.out.println(map.get("Nigeria")); // null

        String key = "Nigeria";
        if (map.containsKey(key)) // containsKey -> returns true if this map contains mapping for the specified
                                  // key
            System.out.println(map.get(key));
        else
            System.out.println("not found");

    }

    // ======================================================================================================================================================================================================
    // ******************_print_frequency_******************
    public static void printFrequency(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        // mathod 1
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        // mathod 2
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        System.out.println(map);
    }

    // ======================================================================================================================================================================================================
    // ******************_highest_frequency_character_******************
    public static void highestFreqChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        char ans = ' ';
        int freq = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (map.get(ch) > freq) {
                freq = map.get(ch);
                ans = ch;
            }

        }

        System.out.println(ans);
    }

    // ======================================================================================================================================================================================================
    // ******************_position_of_character_******************
    public static void positionOfCharacter(String str) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
          
        // mathod 1
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!map.containsKey(ch))
                map.put(ch, new ArrayList<>());
            map.get(ch).add(i);
        }
        
        // mathod 2
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.putIfAbsent(ch, new ArrayList<>());
            map.get(ch).add(i);
        }

        System.out.println(map);
    }
    
    // ======================================================================================================================================================================================================
    // ******************_intersection_of_twoArray_without_duplicates_******************
    public static void intersectionOfTwoArrayWithoutDuplicates(int[] a1, int[] a2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int elem : a1) {
            map.put(elem, map.getOrDefault(elem, 0) + 1);
        }

        for (int elem : a2) {
            if (map.containsKey(elem)) {
                System.out.println(elem);
                map.remove(elem);
            }
        }

    }

    // ======================================================================================================================================================================================================
    // ******************_intersection_of_twoArray_with_duplicates_******************
    public static void intersectionOfTwoArrayWithDuplicates(int[] a1, int[] a2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int elem : a1) {
            map.put(elem, map.getOrDefault(elem, 0) + 1);
        }

        for (int elem : a2) {
            if (map.containsKey(elem)) {
                System.out.println(elem);
                map.put(elem, map.get(elem) - 1);
                if (map.get(elem) == 0)
                    map.remove(elem);
            }
        }

    }

    // ======================================================================================================================================================================================================
    // ******************_longest_consecutive_subsequense_******************
    public static void longestConsecutivSubsequence(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int elem : arr)
            map.put(elem, map.getOrDefault(elem, 0) + 1);

        int sp = 0;
        int maxLength = 0;
        for (int elem : arr) {
            if (!map.containsKey(elem))
                continue;

            map.remove(elem);
            int leftElem = elem - 1;
            int rightElem = elem + 1;

            while (map.containsKey(leftElem)) {
                map.remove(leftElem);
                leftElem--;
            }

            while (map.containsKey(rightElem)) {
                map.remove(rightElem);
                rightElem++;
            }

            int length = rightElem - leftElem - 1;
            if (length > maxLength) {
                maxLength = length;
                sp = leftElem + 1;
            }
        }

        for (int i = 0; i < maxLength; i++) {
            System.out.println(sp + i);
        }
    }

    public static void main(String[] args) {
        int[] a1 = { 12, 5, 1, 2, 10, 2, 13, 7, 11, 8, 9, 11, 8, 9, 5, 6, 11 };
        longestConsecutivSubsequence(a1);
    }
}
