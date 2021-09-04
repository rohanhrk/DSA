import java.util.ArrayList;

public class l008_PreorderMorrisTraversal {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    public static TreeNode getRightMost(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr)
            node = node.right;

        return node;
    }

    public static ArrayList<Integer> preorderMorrisTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                 ans.add(curr.val);
                curr = curr.right;

            } else {
                TreeNode rightMost = getRightMost(left, curr);
                if (rightMost.right == null) { // thread creation area
                    rightMost.right = curr;
                    ans.add(curr.val);
                    curr = curr.left;
                } else {
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }

        return ans;
    }
}