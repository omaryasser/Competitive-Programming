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

const int N=1001;
int n,L,S,T;
vector<int>G[N];
int W[N][N];
vector<pair<int,int> >freee;
vector<pair<int,pair<int,int> > >init;
bool isFree[N][N];
ll dist[N];
int INF;
int P[N];
ll curD;

ll dijkstra(){
    REP(i,n)dist[i]=INF;
    set<pair<ll,int> >pq;
    dist[S]=0;
    pq.insert(mp(0,S));
    P[S]=S;
    while((int)pq.size()){
        auto p=pq.begin();pq.erase(p);
        ll c=(*p).fi;
        int u=(*p).se;
        if(u==T)return curD=c;
        if(dist[u]<c)continue;

        for(int v:G[u]){
            if(dist[v]>c+W[u][v]){
                pq.insert(mp(dist[v]=c+W[u][v],v));
                P[v]=u;
            }
        }
    }
    return dist[T];
}

void change(){
    int cur=T;
    while(cur!=S){
        int p=P[cur];
        if(isFree[cur][p]){
            W[cur][p]+=(L-curD);
            W[p][cur]=W[cur][p];
            return;
        }
        cur=p;
    }
}

void print(){
    for(auto p:init){
        if(p.se.se>L){
            cout<<p.fi<<" "<<p.se.fi<<" "<<p.se.se<<"\n";
        }else{
            cout<<p.fi<<" "<<p.se.fi<<" "<<W[p.fi][p.se.fi]<<"\n";
        }
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int m;
    cin>>n>>m>>L>>S>>T;
    INF=L+1;
    while(m--){
        int u,v,c;
        cin>>u>>v>>c;
        init.pb(mp(u,mp(v,c)));
        if(c>L)continue;
        if(!c){
            freee.pb(mp(u,v));
            isFree[u][v]=isFree[v][u]=1;
            W[u][v]=W[v][u]=INF;
            G[u].pb(v);
            G[v].pb(u);
        }
        else {
            W[u][v]=W[v][u]=c;
            G[u].pb(v);
            G[v].pb(u);
        }
    }

    if(dijkstra()<L){
        cout<<"NO\n";
    }else {
        for(auto p:freee)W[p.fi][p.se]=W[p.se][p.fi]=1;
        if(dijkstra()>L)cout<<"NO\n";
        else{
            while(1){
                change();
                if(dijkstra()==L){
                    cout<<"YES\n";
                    print();
                    return 0;
                }
            }
        }
    }
}