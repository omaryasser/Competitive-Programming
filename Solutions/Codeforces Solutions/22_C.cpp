#include <bits/stdc++.h>
using namespace std;
#define ll long long
#define mp(x, y) make_pair(x, y)
#define all(x) x.begin(), x.end()
#define f(i, x, n) for (int i = x; i < n; i++)

int main () {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n, m, v;
    cin >> n >> m >> v;
    if(m < n - 1){
        cout << "-1\n";
    }else{
        if(m > n - 1 + max(0ll, 1ll * (n - 2) * (n - 3) / 2)){
            cout << "-1\n";
        }else{
            m -= n - 1;
            vector<int> others;
            f(i, 1, n + 1){
                if(v != i){
                    cout << v << " " << i << "\n";
                    others.push_back(i);
                }
            }
            others.erase(--others.end());
            int i = 0, j = 1;
            while(m--){
                cout << others[i] << " " << others[j] << "\n";
                j++;
                if(j == (int)others.size()){
                    i++;
                    j = i + 1;
                }
            }
        }
    }
}