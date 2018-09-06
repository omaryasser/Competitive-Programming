#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

vector<int> manacher(string ss) {
    int ll = (int)ss.length();
    vector<char> s(2 * ll + 1);
    for (int i = 0; i < 2 * ll + 1; i++) {
        if ((i & 1) != 0) s[i] = ss[i / 2];
        else s[i] = '#';
    }
    int n = (int)s.size();
    vector<int> d(n);
    for(int i = 0, l = 0, r = -1; i < n; ++i)
    {
        int k = i > r ? 1 : min(r - i + 1, d[l + r - i]);
        while(i + k < n && i - k >= 0 && s[i + k] == s[i - k])
            ++k;
        d[i] = k--;
        if(i + k > r) { l = i - k; r = i + k; }
    }

    return d;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    string s;
    while(cin >> s) {
        int res = (int)s.length();
        vector<int> d = manacher(s);
        for (int i = 0, sz = (int)d.size(); i < sz; i++) {
            if(d[i] + i == sz) {
                res = min(res, (i - d[i] + 1) / 2);
            }
        }
        string first = s.substr(0, res);
        reverse(first.begin(), first.end());
        cout << s << first << "\n";
    }

    return 0;
}
