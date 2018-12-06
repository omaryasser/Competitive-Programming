#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)

using namespace std;


int rev(int num, int k){
    int res = 0;
    f(i, 0, k)if(!(num & (1 << i)))res |= 1 << i;
    return res;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n, k; cin >> n >> k;
    vector<int> v(n);
    f(i, 0, n)cin >> v[i];
    unordered_map<int,int> cnt;
    cnt[0]++;
    int XOR = max(v[0], rev(v[0], k));
    cnt[XOR]++;
    ll good = XOR != 0;
    f(i, 1, n){
        int x = v[i];
        int x_ = rev(x, k);
        int bad_x = cnt[XOR ^ x], bad_x_ = cnt[XOR ^ x_];
        if(bad_x < bad_x_)good += i + 1 - bad_x, cnt[XOR ^ x]++, XOR ^= x;
        else good += i + 1 - bad_x_, cnt[XOR ^ x_]++, XOR ^= x_;
    }

    unordered_map<int,int> cnt2;
    cnt2[0]++;
    int XOR2 = v[n - 1];
    cnt2[XOR2]++;
    ll good2 = XOR2 != 0;
    for(int i = n - 2; i >= 0; i--){
        int x = v[i];
        int x_ = rev(x, k);
        int bad_x = cnt2[XOR2 ^ x], bad_x_ = cnt2[XOR2 ^ x_];
        if(bad_x < bad_x_)good2 += n - i - bad_x, cnt2[XOR2 ^ x]++, XOR2 ^= x;
        else good2 += n - i - bad_x_, cnt2[XOR2 ^ x_]++, XOR2 ^= x_;
    }

    cout << max(good, good2) << "\n";
}