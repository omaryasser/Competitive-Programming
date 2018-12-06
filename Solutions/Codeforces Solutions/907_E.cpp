#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

int n, m;
vector<int> f(22);
int dp[1 << 22];

int main() {
    FAST;

    cin >> n >> m;
    if (m == n * (n - 1) / 2) return puts("0");
    while (m--) {
        int u, v; cin >> u >> v; u--, v--;
        f[u] |= 1 << v;
        f[v] |= 1 << u;
    }
    for (int i = 0; i < n; i++) {
        f[i] |= 1 << i;
    }

    dp[(1 << n) - 1] = 0;

    for (int mask = (1 << n) - 2; mask >= 0; mask--) {
        dp[mask] = n;
        for (int i = 0; i < n; i++) {
            if (mask & (1 << i)) {
                dp[mask] = min (dp[mask], 1 + dp[mask | f[i]]);
            }
        }
    }

    int start = 0;
    for (int i = 1; i < n; i++) {
        if (dp[1 << i] <= dp[1 << start]) {
            start = i;
        }
    }

    cout << dp[1 << start] << "\n";
    int mask = 1 << start, curRes = dp[mask];
    while (mask != (1 << n) - 1) {
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) && 1 + dp[mask | f[i]] == curRes) {
                cout << i + 1 << "\n";
                mask |= f[i];
                curRes--;
            }
        }
    }
    cout << "\n";
}