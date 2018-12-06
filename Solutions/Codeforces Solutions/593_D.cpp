#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;


const int N=2e5,LOG=log(N)/log(2)+2;
int n,m;
vector<pair<int,ll> >T[N];
int P[LOG][N];
int bef[N];
int lvl[N];
int uu[N],vv[N];
ll edgeWeight[N];
void dfs(int u=0,int p=-1){
    if(!u)edgeWeight[u]=1;
    bef[u]=u;
    P[0][u]=p;
    for(auto vv:T[u])
        if(vv.first!=p){
            lvl[vv.first]=lvl[u]+1;
            edgeWeight[vv.first]=vv.second;
            dfs(vv.first,u);
        }
}

int LCA(int u,int v){
    if(lvl[v]>lvl[u])swap(u,v);
    for(int log=LOG-1;log>=0;log--)
        if((lvl[u]-(1<<log))>=lvl[v])
            u=P[log][u];

    if(u==v)return u;

    for(int log=LOG-1;log>=0;log--)
        if(P[log][u]!=P[log][v])
            u=P[log][u],v=P[log][v];
    return P[0][u];
}

int get(int u){
if(u==-1)return -1;
    int was=bef[u];
    if(was==-1)return -1;
    if(edgeWeight[was]!=1)return was;
    if(P[0][was]==-1)return -1;
    return bef[u]=get(bef[P[0][was]]);
}

ll go(int u,int lcaLvl,ll c){
    while(lvl[u]>lcaLvl){
        int p=get(u);
        if(p==-1)break;
        if(lvl[p]>lcaLvl){
            c/=edgeWeight[p];
        }else break;
        u=P[0][p];
        if(c==0||u==-1)break;
    }
    return c;
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>m;
    REP(i,n-1){
        int u,v;
        ll c;
        cin>>u>>v>>c;
        T[--u].push_back(mp(--v,c));
        T[v].push_back(mp(u,c));
        uu[i]=u,vv[i]=v;
    }
    memset(P,-1,sizeof P);
    dfs();
    REP1(log,1,LOG)REP(i,n)if(P[log-1][i]!=-1)P[log][i]=P[log-1][P[log-1][i]];
    while(m--){
        int op;
        cin>>op;
        if(--op){
            int p;
            ll c;
            cin>>p>>c;
            p--;
            int u=uu[p],v=vv[p];
            if(lvl[v]>lvl[u])swap(u,v);
            edgeWeight[u]=c;
        }else{
            int u,v;
            ll c;
            cin>>u>>v>>c;
            u--,v--;
            int lcaLvl=lvl[LCA(u,v)];
            c=go(u,lcaLvl,c);
            c=go(v,lcaLvl,c);
            cout<<c<<"\n";
        }
    }
}