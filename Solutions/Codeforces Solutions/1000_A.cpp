#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin>>n;
    unordered_map<string,int>mp;
    unordered_map<string,int>mp2;
    REP(i,n){
        string s;
        cin>>s;
        mp[s]++;
    }
    REP(i,n){
        string s;
        cin>>s;
        mp2[s]++;
    }
    int cnt=0;
    for(auto p:mp){
        cnt+=abs(mp[p.first]-min(mp2[p.first],mp[p.first]));
    }
    cout<<cnt<<"\n";
}