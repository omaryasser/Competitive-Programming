#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define double long double
#define mk(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

int n,m;
const int N=101;
vector<int>G[N];
bool vis[N];

pair<bool,int> dfs(int u,int root,int p=-1){
  vis[u]=1;
  int c=1;
  bool a=0;
  if(u==root&&G[u].size()){
    int v=G[u][0];
    pair<bool,int>p=dfs(v,root,u);
    c+=p.se;
    a|=p.fi;
    if(G[u].size()>1&&!vis[G[u][1]]){
      int v=G[u][1];
      pair<bool,int>p=dfs(v,root,u);
      c+=p.se;
      a|=p.fi;
    }
  }else
    for(int v:G[u]){
      if(v==p)continue;
      if(vis[v]&&u!=root)return {1,1};
      pair<bool,int>p=dfs(v,root,u);
      c+=p.se;
      a|=p.fi;
    }
  return {a,c};
}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    scanf("%d%d",&n,&m);
    while(m--){
      int a,b;
      scanf("%d%d",&a,&b);
      G[--a].pb(--b);
      G[b].pb(a);
    }
    
    int cnt=0;
    int o=0;
    REP(i,n){
      if(!vis[i]){
        pair<bool,int>p=dfs(i,i);
        if(!p.fi)o+=p.se&1;
        else cnt+=p.se&1;
      }
    }
    printf("%d\n", cnt+(o&1));
    return 0;
}