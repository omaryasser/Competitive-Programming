#include <bits/stdc++.h>
#define fast 	ios_base::sync_with_stdio(0); cin.tie(0);
using namespace std;

const int MAX = 10001;
int N;
int T;
int maxLog;
vector<vector<pair<int , int> > > adjList;
int level[MAX];
int parent[MAX];
int parentLog[MAX][(int)(log(MAX) / log(2)) + 2];
long long rootRealDist[MAX];
long long rootDist[MAX];
int dist[MAX];
void fillLevels (int node , int p = - 1) {
	parent[node] = p;
	for (int i = 0 ; i < (int)adjList[node].size() ; ++ i) {
		pair<int , int> pairr = adjList[node][i];
		int child = pairr.first , cost = pairr.second;
		if (child != p) {
			rootRealDist[child] = rootRealDist[node] + (long long)cost;
			dist[child] = dist[node] + 1;
			level[child] = level[node] + 1;
			fillLevels (child , node);
		}
	}
}
int LCA (int x , int y) {
	if (level[x] < level[y])
		swap(x , y);
	for (int log = maxLog ; log >= 0 ; -- log)
		if (level[x] - (1 << log) >= level[y])
			x = parentLog[x][log];

	if (x == y) return x;

	for (int log = maxLog ; log >= 0 ; -- log)
		if (parentLog[x][log] != - 1 && parentLog[x][log] != parentLog[y][log])
			x = parentLog[x][log] , y = parentLog[y][log];
	return parent[x];
}

long long distt (int x , int y) {
	return rootRealDist[x] + rootRealDist[y] - 2 * rootRealDist[LCA(x , y)];
}

int kth (int x , int y , int k){
	if (dist[x] - dist[LCA(x , y)] + 1 >= k) {
		k -- ;
		for (int log = maxLog ; log >= 0 ; -- log)
			if ((1 << log) <= k)
				k -= (1 << log) , x = parentLog[x][log];
		return x;
	}
	else {
		int remNodes = k - (dist[x] - dist[LCA(x , y)] + 1);
		int nodesFromLCA = dist[y] - dist[LCA(x , y)];
		k = nodesFromLCA - remNodes;
		for (int log = maxLog ; log >= 0 ; -- log)
					if ((1 << log) <= k)
						k -= (1 << log) , y = parentLog[y][log];
				return y;
	}
}
int main(){
	fast

	cin >> T;
	while (T -- ) {
		cin >> N;
		adjList.assign(N , vector<pair<int , int> > ());
		for (int i = 0 ; i < N - 1 ; ++ i) {
			int u , v , c; cin >> u >> v >> c; u -- , v -- ;
			adjList[u].push_back(make_pair(v , c));
			adjList[v].push_back(make_pair(u , c));
		}
		level[0] = 0;
		rootRealDist[0] = 0;
		rootDist[0] = 0;
		fillLevels (0);
		for (int log = 0 ; 1 << log <= N + 1; ++ log)
			maxLog = log;
		memset(parentLog , - 1 , sizeof(parentLog));
		for (int i = 0 ; i < N ; ++ i)
			parentLog[i][0] = parent[i];
		for (int j = 1 ; j <= maxLog ; ++ j)
			for (int i = 0 ; i < N ; ++ i)
				if (parentLog[i][j - 1] != - 1)
					parentLog[i][j] = parentLog[parentLog[i][j - 1]][j - 1];
		while (1) {
			string op ; cin >> op;
			if (op == "DONE") break;
			if (op == "DIST") {
				int a , b; cin >> a >> b ; a -- , b --;
				cout << distt(a , b) << "\n";
			}
			else {
				int a , b , k; cin >> a >> b >> k; a -- , b --;
				cout << kth(a , b , k)+1 << "\n";
			}
		}
		cout << "\n";
	}
	return 0;
}
