#include <bits/stdc++.h>

using namespace std;
typedef long long ll;

int MOD = 1000000007;
const int MAX = 1000001;
int cnt[26], fac[MAX], poww[MAX];

ll soFar;
    int all;

    ll calc(int changedIdx, int up) {
        soFar = soFar * poww[all] % MOD;
        soFar = soFar * fac[all + up] % MOD;
        soFar = soFar * fac[cnt[changedIdx]] % MOD;
        soFar = soFar * poww[cnt[changedIdx] + up] % MOD;
        all += up;
        return soFar;
    }

    int powww(int a, int e) {
        ll res = 1;
        while (e > 0) {
            if ((e & 1ll) == 1) res = res * a % MOD;
            a = (int) (1ll * a * a % MOD);
            e >>= 1ll;
        }
        return (int) res;
    }

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

fac[0] = 1;
        for (int i = 1; i < 1000001; i++)
            fac[i] = (int) (1ll * fac[i - 1] * i % MOD);
        for (int i = 0; i < 1000001; i++)
            poww[i] = powww(fac[i], MOD - 2);


        string a, c; cin >> a >> c;
        for (int i = 0; i < a.length(); i++) cnt[a[i] - 'a']++;
        int start = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a[i] == c[i]) {
                cnt[a[i] - 'a']--;
                start++;
            } else break;
        }

        all = 0;
        soFar = 1;
        for (int i = 0; i < 26; i++) {
            all += cnt[i];
            soFar = soFar * poww[cnt[i]] % MOD;
        }
        soFar = soFar * fac[all] % MOD;

        int res = 0;
        // put between
        for (int put = a[start] - 'a' + 1; put < c[start] - 'a'; put++) {
            if (cnt[put] != 0) {
                res += calc(put, -1);
                cnt[put]--;
                if (res >= MOD) res -= MOD;
                calc(put, +1);
                cnt[put]++;
            }
        }

        int n = a.length();
        for (int idx = start; idx < n - 1; idx++) {
            calc(a[idx] - 'a', -1);
            cnt[a[idx] - 'a']--;
            for (int putNxt = a[idx + 1] - 'a' + 1; putNxt < 26; putNxt++) {
                if (cnt[putNxt] > 0) {
                    res += calc(putNxt, -1);
                    cnt[putNxt]--;
                    if (res >= MOD) res -= MOD;
                    calc(putNxt, +1);
                    cnt[putNxt]++;
                }
            }

        }
        for (int idx = start; idx < n - 1; idx++) {
            calc(a[idx] - 'a', +1);
            cnt[a[idx] - 'a']++;
        }

        for (int idx = start; idx < n - 1; idx++) {
            if (cnt[c[idx] - 'a'] > 0) {
                calc(c[idx] - 'a', -1);
                cnt[c[idx] - 'a']--;
                for (int putNxt = 0; putNxt < c[idx + 1] - 'a'; putNxt++) {
                    if (cnt[putNxt] > 0) {
                        res += calc(putNxt, -1);
                        cnt[putNxt]--;
                        if (res >= MOD) res -= MOD;
                        calc(putNxt, +1);
                        cnt[putNxt]++;
                    }
                }
            } else break;
        }

        cout << res << "\n";

}