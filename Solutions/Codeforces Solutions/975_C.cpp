#include <bits/stdc++.h>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define pb(x) push_back(x)
#define mp(x,y) make_pair(x,y)
#define fi first
#define se second

typedef long long ll;

using namespace std;


int n,q;
const int N=200001;
ll a[N];
vector<ll> acc;

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif

	scanf("%d%d",&n,&q);
	REP(i,n){
		scanf("%d",&a[i]);
		acc.pb(a[i]);
		if(i)acc[i]+=acc[i-1];
	}
	ll s=0;
	while(q--){
		ll x;
		scanf("%lld",&x);
		s+=x;
		int r=n-(upper_bound(acc.begin(),acc.end(),s)-acc.begin());
		if(r==0)r=n;
		printf("%d\n", r);
		if(s>=acc[n-1])s=0;
	}
    return 0;
}