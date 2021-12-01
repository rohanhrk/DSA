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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (n-- > 0) {
            fast = fast.next;
            if (fast == null && n > 0)
                return head;
        }

        if (fast == null) {
            ListNode rNode = slow;
            head = rNode.next;
            rNode.next = null;
            return head;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode rNode = slow.next;
        slow.next = rNode.next;
        rNode.next = null;

        return head;
    }

    // ===================================================================================================================================================
    // Question_2 :1721. Swapping Nodes in a Linked List
    // https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
    private ListNode getNodeAt(ListNode head, int index) {
        ListNode curr = head;
        while(index-- > 0) 
            curr = curr.next;
        return curr;
    }
    
    private void swap1(ListNode c1, ListNode c2) {
        int temp = c1.val;
        c1.val = c2.val;
        c2.val = temp;
    }
    
    public ListNode swapNodes1(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        fast = getNodeAt(head, k - 1);
        
        ListNode c1 = fast; // backup
        while(fast.next != null) {
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
    // Question_2 :1721. Swapping Nodes in a Linked List
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
    // 86. Partition List
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

    // 912. Sort an Array
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

    // 141. Linked List Cycle
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

    // 142. Linked List Cycle II
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

    // 160. Intersection of Two Linked Lists
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

    // 25. Reverse Nodes in k-Group
    static ListNode th = null, tt = null;

    public int length(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    public void addFirstNode(ListNode node) {
        if (th == null) {
            th = tt = node;
        } else {
            node.next = th;
            th = node;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;

        ListNode curr = head, oh = null, ot = null;
        int len = length(head);
        while (len >= k) {
            int itr = k;
            while (itr-- > 0) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
            }

            if (oh == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = th;
            }

            len -= k;
            th = tt = null;
        }
        ot.next = curr;
        return oh;
    }

    // 92. Reverse Linked List II
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

    // 83. Remove Duplicates from Sorted List
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

    // 82. Remove Duplicates from Sorted List II
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

    // 138. Copy List with Random Pointer
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
}
