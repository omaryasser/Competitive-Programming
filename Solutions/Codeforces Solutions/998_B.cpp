#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;
int cnt[2];
bool can[101][101];
int dp[101][101];
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n,mx;
    cin>>n>>mx;
    vector<int>a(n);
    REP(i,n)cin>>a[i];
    REP(i,n)REP1(j,i+1,n){
        memset(cnt,0,sizeof cnt);
        REP1(k,i,j+1)cnt[a[k]&1]++;
        if(cnt[0]==cnt[1])can[i][j]=1;
    }
    REP1(i,1,n)REP(hv,mx+1){
        for(int j=i-1;j>=0;j--)
            if((!j||(abs(a[j]-a[j-1]))<=hv)&&can[j][i])
                dp[i][hv]=max(dp[i][hv],1+(j?dp[j-1][hv-abs(a[j]-a[j-1])]:0));
    }
    cout<<dp[n-1][mx]-1<<"\n";
    return 0;
}