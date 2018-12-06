#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)


using namespace std;
typedef long long ll;

int main() {
    FAST;

    ll a, b;
    cin >> a >> b;
    ll x, y, z;
    cin >> x >> y >> z;
    cout << max(0ll, x * 2 + y - a) + max(0ll, 1 * y + 3 * z - b) << "\n";
}