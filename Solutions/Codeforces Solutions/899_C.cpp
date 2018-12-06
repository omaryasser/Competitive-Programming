#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

ll n;
int main() {
    FAST;

    cin >> n;
    if (n == 2) cout << "1\n"
                "1 1\n";
    else {
        ll wanted = (n * (n + 1) / 2) / 2;
        ll end = abs((n * (n + 1) / 2) - wanted - wanted);
        vector<int> res;
        for (int i = n; i >= 1; i--) {
            if(wanted >= i) {
                res.push_back(i);
                wanted -= i;
            }
        }
        cout << end << "\n" << res.size();
        for (int i = 0; i < res.size(); i++) {
            cout << " ";
            cout << res[i];
        }
        cout << "\n";
    }
}