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

int d[2][1001][1001];
int x[2],y[2];
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif     

    int n;
    scanf("%d%d%d%d%d",&n,&x[0],&y[0],&x[1],&y[1]);
    REP(i,2)REP(j,1001)REP(k,1001)d[i][j][k]=4*1000;
    if(x[0]==x[1]&&(x[0]==0||x[0]==n)||y[0]==y[1]&&(y[0]==0||y[0]==n))printf("%d\n", abs(x[0]-x[1])+abs(y[0]-y[1]));
    else{
        queue<pair<int,pair<int,int> > >q;
        q.push(mp(0,mp(x[0],y[0]))),q.push(mp(1,mp(x[1],y[1])));
        d[0][x[0]][y[0]]=0,d[1][x[1]][y[1]]=0;
        while(q.size()){
            pair<int,pair<int,int> >p=q.front();q.pop();
            REP(i,2)REP(j,2){
                int x=i*n,y=j*n;
                if((x==p.se.fi||y==p.se.se)&&
                    d[p.fi][x][y]>d[p.fi][p.se.fi][p.se.se]+abs(x-p.se.fi)+abs(y-p.se.se)){
                    // printf("%d\n", p.fi);
                    d[p.fi][x][y]=d[p.fi][p.se.fi][p.se.se]+abs(x-p.se.fi)+abs(y-p.se.se);
                    q.push(mp(p.fi,mp(x,y)));
                }
            }
        }
        int b=4*1000;
        REP(i,1001)REP(j,1001)b=min(b,d[0][i][j]+d[1][i][j]);
        printf("%d\n", b);
    }
    return 0;
}