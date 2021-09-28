import java.util.ArrayList;
import java.util.LinkedList;

public class l003BST {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ============================================================================================
    // DATE: 24/ 08
    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int Maximum(TreeNode root) {
        TreeNode curr = root;
        while (curr.right != null)
            curr = curr.right;
        return curr.val;
    }

    public static int Minimum(TreeNode root) {
        TreeNode curr = root;
        while (curr.left != null)
            curr = curr.left;
        return curr.val;
    }

    public static boolean find(TreeNode root, int data) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.val == data)
                return true;
            else if (curr.val < data)
                curr = curr.right;
            else
                curr = curr.left;
        }

        return false;
    }

    public static ArrayList<Integer> NodeToRootPath(TreeNode root, int data) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        boolean flag = false;
        while (curr != null) {
            ans.add(curr.val);
            if (root.val < data)
                curr = curr.right;
            else if (root.val > data)
                curr = curr.left;
            else {
                flag = true;
                break;
            }
        }
        if (!flag)
            ans.clear();

        return ans;
    }

    public static boolean NodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans) {
        return false;
    }

    // 235. Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val < p.val && curr.val < q.val) {
                curr = curr.right;
            } else if (curr.val > p.val && curr.val > q.val) {
                curr = curr.left;
            } else {
                break;
            }
        }

        return curr;
    }

    // 173. Binary Search Tree Iterator
    // Time -> O(N) , space -> O(log(N))
    class BSTIterator {
        LinkedList<TreeNode> st;

        public BSTIterator(TreeNode root) {
            st = new LinkedList<>();
            addAllLeft(root);
        }

        public void addAllLeft(TreeNode node) {
            while (node != null) {
                this.st.addFirst(node);
                node = node.left;
            }
        }

        // time->on an average O(1) worst time -> O(log(N)) --> worst time will never achieved
        public int next() {
            TreeNode topNode = this.st.removeFirst();
            addAllLeft(topNode.right);
            return topNode.val;
        }

        // time->O(1)
        public boolean hasNext() {
            return this.st.size() != 0;
        }
    }

    // ============================================================================================
    // Predecessor_N_Successor
    public static TreeNode getLeftMost(TreeNode node) {
        if (node == null)
            return node;
        TreeNode curr = node;
        while (curr.left != null)
            curr = curr.left;
        return curr;
    }

    public static TreeNode getRightMost(TreeNode node) {
        if (node == null)
            return node;
        TreeNode curr = node;
        while (curr.right != null)
            curr = curr.right;
        return curr;
    }

    public static void Predecessor_N_Successor(TreeNode root, int data) {
        TreeNode curr = root, pred = null, succ = null;

        while (curr != null) {
            if (curr.val == data) {
                TreeNode leftMost = getLeftMost(curr.right);
                succ = leftMost != null ? leftMost : succ;

                TreeNode rightMost = getRightMost(curr.left);
                pred = rightMost != null ? rightMost : pred;

                break;
            } else if (curr.val > data) {
                succ = curr;
                curr = curr.left;
            } else {
                pred = curr;
                curr = curr.right;
            }
        }
    }

    // 230. Kth Smallest Element in a BST
    public void addAllLeft(LinkedList<TreeNode> st, TreeNode node) {
        while (node != null) {
            st.addLast(node);
            node = node.left;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> st = new LinkedList<>();
        addAllLeft(st, root);
        int element = 0;
        int count = 0;

        while (st.size() != 0) {
            TreeNode rnode = st.removeFirst();
            if (++count == k) {
                element = rnode.val;
                break;
            }
            addAllLeft(st, rnode.right);
        }

        return element;
    }

    // add Node in Bst

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        TreeNode curr = root, prev = null;
        while (curr != null) {
           if (curr.val > val) {
               prev = curr;
               curr = curr.left;
               if (curr == null) {
                   prev.left = new TreeNode(val);
                   break;
               }

           } else {
               prev = curr;
               curr = curr.right;
               if (curr == null) {
                   prev.right = new TreeNode(val);
                   break;
               }

           }
       }
       
       return root;
   }

    // remove node in BST

}