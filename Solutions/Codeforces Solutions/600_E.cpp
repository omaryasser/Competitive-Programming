#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

const int MAX=1e5+10;
int c[MAX];
vector<int>adjList[MAX];
int n;
int t;
int tin[MAX],tout[MAX],ver[MAX],sz[MAX];
ll ans[MAX];
void dfs (int u=0,int p=-1){
    sz[u]=1;
    ver[t]=u;
    tin[u]=t++;
    for(auto v:adjList[u]){
        if(v!=p){
            dfs(v,u);
            sz[u]+=sz[v];
        }
    }
    tout[u]=t-1;
}
int cnt[MAX];
map<int,ll>sumOcc;
void add(int u){
    cnt[c[u]]++;
    sumOcc[cnt[c[u]]]+=c[u];
    sumOcc[cnt[c[u]]-1]-=c[u];
    if(sumOcc[cnt[c[u]]-1]==0)sumOcc.erase(cnt[c[u]]-1);
}
void remo(int u){
    cnt[c[u]]--;
    sumOcc[cnt[c[u]]]+=c[u];
    sumOcc[cnt[c[u]]+1]-=c[u];
    if(sumOcc[cnt[c[u]]+1]==0)sumOcc.erase(cnt[c[u]]+1);
}
void go(int u=0,int p=-1,bool rem=0){
    int bigC=-1;
    for(auto v:adjList[u])
        if(v!=p&&(bigC==-1||sz[v]>sz[bigC]))
            bigC=v;
    for(auto v:adjList[u])
        if(v!=p&&v!=bigC)
            go(v,u,1);
    if(bigC!=-1)
        go(bigC,u,0);
    add(u);
    for(auto v:adjList[u])
        if(v!=p&&v!=bigC)
            FOR(tim,tin[v],tout[v]+1)
                add(ver[tim]);
    ans[u]=sumOcc.rbegin()->second;
    if(rem){
        FOR(tim,tin[u],tout[u]+1)
            remo(ver[tim]);
    }
}
int main() {
    scanf("%d",&n);
    FOR(i,0,n)scanf("%d",c+i);
    FOR(i,0,n-1){
        int u,v;
        scanf("%d%d",&u,&v);
        u--,v--;
        adjList[u].push_back(v);
        adjList[v].push_back(u);
    }
    dfs();
    go();
    FOR(i,0,n){
        if(i>0)printf(" ");
        printf("%lld",ans[i]);
    }
    printf("\n");
}