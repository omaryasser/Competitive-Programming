#include<bits/stdc++.h>
#define FOR(i,s,n) for (int i = s; i < n; i++)
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
using namespace std;

typedef long long ll;

int main() {
    FAST


    int n, k;
    cin >> n >> k;
    int mn = k + 100;
    FOR (i, 0, n) {
        int x; cin >> x;
        if (k % x == 0) mn = min(mn, k / x);
    }
    cout << mn << "\n";
    return 0;
}