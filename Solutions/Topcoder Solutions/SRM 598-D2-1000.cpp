#include <bits/stdc++.h>
#define bug cout<<"HERE"<<endl;
#define FOR(i,l) for(int i=0;i<l;++i)
#define FOR1(i,x,l) for(int i=x;i<l;++i)
using namespace std;

typedef long long ll;
inline int toInt(string s){int v; istringstream sin(s);sin>>v;return v;}
inline ll toll(string s){ll v; istringstream sin(s);sin>>v;return v;}
int mod=1000000007;

class FoxAndFencingEasy{
	public :
	string WhoCanWin(int mov1, int mov2, int d){
		if(mov1>=d||mov1>=2*mov2+1)return "Ciel";
		else if(mov2>=2*mov1+1)return "Liss";
		else return"Draw";
	}
};

