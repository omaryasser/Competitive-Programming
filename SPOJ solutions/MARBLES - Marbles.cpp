#include <bits/stdc++.h>
#define fast 	ios_base::sync_with_stdio(0); cin.tie(0);
 
using namespace std;
 
long long nCr1(int N, int K) {
	if (K > N - K) K = N - K;
	long long res = 1;
	for (int i = 1; i <= K; ++i)
		res = res * (long long)(N - i + 1) / (long long)i;
	return res;
}
 
 
int main() {
	fast
	int T ;
	cin >> T;
	while (T -- ) {
		int n , k;
		cin >> n >> k;
		cout << nCr1(n - 1 , k - 1) << "\n";
	}
	return 0;
}
