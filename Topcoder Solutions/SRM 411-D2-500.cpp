#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)

typedef long long ll;
using namespace std;



const int n_ = 51;
int f[n_][26];
string arr[n_];
int n, len;
string s;
int memo[n_];

int inf = 52;
int dp (int idx) {
	if(idx == len) {
		return 0;
	}

	int &ret = memo[idx];
	if(ret != -1) {
		return ret;
	}

	int cnt[26];
	FOR(i, 0, 25) {
		cnt[i] = 0;
	}

	ret = inf;
	FOR(i, idx, len - 1) {
		cnt[s[i] - 'a']++;
		FOR(j, 0, n - 1) {
			bool ok = 1;
			FOR(k, 0, 25) {
				ok &= f[j][k] == cnt[k];
			}
			if(ok) {
				int diff = 0;
				FOR(k, idx, i) {
					diff += arr[j][k - idx] != s[k];
				}
				ret = min(ret, diff + dp(i + 1));
			}
		}
	}
	return ret;
}
class SentenceDecomposition {
public :

	int decompose(string sentence, vector <string> validWords) {
		n = (int)validWords.size();
		s = sentence;
		len = (int)s.length();
		FOR(i, 0, n - 1) {
			arr[i] = validWords[i];
			FOR(j, 0, (int)validWords[i].length() - 1) {
				f[i][validWords[i][j] - 'a']++;
			}
		}
		memset(memo, -1, sizeof memo);
		int res = dp(0);
		return res > len ? -1 : res;
	}
};

int main () {
	SentenceDecomposition p;
	string x[11] = {"we", "were", "here", "my", "is", "mom", "here", "si", "milk", "where", "si"};
	std::vector<string> v(x, x + sizeof x / sizeof x[0]);
	cout << p.decompose("ommwreehisymkiml", v) << "\n";
}

