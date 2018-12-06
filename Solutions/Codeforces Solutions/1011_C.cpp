#include <bits/stdc++.h>

typedef long long ll;

using namespace std;

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n, m;
    cin >> n >> m;
    vector<int> a(n), b(n);
    for(int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for(int i = 0; i < n; i++) {
        cin >> b[i];
    }
    vector<int> c(n << 1);
    for(int i = 0; i < n - 1; i++) {
        c[i << 1] = a[i];
        c[i << 1 | 1] = b[i + 1];
    }
    c[(n - 1) << 1] = a[n - 1];
    c[(n - 1) << 1 | 1] = b[0];

    double lo = 0, hi = 1e9 + 10;
    for(int cnt = 0; cnt <= 50000; cnt++) {
        double mid = (lo + hi) / 2;
        bool can = 1;
        double fuel = mid;
        for(int x : c) {
            double needed = (m + fuel) / x;
            if(fuel + 1e-7 >= needed) {
                fuel -= needed;
            } else {
                can = 0;
            }
        }
        if(can) {
            hi = mid;
        } else {
            lo = mid;
        }
    }

    if(hi > 1e9) cout << "-1\n";
    else cout << fixed << setprecision(8) << hi << "\n";
}