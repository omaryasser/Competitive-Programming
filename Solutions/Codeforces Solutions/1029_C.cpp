#include <bits/stdc++.h>

using namespace std;


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    multiset<int> l, r;
    vector<pair<int,int> > v;
    cin >> n;
    for(int i = 0; i < n; i++) {
        int a, b;
        cin >> a >> b;
        l.insert(a), r.insert(b);
        v.push_back({a, b});
    }
    int mx = 0;
    for(int i = 0; i < n; i++) {
        l.erase(l.find(v[i].first)), r.erase(r.find(v[i].second));
        mx = max(mx, *r.begin() - *l.rbegin());
        l.insert(v[i].first), r.insert(v[i].second);
    }
    cout << mx << "\n";
}