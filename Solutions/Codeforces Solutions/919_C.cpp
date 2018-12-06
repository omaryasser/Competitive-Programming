#include<bits/stdc++.h>
#define lp(i,j,n) for(int i=j;i<n;i++)
using namespace std;

typedef long long ll;
int n,m,k;
string a[2001];
int main() {
    ios_base::sync_with_stdio(0);cin.tie(NULL);
    cin>>n>>m>>k;
    lp(i,0,n)cin>>a[i];
    int cntDot=0;
    lp(i,0,n)lp(j,0,m)cntDot+=a[i][j]=='.';
    int res=0;
    lp(i,0,n){
        int con=0;
        lp(j,0,m){
            if(a[i][j]=='.')con++;
            else{
                if(con>=k)res+=con-k+1;
                con=0;
            }
        }
        if(con>=k)res+=con-k+1;
    }
    lp(j,0,m){
        int con=0;
        lp(i,0,n){
            if(a[i][j]=='.')con++;
            else{
                if(con>=k)res+=con-k+1;
                con=0;
            }
        }
        if(con>=k)res+=con-k+1;
    }
    if(--k)cout<<res<<"\n";
    else cout<<cntDot<<"\n";
}