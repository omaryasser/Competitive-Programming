#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int main(){
    ios_base::sync_with_stdio(0);cin.tie(0);
    int n,m;
    cin>>n>>m;
    vector<int>a(n),b(m);
    REP(i,n)cin>>a[i];
    REP(i,m)cin>>b[i];
    int cnt=0;
    int idx=0;
    REP(i,n){
        if(idx<m&&b[idx]>=a[i]){
            cnt++;
            idx++;
        }
    }
    cout<<cnt<<"\n";
}