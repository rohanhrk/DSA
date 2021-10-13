import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class algo {
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));

    }

    static int[] par, size;

    // amotized O(1)
    public static int findPar(int u) {
        if (par[u] == u)
            return u;
        int temp = findPar(par[u]);
        par[u] = temp;
        return temp;
    }

    // O(1)
    public static void union(int p1, int p2) {
        if (size[p1] > size[p2]) {
            par[p2] = p1;
            size[p1] += size[p2];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    // {u,v,w}
    // O(E + v)
    public static void unionFind(int[][] edges, ArrayList<Edge>[] graph) {
        int n = edges.length;
        par = new int[n];
        size = new int[n];

        // O(V)
        for (int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        // O(E)
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);

            if (p1 != p2) {
                union(p1, p2);
                addEdge(graph, u, v, w);
            }
        }
    }

    public static void kruskal_Algo(int[][] edges) {
        // O(ElogE) , E->edges.length
        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        int N = edges.length;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        // O(V + E)
        unionFind(edges, graph);
    }

    // ===================================================_Articulation_Point_===================================================
    static int[] disc, low;
    static boolean[] vis, articulationPoint;
    static int time = 0, rootCalls = 0;

    public static void dfs_AP(ArrayList<Edge>[] graph, int src, int par) {
        disc[src] = low[src] = time++;
        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                if (par == -1)
                    rootCalls++;

                dfs_AP(graph, e.v, src);
                if (disc[src] <= low[e.v])
                    articulationPoint[src] = true;

                low[src] = Math.min(low[src], low[e.v]);

            } else if (e.v != par) {
                low[src] = Math.min(low[src], disc[e.v]);
            }
        }
    }

    public static void articulationPoint(ArrayList<Edge>[] graph) {
        int N = graph.length;
        disc = new int[N];
        low = new int[N];
        vis = new boolean[N];
        articulationPoint = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                dfs_AP(graph, i, -1);
            }
        }
    }

    // ================================================_Dijikstra_algorithms_================================================
    public static class pair {
        int par, vtx, weight, wsf;

        pair(int par, int vtx, int weight, int wsf) {
            this.par = par;
            this.vtx = vtx;
            this.weight = weight;
            this.wsf = wsf;
        }
    }

    public static void dijkstra_algo(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            ngraph[i] = new ArrayList<>();

        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });
        pq.add(new pair(-1, src, 0, 0));

        boolean[] vis = new boolean[N]; // visited array
        int[] dis = new int[N]; // distance w.r.t. wsf
        int[] par = new int[N]; // parent

        while (pq.size() != 0) {
            pair p = pq.remove(); // remove

            if (vis[p.vtx])
                continue;

            if (p.par != -1)
                addEdge(ngraph, p.par, p.vtx, p.weight);

            vis[p.vtx] = true; // mark visited
            dis[p.vtx] = p.wsf;
            par[p.vtx] = p.par;

            // call in neighbours of vertex
            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v])
                    pq.add(new pair(p.vtx, e.v, e.w, e.w + p.weight));

            }

        }
    }

    // optimize
    public static void dijkstra_02(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            ngraph[i] = new ArrayList<>();

        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });
        pq.add(new pair(-1, src, 0, 0));

        int[] dis = new int[N]; // distance w.r.t. wsf
        int[] par = new int[N]; // parent
        Arrays.fill(dis, (int) 1e9);
        Arrays.fill(par, -1);

        dis[src] = 0;
        while (pq.size() != 0) {
            pair p = pq.remove();
            if (p.wsf >= dis[p.vtx])
                continue;

            if (p.par != -1)
                addEdge(graph, p.par, p.vtx, p.weight);

            for (Edge e : graph[p.vtx]) {
                if (e.w + p.wsf < dis[p.vtx]) {
                    dis[p.vtx] = e.w + p.wsf;
                    par[p.vtx] = p.par;
                    pq.add(new pair(p.par, e.v, e.w, e.w + p.weight));
                }
            }
        }
    }

    // ============================================_Prim's_Algorithms_============================================
    public static class prims_pair {
        int par, vtx, wt;

        prims_pair(int par, int vtx, int wt) {
            this.par = par;
            this.vtx = vtx;
            this.wt = wt;
        }
    }

    public static void prims_algo(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        boolean[] vis = new boolean[N];
        int[] dis = new int[N];
        int[] par = new int[N];

        ArrayList<Edge>[] new_graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            new_graph[i] = new ArrayList<>();

        PriorityQueue<prims_pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wt - b.wt;
        });
        pq.add(new prims_pair(-1, src, 0));

        vis[src] = true;
        dis[src] = 0;
        par[src] = -1;

        while (pq.size() != 0) {
            prims_pair p = pq.remove();

            if (vis[p.vtx])
                continue;

            if (p.par != -1) {
                addEdge(new_graph, p.par, p.vtx, p.wt);
            }

            vis[p.vtx] = true;
            dis[p.vtx] = p.wt;
            par[p.vtx] = p.par;

            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v])
                    pq.add(new prims_pair(p.vtx, e.v, e.w));
            }
        }

    }

    // optimize
    public static void prims_algo_02(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;

        ArrayList<Edge>[] new_graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            new_graph[i] = new ArrayList<>();

        PriorityQueue<prims_pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wt - b.wt;
        });
        pq.add(new prims_pair(-1, src, 0));

        boolean[] vis = new boolean[N];
        int[] dis = new int[N];
        int[] par = new int[N];
        Arrays.fill(dis, (int) 1e9);
        Arrays.fill(par, -1);

        dis[src] = 0;

        while (pq.size() != 0) {
            prims_pair p = pq.remove();

            if (vis[p.vtx])
                continue;

            if (p.par != -1)
                addEdge(new_graph, p.par, p.vtx, p.wt);

            vis[p.vtx] = true;

            for (Edge e : graph[p.vtx]) {
                if (e.w < dis[e.v]) {
                    pq.add(new prims_pair(p.vtx, e.v, e.w));
                    dis[p.vtx] = e.w;
                    par[p.vtx] = p.par;
                }
            }
        }
    }

    // ==========================================_Bellman_Ford_Algo_==========================================
    public static void Bellman_Ford_Algo(int N, int[][] edges, int src) {
        int[] prev = new int[N];
        Arrays.fill(prev, (int) 1e9);
        prev[src] = 0;

        boolean isNegativeCycle = false;
        for (int i = 1; i <= N; i++) {
            boolean allUpdate = false;
            int[] curr = new int[N];
            for (int j = 0; j < N; j++) {
                curr[j] = prev[j];
            }

            // {u, v, w}
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (prev[u] != (int) 1e9 && prev[u] + w < curr[v]) {
                    if (i == N) {
                        isNegativeCycle = true;
                        break;
                    }

                    curr[v] = prev[u] + w;
                    allUpdate = true;
                }

            }

            if (!allUpdate)
                break;
            prev = curr;
        }

        // return isNegativeCycle;
        System.out.println("Negative cycle : " + isNegativeCycle);

    }

    // =========================================_Floyd_Warshall_=========================================
    public static void floydWarshall(int[][] edges) {
        int N = edges.length;
        int[][] mat = new int[N][N];
        for (int[] d : mat)
            Arrays.fill(d, (int) 1e9);

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            mat[u][v] = w;
        }

        for (int i = 0; i <= N; i++)
            mat[i][i] = 0;

        // IS -> intermadiate stage
        for(int IS = 0; IS <= N; IS++) {
            for(int src = 0; src <= N; src++) {
                for(int dst = 0; dst <= N; dst++) {
                    mat[src][dst] = Math.max(mat[src][dst], mat[src][IS] + mat[IS][dst]);
                }
            }
        }
    }

}