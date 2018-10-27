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

	bool operator <(point other) const {
		if (abs(x - other.x) > EPS)
			return x < other.x;
		return y < other.y;
	}

	bool operator ==(point other) const {
		return abs(x - other.x) < EPS && abs(y - other.y) < EPS;
	}

};

int insideCircle(point p, point c, double r) {
	int dx = p.x - c.x, dy = p.y - c.y;
	int euc = dx * dx + dy * dy, rSq = r * r;
	return euc + EPS < rSq ? 0 : abs(euc - rSq) < EPS ? 1 : 2; // inside / border / outside
}

bool circle2PtsRad(point p1, point p2, double r, point &c) {
	double d2 = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	double det = r * r / d2 - 0.25;
	if (det < 0.0)
		return false;
	double h = sqrt(det);
	c.x = (p1.x + p2.x) * 0.5 + (p1.y - p2.y) * h;
	c.y = (p1.y + p2.y) * 0.5 + (p2.x - p1.x) * h;
	return true;
}  // to get the other center, reverse p1 and p2

int n;
vector<point> points;
double r;
int main() {
//	fast

	while (cin >> n) {
		if (!n)
			break;
		points.clear();
		FOR (i , n)
		{
			double x, y;
			cin >> x >> y;
			point p (x ,y);
			points.pb(p);
		}
		cin >> r;

		bool can = 0;

		FOR (i , n)
		{
			FOR1 (j , i + 1 , n)
			{
				point center;
				circle2PtsRad(points[i], points[j], r, center);
				bool ok = 1;
				FOR (z , n)
				{
					if (insideCircle(points[z], center, r) == 2) {
						ok = 0;
						break;
					}
				}
				if (ok) {
					can = 1;
					break;
				}
				circle2PtsRad(points[j], points[i], r, center);
				ok = 1;
				FOR (z , n)
				{
					if (insideCircle(points[z], center, r) == 2) {
						ok = 0;
						break;
					}
				}
				if (ok) {
					can = 1;
					break;
				}
			}
		}

		if (can)
			cout << "The polygon can be packed in the circle.\n";
		else
			cout << "There is no way of packing that polygon.\n";
	}

	return 0;
}

