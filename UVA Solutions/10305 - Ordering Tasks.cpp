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

int V,E;
vector<vi>adjList;
vector<bool>visited;
stack<int>s;
void clear(){
	adjList.assign(V,vi());
	visited.assign(V,0);
}
void dfs(int u){
	visited[u]=1;
	FOR(i,sz(adjList[u]))if(!visited[adjList[u][i]])dfs(adjList[u][i]);
	s.push(u);
}
int main(){
	fast;
	while(cin>>V>>E){
		if(!V&&!E)break;
		clear();
		while(E--){
			int a,b;cin>>a>>b;a--;b--;
			adjList[a].pb(b);
		}
		FOR(i,V)if(!visited[i])dfs(i);
		bool f=1;
		while(!s.empty())if(f){f=0;int x=s.top();s.pop();cout<<x+1;}else {int x=s.top();s.pop();cout<<" "<<x+1;}
		cout<<"\n";
	}
	return 0;
}
