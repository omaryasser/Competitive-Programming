#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

char s[5];
bool check (ll a, ll b) {
    printf("%lld %lld\n", a, b);
    fflush(stdout);
    scanf("%s", s);
    return s[0] == 'Y';
}

int main() {
    ll n;
    int k;
    scanf("%lld %d", &n, &k);
    srand(time(NULL));
    ll lo = 1, hi = n;
    for(int cnt = 0; ; cnt++) {
        ll mid = lo + hi >> 1;
        if(check(lo, mid)) {
            if(lo == mid) {
                return 0;
            }
            hi = mid;
        } else {
            lo = mid + 1;
        }
        lo = max(1ll, lo - k);
        hi = min(hi + k, n);
        while (hi - lo <= 50) {
            int random = rand() % (hi - lo + 1);
            if(check(lo + random, lo + random)) {
                return 0;
            }
            lo = max(1ll, lo - k);
            hi = min(hi + k, n);
        }
    }

}