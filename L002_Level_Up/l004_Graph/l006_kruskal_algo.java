import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class l006_kruskal_algo {
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

    // ============================================================================================================================================
    // Concept : Kruskal's Algo => Basic
    static int[] par, size;

    // amotized O(1) == O(4)
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

    // ============================================================================================================================================
    // Question_1 : 1168. Optimize Water Distribution in a Village
    // https://www.codingninjas.com/codestudio/problems/water-supply-in-a-village_1380956?leftPanelTab=0
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> allPipes = new ArrayList<>();
        for (int[] a : pipes)
            allPipes.add(a);
        for (int i = 0; i < wells.length; i++)
            allPipes.add(new int[] { 0, i + 1, wells[i] });

        Collections.sort(allPipes, (a, b) -> {
            return a[2] - b[2];
        });

        par = new int[n + 1];
        size = new int[n + 1];
        int cost = 0;
        for (int i = 0; i <= n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int[] e : allPipes) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);
            if (p1 != p2) {
                if (size[p1] > size[p2]) {
                    par[p2] = p1;
                    size[p1] += size[p2];
                } else {
                    par[p1] = p2;
                    size[p2] += size[p1];
                }

                cost += w;
            }
        }

        return cost;
    }

    // ============================================================================================================================================
    // Question_2 : Mr. President
    // https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/
    private static int[] par, size; 

    private static int findPar(int u) {
        if(par[u] == u)
            return u;

        int temp = findPar(par[u]);
        par[u] = temp;
        return temp;
    }
    private static void union(int p1, int p2) {
        if(size[p1] > size[p2]) {
            par[p2] = p1;
            size[p1] += size[p2];
        } else {
            par[p1] = p2;
            size[p2] += size[p1];
        }
    }

    public static int mrPresident(int[][] roads, int n, int k) {
        /*
            step 1 : first we have to convert this graph to a MST(minimum spanning tree)
            => By processing step 1, we maintain total cost maintanace (m_count) and also, with this we store cost in arraylist (r_list);
        */ 
        
        par = new int[n + 1], size = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        Arrays.sort(roads, (a, b) -> {
            return a[2] - b[2];	
        });
        
        int component = n;
        
        int m_cost = 0; // total maintainance cost
        ArrayList<Integer> r_list = new ArrayList<>(); // road's list
        for(int[] road : roads) {
            int u = road[0], v = road[1], w = road[2];

            int p1 = findPar(u), p2 = findPar(v);

            if(p1 != p2) {
                union(p1, p2);
                
                m_cost += w;
                r_list.add(w);
                
                component--;
            }
        }

        if(component > 1) return -1;
        
        /*
            step 2 : saare desired weight ko stored karne ke baad agar total maintainance cost (m_cost),
            given cost (k) se jyada he toh sabse bara cost ko minus kar denge m_cost se
        */
        int super_road = 0;
        for(int i = r_list.size() - 1; i >= 0; i--) {
            if(m_cost <= k)
                break;

            int rem_cost = r_list.remove(i); // remove cost from r_list
            m_cost = m_cost - rem_cost + 1; // +1 for maintanance cost of transforming standard roads into super road
            super_road++;
        }

        return m_cost <= k ? super_road : -1;
    }
}