public class l002Segregate {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int data) {
            this.val = data;
        }
    }

    // =============================================================================================================================================================================================
    // Question_1: segregate odd even
    // https://practice.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1
    public static ListNode segregateEvenOdd(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);
        ListNode ep = even, op = odd, curr = head;

        while (curr != null) {
            if (curr.val % 2 == 0) {
                ep.next = curr;
                ep = ep.next;
            } else {
                op.next = curr;
                op = op.next;
            }
            curr = curr.next;
        }
        ep.next = odd.next;
        op.next = null;
        head = even.next;
        even.next = odd.next = null;

        return head;
    }

    // =============================================================================================================================================================================================
    // Quesstion_2 : Segregate 01 Node Of Linkedlist Over Swapping Nodes
    // =================================================================
    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);
        ListNode op = one, zp = zero, curr = head;

        while (curr != null) {
            if (curr.val != 0) {
                op.next = curr;
                op = op.next;
            } else {
                zp.next = curr;
                zp = zp.next;
            }
            curr = curr.next;
        }

        zp.next = one.next;
        op.next = null;
        head = zero.next;

        zero.next = one.next = null;
        return head;
    }

    // =============================================================================================================================================================================================
    // Question_3 : Segregate 012 Node Of Linkedlist
    // https://practice.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1
    // =========================================================================================
    public static ListNode segregate012(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode zero = new ListNode(-1);
        ListNode one = new ListNode(-1);
        ListNode two = new ListNode(-1);

        ListNode zp = zero, op = one, tp = two, curr = head;
        while (curr != null) {
            if (curr.val == 0) {
                zp.next = zero;
                zp = zp.next;
            } else if (curr.val == 1) {
                op.next = one;
                op = op.next;
            } else {
                tp.next = two;
                tp = tp.next;
            }
            curr = curr.next;
        }
        op.next = two.next;
        zp.next = one.next;
        tp.next = null;

        head = zero.next;

        zero.next = one.next = two.next = null;
        return head;
    }

    // =============================================================================================================================================================================================
    // Question_4 : Segregate Node Of Linkedlist Over Last Index
    // =========================================================
    public static ListNode segregateOnLastIndex(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;

        ListNode pivotNode = head;
        while (pivotNode.next != null)
            pivotNode = pivotNode.next;

        while (curr != null) {
            if (curr.val <= pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }

        lp.next = null;
        sp.next = large.next;
        head = small.next;

        small.next = large.next = null;
        return head;
    }

    // =============================================================================================================================================================================================
    // Question_5 : Segregate Node Of Linkedlist Over Pivot Index
    // ==========================================================
    public static ListNode segregate(ListNode head, int pivotIdx) {
        if (head == null || head.next == null)
            return head;

        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;

        ListNode pivotNode = head;
        while (pivotIdx-- > 0)
            pivotNode = pivotNode.next;

        while (curr != null) {
            if(curr == pivotNode) {
                
            } else if(curr.val <= pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }

        sp.next = pivotNode;
        pivotNode.next = lp;
        lp.next = null;

        head = small.next;
        
        small.next = large.next = null;
        return head;
    }
}
