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

struct edge{
	int v,u,w;
	edge(int vv,int uu,int ww):v(vv),u(uu),w(ww){}
	edge(){}
	bool operator < (edge other){return w<other.w;}
};
class UnionFind {                                              // OOP style
private:
  vi p, rank, setSize;                       // remember: vi is vector<int>
  int numSets;
public:
  UnionFind(int N) {
    setSize.assign(N, 1); numSets = N; rank.assign(N, 0);
    p.assign(N, 0); for (int i = 0; i < N; i++) p[i] = i; }
  int findSet(int i) { return (p[i] == i) ? i : (p[i] = findSet(p[i])); }
  bool isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  void unionSet(int i, int j) {
    if (!isSameSet(i, j)) { numSets--;
    int x = findSet(i), y = findSet(j);
    // rank is used to keep the tree short
    if (rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
    else                   { p[x] = y; setSize[y] += setSize[x];
                             if (rank[x] == rank[y]) rank[y]++; } } }
  int numDisjointSets() { return numSets; }
  int sizeOfSet(int i) { return setSize[findSet(i)]; }
};
int V,E;
vector<edge>edgeList;
void clear(){
	edgeList.clear();
}
int main(){
	fast;
//	inp;
//	out;
	while(1){
		cin>>V>>E;
		if(!V&&!E)break;
		int all=0;
		clear();
		FOR(i,E){
			int a,b,w;cin>>a>>b>>w;
			all+=w;
			edgeList.pb(edge(a,b,w));
		}
		sort(all(edgeList));
		UnionFind UF(V);
		int mst_cost=0;
		FOR(i,E){
			if(UF.numDisjointSets()==1)break;
			edge cur=edgeList[i];
			if(!UF.isSameSet(cur.v,cur.u)){
				UF.unionSet(cur.v,cur.u);
				mst_cost+=cur.w;
			}
		}
		cout<<all-mst_cost<<"\n";
	}
}
