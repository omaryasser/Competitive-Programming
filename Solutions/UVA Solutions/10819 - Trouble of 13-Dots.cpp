#include <bits/stdc++.h>
using namespace std;

const int MAX = 101;
int money , N , p[MAX] , f[MAX];
int memo[101][10001];
int dp (int idx , int spent){
	if(idx == N) return 0;
	int &ret = memo[idx][spent];
	if(ret != - 1) return ret;
	int take = - 20 ;
	if(spent - (spent + p[idx] > 2000 ? 200 : 0) + p[idx] <= money) take = f[idx] + dp(idx + 1 , spent + p[idx]);
	int leave = dp(idx + 1 , spent);
	ret = max (take , leave);
	return ret ;
}
int main(){
	while(scanf("%d %d" , &money , &N) == 2){
		memset(memo , -1 , sizeof(memo));
		for(int i = 0 ; i < N ; ++ i) scanf("%d %d" , &p[i] , &f[i]);
		printf("%d\n", dp(0 , 0));
	}
}
