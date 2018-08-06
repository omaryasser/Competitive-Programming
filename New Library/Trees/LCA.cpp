void fillParent() {
	for (int log = 1; log <= LOG; log++)
		for (int i = 0; i < n; i++)
			if (parentLog[i][log - 1] != -1)
				parentLog[i][log] = parentLog[parentLog[i][log - 1]][log - 1];
}
int LCA(int u, int v) {
	if (dist[u] < dist[v])
		swap(u, v);
	for (int log = LOG; log >= 0; log--)
		if (dist[u] - (1 << log) >= dist[v])
			u = parentLog[u][log];
	if (u == v)
		return u;
	for (int log = LOG; log >= 0; log--)
		if (parentLog[u][log] != -1 && parentLog[u][log] != parentLog[v][log])
			u = parentLog[u][log], v = parentLog[v][log];
	return parentLog[u][0];
}
int getDist(int u, int v) {
	return dist[u] + dist[v] - 2 * dist[LCA(u, v)];
}
