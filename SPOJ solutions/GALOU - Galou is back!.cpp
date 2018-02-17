 #include <bits/stdc++.h>
#define FOR(i, s, n) for (int i = s; i <= (int)n; i++)
#define mp(x, y) make_pair(x, y)
#define all(x) x.begin(),x.end()
#define sz(x) (int)x.size()
#define bug cerr << "HERE\n";
typedef long long ll;
typedef short int si;
 
using namespace std;
int n, m;
vector<string> grid;
vector<vector<int> > state;
int LEFT = 0, RIGHT = 1, FREE = 2, BLOCKED = 3;
int dx [] = {0, 0, 1, -1, 1, -1};
int dy [] = {1, -1, 0, 0, -1, 1};
vector<vector<bool> > visited;
 
bool valid (int x, int y) {
    return x >= 0 && y >= 0 && x < n && y < m && grid[x][y] != '.';
}
void dfs (int x, int y) {
    visited[x][y] = true;
    FOR (k, 0, 5) {
        int nx = x + dx[k], ny = y + dy[k];
        if (valid(nx, ny) && !visited[nx][ny]) {
            state[nx][ny] = BLOCKED;
            dfs (nx, ny);
        }
    }
}
void bfs () {
    queue<pair<int,int> > q;
    FOR (i, 0, n - 1)
        FOR (j, 0, m - 1) {
            if (grid[i][j] == 'I') {
                state[i][j] = RIGHT;
                q.push(mp(i, j));
            }
        }
    while (!q.empty()) {
        pair<int,int> cur = q.front(); q.pop();
        int x = cur.first, y = cur.second;
        if (state[x][y] == BLOCKED) {
            continue;
        } else {
            FOR (k, 0, 5) {
                int nx = x + dx[k], ny = y + dy[k];
                if (valid (nx, ny) && (state[nx][ny] == BLOCKED || state[nx][ny] == state[x][y])) {
                    state[x][y] = state[nx][ny] = BLOCKED;
                } else if (valid (nx, ny)){
                    if (state[nx][ny] == 1 - state[x][y]) continue;
                    state[nx][ny] = 1 - state[x][y];
                    q.push (mp(nx, ny));
                }
            }
        } 
    }
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
 
    while(1) {
        cin >> n >> m;
        if (!n && !m) break;
 
        grid.assign(n, string());
        state.assign(n, vector<int>(m, FREE));
        visited.assign(n, vector<bool>(m, false));
        FOR (i, 0, n - 1) cin >> grid[i];
        bfs ();
        FOR (i, 0, n - 1) {
            FOR (j, 0, m - 1) {
                if (!visited[i][j] && state[i][j] == BLOCKED) {
                    dfs (i, j);
                }
            }
        }
        FOR (i, 0, n - 1) {
            FOR (j, 0, m - 1) {
                cout << (grid[i][j] == '.' ? '.' : state[i][j] == BLOCKED ? 'B' : state[i][j] == FREE ? 'F' : state[i][j] == RIGHT ? '(' : ')');
            }
            cout << "\n";
        }
    }
 
    return 0;
} 
