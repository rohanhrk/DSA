import java.util.ArrayList;
import java.util.PriorityQueue;

// **************************_DATE:-22/07/2021_**************************
public class l001Basic {
    public static int N = 7;
    public static ArrayList<Edge>[] graph = new ArrayList[N];

    // class of Edge
    public static class Edge {
        int v = 0;
        int w = 0;

        // CONSTRUCTOR
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return "(" + this.v + "," + this.w + ") ";
        }
    }

    // BASIC FUNCTION
    // 1. *********_ADD EDGE_*********
    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    // 2. *********_DISPLAY_*********
    public static void display() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i])
                System.out.print(e);

            System.out.println();
        }
    }

    // 3. *********_FIND EDGE_*********
    public static int findEdge(int u, int v) {
        for (int i = 0; i < graph[u].size(); i++) {
            Edge e = graph[u].get(i);
            if (v == e.v)
                return i;
        }

        return -1;
    }

    // 4. *********_REMOVE EDGE_*********
    public static void removeEdge(int u, int v) {
        int idx1 = findEdge(u, v); // u -> v
        graph[u].remove(idx1);

        int idx2 = findEdge(v, u); // v -> u
        graph[v].remove(idx2);
    }

    // 5. *********_REMOVE VERTEX_*********
    public static void removeVtx(int u) {
        for (int i = graph[u].size() - 1; i >= 0; i--) {
            Edge e = graph[u].get(i);
            removeEdge(u, e.v);
        }
    }

    // =================================================_QUESTION_=================================================

    // *************_HAS PATH_*************
    public static boolean HasPath(int src, int dest, boolean[] vis) {
        if (src == dest)
            return true;
        boolean res = false;
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                return res || HasPath(e.v, dest, vis);
        }

        return res;
    }

    // **************************_DATE:-23/07/2021_**************************
    // *************_PRINT ALL PATH_*************
    public static int printAllPath(int src, int dest, boolean[] vis, String ans) {
        if (src == dest) {
            System.out.println(ans + dest);
            return 1;
        }
        int count = 0;

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                count += printAllPath(e.v, dest, vis, ans + src);
        }
        vis[src] = false;

        return count;
    }

    // *************_PRE ORDER_*************
    public static void printPreOrder(int src, boolean[] vis, String ans, int wsf) { // wsf -> weight so far

        vis[src] = true;
        System.out.println(src + "->" + ans + src + " @ " + wsf);

        for (Edge e : graph[src]) {
            if (!vis[e.v])
                printPreOrder(e.v, vis, ans + src, wsf + e.w);
        }

        vis[src] = false;
    }

    // *************_POST ORDER_*************
    public static void printPostOrder(int src, boolean[] vis, String ans, int wsf) { // wsf -> weight so far

        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.v])
                printPostOrder(e.v, vis, ans + src, wsf + e.w);
        }

        System.out.println(src + "->" + ans + src + " @ " + wsf);
        vis[src] = false;
    }

    // *************_Multisolver-Smallest,Longest,Ceil,Floor,Kthlargest_Path_*************

    public static class pair { // pair class
        int smallestWeight = (int) 1e8;
        String smallestPath = "";

        int largestWeight = -(int) 1e8;
        String largestPath = "";

        int floor = -(int) 1e8;
        String fPath = "";

        int ceil = (int) 1e8;
        String cPath = "";

    }

    public static class pqPair { // pqPair
        int wsf = 0;
        String psf = "";

        pqPair(int wsf, String psf) {
            this.wsf = wsf;
            this.psf = psf;
        }
    }

    static PriorityQueue<pqPair> pq = new PriorityQueue<>((a, b) -> { // Priority Queue of pqPair
        return a.wsf - b.wsf; // min pq
    });

    // psf -> path so far , wsf -> weight so far
    public static void allSolution(int src, int dest, boolean[] vis, pair p, String psf, int wsf, int givenWeight,
            int k) {
        if (src == dest) {
            if (wsf < p.smallestWeight) { // smallest path
                p.smallestWeight = wsf;
                p.smallestPath = psf + dest;
            }

            if (wsf > p.largestWeight) { // largest path
                p.largestWeight = wsf;
                p.largestPath = psf + dest;
            }

            if (wsf < givenWeight) {// floor
                p.floor = Math.max(p.floor, wsf);
                if (p.floor == wsf)
                    p.fPath = psf + dest;
            }

            if (wsf > givenWeight) { // ceil
                p.ceil = Math.min(p.ceil, wsf);
                if (p.ceil == wsf)
                    p.cPath = psf + dest;
            }

            pq.add(new pqPair(wsf, psf + dest)); // kth largest path
            if (pq.size() > k)
                pq.remove();
        }

        vis[src] = true; // marked visited
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                allSolution(e.v, dest, vis, p, psf + src, wsf + e.w, givenWeight, k);
            }
        }
        vis[src] = false; // marked unvisited

    }

    // *************_Get Conected Component_*************
    public static void dfs(int src, boolean[] visited, ArrayList<Integer> ans) {
        ans.add(src);
        visited[src] = true;
        for (Edge e : graph[src]) {
            if (!visited[e.v])
                dfs(e.v, visited, ans);
        }
    }

    public static int gcc(ArrayList<ArrayList<Integer>> comps) {
        boolean[] visited = new boolean[N];
        int components = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                ArrayList<Integer> ans = new ArrayList<>();
                components++;
                dfs(i, visited, ans);
                comps.add(ans);
            }

        }
        System.out.println(comps);
        return components;
    }

    // **************************_DATE:-24/07/2021_**************************

    public static void main(String[] args) {
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        addEdge(0, 1, 10);
        // addEdge(0, 3, 40);
        // addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        // addEdge(3, 4, 2);
        addEdge(4, 5, 3);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);
        // addEdge(2, 5, 5);

        // printPostOrder(0, new boolean[N], "", 0);
        pair p = new pair();
        // allSolution(0, 6, new boolean[N], p, "", 0, 30, 4);
        // System.out.println("Smallest Path = " + p.smallestPath + "@" +
        // p.smallestWeight);
        // System.out.println("Largest Path = " + p.largestPath + "@" +
        // p.largestWeight);
        // System.out.println("Just Larger Path than " + 30 + " = " + p.cPath + "@" +
        // p.ceil);
        // System.out.println("Just Smaller Path than " + 30 + " = " + p.fPath + "@" +
        // p.floor);
        // System.out.println(4 + "th largest path = " + pq.peek().psf + "@" +
        // pq.peek().wsf);
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        System.out.print(gcc(comps));
    }

}