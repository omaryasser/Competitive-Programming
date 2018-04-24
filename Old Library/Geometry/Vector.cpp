struct vec {
  double x, y;
  vec(double _x, double _y) : x(_x), y(_y) {}
};

vec toVec(point a, point b) {return vec(b.x - a.x, b.y - a.y);}

vec scale(vec v, double s) {return vec(v.x * s, v.y * s);}

point translate(point p, vec v) {return point(p.x + v.x , p.y + v.y);}

void reflectionPoint(line l, point p, point &ans) {
  point b;
  closestPoint(l, p, b);                     // similar to distToLine
  vec v = toVec(p, b);                             // create a vector
  ans = translate(translate(p, v), v); }         // translate p twice

double dot(vec a, vec b) { return (a.x * b.x + a.y * b.y); }

double cross(vec a, vec b) { return a.x * b.y - a.y * b.x; }

double norm_sq(vec v) { return v.x * v.x + v.y * v.y; }

vec normalize(vec v)
{
  double d = sqrt(norm_sq(v));
  return scale(v , 1 / d);
}
