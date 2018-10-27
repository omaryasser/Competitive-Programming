/* Handling doubles */
/*
EPS=1e-9
Integers   |       Doubles

 a == b    | Math.abs(a - b) < EPS
 a <= b    | a < b + EPS
 a >= b    | a + EPS > b
 a < b     | a + EPS < b
 a > b     | a > b + EPS
 */


double PI = acos(-1.0);
double EPS = 1e-9;
double DEG_to_RAD(double d) {return d * PI / 180.0;}
double RAD_to_DEG(double r) {return r * 180.0 / PI;}

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

double sq(double x) {return x * x;}
double dist(point p1, point p2) {return sqrt(sq(p1.x - p2.x) + sq(p1.y - p2.y));}

// rotate p with angle theta counter-clockwise around the origin
point rotate(point rotate, point around, double theta) {
	double rad = DEG_to_RAD(theta);
	double s = sin(rad), c = cos(rad);

	// translate point back to origin
	double dx, dy;
	rotate.x = dx = rotate.x - around.x;
	rotate.y = dy = rotate.y - around.y;
	// rotate around the origin
	double xNew = rotate.x * c - rotate.y * s;
	double yNew = rotate.x * s + rotate.y * c;

	// translate it back
	xNew += around.x;
	yNew += around.y;

	return point(xNew, yNew);
}

point rotate(double cx, double cy, double angle, point p) {
	double s = sin(angle);
	double c = cos(angle);

	// translate point back to origin:
	p.x -= cx;
	p.y -= cy;

	// rotate point
	double xnew = p.x * c - p.y * s;
	double ynew = p.x * s + p.y * c;

	// translate point back:
	p.x = xnew + cx;
	p.y = ynew + cy;
	return p;
}

struct vec {
	double x, y;
	vec(double _x, double _y) :
			x(_x), y(_y) {
	}
};

vec toVec(point a, point b) {
	return vec(b.x - a.x, b.y - a.y);
}

double cross(vec a, vec b) {
	return a.x * b.y - a.y * b.x;
}

double dot(vec a, vec b) {
	return (a.x * b.x + a.y * b.y);
}

double norm_sq(vec v) {
	return v.x * v.x + v.y * v.y;
}

vec scale(vec v, double s) {
	return vec(v.x * s, v.y * s);
}

point translate(point p, vec v) {return point(p.x + v.x , p.y + v.y);}


vec normalize(vec v) {
	double d = sqrt(norm_sq(v));
	return scale(v, 1 / d);
}

bool ccw(point p, point q, point r) {
	return cross(toVec(p, q), toVec(p, r)) > 0;
}

// returns true if point r is on the same line as the line pq
bool collinear(point p, point q, point r) {
	return fabs(cross(toVec(p, q), toVec(p, r))) < EPS;
}

bool between(point wanted , point p, point q)
{
	double x = wanted.x , y = wanted.y;
	return x < max(p.x, q.x) + EPS && x + EPS > min(p.x, q.x)
			&& y < max(p.y, q.y) + EPS && y + EPS > min(p.y, q.y);
}

//returns true if it is on the line defined by a and b
bool onLine(point wanted, point a, point b) {
	if (a == b)
		return wanted == a;
	return abs(cross(toVec(a, b), toVec(a, wanted))) < EPS;
}

//returns true if it is on the ray whose start point is a and passes through b
bool onRay(point wanted, point a, point b) {
	if (a == b)
		return wanted == a;
	vec f = normalize(toVec(a, b)), s = normalize(toVec(a, wanted));
	return f.x == s.x && f.y == s.y;
	return true;
}

bool onSegment(point wanted, point a, point b) {
	if (a == b)
		return wanted == a;
	return onRay(wanted, a, b) && onRay(wanted, b, a);
}

double angle(point a, point o, point b)  // angle AOB
{
	vec oa = toVec(o, a), ob = toVec(o, b);
	return acos(dot(oa, ob) / sqrt(norm_sq(oa) * norm_sq(ob)));
}

double distToLine(point p, point a, point b) //distance between point p and a line defined by points a, b (a != b)
{
	if (a == b)
		dist(p , a);
	// formula: c = a + u * ab
	vec ap = toVec(a , p), ab = toVec(a , b);
	double u = dot(ap , ab) / norm_sq(ab);
	point c = translate(a , scale(ab , u));
	return dist(p , c);
}
// Another way: find closest point and calculate the distance between it and p

double distToLineSegment(point p, point a, point b) {
	vec ap = toVec(a, p), ab = toVec(a, b);
	double u = dot(ap , ab) / norm_sq(ab);
	if (u < 0.0)
		return dist(p , a);
	if (u > 1.0)
		return dist(p , b);
	return distToLine(p, a, b);
}
