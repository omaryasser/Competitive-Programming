#include<bits/stdc++.h>
#define lp(i,j,n) for(int i=j;i<n;i++)
using namespace std;

typedef long long ll;
int main() {
    ios_base::sync_with_stdio(0);cin.tie(NULL);
    int k;
    cin>>k;
    int cnt=1;
    for(int i=1;i<=50000000;i++){
        int sum=0,tmp=i;
        while(tmp)sum+=tmp%10,tmp/=10;
        if(sum==10){
            if(cnt==k){cout<<i<<"\n";return 0;}
            cnt++;
        }
    }
}