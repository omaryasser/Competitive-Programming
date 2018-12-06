#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";
#define mp(x,y) make_pair(x,y)
typedef long long ll;

using namespace std;

int main(){
    ios_base::sync_with_stdio(0); cin.tie(0);
    int n;
    cin>>n;
    string s1,s2;
    cin>>s1>>s2;
    int res=0;
    REP(i,(n-1)/2+1){
//        int bst=5;
//        REP(a,26)REP(b,26){
//            map<int,int>mp;
//            mp[a]++;if(i!=n-i-1)mp[b]++;
//            mp[(int)(s2[i]-'a')]++;
//            if(i!=n-i-1)mp[(int)(s2[n-i-1]-'a')]++;
//            cerr<<a<<" "<<b<<" "<<(int)s2[i]-'a'<<" "<<(int)s2[n-i-1]-'a'<<"\n";
//            bool ok=1;
//            for(auto p:mp){
//                cerr<<p.second<<"\n";
//                ok&=!(p.second&1);
//            }
//            cerr<<ok<<"\n";
//
//            if(ok)bst=min(bst,(int)(a!=s1[i]-'a')+(b!=s1[n-1-i]-'a'));
//                if(i==3&&bst==0)cerr<<a<<" "<<b<<"\n";
//        }
//        cerr<<bst<<"\n";
        if(i==n-i-1){
            if(s1[i]!=s2[i])res++;
            break;
        }
        vector<char>v1,v2;
        v1.push_back(s1[i]);
        v2.push_back(s2[i]);
        v1.push_back(s1[n-i-1]);
        v2.push_back(s2[n-i-1]);
        sort(all(v1));sort(all(v2));
        res+=min((int)(s2[i]==s2[n-i-1]?v1[0]!=v1[1]:2134),min((int)((v1[0]!=v2[0])+(v1[1]!=v2[1])),(int)((v1[0]!=v2[1])+(v1[1]!=v2[0]))));
    }
    cout<<res<<"\n";
}