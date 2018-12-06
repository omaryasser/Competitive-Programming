#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)
#define pb(x) push_back(x)
#define F first
#define S second

using namespace std;


const int n_ = 3e5 + 10;
int n;
int cnt_has[n_];
int cnt[n_];
vector<int> fac[n_];
vector<int> primes;
bool isPrime[n_];

string to_b(int num){
    string s = "";
    f(i, 0, 31){
        s += (num >> i & 1) ? "1" : "0";
    }
    reverse(all(s));
    return s;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    f(i, 0, n_)isPrime[i] = 1;
    f(i, 2, n_)if(isPrime[i]){
        for(ll j = 1ll * i * i; j < n_; j += i) {
            isPrime[j] = 0;
        }
    }
    f(i, 2, n_)if(isPrime[i])primes.push_back(i);

    int n; cin >> n;
    vector<int> arr(n);
    f(i, 0, n){
        cin >> arr[i];
        if(arr[i] == 1){
            cout << "1\n";
            return 0;
        }
        for(int p : primes)
            if(p * p > arr[i])break;
            else while(arr[i] % (p * p) == 0)arr[i] /= p;
        if(!cnt[arr[i]]){
            int tmp = arr[i];
            for(int p : primes){
                if(p * p > tmp)break;
                if(tmp % p == 0){
                    fac[arr[i]].push_back(p);
                    while(tmp % p == 0)tmp /= p;
                }
            }
            if(tmp != 1)fac[arr[i]].push_back(tmp);
        }
        cnt[arr[i]]++;
    }

    f(i, 0, n){
        int sz = (int)fac[arr[i]].size();
        f(msk, 1, 1 << sz){
            int mul = 1;
            f(j, 0, sz)if(msk >> j & 1)mul *= fac[arr[i]][j];
//            cerr << arr[i] << " " << mul << "\n";
            cnt_has[mul]++;
        }
    }

//    f(i, 0, 50)cerr << i << " " << cnt_has[i] << "\n";
    int gcd = arr[0];
    f(i, 1, n)gcd = __gcd(gcd, arr[i]);
    if(gcd != 1){
        cout << "-1\n";
        return 0;
    }

    int mn = n;
    f(i, 0, n){
        int sz = (int)fac[arr[i]].size();
        vector<int> dp(1 << sz, 10);
        f(msk, 1, 1 << sz){
            int has_either = 0;
            for(int msk2 = msk; msk2 ; msk2 = msk & (msk2 - 1)) {
                int mul = 1, bit_cnt = 0;
                f(j, 0, sz)if(msk2 >> j & 1)mul *= fac[arr[i]][j], bit_cnt++;
                has_either += cnt_has[mul] * ((bit_cnt & 1) ? 1 : -1);
            }
            if(has_either != n)dp[msk] = 1;
//            if(has_either != n){
//                cerr << i << " " << arr[i] << " " << to_b(msk) << " " << has_either << " " << cnt_has[2] << " " << cnt_has[5] << "\n";
//            }
            for(int msk2 = msk; msk2 ; msk2 = msk & (msk2 - 1))
                dp[msk] = min(dp[msk], dp[msk2] + dp[msk ^ msk2]);
        }
        mn = min(mn, dp[(1 << sz) - 1] + 1);
//        cerr << mn << "\n";
    }
    cout << mn << "\n";
}