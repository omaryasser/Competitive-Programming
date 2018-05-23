/*
  dp(i,j,msk)= # of ways to continue of I am deciding for cell (i,j) and msk contain info about the last m cells 
  (Colored-Even,Colored-Odd,Not Colored).
*/

#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()

typedef long long ll;

using namespace std;

int memo[100][8][(int)pow(3,8)+2];

class DengklekPaintingSquares{
public:
    int NOT=0,ODD=1,EVEN=2;
    int n,m;
    int num[8][3];
    int MOD=1e9+7;
    int L;

    string toT (int nu){
        string r="";
        int c=0;
        while(c++<m||nu){
            r+=to_string(nu%3);
            nu/=3;
        }
        return r;
    }
    int dp(int r,int c,int msk){
        if(r==n){
            int t=msk;
            REP(i,m){
                if(t%3==1)return 0;
                t/=3;
            }
            return 1;
        }
        if(c==m) return dp(r+1,0,msk);
        int &ret=memo[r][c][msk];

        if(ret!=-1)return ret;
        int up=(msk>=num[L][2])?EVEN:(msk>=num[L][1])?ODD:NOT;
        int left=msk%3;
        ret=0;
        if(up==EVEN||up==NOT){
            // dont
            ret=dp(r,c+1,3*(msk-num[L][up]));
        }
        if(up==ODD||up==NOT){
            // put
            int arnd=(((left>0&&c)+(up>0))&1)?ODD:EVEN;
            if(left==NOT||!c){
                ret+=dp(r,c+1,3*(msk-num[L][up])+arnd);
            }else{
                ret+=dp(r,c+1,3*(msk-num[L][up]-left+(left==ODD?EVEN:ODD))+arnd);
            }
        }
        if(ret>=MOD)ret-=MOD;
        return ret;
    }
    int numSolutions(int N, int M){
        n=N,m=M;
        memset(memo,-1,sizeof memo);
        int pw=1;
        L=m-1;

        REP(i,8){
            REP(j,3){
                num[i][j]=pw*j;
            }
            pw*=3;
        }

        return dp(0,0,0);
    }
};
