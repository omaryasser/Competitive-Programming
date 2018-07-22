import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, cost;
        Edge (int t, int c) {
            to = t;
            cost = c;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            int V = sc.nextInt();
            if (V == 0) break;
            int E = sc.nextInt();

            V += 2;
            ArrayList<Edge> adjList [] = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();

            while (E-- > 0) {
                int s = sc.nextInt(), e = sc.nextInt();
                String op = sc.next();
                int k = sc.nextInt();
                e += s;
                e++;
                s++;

                if (op.equals("gt")) {
                    adjList[s - 1].add(new Edge(e, -(k+1)));
                } else {
                    adjList[e].add(new Edge(s - 1, k - 1));
                }
            }

            for (int i = 1; i < V; i++) adjList[0].add(new Edge(0, i));
            int dist [] = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE / 2 - 1);
            dist[0] = 0;

            for (int k = 0; k < V - 1; k++) for (int i = 0; i < V; i++) {
                for (Edge edge : adjList[i]) {
                    dist[edge.to] = Math.min(dist[edge.to], dist[i] + edge.cost);
                }
            }

            boolean modified = false;
            for (int i = 0; i < V; i++) {
                for (Edge edge : adjList[i]) {
                    if (dist[i] + edge.cost < dist[edge.to]) modified = true;
                }
            }

            if (modified) out.println("successful conspiracy");
            else out.println("lamentable kingdom");
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
