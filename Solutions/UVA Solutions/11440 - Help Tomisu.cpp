/*
We need to find the # of numbers coprime with M! (call it X), the there is X numbers included in the answer from 2->M!,
also there are other X numbers included in the answer from M! + 1, 2M! , and so on. So the result will be N!/M! * (X)
*/
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

const int MOD=100000007;
int n,m;
const int N=1e7+3;
bool isPrime[N];
vector<int>primes;
int lst;
ll fac[N];
ll phii[N];
ll invv[N];

ll power(int a,int b){
    ll r=1;
    while(b){
        if(b&1)r=r*a%MOD;
        a=(int)(1ll*a*a%MOD);
        b>>=1;
    }
    return r;
}

ll inv(int p){
    return power(p,MOD-2);
}

void phi(){
    ll res=1;
    phii[1]=1;
    REP1(i,2,N){
            if(isPrime[i]){
                res*=(1-inv(i));
                res%=MOD;
                res+=MOD;
                res%=MOD;
            }
        phii[i]=res;
    }
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    REP(i,N)isPrime[i]=1;
    for(int i=2;i*i<N;i++)
        if(isPrime[i])
            for(int j=i*i;j<N;j+=i)
                isPrime[j]=0;

    REP1(i,2,N)if(isPrime[i])primes.pb(i);
    fac[0]=1;
    REP1(i,1,N)fac[i]=fac[i-1]*i%MOD;
    REP(i,N)invv[i]=inv(fac[i]);
    phi();
    while(1){
        cin>>n>>m;
        if(!n)break;
        ll res=(fac[n]*invv[m]%MOD)*(fac[m]*phii[m]%MOD)%MOD;
        res-=1;
        res%=MOD;
        res+=MOD;
        res%=MOD;
        cout<<res<<"\n";
    }
}
