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

class MovingRooksDiv2{
	public :
	vector<int>a,b;
	set<int>st;
	int numB;
	int num(vector<int>x){int res=0;FOR(i,sz(x))res=res*10+x[i];return res;}
	bool backtrack(){
		if(num(a)==numB)return 1;
		else {
			bool ok=0;
			int sz=sz(a);
			FOR(i,sz)FOR1(j,i+1,sz)
				if(a[i]>a[j]){
				int tmp=a[i];
				a[i]=a[j];
				a[j]=tmp;
				int numA=num(a);
				if(st.find(numA)==st.end()){
					st.insert(numA);
					ok|=backtrack();
				}
				a[j]=a[i];
				a[i]=tmp;
			}
			return ok;
		}
	}
	string move(vector <int> y1, vector <int> y2){
		a=y1,b=y2;
		numB=num(b);
		if(backtrack())return "Possible";
		else return "Impossible";
	}
};

int main(){
	MovingRooksDiv2 n;
	int a[]={3,1,2,0};
	int b[]={0,2,1,3};
	vector<int>aa(a,a+sizeof a/sizeof a[0]);
	vector<int>bb(b,b+sizeof b/sizeof b[0]);
//	std::vector<string> B(a, a + sizeof a / sizeof a[0]);
	cout<<n.move(aa,bb)<<"\n";
	return 0;
}
