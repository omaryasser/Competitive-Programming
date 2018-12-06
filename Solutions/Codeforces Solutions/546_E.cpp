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

class edmondkarp{
public:
    const int INF=1e8;
    int n;
    int res[301][301];
    int p[301];
    vector<int>adj[301];
    edmondkarp(int n){
        this->n=n;
        
    }
    void add_edge(int u,int v,int c){
        res[u][v]+=c;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    int aug(int u,int f){
        if(p[u]==u)return f;
        f=min(f,aug(p[u],min(f,res[p[u]][u])));
        res[p[u]][u]-=f;
        res[u][p[u]]+=f;
        return f;
    }
    int max_flow(int S,int T){
        int f=0;
        while(1){
            for(int i=0;i<n;i++)p[i]=-1;
            queue<int>q;
            p[S]=S;q.push(S);
            while((int)q.size()){
                int u=q.front();q.pop();
                for(int v:adj[u])
                    if(res[u][v]>0&&p[v]==-1){
                        q.push(v);
                        p[v]=u;
                    }
            }
            if(p[T]==-1)return f;
            f+=aug(T,INF);
        }
    }
};

int n,m;
const int N=101;
int a[N],b[N];

int left(int c){return 1+c;}
int right(int c){return 1+n+c;}
int src(){return 0;}
int sink(){return 2*n+1;}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif     

    scanf("%d%d",&n,&m);
    edmondkarp e(n*2+2);
    REP(i,n)scanf("%d",a+i);
    REP(i,n)scanf("%d",b+i);
    while(m--){
        int u,v;
        scanf("%d%d",&u,&v);
        --u,--v;
        e.add_edge(left(u),right(v),a[u]);
        e.add_edge(left(v),right(u),a[v]);
    }
    int mf=0,s1=0;
    REP(i,n)e.add_edge(src(),left(i),a[i]);
    REP(i,n)e.add_edge(right(i),sink(),b[i]),mf+=b[i],s1+=a[i];
    REP(i,n)e.add_edge(left(i),right(i),a[i]);
    if(e.max_flow(src(),sink())!=mf||mf!=s1){
        printf("NO\n");
        return 0;
    }else{
        printf("YES\n");
        REP(i,n){
            REP(j,n){
                if(j)printf(" ");
                printf("%d", e.res[right(j)][left(i)]);
            }
            printf("\n");
        }
    }

    return 0;
}