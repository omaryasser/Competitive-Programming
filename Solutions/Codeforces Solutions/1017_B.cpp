#include <bits/stdc++.h>
#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n;
    string a,b;
    cin>>n;
    cin>>a>>b;
    int zeros=0;
    REP(i,n)if(a[i]-'0'==0)zeros++;
    int ones=n-zeros;
    ll res=0;
    REP(i,n){
        if(b[i]=='0'){
            res+=(a[i]=='1'?zeros:ones);
        }
    }
    ones=0,zeros=0;
    REP(i,n){
        if(b[i]=='0'){
            if(a[i]=='1')ones++;
            else zeros++;
        }
    }
    cout<<res-1ll*zeros*ones<<"\n";
}