#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)
#define pb(x) push_back(x)
#define F first
#define S second

using namespace std;


struct Pair{
    int x, y, idx;
    Pair(int _x, int _y, int _i){
        x = _x;
        y = _y;
        idx = _i;
    }
    Pair(){}
    bool operator < (const Pair &other) const {
        return (x - y) < (other.x - other.y);
    }
};

const int n_ = 3e5 + 10;
vector<int> adj[n_];
int pos[n_];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n, m;
    cin >> n >> m;
    vector<Pair> pairs(n);
    f(i, 0, n){
        int x, y;
        cin >> x >> y;
        pairs[i] = Pair(x, y, i);
    }

    sort(all(pairs));
    f(i, 0, n)pos[pairs[i].idx] = i;

    while(m--){
        int u, v;
        cin >> u >> v;
        u--, v--;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    vector<ll> a(n + 1), b(n + 1);
    b[n - 1] = pairs[n - 1].y;
    a[0] = pairs[0].x;
    for(int i = n - 2; i >= 0; i--)
        b[i] = b[i + 1] + pairs[i].y;
    f(i, 1, n)a[i] = a[i - 1] + pairs[i].x;

    vector<ll> ret(n);
    f(i, 0, n){
        ll res = 0;
        res += 1ll * pairs[i].x * (n - i - 1) + b[i + 1];
        res += 1ll * pairs[i].y * (i) + (i == 0 ? 0 : a[i - 1]);
        for(int v : adj[pairs[i].idx]){
            if(pos[v] < i){
                res -= 1ll * pairs[i].y + pairs[pos[v]].x;
            }else {
                res -= 1ll * pairs[i].x + pairs[pos[v]].y;
            }
        }
        ret[pairs[i].idx] = res;
    }
    f(i, 0, n){
        if(i)cout << " ";
        cout << ret[i];
    }
    cout << "\n";
}