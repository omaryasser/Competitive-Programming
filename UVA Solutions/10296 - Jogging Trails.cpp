/*
  To make this tour, the graph should satisfy euler tour conditions.
  Problem reduced to adding some edges to the original graph satisfying euler tour. (these edges should be identical to already existing ones).
  Consider two vertices u,v of odd degree. we can turn them into even-degree-vertices without altering any other vertix by adding shortest path between them.
*/
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

int n;
const int N=16;
int dist[N][N];
ll mem[1<<N];

ll dp(int msk){
    if(!msk)return 0;
    ll &ret=mem[msk];
    if(ret!=-1)return ret;
    REP(i,n)REP(j,n){
            if(i!=j&&(msk&(1<<i))&&(msk&(1<<j))){
                if(ret==-1){
                    ret=dp(msk^(1<<i)^(1<<j))+dist[i][j];
                }else{
                    ret=min(ret,dp(msk^(1<<i)^(1<<j))+dist[i][j]);
                }
            }
        }
    return ret;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    while(1){
        cin>>n;
        if(!n)break;
        int m;
        cin>>m;
        memset(dist,-1,sizeof dist);
        memset(mem,-1,sizeof mem);
        ll res=0;
        int msk=0;
        while(m--){
            int u,v,c;
            cin>>u>>v>>c;
            u--,v--;
            res+=c;
            msk^=(1<<u);
            msk^=(1<<v);
            if(dist[u][v]==-1){
                dist[u][v]=dist[v][u]=c;
            }else{
                dist[u][v]=dist[v][u]=min(dist[u][v],c);
            }
        }
        REP(i,n)REP(j,n)REP(k,n){
                    if(dist[i][k]!=-1&&dist[k][j]!=-1){
                        if(dist[i][j]==-1) {
                            dist[i][j]=dist[i][k]+dist[k][j];
                        }
                        else dist[i][j]=min(dist[i][j],dist[i][k]+dist[k][j]);
                    }
                }
        cout<<res+dp(msk)<<"\n";
    }
}
