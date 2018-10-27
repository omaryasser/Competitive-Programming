#include <cstdlib>
#include <iostream>
#include <map>
#include <stdio.h>
#include <vector>
#include<bits/stdc++.h>
#include <ctype.h>
#include <list>
#include <stack>
#include <queue>
#include <string.h>
#include <iomanip>
#include <string>
#include <algorithm>
#include <cmath>
#include <string>
#define _CRT_SECURE_NO_DEPRECATE
#define INF 1000000000
#define Set(a, s) memset(a, s, sizeof (a))
#define ALL(x) x.begin() , x.end()
#define UNIQUE(c) (c).resize(unique(ALL(c))-(c).begin())
#define FOR(i,n) for (int i=0 ; i<n ; i++)
#define upcase(x) transform(x.begin(), x.end(), x.begin(), ::toupper)
#define locase(x) transform(x.begin(), x.end(), x.begin(), ::tolower)
#define findIndex(v,x) find(v.begin(), v.end(), x) - v.begin()
#define PI 3.14159265358979323846
#define pf printf
#define CLR(a) memset(a,0,sizeof(a))
#define FILL(a,v) memset(a,v,sizeof(a))
#define SSTR( x ) dynamic_cast< std::ostringstream & >( \
       ( std::ostringstream() << std::dec << x ) ).str()
#define mp(x,y) make_pair(x,y)
#define s second
#define f first
#define sc(x) scanf("%d",&x)

using namespace std;

typedef long long ll;
typedef std::pair<int, int> ii;
typedef std::pair<std::string, int> si;
typedef vector<int> vi;
typedef vector<ii> vii;

int n ;
string names [11];
int have [11];
int main() {

	bool first = true;
	int t = 0 ;

	while(cin >> n){
		map<string,int> map;
		memset(have , 0 , sizeof(have));
		for(int i = 0 ; i < n ; ++i){
			cin >> names[i];
			map[names[i]] = i;
		}
		for(int i = 0 ; i < n ; ++i){
			string giver; cin >> giver;
			int money; 	cin >> money;
			int tmp = money;
			int numPeople; cin >> numPeople;
			money = numPeople == 0 ? money : money / numPeople;
			have[map[giver]] -= money * numPeople ;
			for (int i = 0 ; i < numPeople ; ++i) {
				string name ;
				cin >> name ;
				have[map[name]] += money;
			}
		}

		if(first) first = false;
		else cout << "\n";

		for(int i = 0 ; i < n ; ++i) cout << names[i] << " " << have[map[names[i]]] << "\n";
	}
	return 0;
}

