import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class dsu_question {
    static int[] par, size;

    public static int findPar(int u) {
        if (par[u] == u)
            return u;
        int temp = findPar(par[u]);
        par[u] = temp;
        return temp;
    }

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

    // Lexicographically smallest equivalent string (leetcode )
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
            char ch = (char) findPar((baseStr.charAt(i) - 'a') + 'a');
            sb.append(ch);
        }

        return sb.toString();
    }

    // 990. Satisfiability of Equality Equations
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

    // Lintcode 434 · Number of Islands II
    public List<Integer> numIslands2(int n, int m, int[][] position) {
        // write your code here
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

    // 684. Redundant Connection
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

    // 1168. Optimize Water Distribution in a Village
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

    // https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/
    public static int mrPresident(int[][] edges, int N, int K) {
        par = new int[N + 1];
        for (int i = 0; i <= N; i++)
            par[i] = i;

        int mCost = 0, component = N;
        ArrayList<Integer> cost = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);

            if (p1 != p2) {
                par[p1] = p2;
                mCost += w;
                cost.add(w);
                component--;
            }
        }

        if (component > 1)
            return -1;

        int super_road = 0;
        for (int i = cost.size() - 1; i >= 0; i++) {
            if (mCost <= K)
                break;
            mCost = mCost - cost.get(i) + 1;
            super_road++;
        }

        return mCost <= K ? super_road : -1;
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

