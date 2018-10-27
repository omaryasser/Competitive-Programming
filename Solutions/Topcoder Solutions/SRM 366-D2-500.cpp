// BOTTOM UP
#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)
#define all(x) x.begin(),x.end()
#define sz(x) (int)x.size()

typedef long long ll;
using namespace std;




class ChangingSounds {
public :
	int maxFinal(vector <int> arr, int beginLevel, int maxLevel) {
		int n = sz(arr);
		bool dp[n + 1][maxLevel + 1];
		FOR(i, 0, n) FOR(j, 0, maxLevel) dp[i][j] = false;
		dp[0][beginLevel] = true;

		
		FOR(i, 1, n) {
			FOR(j, 0, maxLevel) {
				if(arr[i - 1] <= j) dp[i][j] = dp[i - 1][j - arr[i - 1]];
				if(arr[i - 1] + j <= maxLevel) dp[i][j] |= dp[i - 1][j + arr[i - 1]];
			}
		}
		int max = -1;
		FOR(i, 0, maxLevel) if(dp[n][i]) max = i;
		return max;
	}
};



// TOP DOWN
#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)
#define all(x) x.begin(),x.end()
#define sz(x) (int)x.size()

typedef long long ll;
using namespace std;


const int n_ = 51;
vector<int> arr;
int memo[n_][50 * 1000 + 10];
int inf = 1e7;
int n;

int dp (int idx, int cur, int maxLevel) {
	if(idx == n) return cur;
	int &ret = memo[idx][cur];
	if(ret != -1) return ret;

	ret = -inf;
	if(cur + arr[idx] <= maxLevel) 
		ret = dp (idx + 1, cur + arr[idx], maxLevel);
	if(cur - arr[idx] >= 0) 
		ret = max(ret, dp(idx + 1, cur - arr[idx], maxLevel));
	return ret;
}
class ChangingSounds {
public :
	int maxFinal(vector <int> changeIntervals, int beginLevel, int maxLevel) {
		arr = changeIntervals;
		memset(memo, -1, sizeof memo);
		n = sz(changeIntervals);
		int res = dp(0, beginLevel, maxLevel);
		if(res <= -1e5) return -1;
		else return res;		
	}
};


