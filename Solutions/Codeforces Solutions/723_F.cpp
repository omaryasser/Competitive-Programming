#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

struct Pair{
    int first,second;
    Pair(int f,int s):first(f),second(s){}
    bool operator==(const Pair& other)const{
        return first==other.first;
    }
};

namespace std{
template <>
struct hash<Pair>{
    size_t operator()(const Pair& p)const{
        return hash<int>()(p.first);
    }
};
}

const int N=200001;
int n,m,v1,v2,m1,m2,cc;
vector<int>G[N];
vector<pair<int,int> >res;
int CC[N];
bool vis[N];

void dfs(int u){
    vis[u]=1;
    CC[u]=cc;
    for(int v:G[u])
        if(!vis[v]&&v!=v1&&v!=v2){
            res.push_back(make_pair(u,v));
            dfs(v);
        }
}


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>m;
    while(m--){
        int u,v;
        cin>>u>>v;
        G[--u].push_back(--v);
        G[v].push_back(u);
    }
    cin>>v1>>v2>>m1>>m2;
    v1--,v2--;

    REP(i,n)
        if(!vis[i]&&i!=v1&&i!=v2){
            dfs(i);
            cc++;
        }

    unordered_set<Pair>adj1,adj2;
    bool must=1;
    for(int v:G[v1])
        if(v!=v2)
            adj1.insert(Pair(CC[v],v));
    for(int v:G[v2])
        if(v!=v1)adj2.insert(Pair(CC[v],v));

    for(auto v:adj1){
        if(adj2.find(v)==adj2.end()){
            if(m1)m1--,res.push_back(make_pair(v1,v.second));
            else {
                cout<<"No\n";
                return 0;
            }
        }
    }
    for(auto v:adj2){
        if(adj1.find(v)==adj1.end()){
            if(m2)m2--,res.push_back(make_pair(v2,v.second));
            else {
                cout<<"No\n";
                return 0;
            }
        }
    }
    int ad=-1;
    for(auto v:adj1){
        if(adj2.find(v)!=adj2.end()){
            if(must){
                if(!m1||!m2){
                    cout<<"No\n";
                    return 0;
                }else{
                    must=0;
                    m1--,res.push_back(make_pair(v1,v.second));
                    m2--,ad=v.first;
                }
            }
            else{
                if(m1)m1--,res.push_back(make_pair(v1,v.second));
                else if(m2)m2--,res.push_back(make_pair(v2,v.second));
                else {
                    cout<<"No\n";
                    return 0;
                }
            }
        }
    }
    if(ad!=-1){
        for(auto v:adj2)
            if(v.first==ad){
                res.push_back(make_pair(v2,v.second));
                break;
            }
    }
    for(int v:G[v1]){
        if(v==v2&&must&&m1&&m2){
            must=0;
            res.push_back(make_pair(v1,v2));
            m1--,m2--;
        }
    }
    if(must){
        cout<<"No\n";
        return 0;
    }
    cout<<"Yes\n";
    for(auto p:res)
        cout<<p.first+1<<" "<<p.second+1<<"\n";
}