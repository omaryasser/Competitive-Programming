#include<bits/stdc++.h>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

int MOD = 1000000007;
unordered_map<int,int> memo;

int power (int a, int b) {
    int res = 1;
    while (b) {
        if (b & 1) res = 1ll * res * a % MOD;
        a = 1ll * a * a % MOD;
        b >>= 1;
    }
    return res;
}
int dp (int rem) {
    if (memo.find(rem) != memo.end())
        return memo[rem];
    if (rem == 1) return 1;

    int res = power(2, rem - 1) - 1;
    if (res < 0) res += MOD;
    for (int i = 2; 1ll * i * i <= rem; i++) {
        if (rem % i == 0) {
            res -= dp(rem / i);
            if (res < 0) res += MOD;
            if (i != rem / i) {res -= dp(i); if (res < 0) res += MOD;}
        }
    }
    return memo[rem] = res;
}

int main() {
    FAST;

    int x, y; cin >> x >> y;
    if (y % x) {return puts("0");}
    cout << dp(y / x) << "\n";

    return 0;
}