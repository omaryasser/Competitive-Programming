#include<bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= n; i++)
#define pb(x) push_back(x)
#define mp(x) make_pair(x)

typedef long long ll;
using namespace std;


bool reached[1501][1501];

bool can (int A, int B, int C) {
	if(B > C) swap(B, C);
	if(A > C) swap(A, C);
	if(B < A) swap(A, B);
	
	if(A == B && B == C) return true;
	
	if(reached[A][B]) {
		return false;
	}
	
	reached[A][B] = true;
	return can(A + A, B - A, C) || can(A + A, B, C - A) || can(A, B + B, C - B);
}
class BearPlaysDiv2 {
public :

	string equalPiles(int A, int B, int C)  {
		return can(A, B, C) ? "possible" : "impossible";
	}
};

int main () {
//	CuttingBitString p;
//	p.getmin("");
}

