import java.util.LinkedList;

public class l005TreeConversion {
    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    // ==============================================================================================
    // DATE: 29/ 08
    // https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1
    // Binary Tree to DLL

    static Node prev = null;

    public static void bToDLL_(Node root) {
        if (root == null)
            return;
        bToDLL_(root.left);

        prev.right = root;
        root.left = prev;
        prev = root;

        bToDLL_(root.right);
    }

    public static Node bToDLL(Node root) {
        // Your code here
        Node dummy = new Node(-1);
        prev = dummy;
        bToDLL_(root);
        Node head = dummy.right;
        head.left = dummy.right = null;

        return head;
    }

    // https://practice.geeksforgeeks.org/problems/binary-tree-to-cdll/1
    // Function to convert binary tree into circular doubly linked list.

    public static void bTreeToClist_(Node root) {
        if (root == null)
            return;
        bToDLL_(root.left);

        prev.right = root;
        root.left = prev;
        prev = root;

        bTreeToClist_(root.right);
    }

    public static Node bTreeToClist(Node root) {
        // your code here
        Node dummy = new Node(-1);
        prev = dummy;
        bToDLL_(root);
        Node head = dummy.right;
        head.left = dummy.right = null;
        prev.right = head;
        head.left = prev;
        return head;
    }

    // *********************************_Using_Stack_*********************************

    public static void addAllLeft(Node node, LinkedList<Node> st) {
        while (node != null) {
            st.addFirst(node);
            node = node.left;
        }
    }

    // binary tree to doubly linkedList
    public static Node btToDLL(Node root) {
        Node dummy = new Node(-1);
        Node prev = dummy;

        LinkedList<Node> st = new LinkedList<>();
        addAllLeft(root, st);
        while (st.size() != 0) {
            Node curr = st.removeFirst();

            prev.right = curr;
            curr.left = prev;
            prev = curr;

            addAllLeft(curr.right, st);
        }

        Node head = dummy.right;

        dummy.right = head.left = null;
        return head;

    }

    // binary tree to circular doubly linkedList
    public static Node btToCDLL(Node root) {
        Node dummy = new Node(-1);
        Node prev = dummy;
        LinkedList<Node> st = new LinkedList<>();
        addAllLeft(root, st);
        while (st.size() != 0) {
            Node curr = st.removeFirst();

            prev.right = curr;
            curr.left = prev;
            prev = curr;

            addAllLeft(curr.right, st);
        }

        Node head = dummy.right;

        dummy.right = head.left = null;
        prev.right = head;
        head.left = prev;
        return head;

    }

    // binary tree to bst
    public static Node mergeTwoDLL(Node l1, Node l2) {
        Node dummy = new Node(-1);
        Node prev = dummy, c1 = l1, c2 = l2;

        while (c1 != null && c2 != null) {
            if (c1.data <= c2.data) {
                prev.right = c1;
                c1.left = prev;
                c1 = c1.right;
            } else {
                prev.right = c2;
                c2.left = prev;
                c2 = c2.right;
            }

            prev = prev.right;
        }

        if (c1 != null) {
            prev.right = c1;
            c1.left = prev;
        } else {
            prev.right = c2;
            c2.left = prev;
        }

        Node head = dummy.right;

        dummy.right = head.left = null;
        return head;
    }

    public static Node getMidNode(Node head) {
        if (head == null || head.right == null)
            return head;
        Node slow = head, fast = head;

        while (fast.right != null && fast.right.right != null) {
            slow = slow.right;
            fast = fast.right.right;
        }

        return slow;
    }

    public static Node sortDLL(Node head) {
        if (head == null || head.right == null)
            return head;
        Node mid = getMidNode(head);
        Node nHead = mid.right;
        mid.right = nHead.left = null;

        Node left = sortDLL(head);
        Node right = sortDLL(nHead);

        return mergeTwoDLL(left, right);
    }

    public static Node SortedDLLToBST(Node head) {
        if (head == null || head.right == null)
            return head;
        Node root = getMidNode(head);
        Node nHead = root.right;

        root.left.right = root.right.left = null;
        root.left = root.right = null;

        root.left = SortedDLLToBST(head);
        root.right = SortedDLLToBST(nHead);

        return root;
    }

    public static Node btToBST(Node root) {
        Node head = btToDLL(root);
        head = sortDLL(head);

        return SortedDLLToBST(head);
    }
}