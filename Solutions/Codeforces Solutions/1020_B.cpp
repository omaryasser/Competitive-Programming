#include <bits/stdc++.h>
#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int n;
const int n_=1001;
int p[n_];
bool inc[n_];
bool vis[n_];
int res;
void dfs(int u){
    if(vis[u]){
        res=u+1;
        return;
    }
    vis[u]=1;
    dfs(p[u]);
}
int main() {

    scanf("%d",&n);
    REP(i,n)scanf("%d",p+i);
    REP(i,n)p[i]--;
    REP(i,n){
        REP(j,n)vis[j]=0;
        dfs(i);
        if(i)printf(" ");
        printf("%d",res);
    }
    printf("\n");
}