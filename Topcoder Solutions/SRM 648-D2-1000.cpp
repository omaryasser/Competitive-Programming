#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int k, n;
const int n_ = 31;
int mem[n_][n_][n_][n_ * n_];
int dp(int a, int b, int c, int left) {
    if(left == 0) {
        return 1;
    }
    if(a + b + c == n) return 0;
    int &ret = mem[a][b][c][left];
    if(ret != -1) return ret;
    ret = 0;
    ret |= dp(a + 1, b, c, left);
    if(left >= a) ret |= dp(a, b + 1, c, left - a);
    if(left >= a + b) ret |= dp(a, b, c + 1, left - a - b);
    return ret;
}
class ABC {
public:
    string createString(int N, int K) {
        n = N;
        k = K;
        memset(mem, -1, sizeof mem);
        int a = 0, b = 0, c = 0, left = k;
        string res = "";
        for(int i = 0; i < n; i++) {
            if(dp(a + 1, b, c, left)) {
                res += "A";
                a++;
            } else if(left >= a && dp(a, b + 1, c, left - a)) {
                res += "B";
                b++;
                left -= a;
            } else if(left >= a + b && dp(a, b, c + 1, left - a - b)) {
                res += "C";
                c++;
                left -= a + b;
            } else {
                return "";
            }
        }
        return res;
    }
};
