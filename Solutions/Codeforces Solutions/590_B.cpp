/*
The idea is to separate the the two velocities.
*/
#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

const int N=50001;
char s[N];

double sq(double x){return x*x;}
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif     

    double x1,y1,x2,y2,v,t,vx,vy,wx,wy;
    scanf("%lf%lf%lf%lf%lf%lf%lf%lf%lf%lf",&x1,&y1,&x2,&y2,&v,&t,&vx,&vy,&wx,&wy);
    double lo=0,hi=1e8;
    REP1(dum,0,1000){
        double m=(lo+hi)/2;
        double x11=x1+min(t,m)*vx,y11=y1+min(t,m)*vy;
        x11+=max(0.0,m-t)*wx,y11+=max(0.0,m-t)*wy;
        double d=sqrt(sq(x2-x11)+sq(y2-y11));
        if(d/v<=m){
            //printf("m = %lf d = %lf xAfter = %lf yAfter = %lf need = %lf\n", m,d,x11,y11,d/(v*m));
            hi=m;
        }
        else lo=m;
    }

    printf("%.10lf\n", lo);
    
    return 0;
}