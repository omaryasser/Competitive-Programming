#include <bits/stdc++.h>
#define pb(x) push_back(x)
#define bug cout<<"HERE"<<endl;
#define SSTR( x ) static_cast< std::ostringstream & >( \
        ( std::ostringstream() << std::dec << x ) ).str()
#define clr(x,y) memset(x,y,sizeof(x))
#define all(v) v.begin(),v.end()
#define FOR(i,l) for(int i=0;i<l;++i)
#define FOR1(i,s,l) for(int i=s;i<l;++i)
#define FOR2(i,s) for(int i=s;i>=0;--i)
#define fast ios_base::sync_with_stdio(0); cin.tie(0);
#define sz(v) (int)v.size()
#define mp(x,y) make_pair(x,y)
#define inp freopen("input.txt", "r", stdin);
#define out freopen("output.txt", "w", stdout);
#define PI 3.14159265358979323846
using namespace std;

typedef long long ll;
typedef vector<int> vi;
inline int toInt(string s){int v; istringstream sin(s);sin>>v;return v;}
inline ll toll(string s){ll v; istringstream sin(s);sin>>v;return v;}
int mod=1000000007;

int V;
vector<bool>isD;
vector<vi>adjList;
vector<int>memo;
void clear(){adjList.assign(V,vi());isD.assign(V,false);memo.assign(V,-1);}
int dp(int idx){
	int &ret=memo[idx];
	if(ret!=-1)return ret;
	if(sz(adjList[idx])==0)return 1;
	ret=0;
	FOR(i,sz(adjList[idx]))ret+=dp(adjList[idx][i]);
	return ret;
}
int main(){
	fast
	bool f=1;
	while(cin>>V){
		clear();
		FOR(i,V){
			int n;cin>>n;
			if(!n)isD[i]=1;
			while(n--){
				int v;cin>>v;
				adjList[i].pb(v);
			}
		}
		if(f)f=0;
		else cout<<"\n";
		cout<<dp(0)<<"\n";
	}
	return 0;
}
