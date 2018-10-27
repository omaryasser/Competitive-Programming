import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static ArrayList<Integer> adjList [];
    static boolean visited [];
    static int dfs (int node) {
        visited[node] = true;
        int res = 1;
        for (int child : adjList[node])
            if (!visited[child]) {
                res += dfs(child);
            }

        return res;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            V = sc.nextInt();
            visited = new boolean[V];
            int E = sc.nextInt();
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();

            while (E-- > 0) {
                int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
                adjList[u].add(v);
                adjList[v].add(u);
            }
            int max = 0;
            for (int i = 0; i < V; i++)
                if (!visited[i]) {
                    max = Math.max(max, dfs(i));
                }
            out.println(max);
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
