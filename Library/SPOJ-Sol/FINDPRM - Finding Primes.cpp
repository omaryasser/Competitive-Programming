#include<bits/stdc++.h>
#define FOR(x,y) for (int x = 0 ; x < y ; ++ x)
using namespace std;

const int MAX = 10000009;
bitset<MAX> isPrime ;
int PS [MAX];
void seive (){
	isPrime.set();
	isPrime[0] = isPrime[1] = 0;
	FOR (i , MAX){
		if (isPrime[i]){
			for (int j = i + i ; j < MAX ; j += i) isPrime[j] = 0;
		}
	}
}
int main() {
//	freopen("input.txt", "r", stdin);
	ios_base::sync_with_stdio(0); cin.tie(0);
	seive();
	PS[0] = 0;
	FOR (i , MAX - 1) PS[i + 1] = isPrime[i + 1] + PS[i];
	int T ; cin >> T;
	while (T -- ){
		int N ; cin >> N ;
		cout << PS[N] - PS[N >> 1] << "\n";
	}

}
