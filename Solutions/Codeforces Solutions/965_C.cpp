#include <bits/stdc++.h>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second

typedef long long ll;

using namespace std;


ll c,p,m,d;

int main() {
	#ifndef ONLINE_JUDGE
	freopen("in.txt","r",stdin);
	#endif
	
	scanf("%lld%lld%lld%lld",&c,&p,&m,&d);
	ll l=c/(p*d+1)+1,h=m;
	ll r=0;
	for(int t=1;t<=d;t++){
		// ll a=t==1?c+1:c/((t-1)*p),b=c/((t)*(p)+1);
		// ll w=min(m,t==1?c:(c%((t-1)*p)!=0?a:a-1));

		if(t>1&&1.0*p*(t-1)>1e18)continue;
		ll a=t==1?c+1:c/((t-1)*p+1),b=c/((t)*(p)+1);
		ll w=min(m,t==1?c:a);

		if((1.0*p*t>1e18||w>b)&&(1.0*p*d>1e18||w>=l)&&w<=h){
			// r=max(r,w*(t-1)+min(w,t==1?w:c%((t-1)*p*w)));
			r=max(r,w*(t-1)+w);
		}
	}
	printf("%lld\n", r);
}