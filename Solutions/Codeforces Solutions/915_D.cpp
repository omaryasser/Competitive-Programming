#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)


using namespace std;
typedef long long ll;

int V,E;
const int MAX_N = 501;
vector<int>adjList[MAX_N];
vector<int>cycle;
int cameFrom[MAX_N];
int dfs_num[MAX_N];
int UNVISTED=0,VISITED=1,EXPLORED=2;

int first=-1,second=-1;
bool can=true;

void dfs(int u){
    dfs_num[u]=EXPLORED;
    for(auto v:adjList[u]){
        if(u==first&&v==second)continue;
        if(dfs_num[v]==UNVISTED){
            cameFrom[v]=u;
            dfs(v);
        }else if(dfs_num[v]==EXPLORED){
            can=false;
            if(cycle.empty()){
                int tmp=u;
                cycle.push_back(tmp);
                while(1){
                    tmp=cameFrom[tmp];
                    cycle.push_back(tmp);
                    if(tmp==v)break;
                }
                reverse(cycle.begin(),cycle.end());
            }
        }
    }
    dfs_num[u]=VISITED;
}

int main() {
    scanf("%d%d",&V,&E);
    while(E--){
        int u,v;
        scanf("%d%d",&u,&v);
        u--,v--;
        adjList[u].push_back(v);
    }
    FOR(i,0,V)if(dfs_num[i]==UNVISTED)dfs(i);
    if(cycle.empty())return puts("YES");
    FOR(i,0,cycle.size()){
        first=cycle[i],second=cycle[(i+1)%cycle.size()];
        can=true;
        memset(dfs_num,0, sizeof(dfs_num));
        FOR(j,0,V)if(dfs_num[j]==UNVISTED)dfs(j);
        if(can)return puts("YES");
    }
    return puts("NO");
}