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

    int n,k;
    cin>>n>>k;
    unordered_map<int,int>mp;
    REP(i,n){
        int x;
        cin>>x;
        mp[x]=i+1;
    }
    if(mp.size()<k)cout<<"NO\n";
    else{
        cout<<"YES\n";
        while(k--){
            cout<<mp.begin()->second<<"\n";
            mp.erase(mp.begin());
        }
    }
}