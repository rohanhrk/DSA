import java.util.ArrayList;
// **********************_DATE:10/07/2021_**********************
public class l003GT {
    public static class Node {
        int data = 0;
        ArrayList<Node> childs = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    // size
    public static int size(Node node) {
        int size = 0;

        for (Node child : node.childs) {
            size += size(child);
        }

        return size + 1;
    }

    // height
    public static int height(Node node) {
        int h = -1;

        for (Node child : node.childs) {
            h = Math.max(h, height(child));
        }

        return h + 1;
    }

    // Maximum
    public static int max(Node node) {
        int max = 0;

        for (Node child : node.childs) {
            max = Math.max(max, max(child));
        }

        return Math.max(max, node.data);
    }

    // Minimum
    public static int min(Node node) {
        int min = 0;

        for (Node child : node.childs) {
            min = Math.min(min, min(child));
        }

        return Math.min(min, node.data);
    }

    // find
    public static boolean find(Node node, int data) {
        // write your code here
        boolean res = node.data == data;
        for (Node child : node.childs) {
            res = res || find(child, data);
        }

        return res;
    }

    // root to node path
    public static boolean rootToNodePath(Node node, int data, ArrayList<Node> ans) {
        // write your code here
        boolean res = node.data == data;
        for (Node child : node.childs) {
            res = res || rootToNodePath(child, data, ans);
        }
        if (res)
            ans.add(node);
        return res;
    }

    // lca in bst
    public static int lca(Node root, int d1, int d2) {
        ArrayList<Node> list1 = new ArrayList<>();
        ArrayList<Node> list2 = new ArrayList<>();

        rootToNodePath(root, d1, list1);
        rootToNodePath(root, d2, list2);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        Node lca = null;
        while (i >= 0 && j >= 0) {
            if (list1.get(i) != list2.get(j))
                break;

            lca = list1.get(i);
            i--;
            j--;
        }

        return lca.data;
    }

    // Distance Between Two Nodes In A Generic Tree
    public static int distanceBetweenNodes(Node root, int d1, int d2) {
        ArrayList<Node> list1 = new ArrayList<>();
        ArrayList<Node> list2 = new ArrayList<>();

        rootToNodePath(root, d1, list1);
        rootToNodePath(root, d2, list2);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        int lcaDistance = 0;
        while (i >= 0 && j >= 0) {
            if (list1.get(i) != list2.get(j))
                break;

            lcaDistance++;
            i--;
            j--;
        }

        int dis = (list1.size() + list2.size() - 2 * (lcaDistance) + 1); // distance in terms of No of Nodes

        // int dis = (list1.size() + list2.size() - 2 * (lcaDistance) + 1 - 1);_distance in terms of No of Edges

        return dis;

    }

    // Linearize a generic tree
    public static Node getTail(Node node) {
        Node curr = node;
        while(curr.childs.size() != 0) {
            curr = curr.childs.get(0);
        } 

        return curr;
    }
    
    public static void linearize(Node node) {
        for(Node child : node.childs) {
            linearize(child);
        }

        for(int i = node.childs.size() - 2; i >= 0 ; i--) {
            Node tail = getTail(node.childs.get(i));
            tail.childs.add(node.childs.get(i+1));
            node.childs.remove(i+1);
        }  
    }

    // linearize -> better approach
    public static Node linearize_btr(Node node) {
        if(node.childs.size() == 0) {
            return node;
        }
        
        int n = node.childs.size();
        Node gTail = linearize_btr(node.childs.get(n-1));//global tail

        for(int i = n - 2; i >= 0 ; i--) {
            Node tail = linearize_btr(node.childs.get(i));
            tail.childs.add(node.childs.get(i+1));
            node.childs.remove(i+1);
        }  

        return gTail;
    }
    
    // ceil and floor
    public static int ceil = (int)1e8;
    public static int floor = -(int)1e8;
    public static void ceil_floor(Node node, int data) {
        if(node.data > data) ceil = Math.min(ceil, node.data);
        if(node.data < data) floor = Math.max(floor, node.data);

        for(Node child : node.childs) {
            ceil_floor(child, data);
        }
    }
}