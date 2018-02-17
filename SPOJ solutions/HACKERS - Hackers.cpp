#include<bits/stdc++.h>
#define FOR(x,y) for (int x = 0 ; x < y ; ++ x)
#define ALL(x) x.begin(),x.end()
using namespace std;

typedef vector<int> vi;
typedef pair<int , int> ii ;
int V ;
int E;
int H;
vector<pair<int , ii > > EdgeList;
vector< vector<ii> > adjList ;
int memo[3003][3003];
class UnionFind {
private:
  vi p, rank;
public:
  UnionFind(int N) {
    rank.assign(N, 0);
    p.assign(N, 0); for (int i = 0; i < N; i++) p[i] = i; }
  int findSet(int i) { return (p[i] == i) ? i : (p[i] = findSet(p[i])); }
  bool isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  void unionSet(int i, int j) {
    if (!isSameSet(i, j)) {
    int x = findSet(i), y = findSet(j);
    if (rank[x] > rank[y]) { p[y] = x;  }
    else                   { p[x] = y;
                             if (rank[x] == rank[y]) rank[y]++; } } }
};

int dfs (int u , int v , int p = -1 , int maxx = 0){
	if (u == v) return maxx;

	int ans = 1e9 + 1;
	FOR (i , (int)adjList[u].size()){
		int go = adjList[u][i].first;
		if (go != p) ans = min(ans , dfs(go , v , u , max (maxx , adjList[u][i].second)));
	}
	return ans;
}
int main() {
//	freopen("input.txt", "r", stdin);
	ios_base::sync_with_stdio(0); cin.tie(0);

	while (1){
		memset(memo , -1 , sizeof(memo));
		cin >> V >> E >> H;
		if (V == -1 && E == -1 && H == - 1) break;
		EdgeList.clear();
		adjList.clear();
		FOR (i , E) {
			int A , B , C; cin >> A >> B >> C; A -- ; B -- ;
			EdgeList.push_back(make_pair(C , ii (A , B)));
		}
		sort (ALL (EdgeList));
		adjList.assign(V , vector< ii >());
		UnionFind UF (V);
		FOR (i , E){
			pair<int , ii> front = EdgeList[i];
			int u = front.second.first;
			int v = front.second.second;
			if (!UF.isSameSet(u , v)){
				UF.unionSet(u , v);
				adjList[u].push_back(ii(v,front.first));
				adjList[v].push_back(ii(u,front.first));
			}
		}

		FOR (i , H){
			int u , v ; cin >> u >> v ; u -- ; v --;
			int ans ;
			if (memo[u][v] != -1) ans = memo[u][v];
			else memo[u][v] = ans = dfs (u , v);
			if (i == 0) cout << ans;
			else cout << " " << ans;
		}
		cout << "\n";
	}

}
