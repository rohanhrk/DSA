import java.util.HashSet;

public class dfs_question {
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

    }

    // 200. Number of Islands
    public void dfs(char[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = '0';
        int n = grid.length, m = grid[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1')
                dfs(grid, r, c, dir);
        }
    }

    public int numIslands(char[][] grid) {
        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

        int n = grid.length;
        int m = grid[0].length;
        int islandCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    islandCount++;
                    dfs(grid, i, j, dir);
                }
            }
        }

        return islandCount;
    }

    // 695. Max Area of Island
    public int dfs_maxArea(int[][] grid, int sr, int sc, int[][] dir) {
        grid[sr][sc] = 0;
        int size = 0, n = grid.length, m = grid[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                size += dfs_maxArea(grid, r, c, dir);
        }

        return size + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
        int n = grid.length;
        int m = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs_maxArea(grid, i, j, dir));
                }
            }
        }

        return maxArea;
    }

    

    // 463. Island Perimeter
    public void dfs_perimeter(int[][] grid, int sr, int sc, int[][] dir, int[] waterCount) {
        int n = grid.length, m = grid[0].length;
        if (grid[sr][sc] == 0 || sr < 0 || sc < 0 || sr == n || sc == m)
            waterCount[0]++;

        grid[sr][sc] = -1;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                dfs_perimeter(grid, r, c, dir, waterCount);
            else if (r < 0 || c < 0 || r == n || c == m || grid[r][c] == 0)
                waterCount[0]++;
        }
    }

    public int islandPerimeter(int[][] grid) {
        int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
        int n = grid.length;
        int m = grid[0].length;

        int[] waterCount = new int[1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    dfs_perimeter(grid, i, j, dir, waterCount);
            }
        }

        return waterCount[0];
    }

    public int islandPerimeter_02(int[][] grid) {
        int[][] dir = { { 0, 1 }, { 1, 0 } };
        int n = grid.length, m = grid[0].length;
        int onesCount = 0, nbrsCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    onesCount++;
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r < n && c < m && grid[r][c] == 1) {
                            nbrsCount++;
                        }
                    }
                }
            }
        }

        return 4 * onesCount - 2 * nbrsCount;
    }

    // 130. Surrounded Regions
    public void dfs_surrounded(char[][] grid, int sr, int sc, int[][] dir) {
        grid[sr][sc] = '#';
        int n = grid.length, m = grid[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O')
                dfs_surrounded(grid, r, c, dir);
        }
    }

    public void solve(char[][] grid) {
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && grid[i][j] == 'O') {
                    dfs_surrounded(grid, i, j, dir);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'O')
                    grid[i][j] = 'X';
                else if (grid[i][j] == '#')
                    grid[i][j] = 'O';
            }
        }

    }

    // lintcode 860 · Number of Distinct Islands
    public void dfs_distictIsland(int[][] grid, int sr, int sc, StringBuilder sb, int[][] dir, char[] dirString) {
        grid[sr][sc] = 0;
        int n = grid.length, m = grid[0].length;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                sb.append(dirString[d]);
                dfs_distictIsland(grid, r, c, sb, dir, dirString);
                sb.append('B');
            }
        }
    }

    public int numberofDistinctIslands(int[][] grid) {
        // write your code here
        int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
        char[] dirString = { 'L', 'U', 'R', 'D' };
        int n = grid.length, m = grid[0].length;

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();

                    dfs_distictIsland(grid, i, j, sb, dir, dirString);

                    String st = sb.toString();
                    if (!set.contains(st))
                        set.add(st);

                }
            }
        }

        return set.size();
    }
}