
ll AdjMat[30][30];
ll dp[(1<<19)+2];
int edges[30];

int main () {
    ios_base::sync_with_stdio(0);cin.tie(0);
    
    ll n,m;
    cin>>n>>m;
    
    for (int i=0; i<m; i++) { //O(2^n * n)
        ll a,b;
        cin>>a>>b;
        a--,b--;
        AdjMat[a][b] = 1;
        AdjMat[b][a] = 1;
    }
    memset(dp, 0, sizeof(dp));
    dp[1] = 1;
    
    for (int i=0; i<n; i++) {
        int sum = 0;
        for (int j=0; j<n; j++) {
            if (i==j || AdjMat[i][j]==0) {
                continue;
            }
            sum+=(1<<j);
        }
        edges[i] = sum;
    }
    
    for (int msk=0; msk<(1<<n); msk++) {
        for (int prev=0; prev<n; prev++) {
            if (( dp[msk^(1<<prev)]& edges[prev] )!=0 && (msk&(1<<prev))) {
                dp[msk]+=(1<<prev);
            }
        }
    }
    bool ans = false;
    ll last = dp[(1 << n) - 1];
    for (int i = 0 ; i < n ; ++i) {
        if (((1 << i) & last) != 0) {
            if ((edges[0] & i) != 0) {
                ans = true;
                break;
            }
        }
    }
    cout<<ans;
    return 0;
}
