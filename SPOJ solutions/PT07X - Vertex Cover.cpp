 #include <bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= (int)n; i++)
#define mp(x, y) make_pair(x, y)
#define all(x) x.begin(),x.end()
#define sz(x) (int)x.size()
#define pb(x) push_back(x)
#define bug cerr << "HERE\n";
typedef long long ll;
typedef short int si;

using namespace std;

int take (int, int);
int leave (int, int);

int n;
const int n_ = 100001;
vector<int> tree[n_];
int memo1[n_], memo2[n_];

int take (int idx, int p) {
    int &ret = memo1[idx];
    if (ret != -1) return ret;
    ret = 1;
    FOR (i, 0, sz(tree[idx]) - 1) {
        int nxt = tree[idx][i];
        if (nxt != p) {
            ret += min (leave(nxt, idx), take(nxt, idx));
        }
    }
    return ret;
}

int leave (int idx, int p) {
    int &ret = memo2[idx];
    if (ret != -1) return ret;
    ret = 0;
    FOR (i, 0, sz (tree[idx]) - 1) {
        int nxt = tree[idx][i];
        if (nxt != p) {
            ret += take(nxt, idx);
        }
    }
    return ret;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    FOR (i, 0, n - 2) {
        int u, v;
        cin >> u >> v;
        u--, v--;
        tree[u].pb(v);
        tree[v].pb(u);
    }

    memset(memo1, -1, sizeof memo1);
    memset(memo2, -1, sizeof(memo2));
    cout << min(take(0, -1), leave(0, -1)) << "\n";
    return 0;
}
