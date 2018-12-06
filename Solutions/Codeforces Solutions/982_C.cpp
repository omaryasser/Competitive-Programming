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

int n;
const int N=100001;
vector<int>T[N];
int r=0;
int dfs(int u=0,int p=-1){
	int sz=0;
	for(auto v:T[u])
		if(v!=p){
			int un=dfs(v,u);
			sz+=un;
			if(!(un&1))r++;
		}
	return sz+1;
}
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
	scanf("%d",&n);
	if(n&1){
		printf("-1\n");
		return 0;
	}
	REP(i,n-1){
		int u,v;
		scanf("%d%d",&u,&v);
		T[--u].pb(--v);
		T[v].pb(u);
	}    

	dfs();
	printf("%d\n",r);
    return 0;
}