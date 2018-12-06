#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n,r;
    cin>>n>>r;
    double res=0;
    vector<int>v(1<<n);
    REP(i,1<<n){
        cin>>v[i];
        res+=v[i];
    }
    double pw=1;
    REP(i,n)pw*=1.0/2;
    cout<<setprecision(8)<<fixed<<res*pw<<"\n";
    while(r--){
        int z,g;
        cin>>z>>g;
        res-=v[z];
        res+=g;
        v[z]=g;
        cout<<res*pw<<"\n";
    }
}