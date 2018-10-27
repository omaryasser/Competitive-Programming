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

const int N=51;
int n;
int mem[N][N];
int a[N];

class MailArchiving{
public:
	int dp(int s, int e){
		if(e<s)return 0;
		if(s==e)return 1;
		int &ret=mem[s][e];
		if(ret!=-1)return ret;
		ret=1+dp(s+1,e);
		REP1(i,s+1,e+1){
			if(a[i]==a[s]){
				ret=min(ret,dp(s+1,i-1)+dp(i,e));
			}
		}
		return ret;
	}
	int minSelections(vector <string> destFolders){
		memset(a,-1,sizeof a);
		memset(mem,-1,sizeof mem);	
		n=(int)destFolders.size();
		int id=0;
		REP(i,n){
			if(a[i]==-1){
				a[i]=id++;
				REP1(j,i+1,n)
					if(destFolders[j]==destFolders[i])
						a[j]=a[i];
			}
		}
		// printf("%d\n",dp(6,n-1));
		return dp(0,n-1);
	}
};
