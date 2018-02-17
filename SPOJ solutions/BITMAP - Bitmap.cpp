#include <bits/stdc++.h>
#define fast 	ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;


int T ;
int n , m;
const int MAX = 183;
string arr [MAX];
int main() {

	cin >> T;
	while (T -- ) {
		cin >> n >> m;
		vector<pair<int ,int > > whites;
		for (int i = 0 ; i < n ; ++ i) cin >> arr[i];
		for (int i = 0 ; i < n ; ++ i)
			for (int j = 0 ; j < m ; ++ j)
				if (arr[i][j] == '1')
					whites.push_back(make_pair(i , j));
		int sz = (int)whites.size();
		for (int i = 0 ; i < n ; ++ i) {
			for (int j = 0 ; j < m ; ++ j) {
				int dist = 100000;
				for (int k = 0 ; k < sz ; ++ k)
							dist = min (dist , abs(i - whites[k].first) + abs(j - whites[k].second));
				if (j) cout << " " << dist;
				else cout << dist;
			}
			cout << "\n";
		}
	}
	return 0;
}
