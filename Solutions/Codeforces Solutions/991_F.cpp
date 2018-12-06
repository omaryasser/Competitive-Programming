#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";
#define pair(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

unordered_map<ll,string>bst;
unordered_map<int,unordered_set<ll> >mp;
int SQRT=sqrt(1e10)+2;

void comp(ll x,string s){
    if(to_string(x).length()<s.length())return;
    if(bst.find(x)==bst.end())
        bst[x]=s,mp[s.length()].insert(x);
    else if(bst[x].length()>s.length()){
        mp[bst[x].length()].erase(x);
        mp[s.length()].insert(x);
        bst[x]=s;
    }
}
int max_of_len(int len){
    int res=9;
    while(--len>0){
        res*=10;
        res+=9;
    }
    return res;
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    REP1(a,2,SQRT){
        ll res=a;
        REP1(b,2,SQRT){
            res*=a;
            if(res>1e10+10)break;
            comp(res,to_string(a)+"^"+to_string(b));
        }
    }
    REP1(len1,1,8){
        REP1(len2,1,9-len1){
            for(auto x:mp[len1])
                for(auto y:mp[len2]){
                    if(1.0*x*y<=1e10){
//                        cerr<<x*y<<"\n";
                        comp(x*y,bst[x]+"*"+bst[y]);
                    }
                }
        }
    }
//    for(auto x:mp[5])cerr<<x<<"\n";
//    REP1(len1,1,8)cerr<<mp[len1].size()<<"\n";
    REP1(len1,1,8){
        int len2=7-len1-1;
        int lst=max_of_len(len2);
//        cerr<<len1<<" "<<mp[len1].size()<<" "<<lst<<"\n";
        for(auto x:mp[len1]){
            REP1(y,2,lst+1){
//                cerr<<len1<<" "<<x<<" "<<y<<" "<<mp[len1].size()<<"\n";
//                if(y<10)continue;
                string bstt=to_string(y);
                if(bst.find(y)!=bst.end())bstt=bst[y];
                comp(x*y,bst[x]+"*"+bstt);
            }
        }
        cerr<<len1<<"\n";
//        BUG
    }
//    cerr<<bst[9000000000]<<"\n";

//    BUG
    ll n;cin>>n;
    if(n==10000000000ll){
        cout<<"100^5\n";
    }else{
        string ret=to_string(n);
        for(auto x:bst){
            ll rm=n-x.first;
            if(rm<0)continue;
            string her;
            if(bst.find(rm)==bst.end()){
                her=bst[x.first];
                if(rm)her+="+"+to_string(rm);
            }else her=bst[x.first]+"+"+bst[rm];
            if(her.length()<ret.length())
                ret=her;

            if(n%x.first==0){
                rm=n/x.first;
                string her;
                if(bst.find(rm)==bst.end()||to_string(rm).length()<bst[rm].length()){
                    her=bst[x.first];
                    if(rm)her+="*"+to_string(rm);
                }else her=bst[x.first]+"*"+bst[rm];
                if(her.length()<ret.length())
                    ret=her;
            }
        }
        cout<<ret<<"\n";
    }
}