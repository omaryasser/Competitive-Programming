static int V;
	static ArrayList<Integer> adjList [];
	
	static int Number_Of_Hamiltonian_Cycles () { // Time Complexity O(2 ^ n  * n ^ 2)
		int dp [][] = new int[1 << V][V];
		
		dp[1][0] = 1;
		
		for (int mask = 1 ; mask < 1 << V ; ++mask) {
				if (Integer.bitCount(mask) <= 1) continue;
				for (int current = 0 ; current < V ; ++current) {
					if (((1 << current) & mask) == 0) continue;
					
					for (int last : adjList[current])
						if (((1 << last) & mask) == 1)
							dp[mask][current] += dp[mask ^ (1 << current)][last];
				}
		}
		
		int ans = 0 ;
		int allVisited = (1 << V) - 1;
		for (int i = 0 ; i < V ; ++i)
			ans += (adjList[i].contains(0)) ? dp[allVisited][i] : 0;
		return ans;
	}
