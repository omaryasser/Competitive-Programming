#include<bits/stdc++.h>
#define lp(i,j,n) for(int i=j;i<n;i++)
using namespace std;

typedef long long ll;
int n,m;
int a[5001],b[5001];
int main() {
    scanf("%d%d",&n,&m);
    double res=0;
    lp(i,0,n)scanf("%d%d",a+i,b+i);
    lp(i,0,n){
        bool ok=1;
        lp(j,0,n)ok&=a[i]*b[j]<=b[i]*a[j];
        if(ok){printf("%.8f\n",1.0*a[i]*m/b[i]);return 0;}
    }
}