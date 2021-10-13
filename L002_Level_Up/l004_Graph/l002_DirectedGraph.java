import java.util.LinkedList;
import java.util.ArrayList;

public class l002_DirectedGraph {
	public static class Edge {
		int v = 0, w = 0;

		Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
		graph[u].add(new Edge(v, w));
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

	// TOPOLOGICAL ORDER======================
	public static void dfs_topo(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> ans) {
		vis[src] = true;
		
		for(Edge e : graph[src]) {
			if(!vis[e.v])
			 	dfs_topo(graph, e.v, vis, ans);
		}

		ans.add(src);
	}

	public static void topologicalOrder(ArrayList<Edge>[] graph) {
		ArrayList<Integer> ans = new ArrayList<>();
		int N = graph.length;
		boolean[] vis = new boolean[N];

		for(int i = 0; i < N; i++) {
			if(!vis[i]) 
				dfs_topo(graph, i, vis, ans);
		}
	}

	// KAHN's ALGO -> BFS
	public static ArrayList<Integer> bfs_kahnsAlgo(ArrayList<Edge>[] graph) {
		int N = graph.length;
		LinkedList<Integer> que = new LinkedList<>();
		ArrayList<Integer> ans = new ArrayList<>();
		int[] indegree = new int[N];

		// O(E)
		for(int i = 0; i < N; i++) {
			for(Edge e : graph[i]) {
				indegree[e.v]++;
			}
		}

		// O(V)
		for(int i = 0; i < N; i++) {
			if(indegree[i] == 0) 
				que.addLast(i);			
		} 

		// O(E + V)
		while(que.size() != 0) {
			int size = que.size();
			while(size-->0) {
				int rVtx = que.removeFirst();
				ans.add(rVtx);

				for(Edge e : graph[rVtx]) {
					if(--indegree[e.v] == 0) 
						que.addLast(e.v);
				}
			}
		}

		if(ans.size() != N) ans.clear();

		return ans;
	}

	//	207. Course Schedule 
    public boolean canFinish(int N, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] e : prerequisites) {
            int u = e[0], v = e[1];
            graph[u].add(v);
        }
        
        LinkedList<Integer> que = new LinkedList<>();
		ArrayList<Integer> ans = new ArrayList<>();
		int[] indegree = new int[N];

		for(int i = 0; i < N; i++) {
			for(int e : graph[i]) {
				indegree[e]++;
			}
		}

		for(int i = 0; i < N; i++) {
			if(indegree[i] == 0) 
				que.addLast(i);			
		} 

		while(que.size() != 0) {
			int size = que.size();
			while(size-->0) {
				int rVtx = que.removeFirst();
                ans.add(rVtx);
				for(int e : graph[rVtx]) {
					if(--indegree[e] == 0) 
						que.addLast(e);
				}
			}
		}

		return ans.size() == N;
    }

    // 210. Course Schedule II
    public int[] findOrder(int N, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++) 
            graph[i] = new ArrayList<>();
        
        for(int[] e : prerequisites) {
            int u = e[0];
            int v = e[1];
            
            graph[u].add(v);
        }
        
        LinkedList<Integer> que = new LinkedList<>();
		int[] ans = new int[N];
		int[] indegree = new int[N];
        int idx = N-1;
		// O(E)
		for(int i = 0; i < N; i++) {
			for(int v : graph[i]) {
				indegree[v]++;
			}
		}

		// O(V)
		for(int i = 0; i < N; i++) {
			if(indegree[i] == 0) 
				que.addLast(i);			
		} 

		// O(E + V)
		while(que.size() != 0) {
			
				int rVtx = que.removeFirst();
                ans[idx--] = rVtx;
                
				for(int v : graph[rVtx]) {
					if(--indegree[v] == 0) 
						que.addLast(v);
				}
			
		}

		if(idx != -1) ans = new int[0];

		return ans;
    }

    // =======================================================
    // Cycle dectect using DFS in Directed graph

    // tc -> O(E)
    public static boolean dfs_isCycle(ArrayList<Edge>[] graph, int src, boolean[] vis, boolean[] path, ArrayList<Integer> ans) {
    	vis[src] = path[src] = true;
    	boolean isCycle = false;
    	for(Edge e : graph[src]) {
    		if(!vis[e.v]) {
    			isCycle = isCycle || dfs_isCycle(graph, e.v, vis, path, ans);
    		} else if(path[e.v]) 
    			return true;
    	}
    	ans.add(src);
    	path[src] = false;
    	return isCycle;
    }

    // tc -> O(V + E) , sc -> O(2N) , rsc -> O(N)
    public static ArrayList<Integer> dfs(ArrayList<Edge>[] graph) {
    	ArrayList<Integer> ans = new ArrayList<>();
    	int N = graph.length;
    	boolean[] vis = new boolean[N];	
    	boolean[] path = new boolean[N]; // hum path me do chije stored karenge
    										// 1. current path -> true se denote karenge -> vis ke time true store kar lenge
    										// 2. previous path -> false se denote karenge -> backtrack me false store kar lenge
    	for(int i = 0; i < N; i++) {
    		if(!vis[i] && dfs_isCycle(graph, i, vis, path, ans)) 
    			ans.clear();
    	}
    	return ans;
    }

    // space optimize
    // 0 -> unvisited, 1 -> currentPath, 2 -> backtrack
    // tc -> O(E + V), sc -> O(N)
    public static boolean dfs_topo_isCycle(ArrayList<Edge>[] graph,int src, int[] vis, ArrayList<Integer> ans) {
    	vis[src] = 1;
    	boolean dfsCycle = false; 
    	for(Edge e : graph[src]) {
    		if(vis[e.v] == 0) 
    			dfsCycle = dfsCycle || dfs_topo_isCycle(graph, e.v, vis, ans);
    		else if(vis[e.v] == 2) 
    			return true;
    	}
    	vis[src] = 2;
    	ans.add(src);
    	return dfsCycle;

    }

    public static ArrayList<Integer> cycleDetectUsingDfs(ArrayList<Edge>[] graph) {
    	ArrayList<Integer> ans = new ArrayList<>();
    	boolean isCycle = false;
    	int N = graph.length;
    	int[] vis = new int[N];

    	for(int i = 0; i < N; i++) {
    		if(vis[i] == 0) {
    			isCycle = isCycle || dfs_topo_isCycle(graph, i, vis, ans);
    		}
    	}

    	if(isCycle) 
    		ans.clear();

    	return ans;
    }

    // =================================_Kosaraju'=================================
    public static void topoOrder(ArrayList<Edge>[] graph, int src, ArrayList<Integer> order, boolean[] vis) {
    	vis[src] = true;
    	for(Edge e : graph[u]) {
    		if(!vis[e.v]) {
    			topoOrder(graph, e.v, order, vis);
    		}
    	}

    	order.add(src);
    }

    public static void SCC_compo(ArrayList<Edge>[] ngraph, int scr, boolean[] vis, ArrayList<Integer> component) {
    	vis[src] = true;
    	component.add(src);

    	for(Edge e : graph[src]) {
    		if(!vis[e.v])
    			SCC_compo(ngraph, e.v, vis, component);
    	}
    }

    public static void kosaraju(ArrayList<Edge>[] graph) {
    	int N = graph.length;
    	
    	// 1. Topological order
    	ArrayList<Integer> topoOrder = new ArrayList<>;
    	boolean[] vis = new boolean[N];
    	for(int i = 0; i< N; i++) {
    		if(!vis[i])
    			topoOrder(graph, src, topoOrder, vis);
    	}

    	// 2. compliment of Graph
    	ArrayList<Edge>[] ngraph = new ArrayList[N];
    	for(int i = 0; i < N; i++) 
    		ngraph[i]  = new ArrayList<>();

    	for(int i = 0; i < N; i++) {
    		for(Edge e : graph[i]) {
    			ngraph[e.v].add(new Edge(i,w));
    		}
    	}

    	// 3. DFS -> topological order
    	ArrayList<Integer> component = new ArrayList<>();
    	vis = new boolean[N];
    	for(int i = topoOrder.size(); i >= 0; i++) {
    		int u = topoOrder.get(i);
    		if(!vis[u]) {
    			SCC_compo(ngraph, u, vis, component);
    			System.out.println(component);
    			component.clear();
    		}
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