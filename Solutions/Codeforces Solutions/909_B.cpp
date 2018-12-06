#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, n) for (int i = 0; i < n; i++)

using namespace std;
typedef long long ll;

int n;
vector<pair<int,int> > v;
int cnt[101];

int main() {
    FAST;

    cin >> n;
    int mx = 1;
    FOR (i, n)
    for (int j = i; j < n; j++)
        for (int k = i; k <= j; k++) {
            cnt[k]++, mx = max(mx, cnt[i]);
        }
    cout << mx << "\n";
}