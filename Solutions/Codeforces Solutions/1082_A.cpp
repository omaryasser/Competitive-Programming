#include <bits/stdc++.h>

using namespace std;

int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
#ifndef ONLINE_JUDGE
    freopen("A.in", "r", stdin);
freopen("A.out", "w", stdout);
#endif


	     int tc;
    cin >> tc;
    while(tc--){
        int n, x, y, d;
        cin >> n >> x >> y >> d;
        if(abs(x - y) % d == 0){
            cout << abs(x - y) / d << "\n";
        } else {
            int to_left = (x - 1 + d - 1) / d;
            int to_rght = (n - x + d - 1) / d;

            int bst = INT_MAX;
            if((y - 1) % d == 0)bst = to_left + (y - 1) / d;
            if((n - y) % d == 0)bst = min(bst, to_rght + (n - y) / d);
            cout << (bst == INT_MAX ? -1 : bst) << "\n";
        }
    }
return 0;
}