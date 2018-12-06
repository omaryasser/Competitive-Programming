#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define double long double
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

int n,m;
const int N=100001;
vector<int>G[N];
vector<int>G2[N];
int vis[N];

vector<int>curCC;

void dfs(int u){
    vis[u]=1;
    curCC.pb(u);
    for(int v:G2[u])
        if(!vis[v])
            dfs(v);
}

int EXPLORED=1,VISITED=2;
bool f=0;
void dfs2(int u){
    vis[u]=EXPLORED;
    for(int v:G[u])
        if(vis[v]==EXPLORED)
            f=1;
        else if(vis[v]==0)
            dfs2(v);
    vis[u]=VISITED;
}


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>m;
    while(m--){
        int u,v;
        cin>>u>>v;
        G[--u].pb(--v);
        G2[u].pb(v);
        G2[v].pb(u);
    }

    int res=0;
    vector<vector<int> >CC;
    REP(i,n)
        if(!vis[i]){
            dfs(i),res+=curCC.size()-1;
            CC.pb(curCC);
            curCC.clear();
        }

    memset(vis,0,sizeof vis);
    int more=0;
    REP(i,CC.size()){
        f=0;
        for(int v:CC[i])
            if(vis[v]==0){
                dfs2(v);
            }
        if(f)more++;
    }
    cout<<more+res<<"\n";
}