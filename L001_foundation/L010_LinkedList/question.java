public class question {
    public class Node {
        int data;
        Node next;
    }

    Node head = null;
    Node tail = null;
    int size = 0;

    public int mid() {
        // write your code here
        Node fast = head;
        Node slow = head;

        // while(fast != null && fast.next != null) // 2nd mid of even length linkedlist
        while (fast.next != null && fast.next.next != null) { // 1st mid of even length linkedlist
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow.data;
    }

    // reverse a linkedList (pointer iterative)
    public void reversePI() {
        // write your code here
        Node prev = null;
        Node curr = this.head;

        while (curr != null) {
            Node forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        this.tail = this.head;
        this.head = prev;
    }

    // reverse a linkedList (data iterative)
    private Node getNodeAt(int idx) {
        Node curr = this.head;
        while (idx-- > 0) {
            curr = curr.next;
        }
        return curr;
    }

    private void swap(Node s, Node e) {
        int curr = s.data;
        s.data = e.data;
        e.data = curr;
    }

    public void reverseDI() {
        // write your code here
        int si = 0;
        int ei = this.size - 1;

        while (si < ei) {
            swap(getNodeAt(si), getNodeAt(ei));

            si++;
            ei--;
        }
    }

    // display reverse (recursive) -. linkedList
    private void displayReverseHelper(Node node) {
        // write your code here
        if (node == null) {
            return;
        }
        displayReverseHelper(node.next);
        System.out.print(node.data + " ");
    }

    public void displayReverse() {
        displayReverseHelper(head);
        System.out.println();
    }

    // recursive_===============================================
    // pointer
    private void reversePRHelper(Node node) {
        // write your code here
        if (node.next == null) {

            Node curr = this.head;
            this.head = this.tail;
            this.tail = curr;

            return;
        }
        Node curr = node;
        Node forw = node.next;

        reversePRHelper(node.next);

        forw.next = curr;
    }

    public void reversePR() {
        // write your code here
        reversePRHelper(this.head);

        this.tail.next = null;

        // System.out.println();
    }

    // return type

    private Node reversePRHelper_return(Node node) {
        // write your code here
        if (node.next == null) {

            Node curr = this.head;
            this.head = this.tail;
            this.tail = curr;

            return node;
        }
        Node curr = node;

        Node rNode = reversePRHelper_return(node.next);

        rNode.next = curr;
        curr.next = null;

        return node;
    }

    public void reversePR_return() {
        // write your code here
        Node reverse = reversePRHelper_return(this.head);

        // System.out.println();
    }

    // merge two sorted LinkedList
    public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
        // write your code hered
        Node c1 = l1.head;
        Node c2 = l2.head;
  
        LinkedList ans = new LinkedList();
  
        while (c1 != null && c2 != null) {
          if (c1.data < c2.data) {
            ans.addLast(c1.data);
            c1 = c1.next;
          } else {
            ans.addLast(c2.data);
            c2 = c2.next;
          }
        }
  
        while (c1 != null) {
          ans.addLast(c1.data);
          c1 = c1.next;
        }
        
        while (c2 != null) {
          ans.addLast(c2.data);
          c2 = c2.next;
        }
        
        return ans;
      }
}
