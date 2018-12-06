#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int n;
const int N=5002;
int a[N];
int res[N];
vector<pair<int,int> >T[N];
vector<pair<int,pair<int,int> > > dfs(int u=0,int p=-1,int cap=0){
    vector<pair<int,pair<int,int> >>coming;
    for(auto edge:T[u])
        if(edge.first!=p){
            auto st=dfs(edge.first,u,edge.second);
            for(auto x:st)coming.push_back(x);
        }
    sort(all(coming));
    if(!u){
        for(auto pp:coming)
            res[pp.second.second]=pp.first;
        return vector<pair<int,pair<int,int> > >();
    }else{
        int idxCom=0;
        vector<pair<int,pair<int,int> > >ret;
        ret.push_back(make_pair(1,make_pair(a[u],u)));
        int tim=1,cnt=0;
        priority_queue<pair<int,int>,vector<pair<int,int> >, greater<pair<int,int> > >active;
        while(active.size()||idxCom<coming.size()){
            if(cnt==0||cnt==cap||active.empty()){
                cnt=0;
                while(idxCom<coming.size()){
                    auto cur=coming[idxCom];
                    if(cur.first==tim){
                        idxCom++;
                        active.push(cur.second);
                    }else break;
                }
                tim++;
            }
            while(active.size()&&cnt<cap){
                cnt++;
                auto cur=active.top();
                active.pop();
                ret.push_back(make_pair(tim,cur));
            }
        }
        return ret;
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n;
    REP(i,n)cin>>a[i];
    REP(i,n-1){
        int u,v,cap;
        cin>>u>>v>>cap;
        T[--u].push_back(make_pair(--v,cap));
        T[v].push_back(make_pair(u,cap));
    }
    dfs();
    REP(i,n){
        if(i)cout<<" ";
        cout<<res[i];
    }
    cout<<"\n";
}