#include <bits/stdc++.h>

using namespace std;
const int MAX = 1000001;
class GCDGraph{
public :
	int parent [MAX];
	int getP(int i) {return parent[i] == i ? i : parent[i] = getP(parent[i]);}
	string possible(int n, int k, int x, int y) {
		for (int i = 1 ; i <= n ; ++ i) parent[i] = i;
		for (int i = 1 ; i <= n ; ++ i) {
			if (i > k) {
				int big = getP(i);
				for (int j = i + i; j <= n ; j += i) {
					if (big != getP(j)) {
						parent[getP(j)] = big;
					}
				}
			}
		}
		return getP(x) == getP(y) ? "Possible" : "Impossible";
	}
};

int main(){
	GCDGraph c ;
	cout << c.possible(1000000,
			1000,
			12345,
			54321) << "\n";
	return 0;
}
