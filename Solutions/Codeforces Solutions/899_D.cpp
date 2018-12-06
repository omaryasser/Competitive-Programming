#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

ll append (ll d, ll num) {
    string s = to_string(d) + to_string(num);
    return stoll(s);
}

ll can (ll maxx, ll wanted) {
    ll start = wanted / 2 - 10;
    while(start + start - 1 < wanted) start++;
    return max(0ll, min(wanted - 1, maxx) - start + 1);
}
int main() {
    FAST;

    ll n; cin >> n;
    if (n <= 4) cout << n * (n - 1) / 2 << "\n";
    else {
        ll best = 5;
        int nines = 9;
        while (best * 10 <= n) best = best * 10, nines = nines * 10 + 9;
        ll res = 0;
        for (int i = 0; i < 10; i++) {
            res += can(n, append(i, nines));
        }
        cout << res << "\n";
    }
}