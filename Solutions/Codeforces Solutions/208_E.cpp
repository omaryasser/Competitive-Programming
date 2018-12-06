#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int n;
const int MAX=100001,LOG=log(100001)/log(2)+5;
int P[LOG][MAX];
vector<int>tree[MAX];
vector<pair<int,int> >queries[MAX];
int lvl[MAX],sz[MAX],res[MAX];
int cnt[MAX];
bool bad[MAX];
int getA(int u,int d){
    int wantedLvl=lvl[u]-d;
    if(wantedLvl<0)return 0;
    for(int log=LOG-1;log>=0;log--)
        if(lvl[u]-(1<<log)>wantedLvl)
            u=P[log][u];
    return P[0][u];
}
void dfs(int u=0){
    sz[u]=1;
    for(auto v:tree[u]){
        lvl[v]=lvl[u]+1;
        dfs(v);
        sz[u]+=sz[v];
    }
}
void add(int u,int i){
    cnt[lvl[u]]+=i;
    for(auto v:tree[u])
        if(!bad[v])
            add(v,i);
}
void dfs2(int u=0,int rem=0){
    int mx=-1;
    for(auto v:tree[u])
        if(mx==-1||sz[mx]<sz[v])
            mx=v;
    for(auto v:tree[u])
        if(v!=mx)
            dfs2(v,1);
    if(mx!=-1)dfs2(mx,0),bad[mx]=true;
    add(u,1);
    for(auto q:queries[u])
        res[q.second]=lvl[u]+q.first<MAX?cnt[lvl[u]+q.first]-1:0;
    if(mx!=-1)bad[mx]=false;
    if(rem)add(u,-1);
}
int main() {
    scanf("%d",&n);
    memset(P,-1, sizeof(P));
    FOR(i,1,n+1){
        int p;scanf("%d",&p);
        P[0][i]=p;
        tree[p].push_back(i);
    }
    dfs();
    FOR(log,1,LOG)FOR(i,0,n+1)
            if(P[log-1][i]!=-1)
                P[log][i]=P[log-1][P[log-1][i]];
    int q;scanf("%d",&q);
    FOR(i,0,q){
        int v,p;
        scanf("%d%d",&v,&p);
        int A=getA(v,p);
        if(A)queries[A].push_back({p,i});
    }
    dfs2();
    FOR(i,0,q){
        if(i)printf(" ");
        printf("%d",res[i]);
    }
    printf("\n");
}