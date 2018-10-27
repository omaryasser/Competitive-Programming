  static int INF = (int)1e9;  
	static int V;
	static int dist [][];// dist[i][j] = The distance between Vertix i & Vertix j
	static int Bottom_UP_TSP() {    
	    int lim = 1 << V;  
	    int dp [][] = new int[lim][V];  
	    for (int i = 0 ; i < lim ; ++i) Arrays.fill(dp[i], INF);
	    
	    for (int i = 0; i < V; i++) {  
	        dp[1 << i][i] = 0;    // base case of visiting just 1 city  
	    }  
	    
	    for (int mask = 0; mask < lim; mask++) {  
	        for (int last = 0; last < V; last++) {  
	            if ((mask & (1 << last)) == 0) {      
	                continue; // Didn't visit last.  
	            }  
	            
	            for (int curr = 0; curr < V; curr++) {  
	                if ((mask & (1 << curr)) == 0) {  
	                    continue;  // Didn't visit current
	                }  
	                int otherMask = mask ^ (1 << curr); 
	                dp[mask][curr] = Math.min(dp[mask][curr], dp[otherMask][last] + dist[last][curr]);  
	            }  
	        }  
	    }  
	    int ans = INF;  
	    for (int i = 0; i < V; i++) {  
	        ans = Math.min(ans, dp[lim - 1][i]);  
	    }  
	    return ans;  
	}
