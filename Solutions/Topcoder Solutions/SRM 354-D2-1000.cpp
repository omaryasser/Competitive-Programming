#include <bits/stdc++.h>
#define bug cout<<"HERE"<<endl;
#define FOR(i,l) for(int i=0;i<l;++i)
#define FOR1(i,x,l) for(int i=x;i<l;++i)
using namespace std;

typedef long long ll;
inline int toInt(string s){int v; istringstream sin(s);sin>>v;return v;}
inline ll toll(string s){ll v; istringstream sin(s);sin>>v;return v;}
int mod=1000000007;

class UnsealTheSafe{
	public :
	ll countPasswords(int N){
		vector<ll>dp(10,1);
		vector<ll>dp2(10,0);
		FOR1(i,1,N){
			FOR(k,10)dp2[k]=0;
			dp2[1]+=dp[2]+dp[4];
			dp2[2]+=dp[1]+dp[3]+dp[5];
			dp2[3]+=dp[2]+dp[6];
			dp2[4]+=dp[1]+dp[5]+dp[7];
			dp2[5]+=dp[2]+dp[4]+dp[6]+dp[8];
			dp2[6]+=dp[3]+dp[5]+dp[9];
			dp2[7]+=dp[4]+dp[8]+dp[0];
			dp2[8]+=dp[5]+dp[7]+dp[9];
			dp2[9]+=dp[8]+dp[6];
			dp2[0]+=dp[7];
			FOR(j,10)dp[j]=dp2[j];
		}
		ll res=0;
		FOR(i,10)res+=dp[i];
		return res;
	}
};

