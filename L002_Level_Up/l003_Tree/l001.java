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
     public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }
    // ======================================================================================================
    // ======================================================================================================
    // DATE:22/08
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
        if (root.val == data) // self
            return true;

        return find(root.left, data) || find(root.right, data); // left call || right call
    }
    // ======================================================================================================
    // PROBLEMS: Node-To-Root-Path
    // 1. return type ArrayList
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
    // 2. boolean type
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

    // PROBLEMS: root-To-All-Leaf-Path_
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

    // PROBLEMS: all-single-child-parent-in-binary-tree
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

    // PROBLEMS: count single child
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

    // PROBLEMS: All distacne K in binary Tree
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
    // facts------>>>>>>
    // 1. agar target data, nehi milta toh -1 return karenge
    // 2. agar data milta he toh non negative value return karenge
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
    
    // ======================================================================================================
    // ======================================================================================================
    
    // 236. Lowest Common Ancestor of a Binary Tree
    public boolean NodeToRootPath(TreeNode root, TreeNode node, List<TreeNode> path) {
        if (root == null)
            return false;

        boolean res = root == node || NodeToRootPath(root.left, node, path) || NodeToRootPath(root.right, node, path);
        if (res)
            path.add(root);

        return res;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();
        NodeToRootPath(root, p, path1);
        NodeToRootPath(root, q, path2);

        TreeNode LCA = null;
        int i = path1.size() - 1;
        int j = path2.size() - 1;
        while (i >= 0 && j >= 0 && path1.get(i) == path2.get(j)) {
            LCA = path1.get(i);
            i--;
            j--;
        }

        return LCA;

    }

 
    private TreeNode LCA = null;
    public boolean lowestCommonAncestor_(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return false;

        boolean self = root == p || root == q;

        boolean left = lowestCommonAncestor_(root.left, p, q);
        if (LCA != null)
            return true;

        boolean right = lowestCommonAncestor_(root.right, p, q);
        if (LCA != null)
            return true;

        if ((self && left) || (self && right) || (left && right))
            LCA = root;

        return self || left || right;

    }

    public TreeNode lowestCommonAncestor__(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestor_(root, p, q);
        return LCA;
    }

    // =================================================================================================
    // PREDECESSOR_SUCCESSOR_============================================================================
    public static class allSolnPair {
        TreeNode pred = null;
        TreeNode succ = null;
        TreeNode prev = null;

        int floor = -(int) 1e9;
        int ceil = (int) 1e9;
    }

    public static void precAndSucc(TreeNode root, int data, allSolnPair pair) {
        precAndSucc(root.left, data, pair);
        if (pair.prev.val == data && pair.pred == null)
            pair.pred = pair.prev;
        if (pair.pred != null && pair.prev.val == data)
            pair.succ = root;

        pair.prev = root;
        precAndSucc(root.right, data, pair);
    }

    // Floor_Ceil==================================================================
    public static void FloorNceil(TreeNode node, int data, allSolnPair pair) {
        if (node.val < data)
            pair.floor = Math.max(pair.floor, node.val);
        else
            pair.ceil = Math.min(pair.ceil, node.val);
        FloorNceil(node.left, data, pair);
        FloorNceil(node.right, data, pair);
    }

    // 110. Balanced Binary Tree
    public int getHeight(TreeNode node){
        if(node==null) return 0;
        return Math.max(getHeight(node.left),getHeight(node.right))+1;
    }
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        int left =getHeight(root.left); 
        int right = getHeight(root.right);
        return ((int)Math.abs(left - right) < 2) && isBalanced(root.left) && isBalanced(root.right) ; // self && left && right
    }

   
}