#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)

using namespace std;


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n; cin >> n;
    int finished = -1;

    f(i, 0, n){
        int x; cin >> x;
        if(x <= finished + 1)finished = max(finished, x);
        else{
            cout << i + 1 << "\n";
            return 0;
        }
    }
    cout << -1 << "\n";
}