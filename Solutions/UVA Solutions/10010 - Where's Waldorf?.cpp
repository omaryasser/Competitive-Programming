#include<bits/stdc++.h>
#define lp(x,y) for (int x = 0 ; x < y ; ++ x)
using namespace std;

string grid [51];
string p;
int patternLen;
int n , m ;
int k ;
int dx [] = {0 , 0 , 1 , - 1 , 1 , 1 , -1 , -1 };
int dy [] = {1 , -1 , 0 , 0 , 1 , -1 , 1 , -1 };
bool valid (int x , int y){
	return x >= 0 && y >= 0 && x < n && y < m;
}
bool search (int x , int y , int dir , int found){
	if (found == patternLen) return true;
	if (!valid(x , y)) return false;
	if (grid[x][y] == p[found])
		return search (x + dx[dir] , y + dy[dir] , dir , found + 1);
	else return false;
}
int main() {

	//freopen("input.txt", "r", stdin);
	ios_base::sync_with_stdio(0); cin.tie(0);
	int T; cin >> T;
	while (T -- > 0){
		cin >> n >> m;
		lp (i , n) {
			cin >> grid[i];
			transform(grid[i].begin() , grid[i].end() , grid[i].begin() , ::tolower);
		}
		cin >> k;
		lp (i , k){
			cin >> p;
			transform(p.begin() , p.end() , p.begin() , ::tolower);
			patternLen = p.length();
			bool found = false;
			int ansX = -1 , ansY = -1;
			lp (i , n) lp (j , m) lp (k , 8)
						if (!found && search(i , j , k , 0)){

							found = true;
							ansX = i ;
							ansY = j;
						}
					
			

			cout << (ansX + 1) << " " << (ansY + 1) << "\n";
		}
		if (T) cout << "\n";
	}
	return 0;
}
