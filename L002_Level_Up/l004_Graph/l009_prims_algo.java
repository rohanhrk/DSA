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