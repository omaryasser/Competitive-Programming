ll AdjMat[30][30];
ll dp[(1<<19)+2][30]; //The number of HamPaths starts at idx first(i) up to j

int main () {
    ios_base::sync_with_stdio(0);cin.tie(0);
    
    int n,m;
    cin>>n>>m;
    
    for (int i=0; i<m; i++) {
        ll a,b;
        cin>>a>>b;
        a--,b--;
        AdjMat[a][b] = 1;
        AdjMat[b][a] = 1;
    }
    memset(dp, 0, sizeof(dp));
    for (int i=0; i<n; i++) {
        dp[1<<i][i] = 1;
    }
    for (int msk=0; msk<(1<<n); msk++) {
        if (bitCNT(msk)<2) {
            continue;
        }
        for (int i=0; i<n; i++) {
            
            if (!(msk & (1<<i))) {
                continue;
            }
            
            for (int j=0; j<n; j++) {
                
                if (first(msk) == i || i==j || !AdjMat[i][j]) {
                    continue;
                }
                dp[msk][i] += dp[msk ^ (1<<i)][j];
            }
        }
    }
    ll ans = 0;
    for (int msk=0; msk<(1<<n); msk++) {
        for (int i=0; i<n; i++) {
            if (bitCNT(msk)>=3 && AdjMat[first(msk)][i]) {
                ans+=dp[msk][i];
            }
        }
    }
    cout<<(ans>>1);
    return 0;
}
