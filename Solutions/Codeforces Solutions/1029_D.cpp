#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int main() {
//    ios_base::sync_with_stdio(0);
//    cin.tie(0);

    int n, k;
    scanf("%d %d", &n, &k);
//    n = 200000, k= 999999999;

    vector<int> v(n), len(n);
    unordered_map<int,int> mp[11];
    for(int i = 0; i < n; i++) {
        scanf("%d", &v[i]);
//        v[i] = rand() %(int)1e9 + 1;
        int tmp = v[i];
        int len_ = 0;
        while(tmp) {
            len_++;
            tmp /= 10;
        }
        mp[len_][v[i] = v[i] % k]++;
        len[i] = len_;
    }
    int p10[11];
    p10[0] = 1;
    for(int i = 1; i < 11; i++) {
        p10[i] = (int)(1ll * p10[i - 1] * 10 % k);
    }

    ll res = 0;
    for(int i = 0; i < n; i++) {
        for(int len_ = 1; len_ < 11; len_++) {
            int cur = (int)(1ll * v[i] * p10[len_] % k);
            int rem = (k - cur);
            rem = rem == k ? 0 : rem;
            if(mp[len_].find(rem) != mp[len_].end()) {
                res += mp[len_][rem];
            }
            if(len[i] == len_ && rem == v[i]) {
                res--;
            }
        }
    }
    printf("%lld\n", res);
}