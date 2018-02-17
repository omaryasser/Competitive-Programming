#include <bits/stdc++.h>
using namespace std;

int main(){
	long long res[47][2];
	res[0][0] = 0 , res[0][1] = 1;
	for (int i = 1 ; i <= 47 ; ++ i) {
		long long newMales = res[i - 1][1] + res[i - 1][0];
		long long newFemales = res[i - 1][0] + 1;
		res[i][0] = newMales , res[i][1] = newFemales;
	}

	while (true) {
		int n;
		scanf("%d" , &n);
		if (n == - 1) break;
		cout << res[n][0] << " " << res[n][0]+res[n][1] << "\n";
	}
	return 0;
}
