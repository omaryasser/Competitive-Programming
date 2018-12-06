#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int N=2e5+10;
vector<int>T[N];
int n;

pair<int,int>dfs(int u,int p){
    auto ret=make_pair(u,0);
    for(int v:T[u])
        if(v!=p){
            auto go=dfs(v,u);
            if(go.second+1>ret.second)
                ret=make_pair(go.first,go.second+1);
        }
    return ret;
};

int far(int u){
    return dfs(u,-1).first;
}

vector<int>tmp;
bool dfs(int u,int p,int trg){
    if(u==trg)return true;
    bool f=0;
    for(int v:T[u])
        if(v!=p){
            if(dfs(v,u,trg)){
                f=1;
                tmp.push_back(v);
            }
        }
    return f;
}
vector<int>path(int u,int v){
    dfs(u,-1,v);
    tmp.push_back(u);
    return tmp;
}

int go1(int u,int p){
    int lst=-2;
    for(int v:T[u])
        if(v!=p){
            int her=go1(v,u);
            if(her==-1)return -1;
            if(lst==-2)lst=her;
            else if(lst!=her)return -1;
        }
    return lst==-2?1:lst+1;
}
int go(int root){
    unordered_set<int>depths;
    for(int v:T[root]){
        int her=go1(v,root);
        if(her==-1)return -1;
        depths.insert(her);
    }
    if(depths.size()<=2){
        int res=0;
        while(depths.size()){
            res+=*depths.begin();
            depths.erase(depths.begin());
        }
        while(res%2==0)res/=2;
        return res;
    }
    return -1;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n;
    REP(i,n-1){
        int u,v;
        cin>>u>>v;
        T[--u].push_back(--v);
        T[v].push_back(u);
    }
    int far1=far(0),far2=far(far1);
    vector<int>diam=path(far1,far2);
    vector<int>cands;
    cands.push_back(diam[diam.size()/2]);
    if(diam.size()%2==0)cands.push_back(diam[(diam.size()-1)/2]);
    int rt=-1;
    for(int v:cands){
        int chk=go(v);
        if(chk!=-1){
            if(rt==-1)rt=chk;
            else rt=min(rt,chk);
        }
    }
    cout<<rt<<"\n";
}