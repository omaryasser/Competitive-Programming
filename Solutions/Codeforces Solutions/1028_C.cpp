#include <bits/stdc++.h>

using namespace std;

typedef long long ll;



int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n;
    cin >> n;
    vector<int> r1(n), r2(n), c1(n), c2(n);
    multiset<int> up, down, left, right;
    for(int i = 0; i < n; i++) {
        cin >> r1[i] >> c1[i] >> r2[i] >> c2[i];
        up.insert(r1[i]);
        left.insert(c1[i]);
        down.insert(r2[i]);
        right.insert(c2[i]);
    }

    for(int i = 0; i < n; i++) {
        up.erase(up.find(r1[i]));
        left.erase(left.find(c1[i]));
        down.erase(down.find(r2[i]));
        right.erase(right.find(c2[i]));
        if(*(--left.end()) <= *right.begin() && *(--up.end()) <= *down.begin()) {
            cout << *down.begin() << " " << *right.begin() << "\n";
            return 0;
        }
        up.insert(r1[i]);
        left.insert(c1[i]);
        down.insert(r2[i]);
        right.insert(c2[i]);
    }
}