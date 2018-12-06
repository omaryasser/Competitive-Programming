#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

struct strrcmp{
    bool operator()(const char *a, const char *b){
        return strcmp(a,b)<0;
    }
};
int n,m;
const int MAX=100002;
int sz[MAX],lvl[MAX],ans[MAX];
map<char *,int,strrcmp>mp[MAX];
char name[MAX][21];
vector<int>tree[MAX];
vector<pair<int,int> >q[MAX];
void remo(int u,int p,int in){
    mp[lvl[u]][name[u]]+=in;
    if(mp[lvl[u]][name[u]]==0)mp[lvl[u]].erase(name[u]);
    for(auto v:tree[u])
        if(v!=p)
            remo(v,u,in);
}
void dfs(int u=0,int p=-1,int rem=0){
    int mx=-1;
    for(auto v:tree[u])
        if(v!=p&&(mx==-1||sz[mx]<sz[v]))
            mx=v;
    for(auto v:tree[u])
        if(v!=p&&v!=mx)
            dfs(v,u,1);
    if(mx!=-1)dfs(mx,u,0);
    for(auto v:tree[u])
        if(v!=p&&v!=mx)
            remo(v,u,1);
    for(auto query:q[u])
        if(query.first+lvl[u]<MAX)
            ans[query.second]=mp[query.first+lvl[u]].size();
        
    mp[lvl[u]][name[u]]++;
    if(rem)remo(u,p,-1);
}
void szz(int u=0,int p=-1){
    sz[u]=1;
    for(auto v:tree[u])
        if(v!=p){
            lvl[v]=lvl[u]+1;
            szz(v,u);
            sz[u]+=sz[v];
        }
}
int main() {
    scanf("%d",&n);
    FOR(i,1,n+1){
        int k;
        scanf("%s %d",name[i],&k);
        tree[k].push_back(i);
    }
    scanf("%d",&m);
    FOR(i,0,m){
        int v,k;
        scanf("%d%d",&v,&k);
        q[v].push_back({k,i});
    }
    string f="aaaaaaaaaaaaaaaaaaaaa";
    strcpy(name[0],f.c_str());
    szz();
    dfs();
    FOR(i,0,m)printf("%d\n",ans[i]);
}