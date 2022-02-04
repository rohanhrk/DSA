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