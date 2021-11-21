import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class question {
    // Question_1 : 242. Valid Anagram
    // https://leetcode.com/problems/valid-anagram/
    // using frequency array
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

    // Question_2 : 49. Group Anagrams
    // https://leetcode.com/problems/group-anagrams/
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
            String rels = RLES(s);
            map.putIfAbsent(rels, new ArrayList<>());
            map.get(rels).add(s);
        }

        List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet())
            ans.add(map.get(key));

        return ans;
    }
}