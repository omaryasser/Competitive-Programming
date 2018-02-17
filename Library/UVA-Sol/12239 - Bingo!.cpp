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


int main() {

	int N , B;
	while(true){
		cin >> N >> B;
		if(!N) break;
		bool found [N + 1];
		int balls [B];
		fill (found , false);
		for(int i = 0 ; i < B ; ++i) cin >> balls[i];
		for(int i = 0 ; i < B - 1 ; ++i) for(int j = i ; j < B ; ++j)
			if(abs (balls[i] - balls[j]) >= 0 && abs (balls[i] - balls[j]) <= N)
				found[abs(balls[i] - balls[j])] = 1;
		bool yes = 1;
		for(int i = 0 ; i <= N ; ++i) if (!found[i]) yes = false;
		cout <<( yes ? "Y" : "N" )<< "\n";
	}
	return 0;
}
