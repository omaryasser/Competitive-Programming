#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)


using namespace std;
typedef long long ll;


int main() {
    FAST;

    ll n, k;
    cin >> n >> k;
    ll pw = 1;
    while(pw <= n) pw <<= 1ll;

    if (k == 1) cout << n << "\n";
    else cout << pw-1 << "\n";
}