#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)
#define pb(x) push_back(x)
#define F first
#define S second

using namespace std;

const int n_ = 1e5 + 10;
int a[n_];
int n;
vector<int> divs[n_];
int mod = 1e9 + 7;
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    unordered_map<int,ll> cnt;
    f(i, 0, n){
        cin >> a[i];
        for(int div = 1; 1ll * div * div <= a[i]; div++){
            if(a[i] % div == 0){
                divs[i].push_back(div);
                if(div != a[i] / div)
                    divs[i].push_back(a[i] / div);
            }
        }
    }

    f(i, 0, n)sort(all(divs[i]));
    ll res = 0;
    f(i, 0, n){
        for(int div : divs[i]){
            if(cnt.find(div - 1) != cnt.end()) {
                res += cnt[div - 1];
                if(res >= mod)res -= mod;
            }
        }
        int sz = (int)divs[i].size();
        f(j, 0, sz){
            int div = divs[i][sz - 1 - j];
            if(cnt.find(div - 1) != cnt.end()) {
                cnt[div] += cnt[div - 1];
                if(cnt[div] >= mod)cnt[div] -= mod;
            }
            if(div == 1){
                res++, cnt[div]++;
                if(res >= mod)res -= mod;
                if(cnt[div] >= mod)cnt[div] -= mod;
            }
        }
    }
    cout << res << "\n";
}