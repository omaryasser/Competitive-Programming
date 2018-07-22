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
string board [8];
string op;
char current;
int dx [8] = {0,0,1,-1,1,1,-1,-1};
int dy [8] = {1,-1,0,0,1,-1,1,-1};

pair <int , int > cnt_colors (){
	int b = 0 , w = 0;
	for (int i = 0 ; i < 8 ; ++i){
		for (int j  = 0 ; j < 8 ; ++j){
			if (board[i][j] == 'W') ++w;
			else if (board[i][j] == 'B')++b;
		}
	}

	return make_pair(b,w);
}

bool valid (int row , int col){
	return row >= 0 && col >= 0 && row < 8 && col < 8;
}

bool isValidDir (int row , int col , int dir){
	if (valid (row , col) && board[row][col] == '-' && valid (row + dx[dir] , col + dy[dir])
					&& ((board[row + dx[dir]][col +dy[dir]] == 'W' && current == 'B') || (board[row + dx[dir]][col +dy[dir]] == 'B' && current == 'W'))){
				for (int i = row + dx[dir] , j = col + dy[dir] ; valid (i , j) ; i += dx[dir] , j += dy[dir]){
					if (board[i][j] == '-') break;
					if (board[i][j] == current) return true;
				}
			}
	return false;
}
bool isLegal (int row , int col){

	for (int k = 0 ; k < 8 ; ++k){
		if (isValidDir(row , col , k)) return true;
	}

	return false;
}

void add (int row , int col){
	for (int dir = 0 ; dir < 8 ; ++dir){
		if (isValidDir(row , col , dir)){
			for (int i = row + dx[dir] , j = col + dy[dir] ; valid (i , j) ; i += dx[dir] , j += dy[dir]){
				if (board[i][j] == current) break;
				board[i][j] = current;
			}
		}
	}
	board[row][col] = current;
}
int main() {

	cin >> T;
	while(T --){
		for (int i = 0 ; i < 8 ; ++i) cin >> board[i];
		cin >> current;
		while(1){
			cin >> op;
			if(op[0] == 'Q'){
				for (int i  = 0 ; i < 8 ; ++i) cout << board[i] << "\n";
				break;
			}
			if(op[0] == 'M'){
				int row , col;
				row = op[1] - '1';
				col = op[2] - '1';

				if(!isLegal(row , col)){
					current = (current == 'W') ? 'B' : 'W';
				}



				add(row,col);

				pair<int,int> pair= cnt_colors();
				cout << "Black - " << ((pair.first < 10) ? " " : "" ) << pair.first << " White - " << ((pair.second < 10) ? " " : ""  )<< pair.second << "\n" ;
				current = (current == 'W') ? 'B' : 'W';
			}
			else if (op[0] == 'L'){
				bool firstFound = false;
				for (int i = 0 ; i < 8 ; ++i){
					for (int j = 0 ; j  < 8;  ++ j){
						if (isLegal(i , j)){
							if (!firstFound){
								firstFound = true;
								cout << "(" << i + 1 << "," << j + 1 << ")";
							}
							else cout << " (" << i + 1 << "," << j + 1 << ")";
						}
					}
				}

				if(!firstFound) cout << "No legal move.";
				cout << "\n";
			}
		}

		if (T) cout << "\n";
	}
	return 0;
}
