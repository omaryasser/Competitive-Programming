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

int n,m;
vector<string>grid;
struct Way{
	int x,y,t;
	Way(int xx,int yy,int tt):x(xx),y(yy),t(tt){}
	Way(){}
	bool operator<(Way other)const{return t<other.t;}
};
int main(){
	fast;
	while(1){
		cin>>m>>n;
		if(!n&&!m)break;
		grid.clear();
		FOR(i,n){string x;cin>>x;grid.pb(x);}
		int sX,sY,dX,dY;
		FOR(i,n)FOR(j,m)if(grid[i][j]=='S')sX=i,sY=j;else if(grid[i][j]=='D')dX=i,dY=j;
		int INF=1e9;
		int dist[n][m];FOR(i,n)FOR(j,m)dist[i][j]=INF;
		dist[sX][sY]=0;
		multiset<Way>q;q.insert(Way(sX,sY,0));
		int res=0;
		int dx[]={0,0,1,-1};
		int dy[]={1,-1,0,0};
		while(!q.empty()){
			Way cur=*q.begin();q.erase(q.begin());
			int x=cur.x,y=cur.y,t=cur.t;
			if(x==dX&&y==dY){res=t;break;}
			if(t>dist[x][y])continue;
			FOR(i,4){
				int nx=x+dx[i],ny=y+dy[i];
				if(nx>=0&&ny>=0&&nx<n&&ny<m&&grid[nx][ny]!='X'){
					if(dist[nx][ny]>t+grid[nx][ny]-'0'||grid[nx][ny]=='D'){
						dist[nx][ny]=(grid[nx][ny]=='D')?t:t+grid[nx][ny]-'0';
						q.insert(Way(nx,ny,dist[nx][ny]));
					}
				}
			}
		}
		cout<<res<<"\n";
	}
	return 0;
}
