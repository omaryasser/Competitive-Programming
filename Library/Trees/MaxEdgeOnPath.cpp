#include<bits/stdc++.h>
#define pb(x) push_back(x)
using namespace std;

const int N = 10013;
int LOG = 14;
vector<pair<int,int> > T[N];
vector<pair<int,int> > edges;
int n, size[N], parent[N], lvl[N], arr[N], parentW[N], chainH[N], segT[4 * N], chainId[N], posInarr[N], parentLog[N][15];

void dfs (int node = 0, int p = -1) {
	parent[node] = p;
	size[node] = 1;
	for (int i = 0; i < (int)T[node].size(); i++) {
		pair<int,int> childd = T[node][i];
		int child = childd.first;
		if (child != p) {
			lvl[child] = lvl[node] + 1;
			parentW[child] = childd.second;
			dfs (child, node);
			size[node] += size[child];
		}
	}
}

void fill () {
	memset(parentLog, -1, sizeof parentLog);
	for (int i = 0; i < n; i++)
		parentLog[i][0] = parent[i];

	for (int log = 1; log <= LOG; log++) {
		for (int i = 0; i < n; i++) {
			if (parentLog[i][log - 1] != -1) {
				parentLog[i][log] = parentLog[parentLog[i][log - 1]][log - 1];
			}
		}
	}
}

int LCA (int u, int v) {
	if(lvl[u] < lvl[v]) {
		swap(u, v);
	}

	for (int log = LOG; log >= 0; log--) {
		if ((lvl[u] - (1 << log)) >= lvl[v]) {
			u = parentLog[u][log];
		}
	}

	if (u == v) {
		return u;
	}

	for (int log = LOG; log >= 0; log--) {
		if (parentLog[u][log] != -1 && parentLog[u][log] != parentLog[v][log]) {
			u = parentLog[u][log], v = parentLog[v][log];
		}
	}

	return parent[u];
}

int curId, t;
void dfs2 (int node = 0, bool start = true) {
	int mx = -1, c = -1;
	for (int i = 0; i < (int)T[node].size(); i++) {
		pair<int,int> childd = T[node][i];
		int child = childd.first;
		if (child != parent[node]) {
			if (mx == -1 || size[mx] < size[child]) {
				mx = child;
				c = childd.second;
			}
		}
	}


	if(mx != -1) {
		chainId[mx] = curId;
		arr[t] = c;
		posInarr[mx] = t;
		t++;
		dfs2(mx, false);
	}

	for (int i = 0; i < (int)T[node].size(); i++) {
		pair<int,int> childd = T[node][i];
		int child = childd.first;
		if (child != parent[node]) {
			if (child != mx) {
				curId++;
				chainId[child] = curId;
				chainH[curId] = child;
				arr[t] = childd.second;
				posInarr[child] = t;
				t++;
				dfs2(child, true);
			}
		}
	}
}

void build (int node = 1, int left = 0, int right = t - 1) {
	if (left == right) {
		segT[node] = arr[left];
	}
	else {
		int mid = (left + right) >> 1;
		build(node << 1, left, mid);
		build(node << 1 | 1, mid + 1, right);
		segT[node] = max(segT[node << 1], segT[node << 1 | 1]);
	}
}

void update (int node, int left, int right, int idx, int val) {
	if(left == idx && right == idx) {
		segT[node] = val;
		return ;
	}
	int mid = (left + right) >> 1;
	if (idx <= mid) {
		update (node << 1, left, mid, idx, val);
	}
	else {
		update (node << 1 | 1, mid + 1, right, idx, val);
	}
	segT[node] = max(segT[node << 1], segT[node << 1 | 1]);
}

int query (int node, int left, int right, int l, int r) {
	if (left >= l && right <= r) {
		return segT[node];
	}
	if (left > r || right < l) {
		return -1e9;
	}

	int mid = (left + right) >> 1;

	if (mid >= r) {
		return query (node << 1, left, mid, l, r);
	}
	if (mid < l) {
		return query (node << 1 | 1, mid + 1, right, l, r);
	}

	int resL = query (node << 1, left, mid, l, r);
	int resR = query (node << 1 | 1, mid + 1, right, l, r);
	return max(resL, resR);
}

int query2(int u, int v) {
	int mx = -1e9;
	while (1) {
		if(chainId[u] != chainId[v]) {
			mx = max(mx, query(1, 0, t - 1, posInarr[chainH[chainId[u]]], posInarr[u]));
			mx = max(mx, parentW[chainH[chainId[u]]]);
			u = parent[chainH[chainId[u]]];
		}
		else {
			if (posInarr[v] + 1 >  posInarr[u]) break;
			mx = max(mx, query(1, 0, t - 1, posInarr[v] + 1, posInarr[u]));
			break;
		}
	}
	return mx;
}
int main () {
	int ttt;
	scanf("%d", &ttt);
	while (ttt--) {
		scanf("%d", &n);

		for (int i = 0; i < n; i++)
			T[i].clear();
		edges.clear();
		for (int i = 0; i < n - 1; i++) {
			int u, v, c;
			scanf("%d %d %d", &u, &v, &c);
			u--, v--;
			T[u].pb(make_pair(v, c));
			T[v].pb(make_pair(u, c));
			edges.pb(make_pair(u, v));
		}

		lvl[0] = 0;
		dfs();
		fill();
		curId = 0;
		t = 1;
		chainId[0] = 0;
		chainH[0] = 0;
		posInarr[0] = 0;
		curId = 1;
		dfs2();
		for (int i = 0; i < 4 * N; i++) {
			segT[i] = -1e9;
		}

		build();
		while (1) {
			char op[8];
			scanf("%s", op);
			if (op[0] == 'D') {
				break;
			}
			int u, v;
			scanf("%d %d", &u, &v);
			u--;
			if (op[0] == 'C') {
				if(parent[edges[u].first] == edges[u].second) {
					update (1, 0, t - 1, posInarr[edges[u].first], v);
				}
				else {
					update (1, 0, t - 1, posInarr[edges[u].second], v);
				}
			}

			else {

				v--;
				if (u == v) {
					printf("%d\n", 0);
					continue;
				}
				int lca = LCA (u, v);
				int first = -1e9, second = -1e9;
				if(lvl[u] != lvl[lca]) {
					first = query2(u, lca);
				}
				if (lvl[v] != lvl[lca])	 {
					second = query2(v, lca);
				}
				printf("%d\n", max(first, second));
			}
		}
	}
	return 0;
}
