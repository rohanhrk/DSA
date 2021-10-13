import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.PriorityQueue;

public class bfs_question {
	public static class Edge {
		int v = 0, w = 0;

		Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	// 0 -> empty cell, 1 -> fresh orange, 2 -> rotten orange
	public class pair {
		int row = 0;
		int col = 0;

		pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public int orangesRotting(int[][] grid) {
		int n = grid.length, m = grid[0].length;
		LinkedList<pair> que = new LinkedList<>();
		int freshCount = 0, time = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 2) {
					que.addLast(new pair(i, j));
					grid[i][j] = 2; // mark as visited
				} else if (grid[i][j] == 1) {
					freshCount++;
				}
			}
		}

		if (freshCount == 0)
			return 0;
		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		while (que.size() != 0) {
			int size = que.size();
			while (size-- > 0) {
				pair rem = que.removeFirst();
				for (int d = 0; d < dir.length; d++) {
					int r = rem.row + dir[d][0];
					int c = rem.col + dir[d][1];

					if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
						if (--freshCount == 0)
							return time + 1;
						que.addLast(new pair(r, c));
						grid[r][c] = 2;
					}
				}
			}

			time++;
		}

		return -1;

	}

	public int orangesRotting_02(int[][] grid) {
		int n = grid.length, m = grid[0].length;
		LinkedList<Integer> que = new LinkedList<>();
		int freshCount = 0, time = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 2) {
					que.addLast(i * m + j);
					grid[i][j] = 2; // mark as visited
				} else if (grid[i][j] == 1) {
					freshCount++;
				}
			}
		}

		if (freshCount == 0)
			return 0;

		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		while (que.size() != 0) {
			int size = que.size();
			while (size-- > 0) {
				int rIdx = que.removeFirst();
				int sr = rIdx / m, sc = rIdx % m;
				for (int d = 0; d < dir.length; d++) {
					int r = sr + dir[d][0];
					int c = sc + dir[d][1];

					if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
						if (--freshCount == 0)
							return time + 1;
						que.addLast(r * m + c);
						grid[r][c] = 2;
					}
				}
			}

			time++;
		}

		return -1;

	}

	// 1091. Shortest Path in Binary Matrix
	public int bfs_shortestPath(int[][] grid, int sr, int sc, int dr, int dc, int[][] dir) {
		LinkedList<Integer> queue = new LinkedList<>();
		int n = grid.length, m = grid[0].length;
		queue.addLast(0);
		int ShortestPath = 1;

		grid[sr][sc] = 1;
		while (queue.size() != 0) {
			int size = queue.size();
			while (size-- > 0) {
				int idx = queue.removeFirst();
				int x = idx / m, y = idx % m;
				if (x == dr && y == dc)
					return ShortestPath;

				for (int d = 0; d < dir.length; d++) {
					int r = x + dir[d][0];
					int c = y + dir[d][1];
					if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
						grid[r][c] = 1;
						queue.addLast(r * m + c);
					}
				}
			}
			ShortestPath++;
		}

		return -1;
	}

	public int shortestPathBinaryMatrix(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0)
			return 0;
		int n = grid.length, m = grid[0].length;
		if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
			return -1;

		int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
		return bfs_shortestPath(grid, 0, 0, n - 1, m - 1, dir);
	}

	// 542. 01 Matrix
	public int[][] updateMatrix_01(int[][] mat) {
		LinkedList<Integer> que = new LinkedList<>();
		int n = mat.length, m = mat[0].length;
		boolean[][] vis = new boolean[n][m]; // extra space n * m
		// O(n*m)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (mat[i][j] == 0) {
					vis[i][j] = true;
					que.addLast(i * m + j);

				}
			}
		}

		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		// BFS - O(n*m)
		while (que.size() != 0) {
			int size = que.size();
			while (size-- > 0) {
				int idx = que.removeFirst();
				int sr = idx / m, sc = idx % m;

				for (int d = 0; d < dir.length; d++) {
					int r = sr + dir[d][0];
					int c = sc + dir[d][1];
					if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
						vis[r][c] = true;
						mat[r][c] = mat[sr][sc] + 1;
						que.addLast(r * m + c);
					}
				}
			}
		}

		return mat;
	}

	public int[][] updateMatrix(int[][] mat) {
		LinkedList<Integer> que = new LinkedList<>();
		int n = mat.length, m = mat[0].length;

		// O(n*m)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (mat[i][j] == 0) {
					que.addLast(i * m + j);
				} else {
					mat[i][j] = -1;
				}
			}
		}

		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		// BFS - O(n*m)
		while (que.size() != 0) {
			int size = que.size();
			while (size-- > 0) {
				int idx = que.removeFirst();
				int sr = idx / m, sc = idx % m;

				for (int d = 0; d < dir.length; d++) {
					int r = sr + dir[d][0];
					int c = sc + dir[d][1];
					if (r >= 0 && c >= 0 && r < n && c < m && mat[r][c] < 0) {
						mat[r][c] = mat[sr][sc] + 1;
						que.addLast(r * m + c);
					}
				}
			}
		}

		return mat;
	}

	// Lintcode 663 · Walls and Gates
	public void wallsAndGates(int[][] rooms) {
		LinkedList<Integer> queue = new LinkedList<>();
		int n = rooms.length, m = rooms[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (rooms[i][j] == 0)
					queue.addLast(i * m + j);
			}
		}

		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		int INF = 2147483647;
		while (queue.size() != 0) {
			int size = queue.size();
			while (size-- > 0) {
				int idx = queue.removeFirst();
				int sr = idx / m, sc = idx % m;
				for (int d = 0; d < dir.length; d++) {
					int r = sr + dir[d][0];
					int c = sc + dir[d][1];
					if (r >= 0 && c >= 0 && r < n && c < m && rooms[r][c] == INF) {
						rooms[r][c] = rooms[sr][sc] + 1;
						queue.addLast(r * m + c);
					}
				}
			}
		}
	}

	//
	public int numBusesToDestination(int[][] routes, int source, int target) {
		if (source == target)
			return 0;

		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		for (int busNo = 0; busNo < routes.length; busNo++) {
			for (int busStand : routes[busNo]) {
				map.putIfAbsent(busStand, new ArrayList<>());
				map.get(busStand).add(busNo);
			}
		}

		HashSet<Integer> busStandVisited = new HashSet<>();
		boolean[] busVisited = new boolean[routes.length];

		LinkedList<Integer> que = new LinkedList<>();
		que.add(source);
		busStandVisited.add(source);

		int interchange = 0;
		while (que.size() != 0) {
			int size = que.size();
			while (size-- > 0) {
				int rem = que.removeFirst();
				for (int bus : map.get(rem)) {
					if (!busVisited[bus]) {
						busVisited[bus] = true;
						for (int busStand : routes[bus]) {
							if (!busStandVisited.contains(busStand)) {
								if (busStand == target)
									return interchange + 1;

								que.addLast(busStand);
								busStandVisited.add(busStand);
							}
						}
					}
				}

			}
			interchange++;
		}

		return -1;
	}

	// 1376. Time Needed to Inform All Employees
	public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
		// {u,v,w}
		ArrayList<int[]>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++)
			graph[i] = new ArrayList<>();

		for (int i = 0; i < manager.length; i++) {
			int u = manager[i], v = i;
			if (u != -1) {
				int w = informTime[u];
				graph[u].add(new int[] { v, w });
			}
		}

		// {vtx, wsf}
		LinkedList<int[]> que = new LinkedList<>();
		que.addLast(new int[] { headID, 0 });

		int totalMinutes = 0;
		while (que.size() != 0) {
			int size = que.size();
			while (size-- > 0) {
				int[] rp = que.removeFirst(); // remove pair
				int vtx = rp[0], wsf = rp[1];

				for (int[] e : graph[vtx]) {
					int v = e[0], w = e[1];
					que.addLast(new int[] { v, wsf + w });
					totalMinutes = Math.max(totalMinutes, wsf + w);
				}
			}
		}

		return totalMinutes;

	}

	// Lintcode 787 : The Maze
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		// write your code here
		int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
		boolean[][] vis = new boolean[n][m];
		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

		LinkedList<Integer> que = new LinkedList<>();
		que.addLast(sr * m + sc);
		vis[sr][sc] = true;

		while (que.size() != 0) {
			int size = que.size();
			while (size-- > 0) {
				int idx = que.removeFirst();
				int rem_row = idx / m, rem_col = idx % m;

				for (int[] d : dir) {
					int curr_row = rem_row, curr_col = rem_col;

					while (curr_row >= 0 && curr_col >= 0 && curr_row < n && curr_col < m
							&& maze[curr_row][curr_col] == 0) {
						curr_row += d[0];
						curr_col += d[1];
					}

					curr_row -= d[0];
					curr_col -= d[1];

					if (vis[curr_row][curr_col])
						continue;

					vis[curr_row][curr_col] = true;
					que.addLast(curr_row * m + curr_col);

					if (curr_row == er && curr_col == ec)
						return true;
				}
			}
		}

		return false;
	}

	// 788 · The Maze II
	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		class pair {
			int r, c, dis;

			pair(int r, int c, int dis) {
				this.r = r;
				this.c = c;
				this.dis = dis;
			}
		}

		int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

		int[][] distance = new int[n][m];
		for (int[] d : distance)
			Arrays.fill(d, (int) 1e9);

		PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
			return a.dis - b.dis;
		});
		pq.add(new pair(sr, sc, 0));
		distance[sr][sc] = 0;

		while (pq.size() != 0) {
			pair p = pq.remove();
			int r = p.r, c = p.c, dis = p.dis;

			for (int[] d : dir) {
				while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
					r += d[0];
					c += d[1];
					dis++;
				}

				r -= d[0];
				c -= d[1];
				dis--;

				if (dis >= distance[r][c])
					continue;

				pq.add(new pair(r, c, dis));
				distance[r][c] = dis;

				// if(r == er && c == ec) 
				// 	return dis;
			}
		}
		return distace[er][ec] != (int)1e8 ? distace[er][ec] : -1; 
	}
}
