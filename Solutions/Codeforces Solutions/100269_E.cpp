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
    int sum = 0;
    int mx = 0;
    f(i, 0, n)cin >> a[i], sum += a[i], mx = max(mx, a[i]);

    int res = (2 * sum + n - 1) / n;
    if(((2 * sum)) % n == 0)cout << max(mx, res + 1) << "\n";
    else cout << max(mx, res) << "\n";

}