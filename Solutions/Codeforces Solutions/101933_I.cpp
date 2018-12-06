#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)

using namespace std;


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int x, y, z, t1, t2, t3; cin >> x >> y >> z >> t1 >> t2 >> t3;
    if(abs(x - y) * t1 < (abs(x - z) + abs(x - y)) * t2 + t3 * 3){
        cout << "NO\n";
    }
    else {
        cout << "YES\n";
    }

}