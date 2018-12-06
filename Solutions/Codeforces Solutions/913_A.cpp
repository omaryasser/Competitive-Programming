#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)


using namespace std;
typedef long long ll;

int main() {
    FAST;

    ll n, m;
    cin >> n >> m;

    if (n > 32) cout << m << "\n";
    else cout << m % (1ll << n) << "\n";
}