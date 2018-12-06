#include <bits/stdc++.h>

using namespace std;

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    string s;
    int k;
    int n;
    cin >> n >> k >> s;
    int mx = 0;
    for(int len = 1; len <= n - 1; len++)
    {
        bool ok = 1;
        for(int i = 0; i < len; i++)
            ok &= s[i] == s[n - len + i];
        if(ok) mx = len;
    }

    string res = s;
    string rem = "";
    for(int i = mx; i < n; i++) rem += s[i];
    for(int i = 0; i < k - 1; i++) {
        res += rem;
    }
    cout << res << "\n";
}