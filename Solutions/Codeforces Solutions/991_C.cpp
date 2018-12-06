#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;



int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    ll n;
    cin>>n;
    ll lo=1,hi=n;
    while(lo<hi){
        ll m=lo+hi>>1;
//        cerr<<lo<<" "<<hi<<" "<<m<<"\n";
        ll v=0,tmp=n;
        while(tmp){
            v+=min(m,tmp);
            tmp-=min(m,tmp);
            tmp-=tmp/10;
        }
        if(v*2>=n){
            hi=m;
        }else lo=m+1;
    }
    cout<<lo<<"\n";
}