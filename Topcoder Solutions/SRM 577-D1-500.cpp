/*
Same as the editorial.
*/
#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define double long double
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

const int N=16+8;
class EllysChessboard{
public:
    bool grid[N][N];
    int memo[N][N][N][N];
    bool in(int x,int y,int minx,int maxx,int miny,int maxy){
        return x>=minx&&x<=maxx&&y>=miny&&y<=maxy;
    }
    int dp(int minx,int maxx,int miny,int maxy){
        int &ret=memo[minx][maxx][miny][maxy];
        if(ret!=-1)return ret;
        bool f=0;
        ret=64*64;
        REP(x,N)REP(y,N)
            if(grid[x][y]&&!in(x,y,minx,maxx,miny,maxy)){
                int h=max(abs(x-minx),max(abs(x-maxx),max(abs(y-miny),abs(y-maxy))));
                int minx2=min(minx,x),maxx2=max(maxx,x),
                miny2=min(miny,y),maxy2=max(maxy,y);
                REP(x2,N)REP(y2,N){
                    if(grid[x2][y2]&&
                        !(x2==x&&y2==y)&&!in(x2,y2,minx,maxx,miny,maxy)&&in(x2,y2,minx2,maxx2,miny2,maxy2)){

                        h+=max(abs(x2-minx2),max(abs(x2-maxx2),max(abs(y2-miny2),abs(y2-maxy2))));
                    }
                }
                ret=min(ret,h+dp(minx2,maxx2,miny2,maxy2));
                f=1;
            }
        if(!f)return ret=0;
        return ret;
    }
    int minCost(vector <string> board){
        REP(i,8)REP(j,8)grid[i+j+8][i-j+8]=board[i][j]=='#';
        memset(memo,-1,sizeof memo);
        int bst=64*64;
        bool f=1;
        REP(i,N)REP(j,N)
            if(grid[i][j]){
                bst=min(bst,dp(i,i,j,j));
                f=0;
            }
        if(f)return 0;
        return bst;
    }
};
