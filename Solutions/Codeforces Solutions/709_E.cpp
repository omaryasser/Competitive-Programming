#include<bits/stdc++.h>


using namespace std;
typedef long long ll;

const int MAX=400001;
int n,centroid;
vector<int>tree[MAX];
int sz[MAX];
bool ans[MAX];
map<int,int>can;
int big[MAX];
void dfs(int u=0,int p=-1){
    sz[u]=1;
    for(auto v:tree[u])if(v!=p)
            dfs(v,u),sz[u]+=sz[v],big[u]=max(big[u],sz[v]);
    big[u]=max(big[u],n-sz[u]);
}
int getC(int u=0,int p=-1){
    int mx=-1;
    for(auto v:tree[u])
        if(v!=p&&(mx==-1||sz[v]>sz[mx]))mx=v;
    if(mx==-1||sz[mx]<=n/2)return u;
    return getC(mx,u);
}
int dfs2(int u,int p,int a){
    int h=1;
    for(auto v:tree[u])if(v!=p)h+=dfs2(v,u,a);
    can[h]+=a;
    if(!can[h])can.erase(h);
    return h;
}
void dfs3(int u,int p){
    int needed=big[u]-n/2;
    auto it=can.lower_bound(needed);
    if(it!=can.end()&&it->first<=n/2)ans[u]=true;
    for(auto v:tree[u])
        if(v!=p)dfs3(v,u);
}
void solve(){
    ans[centroid]=1;
    for(auto v:tree[centroid])
        dfs2(v,centroid,1);
    for(auto v:tree[centroid]){
        dfs2(v,centroid,-1);
        can[n-sz[v]]++;
        dfs3(v,centroid);
        can[n-sz[v]]--;
        if(!can[n-sz[v]])can.erase(n-sz[v]);
        dfs2(v,centroid,1);
    }
}
int main() {
    ios_base::sync_with_stdio(0);cin.tie(NULL);
    cin>>n;
    for(int i=0;i<n-1;i++){
        int u,v;cin>>u>>v;
        tree[--u].push_back(--v);
        tree[v].push_back(u);
    }
    dfs();
    centroid=getC();
    dfs(centroid,-1);
    solve();
    for(int i=0;i<n;i++)
        cout<<ans[i]<<" ";
    cout<<"\n";
}