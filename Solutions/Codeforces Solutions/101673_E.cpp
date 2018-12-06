#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)

using namespace std;

int mod = 998244353;
int power(ll a, ll b){
    ll res = 1;
    while(b){
        if(b & 1ll)res = res * a % mod;
        b >>= 1ll;
        a = a * a % mod;
    }
    return res;
}

ll inv(ll a){return power(a, mod - 2);}

ll cnt(ll A, ll len){
    ll a_len = power(A, len);
    ll mul = a_len * a_len % mod;
    ll add = (mul + a_len) % mod;
    return add * inv(2) % mod;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    ll n, m, a; cin >> n >> m >> a;
    vector<ll> b(m);
    f(i, 0, m)cin >> b[i];

    ll res = cnt(a, b[0]);
    f(i, 1, m)res = res * cnt(a, b[i] - b[i - 1]) % mod;
    cout << res * power(a, n - 2 * b[m - 1]) % mod << "\n";
}