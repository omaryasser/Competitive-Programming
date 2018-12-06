#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

int main(){
    string s;
    cin>>s;
    string res="";
    int pos=-1;
    int n=s.length();
    int cnt=0;
    REP(i,n)
        if(s[i]!='1'){
        res+=s[i];
            if(s[i]=='2'&&pos==-1)pos=res.length();
        }
        else{
            cnt++;

        }
    if(pos==-1)cout<<res+string(cnt,'1');
    else{
        cout<<res.substr(0,pos-1)+string(cnt,'1')+res.substr(pos-1,res.length())<<"\n";
    }
}