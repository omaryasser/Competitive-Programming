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


int n;
const int N=100005;
int g[256];
int a[N];
int k;

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
  
    scanf("%d%d",&n,&k);
    vector<int>r;
    REP(i,n)scanf("%d",&a[i]);
    memset(g,-1,sizeof g);

    REP(i,n){
      if(g[a[i]]!=-1){
        r.pb(g[a[i]]);
      }else{
        int m=a[i];
        for(int j=a[i];j>=max(0,a[i]-k+1);j--){
          if(g[j]==-1||a[i]-g[j]<=k-1){
            m=j;
          }
        }
        r.pb(m);
        for(int j=a[i];j>=m;j--)
          g[j]=m;
      }
    }
    REP(i,n){
      if(i)printf(" ");
      printf("%d", r[i]);
    }
    printf("\n");

    
    return 0;
}