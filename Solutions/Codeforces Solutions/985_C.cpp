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

int n,k,l;
const int N=100001;
int a[N];
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    scanf("%d%d%d",&n,&k,&l);
    REP(i,n*k)scanf("%d",a+i);
    sort(a,a+n*k);
    int mx=a[0]+l;
    ll s=a[0];
    int lst=0;
    REP(i,n*k)if(a[i]<=mx)lst=i;
    if(lst+1<n){
    	printf("0\n");
    	return 0;
    }
    while(n*k-lst-1<k-1)lst--;
    REP(i,n-1){
    	s+=a[lst];
    	lst--;
    	while(n*k-lst-1<(i+2)*(k-1)+i+1)lst--;
    }
    printf("%lld\n", s);
    return 0;
}