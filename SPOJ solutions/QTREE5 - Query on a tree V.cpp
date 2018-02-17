#include<bits/stdc++.h>

using namespace std;

const int N = 100002, LOG = 17;
vector<int> T[N];
int sz[N], P[N], parentLog[N][LOG + 1]; // parent in Centroid Tree
bool done[N];
int col[N], dist[N];
int n;
map<int,int> cntD[N];
map<int,int> nodeD[N];

void dfs (int node = 0, int p = -1) {
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (child != p) {
			dist[child] = dist[node] + 1;
			parentLog[child][0] = node;
			dfs (child, node);
		}
	}
}

int LCA (int u, int v) {
	if (dist[u] < dist[v]) {
		swap (u, v);
	}

	for (int log = LOG; log >= 0; log--) {
		if (dist[u] - (1 << log) >= dist[v]) {
			u = parentLog[u][log];
		}
	}

	if (u == v) {
		return u;
	}

	for (int log = LOG; log >= 0; log--) {
		if (parentLog[u][log] != -1 && parentLog[u][log] != parentLog[v][log]) {
			u = parentLog[u][log];
			v = parentLog[v][log];
		}
	}

	return parentLog[u][0];
}

int distance (int u, int v) {
	return dist[u] + dist[v] - 2 * dist[LCA(u, v)];
}
int fillSize (int node, int p) {
	sz[node] = 1;
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (child != p && !done[child]) {
			fillSize(child, node);
			sz[node] += sz[child];
		}
	}
	return sz[node];
}
int decompose (int node = 0, int p = -1, int size = n) {
	if (p == -1) size = fillSize(node, -1);

	int centroid = -1;
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (child != p && !done[child] && sz[child] > size / 2) {
			centroid = child;
			break;
		}
	}

	if (centroid != -1) {
		return decompose (centroid, node, size);
	}

	// node is the centroid
	done[node] = true;
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (!done[child]) {
			P[decompose(child, -1, 0)] = node;
		}
	}

	return node;
}

void update (int node, int col) {
	int cur = node;
	while (1) {
		if(col) {
			int cur_dist = distance(cur, node);
			nodeD[cur][node] = cur_dist;
			cntD[cur][cur_dist]++;
		}
		else {
			int distancee = nodeD[cur][node];
			cntD[cur][distancee]--;
			if (cntD[cur][distancee] == 0) cntD[cur].erase(distancee);
		}
		cur = P[cur];
		if (cur == -1) break;
	}
}

int query (int node) {
	int res = -1;
	int cur = node;
	while (1) {
		if ((int)cntD[cur].size()) {
			int best_distance = distance (node, cur) + (cntD[cur].begin()->first);
			if (res == -1 || res > best_distance) {
				res = best_distance;
			}
		}
		cur = P[cur];
		if (cur == -1) break;
	}
	return res;
}
int main () {

	scanf("%d", &n);
	for (int i = 0; i < n - 1; i++) {
		int u, v;
		scanf("%d %d", &u, &v);
		u--, v--;
		T[u].push_back(v);
		T[v].push_back(u);
	}

	memset(P, -1, sizeof P);
	decompose(0, -1, 0);
	memset(parentLog, -1, sizeof parentLog);
	dfs();
	for (int log = 1; log <= LOG; log++) {
		for (int i = 0; i < n; i++) {
			if (parentLog[i][log - 1] != -1) {
				parentLog[i][log] = parentLog[parentLog[i][log - 1]][log - 1];
			}
		}
	}

	int Q;
	scanf("%d", &Q);
	while (Q--) {
		int op, v;
		scanf("%d %d", &op, &v);
		v--;
		if (!op) {
			col[v] = 1 - col[v];
			update(v, col[v]);
		}
		else {
			printf("%d\n", query(v));
		}
	}
}
