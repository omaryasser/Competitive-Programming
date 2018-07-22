#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

struct point{
    int x,y;
    point(int xx,int yy):x(xx),y(yy){}
    point(){x=0,y=0;}
    bool operator <(point other)const{return other.x!=x?x<other.x:y<other.y;}
};
int dist(point p1, point p2) {return max(abs(p1.x-p2.x),abs(p1.y-p2.y));}
struct cmp{
    bool operator()(point p1,point p2){
        return p1.y!=p2.y?p1.y<p2.y:p1.x<p2.x;
    }
};
int N;
const int MAX=100001;
point points[MAX];
set<point,cmp>activeSet;

int closest(){
    activeSet.clear();
    sort(points,points+N);
    activeSet.insert(points[0]);
    int left=0;
    int best=2e6;
    REP1(i,1,N){
        point cur=points[i];
        while(left<i&&cur.x-points[left].x>=best)activeSet.erase(points[left++]);
        point lo(cur.x,cur.y-best),hi(cur.x,cur.y+best);
        set<point,cmp>::iterator it=activeSet.lower_bound(lo),it2=activeSet.upper_bound(hi);
        for(;it!=it2;it++){
            point inSet(it->x,it->y);
            double d=dist(inSet,cur);
            if(d<best)best=d;
        }
        activeSet.insert(cur);
    }
    return best;
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    while(cin>>N){
        activeSet.clear();
        REP(i,N)cin>>points[i].x>>points[i].y;
        cout<<closest()<<"\n";
    }
}
