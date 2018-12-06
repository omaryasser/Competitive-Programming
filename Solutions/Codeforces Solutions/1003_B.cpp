#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

string solve(int a,int b,int x,int strt){
    string ret="";
    int cur=strt;
    ret=to_string(cur);
    if(!cur)a--;
    else b--;
    while(x--){
        cur^=1;
        if(!cur)a--;
        else b--;
        ret+=to_string(cur);
    }
    if(a<0||b<0)return ret;
    if(a){
        int f=-1;
        REP(i,ret.length())
        if(ret[i]=='0')f=i;
        string tmp=ret.substr(0,f),tmp2=ret.substr(f,ret.length());
        string res="";
        while(a--)res+="0";
        ret=tmp+res+tmp2;
    }
    if(b){
        int f=-1;
        REP(i,ret.length())
            if(ret[i]=='1')f=i;
        string tmp=ret.substr(0,f),tmp2=ret.substr(f,ret.length());
        string res="";
        while(b--)res+="1";
        ret=tmp+res+tmp2;
    }
    return ret;
}
bool ok(int a,int b,int x,string s){
    REP(i,s.length())if(s[i]=='0')a--;else b--;
    REP(i,s.length()-1)if(s[i]!=s[i+1])x--;
    return !a&&!b&&!x;
}
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int a,b,x;
    cin>>a>>b>>x;
    string ret1=solve(a,b,x,0),ret2=solve(a,b,x,1);
    if(ok(a,b,x,ret1))cout<<ret1<<"\n";
    else cout<<ret2<<"\n";
    return 0;
}