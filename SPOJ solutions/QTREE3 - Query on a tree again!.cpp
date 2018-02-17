#include<bits/stdc++.h>

using namespace std;

const int N = 100003;


vector<int> T[N];
int parent[N], chainId[N], chainH[N], order[N], size[N];
int n, Q;
int curId, t;

struct C
{
    bool operator()(const int &a, const int &b) const
    {
        return order[a] < order[b];
    }
};
set<int, C> st[N];
void dfs (int node = 0) {
	size[node] = 1;
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (child != parent[node]) {
			parent[child] = node;
			dfs(child);
			size[node] += size[child];
		}
	}
}
void build (int node = 0) {
	int mx = -1;
	for(int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (child != parent[node]) {
			if (mx == -1 || size[child] > size[mx]) {
				mx = child;
			}
		}
	}

	if (mx != -1) {
		chainId[mx] = curId;
		order[mx] = t++;
		build(mx);
	}

	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (child != parent[node] && child != mx) {
			chainH[++curId] = child;
			chainId[child] = curId;
			order[child] = t++;
			build(child);
		}
	}
}

int main () {

	scanf("%d %d", &n, &Q);
	for (int i = 0; i < n - 1; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		a--, b--;
		T[a].push_back(b);
		T[b].push_back(a);
	}

	parent[0] = -1;
	dfs();
	chainId[0] = 0;
	chainH[0] = 0;
	order[0] = 0;
	curId = 1;
	t = 1;
	build();

	while (Q--) {
		int op, v;
		scanf("%d %d", &op, &v);
		v--;

		if (!op) {
			if (st[chainId[v]].find(v) != st[chainId[v]].end()) {
				st[chainId[v]].erase(v);
			}
			else {
				st[chainId[v]].insert(v);
			}
		}
		else {
			int res = -1;
			while (1) {
				if ((int)st[chainId[v]].size()) {
					int first = *st[chainId[v]].begin();
					if (order[first] <= order[v]) {
						res = first + 1;
					}
				}
				if (chainId[v] == 0) break;
				v = parent[chainH[chainId[v]]];
			}

			printf("%d\n", res);
		}
	}
}
