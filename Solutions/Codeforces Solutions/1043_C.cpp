#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)
#define pb(x) push_back(x)
#define F first
#define S second

using namespace std;


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    string s;
    cin >> s;

    int n = (int)s.length();
    vector<int> res(n);

    f(i, 0, n){
        if(s[i] == 'a' && (i + 1 == n || s[i + 1] == 'b'))
            res[i] = 1;
        if(s[i] == 'b' && (i + 1 != n && s[i + 1] == 'a'))
            res[i] = 1;
    }

    f(i, 0, n){
        if(i)cout << " ";
        cout << res[i];
    }
    cout << "\n";
}