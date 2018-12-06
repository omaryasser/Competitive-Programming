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

const int N=100001;
int n,m;
bool hasp[N];
vector<int>T[N];
int a[N];
bool ok=1;
stack<int>s;

void dfs(int u,int l){
    for(int v:T[u]){
        if(a[v]!=l&&a[v]!=v){
            ok=0;
        }else if(a[v]==v){
            s.push(v);
        }
        dfs(v,a[v]);
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>m;
    while(m--){
        int u,v;
        cin>>u>>v;
        --u,--v;
        hasp[v]=1;
        T[u].pb(v);
    }
    REP(i,n){
        cin>>a[i];
        a[i]--;
    }
    REP(i,n)if(!hasp[i]){
        s.push(i);
        dfs(i,i);
    }
    if(ok){
        cout<<s.size()<<"\n";
        while((int)s.size()){
            cout<<s.top()+1<<"\n";
            s.pop();
        }
    }else cout<<"-1\n";
}