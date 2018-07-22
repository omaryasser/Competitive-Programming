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

double EPS = 1e-9;
struct point {
	double x,y;
	point () {x = y = 0.0;}
	point (double _x , double _y) : x(_x) , y(_y) {}
};

struct line {
	double a , b , c;
};

void pointsToLine (point p1 , point p2 , line &l) {
	if (abs (p1.x - p2.x) < EPS) {l.a = 1.0; l.b = 0.0 ; l.c = -p1.x;}
	else {
		l.a = - (double)(p1.y - p2.y) / (p1.x - p2.x);
		l.b = 1.0;
		l.c = - (double)(l.a * p1.x) - p1.y;
	}
}

bool areParallel (line l1 , line l2) {return abs (l1.a - l2.a) < EPS && abs(l1.b - l2.b) < EPS;}
bool areSame (line l1 , line l2) {return areParallel(l1 , l2) && abs (l1.c - l2.c) < EPS ;}

int areIntersect(line l1, line l2, point &p) {
  if (areParallel(l1, l2)) return areSame(l1 , l2) ? 1 : 0;            // no intersection
  // solve system of 2 linear algebraic equations with 2 unknowns
  p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
  // special case: test for vertical line to avoid division by zero
  if (abs(l1.b) > EPS) p.y = -(l1.a * p.x + l1.c);
  else                  p.y = -(l2.a * p.x + l2.c);
  return 2;
}

int n ;

int main() {

//	inp
//	out
	fast
	double x1 , y1 , x2 , y2 , x3 , y3 , x4 , y4 ;

	cout << "INTERSECTING LINES OUTPUT\n";
	cin >> n ;
	while (n -- ) {
		line l1 , l2 ;
		cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3 >> x4 >> y4 ;
		pointsToLine(point(x1 , y1) , point (x2 , y2) , l1);
		pointsToLine(point(x3 , y3) , point (x4 , y4) , l2);
		point res ;
		int num = areIntersect(l1 , l2 , res);
		if (num == 0) cout << "NONE\n";
		else if (num == 1) cout << "LINE\n";
		else cout << "POINT " << fixed << setprecision(2) << res.x << " " << res.y << "\n";
	}
	cout << "END OF OUTPUT\n";
	return 0;
}

