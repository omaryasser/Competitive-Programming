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
#define ALL(x) x.begin() , x.end()
#define UNIQUE(c) (c).resize(unique(ALL(c))-(c).begin())
#define FOR(i,n) for (int i=0 ; i<n ; i++)
#define upcase(x) transform(x.begin(), x.end(), x.begin(), ::toupper)
#define locase(x) transform(x.begin(), x.end(), x.begin(), ::tolower)
#define findIndex(v,x) find(v.begin(), v.end(), x) - v.begin()
#define PI 3.14159265358979323846
#define pf printf
#define CLR(a) memset(a,0,sizeof(a))
#define FILL(a,v) memset(a,v,sizeof(a))
#define SSTR( x ) dynamic_cast< std::ostringstream & >( \
       ( std::ostringstream() << std::dec << x ) ).str()
#define mp(x,y) make_pair(x,y)
#define s second
#define f first
#define sc(x) scanf("%d",&x)


using namespace std;

typedef long long ll;
typedef std::pair<int,int> ii;
typedef std::pair<std::string,int> si;
typedef vector<int> vi;
typedef vector<ii> vii;



    int main (){

        int T ; cin >> T;
        for(int tt = 1 ; tt <= T ; ++tt){
            int N , M , K ; cin >> N >> M >> K;
            long A2 [N][M];
            for (int i = 0 ; i < N ; ++i) for (int j = 0 ; j  < M ; ++j) cin >> A2[i][j];

            for(int i =0 ; i < N ; i ++)
                for(int j =0 ; j < M ; j++)
                {
                    if(i>0)A2[i][j]+=A2[i-1][j];
                    if(j>0)A2[i][j]+=A2[i][j-1];
                    if(i>0 && j >0)A2[i][j]-=A2[i-1][j-1];

                }

            long maxSubRect = 0 , max_area_side = 0;
            for(int i =0 ; i < N ;i ++)
                for(int j =0 ; j < M ;j ++)
                    for(int k =i ; k < N ; k++)
                        for(int l =j ;l <M ; l++)
                        {
                            int area = (k - i + 1) * (l - j + 1);

                            long sub = A2[k][l];
                            if(i>0)sub-=A2[i-1][l];
                            if(j>0)sub-=A2[k][j-1];
                            if(i>0 && j>0)sub+=A2[i-1][j-1];
                            if (sub > K) continue;
                            if (area > max_area_side || (area == max_area_side && sub < maxSubRect))
                            {
                                max_area_side = area;
                                maxSubRect = sub;
                            }
                        }

                        printf("Case #%d: %d %d\n" ,tt ,  max_area_side , maxSubRect);


        }

    }











