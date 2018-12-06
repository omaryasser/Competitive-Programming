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

    int n, k; cin >> n >> k;
    unordered_map<ll,ll> mp;
    ll dishes = 0;
    f(i, 0, n){
        int x; cin >> x;
        mp[x]++;
        dishes = max(dishes, (mp[x] + k - 1) / k);
    }

    ll res = 0;
    for(auto p : mp)res += dishes * k - p.second;
    cout << res << "\n";
}