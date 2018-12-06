#include<bits/stdc++.h>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;
ll tarjan();
void tarjan(int);
ll dp(int);
int endAt(int);


const int MAX_V = 1000001;
int n, m;
vector<vector<pair<int,int> > > adjList;
vector<vector<pair<int,int> > > adjList2;
ll cumSub[(int) sqrt(100000001) + 10000];
int START;
int dfs_low[MAX_V];
int dfs_num[MAX_V];
int SCC_NUM[MAX_V];
ll SCC_RES[MAX_V];
stack<int> s;
int counter;
int SCC_COUNTER;
ll memo[MAX_V];
ll get(ll);

ll tarjan() {
    memset(SCC_NUM, -1, sizeof SCC_NUM);
    for (int i = 0; i < n; i++)
        if (dfs_num[i] == 0) {
            tarjan(i);
        }
    adjList2.assign(SCC_COUNTER, vector<pair<int,int> >());
    for (int i = 0; i < n; i++)
        for (auto nxt : adjList[i])
    if (SCC_NUM[i] != SCC_NUM[nxt.first]) {
        adjList2[SCC_NUM[i]].push_back(make_pair(SCC_NUM[nxt.first], nxt.second));
    } else {
        SCC_RES[SCC_NUM[i]] += get(nxt.second);
    }
    memset(memo, -1, sizeof memo);
    return dp(SCC_NUM[START]);
}

ll dp(int node) {
    if (memo[node] != -1) return memo[node];
    ll best = 0;
    for (auto nxt : adjList2[node])
    best = max(best, dp(nxt.first) + nxt.second);
    return memo[node] = best + SCC_RES[node];
}

void tarjan(int node) {
    dfs_num[node] = dfs_low[node] = ++counter;
    s.push(node);
    for (auto nxt : adjList[node]) {
        int to = nxt.first;
        if (dfs_num[to] == 0)
            tarjan(to);
        if (SCC_NUM[to] == -1)
            dfs_low[node] = min(dfs_low[node], dfs_low[to]);
    }
    if (dfs_low[node] == dfs_num[node]) {
        while (true) {
            int cur = s.top(); s.pop();
            SCC_NUM[cur] = SCC_COUNTER;
            if (cur == node) break;
        }
        SCC_COUNTER++;
    }
}

int endAt(int num) {
    ll res = (ll) sqrt(2 * num) + 3;
    while (num < res * (res + 1) / 2){
        res--;
    }
    return (int)res;
}

ll get(ll num) {
    return num * (endAt((int) num) + 1) - (cumSub[endAt((int) num)]);
}


int main() {
    FAST;

//    for (int i = 1; i <= 10000; i++) {
//        int result = 1;
//        while((result + 1) * (result + 2) / 2 <= i) result++;
//        if(result != endAt(i))
//            cerr << i << " " << result << " " << endAt(i) << "\n";
//    }
    cumSub[1] = 1;
    for (int i = 2; i < (int) sqrt(100000001) + 10000; i++)
        cumSub[i] = cumSub[i - 1] + (1ll * i * (i + 1)) / 2;
    cin >> n >> m;
    adjList.assign(MAX_V, vector<pair<int,int> >());
    while (m-- > 0) {
        int u, v, c; cin >> u >> v >> c;
        u--, v--;
        adjList[u].push_back(make_pair(v, c));
    }
    cin >> START; START--;
    cout << tarjan() << "\n";


    return 0;
}