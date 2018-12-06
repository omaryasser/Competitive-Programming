#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int k, pA, pB;
int MOD = (int)1e9 + 7;

int power (ll a, int e) {
    ll res = 1;
    while(e) {
        if (e & 1) res = res * a % MOD;
        a = a * a % MOD;
        e >>= 1;
    }
    return (int)res;
}

int oneOverpB;

int memo[1002][1002];
int ADD (int a, int b) {return a + b >= MOD ? a + b - MOD : a + b;}
int SUB (int a, int b) {return a - b < 0 ? a - b + MOD : a - b;}
int MULT (int a, int b) {return (int)(1ll * a * b % MOD);}

int dp (int As, int subsequences) {
    if (subsequences >= k) return subsequences;
    if (As + subsequences >= k) return SUB(ADD(ADD(subsequences , As) , oneOverpB) , 1);
    int &ret = memo[As][subsequences];
    if (ret != -1) return ret;
    return ret = ADD(MULT(pA, dp(As + 1, subsequences)), MULT(pB, dp(As, subsequences + As)));
}
int main() {
    FAST;

    int a, b;
    cin >> k >> a >> b;
    pA = (int)(1ll * a * power(a + b, MOD - 2) % MOD);
    pB = (int)(1ll * b * power(a + b, MOD - 2) % MOD);
    oneOverpB = power(pB, MOD - 2);
    memset(memo, -1, sizeof(memo));
    cout << dp(1, 0);
}