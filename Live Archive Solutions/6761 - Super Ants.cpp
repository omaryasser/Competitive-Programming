/*
-naive dp is to save x, y, remaining time and to loop on all reachable cells and add their dp.
-just add the direction attribute in the state and you will not need to loop on all reachable cell.
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

const int N=51;
int a[N][N];
int mem[9][N][N][501];
int MOD=1e9+7;
int n,m;
int dx[]={0,0,1,-1,1,-1,1,-1};
int dy[]={1,-1,0,0,1,1,-1,-1};


bool valid(int x,int y){
    return x>=0&&y>=0&&x<n&&y<m;
}

int dp(int dir,int x,int y,int rm){
    if(!rm)return a[x][y];
    int &ret=mem[dir][x][y][rm];
    if(ret!=-1)return ret;
    ret=a[x][y];
    if(dir!=8){
        int nx=x+dx[dir],ny=y+dy[dir];
        if(valid(nx,ny))ret+=dp(dir,nx,ny,rm-1);
        if(ret>=MOD)ret-=MOD;
    }
    REP(d,8){
        int nx=x+dx[d],ny=y+dy[d];
        if(!valid(nx,ny))continue;
        // cerr<<nx<<" "<<ny<<" "<<dp(d,nx,ny,rm-1)<<"\n";
        ret+=dp(d,nx,ny,rm-1);
        if(ret>=MOD)ret-=MOD;
    }
    return ret;
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int tc;
    cin>>tc;
    while(tc--){
        memset(mem,-1,sizeof mem);
        int rm,sx,sy;
        cin>>n>>m>>rm;
        cin>>sx>>sy;
        REP(i,n)REP(j,m)cin>>a[i][j];
        cout<<dp(8,sx-1,sy-1,rm)<<"\n";
    }
}
