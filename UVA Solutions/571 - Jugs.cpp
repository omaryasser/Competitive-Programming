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

bool visited [1001][1001];
pair<int,int> parent[1001][1001];
void bfs (int A, int B, int N) {
    queue<pair<int,int> > q;
    q.push(mp(0, 0));
    visited[0][0] = true;
    int reachedA = -1;
    while(1) {
        pair<int,int> cur = q.front(); q.pop();
        int a = cur.first, b = cur.second;
        if (b == N) {
            reachedA = a;
            break;
        }
        if(!visited[a][B]) {
            visited[a][B] = true;
            parent[a][B] = mp(a, b);
            q.push(mp(a, B));
        }
        if (!visited[A][b]) {
            visited[A][b] = true;
            parent[A][b] = mp(a, b);
            q.push(mp(A, b));
        }
        if (!visited[0][b]) {
            visited[0][b] = true;
            parent[0][b] = mp(a, b);
            q.push(mp(0, b));
        }
        if (!visited[a][0]) {
            visited[a][0] = true;
            parent[a][0] = mp(a, b);
            q.push(mp(0, b));
        }

        // pour a -> b
        int change = min(a, B - b);
        if (!visited[a - change][b + change]) {
            visited[a - change][b + change] = true;
            parent[a - change][b + change] = mp(a, b);
            q.push(mp(a - change, b + change));
        }
        // pout b -> a
        change = min(b, A - a);
        if (!visited[a + change][b - change]) {
            visited[a + change][b - change] = true;
            parent[a + change][b - change] = mp(a, b);
            q.push(mp(a + change, b - change));
        }
    }
    vector<string> res;
    while(reachedA || N) {
        pair<int,int> bef = parent[reachedA][N];
        int lastA = bef.first, lastB = bef.second;
        if (reachedA != lastA && lastB != N) {
            if (reachedA > lastA) {
                res.pb("pour B A");
            } else {
                res.pb("pour A B");
            }
        }
        else if (reachedA == 0 && lastA) res.pb("empty A");
        else if (N == 0 && lastB) res.pb("empty B");
        else if (N == B && lastB != B) res.pb("fill B");
        else res.pb("fill A");
        reachedA = lastA;
        N = lastB;
    }
    FOR (i, 0, sz(res) - 1) cout << res[sz(res) - 1 - i] << "\n";
    cout << "success\n";
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int A, B, N;
    while(cin >> A >> B >> N) {
        memset(visited, false, sizeof visited);
        bfs(A, B, N);
    }
    return 0;
}
