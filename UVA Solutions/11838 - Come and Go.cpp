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

int V,E,dfsCounter,UNVISITED=-1,num_SCC;
vi dfs_num,dfs_low;
vector<vi>adjList;
vector<bool>visited;
stack<int>s;
void clear(){
	adjList.assign(V,vi());
	dfsCounter=0;
	dfs_num.assign(V,UNVISITED);
	dfs_low.assign(V,UNVISITED);
	visited.assign(V,false);
	num_SCC=0;
}
void tarjanSCC(int u){
	dfs_num[u]=dfs_low[u]=dfsCounter++;
	s.push(u);
	visited[u]=true;
	FOR(i,sz(adjList[u])){
		int v=adjList[u][i];
		if(dfs_num[v]==UNVISITED)tarjanSCC(v);
		if(visited[v])
			dfs_low[u]=min(dfs_low[u],dfs_low[v]);
	}
	if(dfs_low[u]==dfs_num[u]){
		num_SCC++;
		while(1){
			int v=s.top();s.pop();
			visited[v]=0;
//			cout<<v<<" ";
			if(u==v)break;
		}
//		cout<<"\n";
	}
}
int main(){
//	fast;
	while(1){
		cin>>V>>E;
		if(!V&&!E)break;
		clear();
		while(E--){
			int a,b,c;cin>>a>>b>>c;a--;b--;
			adjList[a].pb(b);
			if(c==2)adjList[b].pb(a);
		}
		FOR(i,V)if(dfs_num[i]==UNVISITED)tarjanSCC(i);
		if(num_SCC==1)cout<<"1\n";
		else cout<<"0\n";
	}
}
