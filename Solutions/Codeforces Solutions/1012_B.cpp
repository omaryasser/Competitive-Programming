#include <bits/stdc++.h>

typedef long long ll;

using namespace std;

const int n_ = 2e5 + 10;
vector<int> adj[n_];
bool visited[n_], have[n_], found_in_row[n_];
vector<int> cur_cc;

void dfs(int u) {
    visited[u] = 1;
    cur_cc.push_back(u);
    for(int v : adj[u]) {
        if(!visited[v]) {
            dfs(v);
        }
    }
}
int solve(int n, int m, int q, vector<int> x, vector<int> y) {
    for(int i = 0; i < n_; i++)
        adj[i] = vector<int>(), visited[i] = 0, have[i] = 0, found_in_row[i] = 0;
    vector<vector<int> > rows;
    rows.assign(n, vector<int>());
    for(int i = 0; i < q; i++) {
        rows[x[i]].push_back(y[i]);
        if(!x[i]) have[y[i]] = 1;
        found_in_row[x[i]] = 1;
    }

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < (int)rows[i].size() - 1; j++) {
            adj[rows[i][j]].push_back(rows[i][j + 1]);
            adj[rows[i][1 + j]].push_back(rows[i][j]);
        }
    }
    int res = 0;
    for(int i = 0; i < m; i++) {
        if(!visited[i]) {
            dfs(i);
            bool f = 0;
            for(int cols : cur_cc) {
                f |= have[cols];
            }
            cur_cc = vector<int>();
            if(!f) res++;
        }
    }

    for(int i = 1; i < n; i++) {
        if(!found_in_row[i]) {
            res++;
        }
    }
    return res;
}

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n, m, q;
    cin >> n >> m >> q;
    vector<int> x(q), y(q);
    for(int i = 0; i < q; i++) {
        cin >> x[i] >> y[i];
        x[i]--, y[i]--;
    }

    cout << min(solve(n, m, q, x, y), solve(m, n, q, y, x)) << "\n";
}