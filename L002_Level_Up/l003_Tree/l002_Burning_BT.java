import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

// DATE: 3/ 08/ 2021
// =============================================_AMAZON_=============================================
public class l002_Burning_BT {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ****************************_burn_the_binary_tree_****************************
    public boolean rootToNodePath(TreeNode root, TreeNode target, List<TreeNode> ans) {
        if (root == null)
            return false;
        boolean res = root == target || rootToNodePath(root.left, target, ans)
                || rootToNodePath(root.right, target, ans);
        if (res)
            ans.add(root);
        return res;
    }

    // faith ----->>>>>>
        // 1. hum time ko as an index ki tarah treat karenge
        // 2. agar time == ans.size(), toh ek naya arraylist banayenge aur ans me add kar denge
        // 3. har ek noot ko apne respective time(index) ke arraylist me append kar lenge   
    public void burnTree(TreeNode node, int time, List<List<Integer>> ans, TreeNode blockNode) {
        if (node == null || node == blockNode)
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>()); // 1/2
        ans.get(time).add(node.val);    // 3

        burnTree(node.left, time + 1, ans, blockNode);
        burnTree(node.right, time + 1, ans, blockNode);
    }

    public List<List<Integer>> burnBinaryTree(TreeNode root, TreeNode targetNode) {
        List<TreeNode> path = new ArrayList<>();
        rootToNodePath(root, targetNode, path);

        List<List<Integer>> ans = new ArrayList<>();
        TreeNode blockNode = null;
        for (int i = 0; i < path.size(); i++) {
            burnTree(path.get(i), i, ans, blockNode);
            blockNode = path.get(i);
        }

        return ans;
    }

    public int burnBinaryTree_01(TreeNode root, TreeNode targetNode, List<List<Integer>> ans) {
        if (root == null)
            return -1;

        if (root == targetNode) {
            burnTree(root, 0, ans, null);
            return 1;
        }

        int leftTime = burnBinaryTree_01(root.left, targetNode, ans);
        if (leftTime != -1) {
            burnTree(root, leftTime, ans, root.left);
            return leftTime + 1;
        }

        int rightTime = burnBinaryTree_01(root.right, targetNode, ans);
        if (rightTime != -1) {
            burnTree(root, rightTime, ans, root.right);
            return rightTime + 1;
        }

        return -1;
    }

    public List<List<Integer>> burnBinaryTree_01(TreeNode root, TreeNode targetNode) {
        List<List<Integer>> ans = new ArrayList<>();
        burnBinaryTree_01(root, targetNode, ans);
        return ans;
    }

    // fire - water problem
    public void burnTreeWithWater(TreeNode root, int time, List<List<Integer>> ans, TreeNode blockNode,
            HashSet<Integer> WaterNode) {
        if (root == null || root == blockNode || WaterNode.contains(root.val))
            return;
        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        burnTreeWithWater(root.left, time + 1, ans, blockNode, WaterNode);
        burnTreeWithWater(root.right, time + 1, ans, blockNode, WaterNode);
    }

    // -1 : did we get the target node, -2 : fire will not reach that node, t > 0 :
    // fire will reach with time t.
    public int burnTreeWithWater(TreeNode root, TreeNode targetNode, List<List<Integer>> ans,
            HashSet<Integer> WaterNode) {
        if (root == null)
            return -1;

        if (root == targetNode) {
            if (!WaterNode.contains(root.val)) {
                burnTreeWithWater(root, 0, ans, null, WaterNode);
                return 1;
            }
            return -2;

        }

        int ld = burnTreeWithWater(root.left, targetNode, ans, WaterNode);
        if (ld > 0) {
            if (!WaterNode.contains(root.val)) {
                burnTreeWithWater(root, ld, ans, root.left, WaterNode);
                return ld + 1;
            }
            return -2;
        }
        if (ld == -2)
            return -2;

        int rd = burnTreeWithWater(root.right, targetNode, ans, WaterNode);
        if (rd > 0) {
            if (!WaterNode.contains(root.val)) {
                burnTreeWithWater(root, ld, ans, root.right, WaterNode);
                return rd + 1;
            }
            return -2;
        }
        if (rd == -2)
            return -2;

        return -1;
    }

}
