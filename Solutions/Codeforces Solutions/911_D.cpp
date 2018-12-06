#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int n;
const int MAX = 1501;
int arr[MAX];

int main() {
    FAST;

    cin >> n;
    FOR(i, 0, n) cin >> arr[i];
    int inv = 0;
    FOR(i, 0, n) FOR(j, i + 1, n) inv += arr[i] > arr[j];

    int m;
    cin >> m;
    int odd = inv & 1;
    while(m--) {
        int l, r;
        cin >> l >> r;
        int len = r - l + 1;
        len = len * (len - 1) / 2;
        odd ^= len & 1;
        if (odd) cout << "odd\n";
        else cout << "even\n";
    }
}