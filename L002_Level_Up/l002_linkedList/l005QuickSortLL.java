
public class l005QuickSortLL {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static int length(ListNode head) {
        if (head == null)
            return 0;
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        return len;
    }

    public static ListNode[] segregate(ListNode head, int pivotIdx) {
        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;

        ListNode pivotNode = head;
        while (pivotIdx-- > 0)
            pivotNode = pivotNode.next;

        while (curr != null) {
            if (curr.val != pivotNode.val) {
                if (curr.val <= pivotNode.val) {
                    sp.next = curr;
                    sp = sp.next;
                } else {
                    lp.next = curr;
                    lp = lp.next;
                }
            }
            curr = curr.next;
        }

        sp.next = null;
        pivotNode.next = null;

        ListNode LH = small.next;
        ListNode RH = large.next;

        return new ListNode[] { LH, pivotNode, RH };

    }
    
    public static ListNode[] MergeNode(ListNode[] left, ListNode pivotNode, ListNode[] right) {
        ListNode head = null;
        ListNode tail = null;

        if(left[0] != null && right[0] != null) {
            head = left[0];
            tail = right[1];
            left[1].next = pivotNode;
            pivotNode.next = right[0];
        } else if(left[0] != null) {
            head = left[0];
            tail = pivotNode;
            left[1].next = pivotNode;
        } else if(right[0] != null) {
            head = pivotNode;
            tail = right[1];
            pivotNode.next = right[0];
        } else {
            head = pivotNode;
            tail = pivotNode;
        }

        return new ListNode[] {head, tail};
    }

    // {LH, LT, PN, RH, RT}
    public static ListNode[] quickSort(ListNode head) {
        if (head == null || head.next == null)
            return new ListNode[] { head, head };
        int len = length(head);
        int pivotIdx = len / 2;
        ListNode[] segregateElements = segregate(head, pivotIdx);

        ListNode[] left = quickSort(segregateElements[0]);
        ListNode[] right = quickSort(segregateElements[2]);
        
        return MergeNode(left,segregateElements[1], right);
    }
}