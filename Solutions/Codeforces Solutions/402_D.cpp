#include <bits/stdc++.h>

#define REP(i,n) for(int i=0;i<n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second

using namespace std;

int n,m;
int a[5001];
unordered_set<int>good;
const int SQRT=sqrt(1e9)+2;
bool isPrime[SQRT];
int main() {
	#ifndef ONLINE_JUDGE
	freopen("in.txt","r",stdin);
	#endif

	scanf("%d%d",&n,&m);
	REP(i,n)scanf("%d",&a[i]);
	REP(i,m){
		int x;
		scanf("%d",&x);
		good.insert(x);
	}
	vector<int>primes;
	REP(i,SQRT)isPrime[i]=1;
	for(int i=2;i<SQRT;i++)
		if(isPrime[i])
			for(int j=i*i;j<SQRT;j+=i)
				isPrime[j]=0;
	for(int i=2;i<SQRT;i++)
		if(isPrime[i])
			primes.pb(i);

	vector<unordered_map<int,int> >p=vector<unordered_map<int,int> >(n,unordered_map<int,int>());
	REP(i,n){
		for(int j:primes){
			while(a[i]%j==0)
				a[i]/=j,p[i][j]++;
		}
		if(a[i]!=1)
			p[i][a[i]]++;
	}

	int r=0;
	REP(i,n){
		for(auto j:p[i]){
			r+=(j.se)*(good.find(j.fi)==good.end()?1:-1);
		}
	}
	int l=r;
	unordered_map<int,int>com=p[0];
	vector<int>comon;
	REP(i,n){
		unordered_map<int,int>nw;
		if(i){
			for(auto j:com)
				if(p[i][j.fi]!=0)
					nw[j.fi]=min(p[i][j.fi],j.se);
			com=nw;
		}
		int h=0;
		for(auto j:com)
			h+=(j.se)*(good.find(j.fi)==good.end()?-1:1);
		comon.pb(h);
	}
	int fin=0;
	for(int i=n-1;i>=0;i--){
		int h=comon[i];
		if((h-fin)*(i+1)>0){
			l+=(h-fin)*(i+1);
			fin+=(h-fin);
		}
	}
	printf("%d\n",l);
}