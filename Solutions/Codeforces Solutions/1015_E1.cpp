#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n, m;
const int n_ = 1001;
char a[n_][n_];
int up[n_][n_], down[n_][n_], left_[n_][n_], right_[n_][n_];
bool done[n_][n_];
int okH[n_][n_], okV[n_][n_];
int main() {
    scanf("%d %d", &n, &m);
    for(int i = 0; i < n; i++)
        scanf("%s", a[i]);
    for(int i = 0; i < n; i++) {
        int cnt = a[i][0] == '*';
        for(int j = 1; j < m; j++) {
            left_[i][j] = cnt;
            if(a[i][j] == '*') cnt++;
            else cnt = 0;
        }
    }
    for(int i = 0; i < n; i++) {
        int cnt = a[i][m - 1] == '*';
        for(int j = m - 2; j >= 0; j--) {
            right_[i][j] = cnt;
            if(a[i][j] == '*') cnt++;
            else cnt = 0;
        }
    }
    for(int j = 0; j < m; j++) {
        int cnt = a[0][j] == '*';
        for(int i = 1; i < n; i++) {
            up[i][j] = cnt;
            if(a[i][j] == '*') cnt++;
            else cnt = 0;
        }
    }

    for(int j = 0; j < m; j++) {
        int cnt = a[n - 1][j] == '*';
        for(int i = n - 2; i >= 0; i--) {
            down[i][j] = cnt;
            if(a[i][j] == '*') cnt++;
            else cnt = 0;
        }
    }

    vector<pair<pair<int,int>, int> > res;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            int mn = min(up[i][j], min(down[i][j], min(left_[i][j], right_[i][j])));
            if(mn && a[i][j] == '*') {
                okH[i][j - mn] = max(okH[i][j - mn], mn * 2 + 1);
                okV[i - mn][j] = max(okV[i - mn][j], mn * 2 + 1);
                res.push_back({{i, j}, mn});
            }
        }
    }
    for(int i = 0; i < n; i++) {
        int remOk = 0;
        for(int j = 0; j < m; j++) {
            remOk = max(remOk, okH[i][j]);
            if(remOk) {
                done[i][j] = 1;
                remOk--;
            }
        }
    }
    for(int j = 0; j < m; j++) {
        int remOk = 0;
        for(int i = 0; i < n; i++) {
            remOk = max(remOk, okV[i][j]);
            if(remOk) {
                done[i][j] = 1;
                remOk--;
            }
        }
    }
    bool ok = 1;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            if(a[i][j] == '*') {
                ok &= done[i][j];
            }
        }
    }
    if(ok) {
        int sz = (int)res.size();
        printf("%d\n", sz);
        for(auto p : res) {
            printf("%d %d %d\n", p.first.first + 1, p.first.second + 1, p.second);
        }
    } else {
        printf("-1\n");
    }
}
