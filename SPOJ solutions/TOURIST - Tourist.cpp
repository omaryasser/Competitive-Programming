#include <bits/stdc++.h>
using namespace std;

int H , W;
const int MAX = 105;
string grid [MAX];
int INF = 100000;
int memo[MAX][MAX][MAX];

bool valid (int i , int j) {return i >= 0 && j >= 0 && i < H && j < W && (grid[i][j] != '#');}
int dp (int x1 , int y1 , int x2) {
	int y2 = x1 + y1 - x2;
	if (x1 == H - 1 && y1 == W - 1 && x2 == H - 1 && y2 == W - 1) return grid[x1][y1] == '*';
	if (!valid (x1 , y1) || !valid (x2 , y2)) return - INF;
	if (memo[x1][y1][x2] != - 1) return memo[x1][y1][x2];
	int here = max(0 , (grid[x1][y1] == '*') + (grid[x2][y2] == '*') - (x1 == x2 && y1 == y2));
	int f = here + dp(x1 + 0 , y1 + 1 , x2 + 0) ;
	int s = here + dp(x1 + 0 , y1 + 1 , x2 + 1) ;
	int t = here + dp(x1 + 1 , y1 + 0 , x2 + 0) ;
	int fo = here + dp(x1 + 1 , y1 + 0 , x2 + 1) ;
	return memo[x1][y1][x2] = max(f , max(s , max(t , fo)));
}
int main (){
	int T ; cin >> T;
	while(T -- ){
		cin >> W >> H;
		for (int i = 0 ; i < H ; ++ i) cin >> grid[i];
		memset(memo , - 1 , sizeof(memo));
		cout << dp(0 , 0 , 0) << "\n";
	}
	return 0;
}


