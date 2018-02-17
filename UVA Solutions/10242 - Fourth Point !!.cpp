#include <bits/stdc++.h>
#define pb(x) push_back(x)
#define bug cout<<"HERE"<<endl;
#define numToStr( x ) static_cast< std::ostringstream & >( \
        ( std::ostringstream() << std::dec << x ) ).str()
#define clr(x,y) memset(x,y,sizeof(x))
#define all(v) v.begin(),v.end()
#define FOR(i,n) for(int i=0;i<n;++i)
#define FOR1(i,x,n) for(int i=x;i<n;++i)
#define FORR(i,n) for(int i=n;i>=0;--i)
#define fast 	ios_base::sync_with_stdio(0); cin.tie(0);
#define inp freopen("input.txt", "r", stdin);
#define out freopen("output.txt", "w", stdout);

using namespace std;

typedef long long ll;
typedef vector<int> vi;

double EPS = 1e-9;

struct point {
	double x, y;
	point() {
		x = y = 0.0;
	}
	point(double _x, double _y) :
				x(_x), y(_y) {
		}
	bool operator == (point other) const {
		return abs(x - other.x) < EPS && abs(y - other.y) < EPS;
	}
};

struct vec {
  double x, y;
  vec(double _x, double _y) : x(_x), y(_y) {}
};

vec toVec(point a, point b) {return vec(b.x - a.x, b.y - a.y);}

point translate(point p, vec v) {return point(p.x + v.x , p.y + v.y);}

point points[4];

double x , y ;
int main() {
//	fast

	while (cin >> x){
		cin >> y ;
		points[0].x = x , points[0].y = y ;
		FOR1(i , 1 , 4) {
			cin >> x >> y ;
			points[i].x = x , points[i].y = y ;
		}

		point points2[3];
		FOR (i , 4) {
			FOR1 (j , i + 1 , 4) {
				if (points[i] == points[j]) points2[0] = points[i];
			}
		}
		int idx = 1;
		FOR (i , 4) if (!(points[i] == points2[0])) points2[idx ++ ] = points[i];
		point res = translate(points2[1] , toVec(points2[0] , points2[2]));
		cout << fixed << setprecision(3) << res.x << " " << res.y << "\n";
	}

	return 0;
}

