#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 1000001;
long long coprimes[MAX_N];
void num_of_coprimes() {
	for (int i = 0; i <= MAX_N; ++i)
		coprimes[i] = i;

	for (int i = 2; i <= MAX_N; ++i)
		if (coprimes[i] == i)
			for (int j = i; j <= MAX_N; j += i)
				coprimes[j] -= coprimes[j] / i;
}

int main() {
	num_of_coprimes();
	int T ; scanf("%d" , &T);
	while (T -- ) {
		int n ; scanf("%d" , &n);
		printf("%lld\n" , coprimes[n]);
	}
	return 0;
}
