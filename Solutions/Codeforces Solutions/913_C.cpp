#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)


using namespace std;
typedef long long ll;

int n;
const int MAX = 31;
ll L;
ll c[MAX];

map<pair<int,ll>, ll > memo;

ll dp (int idx, ll rem) {
    if (rem <= 0) return 0;
    if (idx == 0) return c[idx] * rem;
    if (memo.find({idx, rem}) != memo.end()) return memo[{idx, rem}];
    ll res = dp(idx - 1, rem); // dont
    res = min(res, ((rem + (1 << idx) - 1) / (1 << idx)) * c[idx]); // take all from here
    for (int i = idx - 1; i >= 0; i--) {
        // nxt will use
        ll cur = (1ll << idx);
        ll x = (rem - cur + cur - 1) / cur;
        res = min (res, x * c[idx] + dp(i, rem - x * cur));
    }
    return memo[{idx, rem}] = res;
}
int main() {
    FAST;

    cin >> n >> L;
    FOR (i, 0, n) cin >> c[i];
    cout << dp (n - 1, L) << "\n";
}