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

    int n;
    multiset<int>st;
    cin>>n;
    int sum=0;
    REP(i,n){
        int x;
        cin>>x;
        sum+=x;
        st.insert(x);
    }
//    cerr<<"\sdf\n";
    int cnt=0;
    while(round(sum+n/2)/n<5){
       // cerr<<(sum+n-1)/n<<"\n";
        int f=*st.begin();
        st.erase(st.begin());
        sum-=f;
        sum+=5;
        cnt++;
    }
    cout<<cnt<<"\n";
}