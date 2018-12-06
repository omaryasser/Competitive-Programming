#include <bits/stdc++.h>
using namespace std;
#define ll long long
#define mp(x, y) make_pair(x, y)
#define all(x) x.begin(), x.end()
#define f(i, x, n) for (int i = x; i < n; i++)

int main () {
    ios_base::sync_with_stdio(0); cin.tie(0);
    ll n, k;
    cin >> n >> k;
    cout << (k + n - 1) / n;
}