#include <bits/stdc++.h>

typedef long long ll;

using namespace std;


const int n_ = 5002;
ll mem[n_ / 2][n_][2][2];
int n;
int a[n_];
ll INF = (1ll) * n_ * 2e5 + 10;
ll dp(int rem, int idx, int last, int bef_last) {
    if(idx >= n) {
        if(rem == 0) return 0;
        else return INF;
    }
    ll &ret = mem[rem][idx][last][bef_last];
    if(ret != -1) return ret;

    ret = INF;
    // take this one
    if(!last && rem) {
        ll last_height = a[idx - 1];
        if(bef_last) {
            last_height = min(last_height, 1ll * a[idx - 2] - 1);
        }
        ret = max(0ll, last_height - (a[idx] - 1)) + dp(rem - 1, idx + 1, 1, last);
    }
    // dont take
    if(last) {
        ll done = max(0ll, 1ll * a[idx] - (a[idx - 1] - 1));
        ret = min(ret, done + dp(rem, idx + 1, 0, last));
    } else {
        ret = min(ret, dp(rem, idx + 1, 0, last));
    }
    return ret;
}
int main() {
    memset(mem, -1, sizeof mem);
    scanf("%d", &n);
//    n = 5000;
    n++;
    a[0] = -1;
    for(int i = 1; i < n; i++) {
        scanf("%d", a + i);
//        if(i & 1) a[i] = 1;
//        else a[i] = 100000;
    }

    for(int i = 0; i < 5000; i++) {
        for(int j = 0; j < 200; j++) {
            dp(rand() % 200, (rand() % (5000 - 2)) + 1, 0, 0);
        }
    }

    for(int i = 0; i < (n + 1) / 2; i++) {
        if(i) printf(" ");
        if(n % 2 == 0 || (n % 2 == 1 && i != (n + 1) / 2 - 1))printf("%lld", dp(i + 1, 1, 0, 0));
    }
    printf("\n");
}