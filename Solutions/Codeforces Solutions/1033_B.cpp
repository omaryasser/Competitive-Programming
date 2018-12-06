#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
#define all(v) v.begin(), v.end()
#define ll long long
using namespace std;

bool prime(ll x){
    if(x == 1)return 1;
    for(ll d = 2; d * d <= x; d++)if(x % d == 0)return 0;
    return 1;
}
int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    int tc; cin >> tc;
    while(tc--){
        ll a , b; cin >> a >> b;
        if(prime(a - b) && prime(a + b) && ((a - b) == 1 || (a + b) == 1) && (((a - b) != 1 || (a + b) != 1)) ) cout << "YES\n";
        else cout << "NO\n";
    }
}