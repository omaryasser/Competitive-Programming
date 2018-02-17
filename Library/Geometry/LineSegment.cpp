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

bool areParallel(line l1, line l2) {
	return abs(l1.a - l2.a) < EPS && abs(l1.b - l2.b) < EPS;
}
bool areSame (line l1 , line l2) {return areParallel(l1 , l2) && abs (l1.c - l2.c) < EPS ;}

bool between(point wanted, point p, point q) {
	return wanted.x <= max(p.x, q.x) + EPS && wanted.x + EPS >= min(p.x, q.x)
			&& wanted.y <= max(p.y, q.y) + EPS && wanted.y + EPS >= min(p.y, q.y);
}

bool areIntersect(line l1, line l2, point &p) {
  if (areParallel(l1, l2) ) return false;            // no intersection
  // solve system of 2 linear algebraic equations with 2 unknowns
  p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
  // special case: test for vertical line to avoid division by zero
  if (abs(l1.b) > EPS) p.y = -(l1.a * p.x + l1.c);
  else                  p.y = -(l2.a * p.x + l2.c);
  return true;
}

struct lineSegment {
	point a , b ;
	lineSegment (point _a , point _b) : a(_a) , b(_b) {}
	lineSegment () {
	}
};

bool intersect(lineSegment f , lineSegment s)
{
	line l1 , l2 ;
	pointsToLine(f.a , f.b , l1);
	pointsToLine(s.a , s.b , l2);

	if(areParallel(l1 , l2))
	{
		if(areSame(l1 , l2))
			return between(f.a , s.a, s.b) || between(f.b , s.a, s.b) ||
					between(s.a , f.a, f.b) || between(s.b ,f.a,f.b);
		return false;
	}
	point c ;
	areIntersect(l1 , l2 , c);
	return between(c , f.a, f.b) && between(c , s.a, s.b);
}
