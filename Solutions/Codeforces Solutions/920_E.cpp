#include<bits/stdc++.h>
#define lp(i,j,n) for(int i=j;i<n;i++)
using namespace std;

typedef long long ll;

unordered_set<int>rem;
unordered_set<int>adj[200001];
int cnt=0;
void dfs(int u){
    vector<int>remove;
    for(int num:rem){
        if(!adj[u].count(num)){
            remove.push_back(num);
            cnt++;
        }
    }
    for(int num:remove){
        rem.erase(num);
    }
    for(int num:remove)dfs(num);
}
int main() {
    ios_base::sync_with_stdio(0);cin.tie(NULL);
    int n,m;
    cin>>n>>m;
    while(m-->0){
        int x,y;
        cin>>x>>y;x--,y--;
        adj[x].insert(y);
        adj[y].insert(x);
    }
    for(int i=0;i<n;i++){
        rem.insert(i);
    }
    vector<int>sizes;
    for (int i=0;i<n;i++){
        if(rem.count(i)){
            cnt=0;
            dfs(i);
            sizes.push_back(cnt);
        }
    }
    sort(sizes.begin(),sizes.end());
    cout<<sizes.size()<<"\n";
    for (int num:sizes)cout<<num<<"\n";
}