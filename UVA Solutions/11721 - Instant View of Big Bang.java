import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int from, to, cost;
        Edge (int f, int t, int c) {
            from = f;
            to = t;
            cost = c;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int testCases = sc.nextInt();
        for (int tc = 1; tc <= testCases; tc++){
            int V = sc.nextInt(), E = sc.nextInt();

            ArrayList<Integer> adjListR [] = new ArrayList[V];
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adjListR[i] = new ArrayList<>();
            }

            while (E-- > 0) {
                int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
                edges.add(new Edge(v, u, w));
                adjListR[v].add(u);
            }
            int dist [] = new int[V];
            Arrays.fill(dist, (int)1e9);
            dist[0] = 0;
            for (int i = 0; i < V - 1; i++) {
                for (Edge nxt : edges) {
                    dist[nxt.to] = Math.min(dist[nxt.to], dist[nxt.from] + nxt.cost);
                }
            }
            out.printf("Case %d:", tc);
            Queue<Integer> q = new LinkedList<>();
            boolean taken [] = new boolean[V];
            boolean cycleDetected = false;
            for (Edge nxt : edges) {
                if (dist[nxt.to] > dist[nxt.from] + nxt.cost) {
                    taken[nxt.to] = true;
                    cycleDetected = true;
                }
            }
            for (int i = 0; i < V; i++) if (taken[i]) q.add(i);
            while (!q.isEmpty()) {
                int x = q.poll();
                for (int nxt : adjListR[x]) {
                    if (!taken[nxt]) {
                        taken[nxt] = true;
                        q.add(nxt);
                    }
                }
            }
            if (!cycleDetected) {
                out.print(" impossible\n");
            }
            else {
                for (int i = 0; i < V; i++) if (taken[i]) out.print(" " + i);
                out.print("\n");
            }
        }

        out.flush();
        out.close();
    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner (FileReader f) {br = new BufferedReader(f);}

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
