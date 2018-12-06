#include <bits/stdc++.h>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second

typedef long long ll;

using namespace std;


int main() {
	#ifndef ONLINE_JUDGE
	freopen("in.txt","r",stdin);
	#endif

	int n;
	scanf("%d",&n);
	vector<int>divs;

	ll res=1;
	for(int i=2;i*i<=n;i++){
		while(n%i==0){
			n/=i;
			divs.pb(i);
			res*=i;
		}
	}
	if(n!=1)divs.pb(n),res*=n;
	ll rem=res;
	REP(i,divs.size()-1)
		rem/=divs[i],res+=rem;
	printf("%lld\n",res+1);
}