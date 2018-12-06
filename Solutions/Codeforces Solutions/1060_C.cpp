#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
#define all(v) v.begin(), v.end()
#define ll long long
using namespace std;

const int n_ = 2002;
ll x;
int len[2];
vector<ll> a[2];
ll least[2][n_];
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin >> len[0] >> len[1];
    memset(least, -1, sizeof least);
    a[0].assign(len[0], 0), a[1].assign(len[1], 0);
    f(i, 0, len[0]){
        cin >> a[0][i];
        if(i)a[0][i] += a[0][i - 1];
    }
    f(i, 0, len[1]){
        cin >> a[1][i];
        if(i)a[1][i] += a[1][i - 1];
    }
    cin >> x;
    f(k, 0, 2){
        f(i, 1, len[k] + 1){
            f(start, 0, len[k]){
                if(start + i - 1 >= len[k])continue;
                if(least[k][i] == -1 || least[k][i] > (a[k][start + i - 1] - (start == 0 ? 0ll : a[k][start - 1])))
                    least[k][i] = (a[k][start + i - 1] - (start == 0 ? 0ll : a[k][start - 1]));
            }
        }
    }
    int best = 0, best_len = len[1];
    f(i, 1, len[0] + 1){
        while(best_len && least[1][best_len] * least[0][i] > x)best_len--;
        if(least[1][best_len] * least[0][i] <= x)best = max(best, i * best_len);
    }
    cout << best << "\n";
}