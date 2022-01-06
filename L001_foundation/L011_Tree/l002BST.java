
// ====================================_DATE:-9/07_====================================
public class l002BST {
    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) { // constructor
            this.data = data;
        }
    }

    /* Constructor of BST */
    public static Node constructTree(int[] arr, int si, int ei) {
        if (si > ei)
            return null;

        int mid = (si + ei) / 2;
        Node node = new Node(arr[mid]);

        node.left = constructTree(arr, si, mid - 1);
        node.right = constructTree(arr, mid + 1, ei);

        return node;
    }

    public static Node constructTree(int[] arr) {
        return constructTree(arr, 0, arr.length - 1);
    }

    // ==============================================================================================================================================================
    // Question_1 : size of BST
    public static int size(Node node) {
        return (node == null) ? 0 : size(node.left) + size(node.right) + 1;
    }

    // ==============================================================================================================================================================
    // Question_2 : sum of BST
    public static int sum(Node node) {
        // write your code here
        return node == null ? 0 : sum(node.left) + sum(node.right) + node.data;
    }

    // ==============================================================================================================================================================
    // Question_3: height of bst
    public static int height(Node node) {
        return (node == null) ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    // ==============================================================================================================================================================
    // Question_4 : maximum in BST
    // without recursion
    public static int maximum(Node node) {
        Node curr = node;

        while (curr.right != null) {
            curr = curr.right;
        }

        return curr.data;
    }

    // using recursion
    public static int maximum_rec(Node node) {
        if (node.right == null) {
            return node.data;
        }

        return maximum_rec(node.right);
    }

    // ==============================================================================================================================================================
    // Question_5 : minimum in BST
    // without recursion
    public static int minimum(Node node) {
        Node curr = node;

        while (curr.left != null) {
            curr = curr.left;
        }

        return curr.data;
    }

    // using recursion
    public static int minimum_rec(Node node) {

        return node.left == null ? node.data : minimum_rec(node.left);
    }

    // ==============================================================================================================================================================
    // Question_6 : find node
    // https://leetcode.com/problems/search-in-a-binary-search-tree/

    /* using recursion */
    public static boolean findData(Node node, int data) {
        if (node == null)
            return false;

        if (node.data == data)
            return true;
        else if (node.data < data)
            return findData(node.right, data);
        else
            return findData(node.left, data);

    }

    /* without recursion */
    public static boolean find(Node node, int data) {
        Node curr = node;

        while (curr != null) {
            if (curr.data == data)
                return true;
            else if (curr.data < data)
                curr = curr.right;
            else
                curr = curr.left;
        }

        return false;
    }

    // ==============================================================================================================================================================
    // Question_7 : add Node
    // https://leetcode.com/problems/insert-into-a-binary-search-tree/
    public static Node add(Node node, int data) {
        if (node == null) {
            Node base = new Node(data);
            return base;
        }

        if (node.data == data)
            return node;
        else if (node.data < data)
            node.right = add(node.right, data);
        else
            node.left = add(node.left, data);

        return node;
    }

    // ==============================================================================================================================================================
    // Question_8 : LCA
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
    public static Node LCA(Node node, int p, int q) {
        Node curr = node;
        while (curr != null) {
            if (curr.data < p && curr.data < q)
                curr = curr.right;
            else if (curr.data > p && curr.data > q)
                curr = curr.left;
            else
                return (find(curr, p) && find(curr, q)) ? curr : null;
        }
        return null;
    }

    public static int lca(Node node, int d1, int d2) {
        // write your code here
        if (d1 > node.data && d2 > node.data)
            return lca(node.right, d1, d2);
        else if (d1 < node.data && d2 < node.data)
            return lca(node.left, d1, d2);
        else
            return node.data;
    }

    // ==============================================================================================================================================================
    // Question_9 : remove node in bst
    // https://leetcode.com/problems/delete-node-in-a-bst/
    public static Node removeNode(Node node, int data) {
        if (node == null) {
            return null;
        }

        if (node.data < data) {
            node.right = removeNode(node.right, data);
        } else if (node.data > data) {
            node.left = removeNode(node.left, data);
        } else {
            if (node.left == null || node.right == null)
                return node.left != null ? node.left : node.right;

            int minData = minimum(node.right);
            node.data = minData;
            node.right = removeNode(node.right, minData);
        }

        return node;
    }

    // ==============================================================================================================================================================
    // Question_10 : target sum pair
    // https://practice.geeksforgeeks.org/problems/find-a-pair-with-given-target-in-bst/1/#
    public boolean isPairPresent_(Node root, Node node, int tar) {
        if (node == null)
            return false;

        boolean left = isPairPresent_(root, node.left, tar);
        if (left)
            return true;

        int comp = tar - node.data;
        if (comp > node.data) {
            if (findData(root, comp) == true) {
                return true;
            }
        }

        boolean right = isPairPresent_(root, node.right, tar);
        if (right)
            return true;

        return left || right;
    }

    public int isPairPresent(Node root, int target) {
        // Write your code here
        return isPairPresent_(root, root, target) ? 1 : 0;

    }

}
