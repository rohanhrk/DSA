public class l001Question {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // ===================================================================================================================================================
    // Question_1 : first mid
    // https://leetcode.com/problems/middle-of-the-linked-list/
    public static ListNode middleNode1(ListNode head) {
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

    // ==========
    // second mid
    // ==========
    public static ListNode middleNode2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // ===================================================================================================================================================
    // Question_2 : reverse a linkedList
    // https://practice.geeksforgeeks.org/problems/reverse-a-linked-list/1
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

    // ===================================================================================================================================================
    // Question_3 : palindrome of a linkedlist
    // https://practice.geeksforgeeks.org/problems/check-if-linked-list-is-pallindrome/1
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode midNode = middleNode1(head);
        ListNode nhead = midNode.next;
        midNode.next = null;

        nhead = reverse(nhead);

        ListNode c1 = head;
        ListNode c2 = nhead;
        boolean res = true;
        while (c2 != null) {
            if (c1.val != c2.val) {
                res = false;
                break;
            }

            c1 = c1.next;
            c2 = c2.next;
        }

        nhead = reverse(nhead);
        midNode.next = nhead;

        return res;
    }

    // ===================================================================================================================================================
    // Question_4 : fold of a linkedlist
    // https://practice.geeksforgeeks.org/problems/reorder-list/1
    public static void fold(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode mid = middleNode1(head);
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
    // Question_5 : unfold of linkedList
    // https://www.geeksforgeeks.org/program-to-unfold-a-folded-linked-list/
    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode l1 = new ListNode(-1); // dummy node
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2, c1 = head, c2 = c1.next;

        while (c1 != null && c2 != null) {
            p1.next = c1;
            p2.next = c2;

            p1 = c1; // move
            p2 = c2;

            if (c2 != null)
                c1 = c2.next;
            if (c1 != null)
                c2 = c1.next;
        }

        p1.next = null;
        p2 = reverse(l2.next);
        p1.next = p2;
    }

    public static void unfold1(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2;

        p1.next = head;
        p2.next = head.next;

        p1 = p1.next;
        p2 = p2.next;

        while (p2 != null && p2.next != null) {
            ListNode f = p2.next;

            p1.next = f;
            p2.next = f.next;

            p1 = p1.next;
            p2 = p2.next;
        }

        p1.next = null;
        p2 = reverse(l2.next);
        p1.next = p2;
    }

    // ===================================================================================================================================================
    // Question_6 : merge two sorted list
    // https://leetcode.com/problems/merge-two-sorted-lists/
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy, c1 = l1, c2 = l2;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                p.next = c1;
                c1 = c1.next;
            } else {
                p.next = c2;
                c2 = c2.next;
            }

            p = p.next;
        }

        p.next = c1 != null ? c1 : c2;

        return dummy.next;
    }

    // ===================================================================================================================================================
    // Question_7 : 23. Merge k Sorted Lists
    // https://leetcode.com/problems/merge-k-sorted-lists/
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = null;

        for (ListNode node : lists) {
            dummy = mergeTwoLists(dummy, node);
        }

        return dummy;
    }

    // optimize
    public static ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si == ei)
            return lists[si];
        int mid = (si + ei) / 2;
        ListNode leftListNode = mergeKLists(lists, si, mid);
        ListNode rightListNode = mergeKLists(lists, mid + 1, ei);

        return mergeTwoLists(leftListNode, rightListNode);

    }

    public static ListNode mergeKLists1(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length);
    }

    // ===================================================================================================================================================
    // Question_8 : merge Sort
    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = middleNode1(head);
        ListNode nhead = mid.next;
        mid.next = null;

        ListNode leftListNode = mergeSort(head);
        ListNode rightListNode = mergeSort(nhead);

        return mergeTwoLists(leftListNode, rightListNode);

    }

}