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
    int n;
    cin>>n;
    map<int,int>mp;
    int cnt=0;
    REP(i,n){
        int x;
        cin>>x;
        mp[x]++;
    }
    int hv=0;
    for(auto p:mp){
        int num=p.first,occ=p.second;
        cnt+=min(hv,occ);
        hv=hv-min(hv,occ)+occ;
    }
    cout<<cnt<<"\n";
}