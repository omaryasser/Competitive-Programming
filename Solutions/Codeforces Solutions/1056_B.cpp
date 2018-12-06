#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)
#define pb(x) push_back(x)
#define F first
#define S second

using namespace std;


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n, m;
    cin >> n >> m;
    vector<int> cnt(m);
    f(i, 0, m){
        int cnts = (n + i) / m;
        cnt[i * i % m] += cnts;
    }
    ll res = 0;
    f(i, 0, m)res += 1ll * cnt[i] * cnt[(m - i) % m];
    cout << res << "\n";
}