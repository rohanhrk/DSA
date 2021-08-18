import java.util.Random;
public class l003Leetcode {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int data) {
            this.val = data;
        }
    }

    // leecode 19. Remove Nth Node From End of List
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

    // 328. Odd Even Linked List
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

    // 143. Reorder List
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

    // 1721. Swapping Nodes in a Linked List
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
    
    // 86. Partition List
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;

        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;

        while(curr != null) {
            if(curr.val < x) {
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
        for(int i = si + 1; i <= ei; i++) {
            if(arr[i - 1] > arr[i]) {
                flag = false;
                break;
            }
        }
        if(flag) return;
		quickSort(arr, si, pivotIdx - 1);
		quickSort(arr, pivotIdx + 1, ei);
	}
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
}
