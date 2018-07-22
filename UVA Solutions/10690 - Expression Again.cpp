#include <bits/stdc++.h>
#define mp(x,y) make_pair(x,y)

using namespace std;

int N , M;
const int MAX = 51;
const int MAX_SUM = (2550 << 1) + 50;
int arr [MAX_SUM];
int offset = 2550;
int sum = 0;
int INF = 1e5;
pair<int,int> memo[MAX + MAX][MAX][MAX_SUM];
bool computed[MAX + MAX][MAX][MAX_SUM];
pair<int , int> dp(int idx , int taken , int sumS) {
	if(taken == N) return mp(sumS * (sum - sumS) , sumS * (sum - sumS));
	if (idx == N + M) return mp(-INF , INF);
	if(computed[idx][taken][sumS + offset]) return memo[idx][taken][sumS + offset];
	computed[idx][taken][sumS + offset] = true;
	pair<int , int> take = dp(idx + 1 , taken + 1 , sumS + arr[idx]);
	pair<int , int> leave = dp(idx + 1 , taken , sumS);
	return memo[idx][taken][sumS + offset] = mp(max(take.first , leave.first) , min(take.second , leave.second));
}
int main(){
	while(cin >> N >> M) {
		sum = 0 ;
		for (int i = 0 ; i < N + M ; ++ i) {
			cin >> arr[i];
			sum += arr[i];
		}
		if (N > M) swap(N , M);
		memset(computed , false ,sizeof(computed));
		pair<int , int> res = dp(0 , 0 , 0);
		cout << res.first << " " << res.second << "\n";

	}
	return 0;
}
