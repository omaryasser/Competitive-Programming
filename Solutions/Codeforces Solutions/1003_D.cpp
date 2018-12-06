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

    map<int,int>mp;
    int n,q;
    cin>>n>>q;
    while(n--){
        int x;
        cin>>x;
        mp[x]++;
    }

    while(q--){
        int x;
        cin>>x;
        int cnt=0;
        for(auto it=--mp.end();;it--){
            int pw=it->first,her=it->second;
            int tk=min(her,x/pw);
            x-=tk*pw;
            cnt+=tk;
            if(it==mp.begin())break;
        }
        if(x)cout<<"-1\n";
        else cout<<cnt<<"\n";
    }


    return 0;
}