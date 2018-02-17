const int INF = 1e9;
int BottomUp(vector<vector<int> > dist) { //Distance between city(i,j);
    int n = dist.size();
    int lim = 1 << n;
    int dp[lim][n];
    memset(dp, INF, sizeof(dp));
    for (int i = 0; i < n; i++) {
        dp[1 << i][i] = 0;    // base case of visiting just 1 city
    }
    for (int mask = 0; mask < lim; mask++) {
        for (int last = 0; last < n; last++) {
            if (mask & (1 << last) == 0) { // Didn't visit last.
                continue;
            }
            for (int curr = 0; curr < n; curr++) {
                if (mask & (1 << curr) == 0) { // Didn't visit current
                    continue;
                }
                int otherMask = mask ^ (1 << curr);
                dp[mask][curr] = min(dp[mask][curr], dp[otherMask][last] + dist[last][curr]);
            }
        }
    }
    int ans = INF;
    for (int i = 0; i < n; i++) {
        ans = min(ans, dp[lim - 1][i]);
    }
    return ans;
}

int memo[][];
int TopDown(int pos,int mask){
  if (mask == (1 << V) - 1)
			return cost[pos][0];
  if (memo[pos][mask]!=-1) {
    return memo[pos][mask];
  }
		int min = INF;
		for (int nxt = 0 ; nxt < V ; ++nxt)
			if (nxt != pos && (mask & (1 << nxt)) == 0)
				min = Math.min(min, cost[pos][nxt] + TopDown(nxt, mask | (1 << nxt)));
		return memo[pos][mask] = min;
	}
}
