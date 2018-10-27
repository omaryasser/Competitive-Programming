#include <bits/stdc++.h>
#define bug cout<<"HERE"<<endl;
#define FOR(i,l) for(int i=0;i<l;++i)
#define FOR1(i,x,l) for(int i=x;i<l;++i)
#define pb(x) push_back(x)
#define sz(x) (int)x.size()
using namespace std;

typedef long long ll;
inline int toInt(string s){int v; istringstream sin(s);sin>>v;return v;}
inline ll toll(string s){ll v; istringstream sin(s);sin>>v;return v;}
int mod=1000000007;

class NinePuzzle{
	public :
	int getMinimumCost(string init, string goal){
		vector<int>cnt1(26,0),cnt2(26,0);
		FOR(i,(int)init.length()){
			if(init[i]!='*')cnt1[init[i]-'A']++;
			if(goal[i]!='*')cnt2[goal[i]-'A']++;
		}
		int cnt=0;
		FOR(i,26)if(cnt2[i]>cnt1[i])cnt+=abs(cnt1[i]-cnt2[i]);
		return cnt;
	}
};

