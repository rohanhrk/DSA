
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

    // *****************_constructor_of_bst_*****************
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

    // *****************_size_*****************
    public static int size(Node node) {
        return (node == null) ? 0 : size(node.left) + size(node.right) + 1;
    }

    // *****************_Sum_*****************
    public static int sum(Node node) {
        // write your code here
        return node == null ? 0 : sum(node.left) + sum(node.right) + node.data;
    }

    // *****************_heigth_*****************
    public static int height(Node node) {
        return (node == null) ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    // *****************_maximum_*****************
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

    // *****************_mainimum_*****************
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

    // *****************_find_*****************
    // using recursion
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

    // *****************_add_Node_*****************

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

    // *****************_LCA(lowest common ancestor)_*****************
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
    // *****************_Remove_Node_*****************

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
            removeNode(node.right, minData);
        }

        return node;
    }

    // *****************_Target_Sum_Pair_*****************
    public static void targetSumPair(Node root, Node node, int tar) {
        if (node == null)
            return;

        targetSumPair(root, node.left, tar);

        int comp = tar - node.data;
        if (comp > node.data) {
            if (findData(root, comp) == true)
                System.out.println(node.data + " " + comp);
        }

        targetSumPair(root, node.right, tar);
    }

    
}
