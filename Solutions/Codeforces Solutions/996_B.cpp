#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin>>n;
    int bst=1e9+10,ret=0;
    REP(i,n){
        int x;
        cin>>x;
        int fin=(max(0,x-i)+n-1)/n;
        if(fin<bst)bst=fin,ret=i;
    }
    cout<<ret+1<<"\n";
}