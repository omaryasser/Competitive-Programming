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
#define PI 3.14159265358979323846
#define pf printf
#define SSTR( x ) dynamic_cast< std::ostringstream & >( \
       ( std::ostringstream() << std::dec << x ) ).str()


using namespace std;

typedef long long ll;
typedef std::pair<int,int> ii;
typedef std::pair<std::string,int> si;
typedef vector<int> vi;
typedef vector<ii> vii;

unsigned power( unsigned val, unsigned _pow=0 ) {
	if ( _pow <= 0 )
		return 1;
	return val * power( val, _pow-1 );
}

int n , t;
int main()
{
    scanf("%d" , &t);
    while(t--){
    scanf("%d" , &n);
    int A[2*n][2*n];
		for(int i =0 ; i < n ; i ++)
			for(int j =0 ; j < n ; j++)
			{
                    scanf("%d" , &A[i][j]);
                    A[i+n][j]=A[i][j];
                    A[i][j+n]=A[i][j];
                    A[i+n][j+n]=A[i][j];
			}

			for(int i =0 ; i < 2*n ; i ++)
			for(int j =0 ; j < 2*n ; j++)
			{
				if(i>0)A[i][j]+=A[i-1][j];
				if(j>0)A[i][j]+=A[i][j-1];
				if(i>0 && j >0)A[i][j]-=A[i-1][j-1];
			}

		int  maxSubRect = -127 * 100 * 100 *10;
		for(int i =0 ; i < 2*n ;i ++)
			for(int j =0 ; j < 2*n ;j ++)
				for(int k =i ; k < min(2*n,i+n) ; k++)
					for(int l =j ;l <min(2*n,j+n) ; l++)
					{

                            int sub = A[k][l];
                            if(i>0)sub-=A[i-1][l];
                            if(j>0)sub-=A[k][j-1];
                            if(i>0 && j>0)sub+=A[i-1][j-1];
                            maxSubRect= max(sub, maxSubRect);

					}
        printf("%d\n" , maxSubRect);
    }
	return 0;
}

