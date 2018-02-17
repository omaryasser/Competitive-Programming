int bitCNT(int a){
    int sum = 0;
    while (a>0) {
        if (a&1) {
            sum++;
        }
        a>>=1;
    }
    return sum;
}

int numberOfSimplePaths(){
    memset(dp, 0, sizeof(dp));
    for (int i=0; i<V; i++) {
        dp[1<<i][i] = 1;
    }
    for (int msk = 1; msk<(1<<V); msk++) { //dist[i][j] = 1 if there is a path 0 otherwise
        for (int cur = 0; cur<V; cur++) {
            if ( (msk & (1<<cur) ) == 0) {
                continue;
            }
            for (int nxt = 0; nxt<V; nxt++) {
                
                if (cur == nxt || !(msk&(1<<nxt)) || dist[cur][nxt] == 0) {
                    continue;
                }else{
                    dp[msk][cur] += dp[msk^(1<<cur)][nxt];
                }
            }
        }
        
    }
    int ans = 0;
    for (int msk=1; msk<1<<V; msk++) {
        for (int i=0;i<V ; i++) {
            if (bitCNT(msk)>=2) {
                ans+=dp[msk][i];
            }
        }
    }
    return ans;
}

