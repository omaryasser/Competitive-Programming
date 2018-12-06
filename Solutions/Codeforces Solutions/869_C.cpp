#include <bits/stdc++.h>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define mp(x,y) make_pair(x,y)
#define fi first
#define se second

typedef long long ll;

using namespace std;



const int N=5001;
int fac[N];
int MOD=998244353;
int C[N][N];

int powerr(ll a,ll b){
	ll r=1;
	while(b){
		if(b&1ll)r=r*a%MOD;
		b>>=1ll;
		a=a*a%MOD;
	}
	return (int)r;
}
int inv(int n){
	return powerr(n,MOD-2);
}
int nPr(int n,int k){
	return (int)(1ll*fac[n]*inv(fac[n-k])%MOD);
}
int nCr(int n,int k){
	if(C[n][k]!=-1)return C[n][k];
	if(k==0)return 1;
	if(n<k)return 0;
	return C[n][k]=(1ll*nCr(n-1,k-1)+nCr(n-1,k))%MOD;
}
ll ways(int a,int b){
	ll r=0;
	REP(i,a+1){
		if(i<=b)r+=1ll*nCr(a,i)*nPr(b,i)%MOD;
		r%=MOD;
	}
	return r;
}
int main() {
     #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif

    memset(C,-1,sizeof C);
    fac[0]=1;
    REP1(i,1,N)fac[i]=1ll*i*fac[i-1]%MOD;
	int a,b,c;
	scanf("%d%d%d",&a,&b,&c);
	ll all=ways(a,b);
	all*=ways(a,c);
	all%=MOD;
	all*=ways(b,c);
	all%=MOD;
	printf("%lld\n", all);
    return 0;
}