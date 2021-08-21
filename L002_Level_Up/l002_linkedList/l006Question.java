public class l006Question {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, curr = head;

        while (curr != null) {
            ListNode forw = curr.next;

            curr.next = prev;

            prev = curr;
            curr = forw;
        }

        return prev;

    }

    // add two LinkedList
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, c1 = l1, c2 = l2;
        int carry = 0;
        while (c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
            carry = sum / 10;
            sum %= 10;

            prev.next = new ListNode(sum);
            prev = prev.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        dummy = reverse(dummy.next);

        l1 = reverse(l1);
        l2 = reverse(l2);
        return dummy;
    }

    // subtracted two LinkedList
    public static ListNode subtractTwoLL(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, c1 = l1, c2 = l2;
        int borrow = 0;

        while (c1 != null || c2 != null) {
            int diff = borrow + (c1 != null ? c1.val : 0) - (c2 != null ? c2.val : 0);
            if (diff < 0) {
                diff += 10;
                borrow = -1;
            } else
                borrow = 0;

            prev.next = new ListNode(diff);
            prev = prev.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode head = reverse(dummy.next);

        if (head.val == 0)
            head = head.next;

        l1 = reverse(l1);
        l2 = reverse(l2);
       
        return head;
    }

   
}