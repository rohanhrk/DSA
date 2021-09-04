import java.util.ArrayList;

public class l008_Inorder_MorrisTraversal {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode getRightMost(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }

        return node;
    }

    public static ArrayList<Integer> morrisInorderTraversal(TreeNode root) {
        TreeNode curr = root;
        ArrayList<Integer> ans = new ArrayList<>();

        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMost(left, curr);
                if (rightMost.right == null) { // thread creation area
                    rightMost.right = curr; // thread create
                    curr = curr.left;
                } else { // thread destroy area
                    rightMost.right = null; // thread break
                    ans.add(curr.val);

                    curr = curr.right;
                }
            }

        }

        return ans;
    }

    // Question============================================================================================================
    // validate BST --> using morris Trversal

    // public staic TreeNode getRightMost(TreeNode node, TreeNode curr) {
    // while(node.right != null && node.right != curr) {
    // node = node.right;
    // }

    // return node;
    // }
    public static boolean isBST(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        boolean isBST = true;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (prev != null && prev.val >= curr.val)
                    isBST = false;
                prev = curr;

                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMost(left, curr);
                if (rightMost.right == null) { // thread creation area
                    rightMost.right = curr; // thread create
                    curr = curr.left;

                } else { // thread destroy area
                    rightMost.right = null; // thread break
                    if (prev != null && prev.val >= curr.val)
                        isBST = false;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        return isBST;
    }

    // kth smallest
    public int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (--k == 0)
                    return curr.val;
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMost(left, curr);
                if (rightMost.right == null) { // thread creation area
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    rightMost.right = null;
                    if (--k == 0)
                        return curr.val;
                    curr = curr.right;
                }
            }
        }

        return -1;
    }

    public static TreeNode getLeftMost(TreeNode node, TreeNode curr) {
        while (node.left != null && node.left != curr) {
            node = node.left;
        }
        return node;
    }

    // Kth largest
    public static int kthLatgest(TreeNode root, int k) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode right = curr.right;
            if (right == null) {
                if (--k == 0)
                    return curr.val;
                curr = curr.left;
            } else {
                TreeNode leftMost = getLeftMost(right, curr);
                if (leftMost.left == null) { // thread createtion area
                    leftMost.left = curr; // thread cread
                    curr = curr.right;
                } else {
                    leftMost.left = null;
                    if (--k == 0)
                        return curr.val;
                    curr = curr.left;
                }
            }
        }

        return -1;
    }

    // binary search tree to doubly linkedlist
    public static TreeNode bstToDLL(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        TreeNode curr = root, prev = dummy;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                prev.right = curr;
                curr.left = prev;
                prev = curr;

                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMost(left, curr);
                if (rightMost.right == null) { // thread creation area
                    rightMost.right = curr; // thread create
                    curr = curr.left;
                } else { // thread destroy area
                    rightMost.right = null; // break

                    prev.right = curr;
                    curr.left = prev;
                    prev = curr;

                    curr = curr.right;
                }
            }
        }

        TreeNode head = dummy.right;
        dummy.right = head.left = null;
        return head;
    }

    // BST iterator using Morris traversal
    public class BSTIterator {
        TreeNode curr = null;

        public BSTIterator(TreeNode root) {
            this.curr = root;
        }

        public int next() {
            int rn = 0;
            while (curr != null) {
                TreeNode left = curr.left;
                if (left == null) {
                    rn = curr.val;
                    curr = curr.right;
                    break;
                } else {
                    TreeNode rightMost = getRightMost(left, curr);
                    if (rightMost.right == null) { // thread creation area
                        rightMost.right = curr;
                        curr = curr.left;
                    } else { // thread destroy area
                        rightMost.right = null;
                        rn = curr.val;
                        curr = curr.right;
                        break;
                    }
                }
            }

            return rn;
        }

        public boolean hasNext() {
            return curr != null;
        }
    }

}