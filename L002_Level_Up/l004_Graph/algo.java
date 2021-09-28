import java.util.ArrayList;
import java.util.Arrays;

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
}