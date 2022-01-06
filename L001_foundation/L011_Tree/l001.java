import java.util.ArrayList;

public class l001 {
    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) { // constructor
            this.data = data;
        }
    }

    
    // ==============================================================================================================================================================
    // Question_1 : Traversal of Binary tree
    // pre order
    public static void preOrderTraversal(Node root, ArrayList<Integer> ans) { // pre order
        if (root == null) {
            return;
        }

        ans.add(root.data);
        preOrderTraversal(root.left, ans);
        preOrderTraversal(root.right, ans);
    }

    // In order
    public static void inOrderTraversal(Node root, ArrayList<Integer> ans) { // In order
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left, ans);
        ans.add(root.data);
        inOrderTraversal(root.right, ans);
    }

    // post order
    public static void postOrderTraversal(Node root, ArrayList<Integer> ans) { // post order
        if (root == null) {
            return;
        }

        postOrderTraversal(root.left, ans);
        postOrderTraversal(root.right, ans);
        ans.add(root.data);
    }

    // ==============================================================================================================================================================
    // Question_2 : size
    public static int size(Node root) {
        if (root == null)
            return 0;
        int left = size(root.left);
        int right = size(root.right);

        return left + right + 1;
    }

    // ==============================================================================================================================================================
    // Question_3 : height
    // =======> In Terms Of Edge
    public static int height(Node root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // =======> In terms of Nodes
    public static int heightInTermsOfNode(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = heightInTermsOfNode(root.left);
        int rightHeight = heightInTermsOfNode(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // ==============================================================================================================================================================
    // Question_4 : Maximum
    public static int maximum(Node root) {
        if (root == null) {
            return (int) -1e8;
        }

        int leftMax = maximum(root.left);
        int rightMax = maximum(root.right);

        return Math.max(Math.max(leftMax, rightMax), root.data);
    }

    // ==============================================================================================================================================================
    // Question_5 : Minimum
    public static int minimum(Node root) {
        if (root == null) {
            return (int) 1e8;
        }

        int leftMin = minimum(root.left);
        int rightMin = minimum(root.right);

        return Math.min(Math.min(leftMin, rightMin), root.data);
    }


    // ==============================================================================================================================================================
    // Question_6 : Sum
    public static int sum(Node root) {
        if (root == null)
            return 0;
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);

        return leftSum + root.data + rightSum;
    }

    // ==============================================================================================================================================================
    // Question_7 : find data
    public static boolean findData(Node root, int data) {
        if (root == null)
            return false;

        boolean res = root.data == data;
        return res || findData(root.left, data) || findData(root.right, data);
    }

    // ==============================================================================================================================================================
    // Question_8 : print_node_k_distances_away
    // https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
    /* 
        Important(Amazon ka fav. interview question) 
    */ 
    // a. root to node path
    public static boolean rootToNodePath(Node root, int data, ArrayList<Node> ans) {
        if (root == null)
            return false;

        boolean res = root.data == data || rootToNodePath(root.left, data, ans)
                || rootToNodePath(root.right, data, ans);
        if (res) {
            ans.add(root);
        }

        return res;

    }

    public static void print_At_K_Depth(Node root, int k, Node blockNode, ArrayList<Integer> ans) {
        if (root == null || root == blockNode || k < 0) // base case
            return;

        if (k == 0) { // base case 2
            ans.add(root.data);
            return;
        }

        print_At_K_Depth(root.left, k - 1, blockNode, ans);
        print_At_K_Depth(root.right, k - 1, blockNode, ans);
    }

    public ArrayList<Integer> distanceK(Node root, Node target, int k) {
        ArrayList<Node> path = new ArrayList<>();
        rootToNodePath(root, target.data, path);

        ArrayList<Integer> ans = new ArrayList<>();

        Node block_node = null;
        for (int i = 0; i < path.size(); i++) {
            Node node = path.get(i);
            print_At_K_Depth(node, k - i, block_node, ans);
            block_node = node;
        }

        return ans;
    }

    // ==============================================================================================================================================================
    // Question_9 : print single child node
    public static void printSingleChildNodes(Node node, Node parent) {
        // write your code here
        if (node == null)
            return;

        if (parent != null && (parent.left == null || parent.right == null)) {
            System.out.println(node.data);
        }

        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);

    }

    // ==============================================================================================================================================================
    // question_10 : remove leafs node in binary tree
    // method_1 : return type
    public static Node removeLeaves(Node node) {
        // write your code here
        if (node == null)
            return null;

        if (node.left == null && node.right == null)
            return null;

        node.left = removeLeaves(node.left);
        node.right = removeLeaves(node.right);

        return node;
    }

    // method_2 : void type
    public static void removeLeaves_void(Node node, Node parent) {
        if (node == null)
            return;

        if (node.left == null && node.right == null) {
            if (parent.left == node)
                parent.left = null;

            else
                parent.right = null;
        }
        removeLeaves_void(node.left, node);
        removeLeaves_void(node.right, node);
    }

    public static Node removeLeaves_return_(Node node) {
        if (node.left == null && node.right == null) {
            return null;
        }

        removeLeaves_void(node, null);

        return node;
    }

    // ==============================================================================================================================================================
    // Question_11 : Is a BST
    // https://practice.geeksforgeeks.org/problems/check-for-bst/1/

    // method_1 : boolean type
    public static Node prev = null;
    public static boolean isBST(Node node) {
        if (node == null)
            return true;

        boolean left = isBST(node.left); // left call
        if (!left)
            return false;

        if (prev != null && prev.data > node.data) // In region
            return false;
        prev = node; // update prev

        boolean right = isBST(node.right); // right call
        if (!right)
            return false;

        return true;
    }

    // method_2 : return type -> khudka class banaya -> isBSTsolPair
    public static class isBSTsolPair {
        int maxEle = -(int) 1e8;
        int minEle = (int) 1e8;
        boolean isBST = true;
    }

    public isBSTsolPair isBST_(Node node) {
        if (node == null) {
            return new isBSTsolPair();
        }

        isBSTsolPair left = isBST_(node.left);
        isBSTsolPair right = isBST_(node.right);

        isBSTsolPair myRes = new isBSTsolPair();
        myRes.isBST = false;

        if (left.isBST && right.isBST && left.maxEle < node.data && node.data < right.minEle) {
            myRes.isBST = true;
            myRes.maxEle = Math.max(node.data, right.maxEle);
            myRes.minEle = Math.min(node.data, left.minEle);
        }

        return myRes;
    }

    // ==============================================================================================================================================================
    // Question_12 : Is balace tree
    // https://leetcode.com/problems/balanced-binary-tree/

    // create class isBalPair
    public static class isBalPair {
        int height = -1;
        boolean isBalanceTree = true;
    }

    public static isBalPair isBal(Node node) {
        if (node == null) {
            return new isBalPair();
        }

        isBalPair left = isBal(node.left);
        if (!left.isBalanceTree)
            return left;
        isBalPair right = isBal(node.right);
        if (!right.isBalanceTree)
            return right;

        isBalPair myRes = new isBalPair();
        myRes.isBalanceTree = false;
        if (left.isBalanceTree && right.isBalanceTree && Math.abs(left.height - right.height) <= 1) {
            myRes.isBalanceTree = true;
            myRes.height = Math.max(left.height, right.height) + 1;
        }

        return myRes;
    }

    // ==============================================================================================================================================================
    // Question_13 : Is_BST/Is balance/largest BST Node / Total of BST
    public static class binarySearchTree {
        /* isBST */
        int maxEle = -(int) 1e8;
        int minEle = (int) 1e8;
        boolean isBST = true;

        /* isBal */
        boolean isBal = true;
        int height = -1;

        /* total No Of BST*/ 
        int countOfBST = 0;

        /* lrgest BST Node */ 
        int largestBSTSize = 0;
        Node largestBSTNode = null;
    }

    public static binarySearchTree allSolution(Node node) {
        if (node == null)
            return new binarySearchTree();

        binarySearchTree left = allSolution(node.left); // left subtree
        binarySearchTree right = allSolution(node.right); // right subtree

        binarySearchTree myRes = new binarySearchTree();

        /* isBST */ 
        myRes.isBST = left.isBST && right.isBST && left.maxEle < node.data && node.data < right.minEle;
        myRes.maxEle = Math.max(node.data, right.maxEle);
        myRes.minEle = Math.min(node.data, left.minEle);

        /* isBST balance */ 
        myRes.isBal = left.isBal && right.isBal && Math.abs(left.height - right.height) <= 1;
        myRes.height = Math.max(left.height, right.height) + 1;

        /* total BST */ 
        myRes.countOfBST = left.countOfBST + right.countOfBST + (myRes.isBST ? 1 : 0);

        /* lrgest BST Node */ 
        if (myRes.isBST) {
            myRes.largestBSTNode = node;
            myRes.largestBSTSize += left.largestBSTSize + right.largestBSTSize + 1;
        } else {
            if (left.largestBSTSize < right.largestBSTSize) {
                myRes.largestBSTNode = left.largestBSTNode;
                myRes.largestBSTSize = left.largestBSTSize;
            } else {
                myRes.largestBSTNode = right.largestBSTNode;
                myRes.largestBSTSize = right.largestBSTSize;
            }
        }

        return myRes;

    }

    // ==============================================================================================================================================================
    // Question_14 : tilt of binary tree
    // https://leetcode.com/problems/binary-tree-tilt/
    public static class tiltSolPair {
        int sum = 0;
        int tilt = 0;
    }

    public static tiltSolPair tiltFun(Node node) {
        if (node == null) {
            return new tiltSolPair();
        }

        tiltSolPair left = tiltFun(node.left); // left subtree
        tiltSolPair right = tiltFun(node.right); // right sub

        tiltSolPair ans = new tiltSolPair();

        ans.sum += left.sum + right.sum + node.data;
        ans.tilt += left.tilt + right.tilt + Math.abs(left.sum - right.sum);

        return ans;
    }

    public static int tilt(Node node) {
        // write your code here to set the tilt data member
        tiltSolPair ans = tiltFun(node);
        return ans.tilt;

    }
}