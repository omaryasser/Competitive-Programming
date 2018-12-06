#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n,k;
    cin>>n>>k;
    vector<int>a(n);
    REP(i,n)cin>>a[i];
    double res=0;
    REP1(i,k,n+1){
        int sum=0;
        REP(j,i)sum+=a[j];
        int mx=sum;
        REP1(j,i,n){
            sum-=a[j-i];
            sum+=a[j];
            mx=max(mx,sum);
        }
        res=max(res,1.0*mx/i);
    }
    cout<<fixed<<setprecision(10)<<res<<"\n";
    return 0;
}