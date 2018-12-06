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


    int tc; cin >> tc;
    while(tc--){
        ll a, b, c; cin >> a >> b >> c;
        cout << ((c + 1) / 2) * a - (c / 2) * b << "\n";
    }
}