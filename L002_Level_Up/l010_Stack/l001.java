import java.util.Stack;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;


public class l001 {
    // ==============================================================================================================================================================================
    // Question_1 : next greater element to the right
    // ==============================================
    public static int[] ngr(int[] arr, int n) {
        int[] nge_right = new int[n];
        Stack<Integer> st = new Stack<>(); // store index of element
        for (int idx = 0; idx < n; idx++) {
            while (st.size() > 0 && arr[st.peek()] < arr[idx]) {
                nge_right[st.pop()] = arr[idx];
            }
            st.push(idx);
        }

        while (st.size() > 0) {
            nge_right[st.pop()] = -1;
        }

        return nge_right;
    }

    // ==============================================================================================================================================================================
    // Question_2 : next greater element to the left
    // =============================================
    public static int[] ngl(int[] arr, int n) {
        int[] nge_left = new int[n];
        Stack<Integer> st = new Stack<>(); // store index of element
        for (int idx = n - 1; idx >= 0; idx--) {
            while (st.size() > 0 && arr[st.peek()] < arr[idx]) {
                nge_left[st.pop()] = arr[idx];
            }
            st.push(idx);
        }

        while (st.size() > 0) {
            nge_left[st.pop()] = -1;
        }

        return nge_left;
    }

    // ==============================================================================================================================================================================
    // Question_3 : next smaller element to the right
    // ==============================================
    public static int[] nsr(int[] arr, int n) {
        int[] nsr = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                nsr[st.pop()] = arr[i];
            }
            st.push(i);
        }

        while (st.size() > 0) {
            nsr[st.pop()] = -1;
        }

        return nsr;
    }

    // ==============================================================================================================================================================================
    // Question_4 : next smaller element to the left
    // =============================================
    public static int[] nsl(int[] arr, int n) {
        int[] nsl = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                nsl[st.pop()] = arr[i];
            }
            st.push(i);
        }

        while (st.size() > 0) {
            nsl[st.pop()] = -1;
        }

        return nsl;
    }

    // ==============================================================================================================================================================================
    // Question_5 : 496. Next Greater Element I
    // https://leetcode.com/problems/next-greater-element-i/
    private int[] nextgreater(int[] arr) {
        // ngri -> next greater on right index
        int n = arr.length;
        int[] ngr = new int[n];
        Stack<Integer> st = new Stack<>(); // store index

        for (int i = 0; i < n; i++) {
            while (st.size() > 0 && arr[i] > arr[st.peek()]) {
                ngr[st.pop()] = arr[i];
            }
            st.push(i);
        }

        while (st.size() > 0) {
            ngr[st.pop()] = -1;
        }

        return ngr;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] ngr = nextgreater(nums2); // finding next greater to the right

        int[] res = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>(); // maping element vs index
        for (int i = 0; i < nums2.length; i++) {
            int ele = nums2[i];
            map.put(ele, i);
        }

        for (int i = 0; i < n; i++) {
            int ele = nums1[i];
            int idx = map.get(ele);

            res[i] = ngr[idx];
        }

        return res;
    }

    // ==============================================================================================================================================================================
    // Question_6 : 503. Next Greater Element II
    // https://leetcode.com/problems/next-greater-element-ii/
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] nge = new int[n];
        Stack<Integer> st = new Stack<>(); // only storing index
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (st.size() > 0 && nums[st.peek()] < nums[idx]) {
                nge[st.pop()] = nums[idx];
            }

            if (i < n)
                st.push(i);
        }

        while (st.size() > 0) {
            nge[st.pop()] = -1;
        }

        return nge;
    }

    // ==============================================================================================================================================================================
    // Question_7 : 84. Largest Rectangle in Histogram
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    private int[] nsr_index(int[] arr, int size) {
        Stack<Integer> st = new Stack<>();
        int[] nsr = new int[size];
        for (int i = 0; i < size; i++) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                nsr[st.pop()] = i;
            }
            st.push(i);
        }

        while (st.size() > 0)
            nsr[st.pop()] = size;

        return nsr;
    }

    private int[] nsl_index(int[] arr, int size) {
        Stack<Integer> st = new Stack<>();
        int[] nsl = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                nsl[st.pop()] = i;
            }
            st.push(i);
        }

        while (st.size() > 0)
            nsl[st.pop()] = -1;

        return nsl;
    }

    public int largestRectangleArea(int[] heights) {
        int size = heights.length;
        int[] nsr = nsr_index(heights, size);
        int[] nsl = nsl_index(heights, size);

        int max_area = -(int) 1e9;
        for (int idx = 0; idx < size; idx++) {
            int width = (nsr[idx] - nsl[idx] - 1);
            int area = width * heights[idx];
            max_area = Math.max(max_area, area);
        }

        return max_area;
    }

    // ==============================================================================================================================================================================
    // Question_8 : 921. Minimum Add to Make Parentheses Valid
    // https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
    public int minAddToMakeValid(String s) {
        int len = s.length();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                st.push(i);
            } else {
                // closing bracket
                if (st.size() == 0 || s.charAt(st.peek()) == ')')
                    st.push(i);
                else {
                    st.pop();
                }
            }
        }

        return st.size();
    }

    // ==============================================================================================================================================================================
    // Question_9 : Count the Reversals
    // https://practice.geeksforgeeks.org/problems/count-the-reversals0401/1
    int countRev(String str) {
        // your code here
        int len = str.length();
        Stack<Character> st = new Stack<>();
        int op = 0, cl = 0;
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch == '{') {
                // opening
                st.push(ch);
            } else {
                // closing
                if (st.size() == 0 || st.peek() == '}') {
                    st.push(ch);
                } else {
                    st.pop();
                }
            }
        }

        if (st.size() % 2 != 0)
            return -1;
        while (st.size() > 0) {
            if (st.peek() == '{') {
                op++;
            } else {
                cl++;
            }

            st.pop();
        }

        int res = (int) Math.ceil(op / 2.0) + (int) Math.ceil(cl / 2.0);
        return res;

    }

    // ==============================================================================================================================================================================
    // Question_10 : 946. Validate Stack Sequences
    // https://leetcode.com/problems/validate-stack-sequences/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int size = pushed.length;
        Stack<Integer> st = new Stack<>();
        int j = 0; // pointer for popped array

        for (int i = 0; i < size; i++) {
            st.push(pushed[i]);
            while (st.size() > 0 && st.peek() == popped[j]) {
                st.pop();
                j++;
            }
        }

        return st.size() == 0;
    }

    // ==============================================================================================================================================================================
    // Question_11 : 1021. Remove Outermost Parentheses
    // https://leetcode.com/problems/remove-outermost-parentheses/
    public String removeOuterParentheses(String str) {
        int len = str.length();
        // op -> opening bracket, cl -> closing bracket, sp -> starting point
        int op = 0, cl = 0, sp = 0;
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < len; idx++) {
            char ch = str.charAt(idx);
            if (ch == '(')
                op++;
            else
                cl++;

            if (op == cl) {
                sb.append(str.substring(sp + 1, idx));
                op = cl = 0;
                sp = idx + 1;
            }
        }

        return sb.toString();
    }

    // ==============================================================================================================================================================================
    // Question_12 : 856. Score of Parentheses
    // https://leetcode.com/problems/score-of-parentheses/
    public int scoreOfParentheses(String str) {
        int len = str.length();
        Stack<Integer> st = new Stack<>();
        for (int idx = 0; idx < len; idx++) {
            char ch = str.charAt(idx);
            if (ch == '(') {
                // opening
                st.push(-1); // -1 -> as a marker of opening bracket
            } else {
                // closing
                if (st.size() > 0 && st.peek() == -1) {
                    st.pop();
                    st.push(1); // () -> 1
                } else {
                    // make sum until opening is not encountered
                    // AB -> A + B
                    int sum = 0;
                    while (st.peek() != -1)
                        sum += st.pop();
                    st.pop();
                    st.push(2 * sum); // (A) -> 2 * A
                }
            }
        }

        int sum = 0;
        while (st.size() > 0) {
            sum += st.pop();
        }

        return sum;
    }

    // ==============================================================================================================================================================================
    // Question_13 : 1190. Reverse Substrings Between Each Pair of Parentheses
    // https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
    public String reverseParentheses(String s) {
        int len = s.length();
        StringBuilder ans = new StringBuilder();
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch != ')') {
                st.push(ch);
            } else {
                StringBuilder sb = new StringBuilder();
                while (st.peek() != '(') {
                    sb.append(st.pop());
                }
                st.pop();

                for (int j = 0; j < sb.length(); j++) {
                    st.push(sb.charAt(j));
                }
            }
        }

        while (st.size() > 0)
            ans.append(st.pop());

        return ans.reverse().toString();
    }

    // ==============================================================================================================================================================================
    // Question_14 : 1249. Minimum Remove to Make Valid Parentheses
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
    public String minRemoveToMakeValid(String str) {
        int len = str.length();
        Stack<Integer> st = new Stack<>();
        for (int idx = 0; idx < len; idx++) {
            char ch = str.charAt(idx);
            if (ch == '(') {
                // opening
                st.push(idx);
            } else if (ch == ')') {
                // closing
                if (st.size() == 0 || str.charAt(st.peek()) == ')')
                    st.push(idx);
                else
                    st.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int idx = len - 1; idx >= 0; idx--) {
            if (st.size() > 0 && st.peek() == idx) {
                st.pop();
            } else {
                sb.append(str.charAt(idx));
            }
        }

        return sb.reverse().toString();
    }

    // ==============================================================================================================================================================================
    // Question_15 : 901. Online Stock Span
    // https://leetcode.com/problems/online-stock-span/
    class StockSpanner {
        private class pair {
            int price;
            int idx;

            pair(int price, int idx) {
                this.price = price;
                this.idx = idx;
            }
        }

        private int idx;
        private Stack<pair> st;

        public StockSpanner() {
            this.st = new Stack<>();
            this.st.push(new pair((int) 1e9, -1));
            this.idx = 0;
        }

        public int next(int price) {
            while (this.st.peek().price <= price)
                st.pop();
            int span = idx - this.st.peek().idx;
            this.st.push(new pair(price, idx++));
            return span;
        }
    }

    // ==============================================================================================================================================================================
    // Question_16 : 739. Daily Temperatures
    // https://leetcode.com/problems/daily-temperatures/
    private int[] ngr_index(int[] arr, int n) {
        int[] ngi = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                ngi[st.pop()] = i;
            }
            st.push(i);
        }

        while (st.size() > 0)
            ngi[st.pop()] = n;

        return ngi;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int size = temperatures.length;
        int[] res = ngr_index(temperatures, size);
        for (int i = 0; i < size; i++) {
            if (res[i] == size) {
                res[i] = 0;
                continue;
            }
            res[i] = res[i] - i;
        }

        return res;
    }

    // ==============================================================================================================================================================================
    // Question_17 : 844. Backspace String Compare
    // https://leetcode.com/problems/backspace-string-compare/
    private Stack<Character> makeStack(String s) {
        int len = s.length();
        Stack<Character> st = new Stack<>();
        for (int idx = 0; idx < len; idx++) {
            char ch = s.charAt(idx);
            if (ch != '#')
                st.push(ch);
            else {
                if (st.size() > 0)
                    st.pop();
            }
        }

        return st;
    }

    public boolean backspaceCompare(String s, String t) {
        Stack<Character> st1 = makeStack(s); // store character of 's' string
        Stack<Character> st2 = makeStack(t); // store character of 't' string

        if (st1.size() != st2.size())
            return false;

        while (st1.size() > 0) {
            char c1 = st1.pop(), c2 = st2.pop();
            if (c1 != c2)
                return false;
        }

        return true;
    }

    // ==============================================================================================================================================================================
    // Question_18 : 636. Exclusive Time of Functions
    // https://leetcode.com/problems/exclusive-time-of-functions/
    private class EThelper {
        int id; // function's id
        int start; // functions start time
        int cet; // fn's child execution time
        
        EThelper(int id, int start, int cet) {
            this.id = id;
            this.start = start;
            this.cet = cet;
        }
    }
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<EThelper> st = new Stack<>();
        int[] res = new int[n];
        for(String str : logs) {
            String[] info = str.split(":");
            
            int id = Integer.parseInt(info[0]);
            String status = info[1];
            int timestamp = Integer.parseInt(info[2]);
            
            if(status.equals("start")) {
                st.push(new EThelper(id, timestamp, 0));
            } else {
                // status = end   
                // function time difference = (end time - start time + 1)
                int fn_time_diff = timestamp - st.peek().start + 1; 
                int fn_exe_time = fn_time_diff - st.peek().cet; // function execution time
                res[id] += fn_exe_time;
                
                st.pop();
                if(st.size() > 0) {
                    st.peek().cet += fn_time_diff;
                }
            }
        }
        
        return res;
    }

    // ==============================================================================================================================================================================
    // Question_19 : 735. Asteroid Collision
    // https://leetcode.com/problems/asteroid-collision/
    public int[] asteroidCollision(int[] asteroids) {
        int size = asteroids.length;
        Stack<Integer> st = new Stack<>();
        for(int asteroid : asteroids) {
            if(asteroid > 0) { 
                // if asteroid is positive
                st.push(asteroid);
            } else {
                // if asteroid is negative
                // peek is positive but smaller in terms of size then pop until below condition will not break
                while(st.size() > 0 && st.peek() > 0 && Math.abs(asteroid) > st.peek()) {
                    st.pop();
                }
                
                // absolute of negative val is smaller in terms of peek which is positive
                if(st.size() > 0 && Math.abs(asteroid) < st.peek())
                    continue; // positive will destroy impact of negative
                
                if(st.size() > 0 && Math.abs(asteroid) == st.peek()) {
                    st.pop(); // equal in size but opposite in direction
                    continue;
                }
                
                st.push(asteroid);
            }
        }
        
        int[] res = new int[st.size()];
        for(int i = res.length - 1; i >= 0; i--) 
            res[i] = st.pop();
        
        return res;
    }

    // ==============================================================================================================================================================================
    // Question_20 : 402. Remove K Digits
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

    // ==============================================================================================================================================================================
    // Question_21 : 316. Remove Duplicate Letters
    // https://leetcode.com/problems/remove-duplicate-letters/
     public String removeDuplicateLetters(String s) {
        int[] farr = new int[26]; // frequency arr
        boolean[] varr = new boolean[26]; // visited arr
        LinkedList<Character> st = new LinkedList<>(); // linkedList -> behave as stack
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            farr[ch - 'a']++;
        }
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(varr[ch - 'a']) {
                farr[ch - 'a']--;
                continue;
            }
            
            // pop until it can
            while(st.size() != 0 && ch < st.getLast() && farr[st.getLast() - 'a'] > 0) {
                char pch = st.removeLast(); // popped char
                varr[pch - 'a'] = false;
            }
            
            st.addLast(ch);
            farr[ch - 'a']--;
            varr[ch - 'a'] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        while(st.size() != 0) {
            sb.append(st.removeFirst());
        }
        
        return sb.toString();
    }

    // ==============================================================================================================================================================================
    // Question_22 : 42. Trapping Rain Water
    // https://leetcode.com/problems/trapping-rain-water/

    /*
        Approch => Using Stack
        TC => O(N)
        SC => O(N)
    */ 
    public int trap_usingStack(int[] height) {
        Stack<Integer> st = new Stack<>();
        int water = 0;
        
        for(int i = 0; i < height.length; i++) {
            int rMax = height[i]; // right max height
            int rIdx = i; // right index
            while(st.size() > 0 && rMax >= height[st.peek()]) {
                int remIdx = st.pop();
                int currH = height[remIdx];
                
                if(st.size() == 0)
                    break;
                int lIdx = st.peek(); // left idx
                int lMax = height[lIdx];
                int width = rIdx - lIdx - 1;
                water += (Math.min(lMax, rMax) - currH) * width;
            }
            st.push(i);
        }
        
        return water;
    }


    /*
        Approach => Using Two Pointer
        TC => O(N)
        SC => O(1)
    */ 
    public int trap_usingTP(int[] height) {
        int n = height.length, left = 0, right = n - 1; 
        int lmax = -(int)1e9, rmax = -(int)1e9;
        
        int water = 0;
        while(left < right) {
            lmax = Math.max(lmax, height[left]);
            rmax = Math.max(rmax, height[right]);
            
            if(lmax < rmax) {
                water += lmax - height[left];
                left++;
            } else {
                water += rmax - height[right];
                right--;
            }
        }
        
        return water;
    }

    // ==============================================================================================================================================================================
    // Question_23 : 227. Basic Calculator II
    // https://leetcode.com/problems/basic-calculator-ii/
    private int priority(char op) {
        if(op == '*' || op == '/') {
            return 2;
        } else if(op == '+' || op == '-') {
            return 1;
        } else {
            return 0;
        }
    }
    
    private int evaluate(int v1, char op, int v2) {
        if(op == '*') {
            return v1 * v2;
        } else if(op == '/') {
            return v1 / v2;
        } else if(op == '+') {
            return v1 + v2;
        } else {
            return v1 - v2;
        }
    }
    
    public int calculate(String s) {
        Stack<Integer> vStack = new Stack<>(); // value stack
        Stack<Character> oStack = new Stack<>(); // operator stack
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == ' ')  {
                 continue;
            } else if(ch >= '0' && ch <= '9') {
                // operand
                int j = i;
                StringBuilder sb = new StringBuilder();
                while(j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    sb.append(s.charAt(j));
                    j++;
                }
                
                int num = Integer.parseInt(sb.toString());
                vStack.push(num);
                
                i = j - 1;
            } else if(ch == '*' || ch == '/' || ch == '+' || ch == '-') {
                // pop until it can
                while(oStack.size() > 0 && priority(ch) <= priority(oStack.peek())) {
                    // evaluation area
                    char op = oStack.pop(); // opearator
                    int v2 = vStack.pop();
                    int v1 = vStack.pop();
                    
                    int eval = evaluate(v1, op, v2);
                    vStack.push(eval);
                }
                // push
                oStack.push(ch);
            }
        }
                      
        while(oStack.size() > 0) {
            // evaluation area
            char op = oStack.pop(); // opearator
            int v2 = vStack.pop();
            int v1 = vStack.pop();

            int eval = evaluate(v1, op, v2);
            vStack.push(eval);
        }
                      
        return vStack.pop();
    }
}