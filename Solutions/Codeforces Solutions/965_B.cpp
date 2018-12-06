#include <bits/stdc++.h>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second

typedef long long ll;

using namespace std;

int n,k;
char a[101][101];
int cum[101];
int res[101][101];
int main() {
	#ifndef ONLINE_JUDGE
	freopen("in.txt","r",stdin);
	#endif

	scanf("%d%d",&n,&k);
	REP(i,n)scanf("%s",a[i]);
	REP(i,n){
		memset(cum,0,sizeof cum);
		REP(j,n){
			if(a[i][j]=='.'){
				cum[j]++;
				if(j)cum[j]+=cum[j-1];
			}
			if(cum[j]>=k){
				for(int i2=j;i2>=j-k+1;i2--)
					res[i][i2]++;
			}
		}
	}
	REP(j,n){
		memset(cum,0,sizeof cum);
		REP(i,n){
			if(a[i][j]=='.'){
				cum[i]++;
				if(i)cum[i]+=cum[i-1];
			}
			if(cum[i]>=k){
				for(int i2=i;i2>=i-k+1;i2--)
					res[i2][j]++;
			}
		}	
	}
	int r=0,c=0;
	REP(i,n)REP(j,n)if(res[i][j]>res[r][c])r=i,c=j;
	printf("%d %d\n",r+1,c+1);
}