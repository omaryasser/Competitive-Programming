#include <bits/stdc++.h>
#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)

using namespace std;



int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    ll n, m; cin >> n >> m;
    ll mn = max(0ll, n - m * 2);
    ll mx = 0;
    f(go, 1, n + 1){
        int rem = n - go;
        if(1ll * rem * (rem - 1) / 2 >= m)mx = go;
    }
    cout << mn << " " << mx << "\n";
}