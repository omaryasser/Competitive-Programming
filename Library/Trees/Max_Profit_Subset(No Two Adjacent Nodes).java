static ArrayList<Integer> adjList [];
	static int dp1 [];
	static int dp2 [];
	static int earn [];
	//O(N)
	static void dfs (int V , int pV) { // ans is max(dp1[1] , dp2[1])
		int sum1 = 0;
		int sum2 = 0;
		
		for (int v : adjList[V]) {
			if (v == pV) continue;
			dfs (v , V);
			sum1 += dp2[v];
			sum2 += Math.max(dp1[v], dp2[v]);
		}
		
		dp1[V] = earn[V] + sum1;
		dp2[V] = sum2;
	}
