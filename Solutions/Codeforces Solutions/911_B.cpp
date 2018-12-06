#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int n, a, b;
int main() {
    FAST;

    cin >> n >> a >> b;
    int best = 0;
    FOR(mn, 0, a + b) {
        bool ok = true;
        int remA = a, remB = b;
        ok &= remA >= mn && remB >= mn;
        remA -= mn; remB -= mn;
        FOR(i, 0, n - 2) if (remA >= mn) remA -= mn; else if (remB >= mn) remB -= mn; else ok = false;
        if (ok) best = mn;
    }
    cout << best << "\n";
}