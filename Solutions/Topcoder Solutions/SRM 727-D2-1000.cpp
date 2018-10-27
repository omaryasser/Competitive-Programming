#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

const int MAX = 302;
int mem[MAX][MAX][MAX];
int INF = MAX << 1;
string s, a, b;
int len_s, len_a, len_b;
int dp(int i, int j, int k) {
    if(k == len_b) return INF;
    if(i == len_s) return k >= len_b || j < len_a ? INF : 0;
    int &ret = mem[i][j][k];
    if(ret != -1) return ret;
    // put nxt in a
    ret = INF;
    if(j < len_a) ret = 1 + dp(i, j + 1, k + (a[j] == b[k]));
    ret = min(ret, dp(i + 1, j + (j < len_a && a[j] == s[i]), k + (k < len_b && b[k] == s[i])));
    return ret;
}
class ManageSubsequences {
public:
    int minAdded(string S, string A, string B) {
        s = S + "#";
        a = A;
        b = B;
        len_s = (int)s.length();
        len_a = (int)a.length();
        len_b = (int)b.length();
        memset(mem, -1, sizeof mem);
        return (dp(0, 0, 0) >= INF ? -1 : dp(0, 0, 0));
    }
};
