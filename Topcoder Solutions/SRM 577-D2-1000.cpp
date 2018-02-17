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

class EllysCoprimesDiv2{
	public :
	int gcd(int a,int b){return !b?a:gcd(b,a%b);}
	int getCount(vector <int> numbers){
		sort(numbers.begin(),numbers.end());
		int cnt=0;
		FOR1(i,1,sz(numbers))
			{
				if(gcd(numbers[i],numbers[i-1])!=1){
					bool ok=0;
					FOR1(j,numbers[i-1]+1,numbers[i])
					if(gcd(j,numbers[i-1])==1&&gcd(j,numbers[i])==1){
						ok=1;
					}
					cnt+=(ok?1:2);
				}
			}
		return cnt;
	}
};

