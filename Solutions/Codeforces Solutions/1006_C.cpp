#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";
#define mp(x,y) make_pair(x,y)
typedef long long ll;

using namespace std;

ll a[(int)2e5+10];
int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n;
    cin>>n;
    unordered_map<ll,int>pos;
    ll sum=0;
    ll res=0;
    REP(i,n){
        cin>>a[i];
        sum+=a[i];
        pos[sum]=i;
    }
    sum=0;
    for(int i=n-1;i>=0;i--){
        sum+=a[i];
        if(pos.find(sum)!=pos.end()&&pos[sum]<i){
            res=max(res,sum);
        }
    }
    cout<<res<<"\n";
}