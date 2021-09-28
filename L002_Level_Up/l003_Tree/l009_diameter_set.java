public class l009_diameter_set {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public int diameterOfBinaryTree_01(TreeNode root) {
        int ld = diameterOfBinaryTree_01(root.left);
        int rd = diameterOfBinaryTree_01(root.right);

        int lh = height(root.left);
        int rh = height(root.right);

        int myDiameter = lh + rh + 2;
        return Math.max(Math.max(ld, rd), myDiameter);
    }

    // {diameter, height}
    public int[] diameterOfBinaryTree_02(TreeNode root) {
        int[] lp = diameterOfBinaryTree_02(root.left);
        int[] rp = diameterOfBinaryTree_02(root.right);

        int[] myPair = new int[2];
        myPair[0] = Math.max(Math.max(lp[0], rp[0]), lp[1] + rp[1] + 2);
        myPair[1] = Math.max(lp[1], rp[1]) + 1;

        return myPair;
    }

    public int diameterOfBinaryTree_03(TreeNode root, int[] dia) {
        if (root == null)
            return -1;
        int lh = diameterOfBinaryTree_03(root.left, dia);
        int rh = diameterOfBinaryTree_03(root.right, dia);
        dia[0] = Math.max(dia[0], lh + rh + 2);
        return Math.max(lh, rh) + 1;
    }

    // ===============================================================================================================
     // 113. Path Sum II

    public void pathSum_(TreeNode root, int targetSum, List<Integer> smallAns, List<List<Integer>> ans) {
        if(root == null) {
            return;
        }
        
        if(root.left == null && root.right == null) {
            if(targetSum - root.val == 0) {
                List<Integer> base = new ArrayList<>(smallAns);
                base.add(root.val);
                ans.add(base);
            }
            return;
        }
        smallAns.add(root.val);
        pathSum_(root.left, targetSum - root.val, smallAns, ans);
        pathSum_(root.right, targetSum - root.val, smallAns, ans);
        smallAns.remove(smallAns.size() - 1);

    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> ans =new ArrayList<>();
        pathSum_(root, targetSum, smallAns, ans);
        return ans;
    }

    // 112. Path Sum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null)
            return targetSum - root.val == 0 ? true : false;
        
        return hasPathSum(root.left , targetSum - root.val) || hasPathSum(root.right , targetSum - root.val);
    }

    // https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
    //_find-maximum-path-sum-two-leaves-binary-tree
    public static class leafToLeafPair {
        int LTLMaxSum = -(int)1e9; // leave to leave maximum sum 
        int NTLMaxSum = -(int)1e9; // Node to leave maximum sum
    }

    public static leafToLeafPair maxSum(TreeNode root) {
         if(root == null) 
            return new leafToLeafPair();
        
        if(root.left == null && root.right == null) { // leave node
            leafToLeafPair base = new leafToLeafPair();
            base.NTLMaxSum = root.data;
            return base;
        }
        
        leafToLeafPair lRes = maxSum(root.left);
        leafToLeafPair rRes = maxSum(root.right);

        leafToLeafPair myRes = new leafToLeafPair();
        myRes.LTLMaxSum = Math.max(lRes.LTLMaxSum, rRes.LTLMaxSum);

        if(root.left != null && root.right != null) 
            myRes.LTLMaxSum = Math.max(myRes.LTLMaxSum, lRes.NTLMaxSum + root.data + rRes.NTLMaxSum);

        myRes.NTLMaxSum = Math.max(lRes.NTLMaxSum, rRes.NTLMaxSum) + root.data;
        return myRes;
    }

    public static int maxPathSum(Node root) { 
        int ans1 = maxSum(root).LTLMaxSum;
        int ans2 = maxSum(root).NTLMaxSum;
        return ans1 != -(int)1e9 ? ans1 : Math.max(ans1,ans2);
    } 

    // 124. Binary Tree Maximum Path Sum

    public class NTNPair {
        int maxPossiblePathSum = -(int) 1e9;   // potential max sum path --> jo hamara ans bon sakta he
        int NTNMaxPathSum = 0; // node to node maximum path sum
    }  

    public int getMaximum(int... arr) {
        int max = -(int)1e9;
        for(int elem : arr) 
            max = Math.max(max, elem);
        return max;
    }
    public NTNPair maxPathSum_helper(TreeNode root) {
        NTNPair myPair = new NTNPair();
        if(root == null) return myPair;
        NTNPair leftSubtree = maxPathSum_helper(root.left);
        NTNPair rightSubtree = maxPathSum_helper(root.right);

        int oneSidedNodeToNodeMax = Math.max(leftSubtree.NTNMaxPathSum , rightSubtree.NTNMaxPathSum) + root.val;
        myPair.maxPossiblePathSum = getMaximum(leftSubtree.maxPossiblePathSum , rightSubtree.maxPossiblePathSum, root.val, oneSidedNodeToNodeMax, leftSubtree.NTNMaxPathSum + root.val  + rightSubtree.NTNMaxPathSum);
        myPair.NTNMaxPathSum = Math.max(oneSidedNodeToNodeMax, root.val);

        return myPair;
    }
    public int maxPathSum(TreeNode root) {
        return maxPathSum_helper(root).maxPossiblePathSum;
    }

    //  968. Binary Tree Cameras
    // -1 --> camera required,  0 --> already covered , 1 --> I have a cameras
    public int minCameraCover(TreeNode root, int[] noOfCamerasInstal) {
        int lr = minCameraCover(root.left, noOfCamerasInstal);
        int rr = minCameraCover(root.right, noOfCamerasInstal);

        if(lr == -1 || rr == -1) {
            noOfCamerasInstal[0]++;
            return 1;
        }

        if(lr == 1 || rr == 1) return 0;

        return -1; 
    } 

    // 337. House Robber III

    public class housePair {
        int withRobbery = 0;
        int withoutRobbery = 0;
    }

    public housePair houseRobbery(TreeNode root) {
        if(root == null) 
            return new housePair();

        housePair left = houseRobbery(root.left);
        housePair right = houseRobbery(root.right);

        housePair myAns = new housePair();
        myAns.withRobbery = left.withoutRobbery + root.val + right.withoutRobbery;
        myAns.withoutRobbery = Math.max(left.withRobbery + left.withoutRobbery) + Math.max(right.withRobbery + right.withoutRobbery);

        return myAns;
    }
    public int rob(TreeNode root) {
        housePair ans = houseRobbery(root);
        return Math.max(ans.withRobbery , ans.withoutRobbery);
    }

    // 2nd approach using array
    // {withRobbery, withoutRobbery}

    public static int[] houseRobbery02(TreeNode root) {
        if(root == null) return new int[2];
        
        int[] left = houseRobbery02(root.left);
        int[] right = houseRobbery02(root.right);

        int[] myAns = new int[2];
        myAns[0] = left[1] + root.val + right[1]; // with robbery
        myAns[1] = Math.max(left[0] , left[1]) + Math.max(right[0] , right[1]);

        return myAns;
    }

    // 1372. Longest ZigZag Path in a Binary Tree
    public class zigzagPair {
        int forwardSlop = -1;
        int backwardSlop = -1;
        int maxZigZagLen = 0;
    }

    public int getMaximum(int...  arr) {
        int max = arr[0];
        for(int elem : arr) 
            max = Math.max(max, elem);

        return max;
    }
    public zigzagPair longestZigZag(TreeNode root) {
        if(root == null) return new zigzagPair();

        zigzagPair left = longestZigZag(root.left);
        zigzagPair right = longestZigZag(root.right);

        zigzagPair myAns = new zigzagPair();
        myAns.forwardSlop = left.backwardSlop + 1;
        myAns.backwardSlop = right.forwardSlop + 1;
        myAns.maxZigZagLen = getMaximum(left.maxZigZagLen, right.maxZigZagLen , myAns.forwardSlop, myAns.backwardSlop);

        return myAns;
    }

}