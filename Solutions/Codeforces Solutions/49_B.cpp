#include<bits/stdc++.h>

using namespace std;


int a, b;

int solve(int a,int b,int base){
    int c=0;
    int r=0;
    while(a||b||c){
        r++;
        int nc=((a%10)+(b%10)+c)/base;
        a/=10;
        b/=10;
        c=nc;
    }
    return r;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie();

    cin>>a>>b;
    int aa=a,bb=b;
    int mx=0;
    while(aa){
        mx=max(mx, aa%10);
        aa/=10;
    }
    while(bb){
        mx=max(mx,bb%10);
        bb/=10;
    }
    int ba=mx+1;
    cout<<solve(a,b,ba)<<"\n";

    return 0;
}