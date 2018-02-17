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

vector<string>s;
int dx[]={0,0,1,-1};
int dy[]={1,-1,0,0};
void flood(int x,int y){
	s[x][y]='#';
	FOR(k,4){
		int nx=x+dx[k],ny=y+dy[k];
		if(nx>=0&&ny>=0&&nx<sz(s)&&ny<s[nx].length()&&s[nx][ny]==' ')flood(nx,ny);
	}
}
int main(){
	fast;
	string line;
	int T;
	getline(cin,line);
	istringstream iss(line);
	iss>>T;
	while(T--){
		s.clear();
		string sep;
		while(1){
			getline(cin,line);
			if(line[0]=='_'){sep=line;break;}
			s.pb(line);
		}
		int sX,sY;
		FOR(i,sz(s))FOR(j,(int)s[i].length())if(s[i][j]=='*')sX=i,sY=j;
		flood(sX,sY);
		FOR(i,sz(s))cout<<s[i]<<"\n";
		cout<<sep<<"\n";
	}
	return 0;
}
