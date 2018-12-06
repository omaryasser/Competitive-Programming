#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int N=2e5+10;
int dp[N],bst[3];
char s[N];

int main(){
    scanf("%s",s);
    int n=strlen(s);
    int mod=0;
    REP1(i,1,3)bst[i]=-2*N;
    REP(i,n){
        mod=(mod+s[i]-'0')%3;
        dp[i]=max(i?dp[i-1]:0,1+bst[mod]);
        bst[mod]=dp[i];
    }
    printf("%d\n",dp[n-1]);
}