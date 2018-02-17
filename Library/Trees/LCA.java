
// < O(n) , O (sqrt (height)) >

static int N;
static int parent [];
static int level [];
static int levelParent [];
static int root;
static int sqrt;
static ArrayList<Integer> adjList [];
static void fillLevels (int node) {
        for (int child : adjList[node]) {
                if (child != parent[node]) {
                        level[child] = level[node] + 1;
                        fillLevels(child);
                }
        }
}
static void dfs (int node) {
        if (level[node] < sqrt)
                levelParent[node] = 1;
        else if (level[node] % sqrt == 0)
                levelParent[node] = parent[node];
        else
                levelParent[node] = levelParent[parent[node]];
        for (int child : adjList[node])
                dfs(child);
}
static int query (int x, int y) {
        while (levelParent[x] != levelParent[y]) {
                if (level[x] > level[y])
                        x = levelParent[x];
                else
                        y = levelParent[y];
        }

        while (x != y) {
                if (level[x] > level[y])
                        x = parent[x];
                else
                        y = parent[y];
        }
        return x;
}
public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int T = sc.nextInt();
        for (int TT = 1; TT <= T; TT++ ) {
                N = sc.nextInt();
                parent = new int[N];
                level = new int[N];
                levelParent = new int[N];
                Arrays.fill(parent, -1);
                adjList = new ArrayList[N];
                for (int i = 0; i < N; ++i)
                        adjList[i] = new ArrayList<>();
                sqrt = (int)Math.sqrt(N);
                for (int i = 0; i < N; ++i) {
                        int M = sc.nextInt();
                        while (M-- > 0) {
                                int child = sc.nextInt() - 1;
                                adjList[i].add(child);
                                parent[child] = i;
                        }
                }
                for (int i = 0; i < N; ++i)
                        if (parent[i] == -1)
                                root = i;
                level[root] = 0;
                fillLevels(root);
                dfs(root);
                out.printf("Case %d:\n", TT);
                int Q = sc.nextInt();
                while (Q-- > 0) {
                        int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
                        out.println(query(u, v) + 1);
                }
        }

        out.close();
}





// <O(N log N) , O(log N) >

static int N;
static int N;
static int parent [];
static int parentLog [][];
static int level [];
static int root;
static int maxLog;
static ArrayList<Integer> adjList [];
static void fillLevels (int node) {
        for (int child : adjList[node]) {
                if (child != parent[node]) {
                        level[child] = level[node] + 1;
                        fillLevels(child);
                }
        }
}

static int query (int x, int y) {
        if (level[x] < level[y]) {
                x ^= y;
                y ^= x;
                x ^= y;
        }
        // Now X is in the same level or below Y

        // Now get the ancestor of X which is in the same level as Y
        for (int log = maxLog; log >= 0; --log)
                if (level[x] - (1 << log) >= level[y])
                        x = parentLog[x][log];
        // Now X is in the same level as Y

        if (x == y) return x;
        for (int log = maxLog; log >= 0; --log)
                if (parentLog[x][log] != -1 && parentLog[x][log] != parentLog[y][log]) {
                        x = parentLog[x][log];
                        y = parentLog[y][log];
                }
        return parent[x];
}
public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int T = sc.nextInt();
        for (int TT = 1; TT <= T; TT++ ) {
                N = sc.nextInt();
                adjList = new ArrayList[N];
                parent = new int[N];
                level = new int[N];
                Arrays.fill(parent, -1);

                for (int i = 0; i < N; ++i)
                        adjList[i] = new ArrayList<>();

                for (int i = 0; i < N; ++i) {
                        int M = sc.nextInt();
                        while (M-- > 0) {
                                int child = sc.nextInt() - 1;
                                parent[child] = i;
                                adjList[i].add(child);
                        }
                }

                for (int i = 0; i < N; ++i)
                        if (parent[i] == -1)
                                root = i;
                level[root] = 0;
                fillLevels(root);

                maxLog = 0;
                for (; 1 << maxLog <= N; maxLog++ ) ;
                maxLog--;

                parentLog = new int[N][maxLog + 1];
                for (int i = 0; i < N; ++i)
                        Arrays.fill(parentLog[i], -1);

                for (int i = 0; i < N; ++i)
                        parentLog[i][0] = parent[i];

                for (int j = 1; j <= maxLog; ++j)
                        for (int i = 0; i < N; ++i)
                                if (parentLog[i][j - 1] != -1)
                                        parentLog[i][j] = parentLog[parentLog[i][j - 1]][j - 1];

                out.printf("Case %d:\n", TT);
                int Q = sc.nextInt();
                while (Q-- > 0) {
                        out.println(query(sc.nextInt() - 1, sc.nextInt() - 1) + 1);
                }
        }
        out.close();
}

// <O(n), O(1)>
const int MAXN = 100 * 1000;
const int MAXLIST = MAXN * 2;
const int LOG_MAXLIST = 18;
const int SQRT_MAXLIST = 447;
const int MAXBLOCKS = MAXLIST / ((LOG_MAXLIST + 1) / 2) + 1;

int n, root;
vector <int> g [MAXN];
int h [MAXN]; // vertex height
vector <int> a; // dfs list
int a_pos [MAXN]; // positions in dfs list
int block; // block size = 0.5 log A.size ()
int bt [MAXBLOCKS] [LOG_MAXLIST + 1]; // sparse table on blocks (relative minimum positions in blocks)
int bhash [MAXBLOCKS]; // block hashes
int brmq [SQRT_MAXLIST] [LOG_MAXLIST / 2] [LOG_MAXLIST / 2]; // rmq inside each block, indexed by block hash
int log2 [2 * MAXN]; // precalced logarithms (floored values)

// walk graph
void dfs (int v, int curh) {
        h [v] = curh;
        a_pos [v] = (int) a.size ();
        a.push_back (v);
        for (size_t i = 0; i <g [v].size (); ++i)
                if (h [g [v] [i]] == -1) {
                        dfs (g [v] [i], curh + 1);
                        a.push_back (v);
                }
}

int log (int n) {
        int res = 1;
        while (1 << res <n) ++res;
        return res;
}

// compares two indices in a
inline int min_h (int i, int j) {
        return h [a [i]] <h [a [j]] ? i : j;
}

// O (N) preprocessing
void build_lca () {
        int sz = (int) a.size ();
        block = (log (sz) + 1) / 2;
        int blocks = sz / block + (sz% block ? 1 : 0);

        // precalc in each block and build sparse table
        memset (bt, 255, sizeof bt);
        for (int i = 0, bl = 0, j = 0; i <sz; ++i, ++j) {
                if (j == block)
                        j = 0, ++bl;
                if (bt [bl] [0] == -1 || min_h (i, bt [bl] [0]) == i)
                        bt [bl] [0] = i;
        }
        for (int j = 1; j <= log (sz); ++j)
                for (int i = 0; i <blocks; ++i) {
                        int ni = i + (1 << (j-1));
                        if (ni> = blocks)
                                bt [i] [j] = bt [i] [j-1];
                        else
                                bt [i] [j] = min_h (bt [i] [j-1], bt [ni] [j-1]);
                }

        // calc hashes of blocks
        memset (bhash, 0, sizeof bhash);
        for (int i = 0, bl = 0, j = 0; i <sz || j <block; ++i, ++j) {
                if (j == block)
                        j = 0, ++bl;
                if (j> 0 && (i> = sz || min_h (i-1, i) == i-1))
                        bhash [bl] + = 1 << (j-1);
        }

        // precalc RMQ inside each unique block
        memset (brmq, 255, sizeof brmq);
        for (int i = 0; i <blocks; ++i) {
                int id = bhash [i];
                if (brmq [id] [0] [0] ! = -1) continue;
                for (int l = 0; l <block; ++l) {
                        brmq [id] [l] [l] = l;
                        for (int r = l + 1; r <block; ++r) {
                                brmq [id] [l] [r] = brmq [id] [l] [r-1];
                                if (i * block + r <sz)
                                        brmq [id] [l] [r] =
                                                min_h (i * block + brmq [id] [l] [r], i * block + r) - i * block;
                        }
                }
        }

        // precalc logarithms
        for (int i = 0, j = 0; i <sz; ++i) {
                if (1 << (j + 1) <= i) ++j;
                log2 [i] = j;
        }
}

// answers RMQ in block #bl [l; r] in O (1)
inline int lca_in_block (int bl, int l, int r) {
        return brmq [bhash [bl]] [l] [r] + bl * block;
}

// answers LCA in O (1)
int lca (int v1, int v2) {
        int l = a_pos [v1], r = a_pos [v2];
        if (l> r) swap (l, r);
        int bl = l / block, br = r / block;
        if (bl == br)
                return a [lca_in_block (bl, l% block, r% block)];
        int ans1 = lca_in_block (bl, l% block, block-1);
        int ans2 = lca_in_block (br, 0, r% block);
        int ans = min_h (ans1, ans2);
        if (bl <br - 1) {
                int pw2 = log2 [br-bl-1];
                int ans3 = bt [bl + 1] [pw2];
                int ans4 = bt [br- (1 << pw2)] [pw2];
                ans = min_h (ans, min_h (ans3, ans4));
        }
        return a [ans];
}
