import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import java.util.Random;
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            int V = sc.nextInt(), E = sc.nextInt();
            ArrayList<Integer> adjList [] = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();

            while (E-- > 0) {
                int u = sc.nextInt(), v = sc.nextInt();
                adjList[u].add(v);
                adjList[v].add(u);
            }

            Queue<Integer> q = new LinkedList<>();
            q.add(0);
            int [] dist = new int[V];
            Arrays.fill(dist, -1);
            dist[0] = 0;

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int nxt : adjList[cur]) {
                    if (dist[nxt] == -1) {
                        dist[nxt] = dist[cur] + 1;
                        q.add(nxt);
                    }
                }
            }

            for (int i = 1; i < V; i++) out.println(dist[i]);
            if (tc != 0) out.println();
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
        public Scanner(FileReader s) {
            br = new BufferedReader(s);
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
