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
#define inp freopen("input.txt", "r", stdin);
#define out freopen("output.txt", "w", stdout);
using namespace std;

typedef long long ll;
typedef vector<int> vi;
inline int toInt(string s){int v; istringstream sin(s);sin>>v;return v;}
inline ll toll(string s){ll v; istringstream sin(s);sin>>v;return v;}
int mod=1000000007;

int V,E,UNVISITED=-1;
vector<vi>adjList;
vector<int>color;
void clear(){
	adjList.assign(V,vi());
	color.assign(V,-1);
}
bool isBipartite(int s){
	color[s]=1;
	queue<int>q;q.push(s);
	while(!q.empty()){
		int u=q.front();q.pop();
		FOR(i,sz(adjList[u])){
			int v=adjList[u][i];
			if(color[v]==UNVISITED){
				color[v]=1-color[u];
				q.push(v);
			}
			else if(color[v]==color[u])return 0;
		}
	}
	return 1;
}
int main(){
	fast;
	while(cin>>V){
		if(!V)break;
		cin>>E;
		clear();
		while(E--){
			int a,b;cin>>a>>b;
			adjList[a].pb(b);
			adjList[b].pb(a);
		}
		bool isBip=1;
		FOR(i,V)if(color[i]==UNVISITED)isBip&=isBipartite(i);
		if(isBip)cout<<"BICOLORABLE.\n";
		else cout<<"NOT BICOLORABLE.\n";
	}
	return 0;
}
