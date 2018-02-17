for (int i = 0; i <= (int)1e5 + 3; ++ i)
		dp[i] = INF;

	dp[0] = 0;

// m[i]  is the number of times i occurs
// dp[i] = min number of subsets of which sum is i

	for (map<int,int> ::iterator it = m.begin(); it != m.end(); it++) {
		int size = it->first , cnt = it->second , p = 1;
		while (cnt > 0) {
			int numNow = size * min(cnt, p);
			for (int i = V; i >= numNow; i--)
				dp[i] = min (dp[i] , min(cnt, p) + dp[i - numNow]);
			cnt -= p;
			p <<= 1;;
		}
	}
