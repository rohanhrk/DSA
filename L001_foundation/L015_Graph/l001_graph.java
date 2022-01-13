import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Arrays;

// ============================================================================================================================================
public class l001_graph {
    public static int N = 5;
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

    // ============================================================================================================================================
    /* basic function */ 
    /* 1. add Edge */
    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    /* 2. display */ 
    public static void display() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i])
                System.out.print(e);

            System.out.println();
        }
    }

    /* 3. find edge */
    public static int findEdge(int u, int v) {
        for (int i = 0; i < graph[u].size(); i++) {
            Edge e = graph[u].get(i);
            if (v == e.v)
                return i;
        }

        return -1;
    }

    /* 4. remove edge */ 
    public static void removeEdge(int u, int v) {
        int idx1 = findEdge(u, v); // u -> v
        graph[u].remove(idx1);

        int idx2 = findEdge(v, u); // v -> u
        graph[v].remove(idx2);
    }

    /* 5. remove vertex */
    public static void removeVtx(int u) {
        for (int i = graph[u].size() - 1; i >= 0; i--) {
            Edge e = graph[u].get(i);
            removeEdge(u, e.v);
        }
    }

    // ============================================================================================================================================
    // Question_1 : has path
    // https://leetcode.com/problems/find-if-path-exists-in-graph/
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

    // ============================================================================================================================================
    // Question_2 : print all path
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

    // ============================================================================================================================================
    // Question_3 : print preorder
    public static void printPreOrder(int src, boolean[] vis, String ans, int wsf) { // wsf -> weight so far

        vis[src] = true;
        System.out.println(src + "->" + ans + src + " @ " + wsf);

        for (Edge e : graph[src]) {
            if (!vis[e.v])
                printPreOrder(e.v, vis, ans + src, wsf + e.w);
        }

        vis[src] = false;
    }

    // ============================================================================================================================================
    // Question_4 : print postorder
    public static void printPostOrder(int src, boolean[] vis, String ans, int wsf) { // wsf -> weight so far

        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.v])
                printPostOrder(e.v, vis, ans + src, wsf + e.w);
        }

        System.out.println(src + "->" + ans + src + " @ " + wsf);
        vis[src] = false;
    }

    // ============================================================================================================================================
    // Question_5 : smallest path in terms of wight
    private static class smallestPath_helper {
        int smallest_wt = (int)1e9;
        String smallest_path = "";
    }
    private static void smallest_path_dfs( ArrayList<Edge>[] graph, int src, int dest, int wsf, String psf, boolean[] vis, smallestPath_helper ans) {
        if(src == dest) {
            if(ans.smallest_wt > wsf) {
                ans.smallest_wt = wsf;
                ans.smallest_path = psf + dest;
            }
            return;
        }

        vis[src] = true;
        for(Edge e : graph[src]) {
            if(!vis[e.v]) {
                smallest_path_dfs(graph, e.v, dest, wsf + e.w, psf + src, vis, ans);
            }
        }
        vis[src] = false;
    }
    public static String smallestPath(int[][] edges, int n) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph[u].add(new Edge(v, w));
        }

        boolean[] vis = new boolean[n];
        smallestPath_helper ans = new smallestPath_helper();
        smallest_path_dfs(graph, 0, n - 1, 0, "", vis, ans);
        
        return ans.smallest_path;
    }

    
    // ============================================================================================================================================
    // Question_6 : largest path in terms of wight
    private static class largestPath_helper {
        int largest_wt = (int)1e9;
        String largest_path = "";
    }
    private static void largest_path_dfs( ArrayList<Edge>[] graph, int src, int dest, int wsf, String psf, boolean[] vis, largestPath_helper ans) {
        if(src == dest) {
            if(ans.largest_wt < wsf) {
                ans.largest_wt = wsf;
                ans.largest_path = psf + dest;
            }
            return;
        }

        vis[src] = true;
        for(Edge e : graph[src]) {
            if(!vis[e.v]) {
                largest_path_dfs(graph, e.v, dest, wsf + e.w, psf + src, vis, ans);
            }
        }
        vis[src] = false;
    }

    public static String largestPath(int[][] edges, int n) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph[u].add(new Edge(v, w));
        }

        boolean[] vis = new boolean[n];
        largestPath_helper ans = new largestPath_helper();
        largest_path_dfs(graph, 0, n - 1, 0, "", vis, ans);
        
        return ans.largest_path;
    }

    // ============================================================================================================================================
    // Question_7 : floor and ceil
    private static class FloorCeilHelper {
        int floor = -(int)1e9;
        String floor_path = "";

        int ceil = (int) 1e9;
        String ceil_path = "";
    }
    private static void floor_ceil_dfs(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis, FloorCeilHelper ans, int given_wt, int wsf, String psf) {
        if(src == dest) {
            if(given_wt > wsf) {
                ans.floor = Math.max(ans.floor, wsf);
                if(ans.floor == wsf) {
                    ans.floor_path = psf + dest;
                } 
            } else if(given_wt < wsf) {
                ans.ceil = Math.min(ans.ceil, wsf);
                if(ans.ceil == wsf) {
                    ans.ceil_path = psf + dest;
                } 
            }

            return;
        }
        vis[src] = true;
        for(Edge e : graph[src]) {
            if(!vis[e.v]) {
                floor_ceil_dfs(graph, e.v, dest, vis, ans, given_wt,wsf + e.v, psf + src);
            }
        }
        vis[src] = false;
    } 
    public static void floor_ceil(int[][] edges, int n, int given_wt) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for(int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph[u].add(new Edge(v, w));
        }

        boolean[] vis = new boolean[n];
        FloorCeilHelper ans = new FloorCeilHelper();
        floor_ceil_dfs(graph, 0, n - 1, vis, ans, given_wt, 0, "");

        System.out.println("floor is " + ans.floor + "@" + "floor path is " + ans.floor_path);
        System.out.println("ceil is " + ans.ceil + "@" + "ceil path is " + ans.ceil_path);
    }

    // ============================================================================================================================================
    // Question_8 : Multisolver - Smallest, Longest, Ceil, Floor, Kthlargest Path
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

            return;
        }

        vis[src] = true; // marked visited
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                allSolution(e.v, dest, vis, p, psf + src, wsf + e.w, givenWeight, k);
            }
        }
        vis[src] = false; // marked unvisited

    }

    // ============================================================================================================================================
    // Question_9 : get connected component
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

    
    // ============================================================================================================================================
    // Question_10 : 200. Number of Islands
    // https://leetcode.com/problems/number-of-islands/
    private void numIslands_dfs(char[][] grid, int sr, int sc, int[][] dir) {
        grid[sr][sc] = '0';
        int n = grid.length, m = grid[0].length;
        for(int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0], c = sc + dir[d][1];
            if(r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == '1') {
                numIslands_dfs(grid, r, c, dir);
            }
        }
    }
    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int num_islands = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == '1') {
                    num_islands++;
                    numIslands_dfs(grid, i, j, dir);
                }
            }
        }
        return num_islands;
    }

    // ============================================================================================================================================
    // Question_11 : get connected component
    public static void dfs(int src, boolean[] vis) {

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs(e.v, vis);
        }
    }

    public static boolean isGraphConnected() {
        int components = 0;
        boolean[] vis = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                components++;
                dfs(i, vis);
            }
        }

        return components == 1;
    }

    // ============================================================================================================================================
    // Question_12 : Hamilton path and cycle
    public static void hamintonian_dfs(int src, int osrc, boolean[] vis, int NoOfEdge, String psf) {
        if (NoOfEdge == N - 1) {
            System.out.print(psf + src);
            int idx = findEdge(src, osrc);
            if (idx != -1)
                System.out.print("*");
            else
                System.out.print(".");
            System.out.println();
            return;
        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                hamintonian_dfs(e.v, osrc, vis, NoOfEdge + 1, psf + src);
        }
        vis[src] = false;
    }

    public static void hamintonianPath() {
        hamintonian_dfs(0, 0, new boolean[N], 0, "");
        ;
    }

    // ============================================================================================================================================
    // Question_13 : Journey to the Moon
    // https://www.hackerrank.com/challenges/journey-to-the-moon/problem
    public static int moonDFS(ArrayList<Integer>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        int size = 0;
        for (Integer e : graph[src]) {
            if (!vis[e])
                size += moonDFS(graph, e, vis);
        }

        return size + 1;
    }

    public static long journeyToMoon(int n, int[][] astronaut) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] a : astronaut) {
            graph[a[0]].add(a[1]);
            graph[a[1]].add(a[0]);
        }

        ArrayList<Integer> sizeArray = new ArrayList<>();
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                sizeArray.add(moonDFS(graph, i, vis));
        }

        long ssf = 0, res = 0;

        for (int e : sizeArray) {
            res += ssf * e;
            ssf += e;
        }

        return res;
    }

    // ============================================================================================================================================
    /* 
        Breadth first Search (BFS)
        case 1 : If cycle is required
    */ 
    public static int BFS_01(int src, boolean[] vis) {
        int level = 0, cycleCount = 0;

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        while (que.size() != 0) {
            int loopCount = que.size();
            // System.out.print(level + "->");
            while (loopCount-- > 0) {
                int rVtx = que.removeFirst();
                if (vis[rVtx]) {
                    cycleCount++;
                    continue;
                }

                // System.out.print(rVtx + " ");
                vis[rVtx] = true;
                for (Edge e : graph[rVtx])
                    if (!vis[e.v])
                        que.addLast(e.v);

            }
            // System.out.println();
            level++;
        }

        return cycleCount;

    }

    /*
        case 2 : If cycle is not required
    */ 
    public static void BFS_02(int src, boolean[] vis) {
        int level = 0;

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        vis[src] = true;

        while (que.size() != 0) {
            int loopCount = que.size();
            System.out.print(level + "->");
            while (loopCount-- > 0) {
                int rVtx = que.removeFirst();

                System.out.print(rVtx + " ");
                for (Edge e : graph[rVtx])
                    if (!vis[e.v]) {
                        que.addLast(e.v);
                        vis[rVtx] = true;
                    }
            }

            System.out.println();
            level++;

        }
    }

    // ============================================================================================================================================
    // Question_14 : Check if a given graph is tree or not
    /*
        An undirected graph is tree if it has following properties. 
        1) There is no cycle. 
        2) The graph is connected.
    */ 
    public static void gcc_DFS(int src, boolean[] vis) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                gcc_DFS(e.v, vis);
        }
    }

    public static boolean isTree() {
        // if gcc = 1 and cycle = 0, then graph is a tree
        boolean[] vis = new boolean[N];

        /* 1. check cycle count */ 
        int cycleCount = BFS_01(0, vis);
        System.out.println("cycle->" + cycleCount);
        if (cycleCount > 0) {
            return false;
        }

        /* 2. find component count */
        int component = 0;
        for (int u = 0; u < N; u++) {
            if (!vis[u]) {
                component++;
                gcc_DFS(u, vis);
            }
        }
        if (component > 1) {
            return false;
        }

        return true;
    }

    // ============================================================================================================================================
    // Question_15 : Check if a given graph is forest or not
    /*
        An undirected graph is forest if it has following properties. 
        1) There is no cycle. 
        2) The graph has more than one component.
    */ 
    public static boolean isForest() {
        // if gcc = 1 and cycle = 0, then graph is a tree
        boolean[] vis = new boolean[N];
        for (int i = 0; i < N; i++)
            vis[i] = false;
        // check graph is cyclic or not
        if (BFS_01(0, vis) > 0)
            return false;

        int component = 0;
        for (int u = 0; u < N; u++) {
            if (!vis[u]) {
                component++;
                gcc_DFS(u, vis);
            }
        }
        System.out.println("comp->" + component);
        if (component == 1)
            return false;

        return true;
    }

    // ============================================================================================================================================
    // Question_16 : BFS
    public static class qPair {
        int src;
        String psf;

        qPair(int src, String psf) {
            this.src = src;
            this.psf = psf;
        }
    }

    public static void BFS(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        int level = 0;
        LinkedList<qPair> que = new LinkedList<>();
        String ans = "";
        que.addLast(new qPair(src, ans + src));
        vis[src] = true;
        while (que.size() != 0) {
            int loopCount = que.size();
            while (loopCount-- > 0) {
                qPair rVtx = que.removeFirst();
                System.out.println(rVtx.src + "@" + rVtx.psf);
                for (Edge e : graph[rVtx.src]) {
                    if (!vis[e.v]) {
                        que.addLast(new qPair(e.v, rVtx.psf + e.v));
                        vis[e.v] = true;
                    }
                }
            }
            // System.out.println();
            level++;
        }
    }

    // ============================================================================================================================================
    // Question_17 : 785. Is Graph Bipartite?
    // https://leetcode.com/problems/is-graph-bipartite/
    private boolean isBipartite_dfs(int[][] graph, int src, int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        
        // id "0" => red color, id "1" => green color
        int color_id = 0;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int rem_vtx = que.removeFirst();
                if(vis[rem_vtx] != -1 && vis[rem_vtx] != color_id) {
                    return false;                    
                }
                vis[rem_vtx] = color_id;
                
                for(int nbr : graph[rem_vtx]) {
                    if(vis[nbr] == -1)
                        que.addLast(nbr);
                }
            }
            
            color_id = (color_id + 1) % 2;
        }
        
        return true;
        
    }
    public boolean isBipartite(int[][] graph) {
        int N = graph.length;
        int[] vis = new int[N];
        Arrays.fill(vis, -1);

        for (int i = 0; i < N; i++) {
            if (vis[i] == -1 && !isBipartite_dfs(graph, i, vis))
                return false;
        }

        return true;
        
    }

    // ============================================================================================================================================
    // Question_18 : Spread Of Infection
    public static int spreadOfInfection(ArrayList<Edge>[] graph, int src, boolean[] vis, int timeLimit) {
        LinkedList<Integer> que = new LinkedList<>();
        que.add(src);
        vis[src] = true;
        int time = 0, infectedPeople = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rVtx = que.removeFirst();
                if (time + 1 <= timeLimit)
                    infectedPeople++;

                for (Edge e : graph[rVtx]) {
                    if (!vis[e.v])
                        que.add(e.v);
                    vis[e.v] = true;
                }
            }

            time++;
        }

        return infectedPeople;
    }

    // ============================================================================================================================================
    /* main function */ 
    public static void main(String[] args) {
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        // addEdge(0, 1, 10);
        // addEdge(0, 3, 40);
        // addEdge(1, 2, 10);
        // addEdge(2, 3, 10);
        // // addEdge(3, 4, 2);
        // addEdge(4, 5, 3);
        // addEdge(4, 6, 8);
        // addEdge(5, 6, 3);
        addEdge(0, 1, 10);
        addEdge(0, 2, 10);
        addEdge(0, 3, 10);
        addEdge(3, 4, 10);

        // printPostOrder(0, new boolean[N], "", 0);
        // pair p = new pair();
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
        // ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        // System.out.print(gcc(comps));

        System.out.println(isTree());
    }

}