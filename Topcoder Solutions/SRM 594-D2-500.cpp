#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)
#define all(x) x.begin(),x.end()
#define sz(x) (int)x.size()

typedef long long ll;
using namespace std;



class AstronomicalRecordsEasy {
public :
	int minimalPlanets(vector <int> a, vector <int> b) {
		int n = sz(a), m = sz(b);
		int best = n + m;
		unordered_set<int> st;
		FOR(i, 0, m - 1) st.insert(b[i]);

		FOR(i, 0, n - 1) {
			FOR(j, 0, m - 1) {
				int found = 0;
				FOR(k, 0, n - 1) {
					if((a[k] * b[j]) % a[i] == 0) 
						found += st.count((a[k] * b[j]) / a[i]);
				}
				best = min(best, n + m - found);
			}
		}
		return best;
	}
};


int main () {
	cerr << (1<<19)*2*20*10*(19)<<"\n";
}
