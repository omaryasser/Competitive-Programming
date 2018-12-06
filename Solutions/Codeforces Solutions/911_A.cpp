#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int main() {
    FAST;

    int n;
    cin >> n;
    ll mn = 100000000000;
    ll arr[n];
    FOR(i, 0, n) cin >> arr[i], mn = min (mn, arr[i]);

    int lst = 0;
    FOR(i, 0, n) if (arr[i] == mn) {lst = i; break;}

    ll bst = 100000000000;
    FOR(i, lst + 1, n) if (arr[i] == mn) bst = min (bst, (ll)i - lst), lst = i;
    cout << bst << "\n";
}