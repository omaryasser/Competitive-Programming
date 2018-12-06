#include <bits/stdc++.h>

using namespace std;

int n;
const int N=21;
int a[N],b[N];
int v;

int main() {
	#ifndef ONLINE_JUDGE
	freopen("in.txt", "r", stdin);
	#endif

	scanf("%d%d",&n,&v);

	for(int i=0;i<n;i++)
		scanf("%d",&a[i]);

	int g=a[0];
	for(int i=1;i<n;i++)
		g=__gcd(g,a[i]);
	for(int i=0;i<n;i++)
		a[i]/=g;

	for(int i=0;i<n;i++)
			scanf("%d",&b[i]);

	double lo=0,hi=100,best=lo;
	for(int cnt=0;cnt<=1000;cnt++){
		double m=(lo+hi)/2;
		bool ok=1;
		double s=0;
		for(int i=0;i<n;i++)
			ok&=a[i]*m<=b[i],s+=a[i]*m;
		ok&=s<=v;
		if(ok){
			best=m;
			lo=m+1;
		}else{
			hi=m-1;
		}
	}
	double r=0;
	for(int i=0;i<n;i++)
		r+=best*a[i];
	printf("%.8f\n",r);
}