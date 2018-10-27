/*
    Perfect Triples are easy. 
    After removing them you will have at most 2 cards of each kind left which can be represented by 3^n mask to DP on it.
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

const int N=10000+10;
int mem[(int)(pow(3,13)+10)];

int get(int *a){
    int r=0,pw=1;
    REP(i,13){
        r+=a[i]*pw;
        pw*=3;
    }
    return r;
}
int dp(int *a){
    int msk=get(a);
    int &r=mem[msk];
    if(!msk)return 0;
    if(r!=-1)return r;
    int lst=-1;
    REP(i,13)if(a[i])lst=i;
    a[lst]--;
    r=dp(a);
    REP(i,lst+1)
        REP1(j,i,lst+1){
            if(a[i]>0&&a[j]>0&&a[i]+a[j]-(i==j?a[i]:0)>=2&&i+j>=lst){
                a[i]--,a[j]--;
                r=max(r,1+dp(a));
                a[i]++,a[j]++;
            }
        }
    a[lst]++;
    return r;
}

int main(){
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif

    memset(mem,-1,sizeof mem);
    while(1){
        int n;
        scanf("%d",&n);
        if(!n)break;
        int c[2][13];
        REP(i,2)REP(j,13)c[i][j]=0;
        REP(i,n){
            int x;
            scanf("%d",&x);
            x--;
            c[i&1][x]++;
            
        }
        pair<int,int>a,b;
        REP(i,2)
        REP(j,13){
            if(!i)a.fi+=c[i][j]/3,c[i][j]%=3;
            else b.fi+=c[i][j]/3,c[i][j]%=3;
        }
        a.se=dp(c[0]);
        b.se=dp(c[1]);
        if(a>b)
            printf("1\n");
        else if(b>a)
            printf("2\n");
        else printf("0\n");
    }
}
