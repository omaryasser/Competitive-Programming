// standard bitonic tcp
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

int n;
const int N=101;
double mem[2][2][N][N];
int nxtt[2][2][N][N];

struct point{
    int x,y;
    point(int xx,int yy):x(xx),y(yy){}
    point(){}
}pts[N];

bool comp(point p1,point p2){
    return p1.x!=p2.x?p1.x<p2.x:p1.y<p2.y;
}

int f,s;

double sq(double x){return x*x;}
double dist(int p1,int p2){
    return sqrt(sq(pts[p1].x-pts[p2].x)+sq(pts[p1].y-pts[p2].y));
}

double dp(int t1,int t2,int p1,int p2){
    double &ret=mem[t1][t2][p1][p2];
    if(ret>-1)return ret;
    int nxt=max(p1,p2)+1;
    if(nxt==n)return p1==n-1?dist(p2,n-1):dist(p1,n-1);
    // first
    if((nxt!=f&&nxt!=s)||!t1){
        ret=dist(p1,nxt)+dp(t1||nxt==f||nxt==s,t2,nxt,p2);
        nxtt[t1][t2][p1][p2]=0;
    }
    // second
    if((nxt!=f&&nxt!=s)||!t2){
        if(ret<-1){
            ret=dist(p2,nxt)+dp(t1,t2||nxt==f||nxt==s,p1,nxt);
            nxtt[t1][t2][p1][p2]=1;
        }
        else{
            if(dist(p2,nxt)+dp(t1,t2||nxt==f||nxt==s,p1,nxt)<ret)
                nxtt[t1][t2][p1][p2]=1;
            ret=min(ret,dist(p2,nxt)+dp(t1,t2||nxt==f||nxt==s,p1,nxt));
        }
    }
    return ret;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int tc=1;
    while(1){
        cin>>n>>f>>s;
        if(!n)break;
        REP(i,2)REP(j,2)REP(k,N)REP(l,N)mem[i][j][k][l]=-2;
        REP(i,n)cin>>pts[i].x>>pts[i].y;
        sort(pts,pts+n,comp);
        cout<<"Case "<<tc<<": "<<setprecision(2)<<fixed<<dp(0,0,0,0)<<"\n";
        vector<int>r1,r2;
        int t1=0,t2=0,p1=0,p2=0;

        while(max(p1,p2)!=n-1){
            int nxt=max(p1,p2)+1;
            if(nxtt[t1][t2][p1][p2]){
                r2.push_back(nxt);
                t2=t2||(nxt==f)||(nxt==s);
                p2=nxt;
            }else{
                r1.push_back(nxt);
                t1=t1||(nxt==f)||(nxt==s);
                p1=nxt;
            }
        }
        reverse(all(r2));
        cout<<0;
        for(int v:r1)cout<<" "<<v;
        for(int v:r2)cout<<" "<<v;
        cout<<" "<<0<<"\n";
        tc++;
    }
}
