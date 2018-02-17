#include <bits/stdc++.h>

using namespace std;


int main() {
	int tc;
	scanf("%d", &tc);

	while(tc--) {
		int m, n;
		scanf("%d %d", &m, &n);
		int x[m - 1], y[n - 1];
		for(int i = 0; i < m - 1; i++){
			int xx;
			scanf("%d", &xx);
			x[i] = xx;
		}
		for(int i = 0; i < n - 1; i++) {
			int yy;
			scanf("%d", &yy);
			y[i] = yy;
		}
		sort(x, x + m - 1);
		sort(y, y + n - 1);

		int rows = 1, cols = 1;
		long long res = 0;
		for(int j = n - 2, i = m - 2; j >= 0 || i >= 0;){
			if(i == -1){
				res += y[j] * rows;
				j--;
			}
			else if(j == -1){
				res += x[i] * cols;
				i--;
			}
			else{
				if(x[i] > y[j]){
					res += x[i] * cols;
					rows++;
					i--;
				}
				else{
					res += y[j] * rows;
					cols++;
					j--;
				}
			}
		}

		printf("%lld\n", res);
	}
}

