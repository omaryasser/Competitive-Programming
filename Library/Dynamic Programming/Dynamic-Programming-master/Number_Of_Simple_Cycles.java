static int V;
	static ArrayList<Integer> adjList [];
	
	static int first(int a){
	    int idx = 0;
	    while (a > 0 && (a & 1) == 0) {
	        idx++;
	        a>>=1;
	    }
	    return idx;
	}
	static long Number_Of_Simple_Cycles () { // Time Complexity O(2 ^ n  * n ^ 2)
		long dp [][] = new long[1 << V][V];
		
		for (int i = 0 ; i < V ; ++i)
			dp[1 << i][i] = 1;
		
		for (int mask = 1 ; mask < 1 << V ; ++mask) {
				if (Integer.bitCount(mask) <= 1) continue;
				for (int current = 0 ; current < V ; ++current) {
					if (((1 << current) & mask) == 0) continue;
					
					for (int last : adjList[current])
						if (current != first(mask))
							{
								dp[mask][current] += dp[mask ^ (1 << current)][last];
							}
				}
		}
		
		long ans = 0 ;
		int allVisited = (1 << V) - 1;
		for (int mask = 1 ; mask < 1 << V ; ++mask) {
			if (Integer.bitCount(mask) < 3) continue;
			for (int u = 0 ; u < V ; ++u) {
				if (adjList[u].contains(first (mask)))
					ans += dp[mask][u];
			}
		}
		return ans >> 1;
	}
