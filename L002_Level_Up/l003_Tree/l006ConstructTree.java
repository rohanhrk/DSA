import java.util.LinkedList;

public class l006ConstructTree {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode constructFromInOrder(int[] inOrder, int si, int ei) {
        if (si > ei)
            return null;
        int mid = (si + ei) / 2;
        TreeNode root = new TreeNode(inOrder[mid]);

        root.left = constructFromInOrder(inOrder, si, mid - 1);
        root.right = constructFromInOrder(inOrder, mid + 1, ei);

        return root;
    }

    // construct-bst-from-preorder-traversal
    static int idx;

    public static TreeNode bstFromPreorder(int[] preorder, int lr, int rr) {
        if (idx == preorder.length || rr < preorder[idx] || lr > preorder[idx])
            return null;

        TreeNode root = new TreeNode(preorder[idx++]);

        root.left = bstFromPreorder(preorder, lr, root.val);
        root.right = bstFromPreorder(preorder, root.val, rr);

        return root;
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        idx = 0;
        return bstFromPreorder(preorder, -(int) 1e9, (int) 1e9);

    }

    // construct-bst-from-postorder-traversal
    public static TreeNode bstFromPostorder(int[] postorder, int lr, int rr) {
        if (idx == -1 || rr < postorder[idx] || lr > postorder[idx])
            return null;

        TreeNode root = new TreeNode(postorder[idx--]);

        root.right = bstFromPostorder(postorder, root.val, rr);
        root.left = bstFromPostorder(postorder, lr, root.val);

        return root;
    }

    public static TreeNode bstFromPostorder(int[] postorder) {
        idx = postorder.length - 1;
        return bstFromPostorder(postorder, -(int) 1e9, (int) 1e9);
    }

    // construct-bst-from-levelorder-traversal
    public static class BSTLpair {
        TreeNode parent = null;
        int lb = 0; // left boundary
        int rb = 0; // right boundary

        BSTLpair(TreeNode parent, int lb, int rb) {
            this.parent = parent;
            this.lb = lb;
            this.rb = rb;
        }
    }

    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder) {
        if (LevelOrder.length == 0)
            return null;
        LinkedList<BSTLpair> que = new LinkedList<>();
        TreeNode root = new TreeNode(LevelOrder[0]);
        int index = 1;

        que.addLast(new BSTLpair(root, -(int) 1e9, root.val));
        que.addLast(new BSTLpair(root, root.val, (int) 1e9));

        while (index < LevelOrder.length) {
            BSTLpair rpair = que.removeFirst();
            TreeNode parent = rpair.parent;
            int lb = rpair.lb, rb = rpair.rb;

            if (LevelOrder[index] < lb || LevelOrder[index] > rb)
                continue;

            TreeNode childNode = new TreeNode(LevelOrder[index++]);
            if (childNode.val < parent.val)
                parent.left = childNode;
            else
                parent.right = childNode;

            que.addLast(new BSTLpair(childNode, lb, childNode.val));
            que.addLast(new BSTLpair(childNode, childNode.val, rb));
        }

        return root;

    }

    // 105. Construct Binary Tree from Preorder and Inorder Traversal
    public static int findValIninorder(int val, int[] inorder) {
        int idx = 0;
        while (inorder[idx] != val)
            idx++;

        return idx;
    }

    public TreeNode InOrPre(int[] preorder, int[] inorder, int Psi, int Pei, int Isi, int Iei) {
        if (Psi > Pei || Isi > Iei)
            return null;
        TreeNode root = new TreeNode(preorder[Psi]);
        int idx = findValIninorder(preorder[Psi], inorder);
        int Tel = idx - Isi;
        root.left = InOrPre(preorder, inorder, Psi + 1, Psi + Tel, Isi, idx - 1);
        root.right = InOrPre(preorder, inorder, Psi + Tel + 1, Pei, idx + 1, Iei);

        return root;

    }

   
    // 106. Construct Binary Tree from Inorder and Postorder Traversal
    public  TreeNode InOrPost(int[] postorder, int[] inorder, int Psi, int Pei, int Isi, int Iei) {
        if (Psi > Pei || Isi > Iei)
            return null;
        TreeNode root = new TreeNode(postorder[Pei]);
        int idx = findValIninorder(postorder[Pei], inorder);
        int Tel = idx - Isi;
        
        root.left = InOrPost(postorder, inorder, Psi, Psi + Tel - 1, Isi, idx - 1);
        root.right = InOrPost(postorder, inorder, Psi + Tel, Pei - 1, idx + 1, Iei);

        return root;

    }
    
    public  TreeNode buildTree(int[] inorder, int[] postorder) {
        // return InOrPre(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        return InOrPost(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);
    }
    public static void main(String[] args) {

    }
}