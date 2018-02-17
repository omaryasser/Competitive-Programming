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

using namespace std;


int main() {

	int n ;
	for (int t = 1 ;; t++){
		cin >> n;
		if (!n) break;

		int code [n];
		for (int i = 0 ; i < n ; ++i) cin >> code[i];

		printf("Game %d:\n" , t);
		while(true){
			int guess [n];
			bool allZeros = true;
			for (int i = 0 ; i < n ; ++i){
				cin >> guess[i];
				if (guess[i]) allZeros = false;
			}

			if (allZeros) break;
			int strong = 0 , weak = 0 ;
			for (int i = 0 ; i < n ; ++i){
				if(guess[i] == code[i]) ++ strong;
				else {
					for(int j = 0 ; j < n ; ++j){
						if (guess[j] == code[i] && code[j] != guess[j]){
							++weak;
							guess[j] = 0;
							break;
						}
					}
				}
			}

			printf("    (%d,%d)\n" , strong , weak);

		}
	}
	return 0;
}

