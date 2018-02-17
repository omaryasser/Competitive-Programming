#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)
#define all(x) x.begin(),x.end()

typedef long long ll;
using namespace std;

int n, m, p;
const int n_ = 101;
int memo[n_][n_];
int mod = 1e9 + 7;

int dp (int idx, int done) {
	if(idx == p) {
		return done == n;
	}

	int &ret = memo[idx][done];
	if(ret != -1) return ret;

	ret = 0;
	if(done != n) {
		ret = ((ll)ret + (n - done) * (ll)dp(idx + 1, done + 1)) % mod;
	}

	int can = done - m;
	if(can > 0) {
		ret = ((ll)ret + can * (ll)dp(idx + 1, done)) % mod;
	}
	if(ret > mod) ret -= mod;
	return ret;
}
class NoRepeatPlaylist {
public :
	int numPlaylists(int N, int M, int P) {
		n = N;
		m = M;
		p = P;
		memset(memo, -1, sizeof memo);
		return dp(0, 0);
	}
};

int main () {
	NoRepeatPlaylist p;
	cout << p.numPlaylists(4, 0, 4) << "\n";
}
