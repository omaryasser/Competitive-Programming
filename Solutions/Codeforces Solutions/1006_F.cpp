#include <bits/stdc++.h>
#include <bitset>

#define REP(i, n) for(int i=0;i<(int)n;i++)
#define REP1(i, j, n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";
#define mp(x, y) make_pair(x,y)
typedef long long ll;

using namespace std;

ll a[21][21];
unordered_map<ll, ll> cnt[2][21];
int n, m;

bool valid(int r, int c) {
    return r >= 0 && r < n && c >= 0 && c < m;
}

ll k;
int dx[2][2] = {{0, 1},
                {0, -1}};
int dy[2][2] = {{1,  0},
                {-1, 0}};

bool reach(int r, int c, int idx) {
    return idx == 0 && r + c == n - 1 || idx == 1 && n - 1 - r + m - 1 - c == m - 1;
}

void go(int r, int c, ll sum, int idx) {
    if (reach(r, c, idx)) {
        cnt[idx][r][sum]++;
        return;
    }
    REP(kk, 2) {
        int nx = r + dx[idx][kk], ny = c + dy[idx][kk];
        if (valid(nx, ny)) {
            go(nx, ny, (reach(nx, ny, idx) && idx == 1) ? sum : sum ^ a[nx][ny], idx);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin >> n >> m >> k;
    REP(i, n)REP(j, m)cin >> a[i][j];
    go(0,0,a[0][0],0);
    go(n-1,m-1,reach(n-1, m-1,1)?0:a[n-1][m-1],1);
    ll res=0;
    REP(i,n) {
        for (auto p:cnt[0][i]) {
            res += p.second * cnt[1][i][k ^ p.first];
        }
    }
    cout << res << "\n";
}