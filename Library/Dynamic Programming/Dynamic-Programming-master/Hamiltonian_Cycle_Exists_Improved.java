static int V;
	static ArrayList<Integer> adjList[];

	static boolean Hamiltonian_Cycle_Exists_Improved() { // Time Complexity O(2^n * n)
		int edges[] = new int[V];
		for (int u = 0; u < V; ++u) {
			for (int v : adjList[u]) {
				edges[u] |= (1 << v);
			}
		}
		
		
		int dp[] = new int[1 << V];
		dp[1] = 1;

		for (int mask = 1; mask < 1 << V; ++mask) {
			if (Integer.bitCount(mask) <= 1)
				continue;
			for (int u = 0; u < V; ++u) {
				if (((1 << u) & mask) == 0)
					continue;
				if ((dp[mask ^ (1 << u)] & edges[u]) != 0)
					dp[mask] += (1 << u);
			}
		}
		int last = dp[(1 << V) - 1];
		for (int i = 0; i < V; ++i) {
			if (((1 << i) & last) != 0) {
				if ((edges[0] & i) != 0)
					return true;
			}
		}
		return false;

	}
