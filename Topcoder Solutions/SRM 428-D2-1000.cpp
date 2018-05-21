/*
For every index I check wether I should put 'a' or 'z' by seeing the number of ways that the remaining As, Zs will make after
putting 'a' and the if it is < k then I should put z.

To get the number of ways of putting n As, m Bs, it is equal to (n+m)!/(n!*m!) this number can get so big.
so I will maintain a vector of numbers from 1 -> n+m, which are (n+m)! and then remove n! and m! from it, and then
multiply until I reach a number bigger than MAX_K so that I can return MAX_K+1 safely.
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

ll MAX=1000000000;

class TheDictionary{
public:
	void rem(vector<int>&v,int r){
		REP1(i,2,r+1){
			int tmp=i;
			for(int& x:v){
				if(tmp==1)break;
				REP1(d,2,tmp+1){
					while(tmp%d==0&&x%d==0){
						x/=d;
						tmp/=d;
					}
				}
			}
		}
	}
	ll fac(ll n, ll m){
		vector<int>v;
		REP(i,n+m)v.pb(i+1);
		rem(v,n),rem(v,m);
		ll r=1;
		for(auto x:v){
			r*=x;
			if(r>MAX)return MAX+1;
		}
		return r;
	}
	string find(int n, int m, int k) {
		string r="";
		while(n||m){
			if(!n)r+=string(1,'z'),m--;
			else if(!m)r+=string(1,'a'),n--;
			else{
				ll w=fac(n-1,m);
				if(w>=k){
					r+=string(1,'a'),n--;	
				}else{
					r+=string(1,'z'),m--;
					k-=w;
				}
			}
		}
		return k>1?"":r;
	}
};
