#include <bits/stdc++.h>
#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n;
    cin>>n;
    vector<pair<int,int> >v;
    for(int i=0;i<n;i++){
        int sum=0;
        REP(i,4){
            int x;
            cin>>x;
            sum+=x;
        }
        v.push_back(make_pair(120000-sum,i));
    }
    sort(all(v));
    REP(i,n)if(v[i].second==0){
        cout<<i+1<<"\n";
    }
}