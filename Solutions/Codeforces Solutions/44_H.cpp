#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

ll mem[10][51];
string s;

ll dp(int lst,int idx){
    if(idx==s.length())return 1;
    ll &ret=mem[lst][idx];
    if(ret!=-1)return ret;
    ret=dp((lst+s[idx]-'0')/2,idx+1);
    if((lst+s[idx]-'0')&1)ret+=dp((lst+s[idx]-'0'+1)/2,idx+1);
    return ret;
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    memset(mem,-1,sizeof mem);
    cin>>s;
    ll res=0;
    REP(d,10)res+=dp(d,1);
    int lst=s[0]-'0',idx=1;
    while(idx<s.length()){
        if((lst+s[idx]-'0')/2==s[idx]-'0'||(lst+s[idx]-'0'+1)/2==s[idx]-'0')
            idx++,lst=s[idx-1]-'0';
        else break;
    }
    if(idx==s.length())res--;
    cout<<res<<"\n";
}