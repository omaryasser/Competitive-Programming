#include <bits/stdc++.h>
#define fast 	ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;

const int MAX = 2001;
int days;
int v[MAX];
int memo [MAX][MAX];

int dp (int l , int r) {
	int curDay = l + days - r;
	if (curDay == days + 1) return 0;
	if (memo[l][r] != - 1) return memo[l][r];
	return memo[l][r] = max (v[l] * curDay + dp(l + 1 , r) , v[r] * curDay + dp(l , r - 1));
}
int main() {
	fast
	cin >> days;
	memset(memo , - 1 , sizeof(memo));
	for (int i = 0 ; i < days ; ++ i)
		cin>> v[i];
	cout << dp (0 , days - 1) << "\n";
	return 0;
}
