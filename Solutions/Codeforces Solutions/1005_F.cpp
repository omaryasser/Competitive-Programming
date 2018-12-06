#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int n,k;
vector<string>res;
const int N=2e5+10;
vector<pair<int,int> >G[N];
vector<int>lvl[N];
int dep[N];
int LVLS;
string cur;
void print(){
    printf("%d\n",res.size());
    for(string s:res)
        printf("%s\n",s.c_str());
}
void go(int lvll,int idx=0){
    if(lvll==LVLS+1){
        res.push_back(cur);
        if(res.size()==k){
            print();
            exit(0);
        }
        else return;
    }


    int u=lvl[lvll][idx];
    for(auto edge:G[u]){
        int v=edge.first,edg=edge.second;
        if(dep[v]<dep[u]){
            cur[edg]='1';
            if(idx==lvl[lvll].size()-1)go(lvll+1);
            else go(lvll,idx+1);
            cur[edg]='0';
        }
    }
}
int main(){
    int m;
    scanf("%d%d%d",&n,&m,&k);
    REP(i,m){
        int a,b;
        scanf("%d%d",&a,&b);
        G[--a].push_back(make_pair(--b,i));
        G[b].push_back(make_pair(a,i));
    }

    queue<int>q;
    q.push(0);q.push(0);
    vector<bool>vis(N,0);
    vis[0]=1;
    cur=string(m,'0');
    while(!q.empty()){
        int v=q.front();q.pop();
        int lvll=q.front();q.pop();
        lvl[lvll].push_back(v);
        dep[v]=lvll;
        for(auto edge:G[v])
            if(!vis[edge.first]){
                vis[edge.first]=1;
                q.push(edge.first);
                q.push(lvll+1);
                LVLS=lvll+1;
            }
    }
    go(1);
    print();
}