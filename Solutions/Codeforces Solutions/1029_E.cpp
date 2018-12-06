#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int n_ = 2e5 + 10;
vector<int> adj[n_];
int lvl[n_];
int p[n_];

void dfs(int u = 0, int p_ = -1) {
    p[u] = p_;
    for(int v : adj[u]) {
        if(v != p_) {
            lvl[v] = lvl[u] + 1;
            dfs(v, u);
        }
    }
}


int main() {
    int n;
    scanf("%d", &n);
    for(int i = 0; i < n - 1; i++) {
        int u, v;
        scanf("%d %d", &u, &v);
        adj[--u].push_back(--v);
        adj[v].push_back(u);
    }
    dfs();
    multiset<pair<int,int> > q;
    for(int i = 0; i < n; i++) {
        if(lvl[i] > 2) {
            q.insert({lvl[i], i});
        }
    }

    int res = 0;
    while(!q.empty()) {
        auto it = --q.end();
        int u = it->second;
        q.erase(it);
        res++;
        for(int v : adj[p[u]]) {
            it = q.find({lvl[v], v});
            if(it != q.end()) {
                q.erase(it);
            }
        }
        int v = p[u];
        it = q.find({lvl[v], v});
        if(it != q.end()) {
            q.erase(it);
        }
    }
    cout << res << "\n";
}