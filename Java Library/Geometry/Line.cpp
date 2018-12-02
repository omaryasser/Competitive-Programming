struct point {
	double x,y;
	point () {x = y = 0.0;}
	point (double _x , double _y) : x(_x) , y(_y) {}

	bool operator < (point other) const {
		if (abs(x - other.x) > EPS)
			return x < other.x;
		return y < other.y;
	}

	bool operator == (point other) const {
		return abs(x - other.x) < EPS && abs(y - other.y) < EPS ;
	}

};

double sq (double x) {return x * x;}
double dist (point p1 , point p2) { return sqrt(sq(p1.x - p2.x) + sq(p1.y - p2.y));}

// rotate p with angle theta counter-clockwise around the origin
point rotate(point p, double theta) {
  double rad = DEG_to_RAD(theta);
  double s = sin(rad) , c = cos (rad);
  return point(p.x * c - p.y * s,  p.x * s + p.y * c);
}

point rotate(point rotate , point around ,double theta){
    double rad = DEG_to_RAD(theta);
    double s = sin(rad) , c = cos(rad);

    // translate point back to origin
    double dx , dy ;
    rotate.x = dx = rotate.x - around.x;
    rotate.y = dy = rotate.y - around.y;

    // rotate around the origin
    double xNew = rotate.x * c - rotate.y * s;
    double yNew = rotate.x * s + rotate.y * c;

    // translate it back
    xNew += dx;
    yNew += dy;

    return point(xNew , yNew);
}

point rotate_point(double cx,double cy,double angle,point p)
{
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

void toLine(point p, double m , line &l) { l.a = -m; l.b = 1; l.c =  -(l.a * p.x + p.y); }

bool areParallel (line l1 , line l2) {return abs (l1.a - l2.a) < EPS && abs(l1.b - l2.b) < EPS;}
bool areSame (line l1 , line l2) {return areParallel(l1 , l2) && abs (l1.c - l2.c) < EPS ;}

bool areIntersect(line l1, line l2, point &p) {
  if (areParallel(l1, l2)) return false;            // no intersection
  // solve system of 2 linear algebraic equations with 2 unknowns
  p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
  // special case: test for vertical line to avoid division by zero
  if (abs(l1.b) > EPS) p.y = -(l1.a * p.x + l1.c);
  else                  p.y = -(l2.a * p.x + l2.c);
  return true;
}

void closestPoint(line l, point p, point &ans) {
  line perpendicular;         // perpendicular to l and pass through p
  if (fabs(l.b) < EPS) {              // special case 1: vertical line
    ans.x = -(l.c);   ans.y = p.y;      return; }

  if (fabs(l.a) < EPS) {            // special case 2: horizontal line
    ans.x = p.x;      ans.y = -(l.c);   return; }

  pointSlopeToLine(p, 1 / l.a, perpendicular);          // normal line
  // intersect line l with this perpendicular line
  // the intersection point is the closest point
  areIntersect(l, perpendicular, ans);
}

void reflectionPoint(line l, point p, point &ans) {
  point b;
  closestPoint(l, p, b);                     // similar to distToLine
  vec v = toVec(p, b);                             // create a vector
  ans = translate(translate(p, v), v); }         // translate p twice

double dot(vec a, vec b) { return (a.x * b.x + a.y * b.y); }

double norm_sq(vec v) { return v.x * v.x + v.y * v.y; }

double distToLine(point p, point a, point b, point &c) {
  // formula: c = a + u * ab
  vec ap = toVec(a, p), ab = toVec(a, b);
  double u = dot(ap, ab) / norm_sq(ab);
  c = translate(a, scale(ab, u));                  // translate a to c
  return dist(p, c); }           // Euclidean distance between p and c

// returns the distance from p to the line segment ab defined by
// two points a and b (still OK if a == b)
// the closest point is stored in the 4th parameter (byref)
double distToLineSegment(point p, point a, point b, point &c) {
  vec ap = toVec(a, p), ab = toVec(a, b);
  double u = dot(ap, ab) / norm_sq(ab);
  if (u < 0.0) { c = point(a.x, a.y);                   // closer to a
    return dist(p, a); }         // Euclidean distance between p and a
  if (u > 1.0) { c = point(b.x, b.y);                   // closer to b
    return dist(p, b); }         // Euclidean distance between p and b
  return distToLine(p, a, b, c); }          // run distToLine as above

double angle(point a, point o, point b) {  // returns angle aob in rad
  vec oa = toVec(o, a), ob = toVec(o, b);
  return acos(dot(oa, ob) / sqrt(norm_sq(oa) * norm_sq(ob))); }
