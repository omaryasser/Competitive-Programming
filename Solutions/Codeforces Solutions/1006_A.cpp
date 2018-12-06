#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";
#define mp(x,y) make_pair(x,y)
typedef long long ll;

using namespace std;

int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n;
    cin>>n;
    REP(i,n){
        int x;
        cin>>x;
        if(i)cout<<" ";
        cout<<(x&1?x:x-1);
    }
    cout<<"\n";
}