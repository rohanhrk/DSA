public class l001Practice {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    // first mid 
    public static ListNode middleNode1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    // second mid
    public static ListNode middleNode2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    // reverse a linkedList
    public static ListNode reverse(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // linked

            prev = curr; // move
            curr = forw;
        }

        return prev;
    }

    // palindrome of a linkedlist
    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        
        ListNode midNode = middleNode1(head);
        ListNode nhead = midNode.next;
        midNode.next = null;

        nhead = reverse(nhead);
        
        ListNode c1 = head;
        ListNode c2 = nhead;
        boolean res = true;
        while(c2 != null) {
            if(c1.val != c2.val){
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
}