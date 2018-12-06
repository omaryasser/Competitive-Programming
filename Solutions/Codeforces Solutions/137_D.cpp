#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

string arr;
int n;
const int N=501;
int mem[N][N];
int chan[N][N];
vector<string>res;

int change(int s,int e){
    int &ret=chan[s][e];
    if(ret!=-1)return ret;
    if(s==e||s>e)return 0;
    return ret=arr[s]!=arr[e]?1+change(s+1,e-1):change(s+1,e-1);
}

int dp(int s,int k){
    int &ret=mem[s][k];
    if(ret!=-1)return ret;
    ret=change(s,n-1);
    if(k)REP1(i,s+1,n)ret=min(ret,change(s,i-1)+dp(i,k-1));
    return ret;
}

string make(int s,int e){
    REP1(i,s,e+1)
        if(arr[i]!=arr[e-(i-s)])arr[i]=arr[e-(i-s)];
    string ret="";
    REP1(i,s,e+1)ret+=arr[i];
    return ret;
}
void go(int s,int k){
    if(dp(s,k)==change(s,n-1))res.push_back(make(s,n-1));
    else{
        REP1(i,s+1,n)
            if(dp(s,k)==change(s,i-1)+dp(i,k-1)){
                res.push_back(make(s,i-1));
                go(i,k-1);
                return;
            }
    }
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>arr;
    n=arr.length();
    int k;cin>>k;
    memset(mem,-1,sizeof mem);
    memset(chan,-1,sizeof chan);
    cout<<dp(0,k-1)<<"\n";
    go(0,k-1);
    REP(i,res.size()){
        if(i)cout<<"+";
        cout<<res[i];
    }
    cout<<"\n";
}