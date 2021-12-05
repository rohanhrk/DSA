import java.util.Stack;
import java.util.HashMap;

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
}