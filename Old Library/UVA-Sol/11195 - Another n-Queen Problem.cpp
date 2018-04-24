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
#define _CRT_SECURE_NO_DEPRECATE
#define INF 1000000000
#define Set(a, s) memset(a, s, sizeof (a))
#define forpos(i,j,k) for (int i=0 ; i<j ; i+=k)
#define forneg(i,j,k) for (int i=j ;i>=0 ;i-=k)
#define upcase(x) transform(x.begin(), x.end(), x.begin(), ::toupper)
#define locase(x) transform(x.begin(), x.end(), x.begin(), ::tolower)
#define PI 3.14159265358979323846
#define VISITED 1
#define UNVISITED -1
#define pf printf
#define SSTR( x ) dynamic_cast< std::ostringstream & >( \
       ( std::ostringstream() << std::dec << x ) ).str()


using namespace std;

typedef long long ll;
typedef std::pair<int,int> ii;
typedef std::pair<std::string,int> si;
typedef vector<int> vi;
typedef vector<ii> vii;



int N , cnt;
bool rw [100] , leftDiag[100] , rightDiag[100];
string board[15];

void backtrack (int col)
{

       if(col == N){
            ++ cnt;
            return;
       }

       for(int i = 0 ; i < N ; ++i)
           if(board[i][col] == '.' && ! (rw[i + 50] || leftDiag[col - i + 50] || rightDiag[col + i + 50]) )
           {
              rw[i + 50] = leftDiag[col - i + 50] = rightDiag[col + i + 50] =  true;

              backtrack(col + 1);

              rw[i + 50] = leftDiag[col - i + 50] = rightDiag[col + i + 50] =  false;
            }
}

int main()
{
   for(int T = 1 ;; ++T)
        {
            cin >> N;
            if(N == 0) break;
            cnt = 0 ;

            memset(rw , false , sizeof(rw));
            memset(leftDiag , false , sizeof(leftDiag));
            memset(rightDiag , false , sizeof(rightDiag));

            for(int i = 0 ; i < N ; ++i) cin >> board[i];
            backtrack(0);
            printf("Case %d: %d\n" , T ,  cnt);
        }
}
