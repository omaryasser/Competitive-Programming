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

string tob(ll x){
    string xx="";
    while(x){
        xx=to_string(x%2)+xx;
        x/=2;
    }
    return xx;
}

ll dp[71];

ll solve(ll x){
    if(x<=2)return 0;
    memset(dp,0,sizeof dp);
    int l=tob(x).length();
    REP1(i,1,l)if(l%i==0){
        ll X=0;
        REP(j,l/i)
            X=(X<<i)+(x>>(l-i));
        dp[i]=X<=x;
        dp[i]+=(x>>(l-i))-(1ll<<(i-1));
    }
    REP1(i,1,l)if(l%i==0){
        REP1(j,1,i)if(i%j==0)dp[i]-=dp[j];
    }
    ll r=0;
    REP(i,71)r+=dp[i];
    ll lg=1;
    while(lg<=x)lg<<=1ll;
    lg>>=1ll;
    return r+solve(lg-1);
}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif

    ll l,r;
    scanf("%lld%lld",&l,&r);
    printf("%lld\n", solve(r)-(l>1?solve(l-1):0));    

    return 0;
}