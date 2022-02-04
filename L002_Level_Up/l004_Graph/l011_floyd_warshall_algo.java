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