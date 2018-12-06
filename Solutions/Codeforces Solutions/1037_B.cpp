#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int main () {
    int n, s;
    scanf("%d %d", &n, &s);
    vector<int> v(n);
    for(int i = 0; i < n; i++) {
        cin >> v[i];
    }
    sort(v.begin(), v.end());
    ll res = 0;
    for(int i = 0; i < n; i++) {
        if(i < n / 2) {
            if(v[i] > s) res += abs(v[i] - s);
        } else if(i > n / 2) {
            if(v[i] < s) res += abs(v[i] - s);
        } else {
            res += abs(v[i] - s);
        }
    }
    cout << res << "\n";
}