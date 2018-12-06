#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int main() {
    ll num[3];
    scanf("%lld %lld", &num[0], &num[1]);
    vector<ll> div[3];
    num[2] = num[0] + num[1];
    for(int i = 0; i < 3; i++) {
        for(ll j = 1; j * j <= num[i]; j++) {
            if(num[i] % j == 0) {
                div[i].push_back(-j);
                div[i].push_back(-num[i] / j);
            }
        }
    }
    for(int i = 0; i < 3; i++) {
        sort(div[i].begin(), div[i].end());
    }

    ll root = sqrt(num[2]);
    while(root * root < num[2]) root++;
    while(root * root > num[2]) root--;

    ll mx = 1;
    for(ll div_ = 1; div_ <= root; div_++) {
        if(num[2] % div_ == 0) {
            ll div2 = num[2] / div_;
            ll big = max(div_, div2);
            ll small = min(div_, div2);
            for(int i = 0; i < 2; i++) {
                auto it = lower_bound(div[i].begin(), div[i].end(), -big);
                if (it != div[i].end()) {
                    if (-num[i] / *it <= small) {
                        mx = div_;
                    }
                }
            }

        }
    }
    cout << 2ll * mx + 2 * (num[2] / mx) << "\n";
}