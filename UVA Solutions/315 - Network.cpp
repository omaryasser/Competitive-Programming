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

int V,E,UNVISITED=-1,dfs_counter,dfs_root,cntRootChildren;
vi dfs_num,dfs_low,dfs_parent;
vector<vi>adjList;
vector<bool>isArtc;
void clear(){
	dfs_counter=0;
	dfs_num.assign(V,UNVISITED);
	dfs_low.assign(V,UNVISITED);
	dfs_parent.assign(V,UNVISITED);
	adjList.assign(V,vi());
	isArtc.assign(V,false);
}
void articulationPointsAndBridges(int u){
	dfs_num[u]=dfs_low[u]=dfs_counter++;
	FOR(i,sz(adjList[u])){
		int v=adjList[u][i];
		if(dfs_num[v]==UNVISITED){
			dfs_parent[v]=u;
			if(u==dfs_root)cntRootChildren++;

			articulationPointsAndBridges(v);

			if(dfs_low[v]>=dfs_num[u])isArtc[u]=1;
//			if(dfs_low[v]>dfs_num[u])cout<<"edge "<<u<<" --> "<<v<<" is a bridge\n";
			dfs_low[u]=min(dfs_low[u],dfs_low[v]);
		}
		else if(v!=dfs_parent[u])
			dfs_low[u]=min(dfs_low[u],dfs_num[v]);
	}
}
int main(){
//	fast;
	string line;
	while(1){
		getline(cin,line);
		istringstream ss(line);
		ss>>V;
		if(!V)break;
		clear();
		while(1){
			getline(cin,line);
			istringstream ss(line);
			int s,e;ss>>s;
			if(!s)break;
			s--;
			while(ss>>e){
				e--;
				adjList[s].pb(e);
				adjList[e].pb(s);
			}
		}
		FOR(i,V)if(dfs_num[i]==UNVISITED){
			dfs_root=i;
			cntRootChildren=0;
			articulationPointsAndBridges(i);
			isArtc[dfs_root]=(cntRootChildren>1);
		}
		int cnt=0;
		FOR(i,V)if(isArtc[i])cnt++;
		cout<<cnt<<"\n";
	}
}
