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


const int N=2e5+10;
int n,r;
ll a[N];
int cnt[N];
queue<int>pos[N];
stack<int>take;
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>r;
    REP(i,n){
        cin>>a[i];
        cnt[a[i]%r]++;
        pos[a[i]%r].push(i);
    }
    int need=n/r;
    ll res=0;
//    cerr<<take.size()<<"\n";
//cerr<<r<<"\n";
    REP(i,r){
        //cerr<<"sdff\n";
        if(cnt[i]>need){
            REP(j,cnt[i]-need)take.push(i);
            cnt[i]=need;
        }
        while(take.size()&&cnt[i]<need){
            //cerr<<i<<" "<<cnt[i]<<"\n";
            int p=take.top();take.pop();
            if(i>p){
                a[pos[p].front()]+=i-p;
                res+=(i-p);
            }else{
                a[pos[p].front()]+=(i-p)+r;
                res+=(i-p)+r;
            }
            pos[i].push(a[pos[p].front()]);
            pos[p].pop();
            cnt[i]++;
        }
    }
    REP(i,r){
        if(cnt[i]>need){
            REP(j,cnt[i]-need)take.push(i);
            cnt[i]=need;
        }
        while(take.size()&&cnt[i]<need){
            //cerr<<i<<" "<<cnt[i]<<"\n";

            int p=take.top();take.pop();
            if(i>p){
                a[pos[p].front()]+=i-p;
                res+=(i-p);
            }else{
                a[pos[p].front()]+=(i-p)+r;
                res+=(i-p)+r;
            }
            pos[i].push(a[pos[p].front()]);
            pos[p].pop();
            cnt[i]++;
        }
    }
        cout<<res<<"\n";
    REP(i,n){
        if(i)cout<<" ";
        cout<<a[i];
    }
    cout<<"\n";
}