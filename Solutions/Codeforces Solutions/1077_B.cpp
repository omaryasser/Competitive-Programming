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


    int n; cin >> n;
    vector<int> v(n);
    f(i, 0, n)cin >> v[i];
    int cnt = 0;
    f(i, 1, n - 1){
        if(!v[i] && v[i - 1] && v[i + 1]){
            v[i + 1] = 0;
            cnt++;
        }
    }
    cout << cnt << "\n";
}