import java.util.LinkedList;

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

    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m ; j++) {
    			if(grid[i][j] == 2) {
    				que.addLast(new pair(i,j));
    				grid[i][j] = 2; // mark as visited
    			} else if(grid[i][j] == 1) {
    				freshCount++;
    			} 
    		}
    	} 

    	if(freshCount == 0) return 0;
    	int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
    	while(que.size() != 0) {
    		int size = que.size();
    		while(size-->0) {
    			pair rem = que.removeFirst();
    			for(int d = 0; d < dir.length; d++) {
    				int r = rem.row + dir[d][0];
    				int c = rem.col + dir[d][1];

    				if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
    					if(--freshCount == 0) return time + 1;
                        que.addLast(new pair(r,c));
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

    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m ; j++) {
    			if(grid[i][j] == 2) {
    				que.addLast(i * m + j);
    				grid[i][j] = 2; // mark as visited
    			} else if(grid[i][j] == 1) {
    				freshCount++;
    			} 
    		}
    	} 

    	if(freshCount == 0) return 0;

    	int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
    	while(que.size() != 0) {
    		int size = que.size();
    		while(size-->0) {
    			int rIdx = que.removeFirst();
                int sr = rIdx / m, sc = rIdx % m;
    			for(int d = 0; d < dir.length; d++) {
    				int r =  sr + dir[d][0];
    				int c =  sc + dir[d][1];

    				if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
    					if(--freshCount == 0) return time + 1;
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
    public int bfs_shortestPath(int[][] grid, int sr, int sc ,int dr,int dc, int[][] dir) {
        LinkedList<Integer> queue = new LinkedList<>();
        int n = grid.length, m = grid[0].length;
        queue.addLast(0);
        int ShortestPath = 1;
        
        grid[sr][sc] = 1;
        while(queue.size() != 0) {
            int size = queue.size();
            while(size-->0) {
                int idx = queue.removeFirst();
                int x = idx / m , y = idx % m;
                if(x == dr && y == dc) return ShortestPath;
                
                for(int d = 0; d < dir.length; d++) {
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];
                    if(r>= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
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
        if(grid.length == 0 || grid[0].length == 0 ) return 0;
        int n = grid.length, m = grid[0].length;
        if(grid[0][0] == 1 || grid[n-1][m-1] == 1) return -1;
        
        int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
        return bfs_shortestPath(grid, 0, 0, n-1, m-1, dir);
    }

    // 542. 01 Matrix
    public int[][] updateMatrix_01(int[][] mat) {
    	LinkedList<Integer> que = new LinkedList<>();
    	int n = mat.length, m = mat[0].length;
    	boolean[][] vis = new boolean[n][m]; // extra space n * m
        // O(n*m)     	
        for(int i = 0; i < n ; i++) {
    		for(int j = 0; j < m; j++) {
    			if(mat[i][j] == 0) {
    				vis[i][j] = true;
    				que.addLast(i * m + j);

    			} 
    		}
    	}  
		
		int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
        // BFS - O(n*m)         
    	while(que.size() != 0) {
    		int size = que.size();
    		while(size-- > 0) {
    			int idx = que.removeFirst();
    			int sr = idx / m , sc = idx % m;

    			for(int d = 0; d < dir.length; d++) {
    				int r = sr + dir[d][0];
    				int c = sc + dir[d][1];
    				if(r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
    					vis[r][c] = true;
    					mat[r][c] = mat[sr][sc] + 1;
                        que.addLast(r*m + c);
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
        for(int i = 0; i < n ; i++) {
    		for(int j = 0; j < m; j++) {
    			if(mat[i][j] == 0) {
    				que.addLast(i * m + j);
    			} else {
    				mat[i][j] = -1;
    			}
    		}
    	}  
		
		int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
        // BFS - O(n*m)         
    	while(que.size() != 0) {
    		int size = que.size();
    		while(size-- > 0) {
    			int idx = que.removeFirst();
    			int sr = idx / m , sc = idx % m;

    			for(int d = 0; d < dir.length; d++) {
    				int r = sr + dir[d][0];
    				int c = sc + dir[d][1];
    				if(r >= 0 && c >= 0 && r < n && c < m && mat[r][c] < 0) {
    					mat[r][c] = mat[sr][sc] + 1;
                        que.addLast(r*m + c);
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
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < m; j++) {
                if(rooms[i][j] == 0) 
                    queue.addLast(i * m + j);
            }
        }

        int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
        int INF = 2147483647;
        while(queue.size() != 0) {
            int size = queue.size();
            while(size-->0) {
                int idx = queue.removeFirst();
                int sr = idx / m, sc = idx % m;
                for(int d = 0 ; d < dir.length ; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if(r >= 0 && c >= 0 && r < n && c < m && rooms[r][c] == INF) {
                        rooms[r][c] = rooms[sr][sc] + 1;
                        queue.addLast(r * m + c);
                    }
                }
            }
        }
    }
}

