import java.io.*;
import java.util.*;


public class Main {
    static final int INF = (int) 1e9;
    static int V, s, t;
    static ArrayList<Integer>[] adjList;
    static int[][] res;
    static int[] p;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            int peopleNum = sc.nextInt(), taxisNum = sc.nextInt(), speed = sc.nextInt(), limit = sc.nextInt();
            int[] px = new int[peopleNum], py = new int[peopleNum], tx = new int[taxisNum], ty = new int[taxisNum];
            for (int i = 0; i < peopleNum; i++) {
                px[i] = sc.nextInt();
                py[i] = sc.nextInt();
            }
            for (int i = 0; i < taxisNum; i++) {
                tx[i] = sc.nextInt();
                ty[i] = sc.nextInt();
            }

            V = peopleNum + taxisNum + 2;
            t = V - 1;
            res = new int[V][V];
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();

            for (int i = 0; i < peopleNum; i++) {
                for (int j = 0; j < taxisNum; j++) {
                    if (1L * (Math.abs(px[i] - tx[j]) + Math.abs(py[i] - ty[j])) * 200 <= 1L * speed * limit) {
                        addEdge(i + 1, j + peopleNum + 1);
                    }
                }
                addEdge(s, i + 1);
            }
            for (int j = 0; j < taxisNum; j++)
                addEdge(j + peopleNum + 1, t);
            out.println(edmondsKarp());
        }
        out.close();
    }

    static void addEdge (int u, int v) {
        res[u][v] = 1;
        adjList[u].add(v);
        adjList[v].add(u);
    }
    static int augment(int v, int flow) {
        if (v == s)
            return flow;
        flow = augment(p[v], Math.min(res[p[v]][v], flow));
        res[p[v]][v] -= flow;
        res[v][p[v]] += flow;

        return flow;
    }

    static int edmondsKarp() {
        int mf = 0;
        while (true) {
            Queue<Integer> q = new LinkedList<Integer>();
            p = new int[V];
            Arrays.fill(p, -1);
            p[s] = s;
            q.add(s);
            while (!q.isEmpty()) {
                int u = q.remove();
                if (u == t)
                    break;
                for (int v : adjList[u])
                    if (res[u][v] > 0 && p[v] == -1) {
                        p[v] = u;
                        q.add(v);
                    }
            }

            if (p[t] == -1)
                break;
            mf += augment(t, INF);

        }
        return mf;
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader s) {
            br = new BufferedReader(s);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
            }
            return st.nextToken();
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }
}
