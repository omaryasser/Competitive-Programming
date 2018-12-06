#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n, m, k;
const int n_ = 2e5 + 10;
unordered_set<int> adj[n_];
pair<int,int> edges[n_];
int deg[n_];
bool good[n_];
int main () {
    scanf("%d %d %d", &n, &m, &k);
    for(int i = 0; i < m; i++) {
        int u, v;
        scanf("%d %d", &u, &v);
        --u, --v;
        edges[i].first = u;
        edges[i].second = v;
        adj[u].insert(v);
        adj[v].insert(u);
        deg[u]++;
        deg[v]++;
        good[u] = deg[u] >= k;
        good[v] = deg[v] >= k;
    }
    queue<int> bad;
    for(int i = 0; i < n; i++) {
        if(!good[i]) {
            bad.push(i);
        }
    }
    while(!bad.empty()) {
        int u = bad.front(); bad.pop();
        for(int v : adj[u]) {
            if(good[v]) {
                deg[v]--;
                good[v] = deg[v] >= k;
                if(!good[v]) {
                    bad.push(v);
                }
            }
        }
    }
    int cnt_good = 0;
    for(int i = 0; i < n; i++)
        cnt_good += good[i];

    vector<int> res;
    for(int i = m - 1; i >= 0; i--) {
        res.push_back(cnt_good);
        if(!i) break;
        int u = edges[i].first, v = edges[i].second;
        adj[u].erase(v);
        adj[v].erase(u);
        if(good[v] && good[u]) deg[u]--, deg[v]--;
        for(int _ = 0; _ < 2; _++) {
            if(good[u] && deg[u] < k) {
                bad.push(u);
                good[u] = 0;
                cnt_good--;
                while(!bad.empty()) {
                    int uu = bad.front(); bad.pop();
                    for(int vv : adj[uu]) {
                        if(good[vv]) {
                            deg[vv]--;
                            good[vv] = deg[vv] >= k;
                            if(!good[vv]) {
                                bad.push(vv);
                                cnt_good--;
                            }
                        }
                    }
                }
            }
            swap(u, v);
        }
    }
    reverse(res.begin(), res.end());
    for(int i : res) {
        printf("%d\n", i);
    }
}