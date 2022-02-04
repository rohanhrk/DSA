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