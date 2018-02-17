#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i < n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)

using namespace std;

int dp[2][41];
class NumberofFiboCalls {
public :

	vector <int> fiboCallsMade(int n) {
		dp[0][0] = 1;
		dp[1][1] = 1;
		FOR(i, 2, n + 1) {
			dp[0][i] = dp[0][i - 1] + dp[0][i - 2];
			dp[1][i] = dp[1][i - 1] + dp[1][i - 2];
		}
		vector<int> res;
		res.pb(dp[0][n]);
		res.pb(dp[1][n]);
		return res;
	}
};

int main () {
	NumberofFiboCalls p;
	vector<int> v = p.fiboCallsMade(2);
	cout << v[0] << " " << v[1] << "\n";
}

