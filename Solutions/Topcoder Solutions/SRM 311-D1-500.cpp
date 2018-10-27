/*
  solved with two dp functions.
  -one for counting the number of ways to continue of the remaining digits is d and l is a
  boolean which tells wether the number being constructed is strictly less than the bounding number or not.
  -the other one uses the first one and computes the wanted result.
  
*/
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
const int N=11;
class SumThemAll{
public:
	string s;
	ll mem[N][2][2];
	ll res[N][2][2];
	ll dp(int r,int l,int f){
		if(!r){
			return 1;
		}
		ll& ret=mem[r][l][f];
		if(ret!=-1)return ret;

		ret=0;
		REP1(d,f?1:0,10){
			if(l){
				ret+=dp(r-1,l,0);
			}
			else{
				int cid=(int)s.size()-r;
				if(s[cid]-'0'>d)
					ret+=dp(r-1,1,0);
				else if(s[cid]-'0'==d){
					// printf("%d %d %d\n",d,r,dp(r-1,0,0));
					ret+=dp(r-1,0,0);
					// printf("%lld\n", ret);
				}
			}
		}
		return ret;
	}

	ll dp2(int r,int l,int f){
		if(!r){
			return 0;
		}
		ll& ret=res[r][l][f];
		if(ret!=-1)return ret;

		ret=0;
		REP1(d,f?1:0,10){
			if(l){
				ret+=1ll*d*dp(r-1,l,0)+dp2(r-1,l,0);
			}
			else{
				int cid=(int)s.size()-r;
				if(s[cid]-'0'>d)
					ret+=1ll*d*dp(r-1,1,0)+dp2(r-1,1,0);
				else if(s[cid]-'0'==d){
					// printf("%d %d %d\n",d,r,dp(r-1,0,0));
					ret+=1ll*d*dp(r-1,0,0)+dp2(r-1,0,0);
					// printf("%lld\n", ret);
				}
			}
		}
		return ret;
	}

	ll solve(){
		ll r=0;
		REP1(l,1,s.size()){
			r+=dp2(l,1,1);
		}
		// printf("%lld %s\n",r,s.c_str());
		r+=dp2((int)s.size(),0,1);
		// printf("%lld\n", r);
		return r;
	}

	ll getSum(int lowerBound, int upperBound){
		s=to_string(upperBound);
		memset(mem,-1,sizeof mem);
		memset(res,-1,sizeof res);
		ll a=solve();
		s=to_string(lowerBound-1);
		memset(mem,-1,sizeof mem);
		memset(res,-1,sizeof res);
		ll b=!lowerBound?0:solve();
		return a-b;
	}
};

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    SumThemAll s;
    printf("%lld\n",s.getSum(24660, 308357171	));

    return 0;
}
