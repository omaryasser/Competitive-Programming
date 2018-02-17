#include <bits/stdc++.h>
#define pb(x) push_back(x)
#define bug cout<<"HERE"<<endl;
#define SSTR( x ) static_cast< std::ostringstream & >( \
        ( std::ostringstream() << std::dec << x ) ).str()
#define all(v) v.begin(),v.end()
#define FOR(i,n) for(int i=0;i<n;++i)
#define FOR2(i,x,n) for(int i=x;i<n;++i)
#define FOR3(i,n) for(int i=n;i>=0;--i)
#define fast 	ios_base::sync_with_stdio(0); cin.tie(0);
#define inp freopen("input.txt", "r", stdin);
#define out freopen("output.txt", "w", stdout);

using namespace std;

typedef long long ll;
typedef vector<int> vi;

struct point {
	double x,y,z;
	point (double xx , double yy , double zz) :  x(xx) , y (yy) , z (zz) {}
};

double sq (double x) {return x * x ;}
double dist (point p1 , point p2) {
	return sqrt (sq (p1.x - p2.x) + sq (p1.y - p2.y) + sq (p1.z - p2.z));
}

double EPS = 1e-9;
vector<double> x , y , z;
int res [10];
bool curRes [10];
int main() {

//	inp
//	out

	for (int i = 0 ;; ++ i) {
		double f , s , t ; cin >> f >> s >> t ;
		if (f < EPS && s < EPS && t < EPS) break;
		x.pb(f) ; y.pb(s) ; z.pb(t);
	}

	int sz = x.size();
	FOR (i , sz) {
		point cur (x[i] , y[i] , z[i]);
		memset(curRes , false , sizeof(curRes));
		FOR (j , sz) {
			if (i == j) continue;
			double d = (dist(cur , point (x[j] , y[j] , z[j])));
			if (floor(d) + EPS < 10) {
				curRes[(int)floor(d)] = 1;
			}
		}
		FOR (i , 10) if (curRes[i]){
			res[i] ++;
			break;
		}
	}

	FOR (i , 10) cout << setw(4) << res[i] ;
	cout << "\n";

	return 0;
}
