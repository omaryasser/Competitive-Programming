#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)
#define all(x) x.begin(),x.end()
#define sz(x) (int)x.size()

typedef long long ll;
using namespace std;



const int n_ = 51;
const int H = 101;
double dp[n_][H];
double bad = -1e9;
class PillarsDivTwo {
public :
	double maximalLength(vector <int> arr, int w) {
		int n = sz(arr);
		FOR(i, 1, H) dp[0][i] = i <= arr[0] ? 0 : bad;
		FOR(i, 1, n) FOR(j, 1, H) {
			dp[i][j] = bad;
			if(j > arr[i]) continue;
			FOR(last, 1, arr[i - 1]) {
				dp[i][j] = max(dp[i][j], dp[i - 1][last] + sqrt((j - last) * (j - last) + w * w));
			} 
		}
		double best = bad;
		FOR(i, 1, arr[n - 1]) {
			best = max(best, dp[n - 1][i]);
		}
		return best;
	}
};
