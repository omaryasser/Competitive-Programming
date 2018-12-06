#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int cnt[(int)4e5+10];
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n,d,k;
    cin>>n>>d>>k;
    int idx=1;
    vector<pair<int,int> >ret;
    queue<pair<int,int> >q;

    REP(i,d){
        ret.push_back(make_pair(idx,1+idx));
        cnt[idx]++;
        cnt[1+idx]++;
        idx++;
        if(i!=d-1&&min(i+1,d-i-1))q.push(make_pair(idx,min(i+1,d-i-1)));
    }


    while(!q.empty()){
        if(idx>=n)break;
        auto p=q.front();q.pop();
        int u=p.first,rem=p.second;
        int nxtRem=rem-1;
        while(idx<n&&cnt[u]<k){
            ret.push_back(make_pair(u,++idx));
            cnt[u]++;
            cnt[idx]++;
            if(nxtRem)q.push(make_pair(idx,nxtRem));
        }
    }

    REP(i,n)if(cnt[i]>k){
        cout<<"NO\n";
        return 0;
    }
    if(idx==n&&d<=n-1){
        cout<<"YES\n";
        for(auto p:ret)
            cout<<p.first<<" "<<p.second<<"\n";
    }
    else cout<<"NO\n";

    return 0;
}