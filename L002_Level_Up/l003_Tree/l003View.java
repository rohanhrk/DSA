import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class l003View {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ==========================================================================================================================
    // Question_1 : 102. Binary Tree Level Order Traversal
    // https://leetcode.com/problems/binary-tree-level-order-traversal/
    public static void levelOrder(TreeNode root) {

        LinkedList<TreeNode> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        que.addLast(root);
        int level = 0;

        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            System.out.println(level);

            while (size-- > 0) {
                TreeNode rNode = que.removeFirst();
                smallAns.add(rNode.val);

                if (rNode.left != null)
                    que.addLast(rNode.left);
                if (rNode.right != null)
                    que.addLast(rNode.right);
            }
            ans.add(smallAns);
            level++;
        }

        level = 0;
        for (var list : ans)
            System.out.println(level++ + "->" + list);
    }

    // ==========================================================================================================================
    // Question_1.1 : 107. Binary Tree Level Order Traversal II
    // https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if(root == null)
            return ans;
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(que.size() != 0) {
            int size = que.size();
            List<Integer> list = new LinkedList<>();
            
            while(size-- > 0) {
                TreeNode rn = que.removeFirst();
                list.add(rn.val);
                
                if(rn.left != null)
                    que.addLast(rn.left);
                if(rn.right != null)
                    que.addLast(rn.right);
            }
            
            ans.addFirst(list);
        }
         
        return ans;
             
    }

    // ==========================================================================================================================
    // Question_2 : Binary Tree left Side View
    // https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1/
    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        while (que.size() != 0) {
            int size = que.size();
            ans.add(que.getFirst().val);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();
                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }
        }

        return ans;
    }

    // ==========================================================================================================================
    // Question_3 : 199. Binary Tree Right Side View
    // https://leetcode.com/problems/binary-tree-right-side-view/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        while (que.size() != 0) {
            int size = que.size();
            ans.add(que.getLast().val);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();
                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }
        }

        return ans;
    }

    // ==========================================================================================================================
    // Question_4 : Vertical Traversal of Binary Tree
    // https://practice.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1
    // width of binary tree -> shadow techniques
    // vl -> vertical level
    public static void width(TreeNode root, int vl, int[] minMax) {
        if (root == null)
            return;
        minMax[0] = Math.min(vl, minMax[0]); // min
        minMax[1] = Math.max(vl, minMax[1]); // max
        width(root.left, vl - 1, minMax);
        width(root.right, vl + 1, minMax);
    }

    public static int width(TreeNode root) {
        int[] minMax = new int[2];
        width(root, 0, minMax);

        return minMax[1] - minMax[0] + 1;
    }

    public static class vPair {
        TreeNode node = null;
        int vl = 0;
        int level = 0;

        vPair(TreeNode node, int vl) {
            this.node = node;
            this.vl = vl;
        }

        vPair(TreeNode node, int vl, int level) {
            this.node = node;
            this.vl = vl;
            this.level = level;
        }
    }

    // vl -> vertical level
    public static ArrayList<ArrayList<Integer>> verticalOrder(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        width(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < width; i++)
            ans.add(new ArrayList<>());

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rpair = que.removeFirst(); // remove pair
                TreeNode node = rpair.node;
                int vl = rpair.vl;

                ans.get(vl).add(node.val);

                if (node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }

        return ans;
    }

    // ==========================================================================================================================
    // Question_5 : Bottom View of Binary Tree
    // https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1/
    public static ArrayList<Integer> bottomlSideView(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        width(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < width; i++)
            ans.add(null);

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rpair = que.removeFirst(); // remove pair
                TreeNode node = rpair.node;
                int vl = rpair.vl;

                ans.set(vl, node.val);

                if (node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }

        return ans;
    }

    // ==========================================================================================================================
    // Question_6 : Top View of Binary Tree
    // https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1/
    public static ArrayList<Integer> topSideView(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        width(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < width; i++)
            ans.add(null);

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rpair = que.removeFirst(); // remove pair
                TreeNode node = rpair.node;
                int vl = rpair.vl;

                if (ans.get(vl) == null)
                    ans.set(vl, node.val);

                if (node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }

        return ans;
    }

    // ==========================================================================================================================
    // Question_7 : Diagonal Traversal of Binary Tree 
    // https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1
    public static ArrayList<ArrayList<Integer>> diagonalTraversal(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = que.removeFirst();
                while (node != null) {
                    smallAns.add(node.val);

                    if (node.left != null)
                        que.addLast(node.left);

                    node = node.right;
                }
            }

            ans.add(smallAns);
        }

        return ans;
    }

    // ==========================================================================================================================
    // Question_7 : anti diagonal Traversal of Binary Tree 
    public static ArrayList<ArrayList<Integer>> antiDiagonalTraversal(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = que.removeFirst();
                while (node != null) {
                    smallAns.add(node.val);
                    if (node.right != null)
                        que.addLast(node.right);
                    node = node.left;
                }
            }

            ans.add(smallAns);
        }

        return ans;
    }

    // ==========================================================================================================================
    // Question_8 : diagonal top side view of Binary Tree 
    public static ArrayList<Integer> diagonalTopSideView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        ArrayList<Integer> ans = new ArrayList<>();

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                TreeNode node = que.removeFirst();
                ans.add(que.getFirst().val);

                while (node != null) {

                    if (node.left != null)
                        que.addLast(node.left);

                    node = node.right;
                }
            }

        }

        return ans;
    }

    // ==========================================================================================================================
    // Question_8 : diagonal bottom side view of Binary Tree 
    public static  ArrayList<Integer> diagonalOrderBootomView(TreeNode root) {
        int len = 0;
        TreeNode curr = root;
        while(curr != null) {
            len++;
            curr = curr.left;
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < len; i++) ans.add(null);
        
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        int idx = 0;

        while(que.size() != 0) {
            int size = que.size();
            while(size-->0) {
                TreeNode node =  que.removeFirst();
                while(node != null) {
                    ans.set(idx, node.val);
                    if(node.left != null) 
                        que.addLast(node.left);
                    node = node.right;
                }
            }
            idx++;
        }

        return ans;

    } 

    // ==========================================================================================================================
    // Question_9 : vertical sum of binary tree Nodes
    // https://practice.geeksforgeeks.org/problems/vertical-sum/1
    public static ArrayList<Integer> verticalOrderSum(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        width(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < width; i++)
            ans.add(0);

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rpair = que.removeFirst(); // remove pair
                TreeNode node = rpair.node;
                int vl = rpair.vl;

                ans.set(vl, ans.get(vl) + node.val);

                if (node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }

        return ans;
    }

    // ==========================================================================================================================
    // Question_10 : diagonal sum of binary tree
    // https://practice.geeksforgeeks.org/problems/diagonal-sum-in-binary-tree/1/
    public static ArrayList<Integer> diagonalOrderSum(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        ArrayList<Integer> ans = new ArrayList<>();

        while (que.size() != 0) {
            int size = que.size();
            int sum = 0;
            while (size-- > 0) {
                TreeNode node = que.removeFirst();
                while (node != null) {
                    sum += node.val;
                    if (node.left != null)
                        que.addLast(node.left);

                    node = node.right;
                }
            }

            ans.add(sum);

        }

        return ans;
    }

    // ==========================================================================================================================
    // Question_11 : vertical sum of binary tree Nodes using doubly linkedList
    public static class ListNode {
        int data = 0;
        ListNode prev = null;
        ListNode next = null;

        ListNode(int data) {
            this.data = data;
        }
    }

    public static void verticalOrderSumUsingLL(TreeNode root, ListNode node) {
        node.data += root.val;

        if (root.left != null) {
            if (node.prev == null) {
                ListNode nnode = new ListNode(0);
                nnode.next = node;
                node.prev = nnode;
            }
            verticalOrderSumUsingLL(root.left, node.prev);
        }

        if (root.right != null) {
            if (node.next == null) {
                ListNode nnode = new ListNode(0);
                nnode.prev = node;
                node.next = nnode;
            }
            verticalOrderSumUsingLL(root.right, node.next);
        }
    }

    public static void verticalOrderSumUsingLL(TreeNode root) {
        ListNode curr = new ListNode(0);
        verticalOrderSumUsingLL(root, curr);
    }

    // diagonal sum of binary tree Nodes using doubly linkedList
    public static void diagonalOrderSumUsingLL(TreeNode root, ListNode node) {
        node.data += root.val;

        if (root.left != null) {
            if (node.prev == null) {
                ListNode nnode = new ListNode(0);
                nnode.next = node;
                node.prev = nnode;
            }
            diagonalOrderSumUsingLL(root.left, node.prev);
        }

        if (root.right != null) {
            diagonalOrderSumUsingLL(root.right, node);
        }
    }

    public static void diagonalOrderSumUsingLL(TreeNode root) {
        ListNode curr = new ListNode(0);
        verticalOrderSumUsingLL(root, curr);
    }

    // anti-diagonal sum of binary tree Nodes using doubly linkedList
    public static void antiDiagonalOrderSumUsingLL(TreeNode root, ListNode node) {
        node.data += root.val;

        if (root.left != null) {
            antiDiagonalOrderSumUsingLL(root.left, node);
        }

        if (root.right != null) {
            if (node.next == null) {
                ListNode nnode = new ListNode(0);
                nnode.prev = node;
                node.next = nnode;
            }
            antiDiagonalOrderSumUsingLL(root.right, node.next);
        }
    }

    public static void antiDiagonalOrderSumUsingLL(TreeNode root) {
        ListNode curr = new ListNode(0);
        verticalOrderSumUsingLL(root, curr);
    }


    // 987. Vertical Order Traversal of a Binary Tree
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        PriorityQueue<vPair> que = new PriorityQueue<>((a, b) -> {
            if (a.vl != b.vl)
                return a.vl - b.vl;

            return a.node.val - b.node.val;
        });

        PriorityQueue<vPair> chilque = new PriorityQueue<>((a, b) -> {
            if (a.vl != b.vl)
                return a.vl - b.vl;

            return a.node.val - b.node.val;
        });

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.add(new vPair(root, Math.abs(minMax[0])));

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < len; i++)
            ans.add(new ArrayList<>());

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.remove();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if (node.left != null)
                    chilque.add(new vPair(node.left, vl - 1));
                if (node.right != null)
                    chilque.add(new vPair(node.right, vl + 1));
            }

            PriorityQueue<vPair> temp = que;
            que = chilque;
            chilque = temp;
        }

        return ans;
    }

    public List<List<Integer>> verticalTraversal_02(TreeNode root) {
        PriorityQueue<vPair> que = new PriorityQueue<>((a, b) -> {
            if (a.level != b.level) {
                return a.level - b.level;
            } else if (a.vl != b.vl)
                return a.vl - b.vl;

            return a.node.val - b.node.val;
        });

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.add(new vPair(root, Math.abs(minMax[0]), 0));

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < len; i++)
            ans.add(new ArrayList<>());

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.remove();
                int vl = rp.vl;
                int level = rp.level;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if (node.left != null)
                    que.add(new vPair(node.left, vl - 1, level + 1));
                if (node.right != null)
                    que.add(new vPair(node.right, vl + 1, level + 1));
            }
        }

        return ans;
    }
}