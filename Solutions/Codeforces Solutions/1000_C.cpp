#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


const int N=2e5+10;
ll res[N];

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin>>n;
    map<ll,vector<int> >st;
    REP(i,n){
        ll l,r;
        cin>>l>>r;
        st[l].push_back(0);
        st[r].push_back(1);
    }
    ll lst=-1,cnt=0;
    for(auto p:st){
        ll curP=p.first;
        vector<int>events=p.second;
        int strts=0;
        for(auto x:events)strts+=x==0;
        if(lst!=-1){
            res[cnt]+=curP-lst;
            res[cnt+strts]++;
            lst=curP+1;
            cnt+=strts;
            cnt-=events.size()-strts;
        }else{
            res[cnt+strts]++;
            cnt+=strts;
            cnt-=events.size()-strts;
            lst=curP+1;
        }
    }
    REP1(i,1,n+1){
        if(i>1)cout<<" ";
        cout<<res[i];
    }
    cout<<"\n";
}