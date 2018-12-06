#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int N=1e5+10;
vector<pair<int,int> >T[N];
int n;

pair<int,int>dfs(int u,int p=-1){
    pair<int,int>res=make_pair(u,0);
    for(auto edge:T[u]) {
        int v=edge.first,cost=edge.second;
        if (v!=p){
            pair<int,int>go=dfs(v,u);
            if(go.second+cost>res.second){
                res=make_pair(go.first,go.second+cost);
            }
        }
    }
    return res;
}
vector<int>path,path_cost;
bool dfs2(int u,int trg,int p){
    if(u==trg){
        path.push_back(u);
        return 1;
    }
    for(auto edge:T[u]){
        int v=edge.first;
        if(v!=p){
            if(dfs2(v,trg,u)){
                path_cost.push_back(edge.second);
                path.push_back(u);
                return 1;
            }
        }
    }
    return 0;
}
int main() {

    int k;
    scanf("%d%d",&n,&k);
    REP(i,n-1){
        int u,v,c;
        scanf("%d%d%d",&u,&v,&c);
        T[--u].push_back(make_pair(--v,c));
        T[v].push_back(make_pair(u,c));
    }

    int v1=dfs(0).first,v2=dfs(v1).first;
    dfs2(v1,v2,-1);
    vector<int>acc(path_cost.size());
    if(path_cost.size())acc[0]=path_cost[0];
    if(path_cost.size())REP1(i,1,path_cost.size())acc[i]=acc[i-1]+path_cost[i];
    k=min(k,(int)path.size());
    int bst=1e9+10,bstS=0;
    REP(lft,path.size()){
        int rght=min((int)path.size()-1,lft+k-1);
        int lftD=lft?acc[lft-1]:0;
        int rghtD=rght!=path.size()-1?(acc[acc.size()-1]-(rght?acc[rght-1]:0)):0;
        if(max(lftD,rghtD)<bst){
            bst=max(lftD,rghtD);
            bstS=lft;
        }
    }
    int mx=bst;

    queue<int>q;
    int INF=1e9+91;
    vector<int>dist(n,INF);
    REP1(i,bstS,min((int)path.size(),bstS+k)){
        q.push(path[i]);
        dist[path[i]]=0;
    }

    while(!q.empty()){
        int cur=q.front();q.pop();
        for(auto edge:T[cur]){
            int v=edge.first;
            if(dist[v]==INF){
                dist[v]=dist[cur]+edge.second;
                mx=max(mx,dist[v]);
                q.push(v);
            }
        }
    }
    cout<<mx<<"\n";

    return 0;
}