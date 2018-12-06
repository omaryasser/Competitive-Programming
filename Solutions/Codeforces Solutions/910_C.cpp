#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

int n;
bool bad[10];
int mult[10];
int pre[7];
int main() {
    FAST;

    pre[0] = 1;
    for (int i = 1; i < 7; i++) pre[i] = pre[i - 1] * 10;

    cin >> n;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        bad[s[0] - 'a'] = true;
        for (int j = 0; j < s.length(); j++) {
            int idxFromRight = s.length() - j - 1;
            mult[s[j] - 'a'] += pre[idxFromRight];
        }
    }

    int perm[10];
    for (int i = 0; i < 10; i++) perm[i] = i;
    ll best = 1000ll * 10000001;
    do {
        if (!bad[perm[0]]) {
            ll res = 0;
            for (int i = 1; i < 10; i++)
                res += i * mult[perm[i]];
            best = min (best, res);
        }
    } while(next_permutation(perm, perm + 10));
    cout << best << "\n";
}