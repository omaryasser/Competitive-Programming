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



int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin>>n;
    int k;
    cin>>k;
    vector<int>v(n);
    REP(i,n)cin>>v[i];
    int cnt=0;
    int l=0;
    while(l<n&&v[l]<=k)cnt++,l++;
    int r=n-1;
    while(r>l&&v[r]<=k)cnt++,r--;
    cout<<cnt<<"\n";
}