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

const int MAX = 51;
int n;
int currentBoard;
int board[MAX][MAX];
int boards [MAX * 2][MAX][MAX];

void clear (){
	for (int i = 0 ; i < (n << 1) ; ++i) for (int j = 0 ; j < n ; ++j) for (int k = 0 ; k < n ; ++k) boards[i][j][k] = 0;
	for	(int i = 0 ; i < n 		  ; ++i) for (int j = 0 ; j < n ; ++j) board[i][j] = 0;
	currentBoard = 0 ;
}

bool found (){
	for (int i = 0 ; i < currentBoard ; ++i){
		bool same 	 = 1;
		bool same90L = 1;
		bool same90R = 1;
		bool same180 = 1;
		for (int j = 0 ; j < n ; ++j){
			for (int k = 0 ; k < n ; ++k){
				if (board[j][k] != boards[i][j][k]) same = 0;
				if (board[j][k] != boards[i][n - 1 - k][j]) same90L = 0;
				if (board[j][k] != boards[i][k][n - 1 - j]) same90R = 0;
				if (board[j][k] != boards[i][n - 1 - j][n - 1 - k]) same180 = 0;
			}
		}
		if (same || same90L || same90R || same180) return true;
	}

	return false;
}
int main() {

	while(1){
		cin >> n;
		if (!n) break;

		clear();
		int winner = 0 , move =  - 1;
		int current = 0;
		for (int i = 0 ; i < (n << 1) ; ++i){
			int x , y ;
			char op;
			cin >> x >> y >> op; --x; -- y;
			if (winner) continue;
			if (op == '+') board[x][y] = 1;
			else  board[x][y] = 0;



			if (i && found()) {
				winner = 1 - current + 1;
				move = i + 1;
			}

			current ^= 1;
			++ currentBoard;
			for (int j = 0 ; j < n ; ++j)
				for (int k = 0 ; k < n ; ++k)
					boards[i][j][k] = board[j][k];
		}

		if (winner == 0) printf("Draw\n");
		else printf("Player %d wins on move %d\n" , winner , move);
	}
	return 0;
}
