#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)

typedef long long ll;
using namespace std;

string s;
int n;
const int n_ = 51;
int inf = 53;
ll num [n_][n_];
set<ll> powers;
int dp[n_];
class CuttingBitString {
public :

	int getmin(string S) {
		s = S;
		n = (int)s.length();
		FOR(i, 0, n - 1) {
			FOR(j, i, n - 1) {
				ll a = 0;
				FOR(k, i, j) {
					a = (a << 1LL) + (s[k] - '0');
				}
				num[i][j] = a;
			}
		}
		
		FOR(i, 0, 25) {
			powers.insert(pow(5, i));
		}
		
		FOR(i, 0, n - 1) {
			dp[i] = inf;
			for (int j = i; j >= 0; j--) {
				if(s[j] != '0') {
					if(powers.count(num[j][i])) {
						dp[i] = min(dp[i], 1 + (j == 0 ? 0 : dp[j - 1]));
					}
				}
			}
		}
		return dp[n - 1] > n ? -1 : dp[n - 1];
	}
};

int main () {
	CuttingBitString p;
	p.getmin("");
}

