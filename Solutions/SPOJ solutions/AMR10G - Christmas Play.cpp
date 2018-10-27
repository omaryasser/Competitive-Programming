#include <bits/stdc++.h>
using namespace std;
int T , N , K;
const int MAX = 20000;
int h [MAX];
int main(){
	scanf("%d" , &T);
	while (T -- ) {
		scanf("%d %d" , &N , &K);
		for (int i = 0 ; i < N ; ++ i) scanf("%d" , &h[i]);
		sort(h , h + N);
		int res = 1000000000 + 1;
		for (int i = K - 1 ; i < N ; ++ i)
			res = min (res , h[i] - h[i - K + 1]);
		printf("%d\n" , res);
	}
	return 0;
}
