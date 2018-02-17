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

int t,a,b;
const int MAX=5000001;
ll res[MAX];
bool isPrime[MAX];
int primeFactor[MAX];
void go(){
	clr(isPrime,1);
	isPrime[0]=isPrime[1]=0;
	FOR1(i,2,MAX)if(isPrime[i]){
		primeFactor[i]=i;
		for(int j=2;j*i<=MAX;j++)
			isPrime[i*j]=0,primeFactor[i*j]=i;
	}
}
void fill(){
	res[1]=0;
	FOR1(i,2,MAX){
		res[i]=res[i/primeFactor[i]]+1;
	}
}
int main() {
  fast
	go();
	fill();
	return 0;
}
