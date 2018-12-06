#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


char s[101];
int main(){
//    ios_base::sync_with_stdio(0);cin.tie(0);
    scanf("%s",s);
    int n=strlen(s);
    string v="aouie";
    bool ok=1;
    REP(i,n){
        if(s[i]!='n'&&v.find(s[i])==-1){
            ok&=(i+1<n&&v.find(s[i+1])!=-1);
        }
    }
    printf("%s\n",ok?"YES":"NO");
}