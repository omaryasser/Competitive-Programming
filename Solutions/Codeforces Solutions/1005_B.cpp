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
char s1[N],s2[N];

int main() {
    scanf("%s%s",s1,s2);
    int cnt=0;
    for(int i=strlen(s1)-1,j=strlen(s2)-1;j>=0&&i>=0;j--,i--)
        if(s1[i]!=s2[j])break;
        else cnt++;
    cout<<strlen(s1)+strlen(s2)-2*cnt<<"\n";
}