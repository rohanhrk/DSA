public class l007_AVL {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        int bal = 0;
        int height = 0;

        TreeNode(int val) {
            this.val = val;
            this.bal = 0;
            this.height = 0;
        }
    }

    // ===========================================================================================================
    public static void updateBalanceHeight(TreeNode root) {
        int lh = root.left != null ? root.left.height : -1;
        int rh = root.right != null ? root.right.height : -1;

        root.height = Math.max(lh, rh) + 1;
        root.bal = (lh - rh);
    }

    public static TreeNode rightRotation(TreeNode A) { // right-right rotation
        TreeNode B = A.left;
        TreeNode BKaRight = B.right;

        B.right = A;
        A.left = BKaRight;

        updateBalanceHeight(A);
        updateBalanceHeight(B);

        return B;
    }

    public static TreeNode leftRotation(TreeNode A) { // left-left rotation
        TreeNode B = A.right;
        TreeNode BKaLeft = B.left;

        B.left = A;
        A.right = BKaLeft;

        updateBalanceHeight(A);
        updateBalanceHeight(B);

        return B;
    }

    public static TreeNode getRotation(TreeNode root) {
        updateBalanceHeight(root);

        if (root.bal == 2) { // ll , lr
            if (root.left.bal == 1) { // ll
                root = rightRotation(root);
            } else { // lr
                root.left = leftRotation(root.left);
                return rightRotation(root);
            }
        } else if (root.bal == -2) { // rr, rl
            if (root.right.bal == -1) { // rr
                root = leftRotation(root);
            } else { // rl
                root.right = rightRotation(root.right);
                return leftRotation(root);
            }
        }

        return root;
    }

    // ===========================================================================================================

    public static TreeNode add(TreeNode root, int data) {
        if (root == null)
            return new TreeNode(data);
            
        if (data < root.val)
            root.left = add(root.left, data);
        else
            root.right = add(root.right, data);

        root = getRotation(root);
        return root;
    }

    public static TreeNode getMaximum(TreeNode root) {
        TreeNode curr = root;
        while (curr.right != null)
            curr = curr.right;
        return curr;
    }

    public static TreeNode remove(TreeNode root, int data) {
        if (data < root.val)
            root.left = add(root.left, data);
        else if (data > root.val)
            root.right = add(root.right, data);

        else {
            if (root.left == null || root.right == null) {
                return root.left != null ? root.left : root.right;
            }

            TreeNode maxNode = getMaximum(root.left);
            root.val = maxNode.val;

            remove(root.left, maxNode.val);
        }

        root = getRotation(root);
        return root;
    }
}
