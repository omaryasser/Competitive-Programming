#include <bits/stdc++.h>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define pb(x) push_back(x)
#define mp(x,y) make_pair(x,y)
#define fi first
#define se second

typedef long long ll;

using namespace std;


ll a[14];
ll b[14];
int n=14;
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif

    REP(i,n) scanf("%lld",&a[i]);
    ll mx=0;
    REP(i,n){
      REP(j,n)b[j]=a[j];
      ll go=b[i];
      ll ad=go/14;
      ll r=go%14;
      b[i]=0;
      REP(j,14)b[j]+=ad;
      int j=(i+1)%14;
      while(r--){
        b[j]++;
        j=(j+1)%14;
      }
      ll res=0;
      REP(j,14)
        if(b[j]%2==0)res+=b[j];
      mx=max(mx,res);
    }
    printf("%lld\n", mx);

    return 0;
}