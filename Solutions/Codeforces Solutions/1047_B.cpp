#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
#define all(v) v.begin(), v.end()
#define ll long long
using namespace std;

int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n; cin >> n;
    int mx = 0;
    while(n--){
        int a, b;
        cin >> a >> b;
        mx = max(mx, a + b);
    }
    cout << mx << "\n";
}