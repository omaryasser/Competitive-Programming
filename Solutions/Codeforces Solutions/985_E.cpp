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

int n,k,d;
const int N=500005;
int a[N];
ll ft[N];
void upd2(ll i,ll v){
	while(i<N){
		ft[i]+=v;
		i+=i&-i;
	}
}
void upd(ll a,ll b){
	a++,b++;
	upd2(a,1);
	upd2(b+1,-1);
}

ll quer(ll a){
	a++;
	ll s=0;
	while(a>=1){
		s+=ft[a];
		a-=a&-a;
	}
	return s;
}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    scanf("%d%d%d",&n,&k,&d);
    REP(i,n)scanf("%d",a+i);
    sort(a,a+n);
	upd(0,0);
	int lst=0;
	REP(i,n){
		if(!quer(i))continue;
		while(lst+1<n&&a[lst+1]-a[i]<=d)lst++;
		if(lst-i+1>=k){
			upd(i+k,lst+1);
		}
	}   

	printf("%s\n", quer(n)?"YES":"NO"); 
    
    return 0;
}