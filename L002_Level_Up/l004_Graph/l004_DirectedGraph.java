import java.util.LinkedList;
import java.util.ArrayList;

public class l004_DirectedGraph {
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

	// 	========================================================================================================================================
	// 	Concept_1 : Topological order
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

	// 	========================================================================================================================================
	// 	Concept_2 : KAHN's ALGO -> BFS
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

	//	========================================================================================================================================
	// 	Question_1 : 207. Course Schedule 
	// 	https://leetcode.com/problems/course-schedule/
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++)
            graph[i] = new ArrayList<>();
        
        for(int[] prerequisite : prerequisites) {
            int u = prerequisite[0], v = prerequisite[1];
            graph[u].add(v);
        }
        
        // step 1 => make indedree
        int[] indegree = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            for(int v : graph[i]) 
                indegree[v]++;
        }
        
        /*
            step 2 => create a que and add vtx those have zero indegree 
            which indicate that I am independent to other  
        */ 
        LinkedList<Integer> que = new LinkedList<>();
        for(int v = 0; v < indegree.length; v++) {
            if(indegree[v] == 0) {
                que.addLast(v);
            }
        }
        
        /*
            step 3 : run loop untill size of que not become zero
            and operate these a) remove => add in result b) add => when indegree of v is become zero
        */ 
        ArrayList<Integer> res = new ArrayList<>(); 
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int rv = que.removeFirst();
                res.add(rv);
                
                for(int v : graph[rv]) {
                    if(--indegree[v] == 0)
                        que.addLast(v);
                }
            }
        }
        
        if(res.size() != numCourses)
            return false;
        
        return true;
    }

	// 	========================================================================================================================================
    // 	Question_2 : 210. Course Schedule II
	// 	https://leetcode.com/problems/course-schedule-ii/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++)
            graph[i] = new ArrayList<>();
        
        for(int[] prerequisite : prerequisites) {
            int u = prerequisite[0], v = prerequisite[1];
            graph[u].add(v);
        }
        
        // step 1 => make indedree
        int[] indegree = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            for(int v : graph[i]) 
                indegree[v]++;
        }
        
        /*
            step 2 => create a que and add vtx those have zero indegree 
            which indicate that I am independent to other  
        */ 
        LinkedList<Integer> que = new LinkedList<>();
        for(int v = 0; v < indegree.length; v++) {
            if(indegree[v] == 0) {
                que.addLast(v);
            }
        }
        
        /*
            step 3 : run loop untill size of que not become zero
            and operate these a) remove => add in result b) add => when indegree of v is become zero
        */ 
        int[] res = new int[numCourses];
        int idx = numCourses - 1;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                int rv = que.removeFirst();
                res[idx--] = rv;
                
                for(int v : graph[rv]) {
                    if(--indegree[v] == 0)
                        que.addLast(v);
                }
            }
        }
        
        
        if(idx != -1)
            res = new int[0];
        
        return res;
    }


	// 	========================================================================================================================================
	// 	Question_3 : 329. Longest Increasing Path in a Matrix
	// 	https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
	private class Pair {
        int row, col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public int longestIncreasingPath(int[][] matrix) {
        /*
            step 1 => first find out indegree    
                    3 -> 6 <- 4 => indegree of 6 is 2
        */ 
        int n = matrix.length, m = matrix[0].length;
        int[][] indegree = new int[n][m];
        int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; 
        for(int sr = 0; sr < n; sr++) {
            for(int sc = 0; sc < m; sc++) {
                for(int d = 0; d < dir.length; d++) {
                    int row = sr + dir[d][0], col = sc + dir[d][1];
                    if(row >= 0 && row < n && col >= 0 && col < m && matrix[sr][sc] < matrix[row][col]) {
                        indegree[row][col]++;
                    }
                }
            }
        }
        
        
        // step 2 => add all cell whose indegree is zero in que
        LinkedList<Pair> que = new LinkedList<>();
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(indegree[r][c] == 0) {
                    que.addLast(new Pair(r, c));
                }
            } 
        }
        
        /*
            step 3 => run a loop until size is not zero and remove first pair from que.
            For every removal pair, move in four direction => left, up, right, down => and go in each direction
            whether removal element value is smaller and adding each directional element if --indegree[row][col] == 0
        */           
        int length = 0;
        while(que.size() != 0) {
            int size = que.size();
            while(size-- > 0) {
                Pair rp = que.removeFirst(); // removal pair from que
                int sr = rp.row, sc = rp.col; // extract row and col
                
                for(int d = 0; d < dir.length; d++) {
                    int row = sr + dir[d][0], col = sc + dir[d][1];
                    if(row >= 0 && row < n && col >= 0 && col < m && matrix[row][col] > matrix[sr][sc] && --indegree[row][col] == 0) {
                        que.addLast(new Pair(row, col));
                    }
                }
            }
            length++;
        }
        
        return length;
                                                   
    }

    // 	========================================================================================================================================
    // 	Question_4 : Detect cycle in a directed graph 
	//	https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1/

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