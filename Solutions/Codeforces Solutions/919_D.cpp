#include<bits/stdc++.h>
#define lp(i,j,n) for(int i=j;i<n;i++)
using namespace std;

typedef long long ll;
const int n_=300001;
vector<int>adj[n_];
int n,m;
string s;
int dfs_num[n_];
bool cycle=0;
int memo[n_][26];
void dfs(int u){
    dfs_num[u]=1;
    for(auto v:adj[u])
        if(!dfs_num[v])dfs(v);
        else if(dfs_num[v]==1)cycle=1;
    dfs_num[u]=2;
}
int dp(int u,int c){
    int &ret=memo[u][c];
    if(ret!=-1)return ret;
    ret=s[u]==c+'a'?1:0;
    for(auto v:adj[u])ret=max(ret,(s[u]==c+'a'?1:0)+dp(v,c));
    return ret;
}
int main() {
    ios_base::sync_with_stdio(0);cin.tie(NULL);
    cin>>n>>m;
    cin>>s;
    while(m--){
        int x,y;
        cin>>x>>y;
        if(x==y){cout<<"-1\n";return 0;}
        adj[--x].push_back(--y);
    }
    lp(i,0,n)if(!dfs_num[i])dfs(i);
    if(cycle){cout<<"-1\n";return 0;}
    int mx=0;
    memset(memo,-1,sizeof memo);
    lp(i,0,26)lp(j,0,n)mx=max(mx,dp(j,i));
    cout<<mx<<"\n";
}