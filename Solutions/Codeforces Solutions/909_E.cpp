#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int V, E;
const int MAX = 100001;
vector<vector<int> > adjList(MAX, vector<int>());
bool isC[MAX];
int inD[MAX];
int need[MAX];
int main() {
    FAST;

    cin >> V >> E;
    FOR(i, 0, V) {cin >> isC[i];}
    while(E--) {
        int u, v; cin >> u >> v;
        inD[u]++;
        adjList[v].push_back(u);
    }

    int cnt = 0;
    queue<pair<int,int> > q;
    FOR(i, 0, V) if (!inD[i]) q.push({i, 0});

    while(q.size()) {
        pair<int,int> cur = q.front(); q.pop();
        int node = cur.first, dist = cur.second;
        if (isC[node]) cnt = max(cnt, dist / 2 + 1);
        FOR(i, 0, adjList[node].size()) {
            int nxt = adjList[node][i];
            if (isC[node] != isC[nxt]) {
                need[nxt] = max(need[nxt], need[node] + 1);
            }
            else need[nxt] = max(need[nxt], need[node]);
            if(!(--inD[nxt])) {
                q.push({nxt, need[nxt]});
            }
        }
    }

    cout << cnt << "\n";
}