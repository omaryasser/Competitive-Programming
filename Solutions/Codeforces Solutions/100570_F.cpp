#include<bits/stdc++.h>

#define lp(i, s, n) for (int i = s; i < n; i++)
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define bg cout<<"HERE\n";
using namespace std;

typedef long long ll;

const int MAX=200001;
int n;
vector<int>tree[MAX];
int a[MAX],P[MAX],sz[MAX];
bool done[MAX];
ll ans1[MAX],ans2[MAX];
int cnt[1<<20];

void dfs(int u,int p){
    sz[u]=1;
    for(int v:tree[u])
        if(v!=p&&!done[v])
            dfs(v,u),sz[u]+=sz[v];
}
void add(int u,int p,int msk,int ad){
    msk^=1<<a[u];
    cnt[msk]+=ad;
    for(int v:tree[u])if(v!=p&&!done[v])add(v,u,msk,ad);
}
pair<ll,ll> solve(int u,int p,int msk,int centroid){
    msk^=(1<<a[u]);
    ll res1=cnt[msk],res2=0;
    if(__builtin_popcount(msk^(1<<a[centroid]))<=1)res2++;
    lp(i,0,20)res1+=cnt[msk^(1<<i)];
    for(int v:tree[u])if(v!=p&&!done[v]){
            pair<ll,ll> go=solve(v,u,msk,centroid);
            res1+=go.first,res2+=go.second;
        }
    ans1[u]+=res1,ans2[u]+=res2;
    return {res1,res2};
}
void solve(int centroid){
    ans2[centroid]++;
    for(int v:tree[centroid])if(!done[v])add(v,centroid,1<<a[centroid],1);
    ll res1=0;
    for(int v:tree[centroid])if(!done[v]){
        add(v,centroid,1<<a[centroid],-1);
        pair<ll,ll>go=solve(v,centroid,0,centroid);
        res1+=go.first,ans2[centroid]+=go.second;
        add(v,centroid,1<<a[centroid],1);
    }
    ans1[centroid]+=res1/2;
    for(int v:tree[centroid])if(!done[v])add(v,centroid,1<<a[centroid],-1);
}
int decompose(int u=0,int p=-1,int all=-1){
    if(p==-1)dfs(u,p),all=sz[u];
    int mx=-1;
    for(auto v:tree[u])if(v!=p&&!done[v]&&sz[v]>all/2)mx=v;
    if(mx!=-1){
        decompose(mx,u,all);
    }else{
        done[u]=true;
        solve(u);
        for(auto v:tree[u])if(!done[v])decompose(v,-1,-1);
    }
}
int main() {
    FAST
    cin>>n;
    lp(i,0,n-1){
        int u,v;
        cin>>u>>v;
        tree[--u].push_back(--v);
        tree[v].push_back(u);
    }
    string s;cin>>s;
    lp(i,0,s.length())a[i]=s[i]-'a';
    decompose();
    lp(i,0,n)cout<<ans1[i]+ans2[i]<<"\n";
}