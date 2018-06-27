double EPS=1e-9;
struct point{
	double x,y;
	point(double xx,double yy):x(xx),y(yy){}
	point(){x=0.0,y=0.0;}
	bool operator <(point other)const{return abs(other.x-x)>=EPS?x<other.x:y<other.y;}
};
double sq(double x) {return x * x;}
double dist(point p1, point p2) {return sqrt(sq(p1.x - p2.x) + sq(p1.y - p2.y));}
struct cmp{
	bool operator()(point p1,point p2){
		return abs(p1.y-p2.y)>=EPS?p1.y<p2.y:p1.x<p2.x;
	}
};
int N;
const int MAX=10001;
point points[MAX];
set<point,cmp>activeSet;
int main() {
    fast
	double xInp,yInp;
	while((cin>>N)&&N){
		activeSet.clear();
		FOR(i,N)cin>>xInp>>yInp,points[i]=point(xInp,yInp);
		sort(points,points+N);
		activeSet.insert(points[0]);
		int left=0;
		double best=1000023.0;
		FOR1(i,1,N){
			point cur=points[i];
			while(left<i&&cur.x-points[left].x>=best)activeSet.erase(points[left++]);
			point lo(cur.x,cur.y-best),hi(cur.x,cur.y+best);
			set<point,cmp>::iterator it=activeSet.lower_bound(lo),it2=activeSet.upper_bound(hi);
			for(;it!=it2;it++){
				point inSet(it->x,it->y);
				double d=dist(inSet,cur);
				if(d+EPS<best)best=d;
			}
			activeSet.insert(cur);
		}
		if(best+EPS<10000.0)cout<<fixed<<setprecision(4)<<best<<"\n";
		else cout<<"INFINITY\n";
	}
	return 0;
}
