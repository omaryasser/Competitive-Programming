/*
APSP
*/
#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;


int n,m;
const int N=1001;
vector<pair<int,int> >G[N];
int S,T;
int t[N],c[N];
ll sp[N][N];

ll d[N];
ll INF=1e17;

void dijkstra(int s){
    REP(i,n)sp[s][i]=INF;
    sp[s][s]=0;
    set<pair<ll,int> >pq;
    pq.insert(mp(0,s));
    while((int)pq.size()){
        pair<ll,int>p=*pq.begin();pq.erase(pq.begin());
        ll _c=p.fi;
        int _u=p.se;
        if(sp[s][_u]<_c)continue;
        for(auto nxt:G[_u]){
            int v=nxt.fi;
            int need=nxt.se;
            if(sp[s][v]>sp[s][_u]+need){
                sp[s][v]=sp[s][_u]+need;
                pq.insert(mp(sp[s][_u]+need,v));
            }
        }
    }
}
ll dijkstra1 (){
    REP(i,n)d[i]=INF;
    d[S]=0;
    set<pair<ll,int > >pq;
    pq.insert(mp(0,S));
    while((int)pq.size()){
        pair<ll,int>p=*pq.begin();pq.erase(pq.begin());
        int _u=p.se;
        ll _c=p.fi;
        if(_u==T)return _c;
        if(d[_u]<_c)continue;
        REP(i,n){
            if(sp[_u][i]<=t[_u]&&d[i]>_c+c[_u]){
                d[i]=_c+c[_u];
                pq.insert(mp(d[i],i));
            }
        }
    }
    return -1;
}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif     

    scanf("%d%d",&n,&m);
    scanf("%d%d",&S,&T);
    --S,--T;
    while(m--){
        int u,v,c;
        scanf("%d%d%d",&u,&v,&c);
        G[--u].pb(mp(--v,c));
        G[v].pb(mp(u,c));
    }
    REP(i,n)dijkstra(i);
    REP(i,n)scanf("%d%d",&t[i],&c[i]);
    printf("%lld\n", dijkstra1());
    return 0;
}