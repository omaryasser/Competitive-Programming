#include <bits/stdc++.h>
using namespace std;

struct Pair {
	long long money;
	int time;
	Pair(int m , int t) {
		money = m;
		time = t;
	}
	Pair (){money = - 1 , time = - 1;}
};
const int MAX = 51;
int N , T ;
int tim [MAX][MAX] ;
long long money[MAX][MAX] ;
Pair memo[MAX][1001];
long long INF = 1e17;
Pair dp (int idx , int timeSoFar) {
	if(timeSoFar > T) return Pair(INF , 1e9);
	if(idx == N - 1) return Pair(0 , 0);
	if(memo[idx][timeSoFar].time != - 1) return memo[idx][timeSoFar];
	Pair best(INF,1e9);
	for (int i = 0 ; i < N ; ++ i){
		if(i != idx) {
			Pair cur = dp(i , timeSoFar + tim[idx][i]);
			if(cur.money + money[idx][i] < best.money) {
				best.money = cur.money + money[idx][i];
				best.time = cur.time + tim[idx][i];
			}
		}
	}
	return memo[idx][timeSoFar] = best;

}
int main() {
	while(1){
		scanf("%d %d" , &N , &T);

		for (int i = 0 ; i < N ; ++ i) {
			for (int j = 0 ; j < 1001 ; ++ j) {
				memo[i][j] = Pair();
			}
		}

		if (!N && !T) break;
		for (int i = 0 ; i < N ; ++ i) {
			for (int j = 0 ; j < N ; ++ j) {
				cin >> tim[i][j];
			}
		}

		for (int i = 0 ; i < N ; ++ i) {
			for (int j = 0 ; j < N ; ++ j) {
				cin >> money[i][j];
			}
		}
		Pair res = dp(0 , 0);
		printf("%lld %d\n" , res.money , res.time);

	}
	return 0;
}
