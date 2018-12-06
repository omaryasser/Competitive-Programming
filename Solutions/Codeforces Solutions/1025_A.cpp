#include <bits/stdc++.h>

typedef long long ll;

using namespace std;

int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0);

    int n;
    cin >> n;
    string s;
    cin >> s;
    unordered_map<char,int> mp;
    bool ok = 0;
    for(int i =0 ; i < (int)s.length(); i++) {
        mp[s[i]]++;
        ok |= mp[s[i]] > 1;
    }
    cout << (ok || s.length() == 1 ? "Yes\n" : "No\n");
}