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

    int n;
    cin >> n;
    unordered_map<int,int> cnt;
    f(i, 0, n){
        int c; cin >> c;
        while(c--){
            int x;
            cin >> x;
            cnt[x]++;
        }
    }

    bool first = true;
    for(auto p : cnt)
        if(p.second == n){
            if(first)first = false;
            else cout << " ";
            cout << p.first;
        }
    cout << "\n";
}