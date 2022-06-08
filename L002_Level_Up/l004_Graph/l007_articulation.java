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

// ===================================================_Articulation_Bridge_===================================================
// Question_1 : 1192. Critical Connections in a Network
// https://leetcode.com/problems/critical-connections-in-a-network/
private void dfs(ArrayList<Integer>[] graph, int src, int par, List<List<Integer>> ans, boolean[] vis) {
        disc[src] = low[src] = time++;
        vis[src] = true;
        
        for(int nbr : graph[src]) {
            if(nbr != par) {
                if(!vis[nbr]) {
                    dfs(graph, nbr, src, ans, vis);
                    if(disc[src] < low[nbr]) {
                        List<Integer> list = new ArrayList<>();
                        list.add(src);
                        list.add(nbr);
                        ans.add(list);
                    }
                    
                    low[src] = Math.min(low[src], low[nbr]);
                } else {
                    low[src] = Math.min(low[src], disc[nbr]);
                }
            }
        }
        
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(List<Integer> c : connections) {
            int u = c.get(0), v = c.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
        
        disc = new int[n];
        low = new int[n];
        boolean[] vis = new boolean[n]; 
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(!vis[i]) {
                dfs(graph, i, -1, ans, vis);
            }
        }
        
        return ans;
    }