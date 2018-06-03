#include <bits/stdc++.h>
#include <bitset>

#define REP(i, n) for(int i=0;i<(int)n;i++)
#define REP1(i, j, n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define double long double
#define mp(x, y) make_pair(x,y)

typedef long long ll;

using namespace std;
const int MAX = 200001;
int ways[3][3][MAX];
int ALL[MAX];
int sum[MAX];
int p[3];
ll mem[21][MAX];


class SRMIntermissionPhase {
public:
    vector<string> d;
    int MOD = 1e9 + 7;
    int n;
    int summ(int a, int b) {
        return ((sum[b] - (a == 0 ? 0 : sum[a - 1]) % MOD) + MOD) % MOD;
    }
    ll dp(int i, int sm) {
        if (i == n)return 1;
        if(sm>=MAX)return 0;
        ll &r = mem[i][sm];
        if (r != -1)return r;
        if (d[i] == "YYY") {
            r = sm?ALL[sm]:0;
        } else if (d[i] == "YNN") {
            r=p[0]>=sm&&sm?1:0;
        } else if (d[i] == "NYN") {
            r=p[1]>=sm&&sm?1:0;
        }
        else if (d[i] == "NNY") {
            r=p[2]>=sm&&sm?1:0;
        }
        else if (d[i] == "NYY") {
            r=sm?ways[1][2][sm]:0;
        } else if (d[i] == "YYN") {
            r=sm?ways[0][1][sm]:0;
        } else if (d[i] == "YNY") {
            r=sm?ways[0][2][sm]:0;
        } else {
            r=sm==0;
        }
        r*=dp(i+1,sm+1);
        r%=MOD;
        if(sm+1<MAX)r+=dp(i,sm+1);
        r%=MOD;
        return r;
    }

    int countWays(vector<int> points, vector<string> description) {
        reverse(all(description));
        n = description.size();
        REP(i, 3)p[i] = points[i];
        d = description;
        REP(i, 3)REP1(j, i + 1, 3) {
                REP(md, MAX) {
                    if(p[i]+p[j]>=md&&md>=2)
                        ways[i][j][md] = max(0, min(p[i], md-1) - max(1, md - p[j]) + 1);
                    // if(md<10)cerr<<"i = "<<p[i]<<" j = "<<p[j]<<" md = "<<md<<" res = "<<ways[i][j][md]<<" s "<<max(1, md - p[j])<<" e = "<< min(p[i], md-1)<<"\n";
                }
            }
        REP(i, MAX) {
            sum[i] = ways[1][2][i];
            if (i)sum[i] = (sum[i - 1] + sum[i]) % MOD;
        }
        // cerr<<p[1];
        REP(md, MAX) {
            int f=min(MAX - 1, max(1, md - p[1] - p[2])), s=max(0,min(p[0], md-2));
            ALL[md] = md>=3&&p[0]+p[1]+p[2]>=md?max(0, summ(md-s,md-f)):0;
            // if(md<10){
                // cerr<<"md = "<<md<<" res = "<<ALL[md]<<" start = "<<f<<" "<<s<<"\n";
                // cerr<<md-s<<" "<<md-f<<"\n";
            // }
        }

        memset(mem, -1, sizeof mem);
        for(int sm=MAX-1;sm>=0;sm--)
            for(int i=n-1;i>=0;i--)
                dp(i,sm);
            // REP(i,10)cerr<<i<<" "<<dp(0,i)<<" "<<ALL[6]<<"\n";
        return dp(0, 0);
    }
};
