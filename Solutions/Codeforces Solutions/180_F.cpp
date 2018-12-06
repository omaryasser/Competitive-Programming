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


int n;
const int N=100002;
ll a[N],b[N],c[N];
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n;
    REP(i,n)cin>>a[i];
    REP(i,n){
        cin>>b[i];
        c[a[i]]=b[i];
    }
    REP(i,n)cout<<c[i+1]<<"\n";
}