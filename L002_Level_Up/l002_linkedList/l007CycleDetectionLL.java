public class l007CycleDetectionLL {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static boolean isCyclePresentInLL(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }

    // Cycle Node In Linkedlist
    public static ListNode CycleNode(ListNode head) {
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

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                break;
        }

        if (slow != fast)
            return null;

        ListNode meetingNode = fast;
        slow = head;

        // A -> Length of tail, BC -> B + c -> Length of circles,
        // m -> No. of circles taken by fast pointer before meeting point
        // mDash -> No. of circles taken by fast pointer after meeting point
        int A = 0, B = 0, C = 0, BC = 0, m = 0, mDash = 0;
        
        // 1. Finding length of Tail
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;

            if (fast == meetingNode)
                mDash++;
            A++;
        }

        fast = meetingNode;
        fast = fast.next;
          
        // 2. Finding Length Of Circle
        BC = 1;
        while (fast != meetingNode) {
            fast = fast.next;
            BC++;
        }

        m = mDash + 1;
        C = A - BC * mDash;
        B = BC - C;

        System.out.println("Length of tail is " + A);
        System.out.println("Length of circles is " + BC);
        System.out.println("No. of circles taken by fast pointer before meeting point is " + m);
        System.out.println("No. of circles taken by fast pointer after meeting point is " + mDash);
        return slow;
    }

    // Intersection Node In Two Linkedlist Using Floyad Cycle Method
    public static ListNode getTail(ListNode head) {
        while (head.next != null) {
            head = head.next;
        }

        return head;
    }

    public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {
        ListNode tail = getTail(headA);
        tail.next = headB;
        ListNode cycleNode = CycleNode(headA);
        tail.next = null;

        return cycleNode;
    }
}