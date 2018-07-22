#include <bits/stdc++.h>
using namespace std;

int H[100001];
int l , h , r;
int main(){
	int start =  100003 , end = - 1;
	while(scanf("%d %d %d" , &l , &h , &r) == 3) {
		for (int i = l ; i <= r - 1 ; ++ i) H[i] = max (H[i] , h);
		start = min (start , l);
		end = max (end , r);
	}
	int lastH = - 3;
	bool f = 1;
	for (int i = start ; i <= end ; ++ i) {
		if(H[i] != lastH) {
			if (f) printf("%d %d", i , H[i]) , f = 0;
			else printf(" %d %d", i , H[i]);
			lastH = H[i];
		}
	}
	printf("\n");
}
