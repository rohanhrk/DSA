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

   public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
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

    // 889. Construct Binary Tree from Preorder and Postorder Traversal
    // psi -> pre start idx , pei -> pre end idx
    // ppsi -> post start idx, ppei -> post end idx , tnel -> total no of elem
    public TreeNode constructFromPrePost_helper(int[] preorder, int psi, int pei, int[] postorder, int ppsi, int ppei) {
        if(psi > pei) return null;
        TreeNode root = new TreeNode(preorder[psi]);
        if(psi == pei) return root;
        int idx = ppei;
        while(postorder[idx] != preorder[psi+1]) idx++;

        int tnel = idx - ppsi + 1;

        root.left = constructFromPrePost_helper(preorder, psi + 1, psi + tnel, postorder, ppsi, idx);
        root.right = constructFromPrePost_helper(preorder, psi+tnel+1, pei, postorder, idx + 1, ppei - 1);

        return root;
    }
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        return constructFromPrePost_helper(preorder, 0, n - 1, postorder, 0, n-1);
    }

    // Construct tree from Inorder and LevelOrder
    // https://practice.geeksforgeeks.org/problems/construct-tree-from-inorder-and-levelorder/1
    Node buildTree_helper(int inord[], int isi, int iei, int level[]) {
        //your code here
        if(isi > iei) return null;
        Node root = new Node(level[0]);
        if(isi == iei) return root;

        int idx = isi;
        while(inord[idx] != root.data) idx++;

        HashSet<Integer> set = new HashSet<>();
        for(int i = isi; i < idx; i++) set.add(inord[i]);

        int[] LOrderOfLeftSubtree = new int[idx - isi];
        int[] LOrderOfrightSubtree = new int[iei - idx];
        int ls = 0, rs = 0;
        for(int i = 1; i < level.length; i++) {
            int ele = level[i];
            if(set.size() != 0 && set.contains(ele)) {
                LOrderOfLeftSubtree[ls++] = ele;
                set.remove(ele);
            } else 
                LOrderOfrightSubtree[rs++] = ele;
        }

        root.left = buildTree_helper(inord, isi, idx - 1, LOrderOfLeftSubtree);
        root.right = buildTree_helper(inord, idx + 1, iei, LOrderOfrightSubtree);

        return root;
    }
    Node buildTree(int inord[], int level[]) {
        //your code here
        int n = inord.length;
        return buildTree_helper(inord, 0, n - 1, level);
    }

    // 297. Serialize and Deserialize Binary Tree
     // Encodes a tree to a single string.
    public void serialize_helper(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("null ");
            return;
        }
        sb.append(root.val + " ");
        serialize_helper(root.left, sb);
        serialize_helper(root.right, sb);
    }
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize_helper(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public int idx = 0;
    public TreeNode deserialize_helper(String[] arr) {
        if(idx >= arr.length || arr[idx].equals("null")) {
            idx++;
            return null;
        }
        
        int val = Integer.parseInt(arr[idx++]);
        TreeNode root = new TreeNode(val);
        root.left = deserialize_helper(arr);
        root.right = deserialize_helper(arr);

        return root;
    }
    public TreeNode deserialize(String data) {
        String[] arr = data.split(" ");
        return deserialize_helper(arr);
    }

    // 449. Serialize and Deserialize BST
    // Encodes a tree to a single string.
    public void serialize_preorder(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("null ");
            return;
        }
        sb.append(root.val + " ");
        serialize_preorder(root.left, sb);
        serialize_preorder(root.right, sb);
    }
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize_preorder(root,sb);
        return sb.toString();
    }
    
    int idx;
    //  lr -> left range , rr -> right range
    public TreeNode deserialize_preorder(String[] preorder, int lr, int rr) {
        if(idx >= preorder.length || preorder[idx].equals("null") || rr < Integer.parseInt(preorder[idx]) || lr > Integer.parseInt(preorder[idx])) {
            idx++;
            return null;
        }
            
        
        int val = Integer.parseInt(preorder[idx++]);
        TreeNode root = new TreeNode(val);
        
        root.left = deserialize_preorder(preorder, lr, root.val);
        root.right = deserialize_preorder(preorder, root.val, rr);
        return root;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] preorder = data.split(" ");
        idx = 0;
        return deserialize_preorder(preorder, -(int) 1e9, (int) 1e9);
    }
    public static void main(String[] args) {

    }
}