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