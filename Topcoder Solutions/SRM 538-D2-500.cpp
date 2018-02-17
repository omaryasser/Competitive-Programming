#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)
#define all(x) x.begin(),x.end()
#define sz(x) (int)x.size()

typedef long long ll;
using namespace std;




class EvenRoute {
public :
	string isItPossible(vector <int> x, vector <int> y, int wantedParity) {
		FOR(i, 0, sz(x) - 1) 
			if(((x[i] + y[i]) & 1) == wantedParity) return "CAN";
		return "CANNOT";
	}
};

int main () {
	NoRepeatPlaylist p;
	cout << p.numPlaylists(4, 0, 4) << "\n";
}
