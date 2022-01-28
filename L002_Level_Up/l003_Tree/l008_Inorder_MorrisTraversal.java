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

    // ===================================================================================================================================
    // Question_1 : Morris Inorder traversal
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
            TreeNode left = curr.left; // backup
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

    // ===================================================================================================================================
    // Question_2 : 98. Validate Binary Search Tree 
    // https://leetcode.com/problems/validate-binary-search-tree/
    // --> using morris Trversal
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

    // ===================================================================================================================================
    // Question_3 : 230. Kth Smallest Element in a BST
    // https://leetcode.com/problems/kth-smallest-element-in-a-bst/
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

    // ===================================================================================================================================
    // Question_4 : Kth largest element in BST
    // https://practice.geeksforgeeks.org/problems/kth-largest-element-in-bst/1/
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

    // ===================================================================================================================================
    // Question_5 : binary search tree to doubly linkedlist
    // https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1
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

    // ===================================================================================================================================
    // Question_6 : BST iterator using Morris traversal
    // https://leetcode.com/problems/binary-search-tree-iterator/
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

    // ===================================================================================================================================
    // Question_7 : 99. Recover Binary Search Tree
    // https://leetcode.com/problems/recover-binary-search-tree/
    private void swap(TreeNode n1, TreeNode n2) {
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }
    private TreeNode getRightMostInLeftSubtree(TreeNode node, TreeNode curr) {
        while(node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }
    public void recoverTree(TreeNode root) {
        TreeNode curr = root, prev = null, first = null, second = null;
        while(curr != null) {
            TreeNode left = curr.left;
            if(left == null) {
                // may be left subtree is completely traverse
                if(prev != null && prev.val > curr.val) {
                    if(first == null) {
                        first = prev;
                    } 
                        second = curr;
                
                }
                prev = curr;
                
                curr = curr.right;
            } else {
                // thread creation area
                TreeNode right_most = getRightMostInLeftSubtree(left, curr);
                if(right_most.right == curr) {
                    // means left subtree is already traverse
                    right_most.right = null; // break thread
                    
                    // also IN area
                    if(prev != null && prev.val > curr.val) {
                        if(first == null) {
                            first = prev;
                        } 
                            second = curr;
                  
                    }
                    prev = curr;
                    
                    curr = curr.right;
                } else {
                    // create a thread
                    right_most.right = curr;
                    curr = left;
                }
            }
        }
        
        swap(first, second);
    }

}