import java.util.List;
import java.util.ArrayList;

public class trie {
    // =========================================================================================================================================================================
    // Question_1 : 208. Implement Trie (Prefix Tree)
    // https://leetcode.com/problems/implement-trie-prefix-tree/
    class Trie {
        private class Node {
            Node[] children;
            boolean isWordEnd;

            Node() {
                this.children = new Node[26];
                this.isWordEnd = false;
            }
        }

        private Node root = null;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node ptr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ptr.children[ch - 'a'] == null) {
                    Node nn = new Node();
                    ptr.children[ch - 'a'] = nn;
                }
                ptr = ptr.children[ch - 'a'];
            }

            ptr.isWordEnd = true;
        }

        public boolean search(String word) {
            Node ptr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ptr.children[ch - 'a'] == null)
                    return false;

                ptr = ptr.children[ch - 'a'];
            }

            return ptr.isWordEnd;
        }

        public boolean startsWith(String word) {
            Node ptr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ptr.children[ch - 'a'] == null)
                    return false;

                ptr = ptr.children[ch - 'a'];
            }

            return true;
        }
    }

    // =========================================================================================================================================================================
    // Question_2 : 211. Design Add and Search Words Data Structure
    // https://leetcode.com/problems/design-add-and-search-words-data-structure/
    class WordDictionary {
        private class Node {
            Node[] children;
            boolean isWordEnd;

            Node() {
                this.children = new Node[26];
                this.isWordEnd = false;
            }
        }

        private Node root = null;

        public WordDictionary() {
            this.root = new Node();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            Node ptr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ptr.children[ch - 'a'] == null) {
                    Node child = new Node();
                    ptr.children[ch - 'a'] = child;
                }
                ptr = ptr.children[ch - 'a'];
            }

            ptr.isWordEnd = true;
        }

        public boolean find(Node node, String word, int idx) {
            if (idx == word.length())
                return node.isWordEnd;

            char ch = word.charAt(idx);
            if (ch == '.') {
                for (int i = 0; i < 26; i++) {
                    Node child = node.children[i];
                    if (child != null && find(child, word, idx + 1))
                        return true;
                }
            } else {
                if (node.children[ch - 'a'] == null)
                    return false;
                return find(node.children[ch - 'a'], word, idx + 1);
            }

            return false;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot
         * character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return find(root, word, 0);
        }
    }

    // =========================================================================================================================================================================
    // Question_3 : 212. Word Search II
    // https://leetcode.com/problems/word-search-ii/
    private class Node {
        Node[] children;
        boolean isWordEnd;
        int freq;

        Node() {
            this.children = new Node[26];
            this.isWordEnd = false;
            this.freq = 0;
        }
    }

    private void insert(Node root, String word) {
        Node ptr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ptr.children[ch - 'a'] == null) {
                Node child = new Node();
                ptr.children[ch - 'a'] = child;
            }
            ptr = ptr.children[ch - 'a'];
            ptr.freq++;
        }
        ptr.isWordEnd = true;
    }

    private int dfs(Node node, char[][] board, int sr, int sc, boolean[][] vis, List<String> res, StringBuilder str,
            int[][] dir) {
        char ch = board[sr][sc];
        if (node.children[ch - 'a'] == null)
            return 0;
        node = node.children[ch - 'a'];
        if (node.freq == 0)
            return 0;

        vis[sr][sc] = true;
        str.append(ch);
        int count = 0;
        if (node.isWordEnd) {
            res.add(str.toString());
            node.isWordEnd = false; // for get rid from repetition
            count = 1;
        }

        int n = board.length, m = board[0].length;
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && r < n && c >= 0 && c < m && !vis[r][c]) {
                count += dfs(node, board, r, c, vis, res, str, dir);
            }
        }

        vis[sr][sc] = false;
        str.deleteCharAt(str.length() - 1);
        node.freq -= count;

        return count;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Node root = new Node();
        for (String word : words) {
            insert(root, word);
        }

        int n = board.length, m = board[0].length;
        boolean[][] vis = new boolean[n][m];
        List<String> res = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(root, board, i, j, vis, res, str, dir);
            }
        }

        return res;
    }

    // =========================================================================================================================================================================
    // Question_4 : 648. Replace Words
    // https://leetcode.com/problems/replace-words/
    // private class Node {
    // Node[] children;
    // boolean isWordEnd;

    // Node() {
    // this.children = new Node[26];
    // this.isWordEnd = false;
    // }
    // }

    // private void insert(Node root, String word) {
    // Node ptr = root;
    // for(int i = 0; i < word.length(); i++) {
    // char ch = word.charAt(i);
    // if(ptr.children[ch - 'a'] == null) {
    // Node child = new Node();
    // ptr.children[ch - 'a'] = child;
    // }
    // ptr = ptr.children[ch - 'a'];
    // }
    // ptr.isWordEnd = true;
    // }

    private boolean search(Node root, String word, StringBuilder str) {
        Node ptr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ptr.children[ch - 'a'] == null)
                return false;
            str.append(ch);
            ptr = ptr.children[ch - 'a'];
            if (ptr.isWordEnd == true) {
                return true;
            }
        }

        str = new StringBuilder();
        return false;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        String[] arr = sentence.split(" ");
        StringBuilder str = new StringBuilder();
        Node root = new Node();

        for (String word : dictionary)
            insert(root, word);

        int idx = 0;
        for (String a : arr) {
            boolean found = search(root, a, str);
            if (found == true)
                arr[idx] = str.toString();
            idx++;
            str = new StringBuilder();
        }

        int i = 0;
        for (String a : arr) {
            str.append(a);
            i++;
            if (i != arr.length)
                str.append(" ");
        }
        return str.toString();
    }

    // =========================================================================================================================================================================
    // Question_5 : 472. Concatenated Words
    // https://leetcode.com/problems/concatenated-words/
    private class Node {
        Node[] children;
        String str;
        
        Node() {
            this.children = new Node[26];
            this.str = null;
        }
    }
    private void insert(Node root, String word) {
        if(word.length() == 0)
            return;
        Node ptr = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(ptr.children[ch - 'a'] == null) {
                ptr.children[ch - 'a'] = new Node(); 
            }
            ptr = ptr.children[ch - 'a'];
        }
        ptr.str = word;
    }
    
    private void search_matching_char(Node ptr1, Node ptr2, Node root, List<String> ans) {
        if(ptr1.str != null && ptr2.str != null) {
            ans.add(ptr1.str);
            ptr1.str = null;
        }
        
        if(ptr2.str != null)
            search_matching_char(ptr1, root, root, ans);
        
        for(int i = 0; i < 26; i++) {
            if(ptr1.children[i] != null && ptr2.children[i] != null) {
                search_matching_char(ptr1.children[i], ptr2.children[i], root, ans);
            }
        }
    }
    private void dsf_on_trie(Node root, Node ptr1, List<String> ans) {
        if(ptr1.str != null)
            search_matching_char(ptr1, root, root, ans);
        for(Node child : ptr1.children) {
            if(child != null)
                dsf_on_trie(root, child, ans);
        }
    }
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Node root = new Node();
        for(String word : words) {
            insert(root, word);
        }
        
        List<String> ans = new ArrayList<>();
        dsf_on_trie(root, root, ans);
        
        return ans;
    }

}