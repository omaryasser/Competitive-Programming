#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

const int MAX = 200001, LOG = log(MAX) / log(2) + 3;
int V, P[LOG][MAX], lvl[MAX], deg[MAX];
vector<vector<int> > T(MAX, vector<int>());
bool done[MAX];

void dfs (int node = 0, int p = -1) {
    P[0][node] = p;
    for (auto nxt : T[node])
        if (nxt != p) {
            lvl[nxt] = lvl[node] + 1;
            dfs(nxt, node);
        }
}

void build_parent () {
    FOR (log, 1, LOG)
        FOR (i, 0, V)
            if (P[log - 1][i] != -1)
                P[log][i] = P[log - 1][P[log - 1][i]];
}

int LCA (int u, int v) {
    if (lvl[u] < lvl[v])
        swap(u, v);
    for (int log = LOG - 1; log >= 0; log--)
        if ((lvl[u] - (1 << log)) >= lvl[v])
            u = P[log][u];
    if (u == v) return u;
    for (int log = LOG - 1; log >= 0; log--)
        if (P[log][u] != P[log][v])
            u = P[log][u], v = P[log][v];
    return P[0][u];
}

int distance (int u, int v) {
    int lca = LCA(u, v);
    return lvl[u] + lvl[v] - 2 * lvl[lca];
}

pair<int,int> dfs2 (int node, int p = -1) {
    pair<int,int> furthest = {node, 0};
    for (auto nxt : T[node])
        if (nxt != p) {
            pair<int,int> go = dfs2(nxt, node);
            go.second++;
            if (go.second > furthest.second)
                furthest = go;
        }
    return furthest;
}

void simulate (int first, int second) {
    ll sum = 0;
    vector<pair<int,pair<int,int> > > res;
    queue<int> leaves;
    FOR (i, 0, V) if (deg[i] == 1 && i != first && i != second) leaves.push(i);
    while (leaves.size()) {
        int cur = leaves.front(); leaves.pop();
        done[cur] = true;
        int d1 = distance(cur, first), d2 = distance(cur, second);
        if (d1 > d2) {
            sum += d1;
            res.push_back({cur + 1, {first + 1, cur + 1}});
        } else {
            sum += d2;
            res.push_back({cur + 1, {second + 1, cur + 1}});
        }
        for (auto nxt : T[cur])
            if (!done[nxt] && --deg[nxt] == 1) {
                leaves.push(nxt);
            }
    }
    sum += distance(first, second);
    res.push_back({first + 1, {second + 1, second + 1}});
    for (auto nxt : T[second])
        if (!done[nxt] && --deg[nxt] == 1) {
            leaves.push(nxt);
        }
    while (leaves.size()) {
        int cur = leaves.front(); leaves.pop();
        done[cur] = true;
        int d1 = distance(cur, first);
        if (1 > 0) {
            sum += d1;
            res.push_back({cur + 1, {first + 1, cur + 1}});
        }
        for (auto nxt : T[cur])
            if (!done[nxt] && --deg[nxt] == 1) {
                leaves.push(nxt);
            }
    }
    cout << sum << "\n";
    for (auto p : res)
        cout << p.first << " " << p.second.first << " " << p.second.second << "\n";
}

int main() {
    FAST;
    memset(P, -1, sizeof(P));

    cin >> V;
    FOR (i, 0, V - 1) {
        int a, b; cin >> a >> b; a--, b--;
        T[a].push_back(b);
        T[b].push_back(a);
        deg[a]++, deg[b]++;
    }

    dfs ();
    build_parent();
    int first = dfs2(0).first, second = dfs2(first).first;
    simulate(first, second);
}