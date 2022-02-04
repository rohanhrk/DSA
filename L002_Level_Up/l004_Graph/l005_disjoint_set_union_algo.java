import java.util.ArrayList;

public class l005_disjoint_set_union_algo {
   
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
    // Intro to disjoin set Union
    static int[] par, size;
    public static int findPar(int u) {
        if (par[u] == u)
            return u;

        int temp = findPar(par[u]);
        par[u] = temp; // path compression optimization
        return temp;

        // return par[u] == u : u ? par[u] = find(par[u]);
    }

    public static void union(int p1, int p2) { // union by size optimization
        if(size[p1] < size[p2]){
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }
    
    public static void uionFind(int[][] edge) {
        int N = edge.length;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        par = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int[] e : edge) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                union(p1, p2);
                addEdge(graph, u, v, w);
            }
        }

    }


    // ============================================================================================================================================
    // Question_1 : 695. Max Area of Island
    // https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        par = new int[n * m];
        size = new int[n * m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }

                par[i * m + j] = i * m + j;
                size[i * m + j] = 1;
            }
        }

        int[][] dir = { { 0, 1 }, { 1, 0 } };
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int p1 = findPar(i * m + j);
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                            int p2 = findPar(r * m + c);
                            if (p1 != p2) {
                                count--;
                                par[p2] = p1;
                                size[p1] += size[p2];
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, size[p1]);
                }
            }
        }

        return maxArea;
    }

    /* mathod 2 => using pair class */ 
    private class Pair {
        int row, col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    private Pair[][] par;
    private int[][] size;
    private Pair findParent(Pair u) {
        if(par[u.row][u.col] == u)
            return u;
        
        Pair temp = findParent(par[u.row][u.col]);
        par[u.row][u.col] = temp;
        return temp;
    }
    
    
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        par = new Pair[n][m];
        size = new int[n][m];
        
        // step 1 => Initially make all vtx's parent is itself and make size of themself is 1
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                par[r][c] = new Pair(r, c);
                size[r][c] = 1;
            }
        }
        
        int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int max_area = 0;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(grid[r][c] == 1) {
                    Pair p1 = findParent(new Pair(r, c));
                    for(int d = 0; d < dir.length; d++) {
                        int row = r + dir[d][0], col = c + dir[d][1];
                        if(row >= 0 && row < n && col >= 0 && col < m && grid[row][col] == 1) {
                            Pair p2 = findParent(new Pair(row, col));
                            if (p1 != p2) {
                                par[p2.row][p2.col] = p1;
                                size[p1.row][p1.col] += size[p2.row][p2.col];
                            }
                        }
                    }
                    max_area = Math.max(max_area, size[p1.row][p1.col]);
                }
            }
        }
        
        return max_area;
    }

    // ============================================================================================================================================
    // Question_2 : 1061. Lexicographically Smallest Equivalent String
    // https://leetcode.ca/2018-10-26-1061-Lexicographically-Smallest-Equivalent-String/#level
    public static String smallestString(String s1, String s2, String baseStr) {
        // Write your code here.
        par = new int[26];
        for (int i = 0; i < 26; i++)
            par[i] = i;

        for (int i = 0; i < s1.length(); i++) {
            int p1 = findPar(s1.charAt(i) - 'a');
            int p2 = findPar(s2.charAt(i) - 'a');

            if (p1 < p2)
                par[p2] = p1;
            else
                par[p1] = p2;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baseStr.length(); i++) {
            char ch = (char) (findPar(baseStr.charAt(i) - 'a') + 'a');
            sb.append(ch);
        }

        return sb.toString();
    }

    // ============================================================================================================================================
    // Question_3 : 990. Satisfiability of Equality Equations
    // https://leetcode.com/problems/satisfiability-of-equality-equations/
    public boolean equationsPossible(String[] equations) {
        par = new int[26];
        for (int i = 0; i < 26; i++)
            par[i] = i;

        for (String s : equations) {
            char ch1 = s.charAt(0), ch2 = s.charAt(1), ch3 = s.charAt(3);
            int p1 = findPar(ch1), p2 = findPar(ch3);
            if (ch2 == '=' && p1 != p2)
                par[p1] = p2;
        }

        for (String s : equations) {
            char ch1 = s.charAt(0), ch2 = s.charAt(1), ch3 = s.charAt(3);
            int p1 = findPar(ch1), p2 = findPar(ch3);
            if (ch2 == '!' && p1 == p2)
                return false;
        }

        return true;
    }

    // ============================================================================================================================================
    // Question_4 : Lintcode 434 · Number of Islands II
    // https://www.lintcode.com/problem/434/
    public List<Integer> numIslands2(int n, int m, int[][] position) {
        List<Integer> ans = new ArrayList<>();
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        par = new int[n * m];
        Arrays.fill(par, -1);

        int count = 0;
        for (int[] p : position) { // O(length of position)
            int i = p[0], j = p[1];
            if (par[i * m + j] == -1) {
                count++;
                par[i * m + j] = i * m + j;

                int p1 = findPar(i * m + j);
                for (int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && par[r * m + c] != -1) {
                        int p2 = findPar(r * m + c);
                        if (p1 != p2) {
                            count--;
                            par[p2] = p1;
                        }
                    }
                }
            }
            ans.add(count);
        }

        return ans;
    }

    // ============================================================================================================================================
    // Question_5 : 684. Redundant Connection
    // https://leetcode.com/problems/redundant-connection/
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] ans = new int[2];
        par = new int[n + 1];
        size = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 == p2) {
                ans[0] = u;
                ans[1] = v;
            } else {
                if (size[p1] < size[p2]) {
                    par[p1] = p2;
                    size[p2] += size[p1];
                } else {
                    par[p2] = p1;
                    size[p1] += size[p2];
                }
            }

        }

        return ans;
    }

    // ============================================================================================================================================
    // Question_6 : 839. Similar String Groups
    // https://leetcode.com/problems/similar-string-groups/
    private int[] par;
    private boolean isSimilar(String s1, String s2) {
        if(s1.length() != s2.length())
            return false;
        
        int count = 0;
        boolean isSimilar = true;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i) && ++count > 2) {
                isSimilar = false;
                break;
            }
        }
        
        return isSimilar;
    }
    
    private int findPar(int u) {
        if(par[u] == u)
            return u;
        
        int temp = findPar(par[u]);
        par[u] = temp;
        return temp;
    }
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        par = new int[n];
        
        // Step 1 => initially make all of string in a individual group         
        for(int i = 0; i < n; i++) 
            par[i] = i;
        
        // Hence, initially No of group = strs,length
        int group = strs.length; 
        
        /*
            step 2 => Now Check similarity between two string , if found similar
            place both of them in a single group and do minus group count by 1
        */        
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(isSimilar(strs[i], strs[j])) {
                    int p1 = findPar(i);
                    int p2 = findPar(j);
                    
                    // when both parent are same nothng to do
                    if(p1 != p2) {
                        group--;
                        par[p2] = p1;
                    } 
                }
            }
        }
        
        return group;
    }
    

    

    // 959. Regions Cut By Slashes
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int m = n + 1;
        par = new int[m*m];
        int region = 1;
        
        for(int i = 0; i < m * m; i++) {
            par[i] = i;
            
            int r = i / m, c = i % m;
            if(r == 0 || c == 0 || r == m - 1 || c == m - 1) {
                par[i] = 0;
            }
        }
        
        for(int i = 0; i < grid.length; i++) {
            String str = grid[i];
            for(int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                int p1 = 0, p2 = 0;
                if(ch == '/') {
                    p1 = findPar(i * m + (j + 1));
                    p2 = findPar((i + 1) * m + j);
                } else if(ch == '\\') {
                    p1 = findPar(i * m + j);
                    p2 = findPar((i + 1) * m + (j + 1));
                } else {
                    continue;
                }
                
                if(p1 == p2) {
                    region++;
                } else {
                    par[p1] = p2;
                }
            }
        }
        
        return region;
        
        
    }

    // 924. Minimize Malware Spread
    int[] country; 
    int[] poc; // population of country
    public int findCountry(int u) {
        if(country[u] == u) 
            return u;
        int temp = findCountry(country[u]);
        country[u] = temp;
        return temp;
    }
    
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        country = new int[n];
        poc = new int[n];
        
        for(int i = 0; i < n; i++) {
            country[i] = i;
            poc[i] = 1;
        }
        
        // 1. find population of counttry using DSU where we store population in a poc array         
        for(int i = 0; i < n; i++) {
            int p1 = findCountry(i);
            for(int j = 0; j < n; j++) {
                if(i != j) {
                    if(graph[i][j] == 1) {
                        int p2 = findCountry(j);
                        if(p1 != p2) {
                            country[p2] = p1;
                            poc[p1] += poc[p2];
                        }
                    }
                }
            }
        }
        Arrays.sort(initial);
        
        // 2. find out Total Infected person in a country         
        int[] ipc = new int[n]; // Infected person in a country
        for(int ip : initial) {
            int c = findCountry(ip);
            ipc[c]++;
        }
        
        // 3. figure out which country having infected person only one and 
        // having maximum population
        int maxPopulatedCountry = 0;
        int c = initial[0];
        
        for(int ip : initial) {
            int p = findCountry(ip); 
            if(ipc[p] == 1 && poc[p] > maxPopulatedCountry) {
                maxPopulatedCountry = poc[p];
                c = ip;
            }
        }
        
        return c;
    }
}