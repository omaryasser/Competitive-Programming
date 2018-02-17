double EPS = 1e-9;
double DEG_to_RAD(double d) {
	return d * PI / 180.0;
}
double RAD_to_DEG(double r) {
	return r * 180.0 / PI;
}

#define PI acos(-1.0);

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
	double dx = p.x - c.x, dy = p.y - c.y;
	double euc = dx * dx + dy * dy, rSq = r * r;
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

double circum(double r) {
	return 2 * PI * r;
}

double area(double r) {
	return PI * r * r;
}

double arcLength(double deg, double r) {
	return deg / 360.0 * circum(r);
}

double chordLength(double deg, double r) {
	return 2 * r * sin(DEG_to_RAD(deg) / 2.0);
}

double chordLength2(double deg, double r) {
	return 2 * r * r * (1 - cos(DEG_to_RAD(deg)));
}

double sectorArea(double deg, double r) {
	return deg / 360.0 * area(r);
}

double segmentArea(double deg, double r) {
	return sectorArea(deg, r) - r * r * sin(DEG_to_RAD(deg)) / 2.0;
}

double sq(double x) {
	return x * x;
}
double dist(point p1, point p2) {
	return sqrt(sq(p1.x - p2.x) + sq(p1.y - p2.y));
}

bool intersect(point c, double r, point cirC, double cirR) { // should return false if one contains the other
	return (c, cirC) <= r + cirR + EPS && dist(c, cirC) + EPS >= abs(r - cirR);
}

struct line {
	double a, b, c;
};
void pointsToLine(point p1, point p2, line &l) {
	if (abs(p1.x - p2.x) < EPS) {
		l.a = 1.0;
		l.b = 0.0;
		l.c = -p1.x;
	} else {
		l.a = -(double) (p1.y - p2.y) / (p1.x - p2.x);
		l.b = 1.0;
		l.c = -(double) (l.a * p1.x) - p1.y;
	}
}

bool between(point wanted, point p, point q) {
	return wanted.x < max(p.x, q.x) + EPS && wanted.x + EPS > min(p.x, q.x)
			&& wanted.y < max(p.y, q.y) + EPS && wanted.y + EPS > min(p.y, q.y);
}

//returns true if the circle intersects with the line segment defined by p and q at one or two points
bool intersect(point c, double r, point p, point q) {
	return distToLineSegment(c,p,q) + EPS < r;
}
