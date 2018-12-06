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


class BipGraph{
    int n,m;
    list<int>*adj;
    int*pairU,*pairV,*dist;
    int NIL=0,INF=1e9;
public:
    bool bfs(){
        queue<int> Q;
        for(int u=1;u<=n;u++){
            if(pairU[u]==NIL){
                dist[u]=0;
                Q.push(u);
            }
            else dist[u]=INF;
        }
        dist[NIL]=INF;
        while(!Q.empty()){
            int u=Q.front();
            Q.pop();
            if(dist[u]<dist[NIL]){
                list<int>::iterator i;
                for(i=adj[u].begin();i!=adj[u].end();++i){
                    int v=*i;
                    if(dist[pairV[v]]==INF){
                        dist[pairV[v]]=dist[u]+1;
                        Q.push(pairV[v]);
                    }
                }
            }
        }
        return (dist[NIL] != INF);
    }
    bool dfs(int u){
        if (u!=NIL){
            list<int>::iterator i;
            for(i=adj[u].begin();i!=adj[u].end();++i){
                int v=*i;
                if(dist[pairV[v]]==dist[u]+1){
                    if(dfs(pairV[v])== 1){
                        pairV[v]=u;
                        pairU[u]=v;
                        return 1;
                    }
                }
            }
            dist[u]=INF;
            return 0;
        }
        return 1;
    }
    int hopcroftKarp(){
        pairU=new int[n+1];
        pairV=new int[m+1];
        dist=new int[n+1];
        for (int u=0;u<=n;u++)
            pairU[u]=NIL;
        for (int v=0;v<=m;v++)
            pairV[v]=NIL;
        int result=0;
        while(bfs()){
            for(int u=1;u<=n;u++)
                if (pairU[u]==NIL&&dfs(u)){
                    result++;
                }
        }
        return result;
    }
    BipGraph(int n,int m){
        this->m =m;
        this->n =n;
        adj=new list<int>[n+1];
    }
    void addEdge(int u,int v){
        adj[u].push_back(v);
    }
};
 

const int N=101;
int n,m;
vector<pair<int,int> > a[N];

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    scanf("%d%d",&n,&m);
    BipGraph b(101*31,101*31);
    int ev=1,od=1;
    REP(j,n){
        int x;
        scanf("%d",&x);
        for(int i=2;i*i<=x;i++){
            while(x%i==0){
                a[j].pb(mp(i,j&1?od++:ev++));
                x/=i;
            }
        }
        if(x!=1)a[j].pb(mp(x,j&1?od++:ev++));
    }
    int c=0;
    REP(i,m){
        int x,y;
        scanf("%d%d",&x,&y);
        x--,y--;
        if(x&1)swap(x,y);
        for(auto p:a[x])
            for(auto p2:a[y])
                if(p.fi==p2.fi){
                    b.addEdge(p.se,p2.se);
                }
    }
    printf("%d\n", b.hopcroftKarp());

    return 0;
}