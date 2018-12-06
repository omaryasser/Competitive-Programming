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

const int N=100001,INF=100001+100;
int d[N][101];
vector<int>G[N];
int n,m,k,s;
int a[N];
vector<int>plcs[101];

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
        
    scanf("%d%d%d%d",&n,&m,&k,&s);
    REP(i,n){
        scanf("%d",a+i);
        a[i]--;
        plcs[a[i]].pb(i);
    }
    while(m--){
        int a,b;
        scanf("%d%d",&a,&b);
        G[--a].pb(--b);
        G[b].pb(a);
    }
    REP(i,N)REP(j,101)d[i][j]=INF;
    REP(i,k){
        queue<int>q;
        for(int p:plcs[i])
            q.push(p),d[p][i]=0;
        while((int)q.size()){
            int u=q.front();q.pop();
            for(int x:G[u])
                if(d[x][i]==INF){
                    d[x][i]=d[u][i]+1;
                    q.push(x);
                }
        }
    }

    REP(i,n){
        vector<int>dd;
        REP(j,k)dd.pb(d[i][j]);
        sort(all(dd));
        int ss=0;
        REP(j,s)ss+=dd[j];
        printf("%d\n", ss);
    }
    
    return 0;
}