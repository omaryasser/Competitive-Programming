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

int n,m;
const int N=20;
int a[N][N];
char str[N][N];
int mn[N];
int s[N][N];
int smsk[N][N];
int dp[1<<N];

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
  
    scanf("%d%d",&n,&m);    
    REP(i,n)scanf("%s",str[i]);
    REP(i,n)REP(j,m)scanf("%d",&a[i][j]);
    REP(i,n){
      mn[i]=a[i][0];
      REP(j,m)mn[i]=min(mn[i],a[i][j]);
    }
    REP(i,n){
      REP(j,m){
        int mx=0;
        int msk=0;
        REP(i2,n){
          if(str[i][j]==str[i2][j]){
            msk|=1<<i2;
            s[i][j]+=a[i2][j];
            mx=max(a[i2][j],mx);
          }
        }
        s[i][j]-=mx;
        smsk[i][j]=msk;
      }
    }

    REP1(msk,1,1<<n){
      int rw;
      REP(j,n)if((msk>>j)&1){
        rw=j;
        break;
      }
      dp[msk]=mn[rw]+dp[msk^(1<<rw)];
      REP(j,m){
        dp[msk]=min(dp[msk],s[rw][j]+dp[msk&(~smsk[rw][j])]);
      }
    }

    printf("%d\n", dp[(1<<n)-1]);

    return 0;
}