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







 #define INF 1e9;
 #define EPS 1e-9;
 #define PI acos(-1.0);




 /* helper function */
 double DEG_to_RAD(double d) { return d * PI / 180.0; }
 double RAD_to_DEG(double r) { return r * 180.0 / PI; }

 /* Point struct */
 struct point { double x, y;
   point() { x = y = 0.0; }
   point(double _x, double _y) : x(_x), y(_y) {}     //Point p(0,6);
   bool operator < (point other) const {
     if (fabs(x - other.x) > EPS)
       return x < other.x;
     return y < other.y; }
   bool operator == (point other) const {
    return (fabs(x - other.x) < EPS && (fabs(y - other.y) < EPS)); } };
  struct point_i { int x, y;     // whenever possible, work with point_i
  point_i() { x = y = 0; }                      // default constructor
  point_i(int _x, int _y) : x(_x), y(_y) {} };         // user-defined

  /* Eculidian distance between two points */
  double dist(point p1, point p2) { // hypot(dx, dy) returns sqrt(dx * dx + dy * dy)
  return hypot(p1.x - p2.x, p1.y - p2.y); }

  /* Rotate point counter clockwise around the origin */
  point rotate(point p, double theta) {
  double rad = DEG_to_RAD(theta);    // multiply theta with PI / 180.0
  return point(p.x * cos(rad) - p.y * sin(rad),
               p.x * sin(rad) + p.y * cos(rad));
  }
  /* Rotating relative to another point */
  point rotateRel(point p1,point p2,double theta){
    double rad = DEG_to_RAD(theta);
    return point((p1.x-p2.x) * cos(rad) - (p1.y-p2.y) * sin(rad) + p2.x,
                 (p1.x-p2.x) * sin(rad) + (p1.y-p2.y) * cos(rad) + p2.y);
}


  /*
  f you rotate point (px, py) around point (ox, oy) by angle theta you'll get:

  p'x = cos(theta) * (px-ox) - sin(theta) * (py-oy) + ox

  p'y = sin(theta) * (px-ox) + cos(theta) * (py-oy) + oy

  */

  /* line struct */
  struct line { double a, b, c; };



  /* get the line from two points */
  void pointsToLine(point p1, point p2, line &l) {
  if (fabs(p1.x - p2.x) < EPS) {              // vertical line is fine
    l.a = 1.0;   l.b = 0.0;   l.c = -p1.x;           // default values
  } else {
    l.a = -(double)(p1.y - p2.y) / (p1.x - p2.x);
    l.b = 1.0;
    l.c = -(double)(l.a * p1.x) - p1.y;
} }

  /* get line from a point and a slope */
  void pointSlopeToLine(point p, double m, line &l) {
  l.a = -m;
  l.b = 1;
  l.c = -((l.a * p.x) + (l.b * p.y)); }


  /* Helper functions on lines */
  bool areParallel(line l1, line l2) {
  return (fabs(l1.a-l2.a) < EPS) && (fabs(l1.b-l2.b) < EPS); }

  bool areSame(line l1, line l2) {
  return areParallel(l1 ,l2) && (fabs(l1.c - l2.c) < EPS); }

// returns true (+ intersection point) if two lines are intersect
  bool areIntersect(line l1, line l2, point &p) {
  if (areParallel(l1, l2)) return false;            // no intersection
  // solve system of 2 linear algebraic equations with 2 unknowns
  p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
  // special case: test for vertical line to avoid division by zero
  if (fabs(l1.b) > EPS) p.y = -(l1.a * p.x + l1.c);
  else                  p.y = -(l2.a * p.x + l2.c);
  return true; }


  /* Vectors */
  struct vec { double x, y;
  vec(double _x, double _y) : x(_x), y(_y) {} };

  /* convert two points to vector */
  vec toVec(point a, point b) {       // convert 2 points to vector a->b
  return vec(b.x - a.x, b.y - a.y); }

  /* Helper functions on vectors */
  vec scale(vec v, double s) {
  return vec(v.x * s, v.y * s); }

  point translate(point p, vec v) {
  return point(p.x + v.x , p.y + v.y); }

  /* products on vectors */
  double dot(vec a, vec b) { return (a.x * b.x + a.y * b.y); }
  double norm_sq(vec v) { return v.x * v.x + v.y * v.y; }
  double cross(vec a, vec b) { return a.x * b.y - a.y * b.x; }

  /* find the closest distance between a point and a line descibed with two points */
  double distToLine(point p, point a, point b, point &c) {
  // formula: c = a + u * ab
  vec ap = toVec(a, p), ab = toVec(a, b);
  double u = dot(ap, ab) / norm_sq(ab);
  c = translate(a, scale(ab, u));                  // translate a to c
  return dist(p, c); }

  /* find the closest distance between a point and a line segment */
  double distToLineSegment(point p, point a, point b, point &c) {
  vec ap = toVec(a, p), ab = toVec(a, b);
  double u = dot(ap, ab) / norm_sq(ab);
  if (u < 0.0) { c = point(a.x, a.y);                   // closer to a
    return dist(p, a); }         // Euclidean distance between p and a
  if (u > 1.0) { c = point(b.x, b.y);                   // closer to b
    return dist(p, b); }         // Euclidean distance between p and b
  return distToLine(p, a, b, c); }

  /* closest point to a line */
  void closestPoint(line l, point p, point &ans) {
  line perpendicular;         // perpendicular to l and pass through p
  if (fabs(l.b) < EPS) {
    ans.x = -(l.c);   ans.y = p.y;      return; }

  if (fabs(l.a) < EPS) {
    ans.x = p.x;      ans.y = -(l.c);   return; }

  pointSlopeToLine(p, 1 / l.a, perpendicular);
  // intersect line l with this perpendicular line
  // the intersection point is the closest point
  areIntersect(l, perpendicular, ans); }

  /*  Reflection of a point over a line */
  void reflectionPoint(line l, point p, point &ans) {
  point b;
  closestPoint(l, p, b);
  vec v = toVec(p, b);
  ans = translate(translate(p, v), v); }


  /* the angle <aob */
  double angle(point a, point o, point b) {  // returns angle aob in rad
  vec oa = toVec(o, a), ob = toVec(o, b);
  return acos(dot(oa, ob) / sqrt(norm_sq(oa) * norm_sq(ob))); }

  /* CCW test and cross product use of directions */
  // returns true if point r is on the left side of line pq
  bool ccw(point p, point q, point r) {
    return cross(toVec(p, q), toVec(p, r)) > 0; }

  /* Colinear test */
  // returns true if point r is on the same line as the line pq
  bool collinear(point p, point q, point r) {
  return fabs(cross(toVec(p, q), toVec(p, r))) < EPS; }


  /* The Intersection between two segments */

  // Given three colinear points p, q, r, the function checks if
  // point q lies on line segment 'pr'
  bool onSegment(point p, point q, point r)
  {
      if (q.x <EPS+ max(p.x, r.x) && q.x +EPS== min(p.x, r.x) &&
          q.y <EPS+ max(p.y, r.y) && q.y +EPS== min(p.y, r.y))
         return true;

      return false;
  }
// To find orientation of ordered triplet (p, q, r).
// The function returns following values
// 0 --> p, q and r are colinear
// 1 --> Clockwise
// 2 --> Counterclockwise
int orientation(point p, point q, point r)
{
    double val = (q.y - p.y) * (r.x - q.x) -
              (q.x - p.x) * (r.y - q.y);

    if (abs(val) < EPS) return 0;  // colinear

    return (val > EPS)? 1: 2; // clock or counterclock wise
}
bool doIntersect(point p1, point q1, point p2, point q2)
{
    // Find the four orientations needed for general and
    // special cases
    int o1 = orientation(p1, q1, p2);
    int o2 = orientation(p1, q1, q2);
    int o3 = orientation(p2, q2, p1);
    int o4 = orientation(p2, q2, q1);

    // General case
    if (o1 != o2 && o3 != o4)
        return true;
    // Special Cases
    // p1, q1 and p2 are colinear and p2 lies on segment p1q1
    if (o1 == 0 && onSegment(p1, p2, q1)) return true;
    // p1, q1 and p2 are colinear and q2 lies on segment p1q1
    if (o2 == 0 && onSegment(p1, q2, q1)) return true;
    // p2, q2 and p1 are colinear and p1 lies on segment p2q2
    if (o3 == 0 && onSegment(p2, p1, q2)) return true;
     // p2, q2 and q1 are colinear and q1 lies on segment p2q2
    if (o4 == 0 && onSegment(p2, q1, q2)) return true;
    return false; // Doesn't fall in any of the above cases
}

 /* Circles */
 /*
 dia = 2 * r
 circum/perim = 2 * PI * r
 area = PI * r * r
 arcLencgth = (theta/360.0)*circum
 chordLength = 2 * r * sin (theta/2)
 chordLength = sqrt(2 * r * r * (1-cos(theta))) //Radian
 sectorArea = (theta/360.0)*area
 segmentArea = sectorArea - Area of the trinage with sides of r r chordLength
 */
 boolean intersect(Circle cir)
	{
		return c.dist(cir.c) <= r + cir.r + EPS && c.dist(cir.c) + EPS >= Math.abs(r - cir.r);
	}
	//returns true if the circle intersects with the line segment defined by p and q at one or two points
	boolean intersect(Point p, Point q)
	{
		line l(p, q);
		if(abs(l.b) < EPS)
		{
			if(l.c * l.c > r * r + EPS)
				return false;

			double y1 = sqrt(abs(r * r - l.c * l.c)), y2 = -y1;
			return new Point(-l.c, y1).between(p, q) && new Point(-l.c, y2).between(p, q);
		}
		double a = l.a * l.a + 1, b = 2 * l.a * l.c, c = l.c * l.c - r * r;
		if(b * b - 4 * a * c + EPS < 0)
			return false;

		double dis = b * b - 4 * a * c;

		double x1 = (-b + Math.sqrt(dis)) / (2.0 * a), x2 = (-b - Math.sqrt(dis)) / (2.0 * a);

		return new Point(x1, - l.a * x1 - l.c).between(p, q) || new Point(x2, - l.a * x2 - l.c).between(p, q);
	}


 int insideCircle(point_i p, point_i c, int r) { // all integer version
  int dx = p.x - c.x, dy = p.y - c.y;
  int Euc = dx * dx + dy * dy, rSq = r * r;             // all integer
  return Euc < rSq ? 0 : Euc == rSq ? 1 : 2; } //inside/border/outside
    int insideCircle(point p, point c, double r) { // all double version
        double dx = p.x - c.x, dy = p.y - c.y;
        double Euc = dx * dx + dy * dy, rSq = r * r;             // all double
        return Euc + EPS < rSq ? 0 : abs(Euc-rSq) == EPS ? 1 : 2; } //inside/border/outside


  /* get the centers of the two circles with this intersection points */
  bool circle2PtsRad(point p1, point p2, double r, point &c) {
  double d2 = (p1.x - p2.x) * (p1.x - p2.x) +
              (p1.y - p2.y) * (p1.y - p2.y);
  double det = r * r / d2 - 0.25;
  if (det < 0.0) return false;
  double h = sqrt(det);
  c.x = (p1.x + p2.x) * 0.5 + (p1.y - p2.y) * h;
  c.y = (p1.y + p2.y) * 0.5 + (p2.x - p1.x) * h;
  return true; }         // to get the other center, reverse p1 and p2


  /* Triangles */

  /* usual operations */
  bool canFormTriangle(double a, double b, double c) {
  return (a + b > c) && (a + c > b) && (b + c > a); }

double perimeter(double ab, double bc, double ca) {
  return ab + bc + ca; }

double perimeter(point a, point b, point c) {
  return dist(a, b) + dist(b, c) + dist(c, a); }

double area(double ab, double bc, double ca) {
  // Heron's formula, split sqrt(a * b) into sqrt(a) * sqrt(b); in implementation
  double s = 0.5 * perimeter(ab, bc, ca);
  return sqrt(s) * sqrt(s - ab) * sqrt(s - bc) * sqrt(s - ca); }

double area(point a, point b, point c) {
  return area(dist(a, b), dist(b, c), dist(c, a)); }
/* check if point inside the trinagle */
bool inside_triangle(point x,point y,point z,point p){
    if (ccw(x, y, p)&&ccw(y, z, p)&&ccw(z, x, p)) {
        return true;
    }else{
        return false;
    }
}


bool inside_rectangle(point upLeft,point downRight,point p){
    return p.x>=upLeft.x&&p.x<=downRight.x&&p.y<=upLeft.y&&p.y>=downRight.y;
}

/* inside circle radius*/
double rInCircle(double ab, double bc, double ca) {
  return area(ab, bc, ca) / (0.5 * perimeter(ab, bc, ca)); }

double rInCircle(point a, point b, point c) {
  return rInCircle(dist(a, b), dist(b, c), dist(c, a)); }
/* inside circle center */
int inCircle(point p1, point p2, point p3, point &ctr, double &r) {
  r = rInCircle(p1, p2, p3);
  if (fabs(r) < EPS) return 0;                   // no inCircle center

  line l1, l2;                    // compute these two angle bisectors
  double ratio = dist(p1, p2) / dist(p1, p3);
  point p = translate(p2, scale(toVec(p2, p3), ratio / (1 + ratio)));
  pointsToLine(p1, p, l1);

  ratio = dist(p2, p1) / dist(p2, p3);
  p = translate(p1, scale(toVec(p1, p3), ratio / (1 + ratio)));
  pointsToLine(p2, p, l2);

  areIntersect(l1, l2, ctr);           // get their intersection point
  return 1; }

  /* circum or outside circle radius */
double rCircumCircle(double ab, double bc, double ca) {
  return ab * bc * ca / (4.0 * area(ab, bc, ca)); }

double rCircumCircle(point a, point b, point c) {
  return rCircumCircle(dist(a, b), dist(b, c), dist(c, a)); }

  /* cicumcircle center */
  int circumCircle(point p1, point p2, point p3, point &ctr, double &r){
  double a = p2.x - p1.x, b = p2.y - p1.y;
  double c = p3.x - p1.x, d = p3.y - p1.y;
  double e = a * (p1.x + p2.x) + b * (p1.y + p2.y);
  double f = c * (p1.x + p3.x) + d * (p1.y + p3.y);
  double g = 2.0 * (a * (p3.y - p2.y) - b * (p3.x - p2.x));
  if (fabs(g) < EPS) return 0;

  ctr.x = (d*e - b*f) / g;
  ctr.y = (a*f - c*e) / g;
  r = dist(p1, ctr);  // r = distance from center to 1 of the 3 points
  return 1; }

  /* rturns true if point d is inside the circumCircle defined by a,b,c */
int inCircumCircle(point a, point b, point c, point d) {
  return (a.x - d.x) * (b.y - d.y) * ((c.x - d.x) * (c.x - d.x) + (c.y - d.y) * (c.y - d.y)) +
         (a.y - d.y) * ((b.x - d.x) * (b.x - d.x) + (b.y - d.y) * (b.y - d.y)) * (c.x - d.x) +
         ((a.x - d.x) * (a.x - d.x) + (a.y - d.y) * (a.y - d.y)) * (b.x - d.x) * (c.y - d.y) -
         ((a.x - d.x) * (a.x - d.x) + (a.y - d.y) * (a.y - d.y)) * (b.y - d.y) * (c.x - d.x) -
         (a.y - d.y) * (b.x - d.x) * ((c.x - d.x) * (c.x - d.x) + (c.y - d.y) * (c.y - d.y)) -
         (a.x - d.x) * ((b.x - d.x) * (b.x - d.x) + (b.y - d.y) * (b.y - d.y)) * (c.y - d.y) > 0 ? 1 : 0;
}
//medians of the triangle
 double areaMedians(double ma, double mb, double mc)
	{
		double s = (ma + mb + mc) / 2.0;			//max(ma, mb, mc) < s
		return sqrt(s * (s - ma) * (s - mb) * (s - mc)) * 4.0 / 3.0;
	}
//heights of the triangle
	 double areaHeights(double ha, double hb, double hc)
	{
		double ha_1 = 1.0 / ha, hb_1 = 1.0 / hb, hc_1 = 1.0 / hc;
		double s = (ha_1 + hb_1 + hc_1) / 2.0;
		return 1.0 / (sqrt(s * (s - ha_1) * (s - hb_1) * (s - hc_1)) * 4.0);
	}
  /*




  /* 2D objects quadrilaterals */

  // Area of trapezium = 0.5 * (W1+W2)*H **W1,W2 THE TWO PARALLEL SIDES AND H IS THE HEIGHT BETWEEN THEM
  // Area of a Kite = diagonal1 * diagonal2 / 2



/* largest square sidelength inside a pentagon */
//C = sq rt ( 2A^2 + [(2A^2)(.9511)


  /* Polygons */

  //Polygon representation
  //6 points enetered ccw
  /*
  vector<point> P;
  P.push_back(point(1,4));
  .
  .
  .
  .
  P.push_back(point(1,4)) agaaain
  */



  /* Perimeter of a polygon */
  double perimeter(const vector<point> &P) {
  double result = 0.0;
  for (int i = 0; i < (int)P.size()-1; i++)  // remember that P[0] = P[n-1]
    result += dist(P[i], P[i+1]);
  return result; }

  /* the area of a polygon , which is half the determinant */
double area(const vector<point> &P) {
  double result = 0.0, x1, y1, x2, y2;
  for (int i = 0; i < (int)P.size()-1; i++) {
    x1 = P[i].x; x2 = P[i+1].x;
    y1 = P[i].y; y2 = P[i+1].y;
    result += (x1 * y2 - x2 * y1);
  }
  return fabs(result) / 2.0; }

  /* is convext ? */
  bool isConvex(const vector<point> &P) {
  int sz = (int)P.size();
  if (sz <= 3) return false;   // a point/sz=2 or a line/sz=3 is not convex
  bool isLeft = ccw(P[0], P[1], P[2]);               // remember one result
  for (int i = 1; i < sz-1; i++)            // then compare with the others
    if (ccw(P[i], P[i+1], P[(i+2) == sz ? 1 : i+2]) != isLeft)
      return false;            // different sign -> this polygon is concave
  return true; }                                  // this polygon is convex

  /* check if a point belong to a polygon */
  bool inPolygon(point pt, const vector<point> &P) {
  if ((int)P.size() == 0) return false;
  double sum = 0;    // assume the first vertex is equal to the last vertex
  for (int i = 0; i < (int)P.size()-1; i++) {
    if (ccw(pt, P[i], P[i+1]))
         sum += angle(P[i], pt, P[i+1]);                   // left turn/ccw
    else sum -= angle(P[i], pt, P[i+1]); }                 // right turn/cw
  return fabs(fabs(sum) - 2*PI) < EPS; }

  /* Cutting a polygon with a line */
  // line segment p-q intersect with line A-B.
point lineIntersectSeg(point p, point q, point A, point B) {
  double a = B.y - A.y;
  double b = A.x - B.x;
  double c = B.x * A.y - A.x * B.y;
  double u = fabs(a * p.x + b * p.y + c);
  double v = fabs(a * q.x + b * q.y + c);
  return point((p.x * v + q.x * u) / (u+v), (p.y * v + q.y * u) / (u+v)); }

// cuts polygon Q along the line formed by point a -> point b
// (note: the last point must be the same as the first point)
vector<point> cutPolygon(point a, point b, const vector<point> &Q) {
  vector<point> P;
  for (int i = 0; i < (int)Q.size(); i++) {
    double left1 = cross(toVec(a, b), toVec(a, Q[i])), left2 = 0;
    if (i != (int)Q.size()-1) left2 = cross(toVec(a, b), toVec(a, Q[i+1]));
    if (left1 > -EPS) P.push_back(Q[i]);       // Q[i] is on the left of ab
    if (left1 * left2 < -EPS)        // edge (Q[i], Q[i+1]) crosses line ab
      P.push_back(lineIntersectSeg(Q[i], Q[i+1], a, b));
  }
  if (!P.empty() && !(P.back() == P.front()))
    P.push_back(P.front());        // make P's first point = P's last point
  return P; }

  bool isInside(Point polygon[], int n, Point p)
{
    // There must be at least 3 vertices in polygon[]
    if (n < 3)  return false;

    // Create a point for line segment from p to infinite
    Point extreme = {INF, p.y};

    // Count intersections of the above line with sides of polygon
    int count = 0, i = 0;
    do
    {
        int next = (i+1)%n;

        // Check if the line segment from 'p' to 'extreme' intersects
        // with the line segment from 'polygon[i]' to 'polygon[next]'
        if (doIntersect(polygon[i], polygon[next], p, extreme))
        {
            // If the point 'p' is colinear with line segment 'i-next',
            // then check if it lies on segment. If it lies, return true,
            // otherwise false
            if (orientation(polygon[i], p, polygon[next]) == 0)
               return onSegment(polygon[i], p, polygon[next]);

            count++;
        }
        i = next;
    } while (i != 0);

    // Return true if count is odd, false otherwise
    return count&1;  // Same as (count%2 == 1)
}


  /* Convex Hull of points */
  point pivot;
  bool angleCmp(point a, point b) {                 // angle-sorting function
    if (collinear(pivot, a, b))                               // special case
      return dist(pivot, a) < dist(pivot, b);    // check which one is closer
    double d1x = a.x - pivot.x, d1y = a.y - pivot.y;
    double d2x = b.x - pivot.x, d2y = b.y - pivot.y;
    return (atan2(d1y, d1x) - atan2(d2y, d2x)) < 0; }   // compare two angles

  vector<point> CH(vector<point> P) {   // the content of P may be reshuffled
  int i, j, n = (int)P.size();
  if (n <= 3) {
    if (!(P[0] == P[n-1])) P.push_back(P[0]); // safeguard from corner case
    return P;                           // special case, the CH is P itself
  }
  // first, find P0 = point with lowest Y and if tie: rightmost X
  int P0 = 0;
  for (i = 1; i < n; i++)
    if (P[i].y < P[P0].y || (P[i].y == P[P0].y && P[i].x > P[P0].x))
      P0 = i;

  point temp = P[0]; P[0] = P[P0]; P[P0] = temp;    // swap P[P0] with P[0]

  // second, sort points by angle w.r.t. pivot P0
  pivot = P[0];                    // use this global variable as reference
  sort(++P.begin(), P.end(), angleCmp);              // we do not sort P[0]

  // third, the ccw tests
  vector<point> S;
  S.push_back(P[n-1]); S.push_back(P[0]); S.push_back(P[1]);   // initial S
  i = 2;                                         // then, we check the rest
  while (i < n) {           // note: N must be >= 3 for this method to work
    j = (int)S.size()-1;
    if (ccw(S[j-1], S[j], P[i])) S.push_back(P[i++]);  // left turn, accept
    else S.pop_back(); }   // or pop the top of S until we have a left turn
  return S; }                                          // return the result

/* Center of mass */ //Java codeee
  Point centroid()
  	{
  		double cx = 0.0, cy = 0.0;
  		for(int i = 0; i < g.length - 1; i++)
  		{
  			double x1 = g[i].x, y1 = g[i].y;
  			double x2 = g[i+1].x, y2 = g[i+1].y;

  			double f = x1 * y2 - x2 * y1;
  			cx += (x1 + x2) * f;
  			cy += (y1 + y2) * f;
  		}
  		double area = area();		//remove abs
  		cx /= 6.0 * area;
  		cy /= 6.0 * area;
  		return new Point(cx, cy);
  	}

/* 3d objects */
//Volume of Tetrahedron WXYZ, sides order: WX, WY, WZ, XY, XZ, YZ
static double vTetra(double[] sides)
	{
		double[] coff = new double[3];
		for(int i = 0; i < 3; i++)
			coff[i] = sides[(i+1)%3] * sides[(i+1)%3] + sides[(i+2)%3] * sides[(i+2)%3] - sides[5 - i] * sides[5 - i];

		double sqrt = 4 * sides[0] * sides[0] * sides[1] * sides[1] * sides[2] * sides[2];
		for(int i = 0; i < 3; i++)
			sqrt -= coff[i] * coff[i] * sides[i] * sides[i];
		sqrt += coff[0] * coff[1] * coff[2];

		return 1 / 12.0 * Math.sqrt(sqrt);
	}
