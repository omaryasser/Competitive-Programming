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

const int N=301;
int a[N],g[N];
int n;
int MOD=1e9+7;
int grps[N];
int dp[N][N];
int fact[N];
int invv[N];

void ADD (int& a,int b){
  a = (a+b>=MOD)?a+b-MOD:a+b;
}
int MULT(int a,int b){
  return (int)(1ll*a*b%MOD);
}

int power(int a,int b){
  int r=1;
  while(b){
    if(b&1)r=MULT(r,a);
    a=MULT(a,a);
    b>>=1;
  }
  return r;
}

int inv(int a){return power(a,MOD-2);}

int C(int n,int k){
  if(n<k)return 0;
  return MULT(MULT(fact[n],invv[k]),invv[n-k]);
}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    fact[0]=1;
    invv[0]=inv(1);
    REP1(i,1,N)fact[i]=(int)(1ll*i*fact[i-1]%MOD),invv[i]=inv(fact[i]); 
    scanf("%d",&n);
    REP(i,n)scanf("%d",a+i);
    memset(g,-1,sizeof g);
    int grp=0;
    REP(i,n)if(g[i]==-1){
      g[i]=grp++;
      REP1(j,i+1,n){
        ll x=1ll*a[i]*a[j];
        ll sq=sqrt(x);
        while(sq*sq<x)sq++;
        while(sq*sq>x)sq--;
        if(sq*sq==x)g[j]=g[i];
      }
    }
    REP(i,n){
      grps[g[i]]++;
    }
    dp[0][grps[0]-1]=fact[grps[0]];

    int tot=grps[0];
    REP(i,grp-1){
      REP(b,tot+1){
        REP1(s,1,min(grps[i+1],tot+1)+1){
          REP(e,min(b,s)+1){
            ADD(dp[i+1][b-e+grps[i+1]-s],
              MULT(MULT(MULT(MULT(dp[i][b],fact[grps[i+1]]),C(grps[i+1]-1,s-1)),C(b,e)),C(tot+1-b,s-e)));
          }
        }
      }
      tot+=grps[i+1];
    }
    printf("%d\n",dp[grp-1][0]);
    return 0;
}