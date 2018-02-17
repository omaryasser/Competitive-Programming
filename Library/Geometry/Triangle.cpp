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

double sq(double x) {
	return x * x;
}
double dist(point p1, point p2) {
	return sqrt(sq(p1.x - p2.x) + sq(p1.y - p2.y));
}

double area(double ab , double bc , double ca) {
	double s = (ab + bc + ca) / 2.0;
	return sqrt(s * (s - ab) * (s - bc) * (s - ca));//take care of overflow
}

double area2(point a , point b , point c) {
	return abs(
			a.x * b.y - a.y * b.x + b.x * c.y - b.y * c.x + c.x * a.y
					- c.y * a.x) / 2.0;
}
/*
 * 1. We take the absolute value because the result could be negative which means that point q is on
 *    the left of the line segment pr
 *
 * 2. If the area is zero, then the three points are collinear
 */

double area3(point a , point b , point c) {
	return 0.5 * ((c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x));
}

double perimeter(double ab , double bc , double ca)
{
	return ab + bc + ca;
}

double rInCircle(double ab, double bc, double ca) {
	return area(ab, bc, ca) / (0.5 * perimeter(ab, bc, ca));
}

double rInCircle(point a, point b, point c) {
	return rInCircle(dist(a, b), dist(b, c), dist(c, a));
}

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

bool areIntersect(line l1, line l2, point &p) {
  if (areParallel(l1, l2)) return false;            // no intersection
  // solve system of 2 linear algebraic equations with 2 unknowns
  p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
  // special case: test for vertical line to avoid division by zero
  if (fabs(l1.b) > EPS) p.y = -(l1.a * p.x + l1.c);
  else                  p.y = -(l2.a * p.x + l2.c);
  return true;
}

struct vec {
  double x, y;
  vec(double _x, double _y) : x(_x), y(_y) {}
};

point translate(point p, vec v) {return point(p.x + v.x , p.y + v.y);}

vec scale(vec v, double s) {return vec(v.x * s, v.y * s);}

vec toVec(point a, point b) {return vec(b.x - a.x, b.y - a.y);}

int inCircle(point p1, point p2, point p3, point &ctr, double &r) {
	r = rInCircle(p1, p2, p3);
	if (fabs(r) < EPS)
		return 0;                   // no inCircle center

	line l1, l2;                    // compute these two angle bisectors
	double ratio = dist(p1, p2) / dist(p1, p3);
	point p = translate(p2, scale(toVec(p2, p3), ratio / (1 + ratio)));
	pointsToLine(p1, p, l1);

	ratio = dist(p2, p1) / dist(p2, p3);
	p = translate(p1, scale(toVec(p1, p3), ratio / (1 + ratio)));
	pointsToLine(p2, p, l2);

	areIntersect(l1, l2, ctr);           // get their intersection point
	return 1;
}

double rCircumCircle(double ab, double bc, double ca) {
	return ab * bc * ca / (4.0 * area(ab, bc, ca));
}

double rCircumCircle(point a, point b, point c) {
	return rCircumCircle(dist(a, b), dist(b, c), dist(c, a));
}

// assumption: the required points/lines functions have been written
// returns 1 if there is a circumCenter center, returns 0 otherwise
// if this function returns 1, ctr will be the circumCircle center
// and r is the same as rCircumCircle
int circumCircle(point p1, point p2, point p3, point &ctr, double &r) {
	double a = p2.x - p1.x, b = p2.y - p1.y;
	double c = p3.x - p1.x, d = p3.y - p1.y;
	double e = a * (p1.x + p2.x) + b * (p1.y + p2.y);
	double f = c * (p1.x + p3.x) + d * (p1.y + p3.y);
	double g = 2.0 * (a * (p3.y - p2.y) - b * (p3.x - p2.x));
	if (fabs(g) < EPS)
		return 0;

	ctr.x = (d * e - b * f) / g;
	ctr.y = (a * f - c * e) / g;
	r = dist(p1, ctr);   
	return 1;
}

// returns true if point d is inside the circumCircle defined by a,b,c
int inCircumCircle(point a, point b, point c, point d) {
	return (a.x - d.x) * (b.y - d.y)
			* ((c.x - d.x) * (c.x - d.x) + (c.y - d.y) * (c.y - d.y))
			+ (a.y - d.y)
					* ((b.x - d.x) * (b.x - d.x) + (b.y - d.y) * (b.y - d.y))
					* (c.x - d.x)
			+ ((a.x - d.x) * (a.x - d.x) + (a.y - d.y) * (a.y - d.y))
					* (b.x - d.x) * (c.y - d.y)
			- ((a.x - d.x) * (a.x - d.x) + (a.y - d.y) * (a.y - d.y))
					* (b.y - d.y) * (c.x - d.x)
			- (a.y - d.y) * (b.x - d.x)
					* ((c.x - d.x) * (c.x - d.x) + (c.y - d.y) * (c.y - d.y))
			- (a.x - d.x)
					* ((b.x - d.x) * (b.x - d.x) + (b.y - d.y) * (b.y - d.y))
					* (c.y - d.y) > 0 ? 1 : 0;
}

bool canFormTriangle(double a, double b, double c) {
	return (a + b > c) && (a + c > b) && (b + c > a);
}

double areaMedians(double ma, double mb, double mc)		//medians of the triangle
{
  double s = (ma + mb + mc) / 2.0;							//max(ma, mb, mc) < s
  return sqrt(s * (s - ma) * (s - mb) * (s - mc)) * 4.0 / 3.0;
}

double areaHeights(double ha, double hb, double hc)		//heights of the triangle
{
  double ha_1 = 1.0 / ha, hb_1 = 1.0 / hb, hc_1 = 1.0 / hc;
  double s = (ha_1 + hb_1 + hc_1) / 2.0;
  return 1.0 / (sqrt(s * (s - ha_1) * (s - hb_1) * (s - hc_1)) * 4.0);
}
