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
#define PI 3.14159265358979323846
#define fill(x,y) memset (x , y , sizeof(x))
using namespace std;

const int MAX = 101;
int T;
int N;
int K;
int tmp;
bool good [MAX];
int tmp1[MAX];
int tmp2[MAX];
char op;
int main() {

	cin >> T;
	while(T -- ){
		cin >> N >> K;
		fill (good , false);
		while(K -- ){
			cin >> tmp;
			for (int i = 0 ; i < tmp ; ++i) cin>>tmp1[i];
			for (int i = 0 ; i < tmp ; ++i) cin>>tmp2[i];
			cin >> op;
			if (op == '='){
				for (int i = 0 ; i < tmp ; ++i) good[tmp1[i]] = good[tmp2[i]] = true;
			}
		}

		int cnt = 0 , ans = 0;
		for (int i = 1 ; i <= N ; ++i)
			if (!good[i]) {
			cnt ++;
			ans = i;
		}

		if (cnt > 1 || cnt == 0) printf("%d\n" , 0);
		else printf("%d\n" , ans);

		if (T) printf("\n");
	}
	return 0;
}
