int kth(int u, int v, int k) {
	int lca = LCA(u, v);
	int toLCA = lvl[u] - lvl[lca] + 1;
	if (toLCA == k)
		return lca + 1;
	else {
		if (toLCA < k) {
			int toLCA2 = lvl[v] - lvl[lca];
			int needed = toLCA2 - (k - (toLCA));
			for (int log = LOG; log >= 0; log--) {
				if (needed >= (1 << log)) {
					v = parentLog[v][log];
					needed -= (1 << log);
				}
			}
			return v + 1;
		} else {
			k--;
			for (int log = LOG; log >= 0; log--) {
				if (k >= (1 << log)) {
					u = parentLog[u][log];
					k -= (1 << log);
				}
			}
			return u + 1;
		}
	}
}
