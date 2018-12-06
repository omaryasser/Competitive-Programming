#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int N=301;
int len[N];
vector<int> s;
vector<int> abb;

vector<int> T,P;
int b[301], n, m;
bool strt[301];
void kmpPreProcess() {
    m = P.size();
    int i = 0, j = -1;
    b[0] = -1;
    while (i < m) {
        while (j >= 0 && P[i] != P[j]) j = b[j];
        i++;
        j++;
        b[i] = j;
    }
}

void kmpSearch() {
    int i = 0, j = 0;
    n = T.size();
    while (i < n) {
        while (j >= 0 && T[i] != P[j]) j = b[j];
        i++;
        j++;
        if (j == m) {
            strt[i-j]=1;
            j = b[j];
        }

    }
}

int mem[3][301];

int dp(int mad,int idx){
    if(idx==s.size())return mad<2?1e5+10:0;
    int &ret=mem[mad][idx];
    if(ret!=-1)return ret;
    ret=1e5+10;
    if(strt[idx])
        ret=min(ret,(int)abb.size()+(idx?1:0)+dp(min(2,mad+1),idx+abb.size()));
    else ret=len[idx]+(idx?1:0)+dp(mad,idx+1);
    return ret;
}
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int n;
    cin>>n;
    int res=0;
    unordered_map<string,int>mpp;
    int mpIdx=0;
    vector<string>strs;
    REP(i,n){
        string ss;
        cin>>ss;
        strs.push_back(ss);
        if(mpp.find(ss)==mpp.end())mpp[ss]=mpIdx++;
        s.push_back(mpp[ss]);
        len[i]=ss.length();
        res+=len[i];
        if(i)res++;
    }
    vector<vector<int> >abbs;
    REP(i,s.size()){
        vector<int>cur;
        REP1(j,i,s.size()){
            cur.push_back(mpp[strs[j]]);
            abbs.push_back(cur);
        }
    }
    T=s;
    for(vector<int> ab:abbs){
        abb=ab;
        P=abb;
        memset(strt,0, sizeof strt);
        kmpPreProcess();
        kmpSearch();
        memset(mem,-1,sizeof mem);
        res=min(res,dp(0,0));
    }
    cout<<res<<"\n";

    return 0;
}