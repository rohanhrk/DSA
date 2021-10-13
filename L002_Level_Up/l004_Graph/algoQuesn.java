import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.List;

public class algoQuesn {
    static int[] disc, low;
    static boolean[] vis, articulationPoint;
    static int time = 0, rootCalls = 0;

    // 1192. Critical Connections in a Network
    // Articulation Bridge/edge
    public void dfs_cc(ArrayList<Integer>[] graph, int src, int par, List<List<Integer>> ans) {
        disc[src] = low[src] = time++;
        vis[src] = true;

        for (Integer v : graph[src]) {
            if (!vis[v]) {
                dfs_cc(graph, v, src, ans);

                if (disc[src] < low[v]) {
                    List<Integer> edge = new ArrayList<>();
                    edge.add(src);
                    edge.add(v);
                    ans.add(edge);
                }

                low[src] = Math.min(low[src], low[v]);

            } else if (v != par) {
                low[src] = Math.min(low[src], disc[v]);
            }
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (List<Integer> ar : connections) {
            int u = ar.get(0), v = ar.get(1);
            graph[u].add(v);
            graph[v].add(u);

        }

        disc = new int[n];
        low = new int[n];
        vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs_cc(graph, i, -1, ans);
            }
        }

        return ans;
    }

    // 743. Network Delay Time
    public int networkDelayTime(int[][] times, int n, int k) {
        // {v,w}
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : times) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new int[] { v, w });
        }

        int[] dis = new int[n + 1];
        Arrays.fill(dis, (int) 1e9);
        // {vtx, wsf}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        pq.add(new int[] { k, 0 });
        dis[k] = 0;

        while (pq.size() != 0) {
            int[] rp = pq.remove(); // remove pair -> {vtx, wsf}
            int vtx = rp[0], wsf = rp[1];

            if (wsf > dis[vtx])
                continue;

            for (int[] e : graph[vtx]) {
                int v = e[0], w = e[1];
                if (wsf + w < dis[v]) {
                    dis[v] = wsf + w;
                    pq.add(new int[] { v, wsf + w });
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dis[i] == (int) 1e9)
                return -1;

            max = Math.max(max, dis[i]);
        }

        return max;
    }

    // 787. Cheapest Flights Within K Stops
    public int findCheapestPrice(int n, int[][] flights, int src, int dest, int k) {
        int[] prev = new int[n];
        Arrays.fill(prev, (int) 1e9);
        prev[src] = 0;

        // stops = k , In terms of Edge = k + 1
        for (int i = 1; i <= k + 1; i++) {
            boolean allUpdate = false;
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                curr[j] = prev[j];
            }

            // {u, v, w}
            for (int[] e : flights) {
                int u = e[0], v = e[1], w = e[2];
                if (prev[u] + w < curr[v]) {
                    curr[v] = prev[u] + w;
                    allUpdate = true;
                }

            }

            if (!allUpdate)
                break;
            prev = curr;
        }

        return prev[dest] != (int) 1e9 ? prev[dest] : -1;
    }


    // 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] mat = new int[n][n];
        for(int[] m : mat) 
            Arrays.fill(m, (int)1e9);
        
        for(int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            mat[u][v] = w;
            mat[v][u] = w;
        }
        
        for(int i = 0; i < n; i++) 
            mat[i][i] = 0;
        
        // src -> intermadiate -> dest        
        for(int intermadiate = 0; intermadiate < n; intermadiate++) 
            for(int src = 0; src < n; src++) 
                for(int dest = 0; dest < n ; dest++) 
                    mat[src][dest] = Math.min(mat[src][dest] , mat[src][intermadiate] + mat[intermadiate][dest]);
                
        
        int smallestNoOfCity = (int)1e9;
        int src_city = -1; // source city
        for( int src = 0 ; src < n ; src++ ){
            int reachCity = 0; // reachable city
            for( int dest = 0 ; dest < n ; dest++ ){
               if( mat[src][dest] <= distanceThreshold){
                   reachCity++;
               }
            }
            
            if( reachCity <= smallestNoOfCity ){ // ( "=" bcz we want geeater no. ) 
                smallestNoOfCity = reachCity;
                src_city = src;
            }
        }
        return src_city;
    }
}