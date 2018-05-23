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
const int N=101;
int a[N];
int MOD=1e9+7;


int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    int tc;
    scanf("%d",&tc);
    REP1(i,1,tc+1){
      scanf("%d",&n);
      REP(i,n)scanf("%d",&a[i]);
      sort(a,a+n);
      ll r=1;
      REP(i,n){
        r*=a[i]-i;
        r%=MOD;
      }
      printf("Case %d: %d\n", i,(int)r);
    }
    
    return 0;
}
