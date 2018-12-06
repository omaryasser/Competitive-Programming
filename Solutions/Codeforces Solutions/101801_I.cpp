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


const int N=101;
vector<int>atk,def,b;
int n,m;
char s[4];
bool taken[N];
multiset<int>st;

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
    
    scanf("%d%d",&n,&m);
    REP(i,n){
        scanf("%s",s);
        int x;
        scanf("%d",&x);
        if(string(s)=="ATK")atk.pb(x);
        else def.pb(x);
    }
    REP(i,m){
        int x;
        scanf("%d",&x);
        b.pb(x);
        st.insert(x);
    }
    sort(all(atk)),sort(all(def)),sort(all(b));
    int all=0,res=0;
    bool ok=1;
    REP(i,def.size()){
        auto it=st.upper_bound(def[i]);
        if(it==st.end()){
            ok=0;
            break;
        }else st.erase(it);
    }
    if(ok){
        REP(i,atk.size()){
            auto it=st.lower_bound(atk[i]);
            if(it==st.end()){
                ok=0;
                break;
            }else {
                all+=(*it)-atk[i];
                st.erase(it);
            }
        } 
        if(ok){
            for(int x:st)all+=x;
            res=all;
        }     
    }
    REP(s1,atk.size()){
        REP(s2,b.size()){
            int mnrem=min(atk.size()-s1,b.size()-s2);
            all=0;
            REP(j,mnrem){
                all+=b[s2+j]-atk[s1+j];
                if(b[s2+j]<atk[s1+j]){
                    all=0;
                    break;
                }
            }
            res=max(res,all);
        }
    }
    printf("%d\n", res);
    return 0;
}