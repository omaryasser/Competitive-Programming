#include <bits/stdc++.h>
#define bug cout<<"HERE"<<endl;
#define FOR(i,l) for(int i=0;i<l;++i)
#define FOR1(i,x,l) for(int i=x;i<l;++i)
#define pb(x) push_back(x)
#define sz(x) (int)x.size()
using namespace std;

typedef long long ll;
inline int toInt(string s){int v; istringstream sin(s);sin>>v;return v;}
inline ll toll(string s){ll v; istringstream sin(s);sin>>v;return v;}
int mod=1000000007;

struct Point{
	int x,y,time;
	Point(int xx,int yy,int tt):x(xx),y(yy),time(tt){}
	Point(){}
	bool operator ==(Point other)const{
		return x==other.x&&y==other.y;
	}
	bool operator<(Point other)const{
		return x==other.x?y<other.y:x<other.x;
	}
};
const int MAX=3100;
int alive[MAX][MAX];
class NonXorLife{
	public :


	int countAliveCells(vector <string> field, int K){
		int dx[5]={0,0,1,-1};
		int dy[5]={1,-1,0,0};
		queue<Point>q;
		FOR(i,sz(field))FOR(j,(int)field[0].length())if(field[i][j]=='o')alive[i+1500][j+1500]=1,q.push(Point(i,j,0));
		while(!q.empty()){
			Point cur=q.front();q.pop();
			if(cur.time==K)continue;
			Point now;
			FOR(i,4){
				now.x=cur.x+dx[i],now.y=cur.y+dy[i];
				now.time=cur.time+1;
				if(!alive[now.x+1500][now.y+1500]){
					alive[now.x+1500][now.y+1500]=1;
					q.push(Point(now));
				}
			}
		}
		int cnt=0;
		FOR(i,MAX)FOR(j,MAX)if(alive[i][j])cnt++;
		return cnt;
	}
};

int main(){

	NonXorLife n;
//	vector<int>aa(a,a+sizeof a/sizeof a[0]);
//	vector<int>bb(b,b+sizeof b/sizeof b[0]);
//	std::vector<string> B(a, a + sizeof a / sizeof a[0]);
	string x[]={"o.oo.ooo"
			,"o.o.o.oo"
			,"ooo.oooo"
			,"o.o..o.o"
			,"o.o..o.o"
			,"o..oooo."
			,"..o.o.oo"
			,"oo.ooo.o"};
	vector<string>xx(x,x+sizeof x/sizeof x[0]);
	cout<<n.countAliveCells(xx,
			1234)<<"\n";
	return 0;
}
