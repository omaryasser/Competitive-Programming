#include <bits/stdc++.h>
#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int n_=1e5+10,LOG=20;
bool a[LOG][n_];
vector<int>adj[n_];
ll ans=0;
ll dp[2][LOG][n_];
int n;
void dfs(int u,int p,int b){
    for(int v:adj[u])
        if(v!=p)
            dfs(v,u,b);
    int cnt[2];cnt[0]=cnt[1]=0;
    for(int v:adj[u])
        if(v!=p){
            //down
            ans+=(1ll<<b)*dp[a[b][u]^1][b][v];
            //down to left
            ans+=(1ll<<b)*cnt[0]*dp[a[b][u]^1][b][v];
            ans+=(1ll<<b)*cnt[1]*dp[a[b][u]][b][v];

            REP(i,2)cnt[i]+=dp[i][b][v];
        }
//    cerr<<u<< " " << b<<" "  <<a[b][u]<<"\n";
    ans+=(1ll<<b)*a[b][u];
    REP(i,2)dp[i][b][u]=cnt[i^a[b][u]]+(i==a[b][u]);
}

int main() {
    scanf("%d",&n);
    REP(i,n){
        int x;
        scanf("%d",&x);
        REP(b,LOG)a[b][i]=(x&(1<<b));
    }
    REP(i,n-1){
        int u,v;
        scanf("%d%d",&u,&v);
        adj[--u].push_back(--v);
        adj[v].push_back(u);
    }
    REP(log,LOG)dfs(0,-1,log);
    printf("%lld\n",ans);
}