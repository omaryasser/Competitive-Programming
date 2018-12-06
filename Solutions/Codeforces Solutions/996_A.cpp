#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int a[]={1
        ,
         5
        ,
         10
        ,
         20
        ,
         100};
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin>>n;
    int cnt=0;
    REP(i,5)
        cnt+=n/a[5-i-1],n%=a[5-i-1];
    cout<<cnt<<"\n";
}