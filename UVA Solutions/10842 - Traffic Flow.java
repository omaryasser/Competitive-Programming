import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int testCases = sc.nextInt();
        for (int tc = 1; tc <= testCases; tc++) {
            V = sc.nextInt();
            int E = sc.nextInt();
            edges = new ArrayList<>();

            while (E-- > 0) {
                edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            }


            Collections.sort(edges);
            UnionFind uf = new UnionFind(V);
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < edges.size() && uf.numSets > 1; ++i) {
                Edge e = edges.get(i);
                if (!uf.isSameSet(e.from, e.to)) {
                    min = Math.min(min, e.cost);
                    uf.unionSet(e.from, e.to);
                }
            }
            out.printf("Case #%d: %d\n", tc, min);
        }

        out.flush();
        out.close();
    }

    static class UnionFind {
        int[] p, rank, setSize;
        int numSets;

        public UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            setSize = new int[N];
            numSets = N;
            for (int i = 0; i < N; i++) {
                p[i] = i;
                setSize[i] = 1;
            }
        }

        public int findSet(int i) {
            if (p[i] == i) return i;
            else return p[i] = findSet(p[i]);
        }

        public Boolean isSameSet(int i, int j) {
            return findSet(i) == findSet(j);
        }

        public void unionSet(int i, int j) {
            if (isSameSet(i, j))
                return;
            numSets--;
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) {
                p[y] = x;
                setSize[x] += setSize[y];
            } else {
                p[x] = y;
                setSize[y] += setSize[x];
                if (rank[x] == rank[y]) rank[y]++;
            }
        }

        public int numDisjointSets() {
            return numSets;
        }

        public int sizeOfSet(int i) {
            return setSize[findSet(i)];
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        Edge(int f, int t, int c) {
            from = f;
            to = t;
            cost = c;
        }

        @Override
        public int compareTo(Edge edge) {
            return edge.cost - cost;
        }
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader f) {
            br = new BufferedReader(f);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }
}
