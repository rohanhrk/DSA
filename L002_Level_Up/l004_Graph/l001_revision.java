import java.util.ArrayList;
import java.util.LinkedList;

public class l001_revision {
	public static class Edge {
		int v = 0, w = 0;

		Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	// ======================================================================================================================================================
	// Basic =>
	public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
		graph[u].add(new Edge(v, w));
		graph[v].add(new Edge(u, w));

	}

	// O(E), E -> No of Edge
	public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
		ArrayList<Edge> list = graph[u];
		for (int i = 0; i < list.size(); i++) {
			Edge e = list.get(i);
			if (e.v == v)
				return i;
		}

		return -1;

	}

	// O(E)
	public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
		int idx = findEdge(graph, u, v);
		graph[u].remove(idx);

		idx = findEdge(graph, v, u);
		graph[v].remove(idx);
	}

	// ======================================================================================================================================================
	// Question_1 : 1971. Find if Path Exists in Graph
	// https://leetcode.com/problems/find-if-path-exists-in-graph/

	// O(E), where E is the Total No of Edge in that particular component
	public static boolean dfs_findPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
		if (src == dest)
			return true;

		vis[src] = true;
		boolean hasPath = false;
		for (Edge e : graph[src])
			if (!vis[e.v])
				hasPath = hasPath || dfs_findPath(graph, e.v, dest, vis);

		return hasPath;
	}

	// ======================================================================================================================================================
	// Question_2 : 797. All Paths From Source to Target
	// https://leetcode.com/problems/all-paths-from-source-to-target/
	private void dfs(int[][] graph, int src, int dest, List<List<Integer>> ans, List<Integer> small_ans) {
        if(src == dest) {
            List<Integer> base = new ArrayList<>(small_ans);
            base.add(dest);
            ans.add(base);
            return;
        }
        
        for(int v : graph[src]) {
                small_ans.add(src);
                dfs(graph, v, dest, ans, small_ans);
                small_ans.remove(small_ans.size() - 1);
        }
    }
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> small_ans = new ArrayList<>();
        int n = graph.length;        
        dfs(graph, 0, n - 1, ans, small_ans);
        return ans;
    }

	// ======================================================================================================================================================
	// Question_3 : Connected Components in an undirected graph
	// https://www.geeksforgeeks.org/connected-components-in-an-undirected-graph/
	public static void dfs_gcc(ArrayList<Edge>[] graph, int src, boolean[] vis) {
		vis[src] = true;
		for (Edge e : graph[src])
			if (!vis[e.v])
				dfs_gcc(graph, e.v, vis);
	}

	public static void getConnectedComponent(ArrayList<Edge>[] graph) {
		int N = graph.length, component = 0;
		boolean[] vis = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (!vis[i]) {
				component++;
				dfs_gcc(graph, i, vis);
			}
		}

		System.out.println(component);
	}

	// ======================================================================================================================================================
	// BREADTH FIRST SEARCH (BFS) WITH CYCLE
	// O(E) -> E : Total Number of Edge
	public static void BFS_WithCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
		LinkedList<Integer> que = new LinkedList<>();
		que.addLast(src);
		int level = 0;
		boolean isCycle = false;

		while (que.size() != 0) {
			int size = que.size();
			while (size-- > 0) {
				int rv = que.removeFirst();

				if (vis[rv]) {
					isCycle = true;
					continue;
				}

				vis[rv] = true;
				for (Edge e : graph[rv]) {
					if (!vis[e.v])
						que.addLast(e.v);
				}
			}

			level++;
		}
	}

	// O(V) -> V : Total Number Of Vertecs
	public static void BFS_WithOutCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
		LinkedList<Integer> que = new LinkedList<>();
		int level = 0;
		boolean isCycle = false;

		que.addLast(src);
		vis[src] = true;
		while (que.size() != 0) {
			int size = que.size();
			while (size-- > 0) {
				int rv = que.removeFirst();
				for (Edge e : graph[rv]) {
					if (!vis[e.v]) {
						vis[e.v] = true;
						que.addLast(e.v);
					}
				}
			}

			level++;
		}
	}

	// O(2E)
	public static void display(ArrayList<Edge>[] graph) {
		int N = graph.length;
		for (int i = 0; i < N; i++) {
			System.out.print(i + "->");
			for (Edge e : graph[i])
				System.out.print("(" + e.v + "," + e.w + ") ");
			System.out.println();
		}
	}

	public static void constructGraph() {
		int N = 8;
		ArrayList<Edge>[] graph = new ArrayList[N];
		for (int i = 0; i < N; i++)
			graph[i] = new ArrayList<>();

		addEdge(graph, 0, 1, 10);
		addEdge(graph, 0, 3, 10);
		addEdge(graph, 1, 2, 10);
		addEdge(graph, 2, 3, 40);
		addEdge(graph, 3, 4, 2);
		addEdge(graph, 4, 5, 2);
		addEdge(graph, 4, 6, 8);
		addEdge(graph, 5, 6, 3);

		display(graph);
	}

	public static void main(String[] args) {
		constructGraph();

	}
}