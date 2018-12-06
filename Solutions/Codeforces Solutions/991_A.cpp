#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int a,b,c,n;
    cin>>a>>b>>c>>n;
    if(c<=a&&c<=b&&n-(a+b)+c>=1&&a<=n&&b<=n&&a+b-c<=n){
        cout<<n-(a+b)+c<<"\n";
    }else{
        cout<<"-1\n";
    }
}