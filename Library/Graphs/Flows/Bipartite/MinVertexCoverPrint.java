public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
        }


        int MAX = (int) 2e5 + 10;
        boolean isPrime[] = new boolean[MAX + 1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i * i <= MAX; i++) {
                for (long j = (long) i * i; j <= MAX; j += i) {
                        isPrime[(int) j] = false;
                }
        }

        ArrayList<Integer> L = new ArrayList<>(); // even
        ArrayList<Integer> R = new ArrayList<>(); // odd
        for (int i = 0; i < n; i++) {
                if ((arr[i] & 1) == 0) {
                        L.add(arr[i]);
                } else {
                        R.add(arr[i]);
                }
        }

        nn = L.size();
        m = L.size() + R.size();
        adjList = new ArrayList[nn + m + 1];
        for (int i = 0; i <= nn + m; i++)
                adjList[i] = new ArrayList<>();

        for (int i = 0; i < L.size(); i++) {
                for (int j = 0; j < R.size(); j++) {
                        // IF THERE IS AN EDGE BETWEEN THEM
                        if (isPrime[L.get(i) + R.get(j)]) {
                                adjList[i + 1].add(j + L.size() + 1);
                        }
                }
        }

        out.println(hopcroftKarp());
        HashSet<Integer> matched = new HashSet<>();
        for (int i = 1; i <= nn; i++) {
                if (pair_U[i] != NIL) {
                        matched.add(i);
                }
        }

        boolean selected [] = new boolean[n + 1];
        Queue<Integer> unselected_q = new LinkedList<>();
        for (int i = 1; i <= nn; i++) {
                if (matched.contains(i)) {
                        selected[i] = true;
                } else {
                        unselected_q.add(i);
                }
        }

        while (unselected_q.size() != 0) {
                int node = unselected_q.poll();

                for (int itr : adjList[node]) {
                        if (!selected[itr]) {
                                int oth = pair_V[itr];
                                if (selected[oth]) {
                                        unselected_q.add(oth);
                                        selected[oth] = false;
                                }
                                selected[itr] = true;
                        }
                }
        }

        boolean first = true;
        for (int i = 1; i <= n; i++) {
                if (selected[i]) {
                        if (first) {
                                first = false;
                        } else {
                                out.print(" ");
                        }
                        if (i > nn) out.print(R.get(i - nn - 1));
                        else out.print(L.get(i - 1));
                }
        }
        out.println();
        out.flush();
        out.close();
}

static int nn, m;
static ArrayList<Integer> adjList [];
static int[] pair_U, pair_V, dist;
static final int NIL = 0, INF = (int)1e9;
static int hopcroftKarp()
{
        pair_U = new int[nn + 1]; //filled with NIL
        pair_V = new int[m + 1];  //filled with NIL
        dist = new int[nn + 1];

        int matching = 0;
        while(bfs())
                for(int u = 1; u <= nn; ++u)
                        if(pair_U[u] == NIL && dfs(u))
                                ++matching;
        return matching;
}
static boolean bfs()
{
        Queue<Integer> q = new LinkedList<Integer>();
        for(int u = 1; u <= nn; ++u)
                if(pair_U[u] == NIL)
                {
                        dist[u] = 0;
                        q.add(u);
                }
                else
                        dist[u] = INF;
        dist[NIL] = INF;
        while(!q.isEmpty())
        {
                int u = q.remove();
                if(dist[u] < dist[NIL])
                        for(int v : adjList[u])
                                if(dist[pair_V[v]] == INF)
                                {
                                        dist[pair_V[v]] = dist[u] + 1;
                                        q.add(pair_V[v]);
                                }
        }
        return dist[NIL] != INF;
}

static boolean dfs(int u) {
        if (u == NIL)
                return true;

        for (int v : adjList[u])
                if (dist[pair_V[v]] == dist[u] + 1 && dfs(pair_V[v])) {
                        pair_U[u] = v;
                        pair_V[v] = u;
                        return true;
                }
        dist[u] = INF;
        return false;
}
