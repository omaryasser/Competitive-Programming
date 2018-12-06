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

    int n;
    cin >> n;
    vector<int> a(n);
    f(i, 0, n){
        cin >> a[i];
    }

    for(int i = n - 1; i >= 1; i--)
        a[i] -= a[i - 1];

    vector<int> ret;
    f(k, 1, n + 1){
        bool ok = 1;
        f(i, k, n){
            ok &= a[i] == a[i % k];
        }
        if(ok)ret.push_back(k);
    }
    cout << (int)ret.size() << "\n";
    f(i, 0, (int)ret.size()){
        if(i)cout << " ";
        cout << ret[i];
    }
    cout << "\n";
}