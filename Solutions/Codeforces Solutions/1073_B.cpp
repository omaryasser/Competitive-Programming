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
    vector<int> a(n);
    vector<int> pos(2e5 + 10);
    f(i, 0, n){
        cin >> a[i];
        pos[a[i]] = i;
    }
    int last_removed = -1;
    f(i, 0, n){
        if(i) cout << " ";
        int b; cin >> b;
        if(pos[b] <= last_removed)
            cout << 0;
        else {
            cout << pos[b] - last_removed;
            last_removed = pos[b];
        }
    }
    cout << "\n";
}