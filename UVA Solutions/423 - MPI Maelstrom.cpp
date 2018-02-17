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
#define fast 	ios_base::sync_with_stdio(0); cin.tie(0);
#define sz(v) (int)v.size()
#define mp(x,y) make_pair(x,y)
#define inp freopen("input.txt", "r", stdin);
#define out freopen("output.txt", "w", stdout);
using namespace std;

typedef long long ll;
typedef vector<int> vi;
inline int toInt(string s){int v; istringstream sin(s);sin>>v;return v;}
inline ll toll(string s){ll v; istringstream sin(s);sin>>v;return v;}
int mod=1000000007;

const int MAX=101;
int V;
ll INF=1e17;
ll adjMat[MAX][MAX];
int main(){
	fast;
	cin>>V;
	FOR(i,V)FOR(j,V)adjMat[i][j]=(i==j)?0:INF;
	FOR(i,V)FOR(j,i){
		string tmp;cin>>tmp;
		if(tmp[0]<='9'&&tmp[0]>='0')adjMat[i][j]=adjMat[j][i]=toll(tmp);
	}
	FOR(k,V)FOR(i,V)FOR(j,V)adjMat[i][j]=min(adjMat[i][j],adjMat[i][k]+adjMat[k][j]);
	ll res=-1;
	FOR1(i,1,V)res=max(res,adjMat[0][i]);
	cout<<res<<"\n";
}
