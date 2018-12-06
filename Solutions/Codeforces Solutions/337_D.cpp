#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define double long double
#define mk(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

const int N=100001;
vector<int>T[N];
vector<int>fu[N];
vector<int>mxL[N];
vector<int>mxR[N];
int und[N];
int n,d;
bool b[N];
int r=0;
int dfs(int u=0,int p=-1){
  int fur=b[u]?0:-N-N;
  for(auto v:T[u])
    if(v!=p){
      int fher=dfs(v,u);
      if(fher!=-1)fur=max(fur,fher+1);
      fu[u].pb(fher+1);
    }
  mxL[u].assign(fu[u].size(),0);
  mxR[u].assign(fu[u].size(),0);
  if(fu[u].size())mxL[u][0]=-N-N;
  if(fu[u].size())mxR[u][fu[u].size()-1]=-N-N;
  REP1(i,1,fu[u].size())
    mxL[u][i]=max(mxL[u][i-1],fu[u][i-1]);
  for(int i=fu[u].size()-2;i>=0;i--)
    mxR[u][i]=max(mxR[u][i+1],fu[u][i+1]);
  // if(!u)printf("%d %d\n", mxR[u][0],fu[u][1]);
  return und[u]=fur;
}

void dfs2(int u=0,int p=-1,int f=-N-N){
  if(b[u])f=max(f,0);
  if(max(f,und[u])<=d){
    // printf("%d %d %d\n", u,und[u],f);
    r++;
  }
  int i=0;
  for(int v:T[u])
    if(v!=p){
      dfs2(v,u,max(f+1,1+max(mxL[u][i],mxR[u][i])));
      i++;
    }
}
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    int m;
    scanf("%d%d%d",&n,&m,&d);
    REP(i,m){
      int x;
      scanf("%d",&x);
      b[x-1]=1;
    }
    REP(i,n-1){
      int u,v;
      scanf("%d%d",&u,&v);
      T[--u].pb(--v);
      T[v].pb(u);
    }
    dfs();
    dfs2();
    printf("%d\n",r);
    return 0;
}