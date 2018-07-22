#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)
#define all(x) x.begin(),x.end()
#define sz(x) (int)x.size()

typedef long long ll;
using namespace std;



string s = "";
int memo[2 * 2501][2 * 2501];
int n;

int dp (int l, int r) {
	if(l == r) return true;
	if(l + 1 == r) return s[l] == s[r];

	int &ret = memo[l][r];
	if(ret != -1) return ret;

	ret = s[l] == s[r] && dp(l + 1, r - 1);
	return ret;
}
class PalindromicSubstringsDiv2 {
public :
	int count(vector <string> a, vector <string> b) {
		FOR(i, 0, sz(a) - 1) s += a[i];
		FOR(i, 0, sz(b) - 1) s += b[i];
		int cnt = 0;
		n = (int)s.length();
		memset(memo, -1, sizeof memo);
		FOR(i, 0, n - 1) FOR(j, i, n - 1) {
			cnt += dp(i, j);
		}
		return cnt;
	}
};
