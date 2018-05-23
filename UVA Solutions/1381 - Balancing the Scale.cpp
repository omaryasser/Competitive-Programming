/*
  get the # of 4-numbers that has sum X.
  count the number of disjoint 4-numbers having the same sum.
  count the number of disjoing 8-numbers satisfying the last statement.
*/

#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()

typedef long long ll;

using namespace std;

const int N=16;
int a[N];
map<int,vector<int> >mp4;
ll cnt[1<<16];
int p[4];

string to(int n){
	string s="";
	int c=0;
	while(c++<16||n){
		s+=to_string(n%2);
		n/=2;
		// printf("%d %d\n", n,c);
	}
	return s;
}
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
	int tc=0;
	while(++tc){
		scanf("%d",a);
		if(!a[0])break;
		memset(cnt,0,sizeof cnt);
		mp4.clear();
		REP1(i,1,N)scanf("%d",a+i);
		// REP(i,N)printf("%d\n", a[i]);
		REP(i,1<<N){
			if(__builtin_popcount(i)==4){
				int b=0;
				REP(j,N)if((i>>j)&1){
					p[b++]=a[j];
				}
				sort(p,p+4);
				do{
					mp4[p[0]*4+p[1]*3+p[2]*2+p[3]].pb(i);
				}while(next_permutation(p,p+4));
				// printf("%d\n", c);
			}
		}
		for(auto p:mp4){
			vector<int>msks=p.se;
			// for(int i:msks)
				// printf("%s\n", to(i).c_str());
			// printf("\\\\\\\\\\\\\\\\n");
			REP(i,msks.size())
				REP(j,msks.size()){
					
					if((msks[i]&msks[j])==0){
						// printf("%s %s\n", to(msks[i]).c_str(),to(msks[j]).c_str());
						cnt[msks[i]|msks[j]]++;
					}
				}
		}
		ll r=0;
		REP(i,1<<N)
			if(__builtin_popcount(i)==8)
				r+=cnt[i]*cnt[((1<<16)-1)^i];
		printf("Case %d: %lld\n",tc,r/8);
	}   	
   	

    return 0;
}
