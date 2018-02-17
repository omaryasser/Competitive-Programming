static boolean Hamiltonian_Path_Exists () { // Time Complexity O(2^n *  n ^ 2)
		boolean dp [][] = new boolean[1 << V][V];
		
		for (int i = 0 ; i < 1 << V ; ++i)
			Arrays.fill(dp[i], false);
		
		for (int i = 0 ; i < V ; ++i)
			dp[1 << i][i] = true;
		
		for (int mask = 1 ; mask < 1 << V ; ++mask) {
				if (Integer.bitCount(mask) <= 1) continue;
				for (int current = 0 ; current < V ; ++current) {
					if (((1 << current) & mask) == 0) continue;
					
					for (int last : adjList[current])
						if (((1 << last) & mask) == 1)
							dp[mask][current] |= dp[mask ^ (1 << current)][last];
				}
		}
		
		boolean ans = false ;
		for (int mask = 1 ; mask < 1 << V ; ++mask) {
			for (int i = 0 ; i < V ; ++i) {
				if (Integer.bitCount(mask) >= 2)
					ans |= dp[mask][i];
			}
		}
		return ans;
	}
	
	static int V;
	static ArrayList<Integer> adjList [];
	
	static boolean Hamiltonian_Path_Exists_Improved () { // Time Complexity O(2^n *  n)
		int edges [] = new int[V];
		for (int u = 0 ; u < V ; ++u) {
			for (int v : adjList[u]) {
				edges[u] += (1 << v);
			}
		}
		
		int dp [] = new int[1 << V];
		for (int i = 0 ; i < V ; ++i)
			dp[1 << i] = 1 << i;
		
		for (int mask = 1 ; mask < 1 << V ; ++mask) {
			if (Integer.bitCount(mask) <= 1) continue;
			for (int u = 0 ; u < V ; ++u) {
				if (((1 << u) & mask) == 0) continue;
				if ((dp[mask ^ (1 << u)] & edges[u]) != 0)
					dp[mask] += (1 << u);
			}
		}
		
		return dp[(1 << V) - 1] != 0;
		
	}
