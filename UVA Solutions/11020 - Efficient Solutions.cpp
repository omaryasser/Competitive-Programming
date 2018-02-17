#include<bits/stdc++.h>
#define FOR(x,y) for (int x = 0 ; x < y ; ++ x)
using namespace std;

struct Pair {
	int x, y;
	bool operator <(const Pair& p) const {
		return x == p.x ? y < p.y : x < p.x;
	}
};

int main() {
//	freopen("input.txt", "r", stdin);
	ios_base::sync_with_stdio(0); cin.tie(0);

	int T;
	cin >> T;
	int t = 0 ;
	while (T--) {
	multiset<Pair> m;
		cout << "Case #" << (++t) << ":\n";
		int n;
		cin >> n;
		FOR (i , n) {
			int l, c;
			cin >> l >> c;
			Pair cur;
			cur.x = l;
			cur.y = c;
			multiset<Pair>::iterator it = m.lower_bound(cur);
			if (it == m.begin() || cur.y < (--it)->y) {
				m.insert(cur);
				it = m.upper_bound(cur);
				while (it != m.end() && it->y >= c) {
					m.erase(it++);
				}
			}
			cout << m.size() << "\n";
		}
		if (T) cout << "\n";
	}

}
