#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

vector<int> compute_z(string s) {
    int n = (int)s.length();
    vector<int> z(n);
    int L = -1, R = -1;
    z[0] = n;
    for(int i = 1; i < n; i++) {
        if(i > R) {
            L = R = i;
            while(R < n && s[R] == s[R - L]) R++;
            z[i] = R - L;
            R--;
        } else {
            int k = i - L;
            if(z[k] < R - i + 1) {
                z[i] = z[k];
            } else {
                L = i;
                while(R < n && s[R] == s[R - L]) R++;
                z[i] = R - L;
                R--;
            }
        }
    }
    return z;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int n;
    string s;
    cin >> s;
    string s2 = s;
    reverse(s2.begin(), s2.end());
    n = (int)s.length();
    int m;
    cin >> m;
    int cnt = 0;
    while(m--) {
        string p;
        cin >> p;
        string p2 = p;
        reverse(p2.begin(), p2.end());
        int len = (int)p.length();
        vector<int> first(len + 1, n * 2), last(len + 1, -1);
        vector<int> prefix = compute_z(p + "#" + s), suffix = compute_z(p2 + "#" + s2);
        for(int i = len + 1; i < len + n + 1; i++) {
            int idx = i - (len + 1);
            first[prefix[i]] = min(first[prefix[i]], idx);
            idx = i - (len + 1) + suffix[i] - 1;
            if(suffix[i]) {
                last[suffix[i]] = max(last[suffix[i]], n - idx - 1);
            }
        }
        for(int i = len - 1; i >= 0; i--)
            first[i] = min(first[i], first[i + 1]);
        for(int i = len - 1; i >= 0; i--)
            last[i] = max(last[i], last[i + 1] + 1);
        for(int i = 1; i < len; i++) {
            if(first[i] + i <= last[len - i]) {
                cnt++;
                break;
            }
        }
    }
    cout << cnt << "\n";
}