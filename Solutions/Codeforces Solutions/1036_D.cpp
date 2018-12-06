#include <bits/stdc++.h>

using namespace std;
#define ll long long
#define mp(x, y) make_pair(x, y)
#define all(x) x.begin(), x.end()
#define f(i, x, n) for (int i = x; i < n; i++)


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin >> n;
    vector<ll> a(n);
    ll sum1 = 0;
    f(i, 0, n)cin >> a[i], sum1 += a[i];
    int m;
    cin >> m;
    vector<ll> b(m);
    ll sum2 = 0;
    f(i, 0, m) cin >> b[i], sum2 += b[i];
    if(sum1 != sum2) {
        cout << "-1\n";
    } else {
        int res = 0;
        ll sum1 = 0, sum2 = 0, i = 0, j = 0;
        while(i != n || j != m) {
            sum1 += a[i++];
            sum2 += b[j++];
            res++;
            while(sum1 != sum2) {
                while(sum1 < sum2) {
                    sum1 += a[i++];
                }
                while(sum1 > sum2) {
                    sum2 += b[j++];
                }
            }
        }
        cout << res << "\n";
    }
}