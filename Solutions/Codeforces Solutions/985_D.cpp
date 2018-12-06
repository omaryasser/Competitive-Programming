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

ll n,h;
ll sum(ll a,ll b){
	return b*(b+1)/2-(a==0?0:a*(a-1)/2);
}
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    scanf("%lld%lld",&n,&h);
    h=min(h,(ll)2e9);
    ll lo1=1,hi1=h,b=h;
    while(lo1<=hi1){
    	ll m=lo1+hi1>>1;
    	if(m*(m+1)/2>=n){
    		b=m;
    		hi1=m-1;
    	}else lo1=m+1;
    }

    if(b!=h){
    	printf("%lld\n", b);
    	return 0;
    }

    n-=h*(h+1)/2;
    ll lo2=0,hi2=2E9,b2=2e9;
    while(lo2<=hi2){
    	ll m=lo2+hi2>>1;
    	ll md;
    	if(m&1ll){
    		md=sum(h,h+m/2)+sum(h+1,h+m/2);
    	}else
    		md=sum(h,h+m/2)+sum(h+1,h+m/2-1);
    	if(md>=n){
    		b2=m;
    		hi2=m-1;
    	}else lo2=m+1;
    }
    printf("%lld\n", h+b2);
    return 0;
}