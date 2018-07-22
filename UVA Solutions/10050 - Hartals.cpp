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

int T;
int N;
int P;
int tmp;
bool lost [3651];
int main() {
	cin >> T;
	while(T -- ){
		cin >> N >> P;
		fill(lost , false);
		long long cnt = 0 ;
		while(P -- > 0){
			cin >> tmp;
			int original = tmp;
			while(tmp <= N){
				int num = tmp % 7 ;
				if(num != 6 && num != 0 && !lost[tmp]){
					lost[tmp] = true;
					++cnt;
				}
				tmp += original;
			}
		}
		cout << cnt << endl;
	}
	return 0;
}
