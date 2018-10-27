#include<bits/stdc++.h>

using namespace std;

struct query {
	int l, r, k;
	int idx;
	query() {}
	query(int ll, int rr, int kk, int ii) : l(ll), r(rr), k(kk), idx(ii) {}
	bool operator < (const query& q) const {
		return k < q.k;
	}
};

const int n_ = 30001;
int arr[n_];
vector<pair<int,int> > sortedArr;
int tree[n_ * 4];
int n;

void update (int node, int l, int r, int idx) {
	if (l == r && l == idx) tree[node] = 0;
	else if (l > idx || r < idx) return;
	else {
		int mid = (l + r) >> 1;
		update (node << 1, l, mid, idx);
		update (node << 1 | 1, mid + 1, r, idx);
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}
}

void build (int node, int l, int r) {
//	printf("%d %d %d\n", node, l, r);
	if (l == r) {
//		printf("%d %d %d\n", node, l, r);
		tree[node] = 1;
	}
	else {
		int mid = (l + r) >> 1;
		build (node << 1, l, mid);
		build (node << 1 | 1, mid + 1, r);
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}
}

int queryy (int node, int l, int r, int s, int e) {
	if (l >= s && r <= e) return tree[node];
	if (l > e || r < s) return 0;

	int mid = (l + r) >> 1;
	return queryy (node << 1, l , mid, s, e) + queryy (node << 1 | 1, mid + 1, r, s, e);
}

int main () {
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
		sortedArr.push_back(make_pair(arr[i], i));
	}

	sort(sortedArr.begin(), sortedArr.end());
	build(1, 0, n - 1);

	int q;
	scanf("%d", &q);

	query queries[q];
	for(int i = 0; i < q; i++) {
		int l, r, k;
		scanf("%d %d %d", &l, &r, &k);
		queries[i] = query(l - 1, r - 1, k, i);
	}

	sort(queries, queries + q);
	int res[q];

	int idx = 0;
	for (int i = 0; i < q; i++) {
		while (idx < n && sortedArr[idx].first <= queries[i].k) update(1, 0, n - 1, sortedArr[idx++].second);
		res[queries[i].idx] = queryy(1, 0, n - 1, queries[i].l, queries[i].r);
	}

	for (int i = 0; i < q; i++)
		printf("%d\n", res[i]);
}
