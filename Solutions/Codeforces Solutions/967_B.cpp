#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
#define all(v) v.begin(), v.end()
#define ll long long
using namespace std;


int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n;
    ll a, b; cin >> n >> a >> b;
    vector<ll> v(n);
    f(i, 0, n)cin >> v[i];
    if(n == 1){cout << 0 << "\n"; return 0;}
    sort(v.begin() + 1, v.end());
    ll sum = 0;
    f(i, 0, n)sum += v[i];
    if(sum * b <= a * v[0]){cout << 0 << "\n"; return 0;}
    reverse(v.begin() + 1, v.end());
    vector<ll> cum(n);
    cum[1] = v[1];
    f(i, 2, n)cum[i] = cum[i - 1] + v[i];
    int lo = 1, hi = n - 1, best = n - 1;
    while(lo <= hi){
        int m = lo + hi >> 1;
        if((sum - cum[m]) * b <= a * v[0]){
            best = m;
            hi = m - 1;
        }else lo = m + 1;
    }
    cout << best << "\n";
}