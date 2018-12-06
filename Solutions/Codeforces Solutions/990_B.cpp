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

    //freopen("in.txt","r",stdin);

    int n,k;
    cin>>n>>k;
    vector<int>v;
    REP(i,n){
        int x;
        cin>>x;
        v.pb(x);
    }
    sort(all(v));
    stack<int>s;
    int rem=0;
    REP(i,n){
        int cnt=0;
        REP1(j,i,n)
            if(v[j]==v[i])cnt++;
            else break;
        i+=cnt-1;
        while(!s.empty()){
            if(v[i]>s.top()&&v[i]-k<=s.top()){
                s.pop();
            }else break;
        }
        while(cnt--)s.push(v[i]);
    }
    cout<<s.size()<<"\n";
}