#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int MOD=998244353;
const int N=1e6+2;
int pw[N];

int main(){
    ios_base::sync_with_stdio(0);cin.tie(0);
    pw[0]=1;
    REP1(i,1,N)pw[i]=1ll*pw[i-1]*2%MOD;
    ll res=0;
    int n;
    cin>>n;
    REP(i,n){
        ll x;
        cin>>x;
        res+=x*(pw[n-i-1]+((1ll*n-i-1)*pw[n-i-2]%MOD))%MOD;
        if(res>=MOD)res-=MOD;
    }
    cout<<res<<"\n";
}