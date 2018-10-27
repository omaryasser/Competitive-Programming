#include <bits/stdc++.h>
using namespace std;
int T;
string s;
const int MAX = 6100;
int memo[MAX][MAX];
int dp (int l , int r) {
	if (l >= r) return 0;
	if (memo[l][r] != - 1) return memo[l][r];
	if (s[l] == s[r]) return memo[l][r] = dp (l + 1 , r - 1);
	int f = 1 + dp(l + 1 , r) , s = 1 + dp(l , r - 1);
	return memo[l][r] = min (f , s);
}
int main(){
	cin >> T;
	while (T -- ) {
		cin >> s;
		memset(memo , - 1 , sizeof(memo));
		cout << dp (0 , s.length() - 1) << "\n";
	}
	return 0;
}
