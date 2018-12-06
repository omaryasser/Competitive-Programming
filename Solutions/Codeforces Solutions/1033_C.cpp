#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
#define all(v) v.begin(), v.end()
#define ll long long
using namespace std;

const int n_ = 1e5 + 10;
int n;
vector<int> adj[n_];
int mem[n_];
int dp(int idx){
    if((int)adj[idx].size() == 0)return 0;
    int &ret = mem[idx];
    if(ret != -1)return ret;
    bool can = 0;
    for(int v : adj[idx])if(!dp(v))can = 1;
    return ret = can;
}
int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    cin >> n;
    memset(mem, -1, sizeof mem);
    vector<int> a(n);
    f(i, 0, n)cin >> a[i];
    f(i, 0, n){
        for(int m = 1; 1ll * i + m * a[i] < n || 1ll * i - m * a[i] >= 0; m++){
            int j1 = i + m * a[i], j2 = 1ll * i - m * a[i];
            if(j1 < n && a[j1] > a[i]){
                adj[i].push_back(j1);
            }
            if(j2 >= 0 && a[j2] > a[i]){
                adj[i].push_back(j2);
            }
        }
    }
    f(i, 0, n){
        if(dp(i))cout << "A";
        else cout <<"B";
    }
    cout << "\n";
}