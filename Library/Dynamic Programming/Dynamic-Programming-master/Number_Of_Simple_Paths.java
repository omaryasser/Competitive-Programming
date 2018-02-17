static int V;
	static ArrayList<Integer> adjList [];
	
	static int Number_Of_Simple_Paths () { // Time Complexity O(2 ^ n  * n ^ 2)
		int dp [][] = new int[1 << V][V];
		
		for (int i = 0 ; i < V ; ++i)
			dp[1 << i][i] = 1;
		
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
		for (int mask = 1 ; mask < 1 << V ; ++mask) {
			for (int i = 0 ; i < V ; ++i) {
				if (Integer.bitCount(mask) >= 2)
					ans += dp[mask][i];
			}
		}
		return ans >> 1;
	}
