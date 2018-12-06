#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n;
    cin>>n;
    vector<pair<int,int> >v(n);
    REP(i,n){
        cin>>v[i].first;v[i].second=i;
    }
    sort(all(v));
    if(n==1||n==2&&v[0].first==v[1].first)cout<<"-1\n";
    else{
        cout<<"1\n"<<v[0].second+1<<"\n";
    }
    return 0;
}