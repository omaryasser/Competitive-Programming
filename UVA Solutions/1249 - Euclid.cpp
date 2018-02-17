#include <bits/stdc++.h>
#define pb(x) push_back(x)
#define bug cout<<"HERE"<<endl;
#define numToStr( x ) static_cast< std::ostringstream & >( \
        ( std::ostringstream() << std::dec << x ) ).str()
#define clr(x,y) memset(x,y,sizeof(x))
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
	double x, y;
	point() {
		x = y = 0.0;
	}
	point(double _x, double _y) :
			x(_x), y(_y) {
	}
};

double area(point a, point b, point c) {
	return abs(
			a.x * b.y - a.y * b.x + b.x * c.y - b.y * c.x + c.x * a.y
					- c.y * a.x) / 2.0;
}

double sq(double x) {
	return x * x;
}
double dist(point p1, point p2) {
	return sqrt(sq(p1.x - p2.x) + sq(p1.y - p2.y));
}

struct vec {
	double x, y;
	vec(double _x, double _y) :
			x(_x), y(_y) {
	}
};

point translate(point p, vec v) {
	return point(p.x + v.x, p.y + v.y);
}

double norm_sq(vec v) {
	return v.x * v.x + v.y * v.y;
}

vec toVec(point a, point b) {
	return vec(b.x - a.x, b.y - a.y);
}

vec scale(vec v, double s) {
	return vec(v.x * s, v.y * s);
}

vec normalize(vec v) {
	double d = sqrt(norm_sq(v));
	return scale(v, 1 / d);
}

double dot(vec a, vec b) { return (a.x * b.x + a.y * b.y); }

double angle(point a, point o, point b)  // angle AOB
{
	vec oa = toVec(o, a), ob = toVec(o, b);
	return acos(dot(oa, ob) / sqrt(norm_sq(oa) * norm_sq(ob)));
}

double xa, ya, xb, yb, xc, yc, xd, yd, xe, ye, xf, yf;
point p[6];
double PI = acos(-1.0);

double DEG_to_RAD(double d) {
	return d * PI / 180.0;
}
double RAD_to_DEG(double r) {
	return r * 180.0 / PI;
}
int main() {
//	fast
	while (1) {
		bool finished = 1;
		FOR (i , 6)
		{
			double x, y;
			cin >> x >> y;
			p[i].x = x;
			p[i].y = y;
			finished &= (abs(p[i].x) < EPS && abs(p[i].y) < EPS);
		}
		if (finished)
			return 0;

		double A = area(p[3], p[4], p[5]);
		double height = A / dist(p[0], p[1]);
		double a = angle (p[2] , p[0] , p[1]);
		double heightWanted = height / sin (a);
		point H = translate(p[0], scale(normalize(toVec(p[0], p[2])), heightWanted));
		point G = translate(p[1], scale(normalize(toVec(p[0], p[2])), heightWanted));
		cout << fixed << setprecision(3) << G.x << " " << G.y << " " << H.x
				<< " " << H.y << "\n";
	}
	return 0;
}

