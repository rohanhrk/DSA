import java.util.ArrayList;
import java.util.List;

public class l001 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int Maximum(TreeNode root) {
        return root == null ? -(int) 1e9 : Math.max(Math.max(Maximum(root.left), Maximum(root.right)), root.val);
    }

    public static boolean find(TreeNode root, int data) {
        if (root == null)
            return false;
        if (root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    public static ArrayList<TreeNode> NodeToRootPath(TreeNode root, int data) {
        if (root == null)
            return new ArrayList<>();

        if (root.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<TreeNode> left = NodeToRootPath(root.left, data);
        if (left.size() != 0) {
            left.add(root);
            return left;
        }

        ArrayList<TreeNode> right = NodeToRootPath(root.right, data);
        if (right.size() != 0) {
            right.add(root);
            return right;
        }

        return new ArrayList<>();
    }

    public static boolean NodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans) {
        if (root == null)
            return false;

        if (root.val == data) {
            ans.add(root);
            return true;
        }

        boolean res = NodeToRootPath(root.left, data, ans) || NodeToRootPath(root.right, data, ans);
        if (res)
            ans.add(root);
        return res;
    }

    // root To All Leaf Path
    public static void rootToAllLeafPath(TreeNode root, ArrayList<Integer> smallAns,
            ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
        }

        smallAns.add(root.val);

        rootToAllLeafPath(root.left, smallAns, ans);
        rootToAllLeafPath(root.right, smallAns, ans);

        smallAns.remove(smallAns.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        // write your code.
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>();
        rootToAllLeafPath(root, smallAns, ans);
        return ans;
    }

    // all-single-child-parent-in-binary-tree
    public static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
        if (root == null || (root.left == null && root.right == null))
            return;

        if (root.left == null || root.right == null) {
            ans.add(root.val);
        }

        exactlyOneChild(root.left, ans);
        exactlyOneChild(root.right, ans);

    }

    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChild(root, ans);
        return ans;
    }

    // count single child
    public static int countOneChild(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return 0;

        int leftCount = countOneChild(root.left);
        int rightCount = countOneChild(root.right);

        int count = leftCount + rightCount;
        if (root.left == null || root.right == null) {
            count++;
        }

        return count;
    }

    // All distacne K in binary Tree

    public void kDown(TreeNode root, int k, TreeNode blockNode, List<Integer> ans) {
        if (root == null || k < 0 || root == blockNode)
            return;

        if (k == 0) {
            ans.add(root.val);
        }

        kDown(root.left, k - 1, blockNode, ans);
        kDown(root.right, k - 1, blockNode, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        // root to Node path
        ArrayList<TreeNode> path = new ArrayList<>();
        NodeToRootPath(root, target.val, path);

        // store all Node at K depth
        TreeNode blockNode = null;
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < path.size(); i++) {
            if (k - i >= 0) {
                kDown(path.get(i), k - i, blockNode, ans);
                blockNode = path.get(i);
            }
        }

        return ans;
    }

    // space optimize
    public void kDown(TreeNode root, TreeNode blockNode, int K, List<Integer> ans) {
        if (root == null || root == blockNode || K < 0)
            return;

        if (K == 0) {
            ans.add(root.val);
            return;
        }

        kDown(root.left, blockNode, K - 1, ans);
        kDown(root.right, blockNode, K - 1, ans);
    }

    public int distanceK_01(TreeNode root, TreeNode target, int k, List<Integer> ans) {
        if (root == null)
            return -1;
        if (root == target) {
            kDown(root, null, k, ans);
            return 1;
        }

        int ld = distanceK_01(root.left, target, k, ans);
        if (ld != -1) {
            kDown(root, root.left, k - ld, ans);
            return ld + 1;
        }

        int rd = distanceK_01(root.right, target, k, ans);
        if (rd != -1) {
            kDown(root, root.right, k - rd, ans);
            return rd + 1;
        }

        return -1;
    }

    public List<Integer> distanceK_01(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        distanceK_01(root, target, k, ans);
        return ans;
    }

    
}