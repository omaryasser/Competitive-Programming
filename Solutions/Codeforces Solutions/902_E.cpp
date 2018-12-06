#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

const int MAX = 300001;

int n, m;
vector<int> rightt(MAX, MAX + 10);
vector<vector<int> > adjList(MAX);
int UNVISITED = 0, EXPLORED = 1, VISITED = 2;
int visited[MAX];
stack<int> s;

void dfs (int node, int p) {
    s.push(node);
    visited[node] = EXPLORED;
    for (auto nxt : adjList[node])
        if (nxt != p) {
            if (!visited[nxt]) {
                dfs (nxt, node);
            } else if (visited[nxt] == EXPLORED){
                int mn = MAX + 10, mx = -1;
                while (1) {
                    int num = s.top(); s.pop();
                    mn = min (mn, num);
                    mx = max (mx, num);
                    if (num == nxt) break;
                }
                rightt[mn] = mx;
            }
        }
    visited[node] = VISITED;
}

int main() {
    FAST;
    cin >> n >> m;
    while (m--) {
        int a, b;
        cin >> a >> b; a--, b--;
        adjList[a].push_back(b);
        adjList[b].push_back(a);
    }
    for (int i = 0; i < n; i++)
     if (visited[i] == UNVISITED)
         dfs (i, -1);

    for (int i = n - 2; i >= 0; i--) rightt[i] = min (rightt[i], rightt[i + 1]);
    ll sumDiff[MAX + 1];
    sumDiff[0] = rightt[0];
    for (int i = 1; i < MAX; i++) sumDiff[i] = sumDiff[i - 1] + rightt[i] - i;

    int q; cin >> q;
    while (q--) {
        int l, r; cin >> l >> r; l--, r--;
        auto ptr = upper_bound(rightt.begin() + l, rightt.begin() + r, r);
        if (ptr == rightt.end()) {cout << sumDiff[r] - (l == 0 ? 0 : sumDiff[l - 1]) << "\n";}
        else {
            int start = ptr - rightt.begin();
            cout << 1ll * (r - start + 1) * (r - start + 2) / 2 + (start == 0 ? 0 : sumDiff[start - 1]) - (l == 0 ? 0 : sumDiff[l - 1]) << "\n";
        }
    }
}