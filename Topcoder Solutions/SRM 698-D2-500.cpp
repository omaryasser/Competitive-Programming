#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)

typedef long long ll;
using namespace std;

string ss;
int n;
const int n_ = 51;
int memo[n_][51][51];

int dp (int l, int r, int s) {
	if(l == s) {
		return 0;
	}
	if (r == n) {
		return 0;
	}

	int &ret = memo[l][r][s];
	if(ret != -1) {
		return ret;
	}

	ret = 0;
	if(ss[l] == ss[r]) {
		ret = max(ret, 1 + dp(l + 1, r + 1, s));
	}
	ret = max(ret, dp(l + 1, r, s));
	ret = max(ret, dp(l, r + 1, s));
	return ret;
}
class RepeatStringEasy {
public :

	int maximalLength(string sss) {
		ss = sss;
		n = (int)ss.length();
		memset(memo, -1, sizeof memo);
		int best = 0;
		FOR(i, 0, n - 1) {
			FOR(j, i + 1, n - 1) {
				best = max(best, dp(i, j, j));
			}
		}
		return 2 * best;
	}
};

int main () {
//	CuttingBitString p;
//	p.getmin("");
}

