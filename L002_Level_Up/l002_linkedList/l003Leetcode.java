import java.util.Random;

public class l003Leetcode {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int data) {
            this.val = data;
        }
    }

    // ===================================================================================================================================================
    // Question_1 : 19. Remove Nth Node From End of List
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/
    /*
        Time : O(N) + O(N) + O(N) ~ O(N)
        Space : O(1)
    */ 
    public ListNode getNodeAt(ListNode head, int index) {
        ListNode curr = head;
        while(index-- > 0) {
            if(curr != null)
                curr = curr.next;
        }
        
        return curr;
    }
    
    private void remove(ListNode head , int idx) {
        ListNode prev = getNodeAt(head, idx - 1);
        ListNode curr = prev.next;
        ListNode forw = curr.next;
        
        prev.next = forw;
        curr.next = null;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n <= 0)
            return head;
        ListNode slow = head, fast = head;
        fast = getNodeAt(head, n);  // O(N)

        /* If fast is ponting to null, then remove 1st node */ 
        if(fast == null) {
            ListNode curr =  head;
            ListNode forw = curr.next;
            
            curr.next = null;
            head = forw;
            
            return head;
        }
        
        int index = 0;
        while(fast != null) {  // O(N)
            slow = slow.next;
            fast = fast.next;
            index++;
        }
        
        remove(head, index); // O(N)
        return head;
    }

    // ===================================================================================================================================================
    // Question_2 :1721. Swapping Nodes in a Linked List
    // https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
    /*
        Time : O(N) + O(N) ~ O(N)
        space : O(1)
    */  
    // private ListNode getNodeAt(ListNode head, int index) {
    //     ListNode curr = head;
    //     while(index-- > 0) 
    //         curr = curr.next;
    //     return curr;
    // }
    
    private void swap1(ListNode c1, ListNode c2) {
        int temp = c1.val;
        c1.val = c2.val;
        c2.val = temp;
    }
    
    public ListNode swapNodes1(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        fast = getNodeAt(head, k - 1); // O(N)
        
        ListNode c1 = fast; // backup
        while(fast.next != null) {   // O(N)
            slow = slow.next;
            fast = fast.next;
        }
        
        ListNode c2 = slow; // backup
        swap1(c1, c2);
        return head;
    }
    // ===================================================================================================================================================
    // Question_3 : 328. Odd Even Linked List
    // https://leetcode.com/problems/odd-even-linked-list/
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-1);
        ListNode op = odd, ep = even;
        op.next = head;
        ep.next = head.next;

        op = op.next;
        ep = ep.next;

        while (ep.next != null || ep != null) {
            ListNode f = ep.next;

            op.next = f;
            ep.next = f.next;

            op = op.next;
            ep = ep.next;
        }

        op.next = even.next;
        head = odd.next;
        odd.next = even.next = null;
        return head;
    }

    // ===================================================================================================================================================
    // Question_4 : 143. Reorder List
    // https://leetcode.com/problems/reorder-list/
    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // linked

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode mid = middleNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverse(nhead);

        ListNode c1 = head;
        ListNode c2 = nhead;

        while (c2 != null) {
            ListNode f1 = c1.next; // backup
            ListNode f2 = c2.next;

            c1.next = c2; // linked
            c2.next = f1;

            c1 = f1; // move
            c2 = f2;
        }
    }

    // ===================================================================================================================================================
    // Question_5 :1721. Swapping Nodes in a Linked List
    // https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
    public void swap(ListNode c1, ListNode c2) {
        int a = c1.val, b = c2.val;

        c1.val = b;
        c2.val = a;
    }

    public ListNode swapNodes(ListNode head, int k) {
        if (head == null || k <= 0)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (k-- > 1) {
            fast = fast.next;
            if (fast == null && k > 1)
                return head;
        }

        if (fast == null) {
            return head;
        }
        ListNode c1 = fast; // backup;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode c2 = slow;

        swap(c1, c2);

        return head;
    }

    // ===================================================================================================================================================
    // Question_6 : 86. Partition List
    // https://leetcode.com/problems/partition-list/submissions/
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;

        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;

        while (curr != null) {
            if (curr.val < x) {
                sp.next = curr;
                sp = sp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }

            curr = curr.next;
        }

        sp.next = large.next;
        lp.next = null;

        head = small.next;
        small.next = large.next = null;

        return head;
    }

    // ===================================================================================================================================================
    // Question_7 : 912. Sort an Array
    // https://leetcode.com/problems/sort-an-array/
    public static void swap(int[] arr, int i, int j) {
        // System.out.println("3");
        int a = arr[i], b = arr[j];
        arr[i] = b;
        arr[j] = a;
    }

    public static int segregate(int[] arr, int pivotIdx, int sp, int ep) {
        // System.out.println("2");
        swap(arr, pivotIdx, ep);

        int ptr = sp - 1, itr = sp;
        while (itr <= ep) {
            if (arr[itr] <= arr[ep]) {
                swap(arr, ++ptr, itr);
            }
            itr++;
        }

        return ptr;
    }

    public static void quickSort(int[] arr, int si, int ei) {
        // System.out.println("1");
        if (si > ei)
            return;

        Random rand = new Random();
        int pivotIdx = rand.nextInt(ei - si + 1) + si;
        pivotIdx = segregate(arr, pivotIdx, si, ei);

        boolean flag = true;
        for (int i = si + 1; i <= ei; i++) {
            if (arr[i - 1] > arr[i]) {
                flag = false;
                break;
            }
        }
        if (flag)
            return;
        quickSort(arr, si, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, ei);
    }

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    // ===================================================================================================================================================
    // Question_8 : 141. Linked List Cycle
    // https://leetcode.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }

    // ===================================================================================================================================================
    // Question_9 : 142. Linked List Cycle II
    // https://leetcode.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                break;
        }

        if (slow != fast)
            return null;

        ListNode meetingNode = fast;
        slow = head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    // ===================================================================================================================================================
    // Question_10 : 160. Intersection of Two Linked Lists
    // https://leetcode.com/problems/intersection-of-two-linked-lists/
    public ListNode CycleNode(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                break;
        }

        if (slow != fast)
            return null;

        ListNode meetingNode = fast;
        slow = head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public ListNode getTail(ListNode head) {
        while (head.next != null) {
            head = head.next;
        }

        return head;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tail = getTail(headA);
        tail.next = headB;
        ListNode cycleNode = CycleNode(headA);
        tail.next = null;

        return cycleNode;
    }

    // ===================================================================================================================================================
    // Question_11 : 25. Reverse Nodes in k-Group
    // https://leetcode.com/problems/reverse-nodes-in-k-group/
    private int getListLength(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while(curr != null) {
            len++;
            curr = curr.next;
        }
        
        return len;
    }
    private void addFirstNode(ListNode node) {
        if(tempH == null) {
            tempH = tempT = node;
        } else {
            node.next = tempH;
            tempH = node;
        }
    }
    
    private ListNode tempH = null, tempT = null; // temp head and temp tail
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        
        ListNode overH = null, overT = null; // overall head and overall tail
        
        int len = getListLength(head); // length of given list
        ListNode curr = head; // current node
        while(len >= k) {
            int innerItr = k; // inner iterator
            while(innerItr-- > 0) {
                ListNode forw = curr.next; // forward node
                ListNode node = new ListNode(curr.val); // create node 
                
                // addFirst node
                addFirstNode(node);
                
                curr.next = null; // link break
                curr = forw; // moving forward
            }
            
            // add k group in a result
            if(overH == null) {
                overH = tempH;
                overT = tempT;
            } else {
                overT.next = tempH;
                overT = tempT;
            }
            
            len -= k;
            tempH = tempT = null;
        }
    
        overT.next = curr; // add remaining list in result
        return overH;
    }

    // ===================================================================================================================================================
    // Question_12 : 92. Reverse Linked List II
    // https://leetcode.com/problems/reverse-linked-list-ii/
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head.next == null || m == n)
            return head;
        int i = 1;
        ListNode curr = head, prev = null;
        while (curr != null) {
            while (i >= m && i <= n) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
                i++;
            }

            if (i > n) {
                if (prev == null) {
                    tt.next = curr;
                    return th;
                } else {
                    prev.next = th;
                    tt.next = curr;
                    return head;
                }
            }

            prev = curr;
            curr = curr.next;
            i++;
        }

        return null;
    }

    // ===================================================================================================================================================
    // Question_13 : 83. Remove Duplicates from Sorted List
    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    public ListNode deleteDuplicates_(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode prev = head, curr = head.next;

        while (curr != null) {
            while (curr != null && curr.val == prev.val) {
                ListNode forw = curr.next;
                curr.next = null;
                curr = forw;
            }

            prev.next = curr;
            prev = prev.next;
            if (curr != null)
                curr = curr.next;
        }

        return head;
    }

    // ===================================================================================================================================================
    // Question_14 : 82. Remove Duplicates from Sorted List II
    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, curr = head.next;
        prev.next = head;

        while (curr != null) {
            boolean isLoopRun = false;
            while (curr != null && curr.val == prev.next.val) {
                ListNode forw = curr.next;
                curr.next = null;
                curr = forw;
                isLoopRun = true;
            }

            if (isLoopRun) {
                prev.next = curr;
            } else {
                prev = prev.next;
                prev.next = curr;
            }

            if (curr != null)
                curr = curr.next;
        }

        head = dummy.next;
        return head;
    }

    // ===================================================================================================================================================
    // Question_15 : 138. Copy List with Random Pointer
    // https://leetcode.com/problems/copy-list-with-random-pointer/
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    
    public void copyNodes(Node head) {
        Node curr = head;
        while (curr != null) {
            Node forw = curr.next;
            Node newNode = new Node(curr.val);

            curr.next = newNode;
            newNode.next = forw;

            curr = forw;
        }
    }

    public void copyRandom(Node head) {
        Node curr = head;
        while (curr != null) {
            if (curr.random != null)
                curr.next.random = curr.random.next;

            curr = curr.next.next;
        }
    }

    public Node extractList(Node head) {
        Node dummy = new Node(-1);
        Node curr = head, prev = dummy;

        while (curr != null) {
            prev.next = curr.next;
            prev = prev.next;

            curr.next = curr.next.next;
            curr = curr.next;
        }

        return dummy.next;
    }
    public Node copyRandomList(Node head) {
        copyNodes(head);
        copyRandom(head);
        return extractList(head);
    }

    // ===================================================================================================================================================
    // Question_16 : 61. Rotate List
    // https://leetcode.com/problems/rotate-list/
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;
        
        // 1. find length
        ListNode curr = head;
        int len = 1;
        while(curr.next != null) {
            curr = curr.next;
            len++;
        }
        
        // 2. connect tail to head
        curr.next = head;
        
        // 3.          
        k = len - k % len;
        while(k-- > 0)
            curr = curr.next;
        
        // 3. make head node and break connection         
        head = curr.next;
        curr.next = null;
        
        return head;
    }

    // ===================================================================================================================================================
    // Question_17 : 2130. Maximum Twin Sum of a Linked List
    // https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
    // private ListNode reverse(ListNode node) {
    //     ListNode prev = null, curr = node;
    //     while(curr != null) {
    //         ListNode forw = curr.next;
            
    //         curr.next = prev;
            
    //         prev = curr;
    //         curr = forw;
    //     }
        
    //     return prev;
    // }
    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode nHead = slow.next;
        slow.next = null;
        nHead = reverse(nHead);
        
        int max_twin_sum = -(int)1e9;
        ListNode c1 = head, c2 = nHead;
        while(c1 != null && c2 != null) {
            int sum = c1.val + c2.val;
            max_twin_sum = Math.max(max_twin_sum, sum);
            
            c1 = c1.next;
            c2 = c2.next;
        }
        
        return max_twin_sum;
    }
}
