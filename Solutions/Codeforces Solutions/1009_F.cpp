#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int n;
const int N=1e6+10;
unordered_map<int,int>cnt[N];
pair<int,int> bst[N];
int rep[N];
vector<int>adj[N];
int res[N];
int lvl[N];
int sz[N];
void dfs(int u=0,int p=-1){
    sz[u]=1;
    int mx=-1;
    for(int v:adj[u]){
        if(v!=p){
            lvl[v]=lvl[u]+1;
            dfs(v,u);
            if(mx==-1||sz[v]>sz[mx])mx=v;
            sz[u]+=sz[v];
        }
    }
    if(mx!=-1)rep[u]=rep[mx];
    else bst[rep[u]]=make_pair(-1,-1);
    cnt[rep[u]][lvl[u]]++;
    if(cnt[rep[u]][lvl[u]]>bst[rep[u]].first||(lvl[u]<bst[rep[u]].second&&cnt[rep[u]][lvl[u]]==bst[rep[u]].first)){
        bst[rep[u]]=make_pair(cnt[rep[u]][lvl[u]],lvl[u]);
    }
//    cerr<<u<<" "<<bst[rep[u]].first<<" "<<bst[rep[u]].second<<"\n";
    for(int v:adj[u]){
        if(v!=p&&v!=mx){
            for(auto p:cnt[rep[v]]) {
                cnt[rep[u]][p.first] += p.second;
                if(cnt[rep[u]][p.first]>bst[rep[u]].first||(p.first<bst[rep[u]].second&&cnt[rep[u]][p.first]==bst[rep[u]].first)){
                    bst[rep[u]]=make_pair(cnt[rep[u]][p.first],p.first);
                }
            }
        }
    }
    res[u]=bst[rep[u]].second-lvl[u];
}
int main(){
    REP(i,N)rep[i]=i;
    scanf("%d",&n);
    REP(i,n-1){
        int u,v;
        scanf("%d%d",&u,&v);
        adj[--u].push_back(--v);
        adj[v].push_back(u);
    }
    dfs();
    REP(i,n)cout<<res[i]<<"\n";
}