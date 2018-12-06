#include <bits/stdc++.h>

typedef long long ll;

using namespace std;

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n, m;
    cin >> n >> m;
    unordered_map<int,int> mp;
    for(int i = 0; i < m; i++) {
        int x;
        cin >> x;
        mp[x]++;
    }

    int best = 0;
    for(int can = 1; can <= 100; can++) {
        int made = 0;
        for(auto p : mp) {
            int i = p.second;
            made += i / can;
        }
        if(made >= n) {
            best = can;
        }
    }
    cout << best << "\n";
}