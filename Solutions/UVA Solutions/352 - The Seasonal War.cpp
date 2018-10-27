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
#define PI 3.14159265358979323846
using namespace std;

typedef long long ll;
typedef vector<int> vi;
inline int toInt(string s){int v; istringstream sin(s);sin>>v;return v;}
inline ll toll(string s){ll v; istringstream sin(s);sin>>v;return v;}
int mod=1000000007;

int n;
vector<string>s;
bool visited[25][25];
int dx[]={0,0,1,-1,1,-1,1,-1};
int dy[]={1,-1,0,0,1,1,-1,-1};
bool valid(int i,int j){return i>=0&&j>=0&&i<n&&j<n&&s[i][j]=='1'&&!visited[i][j];}
void flood(int i,int j){
	visited[i][j]=1;
	FOR(k,8){
		int ni=i+dx[k],nj=j+dy[k];
		if(valid(ni,nj))flood(ni,nj);
	}
}
int main(){
	fast;
	int num=1;
	while(cin>>n){
		s.clear();
		FOR(i,n){string x;cin>>x;s.pb(x);}
		clr(visited,false);
		int cnt=0;
		FOR(i,n)FOR(j,n)if(s[i][j]=='1'&&!visited[i][j])flood(i,j),cnt++;
		cout<<"Image number "<<num++<<" contains "<<cnt<<" war eagles.\n";
	}
	return 0;
}
