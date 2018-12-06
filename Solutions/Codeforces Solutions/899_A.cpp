#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

int n;
int cnt[3];
int main() {
    FAST;

    cin >> n;
    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        cnt[x]++;
    }

    cout << min(cnt[2], cnt[1]) + max(0, (cnt[1] - min(cnt[2], cnt[1])) / 3) << "\n";
    
}