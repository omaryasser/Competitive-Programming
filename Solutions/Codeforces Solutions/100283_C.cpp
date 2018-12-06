#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define double long double
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

bool isOne[(int)1e5+10];

string mk(int lst,vector<int>v){
    if(lst<0)return "";
    string res="";
    res+=to_string(v[0]);
    REP1(i,1,lst+1)res+="->"+to_string(v[i]);
    return res;
}
pair<int,string> go(vector<int>v,int n){
    if(v[n-1]!=0)return mp(0,"");
    if(n==1){
        if(v[0]==0){
            return mp(1,"0");
        }else{
            return mp(0,"");
        }
    }else{
        if((v[n-1]==0&&v[n-2]==1)||(v[n-1]==0&&isOne[n-2])){
            string res="";
            res+=mk(n-1,v);
            return mp(1,res);
        }else{
            if(n>=3&&v[n-3]==0){
                string res="";
                res+="(";
                REP(i,n-3)
                    res+=to_string(v[i])+"->";
                res+="(0->0))->0";
                return mp(1,res);
            }else{
                if(n>=4&&v[n-2]==0&&v[n-3]==1&&!isOne[n-4]){
                    string res="(";
                    res+=mk(n-4,v);
                    res+=")";
                    res+="->(1->0)->0";
                    return mp(1,res);
                }else if(n>=4&&v[n-2]==0&&v[n-3]==1&&v[n-4]==0){
                    string res="(";
                    res+=mk(n-5,v);
                    res+=")";
                    res+="->(0->(1->0))->0";
                    return mp(1,res);
                }else{
                    for(int i=n-5;i>=0;i--)
                        if(!v[i]){
                            string res="(";
                            REP1(j,i+1,n-1)
                                if(j!=i+1)res+="->"+to_string(v[j]);
                                else res+=to_string(v[j]);
                            res+=")";
//                            cerr<<res<<"\n";
                            res="(0->"+res+")";
                            if(i-1>=0)res="("+mk(i-1,v)+")->"+res;
                            res="("+res+")"+"->0";
                            return mp(1,res);
                        }
                    return mp(0,"");
                }
            }
        }
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin>>n;
    vector<int>v(n);
    REP(i,n)cin>>v[i];
    int cur=1;
    REP(i,n){
        isOne[i]=!(cur==1&&v[i]==0);
        cur=isOne[i];
    }
    pair<int,string>p=go(v,n);
    if(p.fi)cout<<"YES\n"<<p.se<<"\n";
    else cout<<"NO\n";
}