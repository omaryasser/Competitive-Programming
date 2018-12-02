#include<bits/stdc++.h>
#define pb(x) push_back(x)
#define bug printf("HERE\n");

using namespace std;

const int MAX = (int)1e5 + 10, LOG = 21;
int V, Q;
vector<vector<int> > T;
int parentLog[MAX][LOG];
int parent[MAX], level[MAX], subSize[MAX], dist[MAX], P[MAX], res[MAX];
bool done[MAX];

int LCA (int x, int y) {
	if (level[x] < level[y])
		swap(x, y);

	for (int log = LOG - 1; log >= 0; log--)
		if (level[x] - (1 << log) >= level[y])
			x = parentLog[x][log];

	if (x == y) return x;

	for (int log = LOG - 1; log >= 0; log--)
		if (parentLog[x][log] != - 1 && parentLog[x][log] != parentLog[y][log]) {
			x = parentLog[x][log];
			y = parentLog[y][log];
		}

	return parent[x];
}

void dfs (int node = 0, int p = 0) {
	parent[node] = p;
	level[node] = level[p] + 1;
	dist[node] = node == 0 ? 0 : dist[p] + 1;
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (child != p) {
			dfs (child, node);
		}
	}
}

void fillDist() {
	for (int i = 0; i < V; i++)
		parentLog[i][0] = parent[i];

	for (int log = 1; log < LOG; log++) {
		for (int i = 0; i < V; i++) {
			if (parentLog[i][log - 1] != - 1) {
				parentLog[i][log] = parentLog[parentLog[i][log - 1]][log - 1];
			}
		}
	}
}

void dfs2 (int node, int p) {
	subSize[node] = 1;
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (child != p && !done[child]) {
			dfs2(child, node);
			subSize[node] += subSize[child];
		}
	}
}

int nn;

int getCentroid (int node, int parent) {
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (!done[child] && child != parent && subSize[child] > nn / 2)
			return getCentroid(child, node);
	}
	return node;
}

void decompose (int node = 0, int p = -1) {
	dfs2(node, node);
	nn = subSize[node];
	int centroid = getCentroid(node, node);
	P[centroid] = p;
	done[centroid] = true;

	for (int i = 0; i < (int)T[centroid].size(); i++) {
		int child = T[centroid][i];
		if (done[child]) continue;
		decompose (child, centroid);
	}
}

void preProcess() {
	dfs();
	fillDist();
	decompose();
}

int distancee (int u, int v) {
	return dist[u] + dist[v] - 2 * dist[LCA(u, v)];
}

void update (int v){
	int cur = v;
	while(1) {
		res[cur] = min (res[cur], distancee(cur, v));
		if (P[cur] == cur) break;
		cur = P[cur];
	}
}

int query (int v) {
	int cur = v;
	int ret = MAX;

	while (1) {
		ret = min (ret, res[cur] + distancee(cur, v));
		if (P[cur] == cur) break;
		cur = P[cur];
	}
	return ret;
}
int main () {
	scanf("%d %d", &V, &Q);
	T.assign(V, vector<int>());

	for (int i = 0; i < V - 1; i++) {
		int u, v;
		scanf("%d %d", &u, &v);
		u--,v--;
		T[u].pb(v);
		T[v].pb(u);
	}

	preProcess();
	for (int i = 0; i < V; i++)
		res[i] = MAX;
	update (0);

	while (Q--) {
		int t, v;
		scanf("%d %d", &t, &v);
		v--;

		if (t == 1)
			update(v);
		else
			printf("%d\n", query(v));
	}
}
