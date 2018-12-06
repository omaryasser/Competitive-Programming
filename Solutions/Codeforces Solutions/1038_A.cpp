#include <bits/stdc++.h>

using namespace std;

typedef long long ll;



int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n, k;
    string s;
    cin >> n >> k >> s;
    vector<int> cnt(k);
    for(int i = 0, sz = (int)s.length(); i < sz; i++) {
        cnt[s[i] - 'A']++;
    }
    int mn = cnt[0];
    for(int i = 0; i < k; i++) {
        mn = min(mn, cnt[i]);
    }

    cout << mn * k << "\n";
    return 0;
}