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


const int N=5001;
vector<int>G[N];
int n,m,s;
bool vis[N];
stack<int>st;

void topo(int u){
    vis[u]=1;
    for(int v:G[u])
        if(!vis[v])
            topo(v);
    st.push(u);
}
void dfs(int v){
    vis[v]=1;
    for(int v:G[v])
        if(!vis[v])
            dfs(v);
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);


    cin>>n>>m>>s;
    while(m--){
        int u,v;
        cin>>u>>v;
        u--,v--;
        G[u].pb(v);
    }
    REP(i,n)if(!vis[i])topo(i);
    memset(vis,0,sizeof vis);
    dfs(s-1);
    int cnt=0;
    while(!st.empty()){
        int v=st.top();st.pop();
        if(!vis[v])dfs(v),cnt++;
    }
    cout<<cnt<<"\n";
}