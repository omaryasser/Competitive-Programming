#include <bits/stdc++.h>
#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)

using namespace std;



int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n, k; cin >> n >> k;
    vector<int> h(n);
    int MAX = 2e5 + 10;
    vector<int> cnt(MAX);
    int mn = MAX;
    f(i, 0, n){
        cin >> h[i];
        mn = min(mn, h[i]);
        cnt[h[i]]++;
    }
    int res = 0;
    ll cum = 0, cum2 = 0;
    for(int height = MAX - 1; height > mn; height--){
        ll was = cum, was_cum2 = cum2;
        cum += cum2 + cnt[height];
        cum2 += cnt[height];
//        if(height <= 10)cerr << height << " " << cum << " " << cum2 << "\n";
        if(cum > k){
            res++;
            cum = was_cum2 + cnt[height];
            cum2 = cum;
        }
    }
//    cerr << cum << " " << cum2 << "\n";
    if(cum)res++;
    cout << res << "\n";
}