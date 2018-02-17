bool improvedHamWalkExistence(){
    memset(dp, 0, sizeof(dp));
    for (int i=0; i<V; i++) {
        dp[1<<i] = 1<<i;
    }
    int edges[V];
    for (int i=0; i<V; i++) {
        int sum = 0;
        for (int j=0; j<V; j++) {
            if (i==j || dist[i][j]==0) {
                continue;
            }
            sum+=(1<<j);
        }
        edges[i] = sum;
    }
    for (int msk=1; msk<1<<V; msk++) {
        
        for (int prev=0; prev<V; prev++) {
            if (( dp[msk^(1<<prev)] & edges[prev] )!=0) {
                dp[msk]+=(1<<prev);
            }
        }
    }
    return dp[1<<V-1]!=0;
}
