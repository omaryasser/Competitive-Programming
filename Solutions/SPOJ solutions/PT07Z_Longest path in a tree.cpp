#include<bits/stdc++.h>
typedef long long ll;
using namespace std;

int n;
const int n_ = 10001;
vector<int> T[n_];
int dist[n_];
int inf = n_ + 10;
pair<int,int> furthest (int node) {
	memset(dist, inf, sizeof dist);
	dist[node] = 0;
	queue<int> q;
	q.push(node);
	pair<int,int> res = make_pair(0,0);
	while(!q.empty()) {
		int cur = q.front(); q.pop();
		for (int i = 0; i < (int)T[cur].size(); i++) {
			int nxt = T[cur][i];
			if (dist[nxt] > dist[cur] + 1) {
				dist[nxt] = dist[cur] + 1;
				q.push(nxt);
				if (dist[nxt] > res.second) res = make_pair(nxt, dist[nxt]);
			}
		}
	}
	return res;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    for (int i = 0; i < n - 1; i++) {
    	int u, v;
    	cin >> u >> v;
    	u--, v--;
    	T[u].push_back(v);
    	T[v].push_back(u);
    }

    cout << furthest(furthest(0).first).second << "\n";
    return 0;
}
