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

const int N=7001;
int a[2][N];
int n;
int len[2];
int stat[2][N];
int WIN=1,LOSE=0;
bool vis[2][N];
int st[2][2][N];

void go(int s){
    queue<pair<int,pair<int,int> > >q;
    q.push(mp(0,mp(0,0))),vis[0][0]=1;
    q.push(mp(0,mp(1,0))),vis[1][0]=1;
    REP(i,len[s]){
        if(!vis[s][-a[s][i]+n])q.push(mp(1,mp(s,-a[s][i]+n)));
        vis[s][-a[s][i]+n]=1;
    }
    while((int)q.size()){
        pair<int,pair<int,int> >p=q.front();q.pop();
        stat[p.se.fi][p.se.se]=p.fi;
        REP(i,len[1^p.se.fi]){
            st[1^p.se.fi][1^p.fi][(p.se.se-a[1^p.se.fi][i]+n)%n]++;
            bool c=p.fi==LOSE?1:
            st[1^p.se.fi][1^p.fi][(p.se.se-a[1^p.se.fi][i]+n)%n]==len[1^p.se.fi];
            if(!vis[1^p.se.fi][(p.se.se-a[1^p.se.fi][i]+n)%n]&&c)
                q.push(mp(1^p.fi,mp(1^p.se.fi,(p.se.se-a[1^p.se.fi][i]+n)%n))),
                    vis[1^p.se.fi][(p.se.se-a[1^p.se.fi][i]+n)%n]=1;
        }
    }
}
int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    scanf("%d",&n);
    scanf("%d",&len[0]);
    REP(i,len[0])scanf("%d",a[0]+i);
    scanf("%d",&len[1]);
    REP(i,len[1])scanf("%d",a[1]+i);
    memset(stat,-1,sizeof stat);
    go(0);
    go(1);
    REP(i,2){
        REP1(j,1,n){
            if(j>1)printf(" ");
            if(stat[i][j]==WIN)printf("Win");
            else if(stat[i][j]==LOSE)printf("Lose");
            else printf("Loop");
        }
        printf("\n");
    }
    return 0;
}