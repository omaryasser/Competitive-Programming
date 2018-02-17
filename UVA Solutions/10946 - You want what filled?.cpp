 #include <bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= (int)n; i++)
#define mp(x, y) make_pair(x, y)
#define all(x) x.begin(),x.end()
#define sz(x) (int)x.size()
#define pb(x) push_back(x)
#define bug cerr << "HERE\n";
typedef long long ll;
typedef short int si;

using namespace std;

int n, m;
vector<string> grid(51);
int dx [] = {0, 0, 1, -1};
int dy [] = {1, -1, 0, 0};

bool valid (int x, int y) {
    return x >= 0 && y >= 0 && x < n && y < m;
}

bool visited[51][51];

int dfs (int x, int y, char c) {
    int res = 1;
    visited[x][y] = true;
    FOR (k, 0, 3) {
        int nx = x + dx[k], ny = y + dy[k];
        if (valid (nx, ny) && grid[nx][ny] == c && !visited[nx][ny]) res += dfs (nx, ny, c); 
    }
    return res;
}

struct Pair {
    int cnt;
    char c;
    Pair (int cntt, char cc) : cnt(cntt), c(cc){}
};
bool cmp (const Pair &a, const Pair &b) {
    return a.cnt == b.cnt ? a.c < b.c : a.cnt > b.cnt;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    FOR (tc, 1, 1000000000) {
        cin >> n >> m;
        if (!n && !m) break;

        FOR (i, 0, n - 1) cin >> grid[i];
        memset(visited, false, sizeof visited);
        vector<Pair> res;
        FOR (i, 0, n - 1) {
            FOR (j, 0, m - 1) {
                if (!visited[i][j] && grid[i][j] != '.') {
                    res.pb(Pair(dfs(i, j, grid[i][j]), grid[i][j]));
                }
            }
        }
        sort(all(res), cmp);
        cout << "Problem " << tc << ":\n";
        FOR (i, 0, sz(res) - 1) cout << res[i].c << " " << res[i].cnt << "\n";
    }
    return 0;
}
