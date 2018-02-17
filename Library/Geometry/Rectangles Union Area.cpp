#include <bits/stdc++.h>
#define pb(x) push_back(x)
#define bug cout<<"HERE"<<endl;
#define SSTR( x ) static_cast< std::ostringstream & >( \
        ( std::ostringstream() << std::dec << x ) ).str()
#define clr(x,y) memset(x,y,sizeof(x))
#define all(v) v.begin(),v.end()
#define FOR(i,l) for(int i=0;i<l;++i)
#define FOR1(i,s,l) for(int i=s;i<l;++i)
#define FOR2(i,s) for(int i=s;i>=0;--i)
#define fast 	ios_base::sync_with_stdio(0); cin.tie(0);
#define inp freopen("input.txt", "r", stdin);
#define out freopen("output.txt", "w", stdout);
using namespace std;

typedef long long ll;
typedef vector<int> vi;
inline int toInt(string s){int v; istringstream sin(s);sin>>v;return v;}
inline ll toll(string s){ll v; istringstream sin(s);sin>>v;return v;}

struct point{
	double x,y;
	point(double xx,double yy):x(xx),y(yy){}
	point(){x=0,y=0;}
};
struct event{
	point p;
	bool left;
	int recIdx;
	event(point pp,bool ll ,int rr):p(pp),left(ll),recIdx(rr){}
	event(){p=point(),left=0,recIdx=1;}
};
bool compareV (event e1,event e2){return e1.p.x<e2.p.x;}
bool compareH (event e1,event e2){return e1.p.y<e2.p.y;}
vector<event>eventV;
vector<event>eventH;
ll unionRectangleArea(){
	sort(all(eventV),compareV);
	sort(all(eventH),compareH);
	bool inSet[(int)eventH.size()];
	FOR(i,(int)eventH.size())inSet[i]=0;
	ll area =0;
	inSet[eventV[0].recIdx]=1;
	FOR1(i,1,(int)eventV.size()){
		event cur=eventV[i];
		int cnt=0;
		ll delta_x=cur.p.x-eventV[i-1].p.x;
		if(delta_x==0){
			inSet[cur.recIdx]=cur.left;
			continue;
		}
		int beginY;
		FOR(j,(int)eventH.size()){
			if(inSet[eventH[j].recIdx]){
				if(eventH[j].left){ // here left means bottom
					if(cnt==0)beginY=eventH[j].p.y;
					cnt++;
				}
				else {
					cnt--;
					if(cnt==0){
						ll delta_y=eventH[j].p.y-beginY;
						area+=delta_x*delta_y;
					}
				}
			}
		}
		inSet[cur.recIdx]=cur.left;
	}
	return area;
}
int main() {
    fast
	int N;cin>>N;
    FOR(i,N){
    	int x1,y1,x2,y2;cin>>x1>>y1>>x2>>y2;
    	event lowerL=event(point(x1,y1),1,i),upperR=event(point(x2,y2),0,i);
    	eventV.pb(lowerL),eventV.pb(upperR);
    	eventH.pb(lowerL),eventH.pb(upperR);
    }
    cout<<unionRectangleArea()<<"\n";
	return 0;
}
